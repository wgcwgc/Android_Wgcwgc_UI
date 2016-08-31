package com.lanchuang.wgc.android_wgcwgc_Sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AccountHelper extends SQLiteOpenHelper
{
	private static final String DATABASE_NAME = "wgcwgc";// 数据库名称
	// private static final String TABLE_NAME = "wgcwgc";// 表名称
	private static final int SCHEMA_VERSION = 2;// 版本号,则是升级之后的,升级方法请看onUpgrade方法里面的判断
	// private static final String LOG = "LOG";

	public AccountHelper(Context context)
	{// 构造函数,接收上下文作为参数,直接调用的父类的构造函数
		super(context , DATABASE_NAME , null , SCHEMA_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db )
	{// 创建的是一个午餐订餐的列表,id,菜名,地址等等
		db.execSQL("CREATE TABLE account (_id INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT , password TEXT, address TEXT, type TEXT, notes TEXT, phoneNumber TEXT);");
		// db.execSQL("CREATE TABLE restaurants (_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, address TEXT, type TEXT, notes TEXT, phone TEXT);");
		// String wgc ="wgc";
		// insert(wgc ,wgc ,wgc ,wgc ,wgc ,wgc);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db , int oldVersion , int newVersion )
	{
		if(oldVersion == 1 && newVersion == 2)
		{// 升级判断,如果再升级就要再加两个判断,从1到3,从2到3
			db.execSQL("ALTER TABLE account ADD phone TEXT;");
		}
	}

	public void insert(String username , String password , String address , String type , String notes , String phoneNumber )
	{
		ContentValues cv = new ContentValues();

		cv.put("username" ,username);
		cv.put("password" ,password);
		cv.put("address" ,address);
		cv.put("type" ,type);
		cv.put("notes" ,notes);
		cv.put("phoneNumber" ,phoneNumber);

		getWritableDatabase().insert("account" ,"username" ,cv);
	}

	public void update(String username , String password , String address , String type , String notes , String phoneNumber )
	{
		ContentValues cv = new ContentValues();
		String [] args =
		{ username };

		cv.put("username" ,username);
		cv.put("password" ,password);
		cv.put("address" ,address);
		cv.put("type" ,type);
		cv.put("notes" ,notes);
		cv.put("phoneNumber" ,phoneNumber);

		getWritableDatabase().update("account" ,cv ,"username=?" ,args);
	}

	public Cursor getAll(String where , String orderBy )
	{// 返回表中的数据,where是调用时候传进来的搜索内容,orderby是设置中传进来的列表排序类型
		StringBuilder buf = new StringBuilder("SELECT _id , username, password, address, type, notes, phoneNumber FROM account");

		if(where != null)
		{
			buf.append(" WHERE ");
			buf.append(where);
		}

		if(orderBy != null)
		{
			buf.append(" ORDER BY ");
			buf.append(orderBy);
		}

		return (getReadableDatabase().rawQuery(buf.toString() ,null));
	}

	public Cursor getAllByUsername(String username )
	{
		String [] args =
		{ username };

		return (getReadableDatabase().rawQuery("SELECT _id , username, password , address, type, notes, phoneNumber FROM account WHERE username=?" ,args));
	}

	public String getUsernameByPhoneNumber(String phoneNumber )
	{
		Cursor usernameCursor = getReadableDatabase().query("account" ,null ,null ,null ,null ,null ,null);
		while(usernameCursor.moveToNext())
		{
			if(usernameCursor.getString(6).equals(phoneNumber))
			{
				return usernameCursor.getString(1);
			}
		}
//		Log.d(LOG ,"测试getUsernameByPhoneNumber");
		usernameCursor.close();
		return null;
	}

	public boolean getUsernameIsExist(String username )
	{
		Cursor usernameCursor = getReadableDatabase().query("account" ,null ,null ,null ,null ,null ,null);
		while(usernameCursor.moveToNext())
		{
			if(usernameCursor.getString(1).equals(username))
			{
				return true;
			}
		}
		usernameCursor.close();
		return false;
	}

	public String getPasswordByUsername(String username )
	{
		Cursor usernameCursor = getReadableDatabase().query("account" ,null ,null ,null ,null ,null ,null);
		while(usernameCursor.moveToNext())
		{
			if(usernameCursor.getString(1).equals(username))
			{
				return usernameCursor.getString(2);
			}
		}
		usernameCursor.close();
		return null;
	}

	public String getPasswordByUsernameAndphoneNumber(String username , String phoneNumber )
	{
		Cursor usernameCursor = getReadableDatabase().query("account" ,null ,null ,null ,null ,null ,null);
		while(usernameCursor.moveToNext())
		{
			if(usernameCursor.getString(6).equals(phoneNumber) && usernameCursor.getString(1).equals(username))
			{
				return usernameCursor.getString(2);
			}
		}
		usernameCursor.close();
		return null;
		// if(phoneNumberString.equals(phoneNumber))
		// {
		// return getpassword(passwordCursor);
		// }
		//
		// return null;
	}

	// public String getusername(Cursor c )
	// {
	// return (c.getString(1));
	// }
	//
	// public String getpassword(Cursor c )
	// {
	// return (c.getString(2));
	// }
	//
	// public String getAddress(Cursor c )
	// {
	// return (c.getString(3));
	// }
	//
	// public String getType(Cursor c )
	// {
	// return (c.getString(4));
	// }
	//
	// public String getNotes(Cursor c )
	// {
	// return (c.getString(5));
	// }
	//
	// public String getPhoneNumber(Cursor c )
	// {
	// return (c.getString(6));
	// }

	// public void query(SQLiteDatabase db , String tableName , int index)
	// {
	//
	// // 查询获得游标
	// Cursor cursor = db.query("account" ,null ,null ,null ,null ,null ,null);
	// // 判断游标是否为空
	// if(cursor.moveToFirst())
	// {
	// // 遍历游标
	// for(int i = 0 ; i < cursor.getCount() ; i ++ )
	// {
	// cursor.move(i);
	// // 获得ID
	// int id = cursor.getInt(0);
	// // 获得用户名
	// String username = cursor.getString(1);
	// // 获得密码
	// String password = cursor.getString(2);
	//
	// }
	// }
	// }
}
