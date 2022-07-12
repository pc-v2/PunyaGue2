package com.david.punyague2;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnSignUp;
    EditText usernameSignUp, passwordSignUp;

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //view
        btnSignUp      = findViewById(R.id.signup_button);
        usernameSignUp = findViewById(R.id.usernameSignUp);
        passwordSignUp = findViewById(R.id.passwordSignUp);

        mAuth = FirebaseAuth.getInstance();

        // button di klik
        btnSignUp.setOnClickListener(this);

    }
    private void signUpUser(String email,final String password){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "createUserWithEmail:success");
                        FirebaseUser user = mAuth.getCurrentUser();
                        updateUI(user);
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "createUserWithEmail:failure", task.getException());
                        Toast.makeText(SignUpActivity.this, "Authentication failed.",
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void updateUI(FirebaseUser user) {
        startActivity(new Intent(SignUpActivity.this,HomeActivity.class));
        user.reload();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.signup_button)
        {
            signUpUser(usernameSignUp.getText().toString(), passwordSignUp.getText().toString());
        }

    }
}

