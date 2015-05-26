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
        setContentView(R.layout.tab_ttong);

        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        SignUpView signUpView = new SignUpView(this);
        signUpView.setVisibility(View.GONE);


        if(true){
           signUpView.setVisibility(View.VISIBLE);

        }
        else{

        }
    }
}
