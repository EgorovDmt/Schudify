package ru.vsu.schudify;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.UserService;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

public class ChooseActivity extends AppCompatActivity implements View.OnCreateContextMenuListener  {

    public static final String APPLICATION_ID = "F15C1675-EABE-7BAF-FFBF-2687A86AD400";
    public static final String API_KEY = "7597C60F-985E-5836-FF1D-645282E5C800";
    public static final String SERVER_URL = "https://api.backendless.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);

        Backendless.initApp(this, APPLICATION_ID, API_KEY);


        Button searchScheduleButton = (Button) findViewById(R.id.search_schedule);
        searchScheduleButton.setOnClickListener(new OnClickListener()  {
            @Override
            public void onClick(View view) {
                if (checkData()) {
                    Intent intent = new Intent(ChooseActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });


    }


    private boolean checkData() {
        EditText universityView = (EditText) findViewById(R.id.university);
        String university = universityView.getText().toString();
        EditText facultyView = (EditText) findViewById(R.id.faculty);
        String faculty = facultyView.getText().toString();
        EditText courseView = (EditText) findViewById(R.id.course);
        String course = courseView.getText().toString();
        EditText groupView = (EditText) findViewById(R.id.group);
        String group = groupView.getText().toString();

        boolean checkEmptyness=false;

        if (university.isEmpty()) universityView.setError("Заполните поле");
        if (faculty.isEmpty()) facultyView.setError("Заполните поле");
        if (course.isEmpty()) courseView.setError("Заполните поле");
        if (group.isEmpty()) groupView.setError("Заполните поле");

        if (!university.isEmpty() && !faculty.isEmpty() && !course.isEmpty() && !group.isEmpty()){
            return true;
        }

        return false;
    }
}





