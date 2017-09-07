package com.example.ipandaitems.model.person;

import android.util.Log;

import com.example.ipandaitems.model.Callback;
import com.example.ipandaitems.model.entry.person.LogInBean;
import com.example.ipandaitems.model.entry.person.UserBean;
import com.example.ipandaitems.model.retrofit.RetrofitUtils;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Created by xiaogang on 2017/9/6.
 */

public class PersonModle implements IpersonModle{

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
    public void getuser(String name, String pwd, final Callback callback) {

                Map<String,String> map=new HashMap<>();

        map.put("Referer", LOGIN_URL);
        map.put("User-Agent", USER_AGENT);
        map.put("from", LOGIN_URL);
        map.put("service", "client_transaction");
        map.put("username", name);
        map.put("password", pwd);
        Log.e("我的ID","zhixing l ");


        RetrofitUtils.getmRetrofitUtils_Demo().loginUtils(map, new Observer<LogInBean>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull LogInBean logInBean) {
                String usrid = logInBean.getUser_seq_id();

                Log.e("我的ID",usrid+"控？");
                getname(usrid,callback);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.e("我的ID","失败？");
            }

            @Override
            public void onComplete() {

            }
        });
    }
    //

    @Override
    public void getname(String url, final Callback callback) {
        String ss="http://my.cntv.cn/intf/napi/api.php?client=ipanda_mobile&method=user.getNickNameAndFace&userid=";
        String name_url=ss+url;
        RetrofitUtils.getmRetrofitUtils_Demo().getname(name_url, new Observer<UserBean>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull UserBean userBean) {
            callback.succeed(userBean);
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }
}
