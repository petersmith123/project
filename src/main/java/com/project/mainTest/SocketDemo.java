package com.project.mainTest;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class SocketDemo {
    public static void main(String[] args) {


        try {
            ServerSocket socket = new ServerSocket(9999);
            Socket client = socket.accept();
            System.out.println("client connection");
            InputStream inputStream = client.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader((inputStream)));
            String msg= bufferedReader.readLine();
            msg = URLDecoder.decode(msg,"UTF-8");
            System.out.println(msg);
            OutputStream outputStream = client.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter( new OutputStreamWriter(new DataOutputStream(outputStream)));
            String str= URLEncoder.encode("欢迎访问", "utf-8");
            bufferedWriter.write(str);
            bufferedWriter.flush();
            client.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
