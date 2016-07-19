package com.hackthon.action;

import java.util.Timer;
import java.util.TimerTask;



import com.hackthon.bean.RemindModeBean;
import com.hackthon.bean.RemindStrategyBean;
import com.hackthon.util.MediaPlayerUtils;

public class ClockRemindAction 
{
	
	
	private RemindModeBean remindmode = new RemindModeBean(RemindModeBean.LOCAL);
	private RemindStrategyBean remindstrategy  = new RemindStrategyBean(RemindStrategyBean.AFTER_SOME_TIME);
	private MediaPlayerUtils mediaPlayer =  null;//new MediaPlayer();//媒体播放器对象
	
	private String mediapath = null;
	
	private long after_some_time  = 30;
	
	public ClockRemindAction(RemindStrategyBean rstragy,RemindModeBean remode, String mediap)
	{
		remindstrategy  = rstragy;
		remindmode = remode;
		mediapath = mediap;
	}
	public void setRing_last_Time(long duringtime)
	{
		after_some_time = duringtime;
	}
	public void setsRemindTrategy(RemindStrategyBean rstragy)
	{
		remindstrategy = rstragy;
	}
	public RemindStrategyBean getRemindTrategy()
	{
		return remindstrategy;
	}
	public void setRemindMode(RemindModeBean remode)
	{
		remindmode = remode;
	}
	public RemindModeBean getRemindMode()
	{
		return remindmode;
	}
	
	public void executeRemindAction()
	{
		mediaPlayer = new MediaPlayerUtils(mediapath);
		mediaPlayer.play(0);
	
		switch(remindstrategy.getRemindTrategy())
		{
			case RemindStrategyBean.AFTER_SOME_TIME: 
			{
				Timer timer = new Timer(); 
				timer.schedule(new MediaFinishTask(),  after_some_time * 1000);
				break;
			}
			case RemindStrategyBean.AFTER_SOME_SPORT:
			{
				//先播放一段
				//来自谁谁谁的留言 或者 谁谁谁给你点的歌
				break;
			}
			case RemindStrategyBean.AFTER_SOME_PHOTO:
			{
				//先播放一段
				//来自谁谁谁的留言 或者 谁谁谁给你点的歌
				break;
			}
		}
	}
	public void finishRemindAction()
	{
		
	}
	//指定时间后停止播放相关音频、视频文件
	public class MediaFinishTask extends TimerTask 
	{
		@Override
		public void run() 
		{
			// TODO Auto-generated method stub
			if(mediaPlayer != null)
			{
				mediaPlayer.stop();
			}
		}
	} 
}
