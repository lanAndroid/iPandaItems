package com.example.ipandaitems.view.personalcenter;

import android.os.Environment;

import java.io.File;

/**
 * Created by 张豫耀 on 2017/9/6.
 */

class AppConfig {
    public static final String APP_PATH = Environment
            .getExternalStorageDirectory()
            + File.separator
            + "pandatv"
            + File.separator;
}
