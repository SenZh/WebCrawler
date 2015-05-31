package com.webCrawler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
@SuppressWarnings("deprecation")
public class HttpClientDemo {

public static void main(String[] args) {
	HttpClientDemo demo=new HttpClientDemo();
	demo.getContent("http://cas.hdu.edu.cn/cas/login");
	//demo.getHtml("http://career.hdu.edu.cn");

	}
	public void getContent(String url)
	{
		HttpClient client=new DefaultHttpClient();
		//HttpGet postHttp=new HttpGet(url);
		HttpPost postHttp=new HttpPost(url);
		HttpResponse response;
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();  
        nvps.add(new BasicNameValuePair("username", "132040173")); 
        nvps.add(new BasicNameValuePair("password", "a435fd7317a9c2ed256361247785b8aa")); 
        nvps.add(new BasicNameValuePair("autoLogin", "true")); 
        nvps.add(new BasicNameValuePair("It", "LT-152509-lmrck6Blr7sabA3NzLEX")); 
        nvps.add(new BasicNameValuePair("servicename","null"));
        nvps.add(new BasicNameValuePair("loginErrCnt", "0"));
      /*    nvps.add(new BasicNameValuePair("service", "http://career.hdu.edu.cn/f/v/54/281"));
        
        nvps.add(new BasicNameValuePair("login", "µÇÂ¼"));
        nvps.add(new BasicNameValuePair("encodedService", "http%3a%2f%2fcareer.hdu.edu.cn%2fdocas%3fs%3d%2ff%2fv%2f54%2f281"));
        
        */
		try {
			postHttp.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));  
			response=client.execute(postHttp);
			HttpEntity entity=response.getEntity();
			if(entity!=null)
			{
				System.out.println(EntityUtils.toString(entity));
				EntityUtils.consume(entity);  
			}
			
		} catch (IOException e) {

			e.printStackTrace();
		}
		finally
		{
		
		}
	}
	
	public void getHtml(String url)
	{
		try {
			Document doc=Jsoup.connect(url).userAgent("Chrome").post();
			Element body=doc.body();
			Elements all=body.getElementsByTag("a");
			Elements list=body.select("div.sublist li");
			for(Element li:list)
			{
				Elements children=li.children();
			   
			for(Element child:children)
				{
					if("type".equals(child.attr("class")))
					{
						if("".equals(child.attr("href")))
						{
							break;
						}
							
							 
						else
							System.out.print("type:"+child.text());
							
					}
					if("title".equals(child.attr("class")))
					{
						
							System.out.print("  title:"+child.attr("title"));
							System.out.print("  href:"+child.attr("href"));
						
							
					}
					if("data".equals(child.attr("class")))
					{
							System.out.println("   date:"+child.text());
						
					}
				}

			}
			
	
	/*		for(Element e:all)
			{
				e.
			//	System.out.println(e.html());
				System.out.println(body.html());
			}
			
			*/
		
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}

}
