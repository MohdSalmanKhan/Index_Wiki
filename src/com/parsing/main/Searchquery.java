package com.parsing.main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.RandomAccessFile;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;

public class Searchquery {

	static HashSet<String> hs = new HashSet<String>();
	TreeMap<String,String> title_load = new TreeMap<String,String>(); 
	HashMap<Long, Long> score;
	Stemmer stemmer;
	String folderpath;
	BufferedReader brs;
	RandomAccessFile file;
	
	TreeMap<String, String> sparse;
	HashMap<Character, HashMap<Long, Long>> mapping = new HashMap<Character, HashMap<Long, Long>>();
	char tagname;
	public void tokenize(String str,boolean flag) {
		int i, s;
		i = 0;
		while (i < str.length()) {
			if (str.charAt(i) >= 'a' && str.charAt(i) <= 'z') {
				s = i;
				while (i < str.length() && str.charAt(i) >= 'a'
						&& str.charAt(i) <= 'z') {
					i++;
				}
				String abc = str.substring(s, i);
				if (abc.length() < 100)
					stopwords(abc,flag);
			}
			i++;

		}
	}

	String getvalueinsparse(String str) {
		// System.out.println("HELLO");
		int i = 0, flag = 0;
		String value = "", firstmatchingcharacter = "";
		// System.out.println("str="+str);
		for (Map.Entry<String, String> entry : sparse.entrySet()) {
			// System.out.println(entry.getKey() + "/" + entry.getValue());
			flag = 0;
			int no=str.length();
			String checking=entry.getKey();
			for (i = 0; i < str.length() && i < checking.length(); i++) 
			{
				if (str.charAt(i) < checking.charAt(i)) 
				{
					flag = 1;
					break;
				} 
				else if (str.charAt(i) > checking.charAt(i)) 
				{
					flag = 0;
					break;
				}
				else
				{
					if(i==no-1 && str.length()<checking.length() && str.charAt(no-1)== checking.charAt(no-1))
					{
						flag=1;
						break;
					}
				}
			}
	/*		if(flag==1)
			{
				
				if(str.length()<checking.length() && str.charAt(no-1)== checking.charAt(no-1))
					break;
			}*/
			if (flag == 1)
				break;
			value = entry.getValue();
			firstmatchingcharacter = checking;

		}
		// System.out.println("in function="+value);
		System.out.println(firstmatchingcharacter);
		if (value == "" || str.charAt(0) != firstmatchingcharacter.charAt(0))
			return null;

		return value;
	}
	public void mapping_func1(String mainvalue)
	{
			
		String token[] = mainvalue.split(",");
		String store[];
		int i, j, num, k, start;
		// HashMap<Long,Long> hm = new HashMap<Long,Long>();
		for (i = 1; i < token.length; i++) {
			store = token[i].split("-");
			num = 0;
			start = 0;
			for (j = 0; j < store[1].length(); j++) {

				for (k = j; k < store[1].length() && store[1].charAt(k) != 'b'
						&& store[1].charAt(k) != 'i'
						&& store[1].charAt(k) != 'e'
						&& store[1].charAt(k) != 'c'
						&& store[1].charAt(k) != 't'; k++);
				
				if(store[1].charAt(k)!=tagname)
					continue;
				String h = store[1].substring(j, k);
				Long x = Long.parseLong(h);
		//		System.out.println("x=" + x);
				Long y = Long.parseLong(store[0]);
				HashMap<Long, Long> hm = new HashMap<Long, Long>();
				if (mapping.containsKey(store[1].charAt(k))) {
			//		System.out.println(store[1].charAt(k) + "--1");
					hm = mapping.get(store[1].charAt(k));
					boolean flag = false;
					for (Map.Entry<Long, Long> entry : hm.entrySet()) {
						// entry.getKey() + "/" + entry.getValue());
						if (entry.getKey().equals(y)) {
							// hm.put(y, x + entry.getValue());
							if(store[1].charAt(k)==tagname)
							{
								mapping.get(store[1].charAt(k)).put(y,
										x + entry.getValue());
							}
							flag = true;
							break;							
						}
					}
					if (flag == false) {
						// hm.put(y, x);
		//				System.out.println(store[1].charAt(k) + "--3");
						if(store[1].charAt(k)==tagname)
							mapping.get(store[1].charAt(k)).put(y, x);
					}

				} else {
					hm.put(y, x);
			//		System.out.println(store[1].charAt(k) + "--2--" + x);

					mapping.put(store[1].charAt(k), hm);
				}
				j = k;
			}
		}	
		
		
		
	/*	char ch1;
		HashMap<Long,Long> hm = new HashMap<Long,Long>();
		for (Entry<Character, HashMap<Long, Long>> entry : mapping.entrySet()) 
		{
			ch1 = entry.getKey();
		//	System.out.println("-----111-----");
			System.out.println("key="+ch1);
			hm = entry.getValue();
			
			for (Map.Entry<Long, Long> entry1 : hm.entrySet()) 
			{
				//	Long val1 = entry1.getKey();
					
					
					System.out.println("value="+entry1.getValue());
					
			}
		}*/
		
		
		
		
		
		
		
		
	}
	public void mapping_func(String mainvalue) {
		String token[] = mainvalue.split(",");
		String store[];
		int i, j, num, k, start;
		// HashMap<Long,Long> hm = new HashMap<Long,Long>();
		for (i = 1; i < token.length; i++) {
			store = token[i].split("-");
			num = 0;
			start = 0;
			for (j = 0; j < store[1].length(); j++) {

				for (k = j; k < store[1].length() && store[1].charAt(k) != 'b'
						&& store[1].charAt(k) != 'i'
						&& store[1].charAt(k) != 'e'
						&& store[1].charAt(k) != 'c'
						&& store[1].charAt(k) != 't'; k++)
					;
				String h = store[1].substring(j, k);
				Long x = Long.parseLong(h);
		//		System.out.println("x=" + x);
				Long y = Long.parseLong(store[0]);
				HashMap<Long, Long> hm = new HashMap<Long, Long>();
				if (mapping.containsKey(store[1].charAt(k))) {
			//		System.out.println(store[1].charAt(k) + "--1");
					hm = mapping.get(store[1].charAt(k));
					boolean flag = false;
					for (Map.Entry<Long, Long> entry : hm.entrySet()) {
						// entry.getKey() + "/" + entry.getValue());
						if (entry.getKey().equals(y)) {
							// hm.put(y, x + entry.getValue());
							mapping.get(store[1].charAt(k)).put(y,
									x + entry.getValue());
							flag = true;
							break;
						}
					}
					if (flag == false) {
						// hm.put(y, x);
		//				System.out.println(store[1].charAt(k) + "--3");
						mapping.get(store[1].charAt(k)).put(y, x);
					}

				} else {
					hm.put(y, x);
			//		System.out.println(store[1].charAt(k) + "--2--" + x);

					mapping.put(store[1].charAt(k), hm);
				}
				j = k;
			}
		}
	}

