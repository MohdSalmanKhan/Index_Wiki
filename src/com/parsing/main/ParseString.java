package com.parsing.main;

public class ParseString 
{

	public static void strbreak(String str)
	{
		
		Parser.infobox= new StringBuilder();
		Parser.body=new StringBuilder();  
		Parser.external=new StringBuilder(); 
		Parser.category=new StringBuilder();
		int j=0,start=0,end=0,flag=0;
		//System.out.println("string="+str);
		//------------------------------Infobox------------------------------
		for(int i=0;i<str.length();i++)
		{//	System.out.println("1");
			if(i+1>=str.length())
				break;
			if(str.charAt(i)=='{' && str.charAt(i+1)=='{')
			{
			//	System.out.println("2");
				j=i+2;
				if(j>=str.length())
					break;
				while(str.charAt(j)==' ')
				{
					j++;
					if(j==str.length())
					{
						flag=1;
						break;
					}
				}				
				if(flag==1)
					break;
			
				if((j+6<str.length())&&(str.charAt(j)=='i' && str.charAt(j+1)=='n' && str.charAt(j+2)=='f'&& str.charAt(j+3)=='o'&& str.charAt(j+4)=='b'
						&& str.charAt(j+5)=='o'&& str.charAt(j+6)=='x'))
				{
				//	System.out.println("3");
					start=j+8;					
					break;
				}
				
			}
			
		}
		int left=2;
		int right=0;
		int i;
		for(i=start;i<str.length();i++)
		{
			if(str.charAt(i)=='{')
				left=left+1;
			else if(str.charAt(i)=='}')
				right=right+1;
			if(left==right)
			{
				end=i-1;
				break;
			}				
		}
		if(i==str.length())
			end=str.length();
		if(end>start)
		{
			Parser.infobox.append(str, start, end);
		}
		if(i==str.length())
			return;
//		System.out.println("start="+start+"end="+end);
	//	System.out.println("infobox="+Parser.infobox);
		
		
		//-------------------------------------Body-------------------------
		flag=0;
		if(start!=0)
		{			
			start=end+2;
		}
//		System.out.println(str.charAt(start+1));
		end=0;
	//	int i;
		for( i=start;i<str.length();i++)
		{
			if(i+1>=str.length())
				break;
			if(str.charAt(i)=='='&&str.charAt(i+1)=='=')
			{
				j=i+2;
				if(j>=str.length())
					break;
				while(str.charAt(j)==' ')
				{
					j++;
					if(j==str.length())
					{
						i=j;
						flag=1;
						break;
					}
				}
				if(flag==1)
					break;
				if((j+10<str.length())&&((str.charAt(j)=='r' && str.charAt(j+1)=='e' && str.charAt(j+2)=='f'&& str.charAt(j+3)=='e'&& str.charAt(j+4)=='r'
						&& str.charAt(j+5)=='e'&& str.charAt(j+6)=='n' && str.charAt(j+7)=='c' && str.charAt(j+8)=='e'
						&& str.charAt(j+9)=='s' /*&& str.charAt(j+10)=='i' && str.charAt(j+11)=='n' && str.charAt(j+12)=='k' && str.charAt(j+13)=='s'*/)
						||
						(str.charAt(j)=='e' && str.charAt(j+1)=='x' && str.charAt(j+2)=='t'&& str.charAt(j+3)=='e'&& str.charAt(j+4)=='r'
						&& str.charAt(j+5)=='n'&& str.charAt(j+6)=='a' && str.charAt(j+6)=='l')))
				{
					end=i;
					break;
				}
			}
			else if(str.charAt(i)=='[' && str.charAt(i+1)=='[')
			{
				j=i+2;
				if(j>=str.length())
					break;
				if((j+9<str.length())&&(str.charAt(j)=='c' && str.charAt(j+1)=='a' && str.charAt(j+2)=='t' && str.charAt(j+3)=='e' && str.charAt(j+4)=='g' &&
						str.charAt(j+5)=='o' && str.charAt(j+6)=='r' && str.charAt(j+7)=='y') )
				{
					end=i;
					break;
				}
			}			
		}
		if(i==str.length())
		{
			end=i;
		}
		if(end>start)
		{
		//	StringBuffer gg=new StringBuffer();
		//	gg.append(str,start,end);
		//	System.out.println("gg="+gg);
			Parser.body.append(str, start, end);
		}
	//	System.out.println(Tokenizer.body);
		if(end==str.length())
			return;
		
		
		
		
		//---------------------------------------External links-----------------------------------------
		
		

		//System.out.println("start");
		for(i=end;i<str.length();i++)
		{
			//System.out.println(str.charAt(i));
			if(i+1>=str.length())
				break;
			if(str.charAt(i)=='=' && str.charAt(i+1)=='=')
			{
				//System.out.println("--3--");
				j=i+2;
				if(j>=str.length())
					break;
				while(str.charAt(j)==' ')
				{
					j++;
					if(j>=str.length())
					{	i=j;	
						break;
					}
				}
				if(j+9 < str.length() )
					break;
				if((j+8<str.length())&&(str.charAt(j)=='e' && str.charAt(j+1)=='x' && str.charAt(j+2)=='t'&& str.charAt(j+3)=='e'&& str.charAt(j+4)=='r'
						&& str.charAt(j+5)=='n'&& str.charAt(j+6)=='a' && str.charAt(j+7)=='l'))
				{
					start=j+8;					
					break;
				}				
			}			
		}
		String str2="";
		String str3="";
		flag=0;
		for(i=start;i<str.length();i++)
		{
			if(i+1>=str.length())
				break;
			if(str.charAt(i)=='[' && str.charAt(i+1)!='[')
			{
				j=i+1;
				if(j>=str.length())
					break;
				while(str.charAt(j)!=']')
				{
					str2+=str.charAt(j);
					j++;
					if(j==str.length())
					{
						i=j;
						flag=1;
						break;
					}
				}
				str2+=" ";
				if(flag==1)
					break;
				i=j;
			}
			else if(str.charAt(i)=='[' && str.charAt(i+1)=='[' )
			{
				j=i+2;
				if(j>str.length())
					break;
				if((j+9<str.length())&&(str.charAt(j)=='c' && str.charAt(j+1)=='a' && str.charAt(j+2)=='t' && str.charAt(j+3)=='e' && str.charAt(j+4)=='g' &&
						str.charAt(j+5)=='o' && str.charAt(j+6)=='r' && str.charAt(j+7)=='y') )
				{
					j=j+9;
					if(j+9>=str.length())
						break;
					while(str.charAt(j)!=']')
					{
						str3+=str.charAt(j);
						j++;
						if(j==str.length())
						{
							flag=1;
							i=j;
							break;
						}
					}
					str3+=" ";
					if(flag==1)
						break;
				}
				i=j;
			}
			
		}
		if(str2!="")
		{
			Parser.external.append(str2);
	
		}
	
		if(str3!="")
		{
			Parser.category.append(str3);
		
		}	  
	}
}