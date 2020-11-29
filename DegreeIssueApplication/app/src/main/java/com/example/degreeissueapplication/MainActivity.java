package com.example.degreeissueapplication;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.degreeissueapplication.Model.DegreeIssueModel;
import com.example.degreeissueapplication.Utils.DatabaseHandler;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.MissingFormatArgumentException;

public class MainActivity extends AppCompatActivity {

    //public static final int REQ_CODE = 0123;

    // Declaring variables
    DatabaseHandler db;
    ListView listView;
    Button btn_new;
    List applications;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initializing Variables
        // Finding Views by Ids
        listView = findViewById(R.id.listView);
        btn_new = findViewById(R.id.btn_new);

        applications = new ArrayList<DegreeIssueModel>();

        db = new DatabaseHandler(this);
        db.openDataBase();
        applications = db.getAllApplications();

        // Adapter
        adapter = new ArrayAdapter<DegreeIssueModel>(
                this,
                android.R.layout.simple_expandable_list_item_1,
                applications
        );

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                DegreeIssueModel selectedItem = (DegreeIssueModel) parent.getItemAtPosition(position);

                Intent intent = new Intent(MainActivity.this, DegreeIssuanceFormActivity.class);

                intent.putExtra("FORM", selectedItem);

                startActivity(intent);

            }
        });

        btn_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, DegreeIssuanceFormActivity.class);

                startActivityForResult(intent, 0);

            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 0){
            if(resultCode == RESULT_OK){

                DegreeIssueModel application = (DegreeIssueModel)data.getSerializableExtra(DegreeIssuanceFormActivity.RETURN);
                applications.add(application);
                db.insertApplication(application);
                listView.setAdapter(adapter);
            }
        }
    }
}