package com.hackthon.activity;

import com.hackthon.util.RecordThread;

import android.app.Activity;
import android.content.Intent;
import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Toast;

public class CallRandomActivity extends Activity {
	
	private static int SAMPLE_RATE_IN_HZ = 8000; 
	private Handler handler = null;
	private RecordThread recordthread = null;
	int bs = AudioRecord.getMinBufferSize(SAMPLE_RATE_IN_HZ, AudioFormat.CHANNEL_CONFIGURATION_MONO, AudioFormat.ENCODING_PCM_16BIT); 
	AudioRecord ar = new AudioRecord(MediaRecorder.AudioSource.MIC, SAMPLE_RATE_IN_HZ, AudioFormat.CHANNEL_CONFIGURATION_MONO, AudioFormat.ENCODING_PCM_16BIT, bs);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.call_random);

        initial();

        handler = new Handler()
        {
			@Override
			public void handleMessage(Message msg) 
			{
				// TODO Auto-generated method stub
				super.handleMessage(msg);
				Toast.makeText(CallRandomActivity.this, "麦克风采样值：" + msg.arg1, Toast.LENGTH_SHORT).show();
				gotoRandomRecommend();
			}
        	
        };
        
        addAllEventListener();
    }

    public void initial() {
    	

    	
    }

    public void addAllEventListener() {
    	
        _addClickEventListener(R.id.btn_back, new View.OnClickListener() {
			
			@Override
			public void onClick(View v) 
			{
				// TODO Auto-generated method stub
				goBack();
			}
		});
       recordthread =  new RecordThread(handler,ar,bs);
       recordthread.start();
    }
    
    public void gotoRandomRecommend(){
    	Intent intent = new Intent();
    	Bundle bundle = new Bundle();
    	
    	intent.putExtras(bundle);
    	intent.setClass(CallRandomActivity.this, CallRandomRecommendActivity.class);
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
