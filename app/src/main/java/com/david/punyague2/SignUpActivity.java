package com.david.punyague2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {

    Button btnSignUp;
    EditText usernameSignIn, passwordSignIn;

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //view
        btnSignUp      = findViewById(R.id.signup_button);
        usernameSignIn = findViewById(R.id.usernameSignIn);
        passwordSignIn = findViewById(R.id.passwordSignIn);

        mAuth = FirebaseAuth.getInstance();
    }
    private void signUpUser(String email,final String password){
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, task -> {
            if (!task.isSuccessful())
            {
                if (password.length() < 8) {
                    Toast.makeText(SignUpActivity.this, "Password harus > 8 karakter", Toast.LENGTH_SHORT).show();
                }
            }
            else
            {
                Toast.makeText(SignUpActivity.this, "Sign Up Berhasil", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(SignUpActivity.this, SignInActivity.class));
            }

        });
    }

}

