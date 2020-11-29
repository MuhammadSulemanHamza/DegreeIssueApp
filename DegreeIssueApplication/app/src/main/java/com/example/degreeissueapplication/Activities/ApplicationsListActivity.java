package com.example.degreeissueapplication.Activities;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.degreeissueapplication.Adapters.ApplicationsRecyclerAdapter;
import com.example.degreeissueapplication.DegreeIssuanceFormActivity;
import com.example.degreeissueapplication.MainActivity;
import com.example.degreeissueapplication.Model.DegreeIssueModel;
import com.example.degreeissueapplication.Model.UserModel;
import com.example.degreeissueapplication.R;
import com.example.degreeissueapplication.ROLE;
import com.example.degreeissueapplication.Utils.DatabaseHandler;

import java.util.ArrayList;
import java.util.List;

public class ApplicationsListActivity extends AppCompatActivity {

    private AppCompatActivity activity = ApplicationsListActivity.this;
    private AppCompatTextView textViewName;
    private RecyclerView recyclerViewApps;
    private List<DegreeIssueModel> listApps;
    private ApplicationsRecyclerAdapter appsRecyclerAdapter;
    private DatabaseHandler databaseHelper;
    private Button btn_new;
    private String role;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_applications_list);
        getSupportActionBar().setTitle("");
        initViews();
        initObjects();


    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 0){
            if(resultCode == RESULT_OK){

                DegreeIssueModel application = (DegreeIssueModel)data.getSerializableExtra(DegreeIssuanceFormActivity.RETURN);
                listApps.add(application);
                databaseHelper.insertApplication(application);
                recyclerViewApps.setAdapter(appsRecyclerAdapter);
            }
        }
    }
    /**
     * This method is to initialize views
     */
    private void initViews() {
        textViewName = (AppCompatTextView) findViewById(R.id.textViewName);
        recyclerViewApps = (RecyclerView) findViewById(R.id.recyclerViewUsers);
        btn_new = findViewById(R.id.appCompatButtonNew);

        btn_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ApplicationsListActivity.this, DegreeIssuanceFormActivity.class);

                startActivityForResult(intent, 0);
            }
        });


    }

    /**
     * This method is to initialize objects to be used
     */
    private void initObjects() {

        listApps = new ArrayList<>();
        appsRecyclerAdapter = new ApplicationsRecyclerAdapter(listApps);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewApps.setLayoutManager(mLayoutManager);
        recyclerViewApps.setItemAnimator(new DefaultItemAnimator());
        recyclerViewApps.setHasFixedSize(true);
        recyclerViewApps.setAdapter(appsRecyclerAdapter);
        databaseHelper = new DatabaseHandler(activity);
        databaseHelper.openDataBase();

        String emailFromIntent = getIntent().getStringExtra("EMAIL");
        textViewName.setText(emailFromIntent);

        //role = databaseHelper.getRole(emailFromIntent);
        //if (role !="user")
        //    btn_new.setVisibility(View.INVISIBLE);

        getDataFromSQLite();


    }

    /**
     * This method is to fetch all user records from SQLite
     */
    private void getDataFromSQLite() {
        // AsyncTask is used that SQLite operation not blocks the UI Thread.
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                listApps.clear();
                listApps.addAll(databaseHelper.getAllApplications());

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                appsRecyclerAdapter.notifyDataSetChanged();
            }
        }.execute();
    }
}