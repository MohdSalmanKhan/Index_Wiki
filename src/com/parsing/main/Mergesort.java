package com.parsing.main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Dictionary;

public class Mergesort {
	int Files;
	String folderpath;
	int []arr =new int[26];

	char check_file='a';
	public Mergesort(int count,String folderpath)
	{
		Files=count;
		this.folderpath=folderpath;
		merging();
		try
		{

	//		indexing = new FileWriter(folderpath+"/a.txt");
	//		alpha = new BufferedWriter(indexing);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
/*	public void openfile(char ch)
	{
		try
		{
			alpha.close();
			indexing=new FileWriter(folderpath+"/"+ch+".txt");
			alpha=new BufferedWriter(indexing);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}*/
	int flag=0;
/*	public void filetransfer(char ch,String str)
	{
		if(ch!='a')
			return;
		try{
		//	System.out.println("str="+str);
	//		if(ch!=check_file)
		//	{
		//		openfile(ch);
		//		check_file=ch;
		//	}
			
			if(flag==0)
			{
		//		indexing=new FileWriter(folderpath+"/"+ch+".txt");
			//	alpha=new BufferedWriter(indexing);
				flag=1;
				System.out.println("char="+ch);
			}
			System.out.println("value="+str);
			alpha.write(str);   //writing in alphabetical files alpha is indexed according to alphabets
			alpha.write("\n");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}*/
	public void merging()
	{
		int i;
	//	String pst=null;
		String change,min;
		Dictionarycreate dict = new Dictionarycreate(folderpath);
		Sparseindex sparseindex = new Sparseindex(folderpath);
		try
		{
		//	System.out.println("Files"+Files);
			Files=Files-1;
			BufferedReader[] brs = new BufferedReader[Files];
			for(i=1;i<=Files;i++)
			{			
				change=Integer.toString(i);
	//			System.out.println("In merge"+folderpath);
				brs[i-1] = new BufferedReader(new FileReader(folderpath+"/out"+change+".txt"));		
			}
			FileWriter fstream = new FileWriter(folderpath+"/final.txt");
			BufferedWriter out = new BufferedWriter(fstream);
			
			FileWriter indexing = new FileWriter(folderpath+"/a.txt");
			BufferedWriter alpha =new BufferedWriter(indexing);
			
			String str[] = new String[Files];
			String other[] =new String[Files];
			int j=0;
		
			for(i=1;i<=Files;i++)
			{
				if((change=brs[i-1].readLine())!=null)
				{	j=0;
					while(change.charAt(j)!=',')
					{
						j++;
					}
					str[i-1] = change.substring(0, j);
					other[i-1] = change.substring(j,change.length());
				}
			}
			while(true)
			{
				min=null;
				for(i=0;i<Files;i++)
				{
					if(str[i]!=null)
					{
						min=str[i];
						break;
					}
				}
				
				if(min==null)
				{		
					break;
				}
				for(i=1;i<=Files;i++)
				{
					if(str[i-1]==null)
						continue;
					if(str[i-1].compareTo(min) < 0)
					{
						min=str[i-1];
					}
				}
			//	out.write(min);
				
				
				if(min.charAt(0)==check_file)
				{
					alpha.write(min);
				}
				else
				{
					alpha.close();
					indexing = new FileWriter(folderpath+"/"+min.charAt(0)+".txt");
					alpha =new BufferedWriter(indexing);

					alpha.write(min);
					check_file=min.charAt(0);
				}
				
				
				
				
				
				
				
				for(i=1;i<=Files;i++)
				{	if(str[i-1]==null)
						continue;
					
					
					if(str[i-1].compareTo(min) == 0)
					{	
						
			//			filetransfer(min.charAt(0),min+other[i-1]);
				//		out.write(other[i-1]);  //writing in main file
						alpha.write(other[i-1]);
						if((change=brs[i-1].readLine())!=null)
						{
							j=0;
							while(change.charAt(j)!=',')
							{
								j++;
							}
							str[i-1] = change.substring(0, j);
							other[i-1] = change.substring(j,change.length());
						
						}
						else
						{
							str[i-1]=null;
							other[i-1]=null;
						}
					}
				}
		//		out.write("\n");
				alpha.write("\n");
			}
			out.close();
			alpha.close();
			dict.createdict();
			sparseindex.createsparse();
		}
		catch(Exception e)
		{
			System.out.println("Exception:"+e );
		}
	}
}