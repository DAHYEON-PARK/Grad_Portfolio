package com.example.ttong.VoiceTransport;

/**
 * Created by cse109 on 2015-06-23.
 */

import android.app.Activity;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ttong.R;

public class UDP_Connection extends Activity implements View.OnClickListener {
    /** Called when the activity is first created. */

    TextView ip_port_Tv, msg_Tv;
    EditText msg_Et;
    Button udpSend_Btn;

    String msg;

    String ipAddr;
    int ip;
    int port=4444;

    Thread th_S, th_C;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.default_main);

        ip_port_Tv = (TextView) findViewById(R.id.info_Tv);
        WifiManager wm = (WifiManager) getSystemService(WIFI_SERVICE);
        //DhcpInfo dhcpInfo = wm.getDhcpInfo() ;  ----- last number is always 1. (190.168.0.1)
        //serverIp = dhcpInfo.gateway;
        WifiInfo wifiInfo = wm.getConnectionInfo();
        ip = wifiInfo.getIpAddress();
        ipAddr = String.format("%d.%d.%d.%d", ip & 0xff, ip >> 8 & 0xff, ip >> 16 & 0xff, ip >> 24 & 0xff);
        ip_port_Tv.setText("ip : " + ipAddr + "\nport : " + port);

        msg_Et = (EditText) findViewById(R.id.msg_Et);
        msg = msg_Et.getText().toString();

        udpSend_Btn = (Button) findViewById(R.id.udpSend_Btn);
        udpSend_Btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        UDP_Server s = new UDP_Server(msg, ipAddr, port);
        th_S = new Thread(s);
        th_S.start();

        UDP_Client c = new UDP_Client(msg, ipAddr, port);
        th_C = new Thread();
        th_C.start();
    }
}
