package ru.vsu.schudify;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.util.Log;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public abstract class DBHelper extends SQLiteOpenHelper {

    private static final String url = "jdbc:mysql://localhost:3306/test";
    private static final String user = "root";
    private static final String password = "root";

    List cards;

    private static final String TAG = "RoadMap4.DBHelper";

    String datascripts = "datascripts";
    String database = "database";

    Context mContext;
    int mVersion;

    public DBHelper(Context context, String name, int version) {
        super(context, name, null, version);

        mContext = context;
        mVersion = version;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        ArrayList<String> tables = getSQLTables();
        for (String table: tables){
            db.execSQL(table);
        }

        ArrayList<HashMap<String, ContentValues>> dataSQL = getSQLDatas();
       /* for (HashMap<String, ContentValues> hm: dataSQL){
            for (String table: hm.keySet()){
                Log.d(TAG, "insert into " + table + " " + hm.get(table));
                long rowId = db.insert(table, null, hm.get(table));
            }
        }*/

    }

    private ArrayList<String> getSQLTables() {
        ArrayList<String> tables = new ArrayList<>();
        ArrayList<String> files = new ArrayList<>();

        AssetManager assetManager = mContext.getAssets();
        String dir = database + mVersion;

        try {
            String[] listFiles = assetManager.list(dir);
            for (String file: listFiles){
                files.add(file);
            }

            //Collections.sort(files, new QueryFilesComparator());

            BufferedReader bufferedReader;
            String query;
            String line;

            for (String file: files){
                Log.d(TAG, "file db is " + file);
                bufferedReader = new BufferedReader(new InputStreamReader(assetManager.open(dir + "/" + file)));
                query = "";
                while ((line = bufferedReader.readLine()) != null){
                    query = query + line;
                }
                bufferedReader.close();
                tables.add(query);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return tables;
    }

    /*private class QueryFilesComparator implements Comparator<String> {

        @Override
        public int compare(String file1, String file2) {
            Integer f2 = Integer.parseInt(file1.substring(0, 2));
            Integer f1 = Integer.parseInt(file2.substring(0, 2));
            return f2.compareTo(f1);
        }
    }*/

    private ArrayList<HashMap<String, ContentValues>> getSQLDatas() {
        ArrayList<HashMap<String, ContentValues>> data = new ArrayList<>();
        ArrayList<String> files = new ArrayList<>();

        AssetManager assetManager = mContext.getAssets();
        String dir = database + mVersion;

        try {
            String[] listFiles = assetManager.list(dir);
            for (String file: listFiles){
                files.add(file);
            }

            Collections.sort(files, new QueryFilesComparator());

            BufferedReader bufferedReader;
            String line;

            int separator = 0;
            ContentValues cv = null;
            String[] fields;

            String nameTable = null;
            String packageName = mContext.getPackageName();
            boolean flag = false;

            HashMap<String, ContentValues> hm;

            for (String file: files){
                Log.d(TAG, "file db is " + file);
                bufferedReader = new BufferedReader(new InputStreamReader(assetManager.open(dir + "/" + file)));
                while ((line = bufferedReader.readLine()) != null){
                    fields = line.trim().split(":");
                    if (fields.length == 1){
                        if (flag == true){
                            hm = new HashMap<>();
                            hm.put(nameTable, cv);
                            data.add(hm);
                        }
                        // наименование таблицы
                        nameTable = line.trim();
                        cv = new ContentValues();
                        continue;
                    } else {
                        if (fields[1].equals("UUID")){
                            cv.put(fields[0], UUID.randomUUID().toString());
                        } else if (fields[1].equals("color") || fields[1].equals("string")){
                            int resId = mContext.getResources().getIdentifier(fields[2], fields[1], packageName);
                            Log.d(TAG, fields[1] + "  " + resId);
                            switch (fields[1]){
                                case "color":
                                    cv.put(fields[0], resId);
                                    break;
                                case "string":
                                    cv.put(fields[0], mContext.getString(resId));
                                    break;
                                default:
                                    break;
                            }
                        } else if (fields[1].equals("text")){
                            cv.put(fields[0], fields[2]);
                        } else if (fields[1].equals("int")){
                            cv.put(fields[0], Integer.parseInt(fields[2]));
                        }
                    }
                    flag = true;

                }
                bufferedReader.close();

            }


        } catch (IOException e) {
            e.printStackTrace();
        }


        return data;
    }




}