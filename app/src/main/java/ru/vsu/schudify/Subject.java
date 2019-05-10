package ru.vsu.schudify;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
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

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.UserService;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
public class Subject {
    String title;
    String type;
    String timeStart;
    String timeEnd;
    String classroom;
    String teacher;
    int universityId;
    int weekDay;
    int facultyId;
    int groupId;

    Subject(String title, String type, String timeStart, String timeEnd, String classroom, String teacher, int universityId, int weekDay, int facultyId, int groupId) {
        this.title = title;
        this.type = type;
        this.timeStart=timeStart;
        this.timeEnd=timeEnd;
        this.classroom = classroom;
        this.teacher = teacher;
        this.universityId = universityId;
        this.weekDay = weekDay;
        this.facultyId = facultyId;
        this.groupId = groupId;
    }

    Subject(Subject subject) {
        this.title = subject.title;
        this.type = subject.type;
        this.timeStart=subject.timeStart;
        this.timeEnd=subject.timeEnd;
        this.classroom = subject.classroom;
        this.teacher = subject.teacher;
        this.universityId = subject.universityId;
        this.weekDay = subject.weekDay;
        this.facultyId = subject.facultyId;
        this.groupId = subject.groupId;
    }

    Subject(){}
}