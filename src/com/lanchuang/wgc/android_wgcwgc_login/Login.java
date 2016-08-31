package com.lanchuang.wgc.android_wgcwgc_login;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lanchuang.wgc.android_wgcwgc_ui.R;

public class Login extends Activity
{
	private EditText editText01;
	private Intent intent01 , intent02;
	private Button button01 , button02 , button03 , button04 , button05;

	@Override
	public void onCreate(Bundle savedInstanceState )
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		editText01 = (EditText) findViewById(R.id.login_editText01);

		intent01 = getIntent();
		String contents = intent01.getStringExtra("account");
//		System.out.println(contents + "\t登录成功！！！");
		Log.d("LOG" ,"界面展示： " + contents + "  登录成功！！！");
		Toast.makeText(this ,contents + "\n\t\t登录成功！！！" ,Toast.LENGTH_LONG).show();
		editText01.setText(contents);

		button01 = (Button) findViewById(R.id.login_button01);
		button01.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v )
			{
				intent02 = new Intent(Intent.ACTION_VIEW);
				intent02.setData(Uri.parse("http://www.cnblogs.com/jeff-wgc/"));
				startActivity(intent02);
			}
		});

		button02 = (Button) findViewById(R.id.login_button02);
		button02.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v )
			{
				Intent intent = new Intent(Intent.ACTION_DIAL);
				intent.setData(Uri.parse("tel:17195871521"));
				startActivity(intent);
			}
		});

		button03 = (Button) findViewById(R.id.login_button03);
		button03.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v )
			{
				Intent intent = new Intent(Intent.ACTION_VIEW);
				intent.setType("vnd.android-dir/mms-sms");
				intent.putExtra("sms_body" ,""); // "sms_body"为固定内容
				startActivity(intent);
			}
		});

		button04 = (Button) findViewById(R.id.login_button04);
		button04.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v )
			{
				Intent intent = new Intent(Intent.ACTION_VIEW);
				Uri uri = Uri.parse("file:///mnt/sdcard/netease/cloudmusic/Music/杨宗纬 - 洋葱.mp3");
				intent.setDataAndType(uri ,"audio/mp3");
				startActivity(intent);
				
//				intent = new Intent();
//				intent.setAction(Intent.ACTION_VIEW);
//				Uri data = Uri.parse("file:///storage/sdcard0/平凡之路.mp3");
//				//设置data+type属性
//				intent.setDataAndType(data, "audio/mp3"); //方法：Intent android.content.Intent.setDataAndType(Uri data, String type)
//				startActivity(intent);
			}
		});

		button05 = (Button) findViewById(R.id.login_button05);
		button05.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v )
			{
				Intent intent = new Intent(Intent.ACTION_DELETE);
				Uri data = Uri.parse("package:com.lanchuang.wgc.android_wgcwgc_ui");
				intent.setData(data);
				startActivity(intent);
			}
		});

	}
}
