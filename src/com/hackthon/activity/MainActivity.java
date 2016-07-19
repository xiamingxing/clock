package com.hackthon.activity;

import java.io.IOException;

import com.hackthon.action.RelationManageAction;
import com.hackthon.action.UserManageAction;
import com.hackthon.util.ErrInfo;
import com.hackthon.util.HttpUtils;
import com.hackthon.util.MediaPlayerUtils;
import com.hackthon.util.MyId;
import com.hackthon.util.SystemDayTime;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AnimationUtils;
import android.webkit.WebStorage.Origin;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity{
    private Intent originIntent = null;
    private Bundle originBundle = null;
    private int userId = 0;
    SystemDayTime dt = null;
    Handler mhandlerlist  = null;
    private UserManageAction userManage = new UserManageAction();
	String audiourl = ""; 
	RelationManageAction relation = null;
	MediaPlayerUtils mediaplayer = null;//new MediaPlayerUtils(MatchedFriendOfReminded.audiopath);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        initial();
        
        addAllEventListener();
    }
    
    public void initial()
    {
    	originIntent = this.getIntent();
    	originBundle = originIntent.getExtras();
    	dt = new SystemDayTime();
    	setViewPortDefaultData();
    	//userId = originBundle.getInt("userId");
	   mhandlerlist = new Handler() 
	   {

			@Override
			public void handleMessage(Message msg) 
			{
				// TODO Auto-generated method stub
				super.handleMessage(msg);
				String otherusername = msg.obj.toString();
				if(!otherusername.equals(""))
				{
					mediaplayer = new MediaPlayerUtils(audiourl);
					Toast.makeText(MainActivity.this, "来自" + otherusername + "的闹铃!", Toast.LENGTH_SHORT).show();
					mediaplayer.play(0);
				
				}
			}
		 };
		 relation = new RelationManageAction(mhandlerlist);
		 relation.searchserver(MyId.myid, audiourl);
    }
    
    public void setViewPortDefaultData(){
    	
    	setDate(dt.gettoday());
    	setWeek(dt.getweekday());
    	setTime(dt.gettime(MainActivity.this));
    	setUserName(MyId.userName);
    }
    
    public void setDate(String date){
    	TextView text_main_date = (TextView) findViewById(R.id.text_main_date);
    	text_main_date.setText(date);
    }
    
    public void setWeek(String week){
    	TextView text_main_week = (TextView) findViewById(R.id.text_main_week);
    	text_main_week.setText(week);
    }
    
    public void setTime(String time){
    	TextView text_main_time = (TextView) findViewById(R.id.text_main_time);
    	text_main_time.setText(time);
    }
    
    public void setTemp(String temp){
    	TextView text_main_temp = (TextView) findViewById(R.id.text_main_temp);
    	text_main_temp.setText(temp);
    }
    
    public void setUserName(String userName){
    	TextView text_userName = (TextView) findViewById(R.id.text_userName);
    	text_userName.setText(userName);
    }
    
    public void setWeather(int weather){
    	TextView text_main_weather = (TextView) findViewById(R.id.text_main_weather);
    	ImageView image_main_weather = (ImageView) findViewById(R.id.image_main_weather);
    	
    	switch(weather){
    		case 1:
    			text_main_weather.setText(ErrInfo.WEATHER_TEMPLATE_1);
    			//image_main_weather.setBackgroundDrawable();
    			break;
    	}
    }
    
    public void setGetUpTime(String time, int text_id){
    	TextView text_main_getUp_clock = (TextView) findViewById(text_id);
    	text_main_getUp_clock.setText(time);
    }

	public void addAllEventListener(){
        final View btn_turn_off = findViewById(R.id.btn_turn_off);
        final View btn_turn_on = findViewById(R.id.btn_turn_on);
        
        _addClickEventListener(R.id.btn_toolSet, new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				gotoToolSetMenu();
				
			}
		});
    	
    	_addClickEventListener(R.id.btn_turn_on, new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
                v.setVisibility(View.GONE);
                btn_turn_off.setVisibility(View.VISIBLE);
				
			}
		});
    	_addClickEventListener(R.id.btn_turn_off, new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
                v.setVisibility(View.GONE);
                btn_turn_on.setVisibility(View.VISIBLE);
				
			}
		});
    	_addWeekEventListener();
    	_addClickEventListener(R.id.btn_add_clock, new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
            	goSetMyClock();
            }
        });
    	
    	_addClickEventListener(R.id.btn_edit_clock, new View.OnClickListener() {
    		View background_main_cycle = findViewById(R.id.background_main_cycle);
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				background_main_cycle.setVisibility(View.VISIBLE);
			}
		});

    	_addClickEventListener(R.id.btn_edit_clock_excute, new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				goSetMyClock();
			}
		});
    	_addClickEventListener(R.id.btn_delete_clock_excute, new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			}
		});
		_addClickEventListener(R.id.background_main_cycle, new View.OnClickListener() {
			View background_main_cycle = findViewById(R.id.background_main_cycle);
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				background_main_cycle.setVisibility(View.GONE);
			}
		});
    }
    
	public void _addWeekEventListener(){
		_addClickEventListener(R.id.btn_select_mon, new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
                v.setBackgroundResource(R.drawable.button_day_cancel);
				
			}
		});
		_addClickEventListener(R.id.btn_select_tue, new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
                v.setBackgroundResource(R.drawable.button_day_cancel);
						
					}
				});
		_addClickEventListener(R.id.btn_select_wed, new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
                v.setBackgroundResource(R.drawable.button_day_cancel);
                v.setTop(2);
				
			}
		});
		_addClickEventListener(R.id.btn_select_thu, new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
                v.setBackgroundResource(R.drawable.button_day_cancel);
                v.setTop(2);
				
			}
		});
		_addClickEventListener(R.id.btn_select_fri, new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
                v.setBackgroundResource(R.drawable.button_day_cancel);
                v.setTop(2);
				
			}
		});
		
		_addClickEventListener(R.id.btn_select_sat, new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
                v.setBackgroundResource(R.drawable.button_day_cancel);
                v.setTop(2);
				
			}
		});
		_addClickEventListener(R.id.btn_select_sun, new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});

		
	}
    
    public void _addClickEventListener(int viewId, OnClickListener listener){
    	View element = findViewById(viewId);
    	element.setOnClickListener(listener);
    }
    
    public void gotoToolSetMenu(){
    	Intent intent = new Intent();
    	intent.setClass(MainActivity.this, ToolSetMenuActivity.class);
    	startActivity(intent);

    	overridePendingTransition(R.anim.in_from_left, R.anim.out_to_right);   
    }
    
    public void goSetMyClock(){
    	Intent intent = new Intent();
    	intent.setClass(MainActivity.this, SetMyClockActivity.class);
    	startActivity(intent);

    	overridePendingTransition(R.anim.in_from_left, R.anim.out_to_right);  
    }
}
