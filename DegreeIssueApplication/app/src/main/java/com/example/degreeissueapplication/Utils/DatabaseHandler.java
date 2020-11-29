package com.example.degreeissueapplication.Utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.degreeissueapplication.Model.DegreeIssueModel;
import com.example.degreeissueapplication.Model.UserModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DB_NAME = "DegreeIssueDatabase";
    private static final String APPLICATION_TABLE = "application";
    private static final String ID = "id";
    private static final String DEGREE = "degree";
    private static final String SESSION = "session";
    private static final String ROLL_NUMBER = "rollNumber";
    private static final String BATCH = "batch";
    private static final String DEPARTMENT = "department";
    private static final String REGISTRATION_NUMBER = "registrationNum";
    private static final String REASON = "reason";
    private static final String REV_FROM = "rev_from";
    private static final String REV_TO = "rev_to";
    private static final String CANDIDATE_NAME = "candidateName";
    private static final String CNIC = "cnic";
    private static final String FATHER_NAME = "fatherName";
    private static final String CGPA = "cgpa";
    private static final String DOB = "dob";
    private static final String INSTITUTE = "institute";
    private static final String ADDRESS = "address";
    private static final String CONTACT = "contact";
    private static final String STATUS = "status";
    private static final String COORDINATOR_REMARKS = "coRemarks";
    private static final String HOD_REMARKS = "hodRemarkds";
    private static final String CREATE_APPLICATION_TABLE = "CREATE TABLE IF NOT EXISTS " + APPLICATION_TABLE +
            "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
            DEGREE + " TEXT, " +
            SESSION + " TEXT, " +
            ROLL_NUMBER + " TEXT, " +
            BATCH + " TEXT, " +
            DEPARTMENT + " TEXT, " +
            REGISTRATION_NUMBER + " TEXT, " +
            REASON + " TEXT, " +
            REV_FROM + " TEXT, " +
            REV_TO + " TEXT, " +
            CANDIDATE_NAME + " TEXT, " +
            CNIC + " TEXT, " +
            FATHER_NAME + " TEXT, " +
            CGPA + " TEXT, " +
            DOB + " TEXT, " +
            INSTITUTE + " TEXT, " +
            ADDRESS + " TEXT, " +
            CONTACT + " TEXT, " +
            STATUS + " TEXT, " +
            COORDINATOR_REMARKS + " TEXT, " +
            HOD_REMARKS + " TEXT)";
