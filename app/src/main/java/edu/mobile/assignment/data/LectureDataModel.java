package edu.mobile.assignment.data;

import android.net.Uri;
import android.provider.BaseColumns;

import java.util.Date;
import java.util.Random;
import java.util.UUID;

/**
 * Created by alvar on 26/11/14.
 */
public class LectureDataModel {
    public static final String CONTENT_AUTHORITY = "edu.mobile.assignment.data";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String PATH_WEATHER = "lecture";

    public static final class LectureEntity implements BaseColumns {


        public static final String TABLE_NAME = "lecture";

        public static final String COL_NAME = "name";
        public static final String COL_TIME = "time";
        public static final String COL_LECTURER = "lecturer";
        public static final String COL_ROOM = "room";
        public static final String COL_CODE = "code";
        public static final String COL_LAT = "lat";
        public static final String COL_LON = "lon";

        public static final String CREATE_SQL = "create table "+TABLE_NAME+" (" +
                _ID + " integer primary key autoincrement, " +
                COL_NAME + " text, " +
                COL_CODE + " text, " +
                COL_LECTURER + " text, " +
                COL_ROOM + " text,"+
                COL_TIME + " integer, " +
                COL_LAT + " integer, " +
                COL_LON + " integer"
                +")";
        public static final String DROP_SQL = "drop table if exist "+TABLE_NAME;

        public static final String INSERT_DATA = "insert into "+TABLE_NAME +" (" +
                COL_NAME+","+COL_CODE+","+COL_LECTURER+","+COL_ROOM+","+COL_TIME+","+COL_LAT+","+COL_LON+") values" +
                "('Cryptography and Network Security','COC140','Dr Ana Salagean','HE010',54000000,52765397,-1226117)," +
                "('Software Project Management ','COC281','Dr Christian Dawson','SMB014',54000000,52765397,-1226117)," +
                "('Mobile Application Development ','COC155','Dr Huanjia Yang','HE010',54000000,52765397,-1226117)," +
                "('Robotics ','COC001','Dr Qinggang Meng','N001',54000000,52765397,-1226117)";

        private static String randomString(int length){
            return UUID.randomUUID().toString().substring(0,length);
        }

        private static int randomInt(){
            return new Random().nextInt(999999);
        }
    }

}
