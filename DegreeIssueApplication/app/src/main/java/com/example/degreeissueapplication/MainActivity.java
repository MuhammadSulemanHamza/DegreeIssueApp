package com.example.degreeissueapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.MissingFormatArgumentException;

public class MainActivity extends AppCompatActivity {

    //public static final int REQ_CODE = 0123;

    // Declaring variables
    ListView listView;
    Button btn_new;
    List forms;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initializing Variables
        // Finding Views by Ids
        listView = findViewById(R.id.listView);
        btn_new = findViewById(R.id.btn_new);

        forms = new ArrayList<DegreeIssueApplicationForm>();

        // Adapter
        adapter = new ArrayAdapter<DegreeIssueApplicationForm>(
                this,
                android.R.layout.simple_expandable_list_item_1,
                forms
        );

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                DegreeIssueApplicationForm selectedItem = (DegreeIssueApplicationForm) parent.getItemAtPosition(position);

                Intent intent = new Intent(MainActivity.this, DegreeIssuanceFormActivity.class);

                intent.putExtra("FORM", selectedItem);
                intent.putExtra("ENABLED", false);

                startActivityForResult(intent, 0);

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 0){
            if(resultCode == RESULT_OK){
                DegreeIssueApplicationForm form = (DegreeIssueApplicationForm)data.getSerializableExtra("SUBMITTED");
                forms.add(form);

                // Setting adapter for listView
                listView.setAdapter(adapter);
            }
        }
    }
}