package edu.mobile.assignment;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Spinner;

import edu.mobile.assignment.data.DBHelper;


public class LectureInputActivity extends Activity {

    private Spinner daySpinner;
    private Spinner hourSpinner;
    private AutoCompleteTextView codeText;
    private AutoCompleteTextView nameText;
    private AutoCompleteTextView lecturerText;
    private AutoCompleteTextView roomText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lecture_input);
        codeText = (AutoCompleteTextView) findViewById(R.id.code_text);
        nameText = (AutoCompleteTextView) findViewById(R.id.name_text);
        lecturerText = (AutoCompleteTextView) findViewById(R.id.lecturer_text);
        roomText = (AutoCompleteTextView) findViewById(R.id.room_text);
        daySpinner = (Spinner) findViewById(R.id.spinner_day);
        hourSpinner = (Spinner) findViewById(R.id.spinner_hour);
        daySpinner.setAdapter(ArrayAdapter.createFromResource(this,R.array.day_array,android.R.layout.simple_spinner_dropdown_item));
        hourSpinner.setAdapter(ArrayAdapter.createFromResource(this,R.array.hour_array,android.R.layout.simple_spinner_dropdown_item));

    }

    public void submit(View view){
        DBHelper dbHelper = new DBHelper(this,null);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        SQLiteStatement stmt = db.compileStatement("update lecture set code = ?,name = ?,lecturer = ?,room = ? where time = ?");
        stmt.bindString(1,codeText.getText().toString());
        stmt.bindString(2,nameText.getText().toString());
        stmt.bindString(3,lecturerText.getText().toString());
        stmt.bindString(4,roomText.getText().toString());
        stmt.bindLong(5,daySpinner.getSelectedItemPosition()*86400000
                +32400000+(hourSpinner.getSelectedItemPosition()*3600000));
        stmt.execute();
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
