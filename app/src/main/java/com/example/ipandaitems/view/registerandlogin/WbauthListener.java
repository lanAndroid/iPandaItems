package com.example.ipandaitems.view.registerandlogin;

import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WbConnectErrorMessage;

/**
 * @name yanantian
 * @motto 莫羡他人谢语花, 腹有诗书气自华
 * @E-mail 1173568715@qq.com
 * @WX 15978622391
 */

//登录接口
public interface WbauthListener {
    void onSuccess(Oauth2AccessToken token);

    void cancel();

    void onFailure(WbConnectErrorMessage errorMessage);

}
