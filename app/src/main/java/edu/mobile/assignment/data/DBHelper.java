package edu.mobile.assignment.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by alvar on 26/11/14.
 */
public class DBHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "lecture";

    public DBHelper(Context context, SQLiteDatabase.CursorFactory factory) {
        super(context, DB_NAME, factory, 4);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(LectureDataModel.LectureEntity.CREATE_SQL);
        sqLiteDatabase.execSQL(LectureDataModel.LectureEntity.INSERT_DATA);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {

        sqLiteDatabase.execSQL(LectureDataModel.LectureEntity.DROP_SQL);

    }
}
