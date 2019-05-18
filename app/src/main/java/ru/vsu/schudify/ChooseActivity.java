package ru.vsu.schudify;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

import com.backendless.Backendless;

public class ChooseActivity extends AppCompatActivity implements View.OnCreateContextMenuListener  {

    public static final String APPLICATION_ID = "F15C1675-EABE-7BAF-FFBF-2687A86AD400";
    public static final String API_KEY = "7597C60F-985E-5836-FF1D-645282E5C800";
    public static final String SERVER_URL = "https://api.backendless.com";

    String university;
    String faculty ;
    String course;
    String group;
    String city ;

    String[] languages = { "C","C++","Java","C#","PHP","JavaScript","jQuery","AJAX","JSON" };

    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);

        Backendless.initApp(this, APPLICATION_ID, API_KEY);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button searchScheduleButton = (Button) findViewById(R.id.search_schedule);

        SharedPreferences userDetails = getApplicationContext().getSharedPreferences("data", MODE_PRIVATE);
        final SharedPreferences.Editor data = userDetails.edit();
        String reIntent = getIntent().getStringExtra("reIntent");
        String checker = userDetails.getString("university", "");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.select_dialog_singlechoice, languages);
        //Find TextView control
        AutoCompleteTextView acTextView = (AutoCompleteTextView) findViewById(R.id.city);
        //Set the number of characters the user must type before the drop down list is shown
        acTextView.setThreshold(1);
        //Set the adapter
        acTextView.setAdapter(adapter);

        if (reIntent!=null) {
            checker = "";
            TextView welcome = (TextView) findViewById(R.id.introduce);
            welcome.setText("");
            TextView instruction = (TextView) findViewById(R.id.instruction1);
            instruction.setText("Введите информацию,\nчтобы мы смогли найти\nрасписание");
            instruction.setTextSize(26);
            instruction.setTop(30);

            RelativeLayout.LayoutParams params =
                    new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                            RelativeLayout.LayoutParams.WRAP_CONTENT);
            params.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
            params.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
            instruction.setLayoutParams(params);

        }

        checker="";


        if (!checker.equals("")){
            Intent intent = new Intent(ChooseActivity.this, MainActivity.class);
            String tempUniversity = userDetails.getString("university", "");
            String tempCity = userDetails.getString("city", "");
            String tempFaculty = userDetails.getString("faculty", "");
            String tempCourse = userDetails.getString("course", "");
            String tempGroup = userDetails.getString("group", "");

            intent.putExtra("university", tempUniversity);
            intent.putExtra("city", tempCity);
            intent.putExtra("faculty", tempFaculty);
            intent.putExtra("course", tempCourse);
            intent.putExtra("group", tempGroup);

            startActivity(intent);
        }

        searchScheduleButton.setOnClickListener(new OnClickListener()  {
            @Override
            public void onClick(View view) {
                if (!checkData().isEmpty()) {
                    Intent intent = new Intent(ChooseActivity.this, MainActivity.class);
                    intent.putExtra("university", university);
                    intent.putExtra("city", city);
                    intent.putExtra("faculty", faculty);
                    intent.putExtra("course", course);
                    intent.putExtra("group", group);

                    data.putString("university", university);
                    data.putString("city", city);
                    data.putString("faculty", faculty);
                    data.putString("course", course);
                    data.putString("group", group);
                    data.commit();

                    startActivity(intent);
                }
            }
        });
    }

    public List<String> checkData() {

        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "Заполните поля", Snackbar.LENGTH_LONG);

        EditText universityView = (EditText) findViewById(R.id.university);
        university = universityView.getText().toString();
        EditText facultyView = (EditText) findViewById(R.id.faculty);
        faculty = facultyView.getText().toString();
        EditText courseView = (EditText) findViewById(R.id.course);
        course = courseView.getText().toString();
        EditText groupView = (EditText) findViewById(R.id.group);
        group = groupView.getText().toString();
        EditText cityView = (EditText) findViewById(R.id.city);
        city = cityView.getText().toString();

        if (university.isEmpty()) snackbar.show();
        if (city.isEmpty()) snackbar.show();
        if (faculty.isEmpty()) snackbar.show();
        if (course.isEmpty()) snackbar.show();
        if (group.isEmpty()) snackbar.show();

        List<String> information = new ArrayList<>();

        if (!university.isEmpty() && !faculty.isEmpty() && !course.isEmpty() && !group.isEmpty() && !city.isEmpty())
        {
            information.add(university);
            information.add(faculty);
            information.add(course);
            information.add(group);
            information.add(city);

            return information;
        }
        return information;
    }
}





