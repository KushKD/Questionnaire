package questionnaire.himachal.dit.questionnaire;


import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;



public class SplashScreen extends Activity {

    protected boolean _active = true;
    protected int _splashTime = 3000;
    private int GPS_PERMISSION = 24;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent login_Intent = new Intent(SplashScreen.this, MainActivity.class);
                SplashScreen.this.startActivity(login_Intent);
                SplashScreen.this.finish();





            }
        }, 2000);


    }


}
