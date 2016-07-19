package com.hackthon.activity;

import com.hackthon.bean.MatchedFriendOfReminded;
import com.hackthon.util.MediaRecorderUtils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Toast;

public class CallStartRecordAlarmActivity extends Activity {
	String audiopath = null;
	MediaRecorderUtils mediarecord = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.call_start_record_alarm);

        initial();

        addAllEventListener();
    }

    public void initial() 
    {
    	
//record start
     	//开始录音
    	mediarecord = new MediaRecorderUtils(this);
    	mediarecord.startRecording();
    }

    public void addAllEventListener() {
    	
        _addClickEventListener(R.id.btn_back, new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				goBack();
			}
		});
        _addOnTouchEventListener(R.id.btn_record_alarm,new View.OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				int ea = event.getAction();
                switch (ea) {
                    case MotionEvent.ACTION_DOWN:
                    	
                        break;

                    case MotionEvent.ACTION_MOVE:
                       
                        break;
                    case MotionEvent.ACTION_UP:
                    	Toast.makeText(CallStartRecordAlarmActivity.this, "录音结束！", 1);
                    	
                    	mediarecord.stopRecording();

                    	MatchedFriendOfReminded.audiopath = mediarecord.getRecordingname();
                    	
                    	gotoCallAfterRecordAlarm();
                        break;
                }
				return false;
			}
		});
    }
    
    public void gotoCallAfterRecordAlarm(){
    	Intent intent = new Intent();
    	Bundle bundle = new Bundle();
    	
    	intent.putExtras(bundle);
    	intent.setClass(CallStartRecordAlarmActivity.this, CallAfterRecordAlarmActivity.class);
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
	
	public void _addOnTouchEventListener(int viewId,OnTouchListener listener){
        View element = findViewById(viewId);
        element.setOnTouchListener(listener);
    }
}
