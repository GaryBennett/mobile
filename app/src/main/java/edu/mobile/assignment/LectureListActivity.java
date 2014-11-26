package edu.mobile.assignment;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ListActivity;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.view.ViewGroup.LayoutParams;
import android.widget.SimpleCursorAdapter;

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
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        String[] columns = {
                LectureDataModel.LectureEntity.COL_NAME,
                LectureDataModel.LectureEntity.COL_LECTURER,
                LectureDataModel.LectureEntity.COL_TIME,
                LectureDataModel.LectureEntity.COL_ROOM
        };
        return new CursorLoader(this, LectureDataModel.BASE_CONTENT_URI, columns,null,null,null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {
        adapter.swapCursor(cursor);

    }

    @Override
    public void onLoaderReset(Loader<Cursor> cursorLoader) {
        adapter.swapCursor(null);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_lecture_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
