package com.lanchuang.wgc.android_wgcwgc_ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.lanchuang.wgc.android_wgcwgc_Sqlite.AccountHelper;
import com.lanchuang.wgc.android_wgcwgc_login.Login;
import com.lanchuang.wgc.android_wgcwgc_register.Register;
import com.lanchuang.wgcwgc.android_wgcwgc_webTest.ServerJudges;

/**
 * 
 * 有几个技能你先掌握下： 1. 采用HTTP与服务器通信，方法采用GET/POST，提交参数要做url
 * encode，提交的数据和返回的数据采用JSON格式，要学会组织和解析 2. 采用Web
 * View方式打开HTML5的网页，能正确显示网页内容，提供按钮返回上一页和直接关闭，就像微信内嵌入的网页一样 3.
 * 掌握播放本地音频文件、服务器音频流的操作方法（播放、停止、快进、拖动），掌握通过麦克风录音并存储文件的方法，掌握文件上传服务器的方法。
 * 
 * @author Administrator
 * 
 */

public class MainActivity extends Activity
{

	private Button button01 , button02;
	private EditText editText01 , editText02;
	private TextView textView01 , textView02;

	private String account = null;
	private String password = null;

	private String content = null;

	// private String contents = null;

	/**
	 * 
	 * 账号和密码接收后 ， 数据库处理对比 连接数据库 + 读取、对比数据
	 * 
	 */
	private boolean compare(String username , String password )
	{
		try
		{
			AccountHelper helper = new AccountHelper(this);
			// String wgc = "wgc";
			// helper.insert(wgc ,wgc ,wgc ,wgc ,wgc ,wgc);
			String sqlitePassword = helper.getPasswordByUsername(username);
			if(sqlitePassword.equals(password))
			{
//				System.out.println("成功连接数据库！！！");
				Log.d("LOG" ,username + "  成功访问数据库！！！");
				// Toast.makeText(this ,"成功连接数据库！！！" ,Toast.LENGTH_LONG).show();
				return true;
			}
		}
		catch(Exception e)
		{
//			System.out.println("账号或者密码错误！！！");
			Log.d("LOG" ,"账号或者密码错误！！！");
			Toast.makeText(MainActivity.this ,"账号或者密码错误！！！" ,Toast.LENGTH_LONG).show();
		}
		return false;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState )
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		button01 = (Button) findViewById(R.id.main_button01);
		button02 = (Button) findViewById(R.id.main_button02);

		editText01 = (EditText) findViewById(R.id.main_editText02);
		editText02 = (EditText) findViewById(R.id.main_editText01);

		textView01 = (TextView) findViewById(R.id.main_textView03);
		textView02 = (TextView) findViewById(R.id.main_textView04);

