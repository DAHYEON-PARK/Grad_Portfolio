package com.example.ttong.VoiceTransport;


import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Arrays;

import android.content.Context;
import android.util.Log;


public class UDP_Server implements Runnable {

    private String serverIp;
    private int serverPort;
    private InetAddress serverAddr, clientAddr;
    private int clientPort;

    private String msg;
    private final int bufSize = 30;

    DatagramSocket socket;

    UDP_Server(String ip, int port){
        serverIp = ip;
        serverPort = port;


    }

    public void init(){
        try {
            serverAddr = InetAddress.getByName(serverIp);
            Log.d("UDP", "S_serverAddr: " + serverAddr);
            socket = new DatagramSocket(serverPort, serverAddr);
            Log.d("UDP", "S: Connecting");
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void setMsg(String msg){


    }

    @Override
    public void run() {

        try {



            //while(true) {


                byte[] buf = new byte[bufSize];
                Arrays.fill(buf, (byte) 0);
                DatagramPacket packet = new DatagramPacket(buf, bufSize);
                socket.receive(packet);
                Log.d("UDP", "S: Received '" + new String(packet.getData()) + "'");


                clientAddr = packet.getAddress();
                clientPort = packet.getPort();
                Log.d("UDP", "S_clientAddr: " + clientAddr);
                Log.d("UDP", "S_clientPort: " + clientPort);

                Arrays.fill(buf, (byte) 0);
                buf = ("server 'I'm OK'").getBytes();
                packet = new DatagramPacket(buf, buf.length, clientAddr, clientPort);
                socket.send(packet);
                Log.d("UDP", "S: Send '" + new String(buf) + "'");
           // }
        } catch (Exception e) {

            Log.e("UDP", "S: Error", e);

        }
    }
}
