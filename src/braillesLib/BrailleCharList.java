package braillesLib;

import java.util.ArrayList;

import fulltext.TextSplit;
import fulltext.TextSplitResult;

public class BrailleCharList {
	
	private ArrayList<BrailleChar> charList;
	
	public BrailleCharList() {
		// TODO Auto-generated constructor stub
		charList = new ArrayList<BrailleChar>();
	}
	
	public void add(BrailleChar brailleChar)
	{
		charList.add(brailleChar);
	}
	
	public BrailleChar get(int index)
	{
		return charList.get(index);
	}
	
	public ArrayList<BrailleChar> getList()
	{
		return charList;
	}
	
	@Override
	public String toString()
	{
		/*
		StringBuilder F = new StringBuilder();
		StringBuilder A = new StringBuilder();
		StringBuilder C = new StringBuilder();
		StringBuilder T = new StringBuilder();
		StringBuilder B = new StringBuilder();
		
		StringBuilder All = new StringBuilder();
		StringBuilder Sum = new StringBuilder();
		
		int i = 0;
		for(BrailleChar bChar : charList)
		{
			//System.out.println(bChar.toText());
			
			
			if(bChar.isTHTone())
			{
				T.append(bChar.toText());
				
				if(fineErVowel == true)
				{
					historyWord.add("T:"+bChar.toText());
				}
				
			}else
			if(bChar.isTHVowelCenter())
			{
				
				
				Sum.append(F);
				Sum.append(A);
				Sum.append(C);
				Sum.append(T);
				Sum.append(B);
				
				All.append(Sum.toString());
				
				Sum = new StringBuilder();
				
				F = new StringBuilder();
				A = new StringBuilder();
				C = new StringBuilder();
				T = new StringBuilder();
				B = new StringBuilder();
				
				ArrayList<String> split = TextSplit.getInstance().Split(All.toString());
				String LastWord = split.get(split.size()-1);
				
				A.append(LastWord);
				
				if(bChar.equals("TH", "100101"))
				{
					fineErVowel  = true;
					System.out.println("ER");
					historyWord.add("A:"+LastWord);
					historyWord.add("V");
				}
				
				for(String vowel : bChar.toText().split(" "))
				{
					String[] getVowel = vowel.split(":");
					
					String point = getVowel[0].toUpperCase();
					
					if(point.equals("F"))
					{
						F.append(getVowel[1]);
					}else
					if(point.equals("C"))
					{
						C.append(getVowel[1]);
					}else
					if(point.equals("T"))
					{
						T.append(getVowel[1]);
					}else
					if(point.equals("B"))
					{
						B.append(getVowel[1]);
					}else
					{
						A.append(getVowel[1]);
					}
					
				}
				
				String Buffer = All.toString();
				
				int textLength = Buffer.length()-LastWord.length();
				int getLastLength = textLength-1 <= 0 ? 0 : textLength;
				All = new StringBuilder();
				All.append(Buffer.substring(0,getLastLength));
				
			}else
			{

				if(fineErVowel == true)
				{
					historyWord.add("A:"+bChar.toText());
					fineErVowel = false;
					for(String str : historyWord)
					{
						System.out.println("-->"+str);
					}
				}
					Sum.append(F);
					Sum.append(A);
					Sum.append(C);
					Sum.append(T);
					Sum.append(B);
					All.append(Sum.toString());
					
					F = new StringBuilder();
					A = new StringBuilder();
					C = new StringBuilder();
					T = new StringBuilder();
					B = new StringBuilder();
					
					Sum = new StringBuilder();
					
					A.append(bChar.toText());
				
			}
			//System.out.println("-->"+All.toString());
			
			//check Er Word
			
			StringBuilder backWord = new StringBuilder();
			for(int start = i ; i < charList.size() ; i++)
			{
				backWord.append(charList.get(i).toText());
			}
			
			System.out.println("check-->"+historyWord);
			System.out.println("back-->"+backWord.toString());
			
			i++;
		}
		
		Sum.append(F);
		Sum.append(A);
		Sum.append(C);
		Sum.append(T);
		Sum.append(B);
		All.append(Sum.toString());
		
		return All.toString();*/
		
		if(charList.size() == 0)
		{
			return "";
		}
		
		return _braillToText(charList);
		
	}
	
