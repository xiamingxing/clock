
package com.hackthon.activity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.ContactsContract.CommonDataKinds.Relation;
import android.view.Menu;
import android.widget.Toast;

import com.hackthon.action.Clock;
import com.hackthon.action.RelationManageAction;
import com.hackthon.action.ServerTest;
import com.hackthon.bean.AlarmTimeBean;
import com.hackthon.bean.RemindModeBean;
import com.hackthon.bean.RemindStrategyBean;
import com.hackthon.util.CircleProgressBar;
import com.hackthon.util.HttpUtils;
import com.hackthon.util.MediaPlayerUtils;



public class TestActivity extends Activity{
    CircleProgressBar progressBar;
    RelationManageAction relation = null;
   
    AlarmTimeBean alarmti =  new AlarmTimeBean(1,40);
    RemindModeBean remindmod  = new RemindModeBean(1);
    RemindStrategyBean remindstrag = new RemindStrategyBean(1);
    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
//        MediaPlayerUtils mediaPlayer = new MediaPlayerUtils("/mnt/sdcard/你给我听好.mp3");     
//        mediaPlayer.play(0);
        Clock clock  = new Clock( alarmti
     			, remindmod
     			, remindstrag
     			, "/mnt/sdcard/你给我听好.mp3");
        
//    	Intent TargetIntent = new Intent();
//			TargetIntent.setClass(TestActivity.this, AlarmService.class);
//	    Bundle bundle  = new Bundle();
//	    bundle.puts
//		TargetIntent.putExtra("new data", value);
//        startService(TargetIntent);
        
        
        
        Handler mhandlerlist = new Handler() 
    	{
            @Override
            public void handleMessage(Message msg) 
            {
            	Toast.makeText(TestActivity.this, msg.obj.toString(), Toast.LENGTH_SHORT).show();
            }
    	};
        mhandlerlist = new Handler() 
		{
	        @Override
	        public void handleMessage(Message msg) 
	        {
	        	if(isFinishing())
	        	{
	        		return;
	        	}
	            try 
	            {
	            	switch(msg.what)
	            	{
	            		case 1:
	            		{
	            	
	            			break;
	            		}
	            		case 2:
	            		{
	            			HashMap<String,String> Friend_And_SubFriend_AlarmList = relation.getFriendClockList();
	            		
	            			Iterator<String> iter = Friend_And_SubFriend_AlarmList.keySet().iterator();
	            			while (iter.hasNext()) 
	            			{
		            			String key = iter.next();
		            			String val = Friend_And_SubFriend_AlarmList.get(key);
	            			}
	            			int cnt = Friend_And_SubFriend_AlarmList.size();
	            			break;
	            		}
	            		case 3:
	            		{
    	            			Toast.makeText(TestActivity.this, "receive a client request!", Toast.LENGTH_SHORT).show();
	            			break;
	            		}
	            		case 4:
	            		{
//    	            			Toast.makeText(SendHotSpotActivity.this, "file:" + msg.getData().getString("filesendok") + "send ok!", Toast.LENGTH_SHORT).show();
	            			break;
	            		}
	            		case 5:
	            		{
    	            			Toast.makeText(TestActivity.this, "upload alarm request success！", Toast.LENGTH_SHORT).show();
	            			break;
	            		}
	              		case 6:
	            		{
    	            			Toast.makeText(TestActivity.this, "listen server success！", Toast.LENGTH_SHORT).show();
	            			break;
	            		}
	            		default:
	            		{
	            			
	            		}
	            	}                                                                 
	            } 
	            catch (Exception ee) 
	            {
	                ee.printStackTrace();
	            }
	        }
	    };
    	
    	//获得好友列表测试
    	relation = new RelationManageAction(mhandlerlist);
    	AlarmTimeBean alarmbean = new AlarmTimeBean(20,10);
    	String mediapath = "";
    	relation.ListenServer(4, mediapath);
    	//HashMap<String,AlarmTimeBean> Friend_And_SubFriend_AlarmList = relation.getFriendClockList();

    }   
    
    
}
