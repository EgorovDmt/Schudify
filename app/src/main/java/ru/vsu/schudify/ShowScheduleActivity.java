package ru.vsu.schudify;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;


public class ShowScheduleActivity extends AppCompatActivity implements View.OnCreateContextMenuListener  {

    private List subjectCards;
    private RecyclerView rv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_schedule);

        rv=(RecyclerView)findViewById(R.id.rv);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);

        setDataToCards();
        initializeAdapter();
    }



    private void initializeAdapter(){
        RVAdapter adapter = new RVAdapter(subjectCards);
        rv.setAdapter(adapter);
    }

    void showSnackbar(int position) {
        Snackbar.make(rv, "Карточка с номером "+position, Snackbar.LENGTH_LONG).show();
    }

    public void setDataToCards(){
        subjectCards = new ArrayList<>();
        subjectCards.add(new SubjectCard("Программирование", "Лекция", "9:45", "11:30", "404п", "Скоробогатова М.А."));
        subjectCards.add(new SubjectCard("Программирование", "Лекция", "9:45", "11:30", "404п", "Скоробогатова М.А."));
        subjectCards.add(new SubjectCard("Программирование", "Лекция", "9:45", "11:30", "404п", "Скоробогатова М.А."));
        subjectCards.add(new SubjectCard("Программирование", "Лекция", "9:45", "11:30", "404п", "Скоробогатова М.А."));
        subjectCards.add(new SubjectCard("Программирование", "Лекция", "9:45", "11:30", "404п", "Скоробогатова М.А."));
        subjectCards.add(new SubjectCard("Программирование", "Лекция", "9:45", "11:30", "404п", "Скоробогатова М.А."));
        subjectCards.add(new SubjectCard("Программирование", "Лекция", "9:45", "11:30", "404п", "Скоробогатова М.А."));

    }
}






