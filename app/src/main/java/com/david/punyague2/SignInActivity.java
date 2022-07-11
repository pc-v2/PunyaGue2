package com.david.punyague2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignInActivity extends AppCompatActivity {
    Button btnSignIn;
    EditText usernameSignIn, passwordSignIn;
    TextView lupaPswd;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        //view
        btnSignIn      = (Button)   findViewById(R.id.signin_button);
        usernameSignIn = (EditText) findViewById(R.id.usernameSignIn);
        passwordSignIn = (EditText) findViewById(R.id.passwordSignIn);
        lupaPswd       = (TextView) findViewById(R.id.lupaPswd);
        lupaPswd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignInActivity.this, LupaPassword.class);
            }
        });

        mAuth = FirebaseAuth.getInstance();


    }
    private void signInUser(String email,final String password){
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (!task.isSuccessful())
                {
                    if (password.length() < 8) {
                        Toast.makeText(SignInActivity.this, "Password harus > 8 karakter", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    startActivity(new Intent(SignInActivity.this, HomeActivity.class));
                }
            }
        });
    }

}