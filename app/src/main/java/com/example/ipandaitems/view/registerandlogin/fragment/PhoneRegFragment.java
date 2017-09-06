package com.example.ipandaitems.view.registerandlogin.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ipandaitems.R;
import com.example.ipandaitems.base.BaseFragment;
import com.example.ipandaitems.model.retrofit.RetrofitUtils;
import com.example.ipandaitems.presenter.regis.RegisNumberPresenter;
import com.example.ipandaitems.presenter.regis.RegisNumberPresenterImpl;
import com.example.ipandaitems.presenter.regis.RegisPresenter;
import com.example.ipandaitems.presenter.regis.RegisPresenterImpl;
import com.example.ipandaitems.view.LogActivity;
import com.example.ipandaitems.view.registerandlogin.RegistInfo;
import com.example.ipandaitems.view.registerandlogin.RegistNumberInfo;
import com.example.ipandaitems.view.registerandlogin.Treaty;

import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 * Created by 1 on 2017/9/4.
 */

public class PhoneRegFragment extends BaseFragment implements View.OnClickListener,RegistNumberInfo,RegistInfo {
    @BindView(R.id.edit_phone)
    EditText editPhone;
    @BindView(R.id.hint_phone)
    TextView hintPhone;
    @BindView(R.id.edit_imgyanzhengma)
    EditText editImgyanzhengma;
    @BindView(R.id.personal_reg_imgcheck)
    TextView personalRegImgcheck;
    @BindView(R.id.hint_imagecheck)
    TextView hintImagecheck;
    @BindView(R.id.edit_phoneyanzhengma)
    EditText editPhoneyanzhengma;
    @BindView(R.id.personal_reg_phonecheck)
    TextView personalRegPhonecheck;
    @BindView(R.id.hint_phonecheck)
    TextView hintPhonecheck;
    @BindView(R.id.edit_inputpasswrod)
    EditText editInputpasswrod;
    @BindView(R.id.hint_password)
    TextView hintPassword;
    @BindView(R.id.xieyi_check)
    CheckBox xieyiCheck;
    @BindView(R.id.personal_reg_xieyi_view)
    TextView personalRegXieyiView;
    @BindView(R.id.hint_xieyi)
    TextView hintXieyi;
    @BindView(R.id.btn_register)
    TextView btnRegister;
    Unbinder unbinder;
    private double count;
    private final int IMG_GET_SUCCESS=101;
    private final int IMG_GET_ERROR=102;
    //短信请求成功,失败
    private final int MSG_GETTING_SUCCESS=103;
    private final int MSG_GETTING_ERROR=104;
    //手机注册成功与失败
    private final int MSG_LOGIN_SUCCESS=105;
    private final  int MSG_LOGIN_ERROR=106;
    private Handler handler;
    private byte[] bytes;
    public static String JSESSIONID;
    TimeCount timeCount=new TimeCount(180000, 1000);
    private Handler handlers;
    private Handler yzmHandler;
    private RegisPresenter regisPresenter;
    private RegisNumberPresenter regisNumberPresenter;
    private RegisPresenter regisPresenter1;

    @Override
    protected int layoutID() {
        return R.layout.fragment_personal_phone_register;
    }

    @Override
    protected void initView(View view) {
        regisPresenter1 = new RegisPresenterImpl(this);
        regisNumberPresenter = new RegisNumberPresenterImpl(this);
    }

    @Override
    protected void loadData() {
        handler = new Handler(){
             @Override
             public void handleMessage(Message msg) {
                 super.handleMessage(msg);
                 switch (msg.what){
                    case IMG_GET_SUCCESS:
                        personalRegImgcheck.setText("");
                        Drawable drawable = byteToDrawable(bytes);
                        personalRegImgcheck.setBackgroundDrawable(drawable);
                        break;
                     case IMG_GET_ERROR:
                         personalRegImgcheck.setText("获取图形验证码");
                         sendPicCode();
                         break;
                     case MSG_GETTING_SUCCESS://短信获取成功
                         if (msg.obj!=null){
                             Toast.makeText(getActivity(),(String)msg.obj,Toast.LENGTH_SHORT).show();
                         }
                         break;
                     case MSG_GETTING_ERROR:
                         if (msg.obj!=null){
                             Toast.makeText(getActivity(),(String)msg.obj,Toast.LENGTH_SHORT).show();
                         }
                         break;
                     case MSG_LOGIN_SUCCESS:
                         if (msg.obj!=null){
                             Toast.makeText(getActivity(),(String)msg.obj,Toast.LENGTH_SHORT).show();
                         }
                         Intent intent=new Intent(getActivity(), LogActivity.class);
                         startActivity(intent);
                         break;
                     case MSG_LOGIN_ERROR:
                         if (msg.obj!=null){
                             Toast.makeText(getActivity(),(String)msg.obj,Toast.LENGTH_SHORT).show();
                         }
                         break;
                 }
             }
         };
    }

