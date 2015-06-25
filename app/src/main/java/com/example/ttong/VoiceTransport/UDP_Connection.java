package com.example.ttong.VoiceTransport;


import android.app.Activity;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ttong.R;


public class UDP_Connection extends Activity implements View.OnClickListener {

    TextView ip_port_Tv, msg_Tv;
    EditText msg_Et;
    Button udpSend_Btn;

    String ipAddr;
    int port = 4444;

    UDP_Server s;
    UDP_Client c;
    Thread th_S, th_C;

    String msg;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.default_main);

        ip_port_Tv = (TextView) findViewById(R.id.info_Tv);
        WifiManager wm = (WifiManager) getSystemService(WIFI_SERVICE);
        WifiInfo wifiInfo = wm.getConnectionInfo();
        int ip = wifiInfo.getIpAddress();
        ipAddr = String.format("%d.%d.%d.%d", ip & 0xff, ip >> 8 & 0xff, ip >> 16 & 0xff, ip >> 24 & 0xff);
        ip_port_Tv.setText("ip : " + ipAddr + "\nport : " + port);

        msg_Et = (EditText) findViewById(R.id.msg_Et);

        udpSend_Btn = (Button) findViewById(R.id.udpSend_Btn);
        udpSend_Btn.setOnClickListener(this);

        s = new UDP_Server(ipAddr, port);
        s.init();
        th_S = new Thread(s);
        th_S.start();


        c = new UDP_Client(ipAddr, port);
        th_C = new Thread(c);
    }

    @Override
    public void onClick(View v) {

        msg = msg_Et.getText().toString();

        th_S.run();

        try{
            Thread.sleep(500);
        }catch (InterruptedException e){

        }

        c.setMsg(msg);

        th_C.start();
    }
}
