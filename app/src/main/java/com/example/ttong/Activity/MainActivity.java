package com.example.ttong.Activity;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.ActionBar;
import android.app.ActionBar.*;

import com.example.ttong.Fragment.DialFragment;
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
        dialTab.setTabListener(new ProductTabListener(this, DialFragment.class.getName()));
        abar.addTab(dialTab);
    }

    private class ProductTabListener implements ActionBar.TabListener{
        private Fragment mFragment;
        private final Activity mActivity;
        private final String mFragName;

        public ProductTabListener(Activity activity, String fragName){
            mActivity = activity;
            mFragName = fragName;
        }

        public void onTabReselected(Tab tab, FragmentTransaction arg1){
        }

        // tab selected
        public void onTabSelected(Tab tab, FragmentTransaction ft){
            mFragment = Fragment.instantiate(mActivity,mFragName);
            ft.add(android.R.id.content, mFragment);
        }

        // tab unselected
        public void onTabUnselected(Tab tab, FragmentTransaction ft){
            ft.remove(mFragment);
            mFragment = null;
        }
    }
}

