package com.example.ipandaitems.view.personalcenter.fragment;

import android.os.Bundle;
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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by 张豫耀 on 2017/9/6.
 */

public class CommonFragment extends BaseFragment {
    @BindView(R.id.myquestion_submit_btn)
    TextView myquestionSubmitBtn;
    Unbinder unbinder;
    @BindView(R.id.myquestion_content)
    EditText myquestionContent;
    @BindView(R.id.myquestion_contact)
    EditText myquestionContact;
    CheckBox mCB1, mCB2, mCB3, mCB4, mCB5, mCB6;

    @Override
    protected int layoutID() {
        return R.layout.fragment_common;
    }

    @Override
    protected void initView(View v) {
        mCB1 = (CheckBox) v.findViewById(R.id.myquestion_cb1);
        mCB2 = (CheckBox) v.findViewById(R.id.myquestion_cb2);
        mCB3 = (CheckBox) v.findViewById(R.id.myquestion_cb3);
        mCB4 = (CheckBox) v.findViewById(R.id.myquestion_cb4);
        mCB5 = (CheckBox) v.findViewById(R.id.myquestion_cb5);
        mCB6 = (CheckBox) v.findViewById(R.id.myquestion_cb6);
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void initListener() {

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

    @OnClick(R.id.myquestion_submit_btn)
    public void onViewClicked() {
        if (TextUtils.isEmpty(makeTypes()) || TextUtils.isEmpty(myquestionContact.getText())) {
            String str = TextUtils.isEmpty(makeTypes()) ? "请选择问题类型！" : "联系方式不能为空！";
            {
                Toast.makeText(getActivity(), str, Toast.LENGTH_SHORT);

            }
            return;
        } else {
            if (!checkEmail()) {
                Toast.makeText(getActivity(), "邮箱格式不正确", Toast.LENGTH_SHORT);
                return;
            }
            String content = myquestionContent.getText().toString();
            if (null != myquestionSubmitBtn.getTag() && content.equals(myquestionSubmitBtn.getTag())) {
                Toast.makeText(getActivity(), "内容提交重复", Toast.LENGTH_SHORT);

                return;
            }
            myquestionSubmitBtn.setTag(content);
            myquestionSubmitBtn.setClickable(false);
        }
    }

    private boolean checkEmail() {
        String emailString = myquestionContact.getText().toString().trim();
        Pattern pattern = Pattern.compile("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$");
        Matcher matcher = pattern.matcher(emailString);
        if (matcher.matches()) {
            return true;
        } else {
            return false;
        }
    }


    private String makeTypes() {
        String types = "";
        if (mCB1.isChecked()) {
            types += "1,";
        }
        if (mCB2.isChecked()) {
            types += "2,";
        }
        if (mCB3.isChecked()) {
            types += "3,";
        }
        if (mCB4.isChecked()) {
            types += "4,";
        }
        if (mCB5.isChecked()) {
            types += "5,";
        }
        if (mCB6.isChecked()) {
            types += "6,";
        }
        return types;
    }
}
