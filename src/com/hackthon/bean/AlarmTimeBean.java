package com.hackthon.bean;

public class AlarmTimeBean 
{
	private int Hour = -1;
	private int Minute = -1;
	public AlarmTimeBean(int hour,int minute)
	{
		Hour = hour;
		Minute = minute;
	}
	public int getMinute()
	{
		return Minute;
	}
	public int getHour()
	{
		return Hour;
	}
	public void setMinute(int minute)
	{
		Minute = minute;
	}
	public void setHour(int hour)
	{
		Hour = hour;
	}
	public static AlarmTimeBean getAlarmTimeFormat(String str)//xx:xx
	{
		return new AlarmTimeBean(  
				Integer.parseInt(str.substring(0,str.indexOf(':'))), 
				Integer.parseInt(str.substring(str.indexOf(':')+1))  );
	}
}
