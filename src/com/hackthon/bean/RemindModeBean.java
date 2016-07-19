package com.hackthon.bean;

public class RemindModeBean 
{
	public static final int LOCAL = 1;
	public static final int FRIEND = 2;
	public static final int RANDOM = 3;
	
	private int remindmode  = LOCAL;

	
	public RemindModeBean(int rstragy)
	{
		remindmode = rstragy;
	}
	public void setsRemindMode(int rstragy)
	{
		remindmode = rstragy;
	}
	public int getRemindMode()
	{
		return remindmode;
	}
}
