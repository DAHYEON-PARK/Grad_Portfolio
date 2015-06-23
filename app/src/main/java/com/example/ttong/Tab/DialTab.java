package com.example.ttong.Tab;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.ttong.R;
import com.example.ttong.View.SttView;
import com.example.ttong.View.TtsView;

public class DialTab extends Activity implements View.OnClickListener{

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_dial);

        Button btn_stt = (Button)findViewById(R.id.button1);
        btn_stt.setOnClickListener(this);

        Button btn_tts = (Button)findViewById(R.id.button2);
        btn_tts.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.button1 :
                Intent intent1 = new Intent(this,SttView.class);
                startActivity(intent1);
                break;
            case R.id.button2 :
                Intent intent2 = new Intent(this,TtsView.class);
                startActivity(intent2);
                break;
        }
    }
}

