package com.projectbrailleboxv2;


import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

import braillesLib.BrailleCellList;
import braillesLib.BrailleChar;
import braillesLib.BrailleCharList;
import braillesLib.BrailleDB;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.media.ToneGenerator;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import ioiohelper.Mode;

public class ExampleMode extends Mode {


	//test object
	public EditText Text_ShowText,Text_BrailleString;
	
	//declare
	private BrailleCellList cellList = new BrailleCellList();
	private String BitString_Buffer;
	private char[] Cell1_Buffer,Cell2_Buffer,Cell3_Buffer;
	
	private String current_lang = "TH";
	
	public RadioButton radioB11,radioB12,radioB13,radioB14,radioB15,radioB16;
	public RadioButton radioB21,radioB22,radioB23,radioB24,radioB25,radioB26;
	public RadioButton radioB31,radioB32,radioB33,radioB34,radioB35,radioB36;
	
	private MediaPlayer mp_cell_1,mp_cell_2,mp_cell_3,mp_cell_4,mp_cell_5,mp_cell_6,mp_btn_enter,mp_btn_add,mp_btn_del,mp_btn_clear,mp_btn_lang,mp_btn_mode,mp_,mp_correct,mp_wrong;
	
	private boolean exampleStart = false;
	private boolean example_wait_answer = false;
	
	private int score = 0;
	private int example_i = 0;
	
	private ArrayList<String> array_braille_th = new ArrayList<String>();
	private ArrayList<String> array_braille_en = new ArrayList<String>();
	
	private BrailleChar Example_RandomWord;
	
	private TextView textView1;
	
	public ExampleMode(Context context,Activity activity)
	{
		super(context);
		this.activity = activity;
		this.context = context;
		
		Text_ShowText = (EditText) this.activity.findViewById(R.id.text_showText);
		Text_BrailleString = (EditText) this.activity.findViewById(R.id.text_brailleString);
		
		Text_ShowText.setEnabled(false);
		Text_BrailleString.setEnabled(false);
		
		radioB11 = (RadioButton) activity.findViewById(R.id.inputB11);
		radioB12 = (RadioButton) activity.findViewById(R.id.inputB12);
		radioB13 = (RadioButton) activity.findViewById(R.id.inputB13);
		radioB14 = (RadioButton) activity.findViewById(R.id.inputB14);
		radioB15 = (RadioButton) activity.findViewById(R.id.inputB15);
		radioB16 = (RadioButton) activity.findViewById(R.id.inputB16);
		
		radioB21 = (RadioButton) activity.findViewById(R.id.inputB21);
		radioB22 = (RadioButton) activity.findViewById(R.id.inputB22);
		radioB23 = (RadioButton) activity.findViewById(R.id.inputB23);
		radioB24 = (RadioButton) activity.findViewById(R.id.inputB24);
		radioB25 = (RadioButton) activity.findViewById(R.id.inputB25);
		radioB26 = (RadioButton) activity.findViewById(R.id.inputB26);
		
		radioB31 = (RadioButton) activity.findViewById(R.id.inputB31);
		radioB32 = (RadioButton) activity.findViewById(R.id.inputB32);
		radioB33 = (RadioButton) activity.findViewById(R.id.inputB33);
		radioB34 = (RadioButton) activity.findViewById(R.id.inputB34);
		radioB35 = (RadioButton) activity.findViewById(R.id.inputB35);
		radioB36 = (RadioButton) activity.findViewById(R.id.inputB36);
		
		mp_cell_1 = MediaPlayer.create(context, R.raw.cell_1);
		mp_cell_2 = MediaPlayer.create(context, R.raw.cell_2);
		mp_cell_3 = MediaPlayer.create(context, R.raw.cell_3);
		mp_cell_4 = MediaPlayer.create(context, R.raw.cell_4);
		mp_cell_5 = MediaPlayer.create(context, R.raw.cell_5);
		mp_cell_6 = MediaPlayer.create(context, R.raw.cell_6);
		
		//Clear Buffer Input
		onPressClearAll();
		
		mp_correct 	= MediaPlayer.create(context, R.raw.example_tts_correct);
		mp_wrong 	= MediaPlayer.create(context, R.raw.example_tts_wrong);
		
		mp_ = MediaPlayer.create(context, R.raw.mode_example);
		mp_.start();
		
		while (mp_.isPlaying())
		{
			
		}
		
		mp_ = MediaPlayer.create(context, R.raw.example_do_braille);
		mp_.start();
		
		while (mp_.isPlaying())
		{
			
		}
		
		tts.speech(activity.getString(R.string.say_press_enter_to_start));
		
		Map<String,Map<String,String>> db = BrailleDB.getInstance().getMap();
		
		for(String key : db.get("ALL").keySet())
		{
			array_braille_th.add(key);
			array_braille_en.add(key);
		}
		
		for(String key : db.get("TH").keySet())
		{
			array_braille_th.add(key);
		}
		
		for(String key : db.get("EN").keySet())
		{
			array_braille_en.add(key);
		}
		
		
	}
	
