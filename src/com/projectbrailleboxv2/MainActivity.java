package com.projectbrailleboxv2;

import fulltext.TextSplit;
import ioiohelper.Mode;

import braillesLib.BrailleDB;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.BatteryManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {
                                                
	public TextView textview;     
	public Keyboard keyboard;
	
	public Mode mode;
	public TTS tts;
	public BrailleDB brailleDB;
	
	public Handler mChildHandler;
	
	private int mode_count = 0;
	
	private MediaPlayer mp_;
	
	private boolean onPressFunctionKey = false,onDown=false,sayed = false;
	
	private AudioManager audio;
	
	//private IntentFilter ifilter;
	//private Intent batteryStatus;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
		//batteryStatus = registerReceiver(null, ifilter);
		
		audio = (AudioManager) getSystemService(AUDIO_SERVICE);
		
		//set TTS
		tts = TTS.getInstance();
		tts.setTTS(this);
		
		TextSplit.getInstance().init(this);
		
		//load braille file
		brailleDB = BrailleDB.getInstance();
		brailleDB.LoadAllFile(this);
		
		textview = (TextView) findViewById(R.id.textview);
		
		loadKeyboardSetting();
		
		mode = new SpellMode(this,this);
		
		Runnable runnable = new Runnable() {

			public void run() {
				
				while(true)
				{
					
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					/*
						int status = batteryStatus.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
						boolean isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING ||
						                     status == BatteryManager.BATTERY_STATUS_FULL;
						if(isCharging)
						{
							if(!sayed)
							{
								tts.speech(getString(R.string.say_charging));
								sayed = true;
								Log.i("Log", "is Charging");
							}
						}else{
							sayed = false;
							Log.i("Log", "NOT Charging");
						}
					*/
					
					if((keyboard.cell_1_1_onpress||keyboard.cell_1_2_onpress||keyboard.cell_1_3_onpress) && keyboard.enter_onpress)
					{
						
						if(!onDown)
						{
						
							if(keyboard.cell_1_1_onpress && keyboard.enter_onpress)
							{
								onPressFunctionKey = true;
								audio.adjustStreamVolume(AudioManager.STREAM_MUSIC,
						                AudioManager.ADJUST_RAISE, AudioManager.FLAG_SHOW_UI);
								tts.speech(getString(R.string.say_volumeUp));
							}
							
							if(keyboard.cell_1_2_onpress && keyboard.enter_onpress)
							{
								onPressFunctionKey = true;
								audio.adjustStreamVolume(AudioManager.STREAM_MUSIC,
						                AudioManager.ADJUST_LOWER, AudioManager.FLAG_SHOW_UI);
								tts.speech(getString(R.string.say_volumeDown));
							}
							
							if(keyboard.cell_1_3_onpress && keyboard.enter_onpress)
							{
								onPressFunctionKey = true;
								batteryLevel();
							}
						
						}
						
						onDown = true;
						
					}
					
					if(!(keyboard.cell_1_1_onpress||keyboard.cell_1_2_onpress||keyboard.cell_1_3_onpress) && keyboard.enter_onpress)
					{
						onDown = false;
					}
					
					if((!(keyboard.cell_1_1_onpress||keyboard.cell_1_2_onpress||keyboard.cell_1_3_onpress) && !keyboard.enter_onpress))
					{
						onPressFunctionKey = false;
						onDown = false;
					}
				}
					
			}
		};
		new Thread(runnable).start();
		
		Runnable runnable2 = new Runnable() {

			public void run() {
				
				while(true)
				{
					
					try {
						Thread.sleep(60000);
						batteryLevelLoop();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
					
			}
		};
		new Thread(runnable2).start();

		
	}
	
	private void loadKeyboardSetting() {
		keyboard = Keyboard.getInstance();
		keyboard.loadConfig(this);
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		keyboard.onKeyDown(keyCode, event);
		Log.i("Log", "down Key:"+keyCode);
		return super.onKeyDown(keyCode, event);
	}
	
	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		keyboard.onKeyUp(keyCode, event);
		
		
		if(!onPressFunctionKey)
		{
			try
			{
				
				mode.onKeyUp(keyCode, event);
				
				if(keyCode == keyboard.mode_button())
				{
					mode_count++;
		    		
		    		if(mode_count>1) mode_count = 0;
		    		
		    		if(mode_count == 0)
		    		{
		    			tts.getInstance().speech("");
		    			mp_ = MediaPlayer.create(this, R.raw.mode_spell);
		    			mp_.start();
		    			mode = new SpellMode(this,this);
		    		}else
					if(mode_count == 1)
		    		{
		    			mode = new ExampleMode(this,this);
		    		}
				}
				
			}catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		Log.i("Log", "onKeyUp:"+keyCode);
		
		return super.onKeyUp(keyCode, event);
	}

	private void batteryLevel() {
        BroadcastReceiver batteryLevelReceiver = new BroadcastReceiver() {

			@Override
			public void onReceive(Context context, Intent intent) {
				// TODO Auto-generated method stub
				context.unregisterReceiver(this);
                int rawlevel = intent.getIntExtra("level", -1);
                int scale = intent.getIntExtra("scale", -1);
                int level = -1;
                if (rawlevel >= 0 && scale > 0) {
                    level = (rawlevel * 100) / scale;
                }
                String say = "";
                
                if(level <= 5)
                {
                	say = getString(R.string.say_battery_low10);
                }
                else
                if(level <= 10)
                {
                	say = getString(R.string.say_battery_low5);
                }
                else
                if(level >= 98)
                {
                	say = getString(R.string.say_battery_full);
                }
                else{
                	say = getString(R.string.say_battery_have)+" "+level+" %";
                }
                
                tts.speech(say);
                
                
                while(tts.isSpeaking())
                {
                	
                }
			}
        	
        };
        IntentFilter batteryLevelFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(batteryLevelReceiver, batteryLevelFilter);
    }
	
	private void batteryLevelLoop() {
        BroadcastReceiver batteryLevelReceiver = new BroadcastReceiver() {

			@Override
			public void onReceive(Context context, Intent intent) {
				// TODO Auto-generated method stub
				context.unregisterReceiver(this);
                int rawlevel = intent.getIntExtra("level", -1);
                int scale = intent.getIntExtra("scale", -1);
                int level = -1;
                if (rawlevel >= 0 && scale > 0) {
                    level = (rawlevel * 100) / scale;
                }
                String say = "";
                
                if(level <= 5)
                {
                	say = getString(R.string.say_battery_low10);
                }
                else
                if(level <= 10)
                {
                	say = getString(R.string.say_battery_low5);
                }
                
                tts.speech(say);
                
                while(tts.isSpeaking())
                {
                	
                }
			}
        	
        };
        IntentFilter batteryLevelFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(batteryLevelReceiver, batteryLevelFilter);
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
