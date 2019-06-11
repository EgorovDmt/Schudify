
package ru.vsu.schudify;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.persistence.DataQueryBuilder;

import java.util.List;

public class subject {
  private String faculty_id;
  private String teacher;
  private java.util.Date updated;
  private String title;
  private java.util.Date created;
  private String timeStart;
  private String season;
  private String classroom;
  private String type;
  private String ownerId;
  private String objectId;
  private String university_id;
  private String timeEnd;
  private String group_id;
  private String id;
  private String course_id;
  private String subgroup;
  private String week_day;

  public subject(subject subject){
      this.faculty_id     =   subject.faculty_id    ;
      this.teacher        =   subject.teacher       ;
      this.updated        =   subject.updated       ;
      this.title          =   subject.title         ;
      this.created        =   subject.created       ;
      this.timeStart      =   subject.timeStart     ;
      this.season         =   subject.season        ;
      this.classroom      =   subject.classroom     ;
      this.type           =   subject.type          ;
      this.ownerId        =   subject.ownerId       ;
      this.objectId       =   subject.objectId      ;
      this.university_id  =   subject.university_id ;
      this.timeEnd        =   subject.timeEnd       ;
      this.group_id       =   subject.group_id      ;
      this.id             =   subject.id            ;
      this.course_id      =   subject.course_id     ;
      this.subgroup       =   subject.subgroup      ;
      this.week_day       =   subject.week_day      ;

  }

  public subject() {
  }




  public String getFaculty_id()
  {
    return faculty_id;
  }

  public void setFaculty_id( String faculty_id )
  {
    this.faculty_id = faculty_id;
  }

  public String getTeacher()
  {
    return teacher;
  }

  public void setTeacher( String teacher )
  {
    this.teacher = teacher;
  }

  public java.util.Date getUpdated()
  {
    return updated;
  }

  public String getTitle()
  {
    return title;
  }

  public void setTitle( String title )
  {
    this.title = title;
  }

  public java.util.Date getCreated()
  {
    return created;
  }

  public String getTimeStart()
  {
    return timeStart;
  }

  public void setTimeStart( String timeStart )
  {
    this.timeStart = timeStart;
  }

  public String getSeason()
  {
    return season;
  }

  public void setSeason( String season )
  {
    this.season = season;
  }

  public String getClassroom()
  {
    return classroom;
  }

  public void setClassroom( String classroom )
  {
    this.classroom = classroom;
  }

  public String getType()
  {
    return type;
  }

  public void setType( String type )
  {
    this.type = type;
  }

  public String getOwnerId()
  {
    return ownerId;
  }

  public String getObjectId()
  {
    return objectId;
  }

  public String getUniversity_id()
  {
    return university_id;
  }

  public void setUniversity_id( String university_id )
  {
    this.university_id = university_id;
  }

  public String getTimeEnd()
  {
    return timeEnd;
  }

  public void setTimeEnd( String timeEnd )
  {
    this.timeEnd = timeEnd;
  }

  public String getGroup_id()
  {
    return group_id;
  }

  public void setGroup_id( String group_id )
  {
    this.group_id = group_id;
  }

  public String getId()
  {
    return id;
  }

  public void setId( String id )
  {
    this.id = id;
  }

  public String getCourse_id()
  {
    return course_id;
  }

  public void setCourse_id( String course_id )
  {
    this.course_id = course_id;
  }

  public String getSubgroup()
  {
    return subgroup;
  }

  public void setSubgroup( String subgroup )
  {
    this.subgroup = subgroup;
  }

  public String getWeek_day()
  {
    return week_day;
  }

  public void setWeek_day( String week_day )
  {
    this.week_day = week_day;
  }


  public subject save()
  {
    return Backendless.Data.of( subject.class ).save( this );
  }

  public void saveAsync( AsyncCallback<subject> callback )
  {
    Backendless.Data.of( subject.class ).save( this, callback );
  }

  public Long remove()
  {
    return Backendless.Data.of( subject.class ).remove( this );
  }

  public void removeAsync( AsyncCallback<Long> callback )
  {
    Backendless.Data.of( subject.class ).remove( this, callback );
  }

  public static subject findById(String id )
  {
    return Backendless.Data.of( subject.class ).findById( id );
  }

  public static void findByIdAsync( String id, AsyncCallback<subject> callback )
  {
    Backendless.Data.of( subject.class ).findById( id, callback );
  }

  public static subject findFirst()
  {
    return Backendless.Data.of( subject.class ).findFirst();
  }

  public static void findFirstAsync( AsyncCallback<subject> callback )
  {
    Backendless.Data.of( subject.class ).findFirst( callback );
  }

  public static subject findLast()
  {
    return Backendless.Data.of( subject.class ).findLast();
  }

  public static void findLastAsync( AsyncCallback<subject> callback )
  {
    Backendless.Data.of( subject.class ).findLast( callback );
  }

  public static List<subject> find(DataQueryBuilder queryBuilder )
  {
    return Backendless.Data.of( subject.class ).find( queryBuilder );
  }

  public static void findAsync( DataQueryBuilder queryBuilder, AsyncCallback<List<subject>> callback )
  {
    Backendless.Data.of( subject.class ).find( queryBuilder, callback );
  }
}