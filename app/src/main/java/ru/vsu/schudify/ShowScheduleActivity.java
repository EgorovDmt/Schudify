package ru.vsu.schudify;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.backendless.Backendless;
import com.backendless.Persistence;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ShowScheduleActivity extends AppCompatActivity implements View.OnCreateContextMenuListener  {

    private List subjectCards;
    private RecyclerView rv;
    DBManager dbManager = new DBManager();

    public static final String API_KEY = "7597C60F-985E-5836-FF1D-645282E5C800";
    public static final String SERVER_URL = "https://api.backendless.com";




    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_schedule);

        //Permission for synchronous network
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        rv=(RecyclerView)findViewById(R.id.rv);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);

        setDataToCards();
        initializeAdapter();

        dbManager.addTodb();



    }



    private void initializeAdapter(){
        RVAdapter adapter = new RVAdapter(subjectCards);
        rv.setAdapter(adapter);
    }


    public void setDataToCards(){
        subjectCards = new ArrayList<>();
        final boolean[] check = {false};


        class DataStorage {
            private Map<String, String> subject = new HashMap<String, String>();

            public Map<String, String> getTempValue()
            {
                return subject;
            }

            public void setTempValue( Map<String,String> map )
            {
                subject=map;
            }
        }

        final DataStorage dataStorage = new DataStorage();



        List<Map> result = Backendless.Persistence.of( "subject" ).find();
        Map subject = Backendless.Persistence.of( "subject" ).findFirst();
        dataStorage.setTempValue(subject);



        Subject tempSubject = new Subject();

        for (Map.Entry entry : dataStorage.getTempValue().entrySet()) {
            check[0]=true;
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

        }


        subjectCards.add(new Subject(tempSubject));
        subjectCards.add(new Subject("Программирование", "Лекция", "9:45", "11:30", "404п", "Алексеев М.А.", 1, 1, 1, 1));
        subjectCards.add(new Subject("Программирование", "Лекция", "9:45", "11:30", "404п", "Алексеев М.А.", 1, 1, 1, 1));
    }
}






