package com.lanchuang.wgc.android_wgcwgc_register;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.lanchuang.wgc.android_wgcwgc_Sqlite.AccountHelper;
import com.lanchuang.wgc.android_wgcwgc_ui.MainActivity;
import com.lanchuang.wgc.android_wgcwgc_ui.R;

public class Register extends Activity
{

	// private static final String LOG = "LOG";
	private EditText editText01 , editText02 , editText03 , editText04 ,
	        editText05 , editText06 , editText07;
	private String username , password01 , password02 , address , type , notes ,
	        phoneNumber;

	@Override
	public void onCreate(Bundle savedInstanceState )
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);

	}

	public void okButtonClick(View view )
	{
		editText01 = (EditText) findViewById(R.id.register_username);
		editText02 = (EditText) findViewById(R.id.register_password01);
		editText03 = (EditText) findViewById(R.id.register_password02);
		editText04 = (EditText) findViewById(R.id.register_address);
		editText05 = (EditText) findViewById(R.id.register_type);
		editText06 = (EditText) findViewById(R.id.register_notes);
		editText07 = (EditText) findViewById(R.id.register_phoneNumber);

		username = editText01.getText().toString();
		password01 = editText02.getText().toString();
		password02 = editText03.getText().toString();
		address = editText04.getText().toString();
		type = editText05.getText().toString();
		notes = editText06.getText().toString();
		phoneNumber = editText07.getText().toString();

		String contents = "请仔细核对信息\n账号：\n\t\t\t\t" + username + "\n密码：\n\t\t\t\t" + password01 + "\n地址：\n\t\t\t\t" + address + "\n类型：\n\t\t\t\t" + type + "\n备注：\n\t\t\t\t" + notes + "\n手机号：\n\t\t\t\t" + phoneNumber;
//		System.out.println(contents);
		Toast.makeText(this ,contents ,Toast.LENGTH_LONG).show();

		if(judge(username.trim() ,password01.trim() ,password02.trim() ,address.trim() ,type.trim() ,notes.trim() ,phoneNumber.trim()))
		{
			// System.out.println("恭喜您：注册成功！！！");
			Toast.makeText(this ,"恭喜您： " + "\n\t\t\t\t注册成功！！！" ,Toast.LENGTH_LONG).show();
			finish();
		}

	}

	private boolean judge(String username , String password01 , String password02 , String address , String type , String notes , String phoneNumber )
	{
		try
		{
			AccountHelper helper = new AccountHelper(this);

			if(username.isEmpty() || password01.isEmpty() || password02.isEmpty() || address.isEmpty() || type.isEmpty() || notes.isEmpty() || phoneNumber.isEmpty())
			{
				Toast.makeText(Register.this ,"填写信息不能为空！！！" ,Toast.LENGTH_LONG).show();
				return false;
			}
			// Log.d(LOG ,username + "" + password01 + address + type + notes +
			// phoneNumber);
			String sqliteUsername = helper.getUsernameByPhoneNumber(phoneNumber);
			// Toast.makeText(Register.this ,"测试1" ,Toast.LENGTH_LONG).show();
			// Log.d(LOG ,"asdf" + sqliteUsername + "asdf");
			if(sqliteUsername != null || username.equals(sqliteUsername))
			{
				Toast.makeText(Register.this ,"该手机号已被注册！！！" ,Toast.LENGTH_LONG).show();
				return false;
			}
			// Toast.makeText(Register.this ,"测试2" ,Toast.LENGTH_LONG).show();
			if( !password01.equals(password02) || password01.length() < 7 || password01.length() > 17)
			{
				Toast.makeText(Register.this ,"密码输入有误！！！" ,Toast.LENGTH_LONG).show();
				return false;
			}

			if(helper.getUsernameIsExist(username))
			{
				Toast.makeText(Register.this ,"该用户已存在！！！\n请前往登录。" ,Toast.LENGTH_LONG).show();
				return false;
			}

			helper.insert(username ,password01 ,address ,type ,notes ,phoneNumber);

			// Toast.makeText(Register.this ,"没有注册成功！"
			// ,Toast.LENGTH_LONG).show();
		}
		catch(Exception e)
		{
			System.out.println("访问数据库失败！！！\n信息填写错误！！！");
			Toast.makeText(Register.this ,"访问数据库失败！！！\n信息填写错误！！！" ,Toast.LENGTH_LONG).show();
		}

		return true;

	}

	public void cancelButtonClick(View view )
	{
		Intent intent = new Intent();
		intent.setClass(Register.this ,MainActivity.class);
		startActivity(intent);
		Register.this.closeOptionsMenu();
		finish();
	}
}
