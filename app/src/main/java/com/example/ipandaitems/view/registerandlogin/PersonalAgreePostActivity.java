package com.example.ipandaitems.view.registerandlogin;

import android.view.View;
import android.widget.TextView;

import com.example.ipandaitems.R;
import com.example.ipandaitems.base.BaseActivity;

import java.io.InputStream;
import java.sql.SQLException;

public class PersonalAgreePostActivity extends BaseActivity {

    private static final String sStyleKey = "StyleKey";
    // private static final int sDefaultValue = R.style.ResourceBlueStyle;
    TextView mContent;

    @Override
    protected int layoutID() {
        return R.layout.activity_personal_agree_post;
    }

    @Override
    protected void initView() throws SQLException {
        findViewById(R.id.agree_post_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mContent = (TextView) findViewById(R.id.agree_post_text);

    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void initListener() {

    }
    private String inputStream2String(InputStream inputStream, int bufferSize)
            throws Exception {
        if (inputStream == null || bufferSize < 1) {
            return null;
        }
        int i = -1;
        byte[] b = new byte[bufferSize];
        StringBuffer sb = new StringBuffer();
        while ((i = inputStream.read(b)) != -1) {
            sb.append(new String(b, 0, i));
        }
        return sb.toString();
    }
}
