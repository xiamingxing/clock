package com.hackthon.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Toast;

public class CallMainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.call_main);

        initial();

        addAllEventListener();
    }

    public void initial() {
    	

    	
    }

    public void addAllEventListener() {
    	DisplayMetrics dm=getResources().getDisplayMetrics();  
    	final int screenWidth=dm.widthPixels;  
    	final int screenHeight=dm.heightPixels-50;  
    	
        OnTouchListener onTouchListener = new View.OnTouchListener() {
        	
        	int lastX,lastY;  
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                int ea = event.getAction();

                switch (ea) {
                    case MotionEvent.ACTION_DOWN:

                        lastX = (int) event.getRawX();//获取触摸事件触摸位置的原始X坐标
                        lastY = (int) event.getRawY();
                        break;

                    case MotionEvent.ACTION_MOVE:
                        int dx = (int) event.getRawX() - lastX;
                        int dy = (int) event.getRawY() - lastY;

                        int l = v.getLeft() + dx;
                        int b = v.getBottom() + dy;
                        int r = v.getRight() + dx;
                        int t = v.getTop() + dy;

                        if (l < 0) {
                            l = 0;
                            r = l + v.getWidth();
                        }

                        if (t < 0) {
                            t = 0;
                            b = t + v.getHeight();
                        }

                        if (r > screenWidth) {
                            r = screenWidth;
                            l = r - v.getWidth();
                        }

                        if (b > screenHeight) {
                            b = screenHeight;
                            t = b - v.getHeight();
                        }
                        v.layout(l, t, r, b);

                        lastX = (int) event.getRawX();
                        lastY = (int) event.getRawY();
                        v.postInvalidate();
                        break;
                    case MotionEvent.ACTION_UP:
                    	DisplayMetrics dm = getResources().getDisplayMetrics();  
                    	v.setX((dm.widthPixels-v.getWidth())/2);
                    	v.setY((dm.heightPixels)/3);
                    	//gotoToolSetMenu();
                    	gotoOtherActivity((String) v.getTag());
                        break;
                }
				return false;
            }
        };
        _addClickEventListener(R.id.btn_toolSet,new View.OnClickListener(){

			@Override
			public void onClick(View v){
		        // TODO Auto-generated method stub
		        gotoToolSetMenu();
		
		    }
		 });
        _addOnTouchEventListener(R.id.btn_call_find,onTouchListener);
		_addOnTouchEventListener(R.id.btn_call_group,onTouchListener);
		_addOnTouchEventListener(R.id.btn_call_random,onTouchListener);
        
}

	public void gotoToolSetMenu(){
        Intent intent=new Intent();
        intent.setClass(CallMainActivity.this,ToolSetMenuActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.in_from_left,R.anim.out_to_right);
	}
	
	public void gotoFindList(){
        Intent intent=new Intent();
        intent.setClass(CallMainActivity.this,FindListActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.in_from_left,R.anim.out_to_right);
	}
	
	public void gotoCallRandom(){
        Intent intent=new Intent();
        intent.setClass(CallMainActivity.this,CallRandomActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.in_from_left,R.anim.out_to_right);
	}
	
	public void gotoOtherActivity(String tag){
		if ( tag.equals("btn_call_group") ){
			gotoToolSetMenu();
		}
		else if ( tag.equals("btn_call_find") ){
			gotoFindList();
		}
		else if ( tag.equals("btn_call_random") ){
			gotoCallRandom();
		}
		else{
			gotoToolSetMenu();
		}
		
	}

	public void _addClickEventListener(int viewId,OnClickListener listener){
        View element=findViewById(viewId);
        element.setOnClickListener(listener);
    }
	
	public void _addOnTouchEventListener(int viewId,OnTouchListener listener){
	      View element=findViewById(viewId);
	      element.setOnTouchListener(listener);
	}
}
