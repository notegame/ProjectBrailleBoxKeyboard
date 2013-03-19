package braillesLib;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import android.content.Context;

public class BrailleDB {
	
	private static BrailleDB uniqInstance;
	
	private Map<String,String> th_con = new HashMap<String,String>();
	private Map<String,String> th_front = new HashMap<String,String>();
	private Map<String,String> th_tone_vowel = new HashMap<String,String>();
	private Map<String,String> th_vowel_center = new HashMap<String,String>();
	private Map<String,String> th_vowel_back = new HashMap<String,String>();
	
	private Map<String,Map<String,String>> db = new HashMap<String, Map<String,String>>();
	private Map<String,Map<String,String>> db_speech = new HashMap<String, Map<String,String>>();
	private Map<String,TreeMap<String,Integer>> dbSort = new HashMap<String, TreeMap<String,Integer>>();
	
	public static synchronized BrailleDB getInstance() {
	    if (uniqInstance == null) {
	      uniqInstance = new BrailleDB();
	    }
	    return uniqInstance;
	}
	
	public void LoadAllFile()
	{
		LoadAllFile(null);
	}
	
	public void LoadAllFile(Context context)
	{
		//--- load thai vowel -------
		
		try {
			BufferedReader buff=null;
			if(context == null)
			{
				File fileDir = new File("assets/braille_text/TH/vowel_center.txt");
				buff = new BufferedReader(new InputStreamReader(new FileInputStream(fileDir), "UTF8"));
			}else{
				InputStream in = context.getAssets().open("braille_text/TH/vowel_center.txt");
				buff = new BufferedReader(new InputStreamReader(in, "UTF8"));
			}
			
			
			String  i = buff.readLine();
			
			while (i != null)
			{
				String[] lang = i.split("=");
				th_vowel_center.put(lang[0].trim().replace(" ", ""), lang[1].trim());
			    i = buff.readLine();
			}
			
			buff.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Load 'braille_text/TH/vowel_center.txt' Fail!");
		}
		
		//--- load thai vowel -------
		
		try {
			BufferedReader buff=null;
			if(context == null)
			{
				File fileDir = new File("assets/braille_text/TH/vowel_back.txt");
				buff = new BufferedReader(new InputStreamReader(new FileInputStream(fileDir), "UTF8"));
			}else{
				InputStream in = context.getAssets().open("braille_text/TH/vowel_back.txt");
				buff = new BufferedReader(new InputStreamReader(in, "UTF8"));
			}
			
			
			String  i = buff.readLine();
			
			while (i != null)
			{
				String[] lang = i.split("=");
				th_vowel_back.put(lang[0].trim().replace(" ", ""), lang[1].trim());
			    i = buff.readLine();
			}
			
			buff.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Load 'braille_text/TH/vowel_back.txt' Fail!");
		}
		
		//--- load thai vowel -------
		
		try {
			BufferedReader buff=null;
			if(context == null)
			{
				File fileDir = new File("assets/braille_text/TH/th.txt");
				buff = new BufferedReader(new InputStreamReader(new FileInputStream(fileDir), "UTF8"));
			}else{
				InputStream in = context.getAssets().open("braille_text/TH/th.txt");
				buff = new BufferedReader(new InputStreamReader(in, "UTF8"));
			}
			
			
			String  i = buff.readLine();
			
			while (i != null)
			{
				String[] lang = i.split("=");
				th_con.put(lang[0].trim().replace(" ", ""), lang[1].trim());
			    i = buff.readLine();
			}
			
			buff.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Load 'braille_text/TH/th.txt' Fail!");
		}
		
		//--- load tone vowel -------
		try {
			BufferedReader buff=null;
			if(context == null)
			{
				File fileDir = new File("assets/braille_text/TH/tone_marks.txt");
				buff = new BufferedReader(new InputStreamReader(new FileInputStream(fileDir), "UTF8"));
			}else{
				InputStream in = context.getAssets().open("braille_text/TH/tone_marks.txt");
				buff = new BufferedReader(new InputStreamReader(in, "UTF8"));
			}
			
			
			String  i = buff.readLine();
			
			while (i != null)
			{
				String[] lang = i.split("=");
				th_tone_vowel.put(lang[0].trim(), lang[1].trim());
			    i = buff.readLine();
			}
			
			buff.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Load 'braille_text/TH/tone_marks.txt' Fail!");
		}
		
		if(context != null)
		{
		
			try {
				for(String directory : context.getAssets().list("braille_text"))
				{
					//File[] files = new File("file:///android_asset/braille_text/"+directory.getName()).listFiles();
					
					String[] files = context.getAssets().list("braille_text/"+directory);
					
					Map<String, String> param = new HashMap<String, String>();
					for(String file : files)
					{
						InputStream in = context.getAssets().open("braille_text/"+directory+"/"+file);
						BufferedReader buff = new BufferedReader(new InputStreamReader(in, "UTF8"));
						
						String  i = buff.readLine();
						while (i != null)
						{
							String[] text = i.split("=");
							
							String speech = text.length == 3 ? text[2].trim() : text[1].trim();
							
							this.add(directory,text[0].replace(" ", "").trim(),text[1].trim(),speech);
						    i = buff.readLine();
						}
						buff.close();
					}
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}else
		{
		
			File[] directories;
			//GET All Directory
			File folder = new File("assets/braille_text");
			directories = folder.listFiles(); 
			
			for(File directory : directories)
			{
				try {
					File[] files = new File("assets/braille_text/"+directory.getName()).listFiles(); 
		
					Map<String, String> param = new HashMap<String, String>();
					
					for(File file : files)
					{
						File fileDir = new File("assets/braille_text/"+directory.getName()+"/"+file.getName());
						BufferedReader buff = new BufferedReader(new InputStreamReader(new FileInputStream(fileDir), "UTF8"));
						
						String  i = buff.readLine();
						while (i != null)
						{
							String[] text = i.split("=");
							String speech = text.length == 3 ? text[3].trim() : "";
							this.add(directory.getName().toString(),text[0].replace(" ", "").trim(),text[1].trim(),speech);
						    i = buff.readLine();
						}
						buff.close();
					}
					
				}catch (Exception e) {
					// TODO: handle exception
				}
			}
		}
		

		
	}
	
	public void add(String Language,String BitString,String Text,String Speech)
	{
		if(db.get(Language) == null)
		{
			Map<String, String> map = new HashMap<String, String>();
			map.put(BitString, Text);
			db.put(Language, map);
			
			map = new HashMap<String, String>();
			map.put(BitString, Speech);
			db_speech.put(Language, map);
		}else{
			db.get(Language).put(BitString, Text);
			db_speech.get(Language).put(BitString, Speech);
		}
		
		Map<String,Integer> sizeSort = new HashMap<String, Integer>();
		
		for(String key : db.get(Language).keySet())
		{
			sizeSort.put(key, key.length());
		}
		
		ValueComparator bvc =  new ValueComparator(sizeSort);
		TreeMap<String,Integer> sorted_map = new TreeMap<String,Integer>(bvc);
		sorted_map.putAll(sizeSort);
		
		dbSort.put(Language, sorted_map);
	}
	
	public String get(String Language,String BitString)
	{
		if(BitString.equals("000000"))
		{
			return " ";
		}
		else
		if(db.get(Language).get(BitString) != null)
		{
			return db.get(Language).get(BitString);
		}else
		if(db.get(Language).get(BitString) == null)
		{
			return db.get("ALL").get(BitString);
		}else
		{
			return null;
		}
		
	}
	
	public String getSpeech(String Language,String BitString)
	{
		if(BitString.equals("000000"))
		{
			return " ";
		}
		else
		if(db_speech.get(Language).get(BitString) != null)
		{
			return db_speech.get(Language).get(BitString);
		}else
		if(db_speech.get(Language).get(BitString) == null)
		{
			return db_speech.get("ALL").get(BitString);
		}else
		{
			return null;
		}
		
	}
	
	public Map<String,String> get(String Language)
	{
		return db.get(Language);
	}
	
	public Map<String, Map<String, String>> get()
	{
		return db;
	}
	
	public Boolean isTHVowelCenter(String BitString)
	{
		if(th_vowel_center.get(BitString) == null)
		{
			return false;
		}else{
			return true;
		}
	}
	
	public Boolean isTHVowelBack(String BitString)
	{
		if(th_vowel_back.get(BitString) == null)
		{
			return false;
		}else{
			return true;
		}
	}
	
	public Boolean isTHTone(String BitString)
	{
		if(th_tone_vowel.get(BitString) == null)
		{
			return false;
		}else{
			return true;
		}
	}
	
	public Boolean isConsonant(String BitString)
	{
		if(th_con.get(BitString) == null)
		{
			return false;
		}else{
			return true;
		}
	}
	
	public Boolean isConsonant2(String BitString)
	{
		for(String value : th_con.values())
		{
			if(BitString.trim().equals(value.trim()))
			{
				return true;
			}
		}
		return false;
	}
	
	
	public BrailleSearchResult search(String Language , String BitString)
	{
		
		BrailleSearchResult searchResult = new BrailleSearchResult();
		
		TreeMap<String,Integer> reAll = new TreeMap<String,Integer>();
		
		for(String str : dbSort.get("ALL").keySet())
		{
			reAll.put(str, dbSort.get("ALL").get(str));
		}
		
		for(String str : dbSort.get(Language).keySet())
		{
			reAll.put(str, dbSort.get(Language).get(str));
		}
		
		for(String str : reAll.keySet())
		{
			if(BitString.length() <= str.length())
			{
				if(str.equals(BitString))
				{
					searchResult.setFineResult(new BrailleChar(Language, BitString));
				}
				
				if(str.substring(0, BitString.length()).equals(BitString))
				{
					searchResult.add(new BrailleChar(Language,str));
				}
			}
		}
		
		
		return searchResult;
	}
	
	public Map<String,Map<String,String>> getMap()
	{
		return db;
	}

	
}

class ValueComparator implements Comparator<String> {

    Map<String, Integer> base;
    public ValueComparator(Map<String, Integer> base) {
        this.base = base;
    }

    // Note: this comparator imposes orderings that are inconsistent with equals.    
    public int compare(String a, String b) {
        if (base.get(a) <= base.get(b)) {
            return -1;
        } else {
            return 1;
        } // returning 0 would merge keys
    }
}
