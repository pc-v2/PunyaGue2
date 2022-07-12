package com.david.punyague2;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.google.firebase.auth.FirebaseUser;

public class SignInActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnSignIn;
    EditText usernameSignIn, passwordSignIn;
    TextView lupaPswd;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        //view
        btnSignIn      = findViewById(R.id.signin_button);
        usernameSignIn = findViewById(R.id.usernameSignIn);
        passwordSignIn = findViewById(R.id.passwordSignIn);
        lupaPswd       = findViewById(R.id.lupaPswd);
        lupaPswd.setOnClickListener(v -> {
            Intent intent = new Intent(SignInActivity.this, LupaPassword.class);
            startActivity(intent);
        });


        mAuth = FirebaseAuth.getInstance();

        btnSignIn.setOnClickListener(this);

    }
    private void signInUser(String email, final String password){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "signInWithEmail:success");
                        FirebaseUser user = mAuth.getCurrentUser();
                        startActivity(new Intent(SignInActivity.this,HomeActivity.class));
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "signInWithEmail:failure", task.getException());
                        Toast.makeText(SignInActivity.this, "Authentication failed.",
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.signin_button)
        {
            signInUser(usernameSignIn.getText().toString(),passwordSignIn.getText().toString());
        }
    }
}