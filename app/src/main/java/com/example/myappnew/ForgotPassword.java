package com.example.myappnew;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import maes.tech.intentanim.CustomIntent;

public class ForgotPassword extends AppCompatActivity {
    EditText etemail;
    Button forgotbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        etemail = findViewById(R.id.email);
        forgotbtn = findViewById(R.id.forgotbtn);

        forgotbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                forgotpass();
            }
        });



    }
    private void forgotpass() {

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        String email = etemail.getText().toString().trim();
        if(email.isEmpty()) {
            etemail.setError("Email is required");
            etemail.requestFocus();
            return;
        }


        mAuth.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Check your mail and reset password !", Toast.LENGTH_LONG).show();
                            etemail.setText("");
                            Intent i9 = new Intent(getApplicationContext(),LoginActivity.class);
                            startActivity(i9);
                            CustomIntent.customType(ForgotPassword.this,"right-to-left");
                        } else {
                            // ...
                            Toast.makeText(ForgotPassword.this, "Check your details once again !", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if ((keyCode == KeyEvent.KEYCODE_BACK))
        {
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}