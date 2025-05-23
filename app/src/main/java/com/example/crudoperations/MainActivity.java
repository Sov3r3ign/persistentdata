package com.example.crudoperations;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText txtStudentName, txtStudentCourse, txtCourseFee;
    Button btnAddStudentInfo, btnViewStudentList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtStudentName = findViewById(R.id.sName);
        txtStudentCourse = findViewById(R.id.course);
        txtCourseFee = findViewById(R.id.fee);

        btnAddStudentInfo = findViewById(R.id.addStudentInfo);
        btnViewStudentList = findViewById(R.id.viewStudentList);

        btnAddStudentInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insert();

            }
        });

        public void insert()
        {
            try
            {
                String sName = txtStudentName.getText().toString();
                String course = txtStudentCourse.getText().toString();
                String fee = txtCourseFee.getText().toString();

                SQLiteDatabase db = openOrCreateDatabase("studentRegistrationDb", Context.MODE_PRIVATE, null);
                db.execSQL("CREATE TABLE IF NOT EXISTS records(id INTEGER PRIMARY KEY AUTOINCREMENT, sName VARCHAR, course VARCHAR, fee VARCHAR)");

                String sql = "insert into records (sName, course, fee) values(?,?,?)";
                SQLiteStatement statement = db.compileStatement(sql);
                statement.bindString(1, sName);
                statement.bindString(1, course);
                statement.bindString(1, fee);

                statement.execute();

                Toast.makeText(this, "Student Record Added", Toast.LENGTH_LONG).show();

            }
            catch (Exception ex)
            {
                Toast.makeText(this, "Student Record Adding Failed",Toast.LENGTH_LONG).show();
            }
        }
        btnViewStudentList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}