		/**
		 * 
		 * 登录按钮操作
		 * 
		 */
		button01.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v )
			{
				account = editText01.getText().toString();
				password = editText02.getText().toString();

				/**
				 * @@@@@@@@@@@@@@@@@@@@@@@@@@@@@author Administrator
				 * @author Administrator
				 * 
				 *         这里可以增加访问服务器的判断和反馈
				 *         ************************************
				 *         **************************************
				 */

				// ServerJudge sj = new ServerJudge();
				// if()
				// {
				// Log.d("LOG" , "成功连接服务器！！！");
				// }
				Log.d("LOG" ,"用户输入：  account:" + account + "  password:" + password);
				new ServerJudges(account , password);
				
				if(compare(account ,password))
				{
					Intent intent = new Intent();
					intent.setClass(MainActivity.this ,Login.class);
					intent.putExtra("account" ,account);
					startActivity(intent);
					finish();
				}
				else
				{
//					System.out.println("账号或者密码错误！！！");
					Log.d("LOG" ,"账号或者密码错误！！！");
					Toast.makeText(MainActivity.this ,"账号或者密码错误！！！" ,Toast.LENGTH_SHORT).show();
				}
			}

		});

		/**
		 * 
		 * 注册按钮操作
		 * 
		 */
		button02.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v )
			{
				// account = editText01.getText().toString();
				// password = editText02.getText().toString();
				//
				// content = " :\naccount : " + account + "\npassword : " +
				// password;
				// System.out.println("register" + content);
				// Toast.makeText(MainActivity.this ,"register" + content
				// ,Toast.LENGTH_LONG).show();

				Intent intent = new Intent();
				intent.setClass(MainActivity.this ,Register.class);
				startActivity(intent);
			}
		});

		/**
		 * 
		 * 忘记密码？TextView 触发事件响应
		 * 
		 */
		textView01.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v )
			{
				account = editText01.getText().toString();
				password = editText02.getText().toString();
				content = " :\naccount : " + account + "\npassword : " + password;
				System.out.println("forget password?" + content);
				Toast.makeText(MainActivity.this ,"forget password?" + content ,Toast.LENGTH_LONG).show();
			}
		});

		/**
		 * 
		 * 找回账号！ TextView 触发事件响应
		 * 
		 */
		textView02.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v )
			{
				account = editText01.getText().toString();
				password = editText02.getText().toString();
				content = " :\naccount : " + account + "\npassword : " + password;
				System.out.println("retrieve account" + content);
				Toast.makeText(MainActivity.this ,"retrieve account" + content ,Toast.LENGTH_LONG).show();
			}
		});

		account = editText01.getText().toString();
		password = editText02.getText().toString();
		content = "\naccount : " + account + "\npassword : " + password;

		Toast.makeText(this ,"onCreate..." + content ,Toast.LENGTH_LONG).show();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu )
	{
		getMenuInflater().inflate(R.menu.main ,menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item )
	{
		int id = item.getItemId();
		account = editText01.getText().toString();
		password = editText02.getText().toString();
		content = " :\naccount : " + account + "\npassword : " + password;
		switch(id)
		{
			case R.id.action_settings:
				System.out.println("设置" + content);
				// Toast.makeText(MainActivity.this ,"设置" + content
				// ,Toast.LENGTH_LONG).show();
				break;

			case R.id.mian_about01:
				System.out.println("关于" + content);
				// Toast.makeText(MainActivity.this ,"关于" + content
				// ,Toast.LENGTH_LONG).show();
				break;

			default:
				System.out.println("其他" + content);
				// Toast.makeText(MainActivity.this ,"其他" + content
				// ,Toast.LENGTH_LONG).show();
				break;
		}

		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onStart()
	{
		// TODO Auto-generated method stub
		super.onStart();

		account = editText01.getText().toString();
		password = editText02.getText().toString();
		content = "\naccount : " + account + "\npassword : " + password;

		Toast.makeText(this ,"onStart..." + content ,Toast.LENGTH_LONG).show();

	}

	@Override
	protected void onResume()
	{
		// TODO Auto-generated method stub
		super.onResume();

		account = editText01.getText().toString();
		password = editText02.getText().toString();
		content = "\naccount : " + account + "\npassword : " + password;

		Toast.makeText(this ,"onResume..." + content ,Toast.LENGTH_LONG).show();

	}

	@Override
	protected void onRestart()
	{
		// TODO Auto-generated method stub
		super.onRestart();

		account = editText01.getText().toString();
		password = editText02.getText().toString();
		content = "\naccount : " + account + "\npassword : " + password;

		Toast.makeText(this ,"onRestart..." + content ,Toast.LENGTH_LONG).show();

	}

	@Override
	protected void onPause()
	{
		// TODO Auto-generated method stub
		super.onPause();

		account = editText01.getText().toString();
		password = editText02.getText().toString();
		content = "\naccount : " + account + "\npassword : " + password;

		Toast.makeText(this ,"onPause..." + content ,Toast.LENGTH_LONG).show();
	}

	@Override
	protected void onStop()
	{
		// TODO Auto-generated method stub
		super.onStop();

		account = editText01.getText().toString();
		password = editText02.getText().toString();
		content = "\naccount : " + account + "\npassword : " + password;

		Toast.makeText(this ,"onStop..." + content ,Toast.LENGTH_LONG).show();

	}

	@Override
	protected void onDestroy()
	{
		super.onDestroy();

		account = editText01.getText().toString();
		password = editText02.getText().toString();
		content = "\naccount : " + account + "\npassword : " + password;

		// System.out.println("onDestroy is running...");
		Toast.makeText(MainActivity.this ,"onDestroy..." + content ,Toast.LENGTH_LONG).show();
		//
		// System.out.println(contents);
		// Toast.makeText(MainActivity.this ,contents
		// ,Toast.LENGTH_LONG).show();
		//
		// button01 = button02 = null;
		// editText01 = editText02 = null;
		// textView01 = textView02 = null;
		//
		// System.out.println(contents);
		// Toast.makeText(MainActivity.this ,contents
		// ,Toast.LENGTH_LONG).show();

		// System.out.println("onDestroy is running..." + content);
		// Toast.makeText(MainActivity.this ,"onDestroy is running..." + content
		// ,Toast.LENGTH_LONG).show();

	}

}
