package iavocado.iavocado.Activity.SplashActivtiy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.FlingAnimation;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

import iavocado.iavocado.Activity.HomeActivity.HomeActivity;
import iavocado.iavocado.Activity.OtpActivity.OtpActivity;
import iavocado.iavocado.R;

public class SplashActivity extends AppCompatActivity {
    //Splash section

    private static final int SPLASH_TIME_OUT = 2000;

    ImageView imageView;
    float height;


//Login section

    EditText mob_et,et_refer;
    String mobile = "";
    String referCode = "";
    FloatingActionButton login;
    ProgressDialog pDialog;
//    CallApi callApi = new CallApi();
//    CommonSharedPreference commonSharedPreference;

    ConstraintLayout layout;
    TextInputLayout login_inputTextLayout;

    String name = "";
    boolean isfocusedChanged = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);

//        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        initializeViews();
        try {
            Glide.with(this).load(R.drawable.logo_gif).into(imageView);

        }catch (NullPointerException ignored){

        }catch (Exception e){

        }

  