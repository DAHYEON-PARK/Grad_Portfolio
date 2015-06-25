package com.example.ttong.VoiceTransport;

/**
 * Created by cse109 on 2015-06-23.
 */

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import android.util.Log;

public class UDP_Server implements Runnable {

    String serverIp;
    int serverPort;
    String msg;

    //public static String serverIp = "";
    //public static int serverPort = 0;

    UDP_Server(String msg, String ip, int port){
        this.msg = msg;
        serverIp = ip;
        serverPort = port;
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        try {
            /* Retrieve the ServerName */
            InetAddress serverAddr = InetAddress.getByName(serverIp);

            Log.d("UDP", "S_Addr : "+serverAddr);
            Log.d("UDP", "S: Connecting...");
            /* Create new UDP-Socket */
            DatagramSocket socket = new DatagramSocket(serverPort, serverAddr);

            /* By magic we know, how much data will be waiting for us */

            byte[] buf = new byte[30];

            /* Prepare a UDP-Packet that can
             * contain the data we want to receive */
            DatagramPacket packet = new DatagramPacket(buf, buf.length);
            Log.d("UDP", "S: Receiving...");

            /* Receive the UDP-Packet */
            socket.receive(packet);

            Log.d("UDP", "S: Received: '" + new String(packet.getData()) + "'");
            Log.d("UDP", "S: Done.");

            InetAddress clientAddr = packet.getAddress();
            int clientPort = packet.getPort();

            Log.d("UDP", "S_clientAddr : "+clientAddr);
            Log.d("UDP", "S_clientPort : "+clientPort);

            //String s = "Thanks";
            buf = ("server, "+msg).getBytes();
            packet = new DatagramPacket(buf, buf.length, clientAddr, clientPort);

            Log.d("UDP", "S: Sending: '" + new String(buf) + "'");
            socket.send(packet);

        } catch (Exception e) {
            Log.e("UDP", "S: Error", e);
        }
    }
}
