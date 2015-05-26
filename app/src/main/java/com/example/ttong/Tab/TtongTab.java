package com.example.ttong.Tab;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.ttong.R;
import com.example.ttong.View.SignUpView;

public class TtongTab extends Activity {

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        SignUpView signUpView = new SignUpView(this);
        signUpView.setVisibility(View.GONE);
        FrameLayout.LayoutParams parmas = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        if(true){
            signUpView.setVisibility(View.VISIBLE);
            setContentView(signUpView,parmas);
        }
        else{
            setContentView(R.layout.tab_ttong);
        }
    }
}
