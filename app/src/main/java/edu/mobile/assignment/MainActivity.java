package edu.mobile.assignment;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.content.Intent;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }


    }

    public void selectDay(View view){
        Intent intent = new Intent(this.getApplicationContext(),LectureListActivity.class);
        switch (view.getId()){
            case R.id.btn_mon:
                intent.putExtra("day","mon");
                break;
            case R.id.btn_tue:
                intent.putExtra("day","tue");
                break;
            case R.id.btn_wed:
                intent.putExtra("day","wed");
                break;
            case R.id.btn_thur:
                intent.putExtra("day","thur");
                break;
            case R.id.btn_fri:
                intent.putExtra("day","fri");
                break;
        }
        startActivity(intent);

    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }


    }
}
