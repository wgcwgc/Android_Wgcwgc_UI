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
//		Log.d("LOG" , "run() 执行了！！！");
	}

	public boolean serverJudge(String username , String password )
	{
		// 使用GET方法向本地服务器发送数据
		// GetThread getThread = new GetThread(username, password);
		Log.d("LOG" , "run() 开始执行！！！");
		if(new GetThread().running(username ,password))
		{
			return true;
		}
		return false;

		// 使用POST方法向服务器发送数据
		// PostThread postThread = new PostThread(username , password);
		// if(new PostThread().running(username ,password))
		// {
		// return true;
		// }
		// return false;

	}
}

// 子线程：通过GET方法向服务器发送用户名、密码的信息
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
		// 用HttpClient发送请求，分为五步
		// 第一步：创建HttpClient对象
		@SuppressWarnings(
		{ "deprecation", "resource" })
		HttpClient httpClient = new DefaultHttpClient();
		String url = "http://172.16.1.31:8080/test.jsp?username=" + username + "&password=" + password;
		// http://172.16.1.31:8080/test.jsp?name=wgcwgc&password=wgc
		// 第二步：创建代表请求的对象,参数是访问的服务器地址
		HttpGet httpGet = new HttpGet(url);
		try
		{
			// 第三步：执行请求，获取服务器发还的相应对象
			HttpResponse response = httpClient.execute(httpGet);
			// 第四步：检查相应的状态是否正常：检查状态码的值是200表示正常
			if(response.getStatusLine().getStatusCode() == 200)
			{
				// 第五步：从相应对象当中取出数据，放到entity当中
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
				Log.d("LOG" ,"GET:服务器连接异常01！！！" + httpClient.execute(httpGet).getStatusLine().getStatusCode());
			}
			catch(Exception e1)
			{
				Log.d("LOG" , "八阿哥！！！");
			}
		}

	}

	public boolean running(String username , String password )
	{
		// 用HttpClient发送请求，分为五步
		// 第一步：创建HttpClient对象
		@SuppressWarnings(
		{ "deprecation", "resource" })
		HttpClient httpClient = new DefaultHttpClient();
		String url = "http://172.16.1.31:8080/test.jsp?username=" + username + "&password=" + password;
		// http://172.16.1.31:8080/test.jsp?name=wgcwgc&password=wgc
		// 第二步：创建代表请求的对象,参数是访问的服务器地址
		HttpGet httpGet = new HttpGet(url);
		try
		{
			// 第三步：执行请求，获取服务器发还的相应对象
			HttpResponse response = httpClient.execute(httpGet);
			// 第四步：检查相应的状态是否正常：检查状态码的值是200表示正常
			if(response.getStatusLine().getStatusCode() == 200)
			{
				// 第五步：从相应对象当中取出数据，放到entity当中
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
				Log.d("LOG" ,"GET:服务器连接异常01！！！" + httpClient.execute(httpGet).getStatusLine().getStatusCode());
			}
			catch(Exception e1)
			{
				e1.printStackTrace();
			}
		}

		return false;
	}
}

// 子线程：使用POST方法向服务器发送用户名、密码等数据
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
		// 第二步：生成使用POST方法的请求对象
		HttpPost httpPost = new HttpPost(url);
		// NameValuePair对象代表了一个需要发往服务器的键值对
		NameValuePair pair1 = new BasicNameValuePair("username" , username);
		NameValuePair pair2 = new BasicNameValuePair("password" , password);
		// 将准备好的键值对对象放置在一个List当中
		ArrayList < NameValuePair > pairs = new ArrayList < NameValuePair >();
		pairs.add(pair1);
		pairs.add(pair2);
		try
		{
			// 创建代表请求体的对象（注意，是请求体）
			HttpEntity requestEntity = new UrlEncodedFormEntity(pairs);
			// 将请求体放置在请求对象当中
			httpPost.setEntity(requestEntity);
			// 执行请求对象
			try
			{
				// 第三步：执行请求对象，获取服务器发还的相应对象
				HttpResponse response = httpClient.execute(httpPost);
				// 第四步：检查相应的状态是否正常：检查状态码的值是200表示正常
				if(response.getStatusLine().getStatusCode() == 200)
				{
					// 第五步：从相应对象当中取出数据，放到entity当中
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
				Log.d("LOG" ,"POST:服务器连接异常01！！！" + httpClient.execute(httpPost).getStatusLine().getStatusCode());
			}
		}
		catch(Exception e)
		{
			Log.d("LOG" ,"POST:服务器连接异常02！！！");
		}

		return false;

	}
}
