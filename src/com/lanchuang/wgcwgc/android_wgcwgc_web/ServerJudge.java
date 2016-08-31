package com.lanchuang.wgcwgc.android_wgcwgc_web;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.util.Log;

@SuppressWarnings("deprecation")
public class ServerJudge
{
	String username;
	String password;

	public ServerJudge()
	{

	}

	public ServerJudge(String username , String password)
	{
		this.username = username;
		this.password = password;
		new GetThread(username , password).start();
//		Log.d("LOG" , "run() ִ���ˣ�����");
	}

	public boolean serverJudge(String username , String password )
	{
		// ʹ��GET�����򱾵ط�������������
		// GetThread getThread = new GetThread(username, password);
		Log.d("LOG" , "run() ��ʼִ�У�����");
		if(new GetThread().running(username ,password))
		{
			return true;
		}
		return false;

		// ʹ��POST�������������������
		// PostThread postThread = new PostThread(username , password);
		// if(new PostThread().running(username ,password))
		// {
		// return true;
		// }
		// return false;

	}
}

// ���̣߳�ͨ��GET����������������û������������Ϣ
class GetThread extends Thread
{

	String username;
	String password;

	public GetThread()
	{

	}

	public GetThread(String username , String password)
	{
		this.username = username;
		this.password = password;
		 run();
	}

	@Override
	public void run()
	{
		// ��HttpClient�������󣬷�Ϊ�岽
		// ��һ��������HttpClient����
		@SuppressWarnings(
		{ "deprecation", "resource" })
		HttpClient httpClient = new DefaultHttpClient();
		String url = "http://172.16.1.31:8080/test.jsp?username=" + username + "&password=" + password;
		// http://172.16.1.31:8080/test.jsp?name=wgcwgc&password=wgc
		// �ڶ�����������������Ķ���,�����Ƿ��ʵķ�������ַ
		HttpGet httpGet = new HttpGet(url);
		try
		{
			// ��������ִ�����󣬻�ȡ��������������Ӧ����
			HttpResponse response = httpClient.execute(httpGet);
			// ���Ĳ��������Ӧ��״̬�Ƿ����������״̬���ֵ��200��ʾ����
			if(response.getStatusLine().getStatusCode() == 200)
			{
				// ���岽������Ӧ������ȡ�����ݣ��ŵ�entity����
				HttpEntity entity = response.getEntity();
				BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent()));
				String result = reader.readLine();
				Log.d("LOG" ,"GET:" + result);
			}
		}
		catch(Exception e)
		{
			try
			{
				Log.d("LOG" ,"GET:�����������쳣01������" + httpClient.execute(httpGet).getStatusLine().getStatusCode());
			}
			catch(Exception e1)
			{
				Log.d("LOG" , "�˰��磡����");
			}
		}

	}

	public boolean running(String username , String password )
	{
		// ��HttpClient�������󣬷�Ϊ�岽
		// ��һ��������HttpClient����
		@SuppressWarnings(
		{ "deprecation", "resource" })
		HttpClient httpClient = new DefaultHttpClient();
		String url = "http://172.16.1.31:8080/test.jsp?username=" + username + "&password=" + password;
		// http://172.16.1.31:8080/test.jsp?name=wgcwgc&password=wgc
		// �ڶ�����������������Ķ���,�����Ƿ��ʵķ�������ַ
		HttpGet httpGet = new HttpGet(url);
		try
		{
			// ��������ִ�����󣬻�ȡ��������������Ӧ����
			HttpResponse response = httpClient.execute(httpGet);
			// ���Ĳ��������Ӧ��״̬�Ƿ����������״̬���ֵ��200��ʾ����
			if(response.getStatusLine().getStatusCode() == 200)
			{
				// ���岽������Ӧ������ȡ�����ݣ��ŵ�entity����
				HttpEntity entity = response.getEntity();
				BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent()));
				String result = reader.readLine();
				Log.d("LOG" ,"GET:" + result);
				return true;
			}
		}
		catch(Exception e)
		{
			try
			{
				Log.d("LOG" ,"GET:�����������쳣01������" + httpClient.execute(httpGet).getStatusLine().getStatusCode());
			}
			catch(Exception e1)
			{
				e1.printStackTrace();
			}
		}

		return false;
	}
}

// ���̣߳�ʹ��POST����������������û��������������
class PostThread extends Thread
{

	String username;
	String password;

	public PostThread()
	{

	}

	public PostThread(String username , String password)
	{
		this.username = username;
		this.password = password;
	}

	public boolean running(String username , String password )
	{
		@SuppressWarnings(
		{ "deprecation", "resource" })
		HttpClient httpClient = new DefaultHttpClient();
		String url = "http://172.16.1.31:8080/test.jsp";
		// �ڶ���������ʹ��POST�������������
		HttpPost httpPost = new HttpPost(url);
		// NameValuePair���������һ����Ҫ�����������ļ�ֵ��
		NameValuePair pair1 = new BasicNameValuePair("username" , username);
		NameValuePair pair2 = new BasicNameValuePair("password" , password);
		// ��׼���õļ�ֵ�Զ��������һ��List����
		ArrayList < NameValuePair > pairs = new ArrayList < NameValuePair >();
		pairs.add(pair1);
		pairs.add(pair2);
		try
		{
			// ��������������Ķ���ע�⣬�������壩
			HttpEntity requestEntity = new UrlEncodedFormEntity(pairs);
			// ����������������������
			httpPost.setEntity(requestEntity);
			// ִ���������
			try
			{
				// ��������ִ��������󣬻�ȡ��������������Ӧ����
				HttpResponse response = httpClient.execute(httpPost);
				// ���Ĳ��������Ӧ��״̬�Ƿ����������״̬���ֵ��200��ʾ����
				if(response.getStatusLine().getStatusCode() == 200)
				{
					// ���岽������Ӧ������ȡ�����ݣ��ŵ�entity����
					HttpEntity entity = response.getEntity();
					BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent()));
					String result = reader.readLine();
					Log.d("LOG" ,"POST:" + result);
					return true;
				}
				return true;
			}
			catch(Exception e)
			{
				Log.d("LOG" ,"POST:�����������쳣01������" + httpClient.execute(httpPost).getStatusLine().getStatusCode());
			}
		}
		catch(Exception e)
		{
			Log.d("LOG" ,"POST:�����������쳣02������");
		}

		return false;

	}
}
