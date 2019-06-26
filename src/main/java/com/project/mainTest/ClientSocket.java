package com.project.mainTest;

import java.io.*;
import java.net.Socket;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class ClientSocket {
    public  static  void  main(String[]args){
        Socket socket= null;
        try {
            socket = new Socket("localhost",9999);
            OutputStream outputStream= socket.getOutputStream();// 输出流
            
            BufferedWriter bufferedWriter = new BufferedWriter( new OutputStreamWriter(outputStream));
            String str = URLEncoder.encode("HEllO SERVER", "utf-8");
            bufferedWriter.write(str);
            bufferedWriter.newLine();     
            bufferedWriter.flush(); 
           // bufferedWriter.flush();
            
            InputStream inputStream=socket.getInputStream();// 输入流
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String msg=bufferedReader.readLine();
			 msg=URLDecoder.decode(msg, "utf-8");
            System.out.println(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
