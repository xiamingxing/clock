package com.hackthon.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.TextView;

public class ContactDetailActivity extends Activity {

    private Intent TargetIntent = new Intent();
    private Bundle TargetBundle = new Bundle();
    Intent OriginIntent = null;
    Bundle OriginBundle = null;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.contact_detail);

        initial();
        
        bindAllEventListener();
        
    }
    
    public void initial(){
    	
        OriginIntent = this.getIntent();
        OriginBundle = OriginIntent.getExtras();
        String phoneName = OriginBundle.getString("phoneName");
        String phoneNo = OriginBundle.getString("phoneNo");
        
        String serviceProvider = "默认";
        TelephonyManager telManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE); 
        String IMSI = telManager.getSubscriberId();
        if ( IMSI != null ){  
            if( IMSI.startsWith("46000") || IMSI.startsWith("46002") ){  
            	serviceProvider = "中国电信";
            }else if(IMSI.startsWith("46001")){  
            	serviceProvider = "中国移动";
            }else if(IMSI.startsWith("46003")){  
            	serviceProvider = "中国联通";
            }  
        }
        setDefaultInfo(phoneName, phoneNo, serviceProvider);
    }
    
    public void setDefaultInfo(String phoneName, String phoneNo, String serviceProvider){
    	TextView text_phoneName = (TextView) findViewById(R.id.text_phoneName);
    	TextView text_phoneNo = (TextView) findViewById(R.id.text_phoneNo);
    	TextView text_serviceProvider = (TextView) findViewById(R.id.text_ServiceRroviders);
    	text_phoneName.setText(phoneName);
    	text_phoneNo.setText(phoneNo);
    	text_serviceProvider.setText(serviceProvider);
    }
    
    public void bindAllEventListener(){
    	_bindShareContactEventListener();
    	_bindBackEventListener();
    }
    public void _bindShareContactEventListener(){
    	View btn_shareContact = findViewById(R.id.btn_shareContact);
    	btn_shareContact.setOnClickListener(new View.OnClickListener() {
			
			//@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				v.setBackgroundColor(Color.WHITE);
				
			}
		});
    }
    public void _bindBackEventListener(){
    	View btn_backToCallMain = findViewById(R.id.btn_backToCallMain);
    	btn_backToCallMain.setOnClickListener(new View.OnClickListener() {
			
			//@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
				//overridePendingTransition(R.anim.in_from_left,R.anim.out_to_right);
				overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
			}
		});
    }
    
}
