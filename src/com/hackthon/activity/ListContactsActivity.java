package com.hackthon.activity;

import java.io.InputStream;
import java.util.ArrayList;

import com.hackthon.util.PhotoUtil;

import android.app.Activity;
import android.app.ListActivity;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.CommonDataKinds.Photo;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class ListContactsActivity extends Activity {
	Context mContext = null;
	private static final String[] PHONES_PROJECTION = new String[] {
			Phone.DISPLAY_NAME, Phone.NUMBER, Photo.PHOTO_ID, Phone.CONTACT_ID };
	private static final int PHONES_DISPLAY_NAME_INDEX = 0;
	private static final int PHONES_NUMBER_INDEX = 1;

	private static final int PHONES_PHOTO_ID_INDEX = 2;

	private static final int PHONES_CONTACT_ID_INDEX = 3;
	private ArrayList<String> mContactsName = new ArrayList<String>();

	private ArrayList<String> mContactsNumber = new ArrayList<String>();

	private ArrayList<Bitmap> mContactsPhonto = new ArrayList<Bitmap>();

	ListView mListView = null;
	MyAdapter myAdapter = null;

	// @Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_listview);

		mContext = this;

		mListView = (ListView) findViewById(R.id.listview);

		/* get contact info */
		getPhoneContacts();

		System.out.println("onCreate");

		myAdapter = new MyAdapter(this);
		mListView.setAdapter(myAdapter);

		mListView.setOnItemClickListener(new OnItemClickListener() {
			// @Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Bundle TargetBundle = new Bundle();
				TargetBundle.putString("phoneName", mContactsName.get(arg2));
				TargetBundle.putString("phoneNo", mContactsNumber.get(arg2));
				Intent TargetIntent = new Intent();
				TargetIntent.setClass(ListContactsActivity.this,
						ContactDetailActivity.class);
				TargetIntent.putExtras(TargetBundle);
				startActivity(TargetIntent);
			}
		});
		_addClickEventListener(R.id.btn_toolSet, new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				gotoToolSetMenu();

			}
		});
	}

	public void _addClickEventListener(int viewId, OnClickListener listener) {
		View element = findViewById(viewId);
		element.setOnClickListener(listener);
	}

	public void gotoToolSetMenu() {
		Intent intent = new Intent();
		intent.setClass(ListContactsActivity.this, ToolSetMenuActivity.class);
		startActivity(intent);
		overridePendingTransition(R.anim.in_from_left, R.anim.out_to_right);
	}

	public final class ViewHolder {
		public ImageView headphoto;
		public TextView name;
		public TextView phoneno;
		public Button viewBtn;
	}

	/** inhert BaseAdapter from qihoo.object **/
	/****************************************/
	public class MyAdapter extends BaseAdapter {

		private LayoutInflater mInflater;

		public MyAdapter(Context context) {
			this.mInflater = LayoutInflater.from(context);
		}

		// @Override
		public int getCount() {
			// TODO Auto-generated method stub
			return mContactsName.size();
		}

		// @Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		// @Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return 0;
		}

		// @Override
		public View getView(int position, View convertView, ViewGroup parent) {

			ViewHolder holder = null;

			if (convertView == null) {
				convertView = mInflater.inflate(R.layout.recordlist, null);
				holder = new ViewHolder();
				holder.headphoto = (ImageView) convertView
						.findViewById(R.id.headphoto);
				holder.name = (TextView) convertView.findViewById(R.id.name);
				holder.phoneno = (TextView) convertView
						.findViewById(R.id.phoneno);

				convertView.setTag(holder);

			} else {

				holder = (ViewHolder) convertView.getTag();
			}
			holder.name.setText(mContactsName.get(position));
			holder.phoneno.setText(mContactsNumber.get(position));
			holder.headphoto.setImageBitmap(mContactsPhonto.get(position));

			// holder.viewBtn.setOnClickListener(new View.OnClickListener() {
			// //@Override
			// public void onClick(View v) {
			// showInfo();
			// }
			// });

			return convertView;
		}
	}

	/****************************************/
	/** inhert BaseAdapter from qihoo.object **/

	private void getPhoneContacts() {
		int recordcnt = 0;
		ContentResolver resolver = mContext.getContentResolver();
		Cursor contactcursor = resolver.query(Phone.CONTENT_URI,
				PHONES_PROJECTION, null, null, null);
		if (contactcursor != null) {
			while (contactcursor.moveToNext()) {
				String phoneNumber = contactcursor
						.getString(PHONES_NUMBER_INDEX);
				if (TextUtils.isEmpty(phoneNumber))
					continue;
				if (recordcnt++ > 100) {
					break;
				}
				String contactName = contactcursor
						.getString(PHONES_DISPLAY_NAME_INDEX);

				Long contactid = contactcursor.getLong(PHONES_CONTACT_ID_INDEX);

				Long photoid = contactcursor.getLong(PHONES_PHOTO_ID_INDEX);

				Bitmap contactphoto = null;
				if (photoid > 0) {
					Uri uri = ContentUris.withAppendedId(
							ContactsContract.Contacts.CONTENT_URI, contactid);
					InputStream input = ContactsContract.Contacts
							.openContactPhotoInputStream(resolver, uri);
					contactphoto = BitmapFactory.decodeStream(input);
					contactphoto = PhotoUtil
							.toRoundCorner(contactphoto, 180.0f);

				} else {
					contactphoto = BitmapFactory.decodeResource(getResources(),
							R.drawable.icon_default_portrait);
				}
				mContactsName.add(contactName);
				mContactsNumber.add(phoneNumber);
				mContactsPhonto.add(contactphoto);
			}
			contactcursor.close();
		} else {
			Log.d("getPhoneContacts", "query db data wrong");
		}

	}

}
