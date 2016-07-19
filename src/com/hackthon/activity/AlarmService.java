package com.hackthon.activity;

import java.io.IOException;

import com.hackthon.action.UserManageAction;
import com.hackthon.util.HttpUtils;
import com.hackthon.util.MediaPlayerUtils;
import com.hackthon.util.MyId;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Message;
import android.widget.Toast;

public class AlarmService extends Service  
{
	private UserManageAction userManage = new UserManageAction();
	String audiourl = ""; 
	
	MediaPlayerUtils mediaplayer = null;//new MediaPlayerUtils(MatchedFriendOfReminded.audiopath);
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate() 
	{
		// TODO Auto-generated method stub
		super.onCreate();
		
	}

	@Override
	public void onStart(Intent intent, int startId) 
	{
		// TODO Auto-generated method stub
		super.onStart(intent, startId);
    	
	

	}

	
}
