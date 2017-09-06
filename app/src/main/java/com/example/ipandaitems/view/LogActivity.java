package com.example.ipandaitems.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.example.ipandaitems.R;
import com.example.ipandaitems.base.BaseActivity;
import com.example.ipandaitems.view.registerandlogin.loginActivity;

import java.sql.SQLException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;


public class LogActivity extends BaseActivity {
    SharedPreferences namesp;
    SharedPreferences imagesp;
    SharedPreferences.Editor nameeditor;
    SharedPreferences.Editor imageeditor;
TextView name1;
    @BindView(R.id.personal_back_img)
    ImageView personal_back_img;
    @BindView(R.id.person_no_login_layout)
    RelativeLayout personNoLoginLayout;

    @BindView(R.id.personal_history_layout)
    RelativeLayout personalHistoryLayout;
    @BindView(R.id.personal_shoucang_layout)
    RelativeLayout personalShoucangLayout;
    @BindView(R.id.personal_set_layout)
    RelativeLayout personalSetLayout;
    ImageView imageView1;
    private String name11;

    @Override
    protected int layoutID() {
        return R.layout.activity_log;

    }

    @Override
    protected void initView() throws SQLException {
        ButterKnife.bind(this);
    }

    @Override
    protected void loadData() {
        imageView1= (ImageView) findViewById(R.id.personal_img);
        name1= (TextView) findViewById(R.id.personal_login_view);
        namesp=LogActivity.this.getSharedPreferences("name",MODE_PRIVATE);
        name11 = namesp.getString("name", "i");
        imagesp=LogActivity.this.getSharedPreferences("image",MODE_PRIVATE);
        String iamg11 = imagesp.getString("image", "imag");

        if(!name11.equals("i")){
            name1.setText(name11);
            Log.e("我的头像",iamg11+"头");

            Glide.with(LogActivity.this).load(iamg11).transform(new GlideCircleTransform(this)).into(imageView1);

        }else{
            name1.setText("点击登录");
            imageView1.setImageResource(R.drawable.personal_login_head);
            Log.e("打印下数据","这是名字"+name1);
        }





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

    @Optional
    @OnClick({R.id.personal_back_img, R.id.person_no_login_layout, R.id.personal_history_layout, R.id.personal_shoucang_layout, R.id.personal_set_layout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.personal_back_img:
                finish();
                break;
            case R.id.person_no_login_layout:
                if(!name11.equals("i")){
                    startActivity(new Intent(LogActivity.this, Backlogin.class));

                }else{
                    startActivity(new Intent(LogActivity.this, loginActivity.class));
                }

                finish();
                break;
            case R.id.personal_history_layout:
                break;
            case R.id.personal_shoucang_layout:
                break;
            case R.id.personal_set_layout:
                break;
        }
    }

    class GlideCircleTransform extends BitmapTransformation {
        public GlideCircleTransform(Context context) {
            super(context);
        }

        @Override
        protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
            return circleCrop(pool, toTransform);
        }

        private  Bitmap circleCrop(BitmapPool pool, Bitmap source) {
            if (source == null) return null;

            int size = Math.min(source.getWidth(), source.getHeight());
            int x = (source.getWidth() - size) / 2;
            int y = (source.getHeight() - size) / 2;

            // TODO this could be acquired from the pool too
            Bitmap squared = Bitmap.createBitmap(source, x, y, size, size);

            Bitmap result = pool.get(size, size, Bitmap.Config.ARGB_8888);
            if (result == null) {
                result = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
            }

            Canvas canvas = new Canvas(result);
            Paint paint = new Paint();
            paint.setShader(new BitmapShader(squared, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
            paint.setAntiAlias(true);
            float r = size / 2f;
            canvas.drawCircle(r, r, r, paint);
            return result;
        }

        @Override
        public String getId() {
            return getClass().getName();
        }
    }



    static class  GlideRoundTransform extends BitmapTransformation {

        private static float radius = 0f;

        public GlideRoundTransform(Context context) {
            this(context, 4);
        }

        public GlideRoundTransform(Context context, int dp) {
            super(context);
            this.radius = Resources.getSystem().getDisplayMetrics().density * dp;
        }

        @Override protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
            return roundCrop(pool, toTransform);
        }

        private  Bitmap roundCrop(BitmapPool pool, Bitmap source) {
            if (source == null) return null;

            Bitmap result = pool.get(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
            if (result == null) {
                result = Bitmap.createBitmap(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
            }

            Canvas canvas = new Canvas(result);
            Paint paint = new Paint();
            paint.setShader(new BitmapShader(source, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
            paint.setAntiAlias(true);
            RectF rectF = new RectF(0f, 0f, source.getWidth(), source.getHeight());
            canvas.drawRoundRect(rectF, radius, radius, paint);
            return result;
        }

        @Override public String getId() {
            return getClass().getName() + Math.round(radius);
        }
    }
}
