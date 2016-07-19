package com.hackthon.util;

import android.media.MediaPlayer;

public class MediaPlayerUtils
{
	private String mediapath = null;
	private MediaPlayer mediaPlayer = null;
	
	public MediaPlayerUtils(String filepath)
	{
		mediapath = filepath;
		mediaPlayer = new MediaPlayer();
	}
	 /** 
	  * �������� 
	  * @param position 
	  */  
	public void play(int position) 
	{  
		try {  
	         	mediaPlayer.reset();//恢复到初始状态
	         	mediaPlayer.setDataSource(mediapath);  
	         	mediaPlayer.prepare();  //缓冲 
	         	//mediaPlayer.setOnPreparedListener(new PreparedListener(position));//注册一个监听器
	         	mediaPlayer.setLooping(false);
	         	mediaPlayer.start();
	       }  
	       catch (Exception e) 
	       {  
	            e.printStackTrace();  
	       }  
	}  
	/** 
	* 停止音乐 
	*/  
	public void stop()
	{  
	   if(mediaPlayer != null) 
	   {  
	      mediaPlayer.stop();  
	         try 
	         {  
	            mediaPlayer.prepare(); // 在调用stop后如果需要再次通过start进行播放,需要之前调用prepare函数  
	         } 
	         catch (Exception e) 
	         {  
	             e.printStackTrace();  
	         }  
	    }  
	 }  
	 
}
