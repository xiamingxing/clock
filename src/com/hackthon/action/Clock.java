package com.hackthon.action;

import java.io.IOException;
import java.text.SimpleDateFormat;

import android.os.Message;

import com.hackthon.bean.AlarmTimeBean;
import com.hackthon.bean.RemindModeBean;
import com.hackthon.bean.RemindStrategyBean;
import com.hackthon.util.HttpUtils;

public class Clock
{
	private boolean isopen =  false;
	
	private ClockRemindAction clock_remind_action = null;
	
	private AlarmTimeBean alarmtime = null; 
	private String audioPath = "";
//	SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");//HH:mm:ss.SSS
//	System.out.println(df.format(new Date()));// new Date()
	
 	public Clock(AlarmTimeBean alarmt
 			,RemindModeBean remindmod
 			,RemindStrategyBean remindstrag
 			,String mediapath
 			)
 	{
 		clock_remind_action = new ClockRemindAction(remindstrag,remindmod, mediapath);
 		alarmtime  = alarmt;
 	}
 	AlarmTimeBean getAlarmTime()
 	{
 		return alarmtime;
 	}
	void setRemindMode(RemindModeBean remindmod)
	{
		if(null != clock_remind_action)
		{
			clock_remind_action.setRemindMode(remindmod);
		}
	}
	void setRemindStrategy(RemindStrategyBean remindstrag)
	{
		if(null != clock_remind_action)
		{
			clock_remind_action.setsRemindTrategy(remindstrag);
		}
	}
	void Remind()
	{
		if(null != clock_remind_action)
		{
			clock_remind_action.executeRemindAction();
		}
	}
	void StartListen_FriendRemind()
	{
		HttpUtils httputil = new HttpUtils();
		new Thread
		(
				new Runnable()
				{
					@Override
					public void run() 
					{
						// TODO Auto-generated method stub						
						try
						{
							Thread.sleep(1000*10);
							
						}
						catch(InterruptedException e)
						{
							e.printStackTrace();
						}
						
					}
					
				}
		).start();
 
	}
}
