package com.example.ipandaitems.model.greendao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import org.greenrobot.greendao.database.Database;

/**
 * Created by yuerq on 16/6/24.
 */
public class PandaOpenHelper extends DaoMaster.DevOpenHelper {


    public PandaOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {

        if (oldVersion < 2) {

//            FavoriteDao.createTable(db, true);
//            PlayHistoryDao.createTable(db, true);
            LiveChinaChannelEntityDao.createTable(db, true);
        }
    }
}
