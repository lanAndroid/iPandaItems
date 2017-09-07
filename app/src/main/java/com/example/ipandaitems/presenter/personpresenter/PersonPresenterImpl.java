package com.example.ipandaitems.presenter.personpresenter;

import android.content.Context;
import android.util.Log;

import com.example.ipandaitems.model.Callback;
import com.example.ipandaitems.model.entry.person.UserBean;
import com.example.ipandaitems.model.person.PersonModle;
import com.example.ipandaitems.view.registerandlogin.fragment.iLogActivity;

/**
 * Created by xiaogang on 2017/9/6.
 */

public class PersonPresenterImpl implements IpersonPresenter{
//
 PersonModle personmodle;
 iLogActivity IlogActivity;

 Context context;

 public PersonPresenterImpl( iLogActivity ilogActivity) {
  personmodle=new PersonModle();
  IlogActivity = ilogActivity;

 }

 public final static String SEARCH_URL = "http://so.cntv.cn/service/panda.php";
 public final static String BASE_URL = "http://my.cntv.cn/intf/napi/api.php";
 public final static String LOGIN_URL = "https://reg.cntv.cn/login/login.action";
 public final static String VERFIICATION_URL = "http://reg.cntv.cn/simple/verificationCode.action";
 public final static String CNTV_WX_OAUTH_URL = "http://oauth.passport.cntv.cn/OauthClientWeixin/OAuthMobileWeixinServlet.do";
 public final static String REGCLINET_USER_URL = "http://cbox.regclientuser.cntv.cn";
 public final static String ARTICLE_URL = "http://api.cntv.cn/article/contentinfo";
 public static final String CLIENT = "ipanda_mobile";
 public static final String REFERER = "iPanda.Android";
 public static final String USER_AGENT = "CNTV_APP_CLIENT_CYNTV_MOBILE";




 @Override
    public void login(String name, String pwd) {

     if(name==null){
      Log.e("name+pwd","妹纸");
     }else { Log.e("name+pwd",name+"==="+pwd);}

  personmodle.getuser(name, pwd, new Callback() {
   @Override
   public void succeed(Object o) {
    UserBean userbean= (UserBean) o;
    Log.e("我的",userbean.getContent().getNickname());
    IlogActivity.getnaem((UserBean) o);


   }

   @Override
   public void nothing(String str) {

   }
  });

    }














}
