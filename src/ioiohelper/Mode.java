package ioiohelper;

import android.app.Activity;
import android.content.Context;
import android.view.KeyEvent;
import braillesLib.BrailleDB;

import com.projectbrailleboxv2.Keyboard;
import com.projectbrailleboxv2.TTS;

public class Mode extends Object{
	
	public TTS tts;
	public Activity activity;
	public Context context;
	public BrailleDB brailleDB;
	public Keyboard keyboard;
	
	
	public Mode(Context context) {
		
		this.context = context;
		tts = TTS.getInstance();
		keyboard = Keyboard.getInstance();
		brailleDB = BrailleDB.getInstance();
	}
	
	public void onKeyDown(int keyCode, KeyEvent event)
	{
		
	}
	
	public void onKeyUp(int keyCode, KeyEvent event)
	{
		
	}
}
