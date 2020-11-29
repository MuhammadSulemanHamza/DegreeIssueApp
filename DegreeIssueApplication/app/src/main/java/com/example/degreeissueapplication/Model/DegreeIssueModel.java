package com.example.degreeissueapplication.Model;

import java.io.Serializable;

public class DegreeIssueModel implements Serializable {

    private int id;
    private String degree;
    private String session;
    private String rollNumber;
    private String batch;
    private String department;
    private String regNum;
    private String reason;
    private String rev_from;
    private String rev_to;
    private String candidateName;
    private String cnic;
    private String fatherName;
    private String cgpa;
    private String dob;
    private String institute;
    private String address;
    private String contact;
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCoordinator_remarks() {
        return coordinator_remarks;
    }

    public void setCoordinator_remarks(String coordinator_remarks) {
        this.coordinator_remarks = coordinator_remarks;
    }

    public String getHod_remarks() {
        return hod_remarks;
    }

    public void setHod_remarks(String hod_remarks) {
        this.hod_remarks = hod_remarks;
    }

    private String coordinator_remarks;
    private String hod_remarks;

    public DegreeIssueModel(String degree, String session, String rollNumber, String batch, String department, String regNum, String reason, String rev_from, String rev_to, String candidateName, String cnic, String fatherName, String cgpa, String dob, String institute, String address, String contact, String status, String coordinator_remarks, String hod_remarks) {
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
        this.status = status;
        this.coordinator_remarks = coordinator_remarks;
        this.hod_remarks = hod_remarks;
    }

    public DegreeIssueModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public String getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(String rollNumber) {
        this.rollNumber = rollNumber;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getRegNum() {
        return regNum;
    }

    public void setRegNum(String regNum) {
        this.regNum = regNum;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getRev_from() {
        return rev_from;
    }

    public void setRev_from(String rev_from) {
        this.rev_from = rev_from;
    }

    public String getRev_to() {
        return rev_to;
    }

    public void setRev_to(String rev_to) {
        this.rev_to = rev_to;
    }

    public String getCandidateName() {
        return candidateName;
    }

    public void setCandidateName(String candidateName) {
        this.candidateName = candidateName;
    }

    public String getCnic() {
        return cnic;
    }

    public void setCnic(String cnic) {
        this.cnic = cnic;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getCgpa() {
        return cgpa;
    }

    public void setCgpa(String cgpa) {
        this.cgpa = cgpa;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getInstitute() {
        return institute;
    }

    public void setInstitute(String institute) {
        this.institute = institute;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    @Override
    public String toString() {
        return degree + " - " +
                batch + " - " +
                department + " - "+
                institute;
    }

}
