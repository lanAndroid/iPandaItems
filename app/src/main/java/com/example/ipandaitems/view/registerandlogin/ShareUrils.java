package com.example.ipandaitems.view.registerandlogin;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Toast;

import com.example.ipandaitems.R;
import com.sina.weibo.sdk.api.WebpageObject;
import com.sina.weibo.sdk.api.WeiboMultiMessage;
import com.sina.weibo.sdk.share.WbShareHandler;
import com.sina.weibo.sdk.utils.Utility;

/**
 * @name yanantian
 * @motto 莫羡他人谢语花, 腹有诗书气自华
 * @E-mail 1173568715@qq.com
 * @WX 15978622391
 */

public class ShareUrils {

    private static ShareUrils uril;
    private Context context;
    private WbShareHandler shareHandler;

    public ShareUrils(Context context) {
        this.context = context;
    }

    public void initFenXiang(String title, String miaoshu, String img, String url, String text) {
        shareHandler = new WbShareHandler((Activity) context);
        shareHandler.registerApp();
        WeiboMultiMessage multiMessage = new WeiboMultiMessage();
        multiMessage.mediaObject = getWebpageObj(title, miaoshu, img, url, text);
        shareHandler.shareMessage(multiMessage, false);
    }


    /**
     * 创建多媒体（网页）消息对象。
     *
     * @return 多媒体（网页）消息对象。
     */
    private WebpageObject getWebpageObj(String title, String miaoshu, String img, String url, String text) {
        WebpageObject mediaObject = new WebpageObject();
        mediaObject.identify = Utility.generateGUID();
        mediaObject.title = title;   //      这里填写想要分享的title
        mediaObject.description = miaoshu; //      这里填写想要分享的描述
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.person_sign);
        // 设置 Bitmap 类型的图片到视频对象里         设置缩略图。 注意：最终压缩过的缩略图大小不得超过 32kb。
        mediaObject.setThumbImage(bitmap);
        mediaObject.actionUrl = url; //      这里填写想要分享的url
        mediaObject.defaultText = text; //      这里填写想要分享的默认语句
        return mediaObject;
    }


//    private void onNewIntent(Intent intent) {
//        shareHandler.doResultIntent(intent, new com.sina.weibo.sdk.share.WbShareCallback() {
//            @Override
//            public void onWbShareSuccess() {
//                Toast.makeText(context, "分享成功", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onWbShareCancel() {
//                Toast.makeText(context, "取消分享", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onWbShareFail() {
//                Toast.makeText(context, "分享失败", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }


}
