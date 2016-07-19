package com.hackthon.action;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

public class ServerTest extends Thread
{
	private String ip;
	private int port;
	private String TAG = "SocClientThread";
	public Socket client = null;
	public boolean isRun = true;
	Handler handlerofUI;
	Context ctx;
	SharedPreferences sp;
	private int timeout = 10000;
	boolean haswrite = false;
	boolean hasread = false;
	 public ServerTest(Handler handlerin, Context context,String serverip) 
	 {
		    handlerofUI = handlerin;
	        ctx = context;
	        ip = serverip;
	        port = 80;
	        Log.i(TAG, "创建线程socket");
	 }
	 /**
	  * 连接socket服务器
	  * 
	  */
	 public void conn() 
	 {
	        try 
	        {
	            client = new Socket(ip, port);
	            client = new Socket(ip, port);
	            client = new Socket(ip, port);
	            client = new Socket(ip, port);
	        } 
	        catch (UnknownHostException e) {
	            Log.i(TAG, "连接错误UnknownHostException 重新获取");
	            e.printStackTrace();
	            conn();
	        } catch (IOException e) {
	            Log.i(TAG, "连接服务器io错误");
	            e.printStackTrace();
	        } catch (Exception e) {
	            Log.i(TAG, "连接服务器错误Exception" + e.getMessage());
	            e.printStackTrace();
	        }
	 }
	 /**
	  * 实时接收数据
	  */
	 @Override
	 public void run() 
	 {
		 
        conn();

        while (true) 
        {
      
        }
	 }
	 /**
     * 发送数据
     * 
     * @param mess
     */
    public void Send(String mess) 
    {
        try 
        {
            if (client != null) 
            {
    			//从Socket当中得到OutputStream
    			OutputStream outputStream = client.getOutputStream();
    	
    		    outputStream.write(mess.getBytes(), 0, mess.length());
    		    outputStream.flush();
    		    haswrite = true;
    		    close();
            }
            else
            {
                conn();
            }

        } catch (Exception e) {
            Log.i(TAG, "send error");
            e.printStackTrace();
        } 
    }
    
	/**
     * 关闭连接
     */
    public void close() 
    {
        try 
        {
            if (client != null) 
            {
                Log.i(TAG, "close client");
                client.close();
            }
        }
        catch (Exception e) 
        {
            Log.i(TAG, "close err");
            e.printStackTrace();
        }
    }   
}