	public void stopwords(String str,boolean flag) {
		Integer num;
		String value, str1;
		try {
			if (!(hs.contains(str))) {
			//	{
				stemmer.add(str.toCharArray(), str.length());

				stemmer.stem();

				str = stemmer.toString();

				if (str.length() != 0) {
					// System.out.println("value="+str);
					value = getvalueinsparse(str);
					System.out.println("value=" + value);
					// brs = new BufferedReader(new
					// FileReader(folderpath+"/"+str.charAt(0)+".txt"));
					file = new RandomAccessFile(this.folderpath + "/dict_"
							+ str.charAt(0) + ".txt", "rw");
					file.seek(Long.parseLong(value));

					String line, firstpart, secondpart = "";
					int j = 0;
					System.out.println("str="+str);
					while ((line = file.readLine()) != null) {
						j = 0;
						while (line.charAt(j) != ',') {
							j++;
						}
						firstpart = line.substring(0, j);
						// System.out.println("1="+firstpart);
						if (firstpart.compareTo(str) == 0) {
							// System.out.println("2="+firstpart);
							secondpart = line.substring(j + 1, line.length());
							// System.out.println("2="+secondpart);
							break;
						} else if (firstpart.compareTo(str) > 0) {
							
							System.out.println("Not Found.."+firstpart);
							return;
						}
					}

					file.close();
					file = new RandomAccessFile(this.folderpath + "/"
							+ str.charAt(0) + ".txt", "rw");

					j = 0;

					file.seek(Integer.parseInt(secondpart));
					String mainvalue;
					mainvalue = file.readLine();
		//			System.out.println("Final String="+mainvalue);
					if(flag==false)
						mapping_func(mainvalue);
					else
						mapping_func1(mainvalue);
					file.close();
				}
			}
		} catch (Exception e) {// Catch exception if any
			e.printStackTrace();

		}

	}
	RandomAccessFile title_sparse,title_dense,title;
	void func1(String folderpath) {
		try{
			title_sparse=new RandomAccessFile(new File(folderpath+"/sp.dat" ),"rw");
			title_dense=new RandomAccessFile(new File(folderpath+"/smeta.dat" ),"rw");
			title=new RandomAccessFile(new File(folderpath+"/pagedata.dat" ),"rw");
		//	System.out.println("string="+title_dense.readLine());
			String title_value;	
		sparse = new TreeMap<String, String>();
		this.folderpath = folderpath;
		String titletobesearched="";
		
		initialize();
		
		
		stemmer = new Stemmer();
		// Search search = new Search();
		while (true) {
			Scanner in = new Scanner(System.in);
			String s;
			System.out.println("Enter a string");
			s = in.nextLine();
			Long startTime = System.currentTimeMillis();
			
			if(s.contains(":"))
			{
				int i;
				Long value,num;
				char ch,ch1;
				score = new HashMap<Long, Long>();
				value=Long.parseLong("0");
				num=Long.parseLong("0");
				Allclear();
				String alltoken[] = s.split(" ");
				for(i=0;i<alltoken.length;i++)
				{
					String cutter[]= alltoken[i].split(":");

					tagname=cutter[0].charAt(0);
					ch=tagname;
			//		System.out.println("ch="+ch);
					tokenize(cutter[1],true);
				
					if(ch=='t')
						value=Long.parseLong("20");
					else if(ch=='c' || ch=='e')
						value=Long.parseLong("1");
					else if(ch=='b')
						value=Long.parseLong("3");
					else if(ch=='i')
						value=Long.parseLong("5");
					HashMap<Long, Long> hm = new HashMap<Long, Long>();
					for (Entry<Character, HashMap<Long, Long>> entry : mapping.entrySet()) 
					{
						ch1 = entry.getKey();
					//	System.out.println("-----111-----");
				//		System.out.println(ch1);
						hm = entry.getValue();
						if (ch == ch1) 
						{
							for (Map.Entry<Long, Long> entry1 : hm.entrySet()) 
							{
								Long val1 = entry1.getKey();
						//		System.out.println("val1="+val1);
								if (score.containsKey(val1)) 
								{
									num = score.get(val1) + entry1.getValue()*value;
											
									score.put(val1, num);
									// score.put(entry1.getKey(), )
								} 
								else {
							//		System.out.println("hello");
									score.put(val1, entry1.getValue() * value);
								}
							}
							break;
						}
					}
				}
				Long Max, key;
				for (i = 0; i < 10; i++) 
				{
						Max = Long.parseLong("-2");
						key = Long.parseLong("-2");
				
						for (Map.Entry<Long, Long> entry : score.entrySet()) 
						{
							if (entry.getValue() > Max) 
							{
								Max = entry.getValue();
								key = entry.getKey();
							}							
						}
						if (Max <= -1)
							break;
						title_value=traversemap(key);
						System.out.println(i + 1 + ":->" + key + "-----" + Max+"----title---"+title_value);
						score.put(key, Long.parseLong("-1"));
					}			
					Allclear();
					continue;
				}
			
			
			long totalTime = 0;
			
			
			s = s.toLowerCase();
			tokenize(s,false);
			scoreland();
			// HashMap<Long,Long> hm1 = new HashMap<Long,Long>();
			// hm1.putAll(score);
			Long Max, key;
			for (int i = 0; i < 10; i++) 
			{
				Max = Long.parseLong("-2");
				key = Long.parseLong("-2");
				for (Map.Entry<Long, Long> entry : score.entrySet()) 
				{
					if (entry.getValue() > Max) 
					{
						Max = entry.getValue();
						key = entry.getKey();						
					}
					// System.out.println(entry.getKey() + "/" +
					// entry.getValue());
				}
				if (Max <= -1)
					break;
				
				title_value=traversemap(key);
				System.out.println(i + 1 + ":->" + key + "-----" + Max+"----title"+title_value);
				score.put(key, Long.parseLong("-1"));
			}
			long endTime = System.currentTimeMillis(); 
			totalTime = endTime - startTime;
			System.out.println("time="+totalTime);
			Allclear();
		}
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}

}
	
