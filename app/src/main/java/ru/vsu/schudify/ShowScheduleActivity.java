package ru.vsu.schudify;

import android.content.Context;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.ViewFlipper;

import com.backendless.Backendless;
import com.backendless.persistence.BackendlessDataQuery;
import com.backendless.persistence.DataQueryBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.backendless.persistence.DataQueryBuilder.create;


public class ShowScheduleActivity extends AppCompatActivity implements View.OnCreateContextMenuListener, View.OnTouchListener  {

    private List subjectCards;
    private RecyclerView rv;

    public static final String API_KEY = "7597C60F-985E-5836-FF1D-645282E5C800";
    public static final String SERVER_URL = "https://api.backendless.com";

    ViewFlipper flipper;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_schedule);

        ViewFlipper flipper = findViewById(R.id.flipper);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        rv=(RecyclerView)findViewById(R.id.rv);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);

        LinearLayout mainLayout = (LinearLayout) findViewById(R.id.main_layout);
        mainLayout.setOnTouchListener(this);



        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        int layouts[] = new int[]{ R.layout.monday_screen};
        for (int layout : layouts)
            flipper.addView(inflater.inflate(layout, null));

        String university = getIntent().getStringExtra("university").toLowerCase();
        String city = getIntent().getStringExtra("city").toLowerCase();
        String faculty = getIntent().getStringExtra("faculty").toLowerCase();
        String course = getIntent().getStringExtra("course").toLowerCase();
        String group = getIntent().getStringExtra("group").toLowerCase();


        setSubjectsToCards(city, university, faculty, course, group);
        initializeAdapter();
    }

    public boolean onTouch(View view, MotionEvent event)
    {
        float fromPosition = 0;
        float epsilon = (float) 0.00001;

        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                fromPosition = event.getX();
                break;

            case MotionEvent.ACTION_UP:
                float toPosition = event.getX();
                if (fromPosition - toPosition < epsilon)
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

    private void initializeAdapter(){
        RVAdapter adapter = new RVAdapter(subjectCards);
        rv.setAdapter(adapter);
    }

    public void swapMaps(Map<String, String> first, Map<String, String> second){
        for (Map.Entry parameter1 : first.entrySet()) {
            for (Map.Entry parameter2 : second.entrySet()) {
                if (parameter1.getKey()==parameter2.getKey() && !(parameter1.getKey().toString().equals("__class")) && !(parameter1.getKey().toString().equals("ownerId"))){
                    String tempValue = parameter1.getValue().toString();
                    first.put(parameter1.getKey().toString(), parameter2.getValue().toString());
                    second.put(parameter1.getKey().toString(), tempValue);
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

    public String getTablesID (String tableName, String whereClause){

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
            return id;
        }
        return id;
    }

    public void setSubjectsToCards(String city, String university, String faculty, String course, String group){
        subjectCards = new ArrayList<>();

        String universityWhereClause = "name = '" +university+ "' and city = '"+city+"'";
        String university_id = getTablesID("university", universityWhereClause);

        String facultyWhereClause = "name = '" + faculty + "'";
        String faculty_id = getTablesID("faculty", facultyWhereClause);

        String courseWhereClause = "id = '" + course + "'";
        String course_id = getTablesID("course", courseWhereClause);

        String groupWhereClause = "name = '" + group + "'";
        String group_id = getTablesID("group", groupWhereClause);


        String subjectWhereClause = "university_id = '" +university_id+"' and faculty_id = '"+ faculty_id+"' and group_id = '"+group_id+"' and course_id = '"+course_id+"'";
        DataQueryBuilder subjectDataQuery = DataQueryBuilder.create();
        subjectDataQuery.setWhereClause( subjectWhereClause );

        List<Map> subjects = Backendless.Persistence.of( "subject" ).find( subjectDataQuery );
        subjects=sortSubjectByTime(subjects);

        for (Map<String, String> subject : subjects) {

            Subject tempSubject = new Subject();

            for (Map.Entry entry : subject.entrySet()) {
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

            }
            subjectCards.add(new Subject(tempSubject));
        }

    }
}






