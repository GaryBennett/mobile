package edu.mobile.assignment;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.ListActivity;
import android.app.LoaderManager;
import android.app.PendingIntent;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.view.ViewGroup.LayoutParams;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

import edu.mobile.assignment.data.LectureDataModel;

public class LectureListActivity extends ListActivity implements LoaderManager.LoaderCallbacks<Cursor>{

    private SimpleCursorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ProgressBar progressBar = new ProgressBar(this);
        progressBar.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT));
        progressBar.setIndeterminate(true);
        getListView().setEmptyView(progressBar);

        ViewGroup root = (ViewGroup) findViewById(android.R.id.content);
        root.addView(progressBar);

        String [] fromColumn ={LectureDataModel.LectureEntity.COL_NAME,LectureDataModel.LectureEntity.COL_LECTURER,
                LectureDataModel.LectureEntity.COL_TIME,LectureDataModel.LectureEntity.COL_ROOM};
        int [] toView = {R.id.list_name,R.id.list_lecturer,R.id.list_time,R.id.list_room};

        adapter = new SimpleCursorAdapter(this,R.layout.list_item_card,null,
                fromColumn,toView,0);

        setListAdapter(adapter);

        getLoaderManager().initLoader(0,null,this);

        adapter.setViewBinder(new SimpleCursorAdapter.ViewBinder() {
            @Override
            public boolean setViewValue(View view, Cursor cursor, int i) {
                if(view.getId() == R.id.list_time){
//                    int time = cursor.getInt(cursor.getColumnIndexOrThrow(LectureDataModel.LectureEntity.COL_TIME));
                    int time = cursor.getInt(i);
                    Calendar cal = Calendar.getInstance();
                    cal.clear();
                    cal.add(Calendar.MILLISECOND,time);
                    TextView textView = (TextView) view;
                    textView.setText(cal.get(Calendar.HOUR_OF_DAY)+":00");
                    return true;
                }
                return false;
            }
        });


        final GestureDetector gestureDetector;
        gestureDetector = new GestureDetector(getApplicationContext(),new MyGestureDetector(getListView()));

        getListView().setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (gestureDetector.onTouchEvent(motionEvent)) {
                    showDeleteButton(getListView().pointToPosition((int)motionEvent.getX(),(int)motionEvent.getY()));
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Intent intent = new Intent(this,LectureDetailActivity.class);
        intent.putExtra("_ID",(int)id);
        startActivity(intent);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        String[] columns = {
                LectureDataModel.LectureEntity.COL_NAME,
                LectureDataModel.LectureEntity.COL_LECTURER,
                LectureDataModel.LectureEntity.COL_TIME,
                LectureDataModel.LectureEntity.COL_ROOM
        };
        return new CursorLoader(this, LectureDataModel.BASE_CONTENT_URI, columns,
                LectureDataModel.LectureEntity.COL_TIME+" >= ? and "+LectureDataModel.LectureEntity.COL_TIME+" < ?",
                new String[]{String.valueOf(getIntent().getIntExtra("day",0)),
                        String.valueOf(getIntent().getIntExtra("day",0)+86400000)},null);
//        return new CursorLoader(this, LectureDataModel.BASE_CONTENT_URI, columns,null,null,null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) { adapter.swapCursor(cursor); }

    @Override
    public void onLoaderReset(Loader<Cursor> cursorLoader) {
        adapter.swapCursor(null);
    }


    private void setAlarmForLecture(int pos){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.SECOND,5);

        Intent intent = new Intent(getApplicationContext(),LectureNotifyReceiver.class);
        Cursor c = ((SimpleCursorAdapter) getListView().getAdapter()).getCursor();
        c.moveToPosition(pos);
        intent.putExtra("name",c.getString(c.getColumnIndexOrThrow(LectureDataModel.LectureEntity.COL_NAME)));
        intent.putExtra("time",c.getString(c.getColumnIndexOrThrow(LectureDataModel.LectureEntity.COL_TIME)));
        intent.putExtra("room",c.getString(c.getColumnIndexOrThrow(LectureDataModel.LectureEntity.COL_ROOM)));


        PendingIntent pi = PendingIntent.getBroadcast(getApplicationContext(),1,intent,PendingIntent.FLAG_ONE_SHOT);

        AlarmManager am = (AlarmManager) getSystemService(getApplicationContext().ALARM_SERVICE);
        am.set(AlarmManager.RTC_WAKEUP,cal.getTimeInMillis(),pi);
    }

    private boolean showDeleteButton(final int pos) {
        View child = getListView().getChildAt(pos);
        if (child != null){
            ImageButton delete = (ImageButton) child.findViewById(R.id.btn_set_alarm);
            if (delete != null)
                if (delete.getVisibility() == View.GONE)
                    delete.setVisibility(View.VISIBLE);
                else
                    delete.setVisibility(View.GONE);
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    setAlarmForLecture(pos);
                    view.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(),"Alarm is set",Toast.LENGTH_SHORT).show();
                }
            });
            return true;
        }
        return false;
    }

    public static class MyGestureDetector extends GestureDetector.SimpleOnGestureListener {
        private ListView list;

        public static final int SWIPE_MIN_DISTANCE = 120;
        public static final int SWIPE_THRESHOLD_VELOCITY = 200;

        public MyGestureDetector(ListView list) {
            this.list = list;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE
                    && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY)
//                if (showDeleteButton(e1))
                    return true;
            return super.onFling(e1, e2, velocityX, velocityY);
        }
    }
}
