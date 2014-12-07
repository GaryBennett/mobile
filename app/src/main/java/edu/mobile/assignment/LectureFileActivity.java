package edu.mobile.assignment;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import android.widget.ListView;
import android.widget.TextView;
import android.widget.Button;
import android.content.Intent;
import android.net.Uri;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LectureFileActivity extends Activity {
    private static final String TAG = "MyExplorerDemo";
    private static final int REQUEST_EX = 1;
    private ListView listView;
    private String moduleCode;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lecture_file);

        moduleCode = getIntent().getStringExtra("moduleCode");

        listView=(ListView) findViewById(R.id.listView);
        listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,getData()));



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SharedPreferences mySharedPreferences = getSharedPreferences(moduleCode,
                        Activity.MODE_PRIVATE);
//                mySharedPreferences.getString(moduleCode+((TextView)view).getText().toString(),"");
                Uri uri = Uri.parse(mySharedPreferences.getString(moduleCode+((TextView)view).getText().toString(),""));
//                Uri uri = Uri.parse(mySharedPreferences.getString(String.valueOf(position), ""));
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(uri, "*/*");
                startActivity(intent);
            }
        });

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ExDialog.class);
                intent.putExtra("explorer_title",
                        getString(R.string.dialog_read_from_dir));
                intent.setDataAndType(Uri.fromFile(new File("/sdcard")), "*/*");
                startActivityForResult(intent, REQUEST_EX);

//                Intent fileintent = new Intent(Intent.ACTION_GET_CONTENT);
//                fileintent.setType("gagt/sdf");
//                try {
//                    startActivityForResult(fileintent, 1);
//                } catch (ActivityNotFoundException e) {
//                    Log.e("tag", "No activity can handle picking a file. Showing alternatives.");
//                }
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent intent) {
        String path;
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_EX) {
                int i;
                Uri uri = intent.getData();
                SharedPreferences mySharedPreferences = getSharedPreferences(moduleCode,
                        Activity.MODE_PRIVATE);
                i=mySharedPreferences.getAll().size();
                SharedPreferences.Editor editor = mySharedPreferences.edit();
                editor.putString(moduleCode+new File(uri.toString()).getName(), ""+uri);
                editor.commit();

                listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,getData()));

            }
        }
    }

    private List<String> getData() {
        int i;

        SharedPreferences mySharedPreferences = getSharedPreferences(moduleCode,
                Activity.MODE_PRIVATE);
        i=mySharedPreferences.getAll().size();
        List<String> data = new ArrayList<String>();

        Iterator iterator = mySharedPreferences.getAll().values().iterator();

        while (iterator.hasNext()){
            String fileName = new File((String) iterator.next()).getName();
            data.add(fileName);
        }

        return data;

    }
}
