package com.training.legalmagiccrabs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Created by Zoltan_Beke on 15/04/15.
 */
public class HvacClient {
    private interface SocketExecutor {
        void execute(Object client);
    }

    public void writeToSocketAtAPort(String port, String min, String max, String out ) throws Exception {
        try (SocketWrapper socketWrapper = new SocketWrapper(5000)) {
            StringBuilder sb = new StringBuilder();
            sb.append("port=").append(port).append(";");
            sb.append("min=").append(min).append(";");
            sb.append("max=").append(max).append(";");
            sb.append("out=").append(out);

            withValidConnection((client) -> {
                String dataWritten = writeToSocket((Socket) client, sb.toString());
            });

        }
    }

    private void withValidConnection(SocketExecutor executor) throws InterruptedException, IOException {
        boolean connected = false;
        int retries = 0;
        while (!connected && retries < 5) {
            InetAddress host = InetAddress.getLocalHost();
            try (Socket client = new Socket(host.getHostName(), 5000)) {
                connected = true;
                executor.execute(client);
            } catch (ConnectException e) {
                Thread.sleep(100);
                retries++;
            }
        }
        if (retries >= 5) {
            throw new RuntimeException();
        }
    }

    private String writeToSocket(Socket client, String data) {
        try {
            PrintWriter out = new PrintWriter(client.getOutputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));

            out.println(data);
            out.flush();

            return in.readLine();
        }
        catch (Exception e) {
            throw new RuntimeException();
        }
    }

}
