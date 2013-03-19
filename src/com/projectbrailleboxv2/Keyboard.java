package com.projectbrailleboxv2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import android.R.integer;
import android.content.Context;
import android.view.KeyEvent;

public class Keyboard {
	
	private Context context;
	private Map<String,String> keycode = new HashMap<String,String>();
	public boolean cell_1_1_onpress = false,cell_1_2_onpress = false,cell_1_3_onpress = false,enter_onpress = false;
	
	private static Keyboard uniqInstance;
	public static synchronized Keyboard getInstance() {
	    if (uniqInstance == null) {
	      uniqInstance = new Keyboard();
	    }
	    return uniqInstance;
	}
	
	public void loadConfig(Context context)
	{
		this.context = context;
		// TODO Auto-generated method stub
		try {
			InputStream in = context.getAssets().open("keyboard_setting.txt");
			BufferedReader buff = new BufferedReader(new InputStreamReader(in, "UTF8"));
			
			String  i = buff.readLine();
			
			while (i != null)
			{
				if(!i.isEmpty())
				{
					String[] lang = i.split("=");
					keycode.put(lang[0].trim(), lang[1].trim());
				}
				i = buff.readLine();
			}
			
			buff.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int cell_1_1_button()
	{
		return Integer.parseInt(keycode.get("cell_1_1"));
	}
	
	public int cell_1_2_button()
	{
		return Integer.parseInt(keycode.get("cell_1_2"));
	}
	
	public int cell_1_3_button()
	{
		return Integer.parseInt(keycode.get("cell_1_3"));
	}
	
	public int cell_1_4_button()
	{
		return Integer.parseInt(keycode.get("cell_1_4"));
	}
	
	public int cell_1_5_button()
	{
		return Integer.parseInt(keycode.get("cell_1_5"));
	}
	
	public int cell_1_6_button()
	{
		return Integer.parseInt(keycode.get("cell_1_6"));
	}
	
	public int cell_2_1_button()
	{
		return Integer.parseInt(keycode.get("cell_2_1"));
	}
	
	public int cell_2_2_button()
	{
		return Integer.parseInt(keycode.get("cell_2_2"));
	}
	
	public int cell_2_3_button()
	{
		return Integer.parseInt(keycode.get("cell_2_3"));
	}
	
	public int cell_2_4_button()
	{
		return Integer.parseInt(keycode.get("cell_2_4"));
	}
	
	public int cell_2_5_button()
	{
		return Integer.parseInt(keycode.get("cell_2_5"));
	}
	
	public int cell_2_6_button()
	{
		return Integer.parseInt(keycode.get("cell_2_6"));
	}
	
	public int cell_3_1_button()
	{
		return Integer.parseInt(keycode.get("cell_3_1"));
	}
	
	public int cell_3_2_button()
	{
		return Integer.parseInt(keycode.get("cell_3_2"));
	}
	
	public int cell_3_3_button()
	{
		return Integer.parseInt(keycode.get("cell_3_3"));
	}
	
	public int cell_3_4_button()
	{
		return Integer.parseInt(keycode.get("cell_3_4"));
	}
	
	public int cell_3_5_button()
	{
		return Integer.parseInt(keycode.get("cell_3_5"));
	}
	
	public int cell_3_6_button()
	{
		return Integer.parseInt(keycode.get("cell_3_6"));
	}

	public int enter_button()
	{
		return Integer.parseInt(keycode.get("enter"));
	}
	

	public int add_button()
	{
		return Integer.parseInt(keycode.get("add"));
	}
	

	public int del_button()
	{
		return Integer.parseInt(keycode.get("del"));
	}
	

	public int clear_button()
	{
		return Integer.parseInt(keycode.get("clear"));
	}
	

	public int lang_button()
	{
		return Integer.parseInt(keycode.get("lang"));
	}
	
	public int mode_button()
	{
		return Integer.parseInt(keycode.get("mode"));
	}
	

	public void onKeyUp(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		
		if(keyCode == cell_1_1_button())
		{
			cell_1_1_onpress = false;
		}
		
		if(keyCode == cell_1_2_button())
		{
			cell_1_2_onpress = false;
		}
		
		if(keyCode == cell_1_3_button())
		{
			cell_1_3_onpress = false;
		}
		
		if(keyCode == enter_button())
		{
			enter_onpress = false;
		}
	}

	public void onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		
		if(keyCode == cell_1_1_button())
		{
			cell_1_1_onpress = true;
		}
		
		if(keyCode == cell_1_2_button())
		{
			cell_1_2_onpress = true;
		}
		
		if(keyCode == cell_1_3_button())
		{
			cell_1_3_onpress = true;
		}
		
		if(keyCode == enter_button())
		{
			enter_onpress = true;
		}
		
	}
	
}
