package com.parsing.main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class Sparseindex 
{

	int limit=50;
	String folderpath;
	public Sparseindex(String folderpath)
	{
		this.folderpath = folderpath;
	}
	public void createsparse()
	{
		
		
		try
		{
			FileWriter fstream = new FileWriter(folderpath+"/sparse_a.txt");
			BufferedWriter alpha =new BufferedWriter(fstream);
					
			BufferedReader brs = new BufferedReader(new FileReader(folderpath+"/dict_a.txt"));
			String line,str;
			char ch='a';
			while(true)
			{
				int offset=0,j,number=0;
				
				
				while ((line = brs.readLine()) != null)
				{
					j=0;
		//		System.out.println("len="+line.length());
					while(line.charAt(j)!=',')
					{
						j++;
					}
					str=line.substring(0, j);
					if(number==200)
						number=0;
					if(number==0)
					{
						alpha.write(str);
						alpha.write(","+offset+"\n");
					}
					offset=offset+line.length()+1;
					number=number+1;
				}
				ch = (char)((int)ch + 1);
				alpha.close();
				if(ch<='z')
				{
					fstream = new FileWriter(folderpath+"/sparse_"+ch+".txt");
					alpha =new BufferedWriter(fstream);
					brs = new BufferedReader(new FileReader(folderpath+"/"+"dict_"+ch+".txt"));
				}
				if(ch=='{')
					break;
			}	
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
}
