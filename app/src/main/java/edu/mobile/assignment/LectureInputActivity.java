package edu.mobile.assignment;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;


public class LectureInputActivity extends Activity {

    private Spinner daySpinner;
    private Spinner hourSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lecture_input);
        daySpinner = (Spinner) findViewById(R.id.spinner_day);
        hourSpinner = (Spinner) findViewById(R.id.spinner_hour);
        daySpinner.setAdapter(ArrayAdapter.createFromResource(this,R.array.day_array,android.R.layout.simple_spinner_dropdown_item));
        hourSpinner.setAdapter(ArrayAdapter.createFromResource(this,R.array.hour_array,android.R.layout.simple_spinner_dropdown_item));

    }





//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_lecture_input, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
}
