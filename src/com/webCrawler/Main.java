package com.webCrawler;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		Main mat=new Main();
	}
	int n,l,h;
	LinkedList<StudentInfo> n1=new LinkedList<>();
	LinkedList<StudentInfo> n2=new LinkedList<>();
	LinkedList<StudentInfo> n3=new LinkedList<>();
	LinkedList<StudentInfo> n4=new LinkedList<>();
	StudentInfo[] si;
	public Main()
	{
		Initialize();
		ReadData();
		getGood();
		sort();	
	}
	
	public void Initialize()
	{
		Scanner in = new Scanner(System.in);
		while(in.hasNext())
		{
			n = in.nextInt();
            l = in.nextInt();
            h = in.nextInt();
		}

	}
	public void ReadData()
	{
		if(n<=0||n>100000)
		{
			System.out.println("数据输入有误！");
			Initialize();
		}
		else
		{ 	Scanner in = new Scanner(System.in);
			si=new StudentInfo[n];
			for(int i=0;i<n;i++)
			{
				int id=in.nextInt();
				int m=in.nextInt();
				int t=in.nextInt();
				si[i]=new StudentInfo(String.valueOf(id),m,t);
				
			}
			
		}
			
	}
	public void getGood()
	{
	
			for(int i=0;i<si.length;i++)
			{
				int m=si[i].getM();
				int t=si[i].getT();
				if(m>=h&&t>=h)
				{
					n1.add(si[i]);
				}
				else if(m>=h&&(t>=l&&t<h))
				{
					n2.add(si[i]);
				}
				else if(m>=l&&t>=l&&m>t)
				{
					n3.add(si[i]);
				}
				else if(m>=l&&t>=l)
				{
					n4.add(si[i]);
				}
			}
	
	}
	public void sort()
	{
		int index=0;
		int size=n1.size();
		StudentInfo temp=null;
		int first=n1.get(0).getTotal();
		for(int i=1;i<size;i++)
		{
			int last=n1.get(i-1).getTotal();
			temp=n1.get(i);
			if(temp.getTotal()>first)
				index=0;
			else if(temp.getTotal()<=last)
				index=i;
			else
				index=binartSearch(0,i-1,temp.getTotal(),n1);

			for(int j=i-1;j>=index;j--)
			{
				n1.set(j+1, n1.get(j));
			}
			n1.set(index, temp);
				
			
		}
		
	}
	public int binartSearch(int begin,int end,int dat,LinkedList<StudentInfo> list)
	{
		int left=begin;
		int right=end;
		int mid=(left+right)/2;
		int mdata=list.get(mid).getTotal();
		while(right-left>1)
		{
			mid=(left+right)/2;
			if(dat>=mdata)
				right=mid;
			else if(dat<mdata)
				left=mid;
		}
		return right;
		
	}

}
class StudentInfo
{
	private String id;
	private int m,t;
	public StudentInfo(String id,int m,int t)
	{
		this.id=id;
		this.m=m;
		this.t=t;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getM() {
		return m;
	}
	public void setM(int m) {
		this.m = m;
	}
	public int getT() {
		return t;
	}
	public void setT(int t) {
		this.t = t;
	}
	public int getTotal()
	{
		return m+t;
	}

	
}
