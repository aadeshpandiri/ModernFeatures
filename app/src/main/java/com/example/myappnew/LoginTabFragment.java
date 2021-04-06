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
import androidx.viewpager.widget.ViewPager;

public class LoginTabFragment  extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        EditText email,pass;
        TextView forgotpass;
        Button login;
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.login_tab_fragment,container,false);


        email = root.findViewById(R.id.email);
        pass =  root.findViewById(R.id.pass);
        forgotpass = root.findViewById(R.id.forgotpass);
        login = root.findViewById(R.id.login);

        email.setTranslationY(800);
        pass.setTranslationY(800);
        forgotpass.setTranslationY(800);
        login.setTranslationY(800);

        float v=0;
        email.setAlpha(v);
        pass.setAlpha(v);
        forgotpass.setAlpha(v);
        login.setAlpha(v);

        email.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(300).start();
        pass.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(500).start();
        forgotpass.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(500).start();
        login.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(700).start();

        return  root;
    }
}