	@Override
	public void onKeyUp(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		
		//Cell1
		if(keyCode == keyboard.cell_1_1_button())
		{
			Cell1_Buffer[0] = '1';
			radioB11.setChecked(true);
			
			mp_cell_1.start();
			
		}
		
		if(keyCode == keyboard.cell_1_2_button())
		{
			Cell1_Buffer[1] = '1';
			radioB12.setChecked(true);

			mp_cell_2.start();
			
		}
		
		if(keyCode == keyboard.cell_1_3_button())
		{
			Cell1_Buffer[2] = '1';
			radioB13.setChecked(true);

			mp_cell_3.start();
			
		}
		
		if(keyCode == keyboard.cell_1_4_button())
		{
			Cell1_Buffer[3] = '1';
			radioB14.setChecked(true);
			
			mp_cell_4.start();
			
		}
		
		if(keyCode == keyboard.cell_1_5_button())
		{
			Cell1_Buffer[4] = '1';
			radioB15.setChecked(true);
			
			mp_cell_5.start();
			
		}
		
		if(keyCode == keyboard.cell_1_6_button())
		{
			Cell1_Buffer[5] = '1';
			radioB16.setChecked(true);
			
			mp_cell_6.start();
			
		}
		
		//Cell2
		if(keyCode == keyboard.cell_2_1_button())
		{
			Cell2_Buffer[0] = '1';
			radioB21.setChecked(true);
			
			mp_cell_1.start();
			
		}
		
		if(keyCode == keyboard.cell_2_2_button())
		{
			Cell2_Buffer[1] = '1';
			radioB22.setChecked(true);
	
			mp_cell_2.start();
			
		}
		
		if(keyCode == keyboard.cell_2_3_button())
		{
			Cell2_Buffer[2] = '1';
			radioB23.setChecked(true);
			
			mp_cell_3.start();
			
		}
		
		if(keyCode == keyboard.cell_2_4_button())
		{
			Cell2_Buffer[3] = '1';
			radioB24.setChecked(true);
			
			mp_cell_4.start();
			
		}
		
		if(keyCode == keyboard.cell_2_5_button())
		{
			Cell2_Buffer[4] = '1';
			radioB25.setChecked(true);
			
			mp_cell_5.start();
			
		}
		
		if(keyCode == keyboard.cell_2_6_button())
		{
			Cell2_Buffer[5] = '1';
			radioB26.setChecked(true);
			
			mp_cell_6.start();
			
		}
		
		//Cell3
		if(keyCode == keyboard.cell_3_1_button())
		{
			Cell3_Buffer[0] = '1';
			radioB31.setChecked(true);
			
			mp_cell_1.start();
			
		}
		
		if(keyCode == keyboard.cell_3_2_button())
		{
			Cell3_Buffer[1] = '1';
			radioB32.setChecked(true);
	
			mp_cell_2.start();
			
		}
		
		if(keyCode == keyboard.cell_3_3_button())
		{
			Cell3_Buffer[2] = '1';
			radioB33.setChecked(true);
			
			mp_cell_3.start();
			
		}
		
		if(keyCode == keyboard.cell_3_4_button())
		{
			Cell3_Buffer[3] = '1';
			radioB34.setChecked(true);
			
			mp_cell_4.start();
			
		}
		
		if(keyCode == keyboard.cell_3_5_button())
		{
			Cell3_Buffer[4] = '1';
			radioB35.setChecked(true);
			
			mp_cell_5.start();
			
		}
		
		if(keyCode == keyboard.cell_3_6_button())
		{
			Cell3_Buffer[5] = '1';
			radioB36.setChecked(true);
			
			mp_cell_6.start();
			
		}
		
		//Control Button
		if(keyCode == keyboard.enter_button())
		{
			onPressEnter();
		}
		
		if(keyCode == keyboard.add_button())
		{
			onPressAdd();
		}
		
		if(keyCode == keyboard.del_button())
		{
			onPressDel();
		}
		
		if(keyCode == keyboard.clear_button())
		{
			onPressClear();
		}
		
		if(keyCode == keyboard.lang_button())
		{
			onPressSwitchLang();
		}
		
	}
	