    @Override
    protected void initListener() {
        personalRegImgcheck.setOnClickListener(this);
        personalRegPhonecheck.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    //获取图片验证码
    private void sendPicCode(){
        //如果是联网的情况直接跳出来
        if (!isConnected()){
            return;
        }
        //获取图片验证码的网址
        final String from = "http://reg.cntv.cn/simple/verificationCode.action";
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpGet httpGet=new HttpGet(from);
                DefaultHttpClient client = new DefaultHttpClient();
                try {
                    HttpResponse execute = client.execute(httpGet);

                    if (execute.getStatusLine().getStatusCode()==200){
                       //获取Cookie的存储类
                        CookieStore cookieStore = client.getCookieStore();
                        //获取cookie集合
                        List<Cookie> cookies = cookieStore.getCookies();

                        for (int i = 0, count =cookies.size(); i<count ; i++) {
                            //遍历获取cookie
                            Cookie cookie = cookies.get(i);
                            if ("JSESSIONID".equals(cookie.getName())){
                                 JSESSIONID = cookie.getValue();
                                System.out.println(JSESSIONID+"——————————————————刚请求下来");
                                SharedPreferences userInfo = getActivity().getSharedPreferences("user_info", 0);
                                userInfo.edit().putString("name", JSESSIONID).commit();
                                break;
                            }
                        }

                        //用EntityUtilsba把HttpResponse转换成byte数组
                        bytes = EntityUtils.toByteArray(execute.getEntity());
                        handler.sendEmptyMessage(IMG_GET_SUCCESS);
                    }else {
                        Message message = handler.obtainMessage(IMG_GET_ERROR);
                        message.obj="图片验证码获取失败";
                        handler.sendMessage(message);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    
    //数组转换成Drawable
    private static Drawable byteToDrawable(byte[] byt){
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byt);
        Drawable fromStream = Drawable.createFromStream(byteArrayInputStream, null);
        return fromStream;
    }

    @Override
    public void onResume() {
        super.onResume();
        sendPicCode();
    }
    //点击重新获取图形验证码,获取验证码,协议跳转
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.personal_reg_imgcheck://点击图形验证码
                if (!isConnected()){
                    Toast.makeText(getActivity(),"没有网络连接",Toast.LENGTH_SHORT).show();
                    return;
                }
                sendPicCode();
                break;
            case R.id.personal_reg_xieyi_view://点击协议
                Intent intent=new Intent(getActivity(), Treaty.class);
                startActivity(intent);
                break;
            case R.id.personal_reg_phonecheck://点击获取手机验证吗

                Toast.makeText(getActivity(),"1111",Toast.LENGTH_SHORT).show();
                editPhone.clearFocus();//clearFous取消焦点
                editImgyanzhengma.clearFocus();
                editPhoneyanzhengma.clearFocus();

                String imgCode = personalRegImgcheck.getText().toString().trim();
                if (TextUtils.isEmpty(imgCode)){
                    hintImagecheck.setText("图片验证码输入有误");
                }
                if (!checkPhone()){
                    return;
                }
                if (!checkIgCode()){
                    return;
                }else {
                    hintPhone.setText("");
                    hintImagecheck.setText("");
                    sendPhoneCode();
                }
                timeCount.start();
                break;
            case R.id.btn_register:
                editPhone.clearFocus();
                editImgyanzhengma.clearFocus();
                editPhoneyanzhengma.clearFocus();
                editInputpasswrod.clearFocus();
                if (!isConnected()) {
                   Toast.makeText(getActivity(),"请连接网络",Toast.LENGTH_SHORT).show();
                    return;
                }
                //检查手机号
                if (!checkPhone()) {
                    return;
                }
                //检查图验
                if (!checkIgCode()) {
                    return;
                }
                //检查手验
                if (!checkPhoneCode()) {

                    return;
                }
                //检查密码
                if (!checkPassWord()) {

                    return;
                }
                //检查协议
                if (!checkTreay()) {

                    return;
                }
                regPhone();
                break;
        }
    }
//zhuce
    @Override
    public void onNumberSuccess(ResponseBody responseBody) {
        try {
            String string = responseBody.string();
            System.out.println("---------"+string+"注册");
            Message message = handlers.obtainMessage();
            message.obj=string;
            handlers.sendMessage(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onNumberError(String e) {

    }
//Code
    @Override
    public void onSuccess(ResponseBody responseBody) {
        try {
            String string = responseBody.string();
            System.out.println(string+"）））））））））））））））））））））））））））））））））））））");
            Message message = yzmHandler.obtainMessage();
            message.obj=string;
            yzmHandler.sendMessage(message);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onError(String e) {

    }

    class TimeCount extends CountDownTimer{

        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);//总时长，单时间间隔
        }

        @Override
        public void onTick(long l) {//计时过程显示
            if (personalRegImgcheck!=null){
            personalRegPhonecheck.setClickable(false);
            personalRegPhonecheck.setText("重新获取验证码"+"("+l/1000+")");}
        }

        @Override
        public void onFinish() {//计时完毕显示
            personalRegPhonecheck.setClickable(true);
            personalRegPhonecheck.setText("获取验证码");
        }

    }

    //获取手机验证码
    private void sendPhoneCode(){
        if (!isConnected()){
        Toast.makeText(getActivity(),"请连接网络",Toast.LENGTH_SHORT).show();
            return;
        }
        yzmHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                String obj = (String) msg.obj;
                System.out.println(obj+"这是发过来的——————————————————————————————");
                if (obj.endsWith("success")){
                    Message message = handler.obtainMessage(MSG_GETTING_SUCCESS);
                    message.obj="发送成功";
                    handler.sendMessage(message);
                }else if (obj.endsWith("registered")){
                    Message message = handler.obtainMessage(MSG_GETTING_ERROR);
                    message.obj="您的手机号已注册";
                    handler.sendMessage(message);
                }else if (obj.endsWith("sendfailure")){
                    Message message = handler.obtainMessage(MSG_GETTING_ERROR);
                    message.obj="验证码发送失败";
                    handler.sendMessage(message);
                }else if (obj.endsWith("sendagain")){
                    Message message = handler.obtainMessage(MSG_GETTING_ERROR);
                    message.obj="三分钟内只能获取一次";
                    handler.sendMessage(message);
                }else if (obj.endsWith("ipsendagain")){
                    Message message = handler.obtainMessage(MSG_GETTING_ERROR);
                    message.obj="同一ip请求验证码超过5次";
                    handler.sendMessage(message);
                }else if (obj.endsWith("mobileoften")){
                    Message message = handler.obtainMessage(MSG_GETTING_ERROR);
                    message.obj="同一手机号验证码请求超过3次";
                    handler.sendMessage(message);
                }else if (obj.endsWith("mobilecodeerror")){
                    Message message = handler.obtainMessage(MSG_GETTING_ERROR);
                    message.obj="验证码不正确";
                    handler.sendMessage(message);
                }
            }
        };

        String phoneString = editPhone.getText().toString().trim();
        String phoneyanzhengma = editImgyanzhengma.getText().toString().trim();
        String url = "http://reg.cntv.cn/regist/getVerifiCode.action";
       //String urlhtp="http://reg.cntv.cn/regist/getVerifiCode.action?Referer=http://cbox_mobile.regclientuser.cntv.cn&User-Agent=CNTV_APP_CLIENT_CBOX_MOBILE&Cookie=\"JSESSIONID=\"+JSESSIONID&method=getRequestVerifiCodeM&mobile=17777783421&verfiCodeType=1&verificationCode=vscg"
        String from = "http://cbox_mobile.regclientuser.cntv.cn";

            //URLEncoder.encode编码解码
            //HashMap<String,String> mHeader=new HashMap<>();
           HashMap<String,String> params=new HashMap<>();
          /* mHeader.put("Referer",from);
            mHeader.put("User-Agent","CNTV_APP_CLIENT_CBOX_MOBILE");
            mHeader.put("Cookie","JSESSIONID"+JSESSIONID);*/
        params.put("method", "getRequestVerifiCodeM");
        params.put("mobile", phoneString);
        params.put("verfiCodeType", "1");
        params.put("verificationCode", phoneyanzhengma);

        regisPresenter1.getCode(url,params);

    }
    //手机号注册
    private void regPhone(){
        if (!isConnected()){
            Toast.makeText(getActivity(),"请连接网络",Toast.LENGTH_SHORT).show();
            return;
        }

        handlers = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                String s = (String) msg.obj;

                if (s.endsWith("success")){
                    Message message = handler.obtainMessage(MSG_LOGIN_SUCCESS);
                    message.obj="手机号注册成功";
                    handler.sendMessage(message);
                }else if (s.endsWith("registered")){
                    Message message = handler.obtainMessage(MSG_LOGIN_ERROR);
                    message.obj="手机号已注册";
                    handler.sendMessage(message);
                }else if (s.endsWith("error")){
                    Message message = handler.obtainMessage(MSG_LOGIN_ERROR);
                    message.obj="验证码输入错误";
                    handler.sendMessage(message);
                }else if (s.endsWith("mobilenull")){
                    Message message = handler.obtainMessage(MSG_LOGIN_ERROR);
                    message.obj="手机号为空";
                    handler.sendMessage(message);
                }else if (s.endsWith("timeout")){
                    Message message = handler.obtainMessage(MSG_LOGIN_ERROR);
                    message.obj="验证码超过有效时间";
                    handler.sendMessage(message);
                }else if (s.endsWith("passwordnull")){
                    Message message = handler.obtainMessage(MSG_LOGIN_ERROR);
                    message.obj="密码为空";
                    handler.sendMessage(message);
                }

            }
        };

        String phone = editPhone.getText().toString().trim();
        String phoneCode = editPhoneyanzhengma.getText().toString().trim();
      //  String phoneImgCode = editImgyanzhengma.getText().toString().toString().trim();
        String pwd = editInputpasswrod.getText().toString().trim();
        String url = "https://reg.cntv.cn/regist/mobileRegist.do";

       /* //添加请求头
        HashMap<String,String> header=new HashMap<>();
        try {
            header.put("Referer", URLEncoder.encode("http://cbox_mobile.regclientuser.cntv.cn", "UTF-8"));
            header.put("User-Agent", URLEncoder.encode("CNTV_APP_CLIENT_CBOX_MOBILE", "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }*/
        //添加参数
        HashMap<String,String> params=new HashMap<>();
        params.put("method", "saveMobileRegisterM");
        params.put("mobile", phone);
        params.put("verfiCode", phoneCode);
        try {
            params.put("passWd", URLEncoder.encode(pwd, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        params.put("verfiCodeType", "1");
        try {
            params.put("addons", URLEncoder.encode("http://cbox_mobile.regclientuser.cntv.cn", "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        //获取网络请求
        regisNumberPresenter.postParams(url,params);
    }
//检查图形验证码
    private Boolean checkIgCode(){
        if (bytes==null){
            Toast.makeText(getActivity(),"没有获取到验证码",Toast.LENGTH_SHORT).show();
            return false;
        }
        String igCode = editImgyanzhengma.getText().toString().trim();
        if (igCode.contains(" ")){
            hintImagecheck.setText("验证码不正确");
            return false;
        }
        if (igCode==null||"".equals(igCode)){
            hintImagecheck.setText("验证码不能为空");
            return false;
        }else {
            hintImagecheck.setText("");
            return true;
        }

    }
    //检查手机号
    private Boolean checkPhone(){
        String phone = editPhone.getText().toString().trim();
        if (TextUtils.isEmpty(phone)){
            hintPhone.setText("手机号不能为空");
            return false;
        }
        //创建正则表达式
        Pattern pattern = Pattern.compile("^1[3578]\\d{9}$");
        //用正则表达式与手机号快速校验
        Matcher matcher = pattern.matcher(phone);

        if (matcher.matches()) {
            //如果配对成功
            hintPhone.setText("");
            return true;
        }else {
            hintPhone.setText("手机号格式不正确");
            return false;
        }
    }

    //检查密码
    private Boolean checkPassWord(){
        String pwd = editInputpasswrod.getText().toString().trim();
        if (TextUtils.isEmpty(pwd)){
            hintPassword.setText("密码不能为空");
            return false;
        }else if (pwd.length() < 6 || pwd.length() > 16){
            hintPassword.setText("密码仅限6至16个字符");
            return false;
        }else {
            hintPassword.setText("");
            return true;
        }
    }
    //检查协议
    private Boolean checkTreay(){
        if (!xieyiCheck.isChecked()){
            hintXieyi.setText("未勾选《央视网网络服务使用协议》");
            return false;
        }else {
            hintXieyi.setText("");
            return true;
        }
    }
    //检查手机验证码
    private Boolean checkPhoneCode(){
        String phoneCode = editPhoneyanzhengma.getText().toString().trim();
        if (TextUtils.isEmpty(phoneCode)){
            hintPhonecheck.setText("验证码不能为空");
            return false;
        }else {
            hintPhonecheck.setText("");
            return true;
        }
    }
}