	public String traversemap(Long key)
	{
		String title_value="";
		String offset="",keys="";
		String line,tobefetched="";
		try
		{
			
			for (Entry<String,String> entry : title_load.entrySet()) 
			{
			
				if(Long.parseLong(entry.getKey())>key)
				{
					break;
				}
				else
				{
					offset=entry.getValue();
					keys=entry.getKey();
				}
			
			}
	//		System.out.println("Keys="+keys+"offset="+offset);
			title_sparse.seek(Long.parseLong(offset));
			while((line=title_sparse.readLine())!=null)
			{//	System.out.println(line);
				int j=0;
				while(line.charAt(j)!=':')
				{
					j++;
				}
			//System.out.println("key="+key);
			//System.out.println("line="+line.substring(0,j));
				int x= Integer.parseInt(line.substring(0,j));
				int y = Integer.parseInt(key.toString());
				if(x>y)
				{
			//		tobefetched=line.substring(j+1,line.length());
					break;
				}
				else 
				{
					tobefetched=line.substring(j+1,line.length());
					keys=line.substring(0,j);
				}
			}
			offset=tobefetched;
			title_dense.seek(Long.parseLong(offset));
	//		System.out.println("Keys="+keys+"offset="+offset);
			while((line=title_dense.readLine())!=null)
			{//	System.out.println(line);
				int j=0;
				while(line.charAt(j)!=':')
				{
					j++;
				}
			//System.out.println("key="+key);
			//System.out.println("line="+line.substring(0,j));
				int x= Integer.parseInt(line.substring(0,j));
				int y = Integer.parseInt(key.toString());
				if(x>y)
				{
			//		tobefetched=line.substring(j+1,line.length());
					break;
				}
				else 
				{
					tobefetched=line.substring(j+1,line.length());
					keys=line.substring(0,j);
				}
			}
			
			offset=tobefetched;
	//		System.out.println("Keys="+keys+"offset="+offset);
			title.seek(Long.parseLong(offset));
			title_value=title.readLine();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		
		
		//System.out.println("offset"+offset+"---keys="+keys);
		return title_value;
		
			// entry.getKey() + "/" + entry.getValue());
	}

	public void Allclear() {
		score.clear();
		mapping.clear();
	}

	public void scoreland() {
		score = new HashMap<Long, Long>();
		Long num, val;
		char ch;
		Long key;
		HashMap<Long, Long> hm = new HashMap<Long, Long>();
		Iterator iter = mapping.entrySet().iterator();
		/*
		 * while (iter.hasNext()) { Map.Entry mEntry = (Map.Entry) iter.next();
		 * HashMap hmp = (HashMap)mEntry.getValue(); Set keys = hmp.keySet();
		 * //out.write((String)mEntry.getKey());
		 * System.out.println(mEntry.getKey()); for(Iterator i =
		 * keys.iterator();i.hasNext();) { key = (Long)i.next(); // freq=(count)
		 * hmp.get(key); System.out.println("key="+key);
		 * System.out.println("key="+hmp.get(key)); } }
		 */
		for (Entry<Character, HashMap<Long, Long>> entry : mapping.entrySet()) {

			// entry.getKey() + "/" + entry.getValue());
			ch = entry.getKey();
		//	System.out.println("ch=" + ch);
			// val=entry.getValue();
			hm = entry.getValue();
			if (ch == 't') {
				for (Map.Entry<Long, Long> entry1 : hm.entrySet()) {
					Long val1 = entry1.getKey();
					if (score.containsKey(val1)) {
						num = score.get(val1) + entry1.getValue()*20;
								
						score.put(val1, num);
						// score.put(entry1.getKey(), )
					} else {
						score.put(val1, entry1.getValue() * 20);
					}
				}
			}

			if (ch == 'b') {
				// hm=entry.getValue();
				for (Map.Entry<Long, Long> entry1 : hm.entrySet()) {
					Long val1 = entry1.getKey();
			//		System.out.println("val 1 " + val1);
					if (score.containsKey(val1)) {
			//			System.out.println("--2---");
						num = score.get(val1) + entry1.getValue() * 3;
						score.put(val1, num);
						// score.put(entry1.getKey(), )
					} else {
			//			System.out.println("---1---");
						score.put(val1, entry1.getValue() * 3);
					}
				}
			}

			if (ch == 'i') {
				// hm=entry.getValue();
				for (Map.Entry<Long, Long> entry1 : hm.entrySet()) {
					Long val1 = entry1.getKey();
					if (score.containsKey(val1)) {
						num = score.get(val1) + entry1.getValue()
								* 5;
						// System.out.println("val="+num);
						score.put(val1, num);
						// score.put(entry1.getKey(), )
					} else {
						score.put(val1, entry1.getValue() * 5);
					}
				}
			}

			if (ch == 'e') {
				// hm=entry.getValue();
				for (Map.Entry<Long, Long> entry1 : hm.entrySet()) {
					Long val1 = entry1.getKey();
				//	System.out.println("val 1 " + val1);
					if (score.containsKey(val1)) {
					//	System.out.println("--3---");
						num = score.get(val1) + entry1.getValue();
						score.put(val1, num);

						// score.put(entry1.getKey(), )
					} else {
				//		System.out.println("---4---");
						score.put(val1, entry1.getValue());

					}
				}

				if (ch == 'c') {
					// hm=entry.getValue();
					
					for (Map.Entry<Long, Long> entry1 : hm.entrySet()) {
						Long val1 = entry1.getKey();
						if (score.containsKey(val1)) {
							num = score.get(val1)
									+ entry1.getValue() * 1;
							score.put(val1, num);
							// score.put(entry1.getKey(), )
						} else {
							score.put(val1, entry1.getValue());
						}
					}
				}
			}
		}
	}

	public void initialize() {
		try {
			hs = Parser.hs;
			System.out.println("folderpath=" + folderpath);
			brs = new BufferedReader(new FileReader(folderpath
					+ "/sparse_a.txt"));
			String line, index, str;
			int j = 0;
			char ch = 'a';
			while (true) {
				while ((line = brs.readLine()) != null) {
					j = 0;
					while (line.charAt(j) != ',') {
						j++;
					}
					str = line.substring(0, j);
					index = line.substring(j + 1, line.length());
					sparse.put(str, index);
				}
				ch = (char) ((int) ch + 1);
				brs.close();
				if (ch <= 'z') {
					brs = new BufferedReader(new FileReader(folderpath + "/"
							+ "sparse_" + ch + ".txt"));
				}
				if (ch == '{')
					break;
			}
			brs.close();
			brs = new BufferedReader(new FileReader(folderpath + "/"
					+ "sp_sp.dat"));
			while((line=brs.readLine())!=null)
			{
				String titlesplit[] = line.split(":");
				title_load.put(titlesplit[0], titlesplit[1]);
			}						
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}