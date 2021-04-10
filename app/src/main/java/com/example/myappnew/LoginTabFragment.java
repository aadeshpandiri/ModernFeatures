package com.example.myappnew;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.SignInMethodQueryResult;

import java.util.List;

import maes.tech.intentanim.CustomIntent;

public class LoginTabFragment  extends Fragment {
    private EditText etemail,etpass;
    private TextView forgotpass;
    private Button login;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;
    public static String idforfirebase;
    ImageView loginimg;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.login_tab_fragment,container,false);

        mAuth = FirebaseAuth.getInstance();
        etemail = root.findViewById(R.id.email);
        etpass =  root.findViewById(R.id.pass);
        forgotpass = root.findViewById(R.id.forgotpass);
        login = root.findViewById(R.id.login);
        progressBar = (ProgressBar)root.findViewById(R.id.progressbar);
        loginimg = root.findViewById(R.id.imageView2);

        etemail.setTranslationY(800);
        etpass.setTranslationY(800);
        forgotpass.setTranslationY(800);
        login.setTranslationY(800);
        loginimg.setTranslationX(100);

        float v=0;
        etemail.setAlpha(v);
        etpass.setAlpha(v);
        forgotpass.setAlpha(v);
        login.setAlpha(v);
        loginimg.setAlpha(v);

        loginimg.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        etemail.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(300).start();
        etpass.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(500).start();
        forgotpass.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(500).start();
        login.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(700).start();


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        forgotpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                forgotpass();
            }
        });

        etpass.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_LEFT = 0;
                final int DRAWABLE_TOP = 1;
                final int DRAWABLE_RIGHT = 2;
                final int DRAWABLE_BOTTOM = 3;

                if(event.getAction() == MotionEvent.ACTION_UP) {
                    if(event.getRawX() >= (etpass.getRight() - etpass.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        // your action here
                        etpass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());

                        return true;
                    }else { etpass.setTransformationMethod(PasswordTransformationMethod.getInstance());}
                }
                return false;
            }
        });
        return  root;
    }

    private void forgotpass() {

        Intent i11 = new Intent(getActivity(),ForgotPassword.class);
        startActivity(i11);
        CustomIntent.customType(getActivity(),"right-to-left");

    }

    private void login() {
        String email = etemail.getText().toString().trim();
        String password = etpass.getText().toString().trim();
        if(email.isEmpty()) {
            etemail.setError("Email is required");
            etemail.requestFocus();
            return;
        }
        else if(password.isEmpty()) {
            etpass.setError("Password is required");
            etpass.requestFocus();
            return;
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            etemail.setError("Please provide valid Email");
            etemail.requestFocus();
            return;
        }
        else if(password.length() <6)
        {
            etpass.setError("Min Password length should be 6 characters");
            etpass.requestFocus();
            return;
        }

        else {
         progressBar.setVisibility(View.VISIBLE);


         mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
             @Override
             public void onComplete(@NonNull Task<AuthResult> task) {
                 if(task.isSuccessful())
                 {

                     etemail.setText("");
                     etpass.setText("");

                     Toast.makeText(getActivity(), "Logged-In Successfully", Toast.LENGTH_LONG).show();
                     progressBar.setVisibility(View.GONE);
                     //idforfirebase = email;
                     idforfirebase= FirebaseAuth.getInstance().getCurrentUser().getUid();
                     Intent i = new Intent(getActivity(),HomeActivity.class);
                     startActivity(i);
                     CustomIntent.customType(getActivity(),"right-to-left");

                 }
                 else
                 {
                     Toast.makeText(getActivity(), "Failed to Login ! Please check your Credentials !", Toast.LENGTH_LONG).show();
                     progressBar.setVisibility(View.GONE);
                 }
             }
         });

        }
    }

    private void checkUserExist(String email, String password) {


    }


}
