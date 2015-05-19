package com.example.ttong.Fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;

/**
 * Created by cse109 on 2015-05-19.
 */
public class DialFragment extends Fragment{

    Context context;

    DialFragment(Context context){
        this.context = context;
    }

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_dial);
    }
}
