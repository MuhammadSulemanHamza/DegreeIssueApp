package com.example.degreeissueapplication;

import java.io.Serializable;

public class DegreeIssueApplicationForm implements Serializable {
    String degree;
    String session;
    String rollNumber;
    String batch;
    String department;
    String regNum;
    String reason;
    String rev_from;
    String rev_to;
    String candidateName;
    String cnic;
    String fatherName;
    String cgpa;
    String dob;
    String institute;
    String address;
    String contact;


    public DegreeIssueApplicationForm(String degree, String session, String rollNumber, String batch, String department, String regNum, String reason, String rev_from, String rev_to, String candidateName, String cnic, String fatherName, String cgpa, String dob, String institute, String address, String contact) {
        this.degree = degree;
        this.session = session;
        this.rollNumber = rollNumber;
        this.batch = batch;
        this.department = department;
        this.regNum = regNum;
        this.reason = reason;
        this.rev_from = rev_from;
        this.rev_to = rev_to;
        this.candidateName = candidateName;
        this.cnic = cnic;
        this.fatherName = fatherName;
        this.cgpa = cgpa;
        this.dob = dob;
        this.institute = institute;
        this.address = address;
        this.contact = contact;
    }

    @Override
    public String toString() {
        return degree + " - " +
                batch + " - " +
                department + " - "+
                institute;
    }

    public String toString(boolean dummyValue) {
        return "Degree: " + degree + "\n" +
                "Session: " + session + "\n" +
                "Roll Number: " + rollNumber + "\n" +
                "Batch: " + batch + "\n" +
                "Department: " + department + "\n" +
                "Registration Number: " + regNum + "\n" +
                "Reason: " + reason + "\n" +
                "From: " + rev_from + "\n" +
                "To: " + rev_to + "\n" +
                "CandidateName: " + candidateName + "\n" +
                "CNIC: " + cnic + "\n" +
                "FatherName: " + fatherName + "\n" +
                "CGPA: " + cgpa + "\n" +
                "DOB: " + dob + "\n" +
                "Institute: " + institute + "\n" +
                "Address: " + address + "\n" +
                "Contact: " + contact + "\n";
    }
}
