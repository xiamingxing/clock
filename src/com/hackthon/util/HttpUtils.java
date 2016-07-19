package com.hackthon.util;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;

import com.hackthon.bean.AlarmTimeBean;


public class HttpUtils
{
	//GET
	public static final String GET_URL_LOGIN = "http://182.92.11.204/webserver/index.php";
	public static final String GET_URL_READFRIENDS = "http://182.92.11.204/webserver/readfriend.php";
	public static final String GET_URL_READFRIEND_ALARM_LIST = "http://182.92.11.204/webserver/readfriendtasklist.php";
	public static final String GET_URL_WANTTO_REMIND_OTHER_LIST = "http://182.92.11.204/webserver/wantoremindotherlist.php";
	public static final String GET_URL_RemindedRequestToServerGet = "http://182.92.11.204/webserver/remindedrequest.php";
	
	public static final String GET_URL_ListenServerGet = "http://182.92.11.204/webserver/GET_URL_RemindedRequestToServerGet.php";
	//POST
	public static final String GET_URL_WAKE_SOMEONE_UP_REQUEST = "http://182.92.11.204/webserver/UpLoad.php";

	//
	private SystemDayTime date = null;
	
	public long login(String user,String pass) 
	{
		long rel  = -1;
        // 拼凑get请求的URL字串，使用URLEncoder.encode对特殊和不可见字符进行编码
		try
		{
	        String getURL = GET_URL_LOGIN + "?username="
	              + URLEncoder.encode(user, "utf-8");	
	        
	        getURL += ("&&password=" + URLEncoder.encode(pass, "utf-8"));
	        
	        String restr = readContentFromGet(getURL);
	        rel =  Integer.parseInt(restr);
	       
        }
		catch(UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		 return rel;
	}
	//想要被叫  
	public void WantTo_RemindOther_FriendsList_FromGet(
			HashMap<String,String> Want_To_Remind_Others_List,
			int userid)throws IOException
	{
	 	String getURL = GET_URL_WANTTO_REMIND_OTHER_LIST + "?id="
	              + URLEncoder.encode(userid+"", "utf-8");	
	 	
       URL getUrl = new URL(getURL);
       // 根据拼凑的URL，打开连接，URL.openConnection函数会根据URL的类型，
       // 返回不同的URLConnection子类的对象，这里URL是一个http，因此实际返回的是HttpURLConnection
       HttpURLConnection connection = (HttpURLConnection) getUrl
              .openConnection();
       // 取得输入流，并使用Reader读取
       BufferedReader reader = new BufferedReader(new InputStreamReader(
              connection.getInputStream()));
       String line;
       while ((line = reader.readLine()) != null) 
       {
    	   Want_To_Remind_Others_List.put
    	   (
    			   line.substring(0,line.indexOf(';')), 
    			   line.substring(line.indexOf(';')+1)
    	   );
       }
       reader.close();
       //断开连接
       connection.disconnect();
	}
	//轮询服务器
	public String ListenServer(int userid,String audiourl)throws IOException
	{
	 	String getURL = GET_URL_ListenServerGet + "?id="
	              + URLEncoder.encode(userid+"", "utf-8");	
	 	
	    URL getUrl = new URL(getURL);
	    // 根据拼凑的URL，打开连接，URL.openConnection函数会根据URL的类型，
	    // 返回不同的URLConnection子类的对象，这里URL是一个http，因此实际返回的是HttpURLConnection
	    HttpURLConnection connection = (HttpURLConnection) getUrl
	            .openConnection();

        // 取得输入流，并使用Reader读取
        BufferedReader reader = new BufferedReader(new InputStreamReader(
              connection.getInputStream()));
        String line;
        int remoteid = -1;
        int cnt = 0;
        String remoteurl = "";
        String otherusername = "";
	    while ((line = reader.readLine()) != null)
	    {
	    	if(0 == cnt)
	    	{
	    		if(line.equals("null"))
		    	{
	    			remoteid = -1;
		    		break;
		    	}
	    		else 
	    		{
	    			otherusername = line;
	    			remoteid = 1;
	    		}
	    	}
	    	else if(cnt == 1)
	    	{
	    		remoteurl  = line;
	    	}
	    	cnt++;
	    	
	    }
	    reader.close();
	    connection.disconnect();
	    
	    if(remoteid >= 0)
	    {
	  
	        downFile(audiourl,remoteurl);
	    }
	
	    connection.disconnect();
	    return otherusername;
	}
	
	//想要被叫  随机（有服务器随机选择推荐）
	//返回主叫好友的用户名
	public void RemindedRequestToServerGet(int userid,AlarmTimeBean alarmtime)throws IOException
	{
	 	String getURL = GET_URL_RemindedRequestToServerGet + "?id="
	              + URLEncoder.encode(userid+"", "utf-8") + "&&" + "clocktime="  +alarmtime.getHour()+":"+alarmtime.getMinute();	
	 	
      URL getUrl = new URL(getURL);
      // 根据拼凑的URL，打开连接，URL.openConnection函数会根据URL的类型，
      // 返回不同的URLConnection子类的对象，这里URL是一个http，因此实际返回的是HttpURLConnection
      HttpURLConnection connection = (HttpURLConnection) getUrl
              .openConnection();
      // 取得输入流，并使用Reader读取
      BufferedReader reader = new BufferedReader(new InputStreamReader(
              connection.getInputStream()));
      String line;
      while ((line = reader.readLine()) != null)
      {
    	  
      }
      reader.close();
      //断开连接
      connection.disconnect();
	}
	//主叫 读取有被叫醒需求的好友列表
	public void readFriend_And_SubFriend_AlarmList_FromGet
	(
			HashMap<String,String> Friend_And_SubFriend_AlarmList,
			int userid)throws IOException
	{
	 	String getURL = GET_URL_READFRIEND_ALARM_LIST + "?id="
	              + URLEncoder.encode(userid+"", "utf-8");	
	 	
       URL getUrl = new URL(getURL);
       // 根据拼凑的URL，打开连接，URL.openConnection函数会根据URL的类型，
       // 返回不同的URLConnection子类的对象，这里URL是一个http，因此实际返回的是HttpURLConnection
       HttpURLConnection connection = (HttpURLConnection) getUrl
              .openConnection();
       // 取得输入流，并使用Reader读取
       BufferedReader reader = new BufferedReader(new InputStreamReader(
              connection.getInputStream()));
       String line;
       while ((line = reader.readLine()) != null) 
       {
    	   if(!line.substring(0,line.indexOf(';')).equals(""))
		   {
	    	   Friend_And_SubFriend_AlarmList.put
	    	   (
	    			   line.substring(0,line.indexOf(';')), 
	    			   line.substring(line.indexOf(';')+1)
	    	   );
    	   }
       }
       reader.close();
       //断开连接
       connection.disconnect();
	}
	//读取好友列表
	public void readFriendListFromGet(ArrayList<String> friendslist,int userid) throws IOException
	{
	 	String getURL = GET_URL_READFRIENDS + "?id="
	              + URLEncoder.encode(userid+"", "utf-8");	
	 	
        URL getUrl = new URL(getURL);
        // 根据拼凑的URL，打开连接，URL.openConnection函数会根据URL的类型，
        // 返回不同的URLConnection子类的对象，这里URL是一个http，因此实际返回的是HttpURLConnection
        HttpURLConnection connection = (HttpURLConnection) getUrl
                .openConnection();
        // 取得输入流，并使用Reader读取
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                connection.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) 
        {
        	friendslist.add(line);
        }
        reader.close();
        //断开连接
        connection.disconnect();
	  }

	
	 public String readContentFromGet(String getURL) throws IOException
	 {

		 	StringBuffer sb = new StringBuffer();
	        URL getUrl = new URL(getURL);
	        // 根据拼凑的URL，打开连接，URL.openConnection函数会根据URL的类型，
	        // 返回不同的URLConnection子类的对象，这里URL是一个http，因此实际返回的是HttpURLConnection
	        HttpURLConnection connection = (HttpURLConnection) getUrl
	                .openConnection();
	        // 取得输入流，并使用Reader读取
	        BufferedReader reader = new BufferedReader(new InputStreamReader(
	                connection.getInputStream()));
	        String lines;
	        while ((lines = reader.readLine()) != null) 
	        {
	        	sb.append(lines);
	        }
	        reader.close();
	        //断开连接
	        connection.disconnect();
	        return sb.toString();
	 }
	 //上传选中的有被叫需求的好友的id、自己的ID、音频文件
	 public void UpLoad_RemindRequest_FromPost(String filename,int userid,int matcheduser_id) throws IOException
	 {
		   String GetUrl = GET_URL_WAKE_SOMEONE_UP_REQUEST + "?id="
		              + URLEncoder.encode(userid+"", "utf-8") + "&&" + "sendtoid=" + matcheduser_id;				   
	        String end = "\r\n";
	        String twoHyphens = "--";
	        String boundary = "******";
	        try
	        {
	          URL url = new URL(GetUrl);
	          HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
	          // 璁剧疆姣忔浼犺緭鐨勬祦澶у皬锛屽彲浠ユ湁鏁堥槻姝㈡墜鏈哄洜涓哄唴瀛樹笉瓒冲穿婧�          // 姝ゆ柟娉曠敤浜庡湪棰勫厛涓嶇煡閬撳唴瀹归暱搴︽椂鍚敤娌℃湁杩涜鍐呴儴缂撳啿鐨�HTTP 璇锋眰姝ｆ枃鐨勬祦銆�          httpURLConnection.setChunkedStreamingMode(128 * 1024);// 128K
	          // 鍏佽杈撳叆杈撳嚭娴�          httpURLConnection.setDoInput(true);
	          httpURLConnection.setDoOutput(true);
	          httpURLConnection.setUseCaches(false);
	          // 浣跨敤POST鏂规硶
	          httpURLConnection.setRequestMethod("POST");
	          httpURLConnection.setRequestProperty("Connection", "Keep-Alive");
	          httpURLConnection.setRequestProperty("Charset", "UTF-8");
	          httpURLConnection.setRequestProperty("Content-Type",
	              "multipart/form-data;boundary=" + boundary);
	          DataOutputStream dos = new DataOutputStream(
	              httpURLConnection.getOutputStream());
	          dos.writeBytes(twoHyphens + boundary + end);
	           dos.writeBytes("Content-Disposition: form-data; name=\"file\"; filename=\""
	              + filename.substring(filename.lastIndexOf("/") + 1)+ "\""+ end);
	          dos.writeBytes(end);
	          FileInputStream fis = new FileInputStream(filename);
	          byte[] buffer = new byte[8192]; // 8k
	          int count = 0;
	          while ((count = fis.read(buffer)) != -1)
	          {
	            dos.write(buffer, 0, count);
	          }
	          fis.close();
	          dos.writeBytes(end);
	          dos.writeBytes(twoHyphens + boundary + twoHyphens + end);
	          dos.flush();
	          InputStream is = httpURLConnection.getInputStream();
	          InputStreamReader isr = new InputStreamReader(is, "utf-8");
	          BufferedReader br = new BufferedReader(isr);
	          String result = br.readLine();
	         // Toast.makeText(activity, result, Toast.LENGTH_LONG).show();
	          dos.close();
	          is.close();
	        } 
	        catch (Exception e)
	        {
	          e.printStackTrace();
	        }

	 }
	public int downFile(String fileName,String audiourl) 
	{
		 File file = null;
         OutputStream output = null;
         FileUtils fileUtils = null;
         int readlen  = 0;
         byte buffer [] = new byte[1024*4];
         InputStream inputstream = null;
         HttpURLConnection connection = null;
         String mFilepath = date.gettodaydir() + ".amr";
         try
         {
        	URL getUrl = new URL(audiourl);
        	connection = (HttpURLConnection) getUrl
       	            .openConnection();
         	
            inputstream =  connection.getInputStream();

    
			fileUtils = new FileUtils();
			
			fileName = fileUtils.getSDPATH()  +  mFilepath;
		
			file = fileUtils.creatSDFile(mFilepath);
			
			output = new FileOutputStream(file);
			while((readlen=inputstream.read(buffer)) != -1)
			{
				output.write(buffer, 0, readlen);
			}
			output.flush();
			try
			{
				if(output!=null)
				{
					output.close();
				}
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
		 }
		 catch (Exception e) 
		 {
			e.printStackTrace();
		 	return -1;
		 } 
		 finally
		 {
			try
			{
				if(null != inputstream)
				{
					inputstream.close();
				}
				connection.disconnect();
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
		 }
		 return 0;
	}
}
