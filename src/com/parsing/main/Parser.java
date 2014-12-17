package com.parsing.main;

import java.io.*;
import java.util.*;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;
import java.util.Scanner;


class count
{
	int title;
	int infobox;
	int category;
	int body;
	int external;
}

public class Parser extends DefaultHandler {
		int id= 0;
		int title = 0;
		int text = 0;
		int fnum=1;
		int count_val=0;
		String value;
		String folderpath;
		long startTime ;
		String query;
		Map<String, HashMap<String,Integer>> treeMap ;
		StringBuilder value_text;
		String value_id;
		String value_title;
		Stemmer stemmer ;
		static HashSet <String> hs = new HashSet<String>();
		Map<String,TreeMap<String,count>> hm = new HashMap<String, TreeMap<String , count>>();
		
		public static StringBuilder infobox;
		public static StringBuilder body;
		public static StringBuilder external;
		public static StringBuilder category;
		
		count freq ;
	      // add elements to the hash set
	
	/*	public String result()
		{
			
			while(true)
	    	{
	    		Scanner in = new Scanner(System.in);
	    		String s;
	    		System.out.println("Enter a string");
	    		s = in.nextLine();
	    	
	    		hm.clear();
	    
	    		s=s.toLowerCase();
	    		
	    		tokenize(s);
	    
	    		search_word();
	    		System.out.println("You entered string query"+query);
	    		if(query==null)
	    		{	
	    			System.out.println("Sorry it is a stop word\n");
	    			continue;
	    		}
	    		Searching src = new Searching(query,this.folderpath);
	    		if(src.value==null)
	    			System.out.println("Not Found\n");
	    		else
	    			System.out.println("String\n"+src.value);
	  
	    	}
			
			
			
		}*/
	/*	public Parser(String folderparh,int abc)
		{
			  stemmer=new Stemmer() ;
			  value_id="a";
	     	  hs.add("are");
		      hs.add("for");
		      hs.add("use");
		      hs.add("at");
		      hs.add("because");
		      hs.add("new");
		      hs.add("a");
		      hs.add("able");
		      hs.add("about");
		      hs.add("across");
		      hs.add("after");
		      hs.add("all");
		      hs.add("almost");
		      hs.add("also");
		      hs.add("am");
		      hs.add("among");
		      hs.add("an");
		      hs.add("and");
		      hs.add("any");
		      hs.add("are");
		      hs.add("as");
		      hs.add("at");
		      hs.add("be");
		      hs.add("because");
		      hs.add("been");
		      hs.add("but");
		      hs.add("by");
		      hs.add("can");
		      hs.add("cannot");
		      hs.add("could");
		      hs.add("com");
		      hs.add("dear");
		      hs.add("did");
		      hs.add("do");
		      hs.add("does");
		      hs.add("either");
		      hs.add("else");
		      hs.add("ever");
		      hs.add("every");
		      hs.add("edt");
		      hs.add("for");
		      hs.add("from");
		      hs.add("get");
		      hs.add("got");
		      hs.add("had");
		      hs.add("has");
		      hs.add("have");
		      hs.add("he");
		      hs.add("her");
		      hs.add("hers");
		      hs.add("him");
		      hs.add("his");
		      hs.add("how");
		      hs.add("however");
		      hs.add("i");
		      hs.add("if");
		      hs.add("in");
		      hs.add("into");
		      hs.add("is");
		      hs.add("it");
		      hs.add("its");
		      hs.add("just");
		      hs.add("least");
		      hs.add("let");
		      hs.add("like");
		      hs.add("likely");
		      hs.add("may");
		      hs.add("me");
		      hs.add("might");
		      hs.add("most");
		      hs.add("must");
		      hs.add("my");
		      hs.add("neither");
		      hs.add("no");
		      hs.add("nor");
		      hs.add("not");
		      hs.add("of");
		      hs.add("off");
		      hs.add("often");
		      hs.add("on");
		      hs.add("only");
		      hs.add("or");
		      hs.add("other");
		      hs.add("our");
		      hs.add("own");
		      hs.add("rather");
		      hs.add("said");
		      hs.add("say");
		      hs.add("says");
		      hs.add("she");
		      hs.add("should");
		      hs.add("since");
		      hs.add("so");
		      hs.add("some");
		      hs.add("than");
		      hs.add("that");
		      hs.add("the");
		      hs.add("their");
		      hs.add("them");
		      hs.add("then");
		      hs.add("there");
		      hs.add("these");
		      hs.add("they");
		      hs.add("this");
		      hs.add("tis");
		      hs.add("to");
		      hs.add("too");
		      hs.add("twas");
		      hs.add("us");
		      hs.add("wants");
		      hs.add("was");
		      hs.add("we");
		      hs.add("were");
		      hs.add("what");
		      hs.add("when");
		      hs.add("where");
		      hs.add("which");
		      hs.add("while");
		      hs.add("who");
		      hs.add("whom");
		      hs.add("why");
		      hs.add("will");
		      hs.add("with");
		      hs.add("would");
		      hs.add("yet");
		      hs.add("you");
		      hs.add("your");
		      this.folderpath=folderparh;
		}*/
		public Parser(String folderpath)
		{
			this.folderpath=folderpath;
		}
		
		
		public void tokenize(String str,char ch)
		{
			int i,s;
			i=0;
			//freq = new count();
			
		//	System.out.println("str="+str);
			
			while(i<str.length())
			{
				if (str.charAt(i)>='a' && str.charAt(i)<='z'){
					s=i;
					while(i< str.length() && str.charAt(i)>='a' && str.charAt(i)<='z')
					{
						i++;
					}
					
					String abc = str.substring(s, i);
			//		System.out.println("abc="+abc);
					if(abc.length()<100)
						stopwords(abc,value_id,ch);	
				}
			//	else{
				i++; 
			//	}	
			}			
		}
		
