package com.webCrawler;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
@SuppressWarnings("deprecation")
public class HttpClientDemo {

public static void main(String[] args) {
	HttpClientDemo demo=new HttpClientDemo();
	demo.getContent("http://career.hdu.edu.cn//f/i/clist?cid=2&pageNo=18");
	//	demo.getHtml("http://career.hdu.edu.cn");

	}
	public void getContent(String url)
	{
		HttpClient client=new DefaultHttpClient();
		HttpGet getHttp=new HttpGet(url);
		HttpResponse response;
		try {
			response=client.execute(getHttp);
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
			for(Element e:all)
			{
				
				System.out.println(e.html());
			}
		
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}

}
