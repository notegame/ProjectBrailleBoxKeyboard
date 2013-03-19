package braillesLib;

import java.util.ArrayList;

public class BrailleCellList {
	
	ArrayList<BrailleCell> cellList;
	BrailleDB brailleDB = BrailleDB.getInstance();
	
	public BrailleCellList() {
		// TODO Auto-generated constructor stub
		cellList = new ArrayList<BrailleCell>();
	}
	
	public void add(BrailleCell brailleCell)
	{
		cellList.add(brailleCell);
	}
	
	public void clear()
	{
		cellList = new ArrayList<BrailleCell>();
	}
	
	public void del()
	{
		
		if(cellList.size() > 1)
		{
			BrailleCharList bCharList = toBrailleCharList();
			int numCell = bCharList.get(bCharList.size()-1).toString().length()/6;
			
			for(int i = 0 ; i < numCell ; i++)
			{
				cellList.remove(cellList.size()-1);
			}
			
		}else{
			clear();
		}
	}
	
	public BrailleCharList toBrailleCharList()
	{

		int start=0,end=0;
		
		BrailleCharList charList = new BrailleCharList();
		
		BrailleChar LastFined = null;
		int LastFinedStart = 0,LastFinedEnd = 0;
		String LastLang = null;
		
		StringBuilder SEARCH = new StringBuilder();
		
		while(start < cellList.size())
		{
			if(end-start == 0)
			{
				SEARCH = new StringBuilder();
				LastLang = cellList.get(start).getLanguage(); 
				SEARCH.append(cellList.get(start).toString());
			}else
			{
				SEARCH = new StringBuilder();
				for(int i=start;i<=end;i++)
				{
					SEARCH.append(cellList.get(i).toString());
				}
			}
			
			//Search in Table
			BrailleSearchResult result = brailleDB.search(LastLang, SEARCH.toString());
			
			/*
			System.out.println("-->"+start+"-"+end);
			System.out.println(LastLang+":"+SEARCH);
			System.out.println("-->"+result.fine());
			System.out.println("-->"+result.numRow());*/
			
			if(SEARCH.toString().equals("000000"))
			{
				charList.add(new BrailleChar(LastLang, "000000"));
				start = end+1;
				end = start;
			}else
			if(result.numRow() == 1)
			{
				if(!result.fine())
				{
					end++;
				}else{
					charList.add(new BrailleChar(LastLang, SEARCH.toString()));
					start = end+1;
					end = start;
				}
			}else
			if(result.numRow() > 1)
			{
				LastFined = new BrailleChar(LastLang, SEARCH.toString());
				LastFinedStart = start;
				LastFinedEnd = end;
				
				if(end < cellList.size()-1)
				{
					end++;
				}else
				{
					charList.add(new BrailleChar(LastLang, SEARCH.toString()));
					start = end+1;
					end = start;
				}
				
			}else{
				//not fine
				if((end-start)+1 >= 3)
				{
					if(LastFined == null)
					{
						start++;
						end = start;
					}else
					{
						charList.add(new BrailleChar(LastLang, LastFined.toString()));
						start = LastFinedEnd+1;
						end = start;
						
						LastFined = null;
						LastFinedEnd = 0;
						LastFinedStart = 0;
						
					}
				}else
				{
					if(end-start == 0)
					{
						start = end+1;
						end=start;
					}else{
						
						charList.add(new BrailleChar(LastLang, LastFined.toString()));
						start = LastFinedEnd+1;
						end = start;
						
						LastFined = null;
						LastFinedEnd = 0;
						LastFinedStart = 0;
						
					}
				}
			}
			
			
			if(end < cellList.size()-1)
			{
				if(cellList.get(end).getLanguage() != LastLang)
				{
					start = end;
				}
			}
			
		}
		
		return charList;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		// TODO Auto-generated method stub
		
		for(BrailleCell bcell : cellList)
		{
			sb.append("["+bcell.getLanguage()+":"+bcell.getBitString()+"], ");
		}
		
		return sb.toString();
	}
	
}
