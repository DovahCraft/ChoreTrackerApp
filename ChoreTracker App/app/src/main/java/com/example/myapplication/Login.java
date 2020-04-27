package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.amazonaws.mobile.client.AWSMobileClient;
import com.amazonaws.mobile.client.Callback;
import com.amazonaws.mobile.client.SignInUIOptions;
import com.amazonaws.mobile.client.UserStateDetails;

public class Login extends AppCompatActivity {

    public void forgotPassword(View view) {
        Context context = getApplicationContext();
        CharSequence text = "Reset link sent!";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Intent intent = new Intent(this, Register.class);
        Intent home = new Intent(this, MainActivity.class);
        final String TAG = "Cognito";
        //final EditText username = findViewById(R.id.username);
        //final EditText password = findViewById(R.id.password);

        AWSMobileClient.getInstance().showSignIn(this,
                SignInUIOptions.builder()
                .nextActivity(MainActivity.class)
                .logo(R.drawable.logo)
                        .backgroundColor(Color.WHITE)
                .canCancel(true)
                .build(),
                new Callback<UserStateDetails>() {

            @Override
            public void onResult(UserStateDetails result) {
                Log.d(TAG, "onResult: " + result.getUserState());
            }

            @Override
            public void onError(Exception e) {
                Log.e(TAG, "onError: ", e);
                startActivity(home);
            }
        });

        AWSMobileClient.getInstance().initialize(this, new Callback<UserStateDetails>() {
            @Override
            public void onResult(UserStateDetails result) {
                Log.d("JOE","Start  sign up");

            }

            @Override
            public void onError(Exception e) {
                Log.d("JOE","onError  sign up"+ e.toString());
            }
        });


    }
}

