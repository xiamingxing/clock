package com.hackthon.util;

import java.util.Calendar;

import android.app.AlarmManager;
import android.content.Context;

public class SystemDayTime {
	public  SystemDayTime(){
		date  = Calendar.getInstance();		
	}
	private Calendar date  = null;
	 public String getweekday() {		
			String weekday = String.valueOf(date.get(Calendar.DAY_OF_WEEK));
			if("1".equals(weekday)){  
				weekday = "星期天"; 
			}
			else if("2".equals(weekday)){  
				weekday = "星期一";
			}
			else if("3".equals(weekday)){  
				weekday = "星期二";
			}
			else if("4".equals(weekday)){  
				weekday = "星期三";
			}
			else if("5".equals(weekday)){  
				weekday = "星期四";
			}
			else if("6".equals(weekday)){  
				weekday = "星期五";
			}
			else if("7".equals(weekday)){  
				weekday = "星期六";
			}
			return weekday;
		}
	 
	 public  String gettoday() {
		 String  year = String.valueOf(date.get(Calendar.YEAR));
		 String  month = String.valueOf(date.get(Calendar.MONTH)+1);
		 String  day = String.valueOf(date.get(Calendar.DAY_OF_MONTH));	
			String today = year+"/"+ month+ "/"+day;
			return today;
		}
	 public  String gettodaydir() {
		 String  year = String.valueOf(date.get(Calendar.YEAR));
		 String  month = String.valueOf(date.get(Calendar.MONTH)+1);
		 String  day = String.valueOf(date.get(Calendar.DAY_OF_MONTH));	
			String today = year+"_"+ month+ "_"+day;
			return today;
		}
	 public  String gettime(Context context) {
		 //AlarmManager mAlarmManager = (AlarmManager)getSystemService(Activity.ALARM_SERVICE);	
		 //AlarmManager mAlarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
		 
		// mAlarmManager.setTimeZone("GMT+08:00");
			String hour = String.valueOf(date.get(Calendar.HOUR_OF_DAY));
			int minute  = date.get(Calendar.MINUTE);
			String time = "";
			if(minute<=9)
			{
				String nowminute  = String.valueOf(minute);
				 time = hour+":0"+ nowminute; 
			}
			else
			{
				String nowminute  = String.valueOf(minute);
				 time = hour+":"+ nowminute; 
			}			
			return time;
		}
	 public String gettimedir(Context context) {
		 //AlarmManager mAlarmManager = (AlarmManager)getSystemService(Activity.ALARM_SERVICE);	
		 //AlarmManager mAlarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
		 
		// mAlarmManager.setTimeZone("GMT+08:00");
			String hour = String.valueOf(date.get(Calendar.HOUR_OF_DAY));
			int minute  = date.get(Calendar.MINUTE);
			String time = "";
			if(minute<=9)
			{
				String nowminute  = String.valueOf(minute);
				 time = hour+"_"+"0"+ nowminute; 
			}
			else
			{
				String nowminute  = String.valueOf(minute);
				 time = hour+"_"+ nowminute; 
			}			
			return time;
		}
}
