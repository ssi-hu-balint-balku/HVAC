package com.training;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.Socket;

import static org.junit.Assert.assertEquals;

public class SocketWrapperTest {

    @Test
    public void ItCanStartASocketAtAPort() throws Exception {
        try (SocketWrapper socketWrapper = new SocketWrapper(5000)) {
            startSocket(socketWrapper);
            withValidConnection((client) -> {
                String dataWritten = writeToSocket((Socket) client, "Test\n");
                assertEquals("Test", dataWritten);
            });
        }
    }

    private interface SocketExecutor {
        void execute(Object client);
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

    private void startSocket(SocketWrapper socket) {
        new Thread(socket::start).start();
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
