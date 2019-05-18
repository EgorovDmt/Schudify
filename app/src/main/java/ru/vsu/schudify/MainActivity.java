package ru.vsu.schudify;

import android.content.Intent;
import android.os.Bundle;

import android.content.Context;
import android.os.StrictMode;
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
import android.widget.ViewFlipper;

import com.backendless.Backendless;
import com.backendless.persistence.DataQueryBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements OnTouchListener
{
    private ViewFlipper flipper = null;
    private float fromPosition;

    private Toolbar toolbar;

    private List<Subject> subjectCards;
    private List<Subject> subjectCardForDay = new ArrayList<Subject>();
    private List<Subject> subjectCardForWrongSeason = new ArrayList<Subject>();
    private List<Subject> subjectCardForRightSeason = new ArrayList<Subject>();
    private RecyclerView rv1;
    private RecyclerView rv2;
    private RecyclerView rv3;
    private RecyclerView rv4;
    private RecyclerView rv5;
    private RecyclerView rv6;
    private RecyclerView rv7;
    private RecyclerView rv8;
    private RecyclerView rv9;
    private RecyclerView rv10;
    private RecyclerView rv11;
    private RecyclerView rv12;
    private RecyclerView rv13;
    private RecyclerView rv14;

    List<Map> subjects;

    public String season = "";

    public String getSeason(){
        return season;
    }


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        LinearLayout mainLayout = (LinearLayout) findViewById(R.id.main_layout);
        mainLayout.setOnTouchListener(this);

        flipper = (ViewFlipper) findViewById(R.id.flipper);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        int layouts[] = new int[]{ R.layout.monday, R.layout.tuesday, R.layout.wednesday, R.layout.thursday, R.layout.friday, R.layout.saturday, R.layout.sunday};
        for (int layout : layouts)
            flipper.addView(inflater.inflate(layout, null));

        setManager();

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        String university = getIntent().getStringExtra("university").toLowerCase();
        String city = getIntent().getStringExtra("city").toLowerCase();
        String faculty = getIntent().getStringExtra("faculty").toLowerCase();
        String course = getIntent().getStringExtra("course").toLowerCase();
        String group = getIntent().getStringExtra("group").toLowerCase();

        setSubjectsToCards(city, university, faculty, course, group);

        setSubjectCardForSeason(season);

        initializeAdapter(rv1,1 );
        initializeAdapter(rv2, 2);
        initializeAdapter(rv3, 3);
        initializeAdapter(rv4, 4);
        initializeAdapter(rv5, 5);
        initializeAdapter(rv6, 6);
        initializeAdapter(rv7, 7);
        initializeAdapter(rv8,8 );
        initializeAdapter(rv9, 9);
        initializeAdapter(rv10, 10);
        initializeAdapter(rv11, 11);
        initializeAdapter(rv12, 12);
        initializeAdapter(rv13, 13);
        initializeAdapter(rv14, 14);
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
            case R.id.calendar:

                break;
            case R.id.settings:

                break;
            case R.id.teacher:

                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public List<Subject> setSubjectCardForDay(int day, List<Subject> subjectCardForDays){

        ArrayList<Subject> subjectCards = new ArrayList<Subject>(subjectCardForDays);

        for (Subject subject:subjectCardForDays) {
            if (subject.weekDay != day){
                subjectCards.remove(subject);
            }
        }

        return subjectCards;

    }

    public void setSubjectCardForSeason(String season){

        subjectCardForWrongSeason = new ArrayList<Subject>(subjectCards);
        subjectCardForRightSeason = new ArrayList<Subject>(subjectCards);

        for (Subject subject:subjectCards) {
            String tempSeason = subject.season.toString();
            if (!(tempSeason.equals(season))){
                subjectCardForRightSeason.remove(subject);
            }
            else {
                subjectCardForWrongSeason.remove(subject);
            }
        }

    }

    public void setManager(){
        rv1=(RecyclerView)findViewById(R.id.rv1);
        LinearLayoutManager llm1 = new LinearLayoutManager(this);
        rv1.setLayoutManager(llm1);

        rv2=(RecyclerView)findViewById(R.id.rv2);
        LinearLayoutManager llm2 = new LinearLayoutManager(this);
        rv2.setLayoutManager(llm2);

        rv3=(RecyclerView)findViewById(R.id.rv3);
        LinearLayoutManager llm3 = new LinearLayoutManager(this);
        rv3.setLayoutManager(llm3);

        rv4=(RecyclerView)findViewById(R.id.rv4);
        LinearLayoutManager llm4 = new LinearLayoutManager(this);
        rv4.setLayoutManager(llm4);

        rv5=(RecyclerView)findViewById(R.id.rv5);
        LinearLayoutManager llm5 = new LinearLayoutManager(this);
        rv5.setLayoutManager(llm5);

        rv6=(RecyclerView)findViewById(R.id.rv6);
        LinearLayoutManager llm6 = new LinearLayoutManager(this);
        rv6.setLayoutManager(llm6);

        rv7=(RecyclerView)findViewById(R.id.rv7);
        LinearLayoutManager llm7 = new LinearLayoutManager(this);
        rv7.setLayoutManager(llm7);

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
    }

    public boolean onTouch(View view, MotionEvent event)
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

    private void initializeAdapter(RecyclerView rv, int i){

        List<Subject> subjectCard=new ArrayList<>();

        if (i>7){
            i-=7;
            subjectCard = setSubjectCardForDay(i, subjectCardForWrongSeason);
        }else {
            subjectCard = setSubjectCardForDay(i, subjectCardForRightSeason);
        }
        RVAdapter adapter = new RVAdapter(subjectCard, season);
        rv.setAdapter(adapter);
    }

    public void swapMaps(Map<String, String> first, Map<String, String> second){
        for (Map.Entry<String, String> parameter1 : first.entrySet()) {

            if (!(parameter1.getKey().toString().equals("___class")) && !(parameter1.getKey().toString().equals("ownerId")) &&
                    !(parameter1.getKey().toString().equals("created")) && !(parameter1.getKey().toString().equals("updated")) ){

                        for (Map.Entry<String, String> parameter2 : second.entrySet()) {

                            if (parameter1.getKey()==parameter2.getKey() && !(parameter2.getKey().toString().equals("___class")) &&
                                    !(parameter2.getKey().toString().equals("ownerId")) && !(parameter2.getKey().toString().equals("created")) &&
                                    !(parameter2.getKey().toString().equals("updated")) ){

                                if ((parameter1.getValue()==parameter2.getValue())){ break;}

                                String tempFirstValue;
                                String tempSecondValue;

                                if (parameter1.getValue()==null) { tempFirstValue = ""; }
                                else tempFirstValue = parameter1.getValue().toString();

                                if (parameter2.getValue()==null) { tempSecondValue = ""; }
                                else tempSecondValue = parameter2.getValue().toString();

                                String tempValue = tempFirstValue;
                                first.put(parameter1.getKey().toString(), tempSecondValue);
                                second.put(parameter2.getKey().toString(), tempValue);
                                break;
                            }
                        }
            }
        }

    }

    public List<Map> sortSubjectByTime(List<Map> subjects){
        for (int i=0; i<subjects.size()-1; i++) {
            Map firstSubject = subjects.get(i);

            for (int j=i+1; j<subjects.size(); j++){

                Map secondSubject = subjects.get(j);
                String firstValue = (String) firstSubject.get("timeStart");
                String secondValue = (String) secondSubject.get("timeStart");
                char firstSignOfFirst = firstValue.charAt(0);
                char secondSignOfFirst = firstValue.charAt(1);
                char firstSignOfSecond = secondValue.charAt(0);
                char secondSignOfSecond = secondValue.charAt(1);

                if (firstSignOfFirst<firstSignOfSecond){

                    swapMaps(firstSubject, secondSubject);
                }
                else if (firstSignOfFirst==firstSignOfSecond){
                    if (secondSignOfFirst>secondSignOfSecond){
                        swapMaps(firstSubject, secondSubject);
                    }
                }
            }

        }
        return subjects;
    }

    public String getTableParameter(String tableName, String whereClause, boolean trueSeason){

        DataQueryBuilder DataQuery = DataQueryBuilder.create();
        DataQuery.setWhereClause( whereClause );

        List<Map> table = Backendless.Persistence.of( tableName ).find( DataQuery );
        Map tempTable;
        String id = "0";

        if (table.isEmpty()){

        }
        else{
            tempTable = table.get(0);
            id = tempTable.get("id").toString();
            if (trueSeason){
                season = tempTable.get("season").toString();
            }
            return id;
        }
        return id;
    }

    public void setSubjectsToCards(String city, String university, String faculty, String course, String group){
        subjectCards = new ArrayList<Subject>();

        String universityWhereClause = "name = '" +university+ "' and city = '"+city+"'";
        String university_id = getTableParameter("university", universityWhereClause, false);
        checkEmptyness(university_id);

        String facultyWhereClause = "name = '" + faculty + "'";
        String faculty_id = getTableParameter("faculty", facultyWhereClause, true);
        checkEmptyness(faculty_id);

        String courseWhereClause = "id = '" + course + "'";
        String course_id = getTableParameter("course", courseWhereClause, false);
        checkEmptyness(course_id);

        String groupWhereClause = "name = '" + group + "'";
        String group_id = getTableParameter("group", groupWhereClause, false);
        checkEmptyness(group_id);

        String subjectWhereClause = "university_id = '" +university_id+"' and faculty_id = '"+ faculty_id+"' and group_id = '"+group_id+"' and course_id = '"+course_id+"'";
        DataQueryBuilder subjectDataQuery = DataQueryBuilder.create();
        subjectDataQuery.setWhereClause( subjectWhereClause );

        List<Map> subjects = Backendless.Persistence.of( "subject" ).find( subjectDataQuery );
        checkEmptyness(subjects);
        subjects=sortSubjectByTime(subjects);

        for (Map<String, String> subject : subjects) {

            Subject tempSubject = new Subject();

            for (Map.Entry<String, String> entry : subject.entrySet()) {
                if (entry.getKey().equals("title"))
                    tempSubject.title =  (entry.getValue()).toString();
                else if (entry.getKey().equals("type"))
                    tempSubject.type =  (entry.getValue()).toString();
                else if (entry.getKey().equals("timeStart"))
                    tempSubject.timeStart =  (entry.getValue()).toString();
                else if (entry.getKey().equals("timeEnd"))
                    tempSubject.timeEnd =  (entry.getValue()).toString();
                else if (entry.getKey().equals("classroom"))
                    tempSubject.classroom =  (entry.getValue()).toString();
                else if (entry.getKey().equals("teacher"))
                    tempSubject.teacher =  (entry.getValue()).toString();
                else if (entry.getKey().equals("university_id"))
                    tempSubject.universityId =  Integer.parseInt(entry.getValue().toString());
                else if (entry.getKey().equals("faculty_id"))
                    tempSubject.facultyId =  Integer.parseInt(entry.getValue().toString());
                else if (entry.getKey().equals("group_id"))
                    tempSubject.groupId =  Integer.parseInt(entry.getValue().toString());
                else if (entry.getKey().equals("week_day"))
                    tempSubject.weekDay =  Integer.parseInt(entry.getValue().toString());
                else if (entry.getKey().equals("season"))
                    tempSubject.season =  (entry.getValue().toString());
                else if (entry.getKey().equals("subgroup")){
                    if (entry.getValue()==null){
                        tempSubject.subgroup =  "";
                    }else{
                        tempSubject.subgroup =  (entry.getValue().toString());
                    }
                }

            }
            subjectCards.add(new Subject(tempSubject));
        }

    }

    public void checkEmptyness(String parameter){
        if (parameter=="0"){
            Intent intent = new Intent(MainActivity.this, ErrorActivity.class);
            intent.putExtra("reIntent", "1");
            startActivity(intent);
        }
    }

    public void checkEmptyness(List<Map> parameter){
        if (parameter.size()==0){
            Intent intent = new Intent(MainActivity.this, ErrorActivity.class);
            intent.putExtra("reIntent", "1");
            startActivity(intent);
        }
    }
}
