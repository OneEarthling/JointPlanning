package com.example.jointplanning.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jointplanning.App;
import com.example.jointplanning.DataManager;
import com.example.jointplanning.TaskJsonLab;
import com.example.jointplanning.activity.InfoActivity;
import com.example.jointplanning.R;
import com.example.jointplanning.activity.MainActivity;
import com.example.jointplanning.activity.ReadyActivity;
import com.example.jointplanning.activity.SettingsActivity;
import com.example.jointplanning.adapter.TaskAdapter;
import com.example.jointplanning.model.Task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TaskListFragment extends Fragment {

    private RecyclerView mTasksRecyclerView;
    private TaskAdapter mAdapter;
    private List<Task> mTasks = new ArrayList<>();

    public static TaskListFragment newInstance() {
        return new TaskListFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_joint_planning, container, false);
        mTasksRecyclerView = view.findViewById(R.id.tasks_recycler_view);
        mTasksRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        setupAdapter();
        return view;
    }

    private void setupAdapter() {
        if (isAdded()){
            mAdapter = new TaskAdapter(getContext(), mTasks);
            mTasksRecyclerView.setAdapter(mAdapter);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        new getTasksDB().execute();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.fragment_joint_planning, menu);
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.ready:
                Drawable drawable = item.getIcon();
                if(drawable != null) {
                    drawable.mutate();
                    drawable.setColorFilter(getResources().getColor(R.color.colorBlack), PorterDuff.Mode.SRC_ATOP);
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage(R.string.are_you_ready)
                        .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent intent = ReadyActivity.newIntent(getActivity());
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Drawable drawable = item.getIcon();
                                if(drawable != null) {
                                    drawable.mutate();
                                    drawable.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
                                }
                            }
                        });
                builder.create().show();
                return true;
            case R.id.settings:
                intent = SettingsActivity.newIntent(getActivity());
                startActivity(intent);
                return true;
            case R.id.exit:
                android.app.AlertDialog.Builder adb = new android.app.AlertDialog.Builder(getContext());
                adb.setPositiveButton(getResources().getString(R.string.yes), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // ???
                        ((App) getActivity().getApplication()).unAuthorized(true);

                        Intent intent = new Intent(getContext(), MainActivity.class);
                        startActivity(intent);
                        getActivity().finish();
                    }
                });
                adb.setNegativeButton(getResources().getString(R.string.no), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                android.app.AlertDialog alert = adb.create();
                alert.setTitle(getResources().getString(R.string.confirmExit));
                alert.show();
                return true;
            case R.id.info:
                intent = new Intent(getContext(), InfoActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }



    private class getTasksDB extends AsyncTask<Void, Void, List<Task>> {

        @Override
        protected List<Task> doInBackground(Void... params) {
            try {
                return DataManager.getInstance().getTasks();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
//            TaskJsonLab taskLab = null;
//            try {
//                taskLab = TaskJsonLab.get();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            mTasks = taskLab.getTasks();
//            return mTasks;
        }

        @Override
        protected void onPostExecute(List<Task> items) {
            mTasks = items;
            setupAdapter();
        }
    }
}
