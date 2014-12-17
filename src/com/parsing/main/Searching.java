package com.parsing.main;


import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Searching {
	String value;
	String folderpath;
	public Searching(String target,String folderpath)
	{
		long beg=0;
		long end;
		long mid;
		int j;
		this.folderpath=folderpath;
		String line;
		System.out.println("Pased FolderPath : " + this.folderpath);
		
		try
		{
			RandomAccessFile file = new RandomAccessFile(this.folderpath+"/final.txt", "rw");
			String change=file.readLine();
		//	line = change.toCharArray();
			j=0;
			end=file.length();
			while(change.charAt(j)!=',')
			{
				j++;
			}
			line = change.substring(0, j);
			
			file.seek(0);
			if(line==null || line.compareToIgnoreCase(target)>0)
			{
				value=null;
			}
			else if( line.compareToIgnoreCase(target)==0)
			{
				value=change;
			}
			else
			{
				end=file.length();
				while(beg <= end)
				{
					mid=(beg)+(end-beg)/2;
					if(mid>0)						
						file.seek(mid-1);
					else
						file.seek(0);
					file.readLine();
					change = file.readLine();
			//		change=file.readLine();
					//	line = change.toCharArray();
					j=0;
					if(change==null)
					{	
						value=null;
						break;
					}
					while(change.charAt(j)!=',')
					{
						j++;
					}
					line = change.substring(0, j);
			//		System.out.println("Line="+change);
					if(line.compareToIgnoreCase(target)==0)
					{
						value=change;  
						break;
					}
					else if(line.compareToIgnoreCase(target)<0)
					{
						beg=mid+1;
					}
					else
					{
						end=mid-1;
					}
				}
			}
			if( beg > end)
			{
				value=null;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}