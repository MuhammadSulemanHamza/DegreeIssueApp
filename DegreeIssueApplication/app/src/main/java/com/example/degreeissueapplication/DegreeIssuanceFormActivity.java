package com.example.degreeissueapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DegreeIssuanceFormActivity extends AppCompatActivity {

    public static final String PASS = "SUBMITTED";

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
        DegreeIssueApplicationForm form = (DegreeIssueApplicationForm) getIntent().getSerializableExtra("FORM");
        if(form != null) {
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

            degree.setText(form.degree);
            session.setText(form.session);
            rollNumber.setText(form.rollNumber);
            batch.setText(form.batch);
            department.setText(form.department);
            regNum.setText(form.regNum);
            reason.setText(form.reason);
            rev_from.setText(form.rev_from);
            rev_to.setText(form.rev_to);
            candidateName.setText(form.candidateName);
            cnic.setText(form.cnic);
            fatherName.setText(form.fatherName);
            cgpa.setText(form.cgpa);
            dob.setText(form.dob);
            institute.setText(form.institute);
            address.setText(form.address);
            contact.setText(form.contact);

        }

        // Submit Button Click Event

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DegreeIssueApplicationForm form = new DegreeIssueApplicationForm(
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
                        contact.getText().toString()
                );

                Intent resultIntent = new Intent();
                resultIntent.putExtra(PASS, form);
                setResult(Activity.RESULT_OK, resultIntent);
                sendEmail(form);
                finish();
            }
        });

        // Cancel Button Click Event

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(Activity.RESULT_CANCELED);
                finish();
            }
        });
    }
    private void sendEmail(DegreeIssueApplicationForm form)
    {
        String subject = "Degree Issue Application";
        String body= form.toString(true);

        String mailto = "mailto:muhammadsuleman964@gmail.com" +
                "?cc=" +
                "&subject=" + Uri.encode(subject) +
                "&body=" + Uri.encode(body);
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
        emailIntent.setData(Uri.parse(mailto));

        try {
            startActivity(emailIntent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(getApplicationContext(), "Error to open email app", Toast.LENGTH_SHORT).show();
        }
    }
}