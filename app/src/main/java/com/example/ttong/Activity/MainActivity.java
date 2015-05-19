package com.example.ttong.Activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;

import com.example.ttong.Fragment.DialFragment;
import com.example.ttong.Fragment.TtongFragment;
import com.example.ttong.Fragment.HistoryFragment;
import com.example.ttong.Fragment.ContactFragment;
import com.example.ttong.Listener.TabListener;
import com.example.ttong.R;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        final ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        actionBar.addTab(actionBar.newTab().setText("Dial").setTabListener(new TabListener<DialFragment>(this,"dial",DialFragment.class)));

        actionBar.addTab(actionBar.newTab().setText("TTong").setTabListener(new TabListener<TtongFragment>(this,"ttong",TtongFragment.class)));

        actionBar.addTab(actionBar.newTab().setText("History").setTabListener(new TabListener<HistoryFragment>(this,"dial",HistoryFragment.class)));

        actionBar.addTab(actionBar.newTab().setText("Contact").setTabListener(new TabListener<ContactFragment>(this,"dial",ContactFragment.class)));

    }

    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putInt("selectedTab",getActionBar().getSelectedNavigationIndex());
    }
}
