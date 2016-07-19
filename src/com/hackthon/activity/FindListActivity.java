package com.hackthon.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.hackthon.action.RelationManageAction;
import com.hackthon.activity.ListContactsActivity.ViewHolder;
import com.hackthon.util.MyId;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class FindListActivity extends Activity {

	private ArrayList<Integer> userIdSet = null;
	private ArrayList<Bitmap> portraitSet = null;
	private ArrayList<String> userNameSet = null;
	private ArrayList<String> positionSet = null;
	private ArrayList<String> sexSet = null;
	private Handler handler = null;
	private RelationManageAction relationmanage = null;

	private HashMap<String,String> Friend_And_SubFriend_AlarmList  = null;//<用户名，闹钟时间>
	private int userid = 1; 
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.call_find);

        initial();

        addAllEventListener();
    }

    public void initial() 
    {
    	userIdSet = new ArrayList<Integer>();
    	portraitSet = new ArrayList<Bitmap>();
    	userNameSet = new ArrayList<String>();
    	positionSet = new ArrayList<String>();
    	sexSet = new ArrayList<String>();
    	
    	
    	ListView findList = (ListView) findViewById(R.id.listView_find);
    	
    	//getListViewData();
    	
    	final MyAdapter myAdapter = new MyAdapter(this);
    	
    	findList.setAdapter(myAdapter);
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
					Toast.makeText(FindListActivity.this,
							"get friends alarm ok!", Toast.LENGTH_SHORT).show();
					Friend_And_SubFriend_AlarmList = relationmanage.getFriendClockList();
					
					for (Map.Entry<String, String> entry : Friend_And_SubFriend_AlarmList.entrySet()) 
					{
			            //System.out.println("Key:" + entry.getKey() + "   value:" + entry.getValue().toString());
			            userIdSet.add(new Integer(cnt));
						userNameSet.add(entry.getKey());
						positionSet.add(new String("北京"));
						sexSet.add(new String("female"));
						cnt++;
					}
		    		myAdapter.notifyDataSetChanged();
				}
			}
    		
    	};
    	relationmanage = new RelationManageAction(handler);
    	relationmanage.ReadFriendClockListFromServer(MyId.myid);
    }
    
    public void addAllEventListener(){
    	_addClickEventListener(R.id.btn_backToCallMain, new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
				overridePendingTransition(R.anim.out_to_left,R.anim.in_from_right);
			}
		});
    }
    
    public void _addClickEventListener(int viewId, OnClickListener listener){
    	View element = findViewById(viewId);
    	element.setOnClickListener(listener);
    }
    
	public void gotoRecordClockActivity(String tag){
        Intent intent=new Intent();
        intent.setClass(FindListActivity.this,CallBeforeRecordAlarmActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.in_from_left,R.anim.out_to_right);
	}
    
    public void getListViewData(){
    	int i = 0;
    	while( i < 100 ){
    		userIdSet.add(new Integer(i));
    		//portraitSet.add(getResources().getDrawable(R.drawable.icon_menu_reminder));
    		userNameSet.add(new String("美美"));
    		positionSet.add(new String("北京"));
    		sexSet.add(new String("female"));
    		i++;
    	}
    }
    
	public final class ViewHolder 
	{
		public ImageView image_find_portrait;
		public TextView text_find_userName;
		public TextView text_find_position;
		public ImageView image_find_female;
		public ImageView image_find_male;
		public Button btn_find_remind;
	}	
    
	public class MyAdapter extends BaseAdapter
	{

		private LayoutInflater mInflater;

		public MyAdapter(Context context) 
		{
			this.mInflater = LayoutInflater.from(context);
		}

		//@Override
		public int getCount() 
		{
			// TODO Auto-generated method stub
			return userIdSet.size();
		}

		//@Override
		public Object getItem(int arg0) 
		{
			// TODO Auto-generated method stub
			return null;
		}

		//@Override
		public long getItemId(int arg0) 
		{
			// TODO Auto-generated method stub
			return 0;
		}

		//@Override
		public View getView(int position, View convertView, ViewGroup parent) 
		{

			ViewHolder holder = null;

			if (convertView == null) 
			{
				convertView = mInflater.inflate(R.layout.listview_item_find, null);
				holder = new ViewHolder();
				holder.image_find_portrait = (ImageView) convertView.findViewById(R.id.image_find_portrait);
				holder.text_find_userName = (TextView) convertView.findViewById(R.id.text_find_userName);
				holder.text_find_position = (TextView) convertView.findViewById(R.id.text_find_position);
				holder.btn_find_remind = (Button) convertView.findViewById(R.id.btn_find_remind);
				holder.image_find_female = (ImageView) convertView.findViewById(R.id.image_find_female);
				holder.image_find_male = (ImageView) convertView.findViewById(R.id.image_find_male);
				
				convertView.setTag(holder);

			} else 
			{

				holder = (ViewHolder) convertView.getTag();
			}
			
			holder.image_find_portrait.setBackgroundResource(R.id.image_find_portrait);
			holder.text_find_userName.setText(userNameSet.get(position));
			holder.text_find_position.setText(positionSet.get(position));
			if ( sexSet.get(position).equals("male") ){
				holder.image_find_male.setVisibility(View.VISIBLE);
				holder.image_find_female.setVisibility(View.GONE);
			}
			else{
				holder.image_find_male.setVisibility(View.GONE);
				holder.image_find_female.setVisibility(View.VISIBLE);
			}
			
			holder.btn_find_remind.setTag(userIdSet.get(position));
			
			
			holder.btn_find_remind.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					//gotoRecordClockActivity((String)v.getTag());
					gotoRecordClockActivity("3");
				}
			});

			return convertView;
		}

	}

}
