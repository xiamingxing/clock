package com.hackthon.activity;

import java.util.Map;

import com.hackthon.action.RelationManageAction;
import com.hackthon.bean.MatchedFriendOfReminded;
import com.hackthon.util.CallBackForDialogBtn;
import com.hackthon.util.DialogUtil;
import com.hackthon.util.MediaPlayerUtils;
import com.hackthon.util.MyId;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Toast;

public class CallAfterRecordAlarmActivity extends Activity {
	
	MediaPlayerUtils mediaplayer = new MediaPlayerUtils(MatchedFriendOfReminded.audiopath);
	boolean iscast = false;
	
	private RelationManageAction relation = null;
	private Handler handler = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.call_after_record_alarm);

        initial();

        addAllEventListener();
    }

    public void initial() {
    	
    	//将id\idother\audiopath上传
    	handler = new Handler()
    	{
			@Override
			public void handleMessage(Message msg) 
			{
				// TODO Auto-generated method stub
				super.handleMessage(msg);
				if(msg.what == 3)
				{
					Toast.makeText(CallAfterRecordAlarmActivity.this, "上传成功!", Toast.LENGTH_SHORT).show();
				}
			}		
    	};
    
    	
    }

    public void addAllEventListener() {
    	
        _addClickEventListener(R.id.btn_back, new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				goBack();
			}
		});
        
        _addClickEventListener(R.id.btn_record_upload,new View.OnClickListener() {
			
			@Override
			public void onClick(View v) 
			{
				// TODO Auto-generated method stub
				DialogUtil.prompt("确定要上传录音文件？", CallAfterRecordAlarmActivity.this, new CallBackForDialogBtn(){

					@Override
					public void confirm() {
						// TODO Auto-generated method stub
						relation = new RelationManageAction(handler);
				    	relation.WakeUpSomeoneRequestToServer(MyId.myid,MatchedFriendOfReminded.audiopath,MatchedFriendOfReminded.userid);
					}

					@Override
					public void cancel() {
						// TODO Auto-generated method stub
						
					}});
				
			}
		});
        
        _addClickEventListener(R.id.btn_record_play,new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//DialogUtil.alert("播放当前录音", CallAfterRecordAlarmActivity.this);
				if(!iscast)
				{
					mediaplayer.play(0);
					iscast  = true;
				}
				else 
				{
					mediaplayer.stop();
					iscast  = false;
				}
			}
		});
        
    }
    
    public void gotoRandomRecommend(){
    	Intent intent = new Intent();
    	Bundle bundle = new Bundle();
    	
    	intent.putExtras(bundle);
    	intent.setClass(CallAfterRecordAlarmActivity.this, CallRandomRecommendActivity.class);
    	startActivity(intent);
    	overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
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