////////////////////////////////////////////////////////////////////////////////////////////////////
    // User table name
    private static final String TABLE_USER = "user";
    // User Table Columns names
    private static final String COLUMN_USER_ID = "user_id";
    private static final String COLUMN_USER_NAME = "user_name";
    private static final String COLUMN_USER_EMAIL = "user_email";
    private static final String COLUMN_USER_PASSWORD = "user_password";
    private static final String COLUMN_USER_ROLE = "user_role";

    // create table sql query
    private String CREATE_USER_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_USER + "("
            + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_USER_NAME + " TEXT,"
            + COLUMN_USER_EMAIL + " TEXT," + COLUMN_USER_PASSWORD + " TEXT," + COLUMN_USER_ROLE + " TEXT" + ")";
    // drop table sql query
    private String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_USER;

    ///////////////////////////////////////////////////////////////////////////////////////////////



    private SQLiteDatabase db;

    public DatabaseHandler(Context context) {
        super(context, DB_NAME, null, VERSION);
        openDataBase();
        onCreate(db);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_APPLICATION_TABLE);
        db.execSQL(CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table
        db.execSQL("DROP TABLE IF EXISTS " + APPLICATION_TABLE);
        db.execSQL(DROP_USER_TABLE);

        //Create tables again
        onCreate(db);
    }

    public void openDataBase(){
        db = this.getWritableDatabase();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void insertApplication(DegreeIssueModel application) {
        ContentValues cv = new ContentValues();
        cv.put(DEGREE, application.getDegree());
        cv.put(SESSION, application.getSession());
        cv.put(ROLL_NUMBER, application.getRollNumber());
        cv.put(BATCH , application.getBatch());
        cv.put(DEPARTMENT, application.getDepartment());
        cv.put(REGISTRATION_NUMBER, application.getRegNum());
        cv.put(REASON , application.getReason());
        cv.put(REV_FROM, application.getRev_from());
        cv.put(REV_TO , application.getRev_to());
        cv.put(CANDIDATE_NAME, application.getCandidateName());
        cv.put(CNIC, application.getCnic());
        cv.put(FATHER_NAME, application.getFatherName());
        cv.put(CGPA, application.getCgpa());
        cv.put(DOB, application.getDob());
        cv.put(INSTITUTE, application.getInstitute());
        cv.put(ADDRESS, application.getAddress());
        cv.put(CONTACT, application.getContact());
        cv.put(STATUS, application.getStatus());
        cv.put(COORDINATOR_REMARKS, application.getCoordinator_remarks());
        cv.put(HOD_REMARKS, application.getHod_remarks());
        db.insert(APPLICATION_TABLE, null, cv);
    }

    /**
     * This method is to create user record
     *
     * @param user
     */
    public void addUser(UserModel user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_NAME, user.getName());
        values.put(COLUMN_USER_EMAIL, user.getEmail());
        values.put(COLUMN_USER_PASSWORD, user.getPassword());
        values.put(COLUMN_USER_ROLE, user.getRole());
        // Inserting Row
        db.insert(TABLE_USER, null, values);
        db.close();
    }

    public List<DegreeIssueModel> getAllApplications(){
        List<DegreeIssueModel> appList = new ArrayList<>();
        Cursor cur = null;

        db.beginTransaction();

        try {
            cur = db.query(APPLICATION_TABLE, null, null, null,
                    null, null, null, null);
            if (cur != null) {
                if (cur.moveToFirst()) {
                    do {
                        DegreeIssueModel applications = new DegreeIssueModel();
                        applications.setId(cur.getInt(cur.getColumnIndex(ID)));
                        applications.setDegree(cur.getString(cur.getColumnIndex(DEGREE)));
                        applications.setSession(cur.getString(cur.getColumnIndex(SESSION)));
                        applications.setRollNumber(cur.getString(cur.getColumnIndex(ROLL_NUMBER)));
                        applications.setBatch(cur.getString(cur.getColumnIndex(BATCH)));
                        applications.setDepartment(cur.getString(cur.getColumnIndex(DEPARTMENT)));
                        applications.setRegNum(cur.getString(cur.getColumnIndex(REGISTRATION_NUMBER)));
                        applications.setReason(cur.getString(cur.getColumnIndex(REASON)));
                        applications.setRev_from(cur.getString(cur.getColumnIndex(REV_FROM)));
                        applications.setRev_to(cur.getString(cur.getColumnIndex(REV_TO)));
                        applications.setCandidateName(cur.getString(cur.getColumnIndex(CANDIDATE_NAME)));
                        applications.setCnic(cur.getString(cur.getColumnIndex(CNIC)));
                        applications.setFatherName(cur.getString(cur.getColumnIndex(FATHER_NAME)));
                        applications.setCgpa(cur.getString(cur.getColumnIndex(CGPA)));
                        applications.setDob(cur.getString(cur.getColumnIndex(DOB)));
                        applications.setInstitute(cur.getString(cur.getColumnIndex(INSTITUTE)));
                        applications.setAddress(cur.getString(cur.getColumnIndex(ADDRESS)));
                        applications.setContact(cur.getString(cur.getColumnIndex(CONTACT)));
                        applications.setStatus(cur.getString(cur.getColumnIndex(STATUS)));
                        applications.setCoordinator_remarks(cur.getString(cur.getColumnIndex(COORDINATOR_REMARKS)));
                        applications.setHod_remarks(cur.getString(cur.getColumnIndex(HOD_REMARKS)));
                        appList.add(applications);
                    } while (cur.moveToNext());
                }
            }
        } finally {
          db.endTransaction();
          cur.close();
        }
        return appList;
    }

    public String getRole(String email){
        String role = new String();
        Cursor cur = null;

        String SQL_SELECT_ROLE_QUERY = "SELECT "+ COLUMN_USER_ROLE +" FROM "+ TABLE_USER +" WHERE "
                +COLUMN_USER_EMAIL + "=" + "\""+email+"\"" ;


        openDataBase();

        db.beginTransaction();

        cur = db.rawQuery(SQL_SELECT_ROLE_QUERY,null);

        try {
            if(cur != null)
            {
                if(cur.moveToFirst())
                {
                    role= cur.getString(cur.getColumnIndex(COLUMN_USER_ROLE));
                }
            }
        } finally {
            db.endTransaction();
            cur.close();
        }
        return role;
    }

    /**
     * This method is to fetch all user and return the list of user records
     *
     * @return list
     */
    public List<UserModel> getAllUser() {
        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_ID,
                COLUMN_USER_EMAIL,
                COLUMN_USER_NAME,
                COLUMN_USER_PASSWORD,
                COLUMN_USER_ROLE
        };
        // sorting orders
        String sortOrder =
                COLUMN_USER_NAME + " ASC";
        List<UserModel> userList = new ArrayList<UserModel>();
        SQLiteDatabase db = this.getReadableDatabase();
        // query the user table
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id,user_name,user_email,user_password FROM user ORDER BY user_name;
         */
        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order
        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                UserModel user = new UserModel();
                user.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_USER_ID))));
                user.setName(cursor.getString(cursor.getColumnIndex(COLUMN_USER_NAME)));
                user.setEmail(cursor.getString(cursor.getColumnIndex(COLUMN_USER_EMAIL)));
                user.setPassword(cursor.getString(cursor.getColumnIndex(COLUMN_USER_PASSWORD)));
                user.setRole(cursor.getString(cursor.getColumnIndex(COLUMN_USER_ROLE)));
                // Adding user record to list
                userList.add(user);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        // return user list
        return userList;
    }

    public void updateStatus(int id, int status){
        ContentValues cv = new ContentValues();
        //cv.put(STATUS, status);
        //db.update(TODO_TABLE, cv, ID + "=?", new String[]{String.valueOf(id)});
    }

    public void updateTask(int id, String task){
        ContentValues cv = new ContentValues();
        //cv.put(TASK, task);
        //db.update(TODO_TABLE, cv, ID + "=?", new String[]{String.valueOf(id)});
    }

    public void updateDate(int id, String date){
        ContentValues cv = new ContentValues();
        //cv.put(DATE, date);
        //db.update(TODO_TABLE, cv, ID + "=?", new String[]{String.valueOf(id)});
    }

    public void updateCategory(int id, String cat){
        ContentValues cv = new ContentValues();
        //cv.put(CATEGORY, cat);
        //db.update(TODO_TABLE, cv, ID + "=?", new String[]{String.valueOf(id)});
    }

    /**
     * This method to update user record
     *
     * @param user
     */
    public void updateUser(UserModel user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_NAME, user.getName());
        values.put(COLUMN_USER_EMAIL, user.getEmail());
        values.put(COLUMN_USER_PASSWORD, user.getPassword());
        values.put(COLUMN_USER_ROLE, user.getRole());
        // updating row
        db.update(TABLE_USER, values, COLUMN_USER_ID + " = ?",
                new String[]{String.valueOf(user.getId())});
        db.close();
    }

    public void deleteTask(int id){
        //db.delete(TODO_TABLE, ID + "=?", new String[]{String.valueOf(id)});
    }

    /**
     * This method is to delete user record
     *
     * @param user
     */
    public void deleteUser(UserModel user) {
        SQLiteDatabase db = this.getWritableDatabase();
        // delete user record by id
        db.delete(TABLE_USER, COLUMN_USER_ID + " = ?",
                new String[]{String.valueOf(user.getId())});
        db.close();
    }

    /**
     * This method to check user exist or not
     *
     * @param email
     * @return true/false
     */
    public boolean checkUser(String email) {
        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = COLUMN_USER_EMAIL + " = ?";
        // selection argument
        String[] selectionArgs = {email};
        // query user table with condition
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com';
         */
        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }
        return false;
    }

    /**
     * This method to check user exist or not
     *
     * @param email
     * @param password
     * @return true/false
     */
    public boolean checkUser(String email, String password) {
        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = COLUMN_USER_EMAIL + " = ?" + " AND " + COLUMN_USER_PASSWORD + " = ?";
        // selection arguments
        String[] selectionArgs = {email, password};
        // query user table with conditions
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com' AND user_password = 'qwerty';
         */
        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }
        return false;
    }
}
