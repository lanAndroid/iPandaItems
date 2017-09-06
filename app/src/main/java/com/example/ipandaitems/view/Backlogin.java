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
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.example.ipandaitems.R;
import com.example.ipandaitems.base.BaseActivity;

import java.sql.SQLException;

/**
 * Created by xiaogang on 2017/9/6.
 */

public class Backlogin extends BaseActivity implements View.OnClickListener {
    private ImageView login_finish;
    private TextView login_regis;
    private TextView tv_headjiantou;
    private ImageView iv_headicon;
    private RelativeLayout person_have_login_layout;
    private TextView tv_nichengjiantou;
    private TextView nick_name;
    private RelativeLayout personal_nickname_layout;
    private TextView btn_login_out;
    private RelativeLayout login_out_layout;
    SharedPreferences namesp;
    SharedPreferences imagesp;
    SharedPreferences.Editor nameeditor;
    SharedPreferences.Editor imageeditor;



    @Override
    protected int layoutID() {
        return R.layout.backlog;
    }

    @Override
    protected void initView() throws SQLException {
        iv_headicon= (ImageView) findViewById(R.id.iv_headicon);
        nick_name= (TextView) findViewById(R.id.nick_name);
        btn_login_out= (TextView) findViewById(R.id.btn_login_out);
        login_finish= (ImageView) findViewById(R.id.login_finish);
        btn_login_out.setOnClickListener(this);
        login_finish.setOnClickListener(this);
        namesp=Backlogin.this.getSharedPreferences("name",MODE_PRIVATE);
        imagesp=Backlogin.this.getSharedPreferences("image",MODE_PRIVATE);
        String user=namesp.getString("name","");
        String url=imagesp.getString("image","");
        Glide.with(this).load(url).transform(new GlideCircleTransform(this)).into(iv_headicon);
        nick_name.setText(user);


    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) and run LayoutCreator again
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.login_finish:
                startActivity(new Intent(Backlogin.this,LogActivity.class));
                finish();

                break;
            case R.id.btn_login_out:

                namesp.edit().clear().commit();
                imagesp.edit().clear().commit();
                startActivity(new Intent(Backlogin.this,LogActivity.class));
                finish();

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
