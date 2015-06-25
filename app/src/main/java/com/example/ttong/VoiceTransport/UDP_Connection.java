package com.example.ttong.VoiceTransport;

/**
 * Created by cse109 on 2015-06-23.
 */

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
    /** Called when the activity is first created. */

    TextView ip_port_Tv, msg_Tv;
    EditText msg_Et;
    Button udpSend_Btn;

    String msg;

    int ip;
    int port=4444;
    String ipAddr;

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

        udpSend_Btn = (Button) findViewById(R.id.udpSend_Btn);
        udpSend_Btn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        msg = msg_Et.getText().toString();

        Log.d("UDP_server", ipAddr + " & " + port + " & " + msg);

        //UDP_Server s = new UDP_Server(msg, "127.0.0.1", port);
        //UDP_Client c = new UDP_Client(msg, "127.0.0.1", port);
        UDP_Server s = new UDP_Server(msg, ipAddr, port);
        UDP_Client c = new UDP_Client(msg, ipAddr, port);

        //new Thread(new UDP_Server(msg,ipAddr,port)).start();

        th_S = new Thread(s);
        th_S.start();

        //new Thread(s).start();
        //new Thread(c).start();

        try{
            Thread.sleep(500);
        }catch (InterruptedException e){

        }

        //new Thread(new UDP_Client(msg,ipAddr,port)).start();

        th_C = new Thread(c);
        th_C.start();
    }
}
