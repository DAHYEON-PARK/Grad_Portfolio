package com.example.ttong.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.app.ActionBar;
import android.app.ActionBar.*;

import com.example.ttong.Fragment.DialFragment;
import com.example.ttong.Listener.TabListener;
import com.example.ttong.R;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar abar = getActionBar();
        abar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        Tab dialTab = abar.newTab();
        dialTab.setText("dial");
        dialTab.setTabListener(new ProductTabListener(this,DialFragment.class.getName()));
        abar.addTab(dialTab);
    }
}
