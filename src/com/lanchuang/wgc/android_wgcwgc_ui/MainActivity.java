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
 * �м����������������£� 1. ����HTTP�������ͨ�ţ���������GET/POST���ύ����Ҫ��url
 * encode���ύ�����ݺͷ��ص����ݲ���JSON��ʽ��Ҫѧ����֯�ͽ��� 2. ����Web
 * View��ʽ��HTML5����ҳ������ȷ��ʾ��ҳ���ݣ��ṩ��ť������һҳ��ֱ�ӹرգ�����΢����Ƕ�����ҳһ�� 3.
 * ���ղ��ű�����Ƶ�ļ�����������Ƶ���Ĳ������������š�ֹͣ��������϶���������ͨ����˷�¼�����洢�ļ��ķ����������ļ��ϴ��������ķ�����
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
	 * �˺ź�������պ� �� ���ݿ⴦��Ա� �������ݿ� + ��ȡ���Ա�����
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
//				System.out.println("�ɹ��������ݿ⣡����");
				Log.d("LOG" ,username + "  �ɹ��������ݿ⣡����");
				// Toast.makeText(this ,"�ɹ��������ݿ⣡����" ,Toast.LENGTH_LONG).show();
				return true;
			}
		}
		catch(Exception e)
		{
//			System.out.println("�˺Ż���������󣡣���");
			Log.d("LOG" ,"�˺Ż���������󣡣���");
			Toast.makeText(MainActivity.this ,"�˺Ż���������󣡣���" ,Toast.LENGTH_LONG).show();
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
		 * ��¼��ť����
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
				 *         ����������ӷ��ʷ��������жϺͷ���
				 *         ************************************
				 *         **************************************
				 */

				// ServerJudge sj = new ServerJudge();
				// if()
				// {
				// Log.d("LOG" , "�ɹ����ӷ�����������");
				// }
				Log.d("LOG" ,"�û����룺  account:" + account + "  password:" + password);
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
//					System.out.println("�˺Ż���������󣡣���");
					Log.d("LOG" ,"�˺Ż���������󣡣���");
					Toast.makeText(MainActivity.this ,"�˺Ż���������󣡣���" ,Toast.LENGTH_SHORT).show();
				}
			}

		});

		/**
		 * 
		 * ע�ᰴť����
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
		 * �������룿TextView �����¼���Ӧ
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
		 * �һ��˺ţ� TextView �����¼���Ӧ
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
				System.out.println("����" + content);
				// Toast.makeText(MainActivity.this ,"����" + content
				// ,Toast.LENGTH_LONG).show();
				break;

			case R.id.mian_about01:
				System.out.println("����" + content);
				// Toast.makeText(MainActivity.this ,"����" + content
				// ,Toast.LENGTH_LONG).show();
				break;

			default:
				System.out.println("����" + content);
				// Toast.makeText(MainActivity.this ,"����" + content
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
