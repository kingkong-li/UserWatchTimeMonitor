package com.example.jingangli.userwatchtimemonitor;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

import java.util.ArrayList;

public class MyDbApi {
    /**
     *插入一条数据到数据库
     */
    public static void insertNewItemToDb(final TimeTbItem info) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                ContentValues values = new ContentValues();
                values.put(TimeTbItem.PATH_ID, info.pathId);
                values.put(TimeTbItem.ITEM_ID, info.itemId);
                values.put(TimeTbItem.SHOW_TIME, info.showTime);
                MainApplication
                        .getInstance()
                        .getApplicationContext()
                        .getContentResolver()
                        .insert(MyContentProvider.CONTENT_URI_WATCH_TIME_TABLE,
                                values);
            }
        }).start();

    }

    public static ArrayList<TimeTbItem> getShowTimeList(){
        ArrayList<TimeTbItem> showTimeList = new ArrayList<>();
        ContentResolver contentResolver = MainApplication.getInstance().getContentResolver();
        Uri uri = MyContentProvider.CONTENT_URI_WATCH_TIME_TABLE;
        Cursor cursor = contentResolver.query(uri, null, null, null,null);
        try {
            if (null != cursor && cursor.moveToFirst()) {
                do {
                    TimeTbItem showTimeItem=new TimeTbItem();
                    showTimeItem.pathId=cursor.getString(cursor.getColumnIndex(TimeTbItem.PATH_ID));
                    showTimeItem.itemId=cursor.getInt(cursor.getColumnIndex(TimeTbItem.ITEM_ID));
                    showTimeItem.showTime=cursor.getString(cursor.getColumnIndex(TimeTbItem.SHOW_TIME));
                    showTimeList.add(showTimeItem);
                } while (cursor.moveToNext());
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return showTimeList;

    }
//    public static void updateTbItem(final TimeTbItem info)
//    {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                // 先检索出来
//                ContentResolver contentResolver = MainApplication.getInstance().getContentResolver();
//                Uri uri = MyContentProvider.CONTENT_URI_WATCH_TIME_TABLE;
//                Cursor cursor = contentResolver.query(uri, null, null, null,null);
//
//
//                // where语句
//
//                //插入到原位置
//                ContentValues values = new ContentValues();
//                values.put(TimeTbItem.PATH_ID, info.pathId);
//                values.put(TimeTbItem.ITEM_ID, info.itemId);
//                values.put(TimeTbItem.SHOW_TIME, info.showTime+
//                        cursor.getString(cursor.getColumnIndex(TimeTbItem.SHOW_TIME)));
//                MainApplication
//                        .getInstance()
//                        .getApplicationContext()
//                        .getContentResolver()
//                        .update(MyContentProvider.CONTENT_URI_WATCH_TIME_TABLE,
//                                values);
//            }
//        }).start();

//    }

}