	public String getListString()
	{
		StringBuilder str = new StringBuilder();
		
		for(BrailleChar bchar : charList)
		{
			str.append("[");
			str.append(bchar.toString());
			str.append("] ");
		}
		
		return str.toString();
	}
	
	private String _braillToText(ArrayList<BrailleChar> charList)
	{
		boolean start = true;
		int index = 0;
		
		String F = "";
		String A = "";
		String C = "";
		String T = "";
		String B = "";
		
		StringBuilder All = new StringBuilder();
		StringBuilder Sum = new StringBuilder();
		
		
		
		boolean fineErVowel = false;
		boolean EraddB = false;
		boolean StopFineEr = true;
		
		ArrayList<String> ErWordList = new ArrayList<String>();
		ArrayList<String> HisFText = new ArrayList<String>();
		ArrayList<String> HisIndexList = new ArrayList<String>();
		
		while(start==true)
		{
			System.out.println("---Index---"+index);
			
			BrailleChar bChar = charList.get(index);
			
			if(bChar.isTHTone())
			{
				T += bChar.toText();
			}else
			if(bChar.isTHVowelCenter())
			{
				Sum.append(F);
				Sum.append(A);
				Sum.append(C);
				Sum.append(T);
				Sum.append(B);
				
				All.append(Sum.toString());
				
				Sum = new StringBuilder();
				
				F = "";
				A = "";
				C = "";
				T = "";
				B = "";
				
				if(bChar.equals("TH", "100101"))
				{
					fineErVowel = true;
					StopFineEr = false;
				}
				
				boolean cutAllString = true;
				boolean allIsCon = false;
				String LastWord = "";
				
				if(!All.toString().trim().equals(""))
				{
					ArrayList<String> split = TextSplit.getInstance().Split(All.toString());
					LastWord = split.get(split.size()-1);
				
				
					allIsCon = true;
					
					for(int i=LastWord.length()-1;i>=0;i--)
					{
						String schar =  LastWord.substring(i, i+1);
						if(!BrailleDB.getInstance().isConsonant2(schar))
						{
							allIsCon = false;
						}
					}
				}
				
				if(LastWord.length() <= 2)
				{
					if(allIsCon)
					{
						A += LastWord;
					}else{
						A += "Í";
						cutAllString = false;
					}
				}else{
					String lastChar = LastWord.substring(LastWord.length()-1);
					if( BrailleDB.getInstance().isConsonant2(lastChar) )
					{
						A += lastChar;
					}else{
						A += "Í";
						cutAllString = false;
					}
				}
				
				

				for(String vowel : bChar.toText().split(" "))
				{
					String[] getVowel = vowel.split(":");
					
					String point = getVowel[0].toUpperCase();
					
					if(point.equals("F"))
					{
						F += (getVowel[1]);
					}else
					if(point.equals("C"))
					{
						C += (getVowel[1]);
					}else
					if(point.equals("T"))
					{
						T += (getVowel[1]);
					}else
					if(point.equals("B"))
					{
						B += (getVowel[1]);
					}else
					{
						A += (getVowel[1]);
					}
					
				}

				
				String Buffer = All.toString();
				
				if(cutAllString)
				{
					int textLength = Buffer.length()-A.length();
					int getLastLength = textLength-1 <= 0 ? 0 : textLength;
					All = new StringBuilder();
					All.append(Buffer.substring(0,getLastLength));
				}
				
			}else
			{
				if(fineErVowel)
				{
					if(!EraddB)
					{
						boolean sumWord = false;
						boolean check = false;
						
						if(index+1 < charList.size())
						{
							if(charList.get(index+1).isTHVowelBack() )
							{
								index = Integer.parseInt(HisIndexList.get(HisIndexList.size()-1))-1;
								StopFineEr = true;
							}else
							if(charList.get(index+1).equals("TH", "001011") )
							{
								C += "Ô";
								B = charList.get(index).toText();
								
								B += charList.get(index+1).toText();
								
								
								if(index+2 < charList.size() )
								{
									B += charList.get(index+2).toText();
									index+=2;
								}else{
									index+=1;
								}
								
								StringBuilder BuffTestWord = new StringBuilder();
								BuffTestWord.append(F);
								BuffTestWord.append(A);
								BuffTestWord.append(C);
								BuffTestWord.append(T);
								BuffTestWord.append(B);
								
								ErWordList.add(BuffTestWord.toString());
								StopFineEr = true;
								EraddB = true;
								
								
								
							}else{
								check = true;
								sumWord = true;
							}
						}else{
							//index = Integer.parseInt(HisIndexList.get(HisIndexList.size()-1))-1;
							//StopFineEr = true;
							check = true;
							sumWord = true;
						}
							
						if(sumWord)
						{
							
							C += "Ô";
							B = bChar.toText();
							
						}
						
						
						if(check)
						{
							
							StringBuilder BuffTestWord = new StringBuilder();
							BuffTestWord.append(F);
							BuffTestWord.append(A);
							BuffTestWord.append(C);
							BuffTestWord.append(T);
							BuffTestWord.append(B);
							
							ArrayList<TextSplitResult> sResult = TextSplit.getInstance().Split2(BuffTestWord.toString());
							
							boolean BuffTestWord_isKnow = false;
							
							if( sResult.get(sResult.size()-1).isKnow() && sResult.toString().length() >= BuffTestWord.length() )
							{
								BuffTestWord_isKnow = true;
							}
							
							ArrayList<BrailleChar> charList_Back = new ArrayList<BrailleChar>();
							
							boolean BackWord_isKnow = false;
							boolean haveBackWord = false;
							
							String BuffBackString = "";
							
							if(index+1 < charList.size())
							{
								for(int i = index+1 ; i<  charList.size(); i++)
								{
									charList_Back.add(charList.get(i));
								}
								
								BuffBackString = _braillToText(charList_Back);
								
								sResult = TextSplit.getInstance().Split2(BuffBackString.toString());
								
								if( sResult.get(0).isKnow() )
								{
									BackWord_isKnow = true;
								}
								
								haveBackWord = true;
							}
							
							if(BuffTestWord_isKnow && (BackWord_isKnow || !haveBackWord) )
							{
								ErWordList.add(BuffTestWord.toString());
								StopFineEr = true;
								
							}else
							if(BuffTestWord_isKnow && (!BackWord_isKnow || haveBackWord) )
							{
								ErWordList.add(BuffTestWord.toString());
								StopFineEr = true;
								
							}else
							if(!BuffTestWord_isKnow && (BackWord_isKnow || !haveBackWord) )
							{
								ErWordList.add(BuffTestWord.toString());
								StopFineEr = true;
							}
							else{
								index = Integer.parseInt(HisIndexList.get(HisIndexList.size()-1))-1;
								StopFineEr = true;
							}
							
							EraddB = true;
							
							System.out.println("front->"+BuffTestWord);
							System.out.println("back->"+BuffBackString);
							
						}
							
							
						
						
					}else{
						StopFineEr = true;
						
					}
				}
				else
				{
					Sum.append(F);
					Sum.append(A);
					Sum.append(C);
					Sum.append(T);
					Sum.append(B);
					
					All.append(Sum.toString());
					
					F = "";
					A = "";
					C = "";
					T = "";
					B = "";
					
					Sum = new StringBuilder();
					
					A += bChar.toText();
				}

			}
			
			if(fineErVowel)
			{
				if(index+1 < charList.size())
				{
					if(!charList.get(index+1).isTHTone())
					{
					
						if(charList.get(index+1).isConsonant() && !EraddB)
						{
							Sum.append(F);
							Sum.append(A);
							Sum.append(C);
							Sum.append(T);
							Sum.append(B);
							ErWordList.add(Sum.toString());
							HisIndexList.add(String.valueOf(index+1));
							Sum = new StringBuilder();
							
						}
						
					}
				}
			}
				
			if(fineErVowel)
			{
				if(StopFineEr)
				{
					Sum = new StringBuilder();
					
					if(ErWordList.size()-1 >= 0)
					{
						Sum.append(ErWordList.get(ErWordList.size()-1));
					}
					All.append(Sum.toString());
					Sum = new StringBuilder();
					F = "";
					A = "";
					C = "";
					T = "";
					B = "";
					
					fineErVowel = false;
					
				}
			}else{
				if(StopFineEr)
				{
					EraddB = false;
					ErWordList = new ArrayList<String>();
					HisFText = new ArrayList<String>();
					HisIndexList = new ArrayList<String>();
				}
			}
			
			//-----------------------------
			index++;
			if(index >= charList.size())
			{
				start=false;
			}
			
			System.out.println("F:"+F);
			System.out.println("A:"+A);
			System.out.println("C:"+C);
			System.out.println("T:"+T);
			System.out.println("B:"+B);
			System.out.println("Sum:"+Sum.toString());
			System.out.println("All:"+All.toString());
			System.out.println("----");
			
		}
		
		Sum.append(F);
		Sum.append(A);
		Sum.append(C);
		Sum.append(T);
		Sum.append(B);
		All.append(Sum.toString());
		
		return All.toString();
		
	}
	
	
	/*
	private String _braillToText(ArrayList<BrailleChar> charList)
	{
		boolean fineErVowel = false;
		boolean fineErVowelDelay = false; 
		ArrayList<String> historyWord = new ArrayList<String>();
		ArrayList<String> historyString = new ArrayList<String>();
		
		StringBuilder F = new StringBuilder();
		StringBuilder A = new StringBuilder();
		StringBuilder C = new StringBuilder();
		StringBuilder T = new StringBuilder();
		StringBuilder B = new StringBuilder();
		
		StringBuilder All = new StringBuilder();
		StringBuilder Sum = new StringBuilder();
		
		int i = 0;
		for(BrailleChar bChar : charList)
		{

			if(bChar.isTHTone())
			{
				if(fineErVowel == true)
				{
					historyWord.add("T:"+bChar.toText());
				}else{
					if(!A.toString().equals(""))
					{
						T.append(bChar.toText());
					}
				}
				
			}else
			if(bChar.isTHVowelCenter())
			{
				if(!A.toString().equals(""))
				{
					if(bChar.equals("TH", "100101"))
					{
						fineErVowel  = true;
					}
					
					Sum.append(F);
					Sum.append(A);
					Sum.append(C);
					Sum.append(T);
					Sum.append(B);
					
					All.append(Sum.toString());
					
					Sum = new StringBuilder();
					
					F = new StringBuilder();
					A = new StringBuilder();
					C = new StringBuilder();
					T = new StringBuilder();
					B = new StringBuilder();

					ArrayList<String> split = TextSplit.getInstance().Split(All.toString());
					String LastWord = split.get(split.size()-1);
					
					
					
					if(fineErVowel==true)
					{
						historyWord.add("A:"+LastWord);
						historyWord.add("V");
						
						StringBuilder AllBuffer = All;
						
						All = new StringBuilder();
						All.append(AllBuffer.toString().substring(0, AllBuffer.length()-LastWord.length()));
						
					}
					
					if(fineErVowel==false)
					{
						
						A.append(LastWord);
						
						for(String vowel : bChar.toText().split(" "))
						{
							String[] getVowel = vowel.split(":");
							
							String point = getVowel[0].toUpperCase();
							
							if(point.equals("F"))
							{
								F.append(getVowel[1]);
							}else
							if(point.equals("C"))
							{
								C.append(getVowel[1]);
							}else
							if(point.equals("T"))
							{
								T.append(getVowel[1]);
							}else
							if(point.equals("B"))
							{
								B.append(getVowel[1]);
							}else
							{
								A.append(getVowel[1]);
							}
							
						}
						
						String Buffer = All.toString();
						
						int textLength = Buffer.length()-LastWord.length();
						int getLastLength = textLength-1 <= 0 ? 0 : textLength;
						All = new StringBuilder();
						All.append(Buffer.substring(0,getLastLength));
					}
				}
				
			}else
			{

				if(fineErVowel == true)
				{
					historyWord.add("A:"+bChar.toText());
					fineErVowel = false;
				}else{
					Sum.append(F);
					Sum.append(A);
					Sum.append(C);
					Sum.append(T);
					Sum.append(B);
					All.append(Sum.toString());
					
					F = new StringBuilder();
					A = new StringBuilder();
					C = new StringBuilder();
					T = new StringBuilder();
					B = new StringBuilder();
					
					Sum = new StringBuilder();
					
					A.append(bChar.toText());
				}
				
			}
			//System.out.println("-->"+All.toString());
			
			//check Er Word

			
			if(fineErVowelDelay == true)
			{
				int setHisString = 0;
				ArrayList<String> BufferHistoryString = historyString;
				historyString = new ArrayList<String>();
				for(String hisString : BufferHistoryString)
				{
					historyString.add(hisString+bChar.toText());
				}
				
				ArrayList<BrailleChar> charListBack = new ArrayList<BrailleChar>();
				Boolean firstA = false;
				for(int start = i+1 ; start < charList.size() ; start++)
				{
					if(charList.get(start).isFront())
					{
						firstA = true;
					}
					
					if(firstA == true)
					{
						charListBack.add(charList.get(start));
					}
				}
				String backWord = _braillToText(charListBack);
				String frontWord = "";
				
				String erString = "";
				
				for(String his : historyWord)
				{
			
					if(his.equals("V"))
					{
						if(historyWord.get(historyWord.size()-1).split(":")[0].equals("A"))
						{
							erString = "à"+erString+"Ô";
						}else{
							erString = "à"+erString;
						}
					}else
					if(his.split(":")[0].equals("T"))
					{
						if(!historyWord.get(historyWord.size()-1).split(":")[0].equals("A"))
						{
							erString += his.split(":")[1]+"Í";
						}else{
							erString += his.split(":")[1];
						}
					}else{
						erString += his.split(":")[1];
					}
				}
				
				ArrayList<TextSplitResult> erStringSplitResults = TextSplit.getInstance().Split2(erString.trim());
				ArrayList<TextSplitResult> backWordSplitResults = TextSplit.getInstance().Split2(backWord);
				
				
				if(backWord.equals(""))
				{
					historyString.add(erString);
				}else
				if(erStringSplitResults.size() == 1 && erStringSplitResults.get(0).getType() == 1)
				{
					if(backWord.equals(""))
					{
						historyString.add(erString);
					}else
					{
						if(backWordSplitResults.get(0).getType() == 1)
						{
							historyString.add(erString);
						}
					}
				}
				
				System.out.println(historyString);

			}
			
			if(fineErVowel == true){
				fineErVowelDelay = true;
			}else
			{
				if(fineErVowelDelay == true)
				{
					All.append(historyString.get(historyString.size()-1));

					fineErVowel = false;
					fineErVowelDelay = false; 
					historyWord = new ArrayList<String>();
					historyString = new ArrayList<String>();
					
					fineErVowelDelay = false;
				}
			}
			
			i++;
		}
		
		if(fineErVowel == true){
			All.append(historyString.get(historyString.size()-1));

			fineErVowel = false;
			fineErVowelDelay = false; 
			historyWord = new ArrayList<String>();
			historyString = new ArrayList<String>();
		}
		
		Sum.append(F);
		Sum.append(A);
		Sum.append(C);
		Sum.append(T);
		Sum.append(B);
		All.append(Sum.toString());
		
		return All.toString();
	}
	*/
	
	public int size()
	{
		return charList.size();
	}
	
}
