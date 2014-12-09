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
        Intent intent = new Intent(this.getApplicationContext(),LectureMasterDetailActivity.class);
        switch (view.getId()){
            case R.id.btn_mon:
                intent.putExtra("day",86400000*0);
                break;
            case R.id.btn_tue:
                intent.putExtra("day",86400000*1);
                break;
            case R.id.btn_wed:
                intent.putExtra("day",86400000*2);
                break;
            case R.id.btn_thur:
                intent.putExtra("day",86400000*3);
                break;
            case R.id.btn_fri:
                intent.putExtra("day",86400000*4);
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
