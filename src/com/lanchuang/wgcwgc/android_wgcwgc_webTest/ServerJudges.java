package com.lanchuang.wgcwgc.android_wgcwgc_webTest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URLEncoder;
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
public class ServerJudges
{

	public ServerJudges()
	{

	}

	public ServerJudges(String name , String pwd)
	{
		// �û������û������룬 Ȼ��ͨ��Get�������͸����ط�����
		// String name = nameText.getText().toString();
		// String pwd = pwdText.getText().toString();

		// ʹ��GET�����򱾵ط�������������
		// GetThread getThread = new GetThread(name, pwd);
		// getThread.start();

		// ʹ��POST�������������������
		// PostThread postThread = new PostThread(name , pwd);
		// postThread.start();

		PostThread postThread = new PostThread(name , pwd);
		// Log.d("LOG" ,name + "*************\n****************" + pwd);
		postThread.start();

	}

	// ���̣߳�ͨ��GET����������������û������������Ϣ
	class GetThread extends Thread
	{

		String name;
		String pwd;

		public GetThread(String name , String pwd)
		{
			this.name = name;
			this.pwd = pwd;
		}

		@Override
		public void run()
		{
			// ��HttpClient�������󣬷�Ϊ�岽
			// ��һ��������HttpClient����
			@SuppressWarnings("resource")
			HttpClient httpClient = new DefaultHttpClient();
			// ע�⣬������һ���У���֮ǰ�������е�"test"��д����"text"�����µ�BUG���˰���ûŪ�����������˷�ʱ�䰡
			String url = "http://172.16.1.31:8080/test.jsp?name=" + name + "&password=" + pwd;

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
					Log.d("HTTP" ,"GET:" + result);

				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}

		}
	}

	// ���̣߳�ʹ��POST����������������û��������������
	class PostThread extends Thread
	{

		String name;
		String pwd;

		public PostThread(String name , String pwd)
		{
			this.name = name;
			this.pwd = pwd;
		}

		@Override
		public void run()
		{
			@SuppressWarnings("resource")
			HttpClient httpClient = new DefaultHttpClient();
			String url = "http://172.16.1.31:8080/test.jsp";
//			url = toURLEncoded(url);
			// �ڶ���������ʹ��POST�������������
			HttpPost httpPost = new HttpPost(url);
			// NameValuePair���������һ����Ҫ�����������ļ�ֵ��
			NameValuePair pair1 = new BasicNameValuePair("name" , name);
			NameValuePair pair2 = new BasicNameValuePair("password" , pwd);
			// ��׼���õļ�ֵ�Զ��������һ��List����
			ArrayList < NameValuePair > pairs = new ArrayList < NameValuePair >();
			pairs.add(pair1);
			pairs.add(pair2);
			try
			{
				// ��������������Ķ���ע�⣬�������壩
				HttpEntity requestEntity = new UrlEncodedFormEntity(pairs);

//				Log.d("LOG" ,requestEntity);
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
						// Log.d("HTTP" ,"POST:" + result);
						Log.d("LOG" ,"����������Ӧ��" + result);
						// Toast.makeText(ServerJudges.this ,result
						// ,Toast.LENGTH_LONG).show();
						// System.out.println(result);
					}
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}

		public String toURLEncoded(String paramString )
		{
			if(paramString == null || paramString.equals(""))
			{
				// LogD("toURLEncoded error:"+paramString);
				Log.d("LOG" ,"toURLEncoded error:" + paramString);
				return "";
			}

			try
			{
				String str = new String(paramString.getBytes() , "UTF-8");
				str = URLEncoder.encode(str ,"UTF-8");
				Log.d("LOG" , str);
				return str;
			}
			catch(Exception localException)
			{
				// LogE("toURLEncoded error:"+paramString, localException);
				Log.d("LOG" ,"toURLEncoded error:" + paramString);
			}

			return "";
		}

	}
}