		public void increment(char ch,count freq)
		{
			if(ch=='c')
				freq.category=freq.category+1;
			else if(ch=='b')
				freq.body = freq.body+1;
			else if(ch=='i')
				freq.infobox = freq.infobox + 1;
			else if(ch=='t')
				freq.title = freq.title + 1 ;
			else if(ch=='e')
				freq.external = freq.external + 1;
		}
		
		public void stopwords(String token , String id,char ch)
		{
			  // create a hash set
			
			Integer num;
	
		    try{

		    		
					if( !(hs.contains(token) ))
					{	
						
						stemmer.add(token.toCharArray(),token.length());
						
						stemmer.stem();
						
						token=stemmer.toString();
						
						if(token.length()!=0)
						{ 
								if(hm.containsKey(token))
								{
									if(hm.get(token).containsKey(id))
									{
										
										freq= hm.get(token).get(id);
										increment(ch,freq);
										//num=hm.get(token).get(id)+1;
										hm.get(token).put(id,freq);
									}
									else
									{
										freq=new count();
										increment(ch,freq);
										
										hm.get(token).put(id,freq);
									}
									
								}
								else
								{
									freq=new count();
									increment(ch,freq);
									TreeMap<String,count> hp = new TreeMap<String,count>() ;
									hp.put(id, freq);
									hm.put(token,hp);
								}
						}
					}
			
			}
		    catch (Exception e){//Catch exception if any
		    		e.printStackTrace();
				  
		    }
		}
		public void startDocument(){
		//	System.out.println(folderpath);
			startTime = System.currentTimeMillis();
			stemmer = new Stemmer();
			  hs.add("are");
		      hs.add("for");
		      hs.add("use");
		      hs.add("at");
		      hs.add("because");
		      hs.add("new");
		      hs.add("a");
		      hs.add("able");
		      hs.add("about");
		      hs.add("across");
		      hs.add("after");
		      hs.add("all");
		      hs.add("almost");
		      hs.add("also");
		      hs.add("am");
		      hs.add("among");
		      hs.add("an");
		      hs.add("and");
		      hs.add("any");
		      hs.add("are");
		      hs.add("as");
		      hs.add("at");
		      hs.add("be");
		      hs.add("because");
		      hs.add("been");
		      hs.add("but");
		      hs.add("by");
		      hs.add("can");
		      hs.add("cannot");
		      hs.add("could");
		      hs.add("com");
		      hs.add("dear");
		      hs.add("did");
		      hs.add("do");
		      hs.add("does");
		      hs.add("either");
		      hs.add("else");
		      hs.add("ever");
		      hs.add("every");
		      hs.add("edt");
		      hs.add("for");
		      hs.add("from");
		      hs.add("get");
		      hs.add("got");
		      hs.add("had");
		      hs.add("has");
		      hs.add("have");
		      hs.add("he");
		      hs.add("her");
		      hs.add("hers");
		      hs.add("him");
		      hs.add("his");
		      hs.add("how");
		      hs.add("however");
		      hs.add("i");
		      hs.add("if");
		      hs.add("in");
		      hs.add("into");
		      hs.add("is");
		      hs.add("it");
		      hs.add("its");
		      hs.add("just");
		      hs.add("least");
		      hs.add("let");
		      hs.add("like");
		      hs.add("likely");
		      hs.add("may");
		      hs.add("me");
		      hs.add("might");
		      hs.add("most");
		      hs.add("must");
		      hs.add("my");
		      hs.add("neither");
		      hs.add("no");
		      hs.add("nor");
		      hs.add("not");
		      hs.add("of");
		      hs.add("off");
		      hs.add("often");
		      hs.add("on");
		      hs.add("only");
		      hs.add("or");
		      hs.add("other");
		      hs.add("our");
		      hs.add("own");
		      hs.add("rather");
		      hs.add("said");
		      hs.add("say");
		      hs.add("says");
		      hs.add("she");
		      hs.add("should");
		      hs.add("since");
		      hs.add("so");
		      hs.add("some");
		      hs.add("than");
		      hs.add("that");
		      hs.add("the");
		      hs.add("their");
		      hs.add("them");
		      hs.add("then");
		      hs.add("there");
		      hs.add("these");
		      hs.add("they");
		      hs.add("this");
		      hs.add("tis");
		      hs.add("to");
		      hs.add("too");
		      hs.add("twas");
		      hs.add("us");
		      hs.add("wants");
		      hs.add("was");
		      hs.add("we");
		      hs.add("were");
		      hs.add("what");
		      hs.add("when");
		      hs.add("where");
		      hs.add("which");
		      hs.add("while");
		      hs.add("who");
		      hs.add("whom");
		      hs.add("why");
		      hs.add("will");
		      hs.add("with");
		      hs.add("would");
		      hs.add("yet");
		      hs.add("you");
		      hs.add("your");
	
		}
		
