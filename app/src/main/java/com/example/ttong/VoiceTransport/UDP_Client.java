package com.example.ttong.VoiceTransport;

/**
 * Created by cse109 on 2015-06-23.
 */

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import android.util.Log;

public class UDP_Client implements Runnable {

    String clientIp;
    int clientPort;
    String msg;




    UDP_Client(String msg, String ip, int port){
        this.msg = msg;
        clientIp = ip;
        clientPort = port;
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        try {
            /* Retrieve the ServerName */
            InetAddress serverAddr = InetAddress.getByName(clientIp);

            Log.d("UDP", "C_Addr : "+serverAddr);
            Log.d("UDP", "C: Connecting...");
            /* Create new UDP-Socket */
            DatagramSocket socket = new DatagramSocket();

            /* Prepare some data to be sent. */
            byte[] buf = new byte[30];

            buf = ("client, " + msg).getBytes();

            /* Create UDP-packet with
             * data & destination(url+port) */
            DatagramPacket packet = new DatagramPacket(buf, buf.length, serverAddr, clientPort);
            Log.d("UDP", "C: Sending: '" + new String(buf) + "'");

            /* Send out the packet */
            socket.send(packet);
            Log.d("UDP", "C: Sent.");
            Log.d("UDP", "C: Done.");

            socket.receive(packet);
            Log.d("UDP", "C: Received: '" + new String(packet.getData()) + "'");

        } catch (Exception e) {
            Log.e("UDP", "C: Error", e);
        }
    }
}