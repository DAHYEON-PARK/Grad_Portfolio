package com.example.ttong.Tab;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.ttong.R;
import com.example.ttong.View.SttView;

public class DialTab extends Activity implements View.OnClickListener{

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_dial);

        Button btn1 = (Button)findViewById(R.id.button1);
        btn1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this,SttView.class);
        startActivity(intent);
    }
}

