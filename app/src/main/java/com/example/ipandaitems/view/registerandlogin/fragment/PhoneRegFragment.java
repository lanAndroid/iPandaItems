package com.example.ipandaitems.view.registerandlogin.fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.CountDownTimer;
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

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.example.ipandaitems.R.id.edit_imgyanzhengma;
import static com.example.ipandaitems.R.id.edit_phone;
import static com.example.ipandaitems.R.id.edit_phoneyanzhengma;
import static com.example.ipandaitems.R.id.hint_imagecheck;
import static com.example.ipandaitems.R.id.hint_phone;
import static com.example.ipandaitems.R.id.hint_phonecheck;
import static com.example.ipandaitems.R.id.personal_reg_imgcheck;
import static com.example.ipandaitems.R.id.personal_reg_phonecheck;

/**
 * Created by 张豫耀 on 2017/8/28.
 */

public class PhoneRegFragment extends BaseFragment implements View.OnFocusChangeListener {


    @BindView(edit_phone)
    EditText editPhone;
    @BindView(hint_phone)
    TextView hintPhone;
    @BindView(edit_imgyanzhengma)
    EditText editImgyanzhengma;
    @BindView(personal_reg_imgcheck)
    TextView personalRegImgcheck;
    @BindView(hint_imagecheck)
    TextView hintImagecheck;
    @BindView(edit_phoneyanzhengma)
    EditText editPhoneyanzhengma;
    @BindView(personal_reg_phonecheck)
    TextView personalRegPhonecheck;
    @BindView(hint_phonecheck)
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
    private TimeCount mTime;
    private byte[] mCaptchaBytes;
    //输入框输入的验证码
    private String mCaptchaEditTextString;
    public String JSESSIONID = null;

    @Override
    protected int layoutID() {
        return R.layout.fragment_personal_phone_register;
    }

    @Override
    protected void initView(View view) {
        mTime = new TimeCount(180000, 1000);
        editPhone.setOnFocusChangeListener(this);
        editImgyanzhengma.setOnFocusChangeListener(this);
        editPhoneyanzhengma.setOnFocusChangeListener(this);
        editInputpasswrod.setOnFocusChangeListener(this);
    }

    @Override
    public void onFocusChange(View view, boolean b) {
        if (b) {
            switch (view.getId()) {
                case edit_phone:
                    hintPhone.setText("");
                    break;
                case edit_imgyanzhengma:
                    if (!checkPhone())
                        return;
                    hintImagecheck.setText("");
                    break;
                case edit_phoneyanzhengma:
                    if (!checkCaptcha())
                        return;
                    hintPhonecheck.setText("");
                    break;
                case R.id.edit_inputpasswrod:
                    if (!checkPhoneCheck())
                        return;
                    hintPassword.setText("");
                    break;
            }
        }
    }

    //    检查手机号
    private boolean checkPhone() {
        String trim = editPhone.getText().toString().trim();
        if (TextUtils.isEmpty(trim)) {
            hintPhone.setText("手机号码不能为空");
            return false;
        }
        Pattern pattern = Pattern.compile("^1[3578]\\d{9}$");
        Matcher matcher = pattern.matcher(trim);
        if (matcher.matches()) {
            hintPhone.setText("");
            return true;
        } else {
            hintPhone.setText("手机格式不正确");
            return false;
        }
    }

    //    检查验证码
    private boolean checkCaptcha() {
        if (mCaptchaBytes == null) {
            Toast.makeText(getActivity(), "未获取验证码", Toast.LENGTH_SHORT).show();
            return false;
        }

        mCaptchaEditTextString = editImgyanzhengma.getText().toString().trim();
        if (mCaptchaEditTextString.contains(" ")) {
            hintImagecheck.setText("验证码不正确");
            return false;
        }
        if (mCaptchaEditTextString == null || "".equals(mCaptchaEditTextString)) {
            hintImagecheck.setText("验证码不能为空");
            return false;
        } else {
            hintImagecheck.setText("");
            return true;
        }
    }

