package questionnaire.himachal.dit.questionnaire;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

import Model.QuestionsData;

public class MainActivity extends AppCompatActivity {

    Button Clear_bt,proceed_bt,details_bt;
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
        proceed_bt = (Button)findViewById(R.id.proceed);
        details_bt = (Button)findViewById(R.id.history);
        radiochoice_RG = (RadioGroup)findViewById(R.id.radiochoice);
        layout = (LinearLayout)findViewById(R.id.layout);
        answertwo = (EditText)findViewById(R.id.answertwo);
        answerthree = (EditText)findViewById(R.id.answerthree);

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
        Button agree = (Button)dialog.findViewById(R.id.dialog_ok);
        Button dialog_exit = (Button)dialog.findViewById(R.id.dialog_exit);

        dialog_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
            }
        });
        agree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

}