		public void filecopy()
		{
		
			String key;
			Integer no;
			Map<String, TreeMap<String,count>> treeMap = new TreeMap<String, TreeMap<String,count>>(hm);			
			Iterator iter = treeMap.entrySet().iterator();
			try{
				 // Create file 
	//			System.out.println(folderpath);
				FileWriter fstream = new FileWriter(folderpath+"/out"+fnum+".txt");
				fnum++;
				BufferedWriter out = new BufferedWriter(fstream);							 
				while (iter.hasNext()) 
				{
					Map.Entry mEntry = (Map.Entry) iter.next();
					TreeMap hmp = (TreeMap)mEntry.getValue();
					Set keys = hmp.keySet();
					out.write((String)mEntry.getKey());
				//	System.out.println(mEntry.getKey());
					for(Iterator i = keys.iterator();i.hasNext();)
					{
						key = (String)i.next();
						freq=(count) hmp.get(key);
						out.write(","+key+"-");
						if(freq.title>0)
							out.write(freq.title+"t");
						if(freq.infobox>0)
							out.write(freq.infobox+"i");
						if(freq.body>0)
							out.write(freq.body+"b");
						if(freq.external>0)
							out.write(freq.external+"e");
						if(freq.category>0)
							out.write(freq.category+"c");
				//		System.out.println(key +" : " + no);
					}
					out.write("\n");
				}
				out.close();
				
			}
			catch (Exception e){//Catch exception if any
				  e.printStackTrace();
		    }
			
			
	 
		}
		
/*		public void search_word()
		{
			String key;
			Integer no;
			int flag=0;
			Map<String, TreeMap<String,Integer>> treeMap = new TreeMap<String, TreeMap<String,Integer>>(hm);
			Iterator iter = treeMap.entrySet().iterator();
			try{
							 
				while (iter.hasNext()) 
				{
					Map.Entry mEntry = (Map.Entry) iter.next();
					TreeMap hmp = (TreeMap)mEntry.getValue();
					Set keys = hmp.keySet();
					
					query = (String)mEntry.getKey();
					flag=1;
				}
				if(flag==0)
				{
					query=null;
				}
				
			}
			catch (Exception e){//Catch exception if any
				
				e.printStackTrace();
		    }
		}
	*/   
		public void endDocument(){
	    //	Map<String, HashMap<String,Integer>> treeMap = new TreeMap<String, HashMap<String,Integer>>(hm);
		
			filecopy();
	    	hm.clear();
	   	    Mergesort merge = new Mergesort(fnum,folderpath);
	    	long endTime = System.currentTimeMillis(); 
			long totalTime = endTime - startTime; 
			System.out.println("T="+totalTime+"\n");
	    }
	    public void startElement(String nameSpaceURI , String localName, String qName
	    		, Attributes atts){
	    	

	    	if(qName.equalsIgnoreCase("Page"))
	    		value_text = new StringBuilder();
	    	
	    	if(qName.equalsIgnoreCase("title") && title==0)
	    	{
	    		
	    		title=1;
	    	}
	    	if(qName.equalsIgnoreCase("text") && text==0)
	    	{
	    		value_text = new StringBuilder();
	    		text=1;
	    	}
	    	if(qName.equalsIgnoreCase("id") && id==0)
	    		id=1;

	    }
	    public void endElement(String nameSpaceURI , String localName, String qName){
	    
	    	
	    	if(qName.equalsIgnoreCase("Page"))
	    	{	
	    			id=0;
	    			title=0;
	    			text=0;
	    			value=value_text.toString().toLowerCase()+" "+value_title;
	    			
	    			ParseString.strbreak(value_text.toString().toLowerCase());
	    	//		System.out.println("vaue="+value_text);
	    			tokenize(value_title.toLowerCase(),'t');
		    		tokenize(infobox.toString().toLowerCase(),'i');
		    		tokenize(body.toString().toLowerCase(),'b');
		    		tokenize(external.toString().toLowerCase(),'e');
		    		tokenize(category.toString().toLowerCase(),'c');
		    		
		    		count_val++;
		    		if(count_val==8000)
		    		{
		    			filecopy();
		    			count_val=0;
		    			hm.clear();
		    		}
	    		//	value_text=null;
	    	}
	    	if(id==1 && qName.equalsIgnoreCase("id"))
	    	{
	    		id=2;
	    		
	    	}
	    	if(title==1 && qName.equalsIgnoreCase("title"))
	    	{
	    		title=2;
	    	}
	    	if(text==1 && qName.equalsIgnoreCase("text"))
	    	{
	    		
	    		text=2;
	    	}
	    }
	    public void characters(char[] ch , int start, int length){
	    	if(id==1)
	    	{
	    		value_id = new String(ch,start,length);
	    		}
	    	if(title==1)
	    	{
	    		value_title = new String(ch,start,length);
	    	}
	    	if(text==1)
	    	{
	    		
	    		value_text.append(ch,start,length);
	    		
	 	    	}
	    }
}