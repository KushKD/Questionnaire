package questionnaire.himachal.dit.questionnaire;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ThemedSpinnerAdapter;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import Helper.DatabaseHandler;
import Helper.Date_Time;
import Model.QuestionsData;

public class MainActivity extends AppCompatActivity {

    Button Clear_bt,proceed_bt,details_bt, history_bt;
    TextView count_bt;
    RadioGroup radiochoice_RG;
    private RadioButton radioSexButton;
    LinearLayout layout;
    EditText answertwo,answerthree;

    QuestionsData QD = new QuestionsData();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Clear_bt = (Button)findViewById(R.id.clear);
        count_bt = (TextView)findViewById(R.id.count);
        proceed_bt = (Button)findViewById(R.id.proceed);
        details_bt = (Button)findViewById(R.id.history);
        radiochoice_RG = (RadioGroup)findViewById(R.id.radiochoice);
        layout = (LinearLayout)findViewById(R.id.layout);
        answertwo = (EditText)findViewById(R.id.answertwo);
        answerthree = (EditText)findViewById(R.id.answerthree);
        history_bt = (Button)findViewById(R.id.history);

        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radiochoice);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                int selectedId = radiochoice_RG.getCheckedRadioButtonId();
                radioSexButton = (RadioButton) findViewById(selectedId);
                QD.setQuestion_One(radioSexButton.getText().toString().trim());

                if(radioSexButton.getText().toString().equalsIgnoreCase("Yes")){
                    layout.setVisibility(View.VISIBLE);
                }else{
                    layout.setVisibility(View.GONE);
                }
            }
        });


       try{
           DatabaseHandler DB = new DatabaseHandler(MainActivity.this);
           Log.e("Count Is",Integer.toString(DB.getFeedbackCount()));
           count_bt.setText(Integer.toString(DB.getFeedbackCount()));
       }catch(Exception e){
           Log.e("Working",e.getLocalizedMessage().toString());
       }
        Clear_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Clear Data
                try {

                    answertwo.setText("");
                    answerthree.setText("");
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(), e.getLocalizedMessage().toString().trim(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        proceed_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                QD.setQuestion_Two(answertwo.getText().toString().trim());
                QD.setQuestion_Three(answerthree.getText().toString().trim());

                ShowAlert(QD);


            }
        });

        history_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login_Intent = new Intent(MainActivity.this, ReportActivity.class);
                startActivity(login_Intent);
            }
        });
    }

    private void ShowAlert(QuestionsData  s) {
        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.dialog_info);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setAttributes(lp);
        dialog.setTitle("Feedback");
        dialog.setCancelable(false);
        dialog.show();
        TextView DialogInfo = (TextView)dialog.findViewById(R.id.dialog_info);
        DialogInfo.setText("Contact Information");
        final EditText  PERSONNAME_et = (EditText) dialog.findViewById(R.id.ComtactPersonName);
        final EditText COMPANYNAME_et= (EditText) dialog.findViewById(R.id.ComtactPersonName);
        final EditText STATE_et= (EditText) dialog.findViewById(R.id.Company_Name);
        final EditText CITY_et = (EditText) dialog.findViewById(R.id.city);
        final EditText ADDRESS_et= (EditText) dialog.findViewById(R.id.address);
        final EditText WEBSITEADDRESS_et= (EditText) dialog.findViewById(R.id.WebsiteAddress);
        final EditText CONTACTPERSONMAIL_et= (EditText) dialog.findViewById(R.id.ContactPersonemail);
        final EditText CONTACTPERSONMOBILE_et= (EditText) dialog.findViewById(R.id.ContactPersonMobile);
        final EditText CONTACTPERSONFAX_et= (EditText) dialog.findViewById(R.id.fax);


        Button agree = (Button)dialog.findViewById(R.id.dialog_ok);
        Button dialog_exit = (Button)dialog.findViewById(R.id.dialog_exit);

        final String QuestionOne = s.getQuestion_One();
        final String Questiontwo = s.getQuestion_Two();
        final String Questionthree = s.getQuestion_Three();


        dialog_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
            }
        });
        agree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    QD.setContactPersonName(PERSONNAME_et.getText().toString().trim());
                    QD.setCompany_Name(COMPANYNAME_et.getText().toString().trim());
                    QD.setState(STATE_et.getText().toString().trim());
                    QD.setCity(CITY_et.getText().toString().trim());
                    QD.setAddress(ADDRESS_et.getText().toString());
                    QD.setWebsiteAddress(WEBSITEADDRESS_et.getText().toString().trim());
                    QD.setContactPersonemail(CONTACTPERSONMAIL_et.getText().toString());
                    QD.setContactPersonMobile(CONTACTPERSONMOBILE_et.getText().toString());
                    QD.setContactPersonFax(CONTACTPERSONFAX_et.getText().toString());
                    QD.setQuestion_One(QuestionOne);
                    QD.setQuestion_Two(Questiontwo);
                    QD.setQuestion_Three(Questionthree);
                    QD.setDateTime(Date_Time.GetDateAndTime());

                    DatabaseHandler DH = new DatabaseHandler(MainActivity.this);

                    if(QD.getContactPersonName().length()!=0 && QD.getContactPersonMobile().length()!=0){
                        if( DH.addContact(QD)){
                            Toast.makeText(MainActivity.this,"Data Saved Successfully",Toast.LENGTH_LONG).show();
                            dialog.dismiss();
                            onRestart();
                        }else{
                            Toast.makeText(MainActivity.this,"Unable to save Data",Toast.LENGTH_LONG).show();
                        }
                    }else{
                        Toast.makeText(MainActivity.this,"Please enter your name and valid phone number.",Toast.LENGTH_LONG).show();
                    }

                }catch(Exception e){
                    Toast.makeText(getApplicationContext(), "Something Went Wrong", Toast.LENGTH_SHORT).show();
                }
               // onRestart();

            }
        });
    }



}
