package com.hackthon.activity;

import com.hackthon.action.RelationManageAction;
import com.hackthon.bean.AlarmTimeBean;
import com.hackthon.util.ErrInfo;
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
import android.widget.TextView;
import android.widget.Toast;

public class SetMyClockActivity extends Activity {
	
	private AlarmTimeBean clockTime = null;
	private RelationManageAction relationManageAciton = null;
   
    private Handler handler = new Handler() {
        public void handleMessage(Message msg){

        }
    };
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_my_clock);

        initial();

        addAllEventListener();
    }

    public void initial() {
    	relationManageAciton = new RelationManageAction(handler);
    	
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
    	_addClickEventListener(R.id.btn_confirm, new View.OnClickListener() {
			
			@Override
			public void onClick(View v) 
			{
				// TODO Auto-generated method stub
				setClockInfo();
				Toast.makeText(getApplicationContext(), "设置成功！", Toast.LENGTH_SHORT).show();
				gotoMain();
			}
		});
    }
    
    public void setClockInfo(){
    	TextView text_hour = (TextView) findViewById(R.id.text_hour);
    	TextView text_min = (TextView) findViewById(R.id.text_min);
    	
    	String hour = (String) text_hour.getText();
    	String min = (String) text_min.getText();
    	
    	clockTime = new AlarmTimeBean(Integer.parseInt(hour),Integer.parseInt(min));
    	relationManageAciton.RemindedRequestToServer(MyId.myid, clockTime);
    }

    public void gotoMain(){
    	Intent intent = new Intent();
    	Bundle bundle = new Bundle();
    	
    	intent.putExtras(bundle);
    	intent.setClass(SetMyClockActivity.this, MainActivity.class);
    	startActivity(intent);
    	overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
    }
    
	public void goBack(){
        finish();
        overridePendingTransition(R.anim.out_to_left,R.anim.in_from_right);
	}
    
	public void _addClickEventListener(int viewId,OnClickListener listener){
        View element = findViewById(viewId);
        element.setOnClickListener(listener);
    }

}
