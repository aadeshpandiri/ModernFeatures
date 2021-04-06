package com.example.myappnew;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SignupTabFragment  extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        EditText email,mobile,pass,cpass;

        Button signup;
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.signup_tab_fragment,container,false);


        email = root.findViewById(R.id.email);
        pass =  root.findViewById(R.id.pass);
        mobile = root.findViewById(R.id.mobile);
        cpass =  root.findViewById(R.id.cpass);
        signup = root.findViewById(R.id.signup);

        email.setTranslationY(800);
        pass.setTranslationY(800);
        mobile.setTranslationY(800);
        cpass.setTranslationY(800);
        signup.setTranslationY(800);

        float v=0;
        email.setAlpha(v);
        pass.setAlpha(v);
        cpass.setAlpha(v);
        mobile.setAlpha(v);
        signup.setAlpha(v);

        email.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(300).start();
        pass.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(500).start();
        mobile.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(500).start();
        cpass.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(500).start();
        signup.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(700).start();

        return  root;
    }
}
