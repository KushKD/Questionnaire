package Helper;

import android.database.sqlite.SQLiteOpenHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.HashMap;

import Model.QuestionsData;

/**
 * Created by kuush on 11/14/2016.
 */

public class DatabaseHandler extends SQLiteOpenHelper {
    boolean bool = false;
    static final ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
    private ArrayList<HashMap<String, String>> list_Attendance_Details = new ArrayList<HashMap<String, String>>();


    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String
            DATABASE_NAME = "DIT_Questionare";

    // Contacts table name
    private static final String TABLE_FEEDBACK = "Feebback";


    //User Details Columns
    private static final String KEY_ID = "id";
    private static final String PERSONNAME = "ComtactPersonName";
    private static final String COMPANYNAME = "Company_Name";
    private static final String STATE = "State";
    private static final String CITY = "City";
    private static final String ADDRESS = "Address";
    private static final String WEBSITEADDRESS = "WebsiteAddress";
    private static final String CONTACTPERSONMAIL = "ContactPersonemail";
    private static final String CONTACTPERSONMOBILE = "ContactPersonMobile";
    private static final String CONTACTPERSONFAX = "ContactPersonFax";
    private static final String QUESTION_ONE = "Question_One";
    private static final String QUESTION_TWO = "Question_Two";
    private static final String QUESTION_THREE = "Question_Three";
    private static final String DATE_TIME = "date_time";


    //Database Columns HASHMAP Attendance Details
    public static final String ID_DB = "id";
    public static final String PERSONNAME_DB = "ComtactPersonName";
    public static final String COMPANYNAME_DB = "Company_Name";
    public static final String STATE_DB = "State";
    public static final String CITY_DB = "City";
    public static final String ADDRESS_DB = "Address";
    public static final String WEBSITEADDRESS_DB = "WebsiteAddress";
    public static final String CONTACTPERSONMAIL_DB = "ContactPersonemail";
    public static final String CONTACTPERSONMOBILE_DB = "ContactPersonMobile";
    public static final String CONTACTPERSONFAX_DB = "ContactPersonFax";
    public static final String QUESTION_ONE_DB = "Question_One";
    public static final String QUESTION_TWO_DB = "Question_Two";
    public static final String QUESTION_THREE_DB = "Question_Three";
    public static final String DATE_TIME_DB = "date_time";


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USERDATA_TABLE = "CREATE TABLE " + TABLE_FEEDBACK + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + PERSONNAME + " TEXT,"
                + COMPANYNAME + " TEXT,"
                + STATE + " TEXT,"
                + CITY + " TEXT,"
                + ADDRESS + " TEXT,"
                + WEBSITEADDRESS + " TEXT,"
                + CONTACTPERSONMAIL + " TEXT,"
                + CONTACTPERSONMOBILE + " TEXT,"
                + CONTACTPERSONFAX + " TEXT,"
                + QUESTION_ONE + " TEXT,"
                + QUESTION_TWO + " TEXT,"
                + QUESTION_THREE + " TEXT,"
                + DATE_TIME + " TEXT" + ")";


        db.execSQL(CREATE_USERDATA_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FEEDBACK);

        // Create tables again
        onCreate(db);
    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    public Boolean addContact(QuestionsData Details) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(PERSONNAME, Details.getContactPersonName()); // Fingure String
        values.put(COMPANYNAME, Details.getCompany_Name());
        values.put(STATE, Details.getState());
        values.put(CITY, Details.getCity());
        values.put(ADDRESS, Details.getAddress());
        values.put(WEBSITEADDRESS, Details.getWebsiteAddress());
        values.put(CONTACTPERSONMAIL, Details.getContactPersonemail());
        values.put(CONTACTPERSONMOBILE, Details.getContactPersonMobile());
        values.put(CONTACTPERSONFAX, Details.getContactPersonFax());
        values.put(QUESTION_ONE, Details.getQuestion_One());
        values.put(QUESTION_TWO, Details.getQuestion_Two());
        values.put(QUESTION_THREE, Details.getQuestion_Three());
        values.put(DATE_TIME, Details.getDateTime());

        Log.e("Valaues", values.toString());

        // Inserting Row
        db.insert(TABLE_FEEDBACK, null, values);
        db.close(); // Closing database connection

