package com.example.degreeissueapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.degreeissueapplication.Model.DegreeIssueModel;
import com.example.degreeissueapplication.Utils.DatabaseHandler;

public class DegreeIssuanceFormActivity extends AppCompatActivity {

    public static final String RETURN = "RETURN";

    // Declaring variables

    TextView degree;
    TextView session;
    TextView rollNumber;
    TextView batch;
    TextView department;
    TextView regNum;
    TextView reason;
    TextView rev_from;
    TextView rev_to;
    TextView candidateName;
    TextView cnic;
    TextView fatherName;
    TextView cgpa;
    TextView dob;
    TextView institute;
    TextView address;
    TextView contact;

    Button btn_submit;
    Button btn_cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_degree_issuance_form);

        // TextViews
        degree = findViewById(R.id.degree);
        session = findViewById(R.id.session);
        rollNumber = findViewById(R.id.rollNumber);
        batch = findViewById(R.id.batch);
        department = findViewById(R.id.department);
        regNum = findViewById(R.id.regNum);
        reason = findViewById(R.id.reason);
        rev_from = findViewById(R.id.rev_from);
        rev_to = findViewById(R.id.rev_to);
        candidateName = findViewById(R.id.candidateName);
        cnic = findViewById(R.id.cnic);
        fatherName = findViewById(R.id.fatherName);
        cgpa = findViewById(R.id.cgpa);
        dob = findViewById(R.id.dob);
        institute = findViewById(R.id.institute);
        address = findViewById(R.id.address);
        contact = findViewById(R.id.contact);

        // Buttons
        btn_submit = findViewById(R.id.btn_submit);
        btn_cancel = findViewById(R.id.btn_cancel);

        ////////////////////////////////////////////////////////////////////////////////////////////

        // Get value from Intent
        DegreeIssueModel application = (DegreeIssueModel) getIntent().getSerializableExtra("FORM");
        if(application != null) {
             degree.setEnabled(false);
             session.setEnabled(false);
             rollNumber.setEnabled(false);
             batch.setEnabled(false);
             department.setEnabled(false);
             regNum.setEnabled(false);
             reason.setEnabled(false);
             rev_from.setEnabled(false);
             rev_to.setEnabled(false);
             candidateName.setEnabled(false);
             cnic.setEnabled(false);
             fatherName.setEnabled(false);
             cgpa.setEnabled(false);
             dob.setEnabled(false);
             institute.setEnabled(false);
             address.setEnabled(false);
             contact.setEnabled(false);
             btn_submit.setEnabled(false);

            degree.setText(application.getDegree());
            session.setText(application.getSession());
            rollNumber.setText(application.getRollNumber());
            batch.setText(application.getBatch());
            department.setText(application.getDepartment());
            regNum.setText(application.getRegNum());
            reason.setText(application.getReason());
            rev_from.setText(application.getRev_from());
            rev_to.setText(application.getRev_to());
            candidateName.setText(application.getCandidateName());
            cnic.setText(application.getCnic());
            fatherName.setText(application.getFatherName());
            cgpa.setText(application.getCgpa());
            dob.setText(application.getDob());
            institute.setText(application.getInstitute());
            address.setText(application.getAddress());
            contact.setText(application.getContact());

        }

        // Submit Button Click Event

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {

                DegreeIssueModel application = new DegreeIssueModel(
                        degree.getText().toString(),
                        session.getText().toString(),
                        rollNumber.getText().toString(),
                        batch.getText().toString(),
                        department.getText().toString(),
                        regNum.getText().toString(),
                        reason.getText().toString(),
                        rev_from.getText().toString(),
                        rev_to.getText().toString(),
                        candidateName.getText().toString(),
                        cnic.getText().toString(),
                        fatherName.getText().toString(),
                        cgpa.getText().toString(),
                        dob.getText().toString(),
                        institute.getText().toString(),
                        address.getText().toString(),
                        contact.getText().toString(),
                        "Pending",
                        "Not yet added",
                        "Not yet added"
                );

                Intent resultIntent = new Intent();
                resultIntent.putExtra(RETURN, application);
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            }
        });

        // Cancel Button Click Event

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}