	private void onPressEnter()
	{
		//Start Example
		
		if(!exampleStart)
		{
			//set new score and count
			score = 0;
			example_i = 0;
			
			//start example
			exampleStart = true;
			
			newExample();
		}else
		{
			//Say Example Again
			//say braille word
			tts.speech(activity.getString(R.string.say_enter_braille)+" "+Example_RandomWord.speech());
			
		}
	}
	
	private void onPressAdd()
	{
		//Enter Answer
		
		if(exampleStart)
		{
			//check in buffer
			checkExample();

			//new Example
			newExample();
		}
		
		clearCellBuffer();
	}
	
	private void onPressClear()
	{
		//answer
		answer();
		
		//new Example
		newExample();
	}
	
	private void answer(){
		String string = Example_RandomWord.getBitString();
		
		int length = string.length();
		int cell = 1;
		String speech = "";
		for(int i = 0; i<length; i++)
		{
			if(string.substring(i, i+1).equals("1"))
			{
				speech += cell+" ";
			}
			
			cell++;
			if(cell>6) cell=1;
		}
		
		tts.getInstance().speech(activity.getString(R.string.say_answer_is)+" "+speech);

		//while playing end
		while(tts.getInstance().isSpeaking())
		{
			
		}
		
	}
	
	private void checkExample()
	{
		example_wait_answer = false;
		
		
		//Say Word in Cell
		String cell1 = new String(Cell1_Buffer);
		String cell2 = new String(Cell2_Buffer);
		String cell3 = new String(Cell3_Buffer);
		String sumCell = "";

		sumCell += cell1;
		
		boolean add2 = false;
		boolean add3 = false;
		
		if( (cell2.equals("000000") && cell3.equals("000000")) == false )
		{
			sumCell += cell2;
			add2 = true;
		}
		
		if(!cell3.equals("000000"))
		{
			sumCell += cell3;
			add3 = true;
		}
		
		boolean Answer = false;
		
		if(Example_RandomWord.equals(sumCell))
		{
			score++;
			
			mp_correct.start();
			//while playing end
			while(mp_correct.isPlaying())
			{
				
			}
			
		}else
		{
			mp_wrong.start();
			//while playing end
			while(mp_wrong.isPlaying())
			{
				
			}
		}

		answer();
	}
	
