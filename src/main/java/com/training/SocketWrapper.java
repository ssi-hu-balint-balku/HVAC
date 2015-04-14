package com.training;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.PrintWriter;

public class SocketWrapper implements java.lang.AutoCloseable {

    public static interface ISocketCallback {
        void lineRead(String line);
    }


    private int             port;
    private ISocketCallback callback;
    private ServerSocket    serverSocket;
    private Socket          socket;

    public SocketWrapper(int port) {
        this.port = port;
    }

    public SocketWrapper(int port, ISocketCallback callback) {
        this.port = port;
        this.callback = callback;
    }

    public void setCallback(ISocketCallback callback) {
        this.callback = callback;
    }

    public void start() {
        try {
            serverSocket = new ServerSocket(this.port);
            socket = serverSocket.accept();

            PrintWriter out = new PrintWriter(socket.getOutputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            while (true) {
                String input = in.readLine();
                if (this.callback != null) {
                    try {
                        this.callback.lineRead(input);
                    } catch (Throwable ignore) {
                        // do not fail on exceptions in the callback
                    }
                }
                out.println(input);
                out.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            System.out.println("closing");
            socket.close();
            serverSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
