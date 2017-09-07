package com.example.ipandaitems.view.registerandlogin;

import android.content.Intent;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ipandaitems.R;
import com.example.ipandaitems.base.BaseActivity;
import com.example.ipandaitems.model.entry.person.LogInBean;
import com.example.ipandaitems.model.entry.person.UserBean;
import com.example.ipandaitems.presenter.personpresenter.PersonPresenterImpl;
import com.example.ipandaitems.view.LogActivity;
import com.example.ipandaitems.view.registerandlogin.fragment.iLogActivity;
import com.example.ipandaitems.view.video.PasswordretrievalActivity;

import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;


public class loginActivity extends BaseActivity implements View.OnClickListener,iLogActivity {

//    personal_login_forget_pwd
    @BindView(R.id.login_finish)
    ImageView loginFinish;
    @BindView(R.id.login_regis)
    TextView loginRegis;

    EditText username;
    EditText password;
    TextView login;
    TextView mhint_account;
    TextView mhint_password;
    TextView personal_login_forget_pwd;

    @Override
    protected int layoutID() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() throws SQLException {
            username= (EditText) findViewById(R.id.username);
        password= (EditText) findViewById(R.id.passwrod);
        login= (TextView) findViewById(R.id.loding_btn);
        mhint_account= (TextView) findViewById(R.id.hint_account);
        mhint_password= (TextView) findViewById(R.id.hint_password);
        personal_login_forget_pwd= (TextView) findViewById(R.id.personal_login_forget_pwd);
        personal_login_forget_pwd.setOnClickListener(this);
        login.setOnClickListener(this);
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void initListener() {

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.loding_btn:
                Log.e("登陆","kaishi");

                goLogin();

        if(checkEmailAndPhone()){
            mhint_account.setText("");
            return;
        }
        if(checkEmpty(password)==false){
            mhint_password.setText("密码不能为空");
            return;
        }
            mhint_account.setText("");
                mhint_password.setText("");


                break;
            case R.id.personal_login_forget_pwd:

                startActivity(new Intent(loginActivity.this, PasswordretrievalActivity.class));
                break;
//

        }
    }

    private void goLogin() {
        PersonPresenterImpl personPresenter=new PersonPresenterImpl(this);

        String name=username.getText().toString().trim();
        String pwd=password.getText().toString().trim();
        Log.e("登陆","开始传我的账号和密码"+name+"===="+pwd);

        personPresenter.login(name,pwd);





    }

    //判断账号 输入的格式
    private boolean checkEmailAndPhone() {
        String emailString = username.getText().toString().trim();
        if (TextUtils.isEmpty(emailString)) {
            mhint_account.setText("手机/邮箱格式不正确");
            return false;
        }
        String tEmail = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        if (emailString.indexOf("@") == -1) {
            tEmail = "^1[3578]\\d{9}$";
        }
        Pattern pattern = Pattern
                .compile(tEmail);
        Matcher matcher = pattern.matcher(emailString);
        if (matcher.matches()) {
            mhint_account.setText("");
            return true;
        } else {
            mhint_account.setText("还是不正确");
            return false;
        }
    }


    /**
     * 检查是否为空
     *
     * @param editText
     * @return
     */
    private boolean checkEmpty(EditText editText) {
        String testTxt = editText.getText().toString();
        if (testTxt == null || "".equals(testTxt)) {
            return false;
        }
        return true;
    }

    @Override
    public void getuser(LogInBean logInBean) {
        String usrid = logInBean.getUsrid();

        Log.e("asd","我的us"+usrid);
    }
    SharedPreferences namesp;
    SharedPreferences imagesp;
    SharedPreferences.Editor nameeditor;
    SharedPreferences.Editor imageeditor;


    @Override
    public void getnaem(UserBean userBean) {
        int wp = userBean.getCode();
        Log.e("asd","我的WP"+wp);

        if(wp==0){
            namesp=getSharedPreferences("name",MODE_PRIVATE);
            namesp.edit().putString("name",userBean.getContent().getNickname()).commit();
            imagesp=getSharedPreferences("image",MODE_PRIVATE);
            imagesp.edit().putString("image",userBean.getContent().getUserface()).commit();
                startActivity(new Intent(this, LogActivity.class));

            finish();
        }
    }
}
