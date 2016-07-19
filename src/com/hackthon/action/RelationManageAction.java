package com.hackthon.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import android.os.Handler;
import android.os.Message;

import com.hackthon.bean.AlarmTimeBean;
import com.hackthon.util.HttpUtils;

public class RelationManageAction 
{
	private ArrayList<String> FriendsList  = new ArrayList<String>();
	private HashMap<String,String> Friend_And_SubFriend_AlarmList  = new HashMap<String,String>();//<用户名，ID>
	private HashMap<String,String> Want_To_Remind_Others_List  = new HashMap<String,String>();//<用户名，叫醒别人的文件名>
	
	
	
	private boolean IsFirstRead = true;
	private Handler handleOfUI = null;
	public Object WakeUpSomeoneRequestToServer;
	
	public RelationManageAction( Handler handle )
	{
		handleOfUI = handle;
	}
	
	//从服务器读好友列表进行展示
	public void  ReadFriendsFromServer(final long userid)
	{
		new Thread
		(
				new Runnable()
				{

					@Override
					public void run() 
					{
						// TODO Auto-generated method stub
						HttpUtils httputil = new HttpUtils();
						try
						{
							httputil.readFriendListFromGet(FriendsList,(int)userid);
							Message msg = new Message();
							msg.what = 1;
							handleOfUI.sendMessage(msg);
						}
						catch(IOException e)
						{
							e.printStackTrace();
						}
					}
					
				}
		).start();
	}
	public ArrayList<String> getFriendList()
	{
		return FriendsList;
	}
	//从服务器获取好友以及间接好友的闹钟列表
	public void ReadFriendClockListFromServer(final long userid)
	{
		new Thread
		(
				new Runnable()
				{
					@Override
					public void run() 
					{
						// TODO Auto-generated method stub
						HttpUtils httputil = new HttpUtils();
						try
						{
							httputil.readFriend_And_SubFriend_AlarmList_FromGet(Friend_And_SubFriend_AlarmList,(int)userid);
							Message msg = new Message();
							msg.what = 2;
							handleOfUI.sendMessage(msg);
						}
						catch(IOException e)
						{
							e.printStackTrace();
						}
					}
					
				}
		).start();

	}
	public HashMap<String,String> getFriendClockList()
	{
		return Friend_And_SubFriend_AlarmList;
	}
	//像服务器上传被叫醒的请求
	public void RemindedRequestToServer(final long userid,final AlarmTimeBean alarmtime)
	{
		new Thread
		(
				new Runnable()
				{
					@Override
					public void run() 
					{
						// TODO Auto-generated method stub
						HttpUtils httputil = new HttpUtils();
						try
						{
							httputil.RemindedRequestToServerGet((int)userid,alarmtime);
							Message msg = new Message();
							msg.what = 5;
							handleOfUI.sendMessage(msg);
						}
						catch(IOException e)
						{
							e.printStackTrace();
						}
					}					
				}
		).start();
	}

	
    //读取
	//从服务器获取希望叫醒别人的的好友及间接好友的列表
	public void ReadRemindOtherListFromServer(final long userid)
	{
		new Thread
		(
				new Runnable()
				{
					@Override
					public void run() 
					{
						// TODO Auto-generated method stub
						HttpUtils httputil = new HttpUtils();
						try
						{
							httputil.WantTo_RemindOther_FriendsList_FromGet(Want_To_Remind_Others_List,(int)userid);
							Message msg = new Message();
							msg.what = 4;
							handleOfUI.sendMessage(msg);
						}
						catch(IOException e)
						{
							e.printStackTrace();
						}
					}
					
				}
		).start();
	}
	public HashMap<String,String> getWantToRemindOtherList()
	{
		return Friend_And_SubFriend_AlarmList;
	}

	//我要叫醒
	public void WakeUpSomeoneRequestToServer(final long userid,final String mediapath,final long matchedid)
	{
		new Thread
		(
				new Runnable()
				{
					@Override
					public void run() 
					{
						// TODO Auto-generated method stub
						HttpUtils httputil = new HttpUtils();
						try
						{
							httputil.UpLoad_RemindRequest_FromPost(mediapath, (int)userid, (int)matchedid);
							Message msg = new Message();
							msg.what = 3;
							handleOfUI.sendMessage(msg);
						}
						catch(IOException e)
						{
							e.printStackTrace();
						}
					}
					
				}
		).start();

	}
    
	
	
	//下载
	public void ListenServer(final long userid,final String mediapath )
	{
		new Thread
		(
				new Runnable()
				{
					@Override
					public void run() 
					{
						// TODO Auto-generated method stub
						HttpUtils httputil = new HttpUtils();
						//try
						{
							int remoteid =  httputil.downFile("rwc.txt","http://182.92.11.204/upload/aliyun.txt");   //ListenServer((int)userid, mediapath);
							Message msg = new Message();
							msg.what = 6;
							handleOfUI.sendMessage(msg);
						}
//						catch(IOException e)
//						{
//							e.printStackTrace();
//						}
					}
					
				}
		).start();

	}

	public void posttest(final long userid,final String mediapath )
	{
		new Thread
		(
				new Runnable()
				{
					@Override
					public void run() 
					{
						// TODO Auto-generated method stub
						HttpUtils httputil = new HttpUtils();
						try
						{
						    httputil.UpLoad_RemindRequest_FromPost(mediapath, (int) userid, 1) ;//((int)userid, mediapath);
							Message msg = new Message();
							msg.what = 6;
							handleOfUI.sendMessage(msg);
						}
						catch(IOException e)
						{
							e.printStackTrace();
						}
					}
					
				}
		).start();

	}
	public void searchserver(final long userid,final String mediapath)
	{
		new Thread
		(
				new Runnable()
				{
					@Override
					public void run() 
					{
						// TODO Auto-generated method stub
						HttpUtils httputil = new HttpUtils();
						try
						{
							String otherusername  = httputil.ListenServer((int)userid, mediapath);
							Message msg = new Message();
							msg.obj  = otherusername;
							msg.what = 7;
							handleOfUI.sendMessage(msg);
						}
						catch(IOException e)
						{
							e.printStackTrace();
						}
						
						
					}
					
				}
		).start();

	}
	
	//编辑好友列表
	public void updateFriend()
	{
		
	}
	public void DeleteFriend(String usrname)
	{
		
	}
	public void AddFriend(String usrname)
	{
		
	}
}
