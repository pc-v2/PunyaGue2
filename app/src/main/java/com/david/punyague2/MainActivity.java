package com.david.punyague2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    TextView appName;
    Button signInBtn, signUpBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    signInBtn = findViewById(R.id.signIn_button);
    signUpBtn = findViewById(R.id.signUp_button);
    appName = findViewById(R.id.appName);
    mAuth = FirebaseAuth.getInstance();

        // assign method ke button daftar & login
        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,SignInActivity.class);
                startActivity(intent);
            }
        });

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,SignUpActivity.class);
                startActivity(intent);
            }
        });
    }
    private void updateUI(FirebaseUser user) {
        user.reload();
    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update state accordingly.
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null){
            updateUI(user);
        } else {
            Toast.makeText(MainActivity.this, R.string.blm_sign_up_dulu, Toast.LENGTH_LONG).show();
            startActivity(new Intent(MainActivity.this, SignInActivity.class));
        }

    }
}