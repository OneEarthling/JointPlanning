package com.example.jointplanning.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.jointplanning.Constants;
import com.example.jointplanning.ISocketNotification;
import com.example.jointplanning.R;
import com.example.jointplanning.SocketManager;
import com.example.jointplanning.authorization.Authorization;
import com.example.jointplanning.authorization.BasicCredentials;

public class ReadyWaitFragment extends Fragment implements ISocketNotification{
    private SocketManager socketManager;
    private TextView tv;

    public static Fragment newInstance() {
        Bundle args = new Bundle();
        ReadyWaitFragment fragment = new ReadyWaitFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        // последний параметр это логин
        //socketManager = SocketManager.createInstance(baseUrl, "dGVzdDp0ZXN0", Authorization.getInstance().getLastAuthUser().getCredentials().login);
        socketManager = SocketManager.createInstance(Constants.BASE_URL, Authorization.getInstance().getUser().getCredentials().getToken(), Authorization.getInstance().getUser().getCredentials().login);
        socketManager.open(this);
        Log.d("one", Authorization.getInstance().getUser().getCredentials().getToken());
        Log.d("one", Authorization.getInstance().getUser().getCredentials().login);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ready_wait, container, false);
        tv = view.findViewById(R.id.plsWait);
        /*tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, ReadyTaskFragment.newInstance());
                transaction.commit();
            }
        });*/

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.fragment_joint_planning, menu);
    }

    @Override
    public void onNotificationMessage(String type, final String buffer) {
        // added getActivity()
        Log.d("one", "message from server received");
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tv.setText(buffer);
                // переход на след фрагмент, теперь нужна задача!
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, ReadyTaskFragment.newInstance());
                transaction.commit();
            }
        });
    }

    @Override
    public void onNotificationDelivered(String buffer) {

    }

    @Override
    public void onNotificationUnDelivered(String buffer) {

    }

}
