package ru.vsu.schudify;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

import com.backendless.Backendless;

public class ErrorActivity extends AppCompatActivity implements View.OnCreateContextMenuListener {

    private Toolbar toolbar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_error);

        Button searchButton = (Button) findViewById(R.id.search);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        searchButton.setOnClickListener(new OnClickListener()  {
            @Override
            public void onClick(View view) {

                    Intent intent = new Intent(ErrorActivity.this, ChooseActivity.class);
                    intent.putExtra("reIntent", "1");
                    startActivity(intent);

            }
        });
    }
}




