package com.example.ipandaitems.view.personalcenter;

import java.io.File;

/**
 * Created by 张豫耀 on 2017/9/6.
 */

class DataCleanManager {
    public static void deleteDirectory(File file) {
        if (file.isFile()) {
            file.delete();
            return;
        }

        if (file.isDirectory()) {
            File[] childFiles = file.listFiles();
            if (childFiles == null || childFiles.length == 0) {
                file.delete();
                return;
            }

            for (int i = 0; i < childFiles.length; i++) {
                deleteDirectory(childFiles[i]);
            }
            file.delete();
        }
    }
}
