package com.parsing.main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.RandomAccessFile;

public class Dictionarycreate 
{

	String folderpath;
	
	public Dictionarycreate(String folderpath)
	{
		this.folderpath = folderpath;
	}
	
	public void createdict()
	{
		try
		{
			FileWriter fstream = new FileWriter(folderpath+"/dict_a.txt");
			BufferedWriter alpha =new BufferedWriter(fstream);
					
			BufferedReader brs = new BufferedReader(new FileReader(folderpath+"/a.txt"));
			String line,str;
			char ch='a';
			int flag=0;
			while(true)
			{
				int offset=0,j;
				
				
				while ((line = brs.readLine()) != null)
				{
					j=0;
		//		System.out.println("len="+line.length());
					while(line.charAt(j)!=',')
					{
						j++;
					}
					str=line.substring(0, j);
					alpha.write(str);
					alpha.write(","+offset+"\n");
					offset=offset+line.length()+1;
				}
				ch = (char)((int)ch + 1);
			//	System.out.println("ch="+ch);
	//			if(flag==1)
		//			break;
		//		if(ch=='{')
		//			break;
				alpha.close();
				if(ch<='z')
				{
					fstream = new FileWriter(folderpath+"/dict_"+ch+".txt");
					alpha =new BufferedWriter(fstream);
					brs = new BufferedReader(new FileReader(folderpath+"/"+ch+".txt"));
				}
				if(ch=='{')
					break;
			}	

			// For checking 
			/* 
			alpha.close();
			brs = new BufferedReader(new FileReader(folderpath+"/dict_a.txt"));
			line=brs.readLine();
			line=brs.readLine();
			line=brs.readLine();
			j=0;
			System.out.println("len="+line.length());
			while(line.charAt(j)!=',')
			{
				j++;
			}
			str=line.substring(j+1, line.length());
			System.out.println("str="+str);			
			int number=Integer.parseInt(str);
		//	brs = new BufferedReader(new FileReader(folderpath+"/a.txt"));
		    RandomAccessFile r  = new RandomAccessFile(folderpath+"/a.txt","r");
            //System.out.println("offset="+offset+"by="+bytesToBeRead);
            r.seek(number);
            int content=0;
            str="";
            while ((content = r.readByte() ) != -1)
            {
                // convert to char and display it
                //System.out.println((char) content);
                

                if((char)content == '\n')
                    break;
                str+=(char)content;
                 
            }
            System.out.println("str="+str);
*/
            
            
            

			
//			brs[i-1] = new BufferedReader(new FileReader(folderpath+"/out"+change+".txt"));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
