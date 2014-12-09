package edu.mobile.assignment.data;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by alvar on 26/11/14.
 */
public class DBHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "lecture";
    private Context context;

    public DBHelper(Context context, SQLiteDatabase.CursorFactory factory) {
        super(context, DB_NAME, factory, 14);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        execSqlFile(sqLiteDatabase,readSqlFile());

//        sqLiteDatabase.execSQL(LectureDataModel.LectureEntity.CREATE_SQL);
//        sqLiteDatabase.execSQL(LectureDataModel.LectureEntity.INSERT_DATA);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {

        sqLiteDatabase.execSQL(LectureDataModel.LectureEntity.DROP_SQL);
        execSqlFile(sqLiteDatabase,readSqlFile());
//        sqLiteDatabase.execSQL(LectureDataModel.LectureEntity.CREATE_SQL);
//        sqLiteDatabase.execSQL(LectureDataModel.LectureEntity.INSERT_DATA);

    }

    private String readSqlFile(){
        AssetManager assetManager = context.getAssets();
        StringBuilder sql = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(assetManager.open("db.sql")));
            String line;
            while ((line = reader.readLine()) != null){
                sql.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sql.toString();
    }

    private void execSqlFile(SQLiteDatabase sqLiteDatabase,String sql){
        for(String sqlInstruction : sql.split(";")){
            sqLiteDatabase.execSQL(sqlInstruction);
        }
    }
}