        try {
            exportDatabse(DATABASE_NAME);
            return true;
        } catch (Exception e) {
            Log.d("Got Error ..", e.getLocalizedMessage());
            return false;
        }

    }


    // Getting the Complete Database in a List
  /*  public ArrayList<HashMap<String, String>> GetAllData(){
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_USERDETAILS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        while(cursor.moveToNext()){
            HashMap<String,String> temp = new HashMap<String, String>();
            Log.d(KEY_ID_DB, cursor.getString(0));
            temp.put(KEY_ID_DB, cursor.getString(0));
            temp.put(FINGER_ONE_DB, cursor.getString(1));
            temp.put(FINGER_TWO_DB,cursor.getString(2));
            temp.put(AADHAAR_DB,cursor.getString(3));
            temp.put(NAME_DB,cursor.getString(4));
            temp.put(CAREOFF_DB,cursor.getString(5));
            temp.put(DOB_DB,cursor.getString(6));
            list.add(temp);
        }
        return list;
    }*/

    // // Getting the Complete Database in a List Attendance
    public ArrayList<HashMap<String, String>> GetAllData_AttendanceStatus() {
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_FEEDBACK + " ORDER BY " + DATE_TIME + " DESC";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        while (cursor.moveToNext()) {
            HashMap<String, String> Attendance_Details = new HashMap<String, String>();
            //Log.d(KEY_ID_DB, cursor.getString(0));
            Attendance_Details.put(ID_DB, cursor.getString(0));
            Attendance_Details.put(PERSONNAME_DB, cursor.getString(1));
            Attendance_Details.put(COMPANYNAME_DB, cursor.getString(2));
            Attendance_Details.put(STATE_DB, cursor.getString(3));
            Attendance_Details.put(CITY_DB, cursor.getString(4));
            Attendance_Details.put(ADDRESS_DB, cursor.getString(5));

            Attendance_Details.put(WEBSITEADDRESS_DB, cursor.getString(6));
            Attendance_Details.put(CONTACTPERSONMAIL_DB, cursor.getString(7));
            Attendance_Details.put(CONTACTPERSONMOBILE_DB, cursor.getString(8));
            Attendance_Details.put(CONTACTPERSONFAX_DB, cursor.getString(9));
            Attendance_Details.put(QUESTION_ONE_DB, cursor.getString(10));
            Attendance_Details.put(QUESTION_TWO_DB, cursor.getString(11));
            Attendance_Details.put(QUESTION_THREE_DB, cursor.getString(12));

            Attendance_Details.put(DATE_TIME_DB, cursor.getString(13));
            list_Attendance_Details.add(Attendance_Details);
        }
        return list_Attendance_Details;

    }


    // Getting contacts Count
    public int getFeedbackCount() {
        String countQuery = "SELECT  * FROM " + TABLE_FEEDBACK;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        db.close();

        return count;
    }

    // Updating single contact
  /*  public int updateContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact.getName());
        values.put(KEY_PH_NO, contact.getPhoneNumber());
        // updating row
        return db.update(TABLE_CONTACTS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(contact.getID()) });
    }*/

    // Deleting single contact
  /*  public void deleteContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CONTACTS, KEY_ID + " = ?",
                new String[] { String.valueOf(contact.getID()) });
        db.close();
    }*/

    // Adding new contact
   /* void addContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact.getName()); // Contact Name
        values.put(KEY_PH_NO, contact.getPhoneNumber()); // Contact Phone
        // Inserting Row
        db.insert(TABLE_CONTACTS, null, values);
        db.close(); // Closing database connection
    }*/

    // Getting single contact
   /* Contact getContact(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_REPORTING, new String[] { KEY_ID,
                        KEY_NAME, KEY_PH_NO }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        Contact contact = new Contact(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2));
        // return contact
        return contact;
    }*/

    // Getting All Contacts
  /*  public List<FingurePoJo> getAllContacts() {
        List<FingurePoJo> FingureList = new ArrayList<FingurePoJo>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_REPORTING;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                FingurePoJo FP = new FingurePoJo();
               // contact.setID(Integer.parseInt(cursor.getString(0)));
               // contact.setName(cursor.getString(1));
               // contact.setPhoneNumber(cursor.getString(2));
                FP.setFingure_DB(cursor.getString(1));
                Log.d(cursor.getString(1) , "Nothing");
                FingureList.add(FP);
            } while (cursor.moveToNext());
        }
        // return contact list
        return FingureList;
    }*/


    public void exportDatabse(String databaseName) {

        try {
            File sd = Environment.getExternalStorageDirectory();
            File data = Environment.getDataDirectory();

            if (sd.canWrite()) {
                Log.e("We are Here", "1");
                String currentDBPath = "//data//" + DatabaseHandler.class.getPackage().getName() + "//databases//" + databaseName + "";
                Log.e("We are Here", "2");
                String backupDBPath = "backupname.db";
                Log.e("We are Here", "3");
                File currentDB = new File(data, currentDBPath);
                Log.e("Path", currentDBPath.toString());
                Log.e("We are Here", "4");
                File backupDB = new File(sd, backupDBPath);
                Log.e("Path", backupDB.toString());

                try {
                    Log.e("Exists", Boolean.toString(currentDB.exists()));
                } catch (Exception e) {
                    Log.d("Error", e.getLocalizedMessage().toString());
                }

                try {
                    if (currentDB.exists()) {
                        FileChannel src = new FileInputStream(currentDB).getChannel();
                        Log.e("We are Here", "6");
                        FileChannel dst = new FileOutputStream(backupDB).getChannel();
                        Log.e("We are Here", "7");
                        dst.transferFrom(src, 0, src.size());
                        Log.e("We are Here", "8");
                        src.close();
                        dst.close();
                    } else {
                        Log.d("Error", "No Idea");
                    }
                } catch (Exception e) {
                    Log.d("Error", e.getLocalizedMessage().toString());
                }
            } else {
                Log.d("Error", "No Idea 2");
            }
        } catch (Exception e) {
            Log.d("Error", e.getLocalizedMessage().toString());
        }
    }
}

