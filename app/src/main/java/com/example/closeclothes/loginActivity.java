package com.example.closeclothes;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class loginActivity extends AppCompatActivity {


    @BindView(R.id.back_image)
    ImageView backImage;
    @BindView(R.id.back_image_1)
    ImageView backImage1;
    @BindView(R.id.log_title)
    TextView logTitle;
    @BindView(R.id.user_et)
    EditText userEt;
    @BindView(R.id.email_et)
    EditText emailEt;
    @BindView(R.id.repassword_et)
    EditText repasswordEt;
    @BindView(R.id.password_et)
    EditText passwordEt;
    @BindView(R.id.log_btn)
    TextView logBtn;
    @BindView(R.id.have_email_tv)
    TextView haveEmailTv;
    @BindView(R.id.sign_tv)
    TextView signTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    public void onComponentClick(View view) {
        int id = view.getId();
        if (id == R.id.sign_tv) {
            if(logBtn.getText().toString().equals("LOGIN")){
                backImage1.setVisibility(View.GONE);
                backImage.setVisibility(View.VISIBLE);
                backImage.startAnimation(AnimationUtils.loadAnimation(this, R.anim.move_back_down));

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        backImage.clearAnimation();
                        backImage1.setVisibility(View.VISIBLE);
                        backImage.setVisibility(View.GONE);

                    }
                }, 2000);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        logTitle.setText("SignUp");
                        logTitle.setY(logTitle.getY() + 2*(userEt.getHeight()*-1.5f));
                        userEt.setY(userEt.getY() + 1.85f*(userEt.getHeight()*-1.5f));
                        emailEt.setY(emailEt.getY() + userEt.getHeight()*-1.5f);
                        passwordEt.setY(passwordEt.getY() + userEt.getHeight()*-1.4f);
                        logBtn.setText("SIGNUP");
                        haveEmailTv.setText("You already have an account?");
                        signTv.setText("LogIn");

                    }
                }, 1000);

            }
        else{
                backImage.setY(backImage.getY());
                backImage.setVisibility(View.GONE);
                backImage1.setVisibility(View.VISIBLE);
                backImage1.startAnimation(AnimationUtils.loadAnimation(this, R.anim.move_back_up));

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        backImage1.clearAnimation();
                        backImage.setVisibility(View.VISIBLE);
                        backImage1.setVisibility(View.GONE);

                    }
                }, 2000);

                new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    logTitle.setText("LOGIN");
                    logTitle.setY(logTitle.getY() + 2*(userEt.getHeight()*1.5f));
                    userEt.setY(userEt.getY() + 1.85f*(userEt.getHeight()*1.5f));
                    emailEt.setY(emailEt.getY() + userEt.getHeight()*1.5f);
                    passwordEt.setY(passwordEt.getY() + userEt.getHeight()*1.4f);
                    logBtn.setText("LOGIN");
                    haveEmailTv.setText("Don't you have an account?");
                    signTv.setText("SignUp");
                }
            }, 1000);



        }
        }

    }
}