package edu.mobile.assignment;

import android.app.AlarmManager;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.app.LoaderManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

import edu.mobile.assignment.data.LectureDataModel;

public class LectureListFragment  extends ListFragment implements LoaderManager.LoaderCallbacks<Cursor>{

    private SimpleCursorAdapter adapter;

    boolean dualPane;
    int mCurCheckPosition = 0;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View detailFrame = getActivity().findViewById(R.id.lecture_detail_container);
        dualPane = detailFrame != null && detailFrame.getVisibility() == View.VISIBLE;

        if(dualPane){
            getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
            showDetails(mCurCheckPosition);

        }


        ProgressBar progressBar = new ProgressBar(getActivity());
        progressBar.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        progressBar.setIndeterminate(true);
        getListView().setEmptyView(progressBar);

        ViewGroup root = (ViewGroup) getActivity().findViewById(android.R.id.content);
        root.addView(progressBar);

        String [] fromColumn ={LectureDataModel.LectureEntity.COL_NAME,LectureDataModel.LectureEntity.COL_LECTURER,
                LectureDataModel.LectureEntity.COL_TIME,LectureDataModel.LectureEntity.COL_ROOM};
        int [] toView = {R.id.list_name,R.id.list_lecturer,R.id.list_time,R.id.list_room};

        adapter = new SimpleCursorAdapter(getActivity(),R.layout.list_item_card,null,
                fromColumn,toView,0){
            @Override
            public void bindView(View view, Context context, Cursor cursor) {
                super.bindView (view, context, cursor);
                if(((TextView)view.findViewById(R.id.list_name)).getText().toString().length() == 0){
                    view.findViewById(R.id.empty_slot).setVisibility(View.VISIBLE);
                    view.setClickable(false);
                    view.setOnClickListener(null);
                }
            }
        };

        setListAdapter(adapter);

        getLoaderManager().initLoader(0,null,this);

        adapter.setViewBinder(new SimpleCursorAdapter.ViewBinder() {
            @Override
            public boolean setViewValue(View view, Cursor cursor, int i) {
                if(view.getId() == R.id.list_time){
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
        gestureDetector = new GestureDetector(getActivity(),new MyGestureDetector(getListView()));

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

    private void showDetails(int mCurCheckPosition) {

    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {

        if(dualPane){
            LectureDetailFragment detailFragment = new LectureDetailFragment();
            Bundle args = new Bundle();
            args.putInt("_ID",(int)id);
            detailFragment.setArguments(args);
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.lecture_detail_container,detailFragment);
            ft.commit();


        }else{
            Intent intent = new Intent(getActivity(),LectureDetailActivity.class);
            intent.putExtra("_ID",(int)id);
            startActivity(intent);
        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        String[] columns = {
                LectureDataModel.LectureEntity.COL_NAME,
                LectureDataModel.LectureEntity.COL_LECTURER,
                LectureDataModel.LectureEntity.COL_TIME,
                LectureDataModel.LectureEntity.COL_ROOM
        };
        return new CursorLoader(getActivity(), LectureDataModel.BASE_CONTENT_URI, columns,
                LectureDataModel.LectureEntity.COL_TIME+" >= ? and "+LectureDataModel.LectureEntity.COL_TIME+" < ?",
                new String[]{String.valueOf(getActivity().getIntent().getIntExtra("day", 0)),
                        String.valueOf(getActivity().getIntent().getIntExtra("day", 0)+86400000)},null);
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

        Intent intent = new Intent(getActivity(),LectureNotifyReceiver.class);
        Cursor c = ((SimpleCursorAdapter) getListView().getAdapter()).getCursor();
        c.moveToPosition(pos);
        intent.putExtra("name",c.getString(c.getColumnIndexOrThrow(LectureDataModel.LectureEntity.COL_NAME)));
        intent.putExtra("time",c.getString(c.getColumnIndexOrThrow(LectureDataModel.LectureEntity.COL_TIME)));
        intent.putExtra("room",c.getString(c.getColumnIndexOrThrow(LectureDataModel.LectureEntity.COL_ROOM)));


        PendingIntent pi = PendingIntent.getBroadcast(getActivity(),1,intent,PendingIntent.FLAG_ONE_SHOT);

        AlarmManager am = (AlarmManager) getActivity().getSystemService(getActivity().ALARM_SERVICE);
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
                    Toast.makeText(getActivity(), "Alarm is set", Toast.LENGTH_SHORT).show();
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
                return true;
            return super.onFling(e1, e2, velocityX, velocityY);
        }
    }


}
