package edu.mobile.assignment;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.content.res.Configuration;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getResources().getConfiguration().orientation
                == Configuration.ORIENTATION_LANDSCAPE) {
            // If the screen is now in landscape mode, we can show the
            // dialog in-line with the list so we don't need this activity.
            finish();
            return;
        }

        if (savedInstanceState == null) {
            // During initial setup, plug in the details fragment.
            LectureDetailFragment details = new LectureDetailFragment();
            details.setArguments(getIntent().getExtras());
            getFragmentManager().beginTransaction().add(android.R.id.content, details).commit();
        }
    }
}
