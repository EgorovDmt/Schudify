package ru.vsu.schudify;

import com.backendless.Backendless;
import com.backendless.persistence.DataQueryBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class MainModel {

    private MainPresenter presenter;

    public MainModel(MainPresenter presenter){
        this.presenter = presenter;
    }

    ArrayList<ru.vsu.schudify.subject> subjectCardForWrongSeason=null;

    ArrayList<ru.vsu.schudify.subject> subjectCardForRightSeason = null;

    public boolean setSubjectsToCards(String city, String university, String faculty, String course, String group){
        List<subject> subjectCards = new ArrayList<subject>();

        boolean checker = true;
        Reference<String> season = new Reference<>("");

        String universityWhereClause = "name = '" +university+ "' and city = '"+city+"'";
        String university_id = getTableParameter("university", universityWhereClause, false, season);
        checker = presenter.checkEmptyness(university_id);

        if (!checker) {return false;}

        String facultyWhereClause = "name = '" + faculty + "'";
        String faculty_id = getTableParameter("faculty", facultyWhereClause, true, season);
        checker = presenter.checkEmptyness(faculty_id);

        if (!checker) {return false;}

        String courseWhereClause = "id = '" + course + "'";
        String course_id = getTableParameter("course", courseWhereClause, false, season);
        checker = presenter.checkEmptyness(course_id);

        if (!checker) {return false;}

        String groupWhereClause = "name = '" + group + "'";
        String group_id = getTableParameter("group", groupWhereClause, false, season);
        checker = presenter.checkEmptyness(group_id);

        if (!checker) {return false;}

        String subjectWhereClause = "university_id = '" +university_id+"' and faculty_id = '"+ faculty_id+"' and group_id = '"+group_id+"' and course_id = '"+course_id+"'";
        DataQueryBuilder subjectDataQuery = DataQueryBuilder.create();
        subjectDataQuery.setWhereClause( subjectWhereClause );

        List<subject> subject = Backendless.Data.of(ru.vsu.schudify.subject.class).find( subjectDataQuery );

        checker = (boolean) presenter.checkEmptyness(subject);

        if (!checker) {return false;}

        subject=sortSubjectByTime(subject);



        for (int  i= 0; i<subject.size(); i++) {
            ru.vsu.schudify.subject tempSubject= subject.get(i);
            if ((subject.get(i).getSubgroup())==null){
                tempSubject.setSubgroup("");
            }
            subjectCards.add(new subject(tempSubject));
        }

        setSubjectCardForSeason( season, subjectCards );

        presenter.initializeManager(subjectCardForRightSeason, subjectCardForWrongSeason, season);

        return true;

    }

    public String getTableParameter(String tableName, String whereClause, boolean trueSeason, Reference<String> season){

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
                season.set(tempTable.get("season").toString());
            }
            return id;
        }
        return id;
    }//returns id of table if it's not empty

    public List<subject> sortSubjectByTime(List<subject> subjects){
        for (int i=0; i<subjects.size()-1; i++) {
            subject firstSubject = subjects.get(i);

            for (int j=i+1; j<subjects.size(); j++){

                subject secondSubject = subjects.get(j);
                String firstValue = (String) firstSubject.getTimeStart();
                String secondValue = (String) secondSubject.getTimeStart();
                char firstSignOfFirst = firstValue.charAt(0);
                char secondSignOfFirst = firstValue.charAt(1);
                char firstSignOfSecond = secondValue.charAt(0);
                char secondSignOfSecond = secondValue.charAt(1);

                if (firstSignOfFirst>firstSignOfSecond && secondValue.length()<5){

                    subject tempSubject = new subject(subjects.get(i));
                    subjects.set(i,subjects.get(j));
                    subjects.set(j,tempSubject);
                }

                else if (firstSignOfFirst==firstSignOfSecond){
                    if (secondSignOfFirst>secondSignOfSecond){
                        subject tempSubject = new subject(subjects.get(i));
                        subjects.set(i,subjects.get(j));
                        subjects.set(j,tempSubject);
                    }}
            }
        }


        return subjects;
    }//sorts list of subject maps by time

    public List<subject> setSubjectCardForDay(int day, List<subject> subjectCardForDays){

        String stringDay = Integer.toString(day);

        ArrayList<subject> subjectCards = new ArrayList<subject>(subjectCardForDays);

        for (ru.vsu.schudify.subject subject:subjectCardForDays) {
            if (!subject.getWeek_day().equals(stringDay)){
                subjectCards.remove(subject);
            }
        }

        return subjectCards;

    }//removes wrong subjects from list by day

    public void setSubjectCardForSeason(Reference<String> season, List<subject> subjectCards){

        subjectCardForWrongSeason = new ArrayList<subject>(subjectCards);
        subjectCardForRightSeason = new ArrayList<subject>(subjectCards);

        for (ru.vsu.schudify.subject subject:subjectCards) {
            String tempSeason = subject.getSeason();
            if (!(tempSeason.equals(season.get()))){
                subjectCardForRightSeason.remove(subject);
            }
            else {
                subjectCardForWrongSeason.remove(subject);
            }
        }

    }//removes wrong subjects from list by season
}
