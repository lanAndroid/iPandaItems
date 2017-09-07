package com.example.ipandaitems.view.personalcenter;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ipandaitems.R;
import com.example.ipandaitems.base.BaseActivity;
import com.gridsum.mobiledissector.util.StringUtil;

import java.io.File;
import java.sql.SQLException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SetActivity extends BaseActivity {

    @BindView(R.id.set_finish)
    ImageView setFinish;
    @BindView(R.id.set_check1)
    CheckBox setCheck1;
    @BindView(R.id.set_check2)
    CheckBox setCheck2;
    @BindView(R.id.set_delete)
    ImageView setDelete;
    @BindView(R.id.set_tv)
    TextView setTv;
    @BindView(R.id.set_tickling)
    RelativeLayout setTickling;
    @BindView(R.id.sec_upgrade)
    RelativeLayout secUpgrade;
    @BindView(R.id.sec_regards)
    RelativeLayout secRegards;

    @Override
    protected int layoutID() {
        return R.layout.activity_set;
    }

    @Override
    protected void initView() throws SQLException {

    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void initListener() {
        setFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.set_check1, R.id.set_check2, R.id.set_delete, R.id.set_tickling, R.id.sec_upgrade, R.id.sec_regards})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.set_check1:
                break;
            case R.id.set_check2:
                break;
            case R.id.set_delete:
                final AlertDialog.Builder dialog = new AlertDialog.Builder(SetActivity.this);
                dialog.setTitle("确定清理缓存吗");
                dialog.setNeutralButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        DataCleanManager.deleteDirectory(getCacheDir());
                        DataCleanManager.deleteDirectory(getFilesDir());
                        DataCleanManager.deleteDirectory(new File(AppConfig.APP_PATH));
                        initCache();
                        dialog.create().dismiss();
                    }
                });
                dialog.setPositiveButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialog.create().dismiss();
                    }
                });
                dialog.show();
                break;
            case R.id.set_tickling:
                startActivity(new Intent(this, TickingActivity.class));
                break;
            case R.id.sec_upgrade:
                Toast.makeText(this, "当前版本为最高版本！", Toast.LENGTH_SHORT).show();
                break;
            case R.id.sec_regards:
                startActivity(new Intent(this, RegardsActivity.class));
                break;
        }
    }

    void initCache() {
        try {
            long innerCacheSize = 0, innerFileSize = 0;
            if (getCacheDir().exists()) {
                innerCacheSize = GetFileSizeUtil.getInstance().getFileSize(
                        getCacheDir());
            }
            if (getFilesDir().exists()) {
                innerFileSize = GetFileSizeUtil.getInstance().getFileSize(
                        getFilesDir());
            }
            // SD card
            File appFile = new File(AppConfig.APP_PATH);
            long outerDirSize = 0;
            if (appFile.exists()) {
                outerDirSize = GetFileSizeUtil.getInstance().getFileSize(
                        appFile);
            }
            String strInnerSize = GetFileSizeUtil.getInstance().FormetFileSize(
                    innerCacheSize + innerFileSize + outerDirSize);
            if (StringUtil.isNullOrEmpty(strInnerSize)) {
                strInnerSize = "0KB";
            }
            setTv.setText(strInnerSize);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
