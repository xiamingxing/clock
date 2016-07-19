package com.hackthon.activity;


import java.util.HashMap;
import java.util.Map;

import com.hackthon.action.RelationManageAction;
import com.hackthon.bean.MatchedFriendOfReminded;
import com.hackthon.util.MyId;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class CallRandomRecommendActivity extends Activity{
	
	private RelationManageAction relation = null;
	private Handler handler = null;
	private HashMap<String,String> Friend_And_SubFriend_AlarmList  = null;//<用户名，闹钟时间>
	int userid = 1;
	String selectedname = null;
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.call_random_recommend);

        initial();
        
        addAllEventListener();
    }

    public void initial() {
    	
    	//setDefaultViewPortInfo();
    	//从数据库中获取朋友或者间接朋友的闹钟
    	handler = new Handler()
    	{
			@Override
			public void handleMessage(Message msg) 
			{
				// TODO Auto-generated method stub
				super.handleMessage(msg);
				if(msg.what == 2)
				{
					int cnt = 0;
					Toast.makeText(CallRandomRecommendActivity.this,
							"get friends alarm list ok!", Toast.LENGTH_SHORT).show();
					Friend_And_SubFriend_AlarmList = relation.getFriendClockList();
					cnt = Friend_And_SubFriend_AlarmList.size();
					long randomno = Math.round(Math.random()) % cnt;
					cnt = 0;
					for (Map.Entry<String, String> entry : Friend_And_SubFriend_AlarmList.entrySet()) 
					{
			            //System.out.println("Key:" + entry.getKey() + "   value:" + entry.getValue().toString());
						if(cnt == randomno)
						{
							selectedname  = entry.getKey();
							MatchedFriendOfReminded.usrname  = selectedname;
							MatchedFriendOfReminded.userid = Integer.parseInt(entry.getValue());
							setUserName(selectedname);
							setPortrait(null);
							break;
						}
						cnt++;
					}
				}
			}		
    	};
    	relation = new RelationManageAction(handler);
    	relation.ReadFriendClockListFromServer(MyId.myid);
    	//WakeUpSomeoneRequestToServer(final long userid,final String mediapath,final long matchedid)
    	//relation.WakeUpSomeoneRequestToServer();
    }
    
    public void setDefaultViewPortInfo(){
    	setUserName("美丽的蝈蝈");
    	setPortrait(null);
    }
    
    public void setUserName(String userName){
    	TextView text_randomUserName = (TextView) findViewById(R.id.text_randomUserName);
    	text_randomUserName.setText(userName);
    }
    
    public void setPortrait(Bitmap portrait){
    	ImageView image_randomPortrait = (ImageView) findViewById(R.id.image_randomPortrait);
    	
    	if( portrait != null ){
    		image_randomPortrait.setBackgroundDrawable(new BitmapDrawable(portrait));
    	}
    	else{
    		image_randomPortrait.setBackgroundDrawable(getResources().getDrawable(R.drawable.icon_random_portrait));
    	}
    }

    public void gotoCallBeforeRecordAlarm(){
    	Intent intent = new Intent();
    	Bundle bundle = new Bundle();
    	
    	intent.putExtras(bundle);
    	intent.setClass(CallRandomRecommendActivity.this, CallBeforeRecordAlarmActivity.class);
    	startActivity(intent);
    	overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
    }
    
    public void addAllEventListener() {
    	
        _addClickEventListener(R.id.btn_random_remind, new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				gotoCallBeforeRecordAlarm();
			}
		});
        
        _addClickEventListener(R.id.btn_random_remind_cancel, new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				goBack();
			}
		});
        
        _addClickEventListener(R.id.btn_back, new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				goBack();
			}
		});
    }
    

	public void goBack(){
        finish();
        overridePendingTransition(R.anim.in_from_left,R.anim.out_to_right);
	}
	
	public void _addClickEventListener(int viewId,OnClickListener listener){
        View element=findViewById(viewId);
        element.setOnClickListener(listener);
    }
}
