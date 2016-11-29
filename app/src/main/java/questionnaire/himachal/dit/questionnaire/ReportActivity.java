package questionnaire.himachal.dit.questionnaire;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;

import Helper.DatabaseHandler;

public class ReportActivity extends AppCompatActivity {

    DatabaseHandler DH = null;
    BinderData bindingData ;
    ListView list;
    Button closereport_bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        list = (ListView)findViewById(R.id.listreport);
        closereport_bt = (Button)findViewById(R.id.closereport);

        closereport_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReportActivity.this.finish();
            }
        });

        ReadDB_Details RDBA = new ReadDB_Details();
        RDBA.execute();
    }

    class ReadDB_Details extends AsyncTask<String,String,String> {

        ArrayList<HashMap<String,String>> listDB_AttendanceDetails = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {

            DH = new DatabaseHandler(ReportActivity.this);
            listDB_AttendanceDetails = new ArrayList<HashMap<String,String>>();
            listDB_AttendanceDetails = DH.GetAllData_AttendanceStatus();
            String Message = null;

            if(listDB_AttendanceDetails.size()!=0){


                bindingData = new BinderData(ReportActivity.this,listDB_AttendanceDetails);
                Message = "List is not empty" + Integer.toString( listDB_AttendanceDetails.size());


            }else{
                Message = "List is  empty";
            }





            return Message;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            list.setAdapter(bindingData);
            // Toast.makeText(ReportActivity.this,s,Toast.LENGTH_LONG).show();
        }
    }

}
