package com.hackthon.action;

import java.util.Timer;
import java.util.TimerTask;

import com.hackthon.bean.LoginInfo;
import com.hackthon.util.HttpUtils;

import android.os.Handler;
import android.os.Message;

public class UserManageAction 
{
	boolean IslogInOk = false;
	boolean timeout = false;
	
	public void login(LoginInfo loginfo,Handler handle)
	{
		new Thread( new dologinThread(loginfo, handle)).start();
	}
	
	class dologinThread implements Runnable
	{
		String username;
		String password;
		Handler handleToUI;
		
		public dologinThread(LoginInfo loginfo,Handler handler)
		{
			username = loginfo.getUserName();
			password = loginfo.getPassWord();
			handleToUI = handler;
		}
		@Override
		public void run() 
		{
			// TODO Auto-generated method stub
			long recode = -1;
			Timer timer = new Timer(); 
			timer.schedule(new OutTimeTask(handleToUI),  10 * 1000);
			
			while(!IslogInOk)
			{
				HttpUtils httputil = new HttpUtils();
				recode = httputil.login(username, password);
				//recode = 1;
				if(recode > 0)
				{
					IslogInOk = true;
					break;
				}
			}
			if(!timeout)
			{
				Message msg = handleToUI.obtainMessage();
				msg.arg1 = (int)recode;
				msg.sendToTarget();
			}
		}
	}
	public class OutTimeTask extends TimerTask 
	{
		Handler handleToUI = null;
		public OutTimeTask(Handler handle)
		{
			handleToUI = handle;
		}
		@Override
		public void run() 
		{
			// TODO Auto-generated method stub
			if(!IslogInOk)
			{
				IslogInOk = true;
				timeout = true;
				Message msg = handleToUI.obtainMessage();
				msg.arg1 = -2;
				msg.sendToTarget();
			}
		}
	} 
}