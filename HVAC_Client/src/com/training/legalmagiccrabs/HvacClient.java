package com.training.legalmagiccrabs;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Created by Zoltan_Beke on 15/04/15.
 */
public class HvacClient {

    public void writeToSocketAtAPort(String min, String max, String outvalue ) throws Exception {

        StringBuilder sb = new StringBuilder();
        sb.append("min=").append(min).append(";");
        sb.append("max=").append(max).append(";");
        sb.append("out=").append(outvalue).append("\n");
        System.out.println("sending: " + sb.toString());


        InetAddress host = InetAddress.getLocalHost();
        Socket socket = null;
        ObjectOutputStream oos = null;
        socket = new Socket("localhost", 5000);


        oos = new ObjectOutputStream(socket.getOutputStream());
        System.out.println("Sending request to Socket Server");
        oos.writeObject(sb.toString());
        oos.flush();

        oos.close();

    }



}
