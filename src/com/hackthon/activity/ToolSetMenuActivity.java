package com.hackthon.activity;

import com.hackthon.util.MyId;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ToolSetMenuActivity extends Activity{
    private Intent originIntent = null;
    private Bundle originBundle = null;
    private int userId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.toolset_menu);

        initial();

        addAllEventListener();
    }

    public void initial(){
        originIntent = this.getIntent();
        originBundle = originIntent.getExtras();
        //userId = originBundle.getInt("userId");
        setViewPortDefaultData();

    }

    public void setViewPortDefaultData(){
    	setUserName(MyId.userName);
    	//setUserPortrait(null);
    }
    
    
    public void setUserName(String name){
    	TextView text_menu_userName = (TextView) findViewById(R.id.text_menu_userName);
    	text_menu_userName.setText(name);
    }

    public void setUserPortrait(Bitmap bm){
    	ImageView image_menu_portrait = (ImageView) findViewById(R.id.image_menu_portrait);
    	Drawable drawable = new BitmapDrawable(bm ); 
    	image_menu_portrait.setBackgroundDrawable(drawable);
    }
    
    public void addAllEventListener(){
    	_addClickEventListener(R.id.btn_toolSet_back, new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
				overridePendingTransition(R.anim.out_to_left,R.anim.in_from_right);
			}
		});
    	
    	final int selectedButtonTextColor = Color.parseColor("#ef8059");
    	TextView btn_menu_call = (TextView) findViewById(R.id.btn_menu_call);
    	
    	_addClickEventListenerForButton(R.id.btn_menu_call, new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					((Button) v).setTextColor(selectedButtonTextColor);
					gotoCallMain();
				}
			});
    	_addClickEventListenerForButton(R.id.btn_menu_contacts, new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				((Button) v).setTextColor(selectedButtonTextColor);
				gotoContact();
			}
		});
    	_addClickEventListenerForButton(R.id.btn_menu_home, new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				((Button) v).setTextColor(selectedButtonTextColor);
				gotoMain();
			}
		});
    	_addClickEventListenerForButton(R.id.btn_menu_reminder, new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				((Button) v).setTextColor(selectedButtonTextColor);
				goSetMyClock();
			}
		});
    	_addClickEventListenerForButton(R.id.btn_menu_setting, new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				((Button) v).setTextColor(selectedButtonTextColor);
			}
		});
    }

    public void _addClickEventListener(int viewId, View.OnClickListener listener){
        View element = findViewById(viewId);
        element.setOnClickListener(listener);
    }
    public void _addClickEventListenerForButton(int viewId, View.OnClickListener listener){
        Button element = (Button) findViewById(viewId);
        element.setOnClickListener(listener);
    }
    
    public void gotoCallMain(){
        Intent intent=new Intent();
        intent.setClass(ToolSetMenuActivity.this,CallMainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.out_to_left,R.anim.in_from_right);
    }
    
    public void gotoMain(){
    	Intent intent = new Intent();
    	intent.setClass(ToolSetMenuActivity.this, MainActivity.class);
    	startActivity(intent);
    	overridePendingTransition(R.anim.out_to_left,R.anim.in_from_right);
    }
    
    public void gotoContact(){
    	Intent intent = new Intent();
    	intent.setClass(ToolSetMenuActivity.this, ListContactsActivity.class);
    	startActivity(intent);
    	overridePendingTransition(R.anim.out_to_left,R.anim.in_from_right);
    }
    
    public void goSetMyClock(){
    	Intent intent = new Intent();
    	intent.setClass(ToolSetMenuActivity.this, SetMyClockActivity.class);
    	startActivity(intent);

    	overridePendingTransition(R.anim.out_to_left,R.anim.in_from_right);
    }
}
