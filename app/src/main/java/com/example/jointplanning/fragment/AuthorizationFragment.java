package com.example.jointplanning.fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.jointplanning.App;
import com.example.jointplanning.ICallback;
import com.example.jointplanning.R;
import com.example.jointplanning.activity.MainActivity;
import com.example.jointplanning.authorization.Authorization;
import com.example.jointplanning.authorization.AuthorizationMeta;
import com.example.jointplanning.authorization.BasicUser;
import com.example.jointplanning.rpc.Meta;
import com.example.jointplanning.util.DateUtil;
import com.example.jointplanning.util.NetworkUtil;
import com.example.jointplanning.util.Version;
import com.example.jointplanning.util.VersionUtil;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;
import java.util.UUID;


public class AuthorizationFragment extends Fragment implements View.OnClickListener, TextWatcher {
    private final String LOGIN = "login";
    private Authorization mAuthorization;
    private final int MIN_LENGTH = 3;
    private String login = "";

    private TextView tvVersion;
    private EditText etLogin;
    private TextInputLayout tilLogin;
    private Button btnSignIn;
    private BasicUser mBasicUser;
    private ProgressBar mProgressBar;

    public static AuthorizationFragment newInstance() {
        return new AuthorizationFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAuthorization = Authorization.getInstance();
        mBasicUser = mAuthorization.getLastAuthUser();

        if (savedInstanceState != null) {
            login = savedInstanceState.getString(LOGIN);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mAuthorization.isAutoSignIn()) {
            singIn(mBasicUser.getCredentials().login, mBasicUser.getCredentials().password);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_authorization, container, false);
        etLogin = view.findViewById(R.id.authorization_Login);
        tilLogin = view.findViewById(R.id.authorization_Layout_Login);
        btnSignIn = view.findViewById(R.id.authorization_SignIn);
        btnSignIn.setOnClickListener(this);

        tvVersion = view.findViewById(R.id.authorization_Version);
        tvVersion.setOnClickListener(this);
        tvVersion.setText(getString(R.string.versionShort, getVersion()));

        mProgressBar = view.findViewById(R.id.authorization_Loading);
        etLogin.addTextChangedListener(this);

        return view;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(LOGIN, etLogin.getText().toString());
    }

    private void singIn(String login, String password) {
        if (NetworkUtil.isNetworkAvailable(Objects.requireNonNull(getContext()))) {
            mProgressBar.setVisibility(View.VISIBLE);
            mAuthorization.onSignIn(login, password, Authorization.ONLINE, new ICallback() {
                @Override
                public void onResult(Meta meta) {
                    AuthorizationMeta authorizationMeta = (AuthorizationMeta) meta;

                    switch (authorizationMeta.getStatus()) {
                        case Meta.NOT_AUTHORIZATION:
                            failAuthorized(meta.getMessage());
                            break;
                        case Meta.OK:
                            toast(authorizationMeta.getMessage());
                            onAuthorized();
                            break;

                        default:
                            failAuthorized(getString(R.string.serverNotAvailable));
                            break;
                    }
                }
            });
        } else {
            toast("Нет подключения к интернету.");
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        String login = etLogin.getText().toString();

        if (etLogin.isFocused()) {
            tilLogin.setError(String.format(minLength(login), getString(R.string.nickname)));
        }

        btnSignIn.setEnabled(login.length() >= MIN_LENGTH);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.authorization_Version:
                onVersionClick();
                break;
            case R.id.authorization_SignIn:
                singIn(etLogin.getText().toString(), UUID.randomUUID().toString());
                break;

        }
    }

    /**
     * Получение версии приложения
     *
     * @return версия
     */
    private String getVersion() {
        String versionName = VersionUtil.getVersionName(Objects.requireNonNull(getContext()));
        if (new Version().getVersionParts(versionName)[2] == Version.PRODUCTION) {
            return VersionUtil.getShortVersionName(getContext());
        } else {
            return VersionUtil.getVersionName(getContext());
        }
    }


    private void toast(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }

    private void onAuthorized() {
        getApplication().onAuthorized();

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_container);
        if(fragment != null) {
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, TaskListFragment.newInstance())
                    .addToBackStack(null)
                    .commit();
        }
    }

    private void failAuthorized(String message) {
        if (!message.isEmpty()) {
            toast(message);
        }
        mProgressBar.setVisibility(View.GONE);
    }

    protected App getApplication() {
        return (App)getActivity().getApplication();
    }

    public void onVersionClick() {
        String versionName = VersionUtil.getVersionName(Objects.requireNonNull(getContext()));
        String status = "неизвестен";
        switch (new Version().getVersionParts(versionName)[2]) {
            case 0:
                status = getString(R.string.alphaText);
                break;
            case 1:
                status = getString(R.string.betaText);
                break;
            case 2:
                status = getString(R.string.releaseCandidateText);
                break;
            case 3:
                status = getString(R.string.productionText);
                break;
        }
        toast(getVersionToast(getString(R.string.versionToast), versionName, status));
    }

    /**
     * Вывод сообщения о версии приложения
     *
     * @param template    шаблон строки
     * @param versionName Версия
     * @param status      статус приложения
     * @return строка
     */
    public String getVersionToast(String template, String versionName, String status) {
        if (isValidTemplate(template)) {
            Version mVersion = new Version();
            int[] parts = mVersion.getVersionParts(versionName);
            String userDateString = DateUtil.convertDateToUserString(mVersion.getBuildDate(Version.BIRTH_DAY, versionName));

            return String.format(template, parts[0], parts[1], userDateString, status);
        }

        return null;
    }

    /**
     * Проверка шаблона сообщения
     *
     * @param template шаблон
     * @return
     */
    boolean isValidTemplate(String template) {
        int count = -1;
        int i = -1;
        do {
            i++;
            count++;
            i = template.indexOf("%s", i);
        } while (i >= 0);

        return count == 4;
    }

    /**
     * Проверка на минимальную длину
     *
     * @param field содержимое поля
     * @return строка с результатом. Если String.Empty, то все хорошо.
     */
    public String minLength(String field) {
        if (field.length() >= MIN_LENGTH) {
            return "";
        } else {
            return "Минимальная длина поля %s должна быть " + MIN_LENGTH + " символа (ов).";
        }
    }
}
