package questionnaire.himachal.dit.questionnaire;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button Clear_bt,proceed_bt,details_bt;
    RadioGroup radiochoice_RG;
    private RadioButton radioSexButton;
    LinearLayout layout;
    EditText answertwo,answerthree;

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

        radiochoice_RG.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // get selected radio button from radioGroup
                int selectedId = radiochoice_RG.getCheckedRadioButtonId();

                // find the radiobutton by returned id
                radioSexButton = (RadioButton) findViewById(selectedId);

                Toast.makeText(getApplicationContext(), radioSexButton.getText(), Toast.LENGTH_SHORT).show();

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
                //// TODO: 11/14/2016  
            }
        });
    }
}
