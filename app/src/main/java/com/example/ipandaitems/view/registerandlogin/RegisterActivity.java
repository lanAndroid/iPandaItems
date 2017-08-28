package com.example.ipandaitems.view.registerandlogin;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;

import com.example.ipandaitems.R;
import com.example.ipandaitems.base.BaseActivity;
import com.example.ipandaitems.view.registerandlogin.fragment.EmailRegFragment;
import com.example.ipandaitems.view.registerandlogin.fragment.PhoneRegFragment;

import java.sql.SQLException;

import butterknife.ButterKnife;

public class RegisterActivity extends BaseActivity implements View.OnClickListener {


    private ImageView register_finish;
    private RadioButton tvphonereg;
    private RadioButton tvemailreg;
    private PhoneRegFragment phoneRegFragment;
    private EmailRegFragment emailRegFragment;

    @Override
    protected int layoutID() {
        return R.layout.activity_register;
    }

    @Override
    protected void initView() throws SQLException {

        register_finish = (ImageView) findViewById(R.id.register_finish);
        register_finish.setOnClickListener(this);
        tvphonereg = (RadioButton) findViewById(R.id.tvphonereg);
        tvphonereg.setOnClickListener(this);
        tvemailreg = (RadioButton) findViewById(R.id.tvemailreg);
        tvemailreg.setOnClickListener(this);
    }

    @Override
    protected void loadData() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        hide(transaction);
        phoneRegFragment = new PhoneRegFragment();
        transaction.add(R.id.framelayout_register_content, phoneRegFragment);
        transaction.show(phoneRegFragment);
        transaction.commit();
    }

    @Override
    protected void initListener() {

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void onClick(View view) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        hide(transaction);
        switch (view.getId()) {
            case R.id.register_finish:
                finish();
                break;
            case R.id.tvphonereg:
                if (phoneRegFragment == null) {
                    phoneRegFragment = new PhoneRegFragment();
                    transaction.add(R.id.framelayout_register_content, phoneRegFragment);
                    transaction.show(phoneRegFragment);
                } else {
                    transaction.show(phoneRegFragment);
                }
                break;
            case R.id.tvemailreg:
                if (emailRegFragment == null) {
                    emailRegFragment = new EmailRegFragment();
                    transaction.add(R.id.framelayout_register_content, emailRegFragment);
                    transaction.show(emailRegFragment);
                } else {
                    transaction.show(emailRegFragment);
                }
                break;
        }
        transaction.commit();
    }

    private void hide(FragmentTransaction transaction) {
        if (phoneRegFragment != null) {
            transaction.hide(phoneRegFragment);
        }
        if (emailRegFragment != null) {
            transaction.hide(emailRegFragment);
        }
    }
}
