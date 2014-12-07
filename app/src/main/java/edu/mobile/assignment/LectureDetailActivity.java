package edu.mobile.assignment;

import android.app.Activity;
import android.app.Fragment;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import 	android.widget.ImageView;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import 	java.io.InputStream;
import android.graphics.BitmapFactory;
import java.net.URL;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Calendar;

import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.content.SharedPreferences;

import edu.mobile.assignment.data.DBHelper;
import edu.mobile.assignment.data.LectureDataModel;


public class LectureDetailActivity extends Activity {

    ImageView map_image;
    Button button_to_data_page;
    private SimpleCursorAdapter adapter;
    private SQLiteDatabase db;

    private DBHelper dbHelper;
    private SharedPreferences sharedPreferences;

    private static int data_id = 1;
    private static String data_module_code = "";
    private static String data_module_name = "";
    private static String data_module_lecturer = "";
    private static String data_module_room = "";
    private static String data_module_time = "";
    private static double data_module_lat = 1;
    private static double data_module_lon = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lecture_detail);

        map_image = (ImageView) findViewById(R.id.map_image);

        //Get id from prev page
        Intent intent = this.getIntent();
        getData(intent.getIntExtra("_ID",1));



        //Display module details
        TextView module_code = (TextView) findViewById(R.id.module_code);
        module_code.setText("Module Code: "+data_module_code);

        TextView module_name = (TextView) findViewById(R.id.module_name);
        module_name.setText("Module name: "+data_module_name);

        TextView lecturer = (TextView) findViewById(R.id.lecturer);
        lecturer.setText("Lecturer: "+data_module_lecturer);

        TextView room = (TextView) findViewById(R.id.room);
        room.setText("Room: "+data_module_room);

        TextView time = (TextView) findViewById(R.id.time);
        time.setText("Time: "+data_module_time);

        //Setup map image

        LoadImageFromURL loadImage = new LoadImageFromURL();
        loadImage.execute();




        button_to_data_page = (Button) findViewById(R.id.button_to_data);
        button_to_data_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),LectureFileActivity.class);
                intent.putExtra("moduleCode",data_module_code);
                startActivity(intent);
            }
        });
    }

    public void getData(int id) {
        String where = "_ID =" + id;
        dbHelper = new DBHelper(getApplicationContext(),null);
        db = dbHelper.getReadableDatabase();
        Cursor result = db.query(LectureDataModel.LectureEntity.TABLE_NAME, null, where, null, null, null, null);

        if (result.moveToFirst()) {
            data_id = result.getInt(0);
            data_module_name = result.getString(1);
            data_module_code = result.getString(2);
            data_module_lecturer = result.getString(3);
            data_module_room = result.getString(4);
            int data_module_time_int = result.getInt(5);
            data_module_lat = result.getInt(6)/Math.pow(10,6);
            data_module_lon = result.getInt(7)/Math.pow(10,6);

            Calendar cal = Calendar.getInstance();
            cal.clear();
            cal.add(Calendar.MILLISECOND,data_module_time_int);

            data_module_time = String.format("%d:00", cal.get(Calendar.HOUR_OF_DAY));

            map_image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:"+data_module_lat+","+data_module_lon));
                    startActivity(intent);
                }
            });
        }
        result.close();

    }



    //Setup image function
    public class LoadImageFromURL extends AsyncTask<String, Void, Bitmap>{

        @Override
        protected Bitmap doInBackground(String... params) {
            // TODO Auto-generated method stub
            try {
                URL url = new URL("http://maps.googleapis.com/maps/api/staticmap?center=" + data_module_lat + "," + data_module_lon + "&zoom=15&size=300x300&sensor=false&markers=color:red%7Clabel:A%7C" + data_module_lat + "," + data_module_lon);
                InputStream is = url.openConnection().getInputStream();
                Bitmap bitMap = BitmapFactory.decodeStream(is);
                return bitMap;

            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;

        }

        @Override
        protected void onPostExecute(Bitmap result) {
            // TODO Auto-generated method stub

            super.onPostExecute(result);
            map_image.setImageBitmap(result);
        }

    }

}
