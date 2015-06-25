package com.example.ttong.VoiceTransport;


import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Arrays;

import android.util.Log;


public class UDP_Client implements Runnable {

    private String serverIp;
    private int serverPort;
    private InetAddress serverAddr, clientAddr;

    private String msg;
    private final int bufSize = 30;


    UDP_Client(String ip, int port){
        serverIp = ip;
        serverPort = port;
    }


    public void setMsg(String msg){
        this.msg = msg;
    }

    @Override
    public void run() {

        try {
            serverAddr = InetAddress.getByName(serverIp);
            Log.d("UDP", "C_serverAddr: " + serverAddr);

            DatagramSocket socket = new DatagramSocket();
            Log.d("UDP", "C: Connecting");

            byte[] buf = new byte[bufSize];
            Arrays.fill(buf, (byte) 0);
            buf = ("^^" + msg).getBytes();
            DatagramPacket packet = new DatagramPacket(buf, buf.length, serverAddr, serverPort);
            socket.send(packet);
            Log.d("UDP", "C: Send '" + new String(buf) + "'");

            Arrays.fill(buf, (byte) 0);
            socket.receive(packet);
            Log.d("UDP", "C: Received '" + new String(packet.getData()) + "'");

        } catch (Exception e) {
            Log.e("UDP", "C: Error", e);
        }
    }
}