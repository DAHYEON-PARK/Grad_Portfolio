package com.example.ttong.Listener;

import android.app.Activity;
import android.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;

/**
 * Created by cse109 on 2015-05-19.
 */
public class TabListener<T extends Fragment> implements ActionBar.TabListener{

    public TabListener(Activity activity, String tag, Class<T> clz){

    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

    }
}
