package com.example.myappnew;

import android.content.Intent;
import android.os.Bundle;
import android.os.PatternMatcher;
import android.util.Patterns;
import android.view.LayoutInflater;
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

import com.airbnb.lottie.utils.Utils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.BreakIterator;

import maes.tech.intentanim.CustomIntent;

public class SignupTabFragment  extends Fragment {
    private EditText etemail,etmobile,etpass,etusername;
    private Button signup;
    private FirebaseAuth mAuth;
    FirebaseDatabase database;
    DatabaseReference myref;
    public int c=0;
    ImageView signupimg;

    private ProgressBar progressBar;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {



        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.signup_tab_fragment,container,false);


        mAuth = FirebaseAuth.getInstance();
        etemail = (EditText)root.findViewById(R.id.email);
        etmobile = root.findViewById(R.id.mobile);
        etusername =  root.findViewById(R.id.username);
        etpass =  (EditText)root.findViewById(R.id.pass);
        progressBar = (ProgressBar)root.findViewById(R.id.progressbar);
        signupimg = root.findViewById(R.id.imageView2);


        signup = root.findViewById(R.id.signup);

        etemail.setTranslationY(800);
        etpass.setTranslationY(800);
        etmobile.setTranslationY(800);
        etusername.setTranslationY(800);
        signup.setTranslationY(800);
        signupimg.setTranslationX(800);

        float v=0;
        etemail.setAlpha(v);
        etpass.setAlpha(v);
        etusername.setAlpha(v);
        etmobile.setAlpha(v);
        signup.setAlpha(v);
        signupimg.setAlpha(v);

        signupimg.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        etemail.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(300).start();
        etpass.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(500).start();
        etmobile.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(500).start();
        etusername.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(500).start();
        signup.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(700).start();



        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });
       


        return  root;
    }

    private void register() {
        String email = etemail.getText().toString().trim();
        String password = etpass.getText().toString().trim();
        String mobile = etmobile.getText().toString().trim();
        String username = etusername.getText().toString().trim();

        if(email.isEmpty()) {
            etemail.setError("Email is required");
            etemail.requestFocus();
            return;
        }

        else if(username.isEmpty()) {
            etusername.setError("Username is required");
            etusername.requestFocus();
            return;
        }
        else if(mobile.isEmpty()) {
            etmobile.setError("Mobile Number is required");
            etmobile.requestFocus();
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


            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                User user = new User(email, mobile, username, password);
                                FirebaseDatabase.getInstance().getReference("Users")
                                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {

                                            etemail.setText("");
                                            etusername.setText("");
                                            etmobile.setText("");
                                            etpass.setText("");

                                            Toast.makeText(getActivity(), "User has been registered successfully", Toast.LENGTH_LONG).show();
                                            progressBar.setVisibility(View.GONE);

                                            Intent i = new Intent(getActivity(),LoginActivity.class);
                                            startActivity(i);
                                            CustomIntent.customType(getActivity(),"right-to-left");
                                        } else {
                                            Toast.makeText(getActivity(), "Failed to register in Database ! Try Again !", Toast.LENGTH_SHORT).show();
                                            progressBar.setVisibility(View.GONE);
                                        }
                                    }

                                });
                            } else {
                                Toast.makeText(getActivity(), "Failed to register! Try Again !", Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                            }
                        }
                    });






        }



    }



    public static String EncodeString(String string) {
        return string.replace(".", "_");
    }

    public static String DecodeString(String string) {
        return string.replace("_", ".");
    }

}
