package com.hackthon.activity;

import java.io.IOException;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hackthon.action.UserManageAction;
import com.hackthon.bean.LoginInfo;
import com.hackthon.bean.MatchedFriendOfReminded;
import com.hackthon.util.ErrInfo;
import com.hackthon.util.HttpUtils;
import com.hackthon.util.MediaPlayerUtils;
import com.hackthon.util.MyId;

public class LoginActivity extends Activity {

	private ProgressDialog dialog;
	private LoginInfo loginInfo = null;
	private UserManageAction userManage = new UserManageAction();
	String audiourl = "";

	MediaPlayerUtils mediaplayer = null;// new
										// MediaPlayerUtils(MatchedFriendOfReminded.audiopath);

	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			if (msg.arg1 == -2) {
				warning(ErrInfo.ERR_LOGIN);
			} else if (msg.arg1 == -1) {
				warning(ErrInfo.ERR_LOGIN);
			} else {
				Toast.makeText(LoginActivity.this, "鐧诲綍鎴愬姛锛�", 1).show();
				gotoMain(msg.arg1);
			}

		}
	};

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);

		initial();

		addAllEventListener();

	}

	public void initial() {
		// this.startService(new Intent(this, AlarmService.class));

	}

	public void addAllEventListener() {

		final EditText text_userName = (EditText) findViewById(R.id.edit_userName);
		final EditText text_passWord = (EditText) findViewById(R.id.edit_passWord);

		_addClickEventListener(R.id.btn_login, new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				v.setBackgroundColor(Color.parseColor("#c5481c"));

				// dialog = ProgressDialog.show(LoginActivity.this, null,
				// "姝ｅ湪鐧诲綍锛岃绋嶅��...", true, false);

				loginInfo = getUserInputInfo();
				MyId.userName = loginInfo.getUserName();
				String loginValidationInfo = validation(loginInfo);

				if (!loginValidationInfo
						.equals(ErrInfo.SUCCESS_VALIDATION_LOGIN)) {
					warning(loginValidationInfo);

				} else {
					userManage.login(loginInfo, handler);
					// gotoMain(0);
				}
			}
		});
		_addClickEventListener(R.id.btn_register, new View.OnClickListener() {
			public void onClick(View v) {
				v.setBackgroundColor(Color.parseColor("#15a879"));
				gotoMain(1);
			}
		});
		_addClickEventListener(R.id.btn_cancelUserName,
				new View.OnClickListener() {

					public void onClick(View v) {
						// TODO Auto-generated method stub
						text_userName.setText("");
					}
				});
		_addClickEventListener(R.id.btn_cancelPassWord,
				new View.OnClickListener() {

					public void onClick(View v) {
						text_passWord.setText("");
					}
				});
	}

	public void gotoMain(int userId) {
		Intent intent = new Intent();
		Bundle bundle = new Bundle();

		bundle.putInt("userId", userId);
		intent.putExtras(bundle);
		MyId.myid = userId;
		intent.setClass(LoginActivity.this, MainActivity.class);
		startActivity(intent);
		overridePendingTransition(android.R.anim.fade_in,
				android.R.anim.fade_out);
	}

	public void gotoRegister() {
		Intent intent = new Intent();
		Bundle bundle = new Bundle();
		intent.setClass(LoginActivity.this, MainActivity.class);
		startActivity(intent);
	}

	public LoginInfo getUserInputInfo() {

		EditText edit_loginUserName = (EditText) findViewById(R.id.edit_userName);
		EditText edit_loginPassWord = (EditText) findViewById(R.id.edit_passWord);

		String userName = edit_loginUserName.getText().toString();
		String passWord = edit_loginPassWord.getText().toString();

		LoginInfo loginInfo = new LoginInfo(userName, passWord);
		return loginInfo;
	}

	public void warning(String text) {
		// TextView text_warning = ( TextView ) findViewById(R.id.text_warning);
		// text_warning.setText(text);
		Toast.makeText(LoginActivity.this, text, 1).show();
	}

	public String validation(LoginInfo loginInfo) {
		if (loginInfo == null) {
			return ErrInfo.ERR_INPUT;
		}
		if (loginInfo.getUserName().length() == 0) {
			return ErrInfo.ERR_EMPTY_USERNAME;
		}

		if (loginInfo.getPassWord().length() == 0) {
			return ErrInfo.ERR_EMPTY_PASSWORD;
		}
		return ErrInfo.SUCCESS_VALIDATION_LOGIN;
	}

	public void _addClickEventListener(int viewId, OnClickListener listener) {
		View element = findViewById(viewId);
		element.setOnClickListener(listener);
	}
}