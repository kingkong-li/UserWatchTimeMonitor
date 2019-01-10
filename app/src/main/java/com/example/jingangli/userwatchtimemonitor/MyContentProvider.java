package com.example.jingangli.userwatchtimemonitor;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * @author jingang.li
 */
public class MyContentProvider extends ContentProvider {
    private static final String LOG_CLASS_NAME = MyContentProvider.class.getSimpleName();
    /** 数据库操作帮助类 */
    private DatabaseHelper	mOpenHelper;
    /** 数据库名称 */
    private static final String DATABASE_NAME = "MyProvider.db";
    /** 数据库版本号 */
    private static final int DATABASE_VERSION = 1;
    /** 观测时间数据表*/
    private static final String WATCH_TIME_TABLE_NAME = "watch_time_table";
    private static final int WATCH_TIME_TABLE_ID = 1;
    public static final String AUTHORITY = "com.example.jg.MyProvider";
    public static final Uri CONTENT_URI_WATCH_TIME_TABLE = Uri.parse("content://" + AUTHORITY + "/watch_time_table");
    private static final UriMatcher URI_MATCHER;
    static{
        URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);
        URI_MATCHER.addURI(AUTHORITY, WATCH_TIME_TABLE_NAME, WATCH_TIME_TABLE_ID);

    }

    @Override
    public boolean onCreate() {
        mOpenHelper = DatabaseHelper.getInstance(getContext());
        Log.v(LOG_CLASS_NAME, "onCreate this:"+this);
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        Cursor cursor;
        SQLiteDatabase db = mOpenHelper.getReadableDatabase();
        switch (URI_MATCHER.match(uri)) {
            case WATCH_TIME_TABLE_ID:
                cursor=db.query(WATCH_TIME_TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }

        if (cursor != null) {
            cursor.setNotificationUri(getContext().getContentResolver(), uri);
        }
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {

        Uri returnUri;
        SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        long rowId;
        switch (URI_MATCHER.match(uri)){
            case WATCH_TIME_TABLE_ID:
                //values要和table的列对应 否则会出现插入错误*
                rowId = db.insert(WATCH_TIME_TABLE_NAME, null, values);
                if (rowId > 0){
                    returnUri = ContentUris.withAppendedId(CONTENT_URI_WATCH_TIME_TABLE, rowId);
                    getContext().getContentResolver().notifyChange(returnUri, null);
                    return returnUri;
                }
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }

        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        int count;

        switch (URI_MATCHER.match(uri)){
            case WATCH_TIME_TABLE_ID:
                count = db.delete(WATCH_TIME_TABLE_NAME, selection, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        int count ;
        switch (URI_MATCHER.match(uri)){
            case WATCH_TIME_TABLE_ID:
                count = db.update(WATCH_TIME_TABLE_NAME, values, selection, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    private static class DatabaseHelper extends SQLiteOpenHelper{

        private static final String CREATE_WATCH_TIME_TABLE =
                "CREATE TABLE "
                        + WATCH_TIME_TABLE_NAME
                        + "("
                        + "_id" + " INTEGER PRIMARY KEY"
                        + TimeTbItem.PATH_ID+ " TEXT,"
                        + TimeTbItem.ITEM_ID+ " INTEGER,"
                        + TimeTbItem.SHOW_TIME+ " TEXT,"
                        +" );";
        private static DatabaseHelper mInstance = null;



        static DatabaseHelper getInstance(Context context) {
            if (mInstance == null) {
                mInstance = new DatabaseHelper(context.getApplicationContext());
            }

            return mInstance;
        }

        private DatabaseHelper(Context context){
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            Log.v(LOG_CLASS_NAME, "NewDataBaseHelper："+getDatabaseName());
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            Log.v(LOG_CLASS_NAME, "onCreate Table in(db):"+ " " +db);
            // 创建数据库表
            db.execSQL(CREATE_WATCH_TIME_TABLE);

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
}
