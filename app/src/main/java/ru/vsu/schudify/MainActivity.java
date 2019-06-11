package ru.vsu.schudify;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import android.content.Context;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.DataQueryBuilder;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import javax.security.auth.Subject;


public class MainActivity extends AppCompatActivity implements OnTouchListener
{
    private ViewFlipper flipper = null;
    private float fromPosition;

    private Toolbar toolbar;

    private MainPresenter presenter;
    private MainModel model;

    private List<Subject> subjectCards;
    private List<Subject> subjectCardForWrongSeason = new ArrayList<Subject>();
    private List<Subject> subjectCardForRightSeason = new ArrayList<Subject>();
    public static RecyclerView mondayNumerical;//numeral week
    public static RecyclerView tuesdayNumerical;
    public static RecyclerView wednesdayNumerical;
    public static RecyclerView thursdayNumerical;
    public static RecyclerView fridayNumerical;
    public static RecyclerView saturdayNumerical;
    public static RecyclerView sundayNumerical;
    public static RecyclerView rv8;//denumeral week
    public static RecyclerView rv9;
    public static RecyclerView rv10;
    public static RecyclerView rv11;
    public static RecyclerView rv12;
    public static RecyclerView rv13;
    public static RecyclerView rv14;

    List<Map> subject = new ArrayList<>();

    public void setDataToSubjectsFromServer(List<Map> subjects){
        this.subject=subjects;
    }

    public String season = "";

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        LinearLayout mainLayout = (LinearLayout) findViewById(R.id.main_layout);
        mainLayout.setOnTouchListener(this);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        presenter = new MainPresenter(this);

        presenter.attachView(MainActivity.this);

