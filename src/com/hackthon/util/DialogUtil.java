package com.hackthon.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;

public class DialogUtil {
	
	public static void alert(String title, String content, int icon, Activity activity){
		Dialog alertDialog = new AlertDialog.Builder(activity). 
                setTitle(title). 
                setMessage(content). 
                setIcon(icon).
                setPositiveButton("确定", new android.content.DialogInterface.OnClickListener() {
            		
					//@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						dialog.dismiss();
					}
					
		    	}).
                create(); 
        alertDialog.show();
    }
    
    public static void alert(String content, Activity activity){
    	alert("提示ʾ",content,android.R.drawable.alert_light_frame,activity);
    }
    
    public static void prompt(String title, String content, 
    						  int icon, Activity activity, 
    						  final CallBackForDialogBtn callBackForDialogBtn){
    	
    	 AlertDialog.Builder builder = new Builder(activity);
    	 builder.setTitle(title).
    	 		setMessage(content).
    	 		setIcon(icon).
    	 		setPositiveButton("确定", new android.content.DialogInterface.OnClickListener() {
		
					//@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						callBackForDialogBtn.confirm();
						dialog.dismiss();
					}
					
		    	}).
		    	setNegativeButton("取消", new android.content.DialogInterface.OnClickListener() {

		    		//@Override
		    		public void onClick(DialogInterface dialog, int which) {
		 				// TODO Auto-generated method stub
		    			callBackForDialogBtn.cancel();
		    			dialog.dismiss();
		    		}
		 			
		     	});
    	 builder.create().show();
    }
    
    public static void prompt(String content, Activity activity, 
			  final CallBackForDialogBtn callBackForDialogBtn){
		
		AlertDialog.Builder builder = new Builder(activity);
		builder.setTitle("提示ʾ").
		setMessage(content).
		setIcon(android.R.drawable.alert_dark_frame).
		setPositiveButton("确定", new android.content.DialogInterface.OnClickListener() {
		
			//@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				callBackForDialogBtn.confirm();
				dialog.dismiss();
			}
			
		}).
		setNegativeButton("取消", new android.content.DialogInterface.OnClickListener() {
		
			//@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				callBackForDialogBtn.cancel();
				dialog.dismiss();
			}
			
		});
		builder.create().show();
	}
}