	private void newExample()
	{
		
		clearCellBuffer();
		
		//count++
		example_i++;
		
		//say count
		tts.speech(activity.getString(R.string.say_example_no)+" "+example_i);
		while(tts.isSpeaking())
		{
			
		}
		
		//random braille word
		Random rn = new Random();
		int size,random;
		String randomWord = "";
		
		if(current_lang == "TH")
		{
			size = array_braille_th.size();
			random = rn.nextInt(size-1);
			randomWord = array_braille_th.get(random);
		}else{
			size = array_braille_en.size();
			random = rn.nextInt(size-1);
			randomWord = array_braille_en.get(random);
		}
		
		Example_RandomWord = new BrailleChar(current_lang, randomWord);
		
		//say braille word
		tts.speech(activity.getString(R.string.say_enter_braille)+" "+Example_RandomWord.speech());
		
		example_wait_answer = true;
	}
	
	private void onPressDel()
	{
		/*if(mp_btn_del != null)
		{
			mp_btn_del.stop();
		}*/
		
		if((new String(Cell1_Buffer).equals("000000")) && (new String(Cell2_Buffer).equals("000000")) && (new String(Cell3_Buffer).equals("000000")))
		{
			
			BrailleCharList bCharList = cellList.toBrailleCharList();
			if(bCharList.size()>=1)
			{
				BrailleChar LastChar = bCharList.get(bCharList.size()-1);
				tts.speech(activity.getString(R.string.say_delete)+" "+LastChar.speech());
			}else{
				//mp_btn_del = MediaPlayer.create(context, R.raw.deteled);
				tts.speech(context.getString(R.string.say_deleted));
			}
			
			cellList.del();
			
		}else{
			
			//mp_btn_del = MediaPlayer.create(context, R.raw.cancel);
			tts.speech(context.getString(R.string.say_cancel));
			
		}
		
		//mp_btn_del.start();
		
		clearCellBuffer();
		
		activity.runOnUiThread(new Runnable() {            
		    @Override
		    public void run() 
			{ 
			  	Text_ShowText.setText(cellList.toBrailleCharList().toString());
			  	Text_BrailleString.setText(cellList.toBrailleCharList().getListString());
			}
		});
	}

	private void clearBitStringBuffer()
	{
		BitString_Buffer = "";
		activity.runOnUiThread(new Runnable() {            
		    @Override
		    public void run() 
			{ 
		    	Text_ShowText.setText("");
		    	Text_BrailleString.setText(cellList.toBrailleCharList().getListString());
			}
		});
		
	}
	
	private void clearCellBuffer()
	{
		Cell1_Buffer = ("000000").toCharArray();
		Cell2_Buffer = ("000000").toCharArray();
		Cell3_Buffer = ("000000").toCharArray();
		
		radioB11.setChecked(false);
		radioB12.setChecked(false);
		radioB13.setChecked(false);
		radioB14.setChecked(false);
		radioB15.setChecked(false);
		radioB16.setChecked(false);
		
		radioB21.setChecked(false);
		radioB22.setChecked(false);
		radioB23.setChecked(false);
		radioB24.setChecked(false);
		radioB25.setChecked(false);
		radioB26.setChecked(false);
		
		radioB31.setChecked(false);
		radioB32.setChecked(false);
		radioB33.setChecked(false);
		radioB34.setChecked(false);
		radioB35.setChecked(false);
		radioB36.setChecked(false);
	}
	
	private void clearBrailleStringBuffer()
	{
		cellList.clear();
	}
	
	private void onPressClearAll()
	{
		clearCellBuffer();
		clearBitStringBuffer();
		clearBrailleStringBuffer();
	}
	
	
	private void onPressSwitchLang()
	{
		/*if(mp_btn_lang != null)
		{
			mp_btn_lang.stop();
		}*/
		
		if(current_lang == "TH" )
		{
			current_lang = "EN";
			tts.speech(context.getString(R.string.say_eng));
			//mp_btn_lang = MediaPlayer.create(context, R.raw.eng);
			//mp_btn_lang.start();
		}else{
			current_lang = "TH";
			tts.speech(context.getString(R.string.say_thai));
			//mp_btn_lang = MediaPlayer.create(context, R.raw.thai);
			//mp_btn_lang.start();
		}
	}
	

}
