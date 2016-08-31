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
		// 用户输入用户名密码， 然后通过Get方法发送给本地服务器
		// String name = nameText.getText().toString();
		// String pwd = pwdText.getText().toString();

		// 使用GET方法向本地服务器发送数据
		// GetThread getThread = new GetThread(name, pwd);
		// getThread.start();

		// 使用POST方法向服务器发送数据
		// PostThread postThread = new PostThread(name , pwd);
		// postThread.start();

		PostThread postThread = new PostThread(name , pwd);
		// Log.d("LOG" ,name + "*************\n****************" + pwd);
		postThread.start();

	}

	// 子线程：通过GET方法向服务器发送用户名、密码的信息
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
			// 用HttpClient发送请求，分为五步
			// 第一步：创建HttpClient对象
			@SuppressWarnings("resource")
			HttpClient httpClient = new DefaultHttpClient();
			// 注意，下面这一行中，我之前把链接中的"test"误写成了"text"，导致调BUG调了半天没弄出来，真是浪费时间啊
			String url = "http://172.16.1.31:8080/test.jsp?name=" + name + "&password=" + pwd;

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
					Log.d("HTTP" ,"GET:" + result);

				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}

		}
	}

	// 子线程：使用POST方法向服务器发送用户名、密码等数据
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
			// 第二步：生成使用POST方法的请求对象
			HttpPost httpPost = new HttpPost(url);
			// NameValuePair对象代表了一个需要发往服务器的键值对
			NameValuePair pair1 = new BasicNameValuePair("name" , name);
			NameValuePair pair2 = new BasicNameValuePair("password" , pwd);
			// 将准备好的键值对对象放置在一个List当中
			ArrayList < NameValuePair > pairs = new ArrayList < NameValuePair >();
			pairs.add(pair1);
			pairs.add(pair2);
			try
			{
				// 创建代表请求体的对象（注意，是请求体）
				HttpEntity requestEntity = new UrlEncodedFormEntity(pairs);

//				Log.d("LOG" ,requestEntity);
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
						// Log.d("HTTP" ,"POST:" + result);
						Log.d("LOG" ,"服务器端响应：" + result);
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
