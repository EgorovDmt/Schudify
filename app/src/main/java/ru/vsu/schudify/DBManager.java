package ru.vsu.schudify;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class DBManager extends Activity {

    final String LOG_TAG = "myLogs";

    DBHelper dbHelper;
    ContentValues cv = new ContentValues();

    SQLiteDatabase db;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dbHelper = new DBHelper(this);
        db = ConnectToDB();
    }

    public SQLiteDatabase ConnectToDB() {
        db = dbHelper.getWritableDatabase();
        return db;
    }

    public void InsertToDB(String tableName) {
        Log.d(LOG_TAG, "--- Insert in mytable: ---");

        while (true) {

            cv.put("title", "Программирование");
            cv.put("type", "лекция");
            cv.put("timeStart", "9:45");
            cv.put("timeEnd", "11:30");
            cv.put("classroom", "404");
            cv.put("teacher", "Иванов И.И.");
            cv.put("universityId", "1");
            cv.put("weekDay", "1");
            cv.put("facultyId", "1");
            cv.put("groupId", "1");

            // вставляем запись и получаем ее ID
            long rowID = db.insert(tableName, null, cv);
            Log.d(LOG_TAG, "row inserted, ID = " + rowID);
        }
    }

    public void GetFromDB(String tableName){


        Log.d(LOG_TAG, "--- Rows in mytable: ---");
        // делаем запрос всех данных из таблицы, получаем Cursor
        Cursor c = db.query(tableName, null, null, null, null, null, null);

        // ставим позицию курсора на первую строку выборки
        // если в выборке нет строк, вернется false
        if (c.moveToFirst()) {

            // определяем номера столбцов по имени в выборке
            int idColIndex = c.getColumnIndex("id");
            int nameColIndex = c.getColumnIndex("name");
            int emailColIndex = c.getColumnIndex("email");

            do {
                // получаем значения по номерам столбцов и пишем все в лог
                Log.d(LOG_TAG,
                        "ID = " + c.getInt(idColIndex) +
                                ", name = " + c.getString(nameColIndex) +
                                ", email = " + c.getString(emailColIndex));
                // переход на следующую строку
                // а если следующей нет (текущая - последняя), то false - выходим из цикла
            } while (c.moveToNext());
        } else
            Log.d(LOG_TAG, "0 rows");
        c.close();

    }

    public void DeleteFromDB(){
        Log.d(LOG_TAG, "--- Clear mytable: ---");
        // удаляем все записи
        int clearCount = db.delete("mytable", null, null);
        Log.d(LOG_TAG, "deleted rows count = " + clearCount);





        dbHelper.close();
    }

}