        flipper = (ViewFlipper) findViewById(R.id.flipper);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.WHITE);


        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        int layouts[] = new int[]{ R.layout.monday, R.layout.tuesday, R.layout.wednesday, R.layout.thursday, R.layout.friday, R.layout.saturday, R.layout.sunday};
        for (int layout : layouts)
            flipper.addView(inflater.inflate(layout, null));

        setManager();

        String university = getIntent().getStringExtra("university").toLowerCase();
        String city = getIntent().getStringExtra("city").toLowerCase();
        String faculty = getIntent().getStringExtra("faculty").toLowerCase();
        String course = getIntent().getStringExtra("course").toLowerCase();
        String group = getIntent().getStringExtra("group").toLowerCase();

        presenter.setSubjectsToCards(city, university, faculty, course, group);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_layout, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.change_schedule:
                Intent intent = new Intent(MainActivity.this, ChooseActivity.class);
                intent.putExtra("reIntent", "1");
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }//menu tap describer

    public void setManager(){
        mondayNumerical =(RecyclerView)findViewById(R.id.rv1);
        LinearLayoutManager llm1 = new LinearLayoutManager(this);
        mondayNumerical.setLayoutManager(llm1);

        tuesdayNumerical =(RecyclerView)findViewById(R.id.rv2);
        LinearLayoutManager llm2 = new LinearLayoutManager(this);
        tuesdayNumerical.setLayoutManager(llm2);

        wednesdayNumerical =(RecyclerView)findViewById(R.id.rv3);
        LinearLayoutManager llm3 = new LinearLayoutManager(this);
        wednesdayNumerical.setLayoutManager(llm3);

        thursdayNumerical =(RecyclerView)findViewById(R.id.rv4);
        LinearLayoutManager llm4 = new LinearLayoutManager(this);
        thursdayNumerical.setLayoutManager(llm4);

        fridayNumerical =(RecyclerView)findViewById(R.id.rv5);
        LinearLayoutManager llm5 = new LinearLayoutManager(this);
        fridayNumerical.setLayoutManager(llm5);

        saturdayNumerical =(RecyclerView)findViewById(R.id.rv6);
        LinearLayoutManager llm6 = new LinearLayoutManager(this);
        saturdayNumerical.setLayoutManager(llm6);

        sundayNumerical =(RecyclerView)findViewById(R.id.rv7);
        LinearLayoutManager llm7 = new LinearLayoutManager(this);
        sundayNumerical.setLayoutManager(llm7);

        rv8=(RecyclerView)findViewById(R.id.rv8);
        LinearLayoutManager llm8 = new LinearLayoutManager(this);
        rv8.setLayoutManager(llm8);

        rv9=(RecyclerView)findViewById(R.id.rv9);
        LinearLayoutManager llm9 = new LinearLayoutManager(this);
        rv9.setLayoutManager(llm9);

        rv10=(RecyclerView)findViewById(R.id.rv10);
        LinearLayoutManager llm10 = new LinearLayoutManager(this);
        rv10.setLayoutManager(llm10);

        rv11=(RecyclerView)findViewById(R.id.rv11);
        LinearLayoutManager llm11 = new LinearLayoutManager(this);
        rv11.setLayoutManager(llm11);

        rv12=(RecyclerView)findViewById(R.id.rv12);
        LinearLayoutManager llm12 = new LinearLayoutManager(this);
        rv12.setLayoutManager(llm12);

        rv13=(RecyclerView)findViewById(R.id.rv13);
        LinearLayoutManager llm13 = new LinearLayoutManager(this);
        rv13.setLayoutManager(llm13);

        rv14=(RecyclerView)findViewById(R.id.rv14);
        LinearLayoutManager llm14 = new LinearLayoutManager(this);
        rv14.setLayoutManager(llm14);
    }//setting layout manager to all 14 cardviews

    public boolean onTouch(View view, MotionEvent event)//describes actions of swipes
    {
        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                fromPosition = event.getX();
                break;
            case MotionEvent.ACTION_UP:
                float toPosition = event.getX();
                if (fromPosition > toPosition)
                {
                    flipper.setInAnimation(AnimationUtils.loadAnimation(this,R.anim.go_next_in));
                    flipper.setOutAnimation(AnimationUtils.loadAnimation(this,R.anim.go_next_out));
                    flipper.showNext();
                }
                else if (fromPosition < toPosition)
                {
                    flipper.setInAnimation(AnimationUtils.loadAnimation(this,R.anim.go_prev_in));
                    flipper.setOutAnimation(AnimationUtils.loadAnimation(this,R.anim.go_prev_out));
                    flipper.showPrevious();
                }
            default:
                break;
        }
        return true;
    }

    public MainPresenter getPresenter(){
        return presenter;
    }
    public void setNoLessonByRVNumber(int i){

        if (i==1){
            TextView noLessons = (TextView) findViewById(R.id.no_lessons_1);
            noLessons.setText("Занятий нет");
            return;
        }
        else if (i==2){
            TextView noLessons = (TextView) findViewById(R.id.no_lessons_2);
            noLessons.setText("Занятий нет");
            return;
        }
        else if (i==3){
            TextView noLessons = (TextView) findViewById(R.id.no_lessons_3);
            noLessons.setText("Занятий нет");
            return;
        }
        else if (i==4){
            TextView noLessons = (TextView) findViewById(R.id.no_lessons_4);
            noLessons.setText("Занятий нет");
            return;
        }
        else if (i==5){
            TextView noLessons = (TextView) findViewById(R.id.no_lessons_5);
            noLessons.setText("Занятий нет");
            return;
        }
        else if (i==6){
            TextView noLessons = (TextView) findViewById(R.id.no_lessons_6);
            noLessons.setText("Занятий нет");
            return;
        }
        else if (i==7){
            TextView noLessons = (TextView) findViewById(R.id.no_lessons_7);
            noLessons.setText("Занятий нет");
            return;
        }
        else if (i==8){
            TextView noLessons = (TextView) findViewById(R.id.no_lessons_8);
            noLessons.setText("Занятий нет");
            return;
        }
        else if (i==9){
            TextView noLessons = (TextView) findViewById(R.id.no_lessons_9);
            noLessons.setText("Занятий нет");
            return;
        }
        else if (i==10){
            TextView noLessons = (TextView) findViewById(R.id.no_lessons_10);
            noLessons.setText("Занятий нет");
            return;
        }
        else if (i==11){
            TextView noLessons = (TextView) findViewById(R.id.no_lessons_11);
            noLessons.setText("Занятий нет");
            return;
        }
        else if (i==12){
            TextView noLessons = (TextView) findViewById(R.id.no_lessons_12);
            noLessons.setText("Занятий нет");
            return;
        }
        else if (i==13){
            TextView noLessons = (TextView) findViewById(R.id.no_lessons_13);
            noLessons.setText("Занятий нет");
            return;
        }
        else if (i==14){
            TextView noLessons = (TextView) findViewById(R.id.no_lessons_14);
            noLessons.setText("Занятий нет");
            return;
        }
        return;
    }

    public void goToErrorActivity(){
        Intent intent = new Intent(MainActivity.this, ErrorActivity.class);
        intent.putExtra("reIntent", "1");
        startActivity(intent);
    }
}

