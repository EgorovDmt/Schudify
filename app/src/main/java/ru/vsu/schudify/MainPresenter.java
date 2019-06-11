package ru.vsu.schudify;

import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainPresenter {

    private MainActivity view;
    private MainModel model;

    public MainPresenter( MainActivity view) {
        this.view = view;
    }

    public void attachView(MainActivity mainActivity) {
        this.view = mainActivity;
        model = new MainModel(this);
    }

    public void detachView() {
        view = null;
    }

    public boolean checkEmptyness(String parameter){
        if (parameter=="0"){
            view.goToErrorActivity();
            return false;
        }
        return true;
    }

    public boolean checkEmptyness(List<subject> parameter){
        if (parameter.size()==0){
            view.goToErrorActivity();
            return false;
        }
        return true;
    }

    public boolean setSubjectsToCards(String city, String university, String faculty, String course, String group){

        List<subject> subjectCardForRightSeason=null;
        List<subject> subjectCardForWrongSeason=null;
        String season="";
        model.setSubjectsToCards(city, university, faculty, course, group);

        return true;
    }




    public void initializeManager(List<subject> subjectCardForRightSeason, List<subject> subjectCardForWrongSeason, Reference<String> season){//calls initialize adapter for every card view field
        initializeAdapter(MainActivity.mondayNumerical,    subjectCardForRightSeason, subjectCardForWrongSeason, season,  1 );
        initializeAdapter(MainActivity.tuesdayNumerical,   subjectCardForRightSeason, subjectCardForWrongSeason, season,  2);
        initializeAdapter(MainActivity.wednesdayNumerical, subjectCardForRightSeason, subjectCardForWrongSeason, season,  3);
        initializeAdapter(MainActivity.thursdayNumerical,  subjectCardForRightSeason, subjectCardForWrongSeason, season,  4);
        initializeAdapter(MainActivity.fridayNumerical,    subjectCardForRightSeason, subjectCardForWrongSeason, season,  5);
        initializeAdapter(MainActivity.saturdayNumerical,  subjectCardForRightSeason, subjectCardForWrongSeason, season,  6);
        initializeAdapter(MainActivity.sundayNumerical,    subjectCardForRightSeason, subjectCardForWrongSeason, season,  7);
        initializeAdapter(MainActivity.rv8,                subjectCardForRightSeason, subjectCardForWrongSeason, season,  8 );
        initializeAdapter(MainActivity.rv9,                subjectCardForRightSeason, subjectCardForWrongSeason, season,  9);
        initializeAdapter(MainActivity.rv10,               subjectCardForRightSeason, subjectCardForWrongSeason, season,  10);
        initializeAdapter(MainActivity.rv11,               subjectCardForRightSeason, subjectCardForWrongSeason, season,  11);
        initializeAdapter(MainActivity.rv12,               subjectCardForRightSeason, subjectCardForWrongSeason, season,  12);
        initializeAdapter(MainActivity.rv13,               subjectCardForRightSeason, subjectCardForWrongSeason, season,  13);
        initializeAdapter(MainActivity.rv14,               subjectCardForRightSeason, subjectCardForWrongSeason, season,  14);

    }

    public void initializeAdapter(RecyclerView rv, List<subject> subjectCardForRightSeason, List<subject> subjectCardForWrongSeason, Reference<String> season, int i){

        List<subject> subjectCard=new ArrayList<>();

        if (i>7){
            subjectCard = model.setSubjectCardForDay(i-7, subjectCardForWrongSeason);
        }else {
            subjectCard = model.setSubjectCardForDay(i, subjectCardForRightSeason);
        }

        RVAdapter adapter = new RVAdapter(subjectCard, season);
        if (subjectCard.isEmpty()){
            view.setNoLessonByRVNumber(i);
        }
        rv.setAdapter(adapter);
    }//gives information of cards to card adapter

}