    //    检查手机验证码
    private boolean checkPhoneCheck() {
        String phonecheck = editPhoneyanzhengma.getText().toString().trim();
        if (TextUtils.isEmpty(phonecheck)) {
            hintPhonecheck.setText("验证码不能为空");
            return false;
        } else {
            hintPhonecheck.setText(" ");
            return true;
        }
    }

    //    判断手机是否有网络
    public boolean isConnected() {
        // 获取手机所有连接管理对象（包括对wi-fi,net等连接的管理）
        try {
            ConnectivityManager connectivity = (ConnectivityManager) getActivity()
                    .getSystemService(getActivity().CONNECTIVITY_SERVICE);
            if (connectivity != null) {
                // 获取网络连接管理的对象
                NetworkInfo info = connectivity.getActiveNetworkInfo();

                if (info != null && info.isConnected()) {
                    // 判断当前网络是否已经连接
                    if (info.getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        } catch (Exception e) {
        }
        return false;
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void initListener() {

    }

    //    点击事件
    @OnClick({personal_reg_imgcheck, personal_reg_phonecheck, R.id.xieyi_check, R.id.personal_reg_xieyi_view, R.id.btn_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case personal_reg_imgcheck:
                if (!isConnected()) {
                    Toast.makeText(getContext(), "没有网络", Toast.LENGTH_SHORT).show();
                    return;
                }
//                获取图片验证码
                sendImgYanZhenMa();
                break;
            case personal_reg_phonecheck:
                editPhone.clearFocus();
                editImgyanzhengma.clearFocus();
                editPhoneyanzhengma.clearFocus();
                String phone = editPhone.getText().toString().trim();
                String imgyzm = editImgyanzhengma.getText().toString().trim();
                if (TextUtils.isEmpty(imgyzm)) {
                    hintImagecheck.setText("图片验证码输入有误");
                }
                if (!checkPhone()) {
                    return;
                }
                if (!checkCaptcha()) {
                    return;
                } else {
                    hintPhone.setText("");
                    hintImagecheck.setText("");
                    sendCaptYanZhenMa();
                }
                break;
            case R.id.xieyi_check:

                break;
            case R.id.personal_reg_xieyi_view:

                break;
            case R.id.btn_register:

                break;
        }
    }

    //    获取短信验证码
    private void sendCaptYanZhenMa() {
        if (!isConnected()) {
            Toast.makeText(getContext(), "请连接网络...", Toast.LENGTH_SHORT).show();
            return;
        }
        String phone = editPhone.getText().toString().trim();
        String phoneyzm = editPhoneyanzhengma.getText().toString().trim();
        String url = "http://reg.cntv.cn/regist/getVerifiCode.action";
        String from = "http://cbox_mobile.regclientuser.cntv.cn";
        HashMap<String, String> theaders = new HashMap<>();
        try {
            theaders.put("Referer", URLEncoder.encode(from, "UTF-8"));
            theaders.put("User-Agent", URLEncoder.encode("CNTV_APP_CLIENT_CBOX_MOBILE", "UTF-8"));
            theaders.put("Cookie", "JSESSIONID=" + JSESSIONID);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }




    }

    //    获取图片验证码
    private void sendImgYanZhenMa() {
        if (!isConnected()) {
            return;
        }
        String from = "http://reg.cntv.cn/simple/verificationCode.action";
        OkHttpClient client = new OkHttpClient.Builder().build();
        Request request = new Request.Builder().url(from).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                InputStream stream = response.body().byteStream();
                Bitmap stream1 = BitmapFactory.decodeStream(stream);
                byte[] bytes = response.body().bytes();
                final Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                final Drawable drawable = new BitmapDrawable(stream1);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        personalRegImgcheck.setText("");
                        personalRegImgcheck.setBackgroundDrawable(drawable);
                    }
                });
            }
        });
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

    class TimeCount extends CountDownTimer {

        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);//参数依次为总时长,和计时的时间间隔
        }

        @Override
        public void onTick(long l) {
            personalRegPhonecheck.setClickable(false);
            personalRegPhonecheck.setText("重新获取" + "(" + l / 1000 + ")");
        }

        @Override
        public void onFinish() {
            personalRegPhonecheck.setText("获取验证码");
            personalRegPhonecheck.setClickable(true);
        }
    }
}
