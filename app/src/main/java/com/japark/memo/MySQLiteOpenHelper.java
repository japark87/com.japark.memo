package com.japark.memo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by jinapark on 2016. 8. 13..
 */
public class MySQLiteOpenHelper extends SQLiteOpenHelper {

    public static ArrayList<HashMap<Object, Object>> list = new ArrayList<>();

    public MySQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE MEMO(_id INTEGER PRIMARY KEY AUTOINCREMENT, TITLE TEXT, CONTENTS TEXT, DATE TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    // 메모 입력 시
    public void insert(String title, String contents, String date) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO MEMO VALUES(null, '" + title + "', '" + contents + "', '" + date + "');");
        db.close();
    }

    // 메모 수정 시
    public void update(String title, String contents, String date, int position) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("title", title);
        values.put("contents", contents);
        values.put("date", date);
        db.update("MEMO", values, "_id=?", new String[]{String.valueOf(position)});

    }

    // 메모 삭제 시
    public void delete(int position) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete("MEMO", "_id=?", new String[]{String.valueOf(position)});
    }

    public ArrayList getResult() {
        // 읽기가 가능하게 DB 열기
        SQLiteDatabase db = getReadableDatabase();

        // DB에 있는 데이터를 쉽게 처리하기 위해 Cursor를 사용하여 테이블에 있는 모든 데이터 출력
        Cursor cursor = db.rawQuery("SELECT * FROM MEMO ORDER BY date desc", null);

        while (cursor.moveToNext()) {
            HashMap<Object, Object> map = new HashMap<>();

            map.put("id", cursor.getInt(cursor.getColumnIndex("_id")));
            map.put("title", cursor.getString(cursor.getColumnIndex("title")));
            map.put("contents", cursor.getString(cursor.getColumnIndex("contents")));
            map.put("date", cursor.getString(cursor.getColumnIndex("date")));

            list.add(map);

        }

        return list;
    }
}
