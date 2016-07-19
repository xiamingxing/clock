package com.hackthon.activity;

import com.hackthon.bean.MatchedFriendOfReminded;
import com.hackthon.util.MediaRecorderUtils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.widget.Toast;

public class CallBeforeRecordAlarmActivity extends Activity {
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.call_before_record_alarm);

        initial();

        addAllEventListener();
    }

    public void initial() {
   
    	

    	
    }

    public void addAllEventListener() {
    	
        _addClickEventListener(R.id.btn_back, new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				goBack();
			}
		});
//        _addLongClickEventListener(R.id.btn_start_alarm,new View.OnLongClickListener() {
//			
//			@Override
//			public boolean onLongClick(View v) {
//				// TODO Auto-generated method stub
//				gotoCallStartRecordAlarm();
//				return false;
//			}
//		});
//        _addClickEventListener(R.id.btn_start_alarm, new View.OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				gotoCallStartRecordAlarm();
//			}
//		});
        _addOnTouchEventListener(R.id.btn_start_alarm,new View.OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				int ea = event.getAction();
                switch (ea) {
                    case MotionEvent.ACTION_DOWN:
                    	gotoCallStartRecordAlarm();
                        break;

                    case MotionEvent.ACTION_MOVE:
                       
                        break;
                    case MotionEvent.ACTION_UP:
                        break;
                }
				return false;
			}
		});
    }
        
    
    
    public void gotoCallStartRecordAlarm(){
    	Intent intent = new Intent();
    	Bundle bundle = new Bundle();
    	
    	intent.putExtras(bundle);
    	intent.setClass(CallBeforeRecordAlarmActivity.this, CallStartRecordAlarmActivity.class);
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
	
	public void _addLongClickEventListener(int viewId,OnLongClickListener listener){
        View element = findViewById(viewId);
        element.setOnLongClickListener(listener);
    }
	
	public void _addOnTouchEventListener(int viewId,OnTouchListener listener){
        View element = findViewById(viewId);
        element.setOnTouchListener(listener);
    }
}
