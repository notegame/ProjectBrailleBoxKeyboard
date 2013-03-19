package com.projectbrailleboxv2;

import java.util.ArrayList;

import braillesLib.BrailleCell;
import braillesLib.BrailleCellList;
import braillesLib.BrailleChar;
import braillesLib.BrailleCharList;
import braillesLib.BrailleDB;
import braillesLib.BrailleSearchResult;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.media.ToneGenerator;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.RadioButton;
import ioiohelper.Mode;

public class SpellMode extends Mode {

	public EditText Text_ShowText,Text_BrailleString;
	
	private String current_lang = "TH";
	
	//declare
	private BrailleCellList cellList = new BrailleCellList();
	private String BitString_Buffer;
	private char[] Cell1_Buffer,Cell2_Buffer,Cell3_Buffer;
	
	//radio output
	public RadioButton radioB11,radioB12,radioB13,radioB14,radioB15,radioB16;
	public RadioButton radioB21,radioB22,radioB23,radioB24,radioB25,radioB26;
	public RadioButton radioB31,radioB32,radioB33,radioB34,radioB35,radioB36;
	
	public boolean pressed_enter = false;
	
	//sound player
	private MediaPlayer mp_cell_1,mp_cell_2,mp_cell_3,mp_cell_4,mp_cell_5,mp_cell_6,mp_btn_enter,mp_btn_add,mp_btn_del,mp_btn_clear,mp_btn_lang,mp_btn_mode,mp_;
	
	
	public SpellMode(Context context,Activity activity)
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
		
	}
	
	@Override
	public void onKeyUp(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		
		//Cell1
		if(keyCode == keyboard.cell_1_1_button())
		{
			if(pressed_enter) clearCellBuffer();
				
			Cell1_Buffer[0] = '1';
			radioB11.setChecked(true);

			mp_cell_1.start();
		}
		
		if(keyCode == keyboard.cell_1_2_button())
		{
			if(pressed_enter) clearCellBuffer();
			
			Cell1_Buffer[1] = '1';
			radioB12.setChecked(true);
	
			mp_cell_2.start();
			
		}
		
		if(keyCode == keyboard.cell_1_3_button())
		{
			if(pressed_enter) clearCellBuffer();
			
			Cell1_Buffer[2] = '1';
			radioB13.setChecked(true);
			
			mp_cell_3.start();
			
		}
		
		if(keyCode == keyboard.cell_1_4_button())
		{
			if(pressed_enter) clearCellBuffer();
			
			Cell1_Buffer[3] = '1';
			radioB14.setChecked(true);
			
			mp_cell_4.start();
			
		}
		
		if(keyCode == keyboard.cell_1_5_button())
		{
			if(pressed_enter) clearCellBuffer();
			
			Cell1_Buffer[4] = '1';
			radioB15.setChecked(true);
			
			mp_cell_5.start();
			
		}
		
		if(keyCode == keyboard.cell_1_6_button())
		{
			if(pressed_enter) clearCellBuffer();
			
			Cell1_Buffer[5] = '1';
			radioB16.setChecked(true);

			mp_cell_6.start();
			
		}
		
		//Cell2
		if(keyCode == keyboard.cell_2_1_button())
		{
			if(pressed_enter) clearCellBuffer();
			
			Cell2_Buffer[0] = '1';
			radioB21.setChecked(true);
			
			mp_cell_1.start();
			
		}
		
		if(keyCode == keyboard.cell_2_2_button())
		{
			if(pressed_enter) clearCellBuffer();
			
			Cell2_Buffer[1] = '1';
			radioB22.setChecked(true);
	
			mp_cell_2.start();
			
		}
		
		if(keyCode == keyboard.cell_2_3_button())
		{
			if(pressed_enter) clearCellBuffer();
			
			Cell2_Buffer[2] = '1';
			radioB23.setChecked(true);
			
			mp_cell_3.start();
			
		}
		
		if(keyCode == keyboard.cell_2_4_button())
		{
			if(pressed_enter) clearCellBuffer();
			
			Cell2_Buffer[3] = '1';
			radioB24.setChecked(true);
			
			mp_cell_4.start();
			
		}
		
		if(keyCode == keyboard.cell_2_5_button())
		{
			if(pressed_enter) clearCellBuffer();
			
			Cell2_Buffer[4] = '1';
			radioB25.setChecked(true);
			
			mp_cell_5.start();
			
		}
		
		if(keyCode == keyboard.cell_2_6_button())
		{
			if(pressed_enter) clearCellBuffer();
			
			Cell2_Buffer[5] = '1';
			radioB26.setChecked(true);
			
			mp_cell_6.start();
			
		}
		
		//Cell3
		if(keyCode == keyboard.cell_3_1_button())
		{
			if(pressed_enter) clearCellBuffer();
			
			Cell3_Buffer[0] = '1';
			radioB31.setChecked(true);
			
			mp_cell_1.start();
			
		}
		
		if(keyCode == keyboard.cell_3_2_button())
		{
			if(pressed_enter) clearCellBuffer();
			
			Cell3_Buffer[1] = '1';
			radioB32.setChecked(true);
	
			mp_cell_2.start();
			
		}
		
		if(keyCode == keyboard.cell_3_3_button())
		{
			if(pressed_enter) clearCellBuffer();
			
			Cell3_Buffer[2] = '1';
			radioB33.setChecked(true);
			
			mp_cell_3.start();
			
		}
		
		if(keyCode == keyboard.cell_3_4_button())
		{
			if(pressed_enter) clearCellBuffer();
			
			Cell3_Buffer[3] = '1';
			radioB34.setChecked(true);
			
			mp_cell_4.start();
			
		}
		
		if(keyCode == keyboard.cell_3_5_button())
		{
			if(pressed_enter) clearCellBuffer();
			
			Cell3_Buffer[4] = '1';
			radioB35.setChecked(true);

			mp_cell_5.start();
			
		}
		
		if(keyCode == keyboard.cell_3_6_button())
		{
			if(pressed_enter) clearCellBuffer();
			
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
			onPressClearAll();
			if(mp_btn_clear != null)
			{
				mp_btn_clear.stop();
			}
			//mp_btn_clear = MediaPlayer.create(context, R.raw.cleared);
			//mp_btn_clear.start();
			tts.speech(context.getString(R.string.say_clear));
		}
		
		if(keyCode == keyboard.lang_button())
		{
			onPressSwitchLang();
		}
		
		if(keyCode != keyboard.enter_button())
		{
			pressed_enter = false;
		}
		
	}
	
	private void onPressEnter()
	{
		pressed_enter = true; 
		
		//have buffer in 3 cell
		
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
		
		if(sumCell.equals("000000"))
		{
			tts.speech(cellList.toBrailleCharList().toString());
		}else{
			String speech = brailleDB.getSpeech(current_lang, sumCell);
			if(speech != null)
			{
				//Say Word
				tts.speech(speech);
			}else{
				//Say "Not Found"
				//mp_ = MediaPlayer.create(context, R.raw.not_found);
				//mp_.start();
				tts.speech(context.getString(R.string.say_not_found));
			}
		}
	}
	
	private void onPressAdd()
	{
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
		
		if(sumCell.equals("000000"))
		{
			cellList.add(new BrailleCell(current_lang,new String(Cell1_Buffer)));
			tts.speech(context.getString(R.string.say_spacebar));
		}else{
			if(brailleDB.get(current_lang, sumCell) != null)
			{
				cellList.add(new BrailleCell(current_lang,new String(Cell1_Buffer)));
				
				if(add2)
				{
					cellList.add(new BrailleCell(current_lang,new String(Cell2_Buffer)));
				}
				
				if(add3)
				{
					cellList.add(new BrailleCell(current_lang,new String(Cell3_Buffer)));
				}
				
				//Say Word
				
				String speech = brailleDB.getSpeech(current_lang, sumCell);

				tts.speech(speech);
				
				tts.speech(activity.getString(R.string.say_add)+" "+speech);
			}else{
				//Say "Not Found"
				//mp_ = MediaPlayer.create(context, R.raw.not_found);
				//mp_.start();
				tts.speech(context.getString(R.string.say_not_found));
			}
			
		}
		
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
	
	private void onPressDel()
	{
		try{

			if(mp_btn_del != null)
			{
				mp_btn_del.stop();
			}
			
			if((new String(Cell1_Buffer).equals("000000")) && (new String(Cell2_Buffer).equals("000000")) && (new String(Cell3_Buffer).equals("000000")))
			{
				
				BrailleCharList bCharList = cellList.toBrailleCharList();
				if(bCharList.size()>=1)
				{
					BrailleChar LastChar = bCharList.get(bCharList.size()-1);
					if(LastChar.equals("000000"))
					{
						tts.speech(activity.getString(R.string.say_delete)+""+activity.getString(R.string.say_spacebar));
					}else{
						tts.speech(activity.getString(R.string.say_delete)+" "+LastChar.speech());
					}
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
		}catch (Exception e) {
			// TODO: handle exception
		}
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
	
	
	/*
	//test object
	private IOIOButton btn_11,btn_12,btn_13,btn_14,btn_15,btn_16;
	private IOIOButton btn_21,btn_22,btn_23,btn_24,btn_25,btn_26;
	private IOIOButton btn_31,btn_32,btn_33,btn_34,btn_35,btn_36;
	private IOIOButton btn_enter,btn_add,btn_del,btn_clear,btn_lang,btn_mode;
	public EditText Text_ShowText,Text_BrailleString;
	
	//declare
	private BrailleCellList cellList = new BrailleCellList();
	private String BitString_Buffer;
	private char[] Cell1_Buffer,Cell2_Buffer,Cell3_Buffer;
	private Activity activity;
	
	private String current_lang = "TH";
	
	public RadioButton radioB11,radioB12,radioB13,radioB14,radioB15,radioB16;
	public RadioButton radioB21,radioB22,radioB23,radioB24,radioB25,radioB26;
	public RadioButton radioB31,radioB32,radioB33,radioB34,radioB35,radioB36;
	
	private MediaPlayer mp_cell_1,mp_cell_2,mp_cell_3,mp_cell_4,mp_cell_5,mp_cell_6,mp_btn_enter,mp_btn_add,mp_btn_del,mp_btn_clear,mp_btn_lang,mp_btn_mode,mp_;
	
	public SpellMode(Context context,Activity activity)
	{
		super(context);
		this.activity = activity;
		Text_ShowText = (EditText) this.activity.findViewById(R.id.text_showText);
		Text_BrailleString = (EditText) this.activity.findViewById(R.id.text_brailleString);
		
		btn_11 = new IOIOButton();
		btn_12 = new IOIOButton();
		btn_13 = new IOIOButton();
		btn_14 = new IOIOButton();
		btn_15 = new IOIOButton();
		btn_16 = new IOIOButton();
		
		btn_21 = new IOIOButton();
		btn_22 = new IOIOButton();
		btn_23 = new IOIOButton();
		btn_24 = new IOIOButton();
		btn_25 = new IOIOButton();
		btn_26 = new IOIOButton();
		
		btn_31 = new IOIOButton();
		btn_32 = new IOIOButton();
		btn_33 = new IOIOButton();
		btn_34 = new IOIOButton();
		btn_35 = new IOIOButton();
		btn_36 = new IOIOButton();
		
		btn_enter = new IOIOButton();
		btn_add = new IOIOButton();
		btn_del = new IOIOButton();
		btn_clear = new IOIOButton();
		btn_lang = new IOIOButton();
		btn_mode = new IOIOButton();
		
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
		
		//Clear Buffer Input
		onPressClearAll();
	}

	@Override
	public void loop() {
		//Enter Cell
		
		//Input Cell1----------------------------------------------
		if(btn_11.onKeyPress(IOInput.input11))
		{
			Cell1_Buffer[0] = '1';
			radioB11.setChecked(true);

			mp_cell_1 = MediaPlayer.create(context, R.raw.cell_1);
			mp_cell_1.start();
		}
		if(btn_12.onKeyPress(IOInput.input12))
		{
			Cell1_Buffer[1]= '1';
			radioB12.setChecked(true);

			mp_cell_2 = MediaPlayer.create(context, R.raw.cell_2);
			mp_cell_2.start();
		}
		if(btn_13.onKeyPress(IOInput.input13))
		{
			Cell1_Buffer[2]= '1';
			radioB13.setChecked(true);
		
			mp_cell_3 = MediaPlayer.create(context, R.raw.cell_3);
			mp_cell_3.start();
		}
		if(btn_14.onKeyPress(IOInput.input14))
		{
			Cell1_Buffer[3]= '1';
			radioB14.setChecked(true);
		
			mp_cell_4 = MediaPlayer.create(context, R.raw.cell_4);
			mp_cell_4.start();
		}
		if(btn_15.onKeyPress(IOInput.input15))
		{
			Cell1_Buffer[4]= '1';
			radioB15.setChecked(true);
		
			mp_cell_5 = MediaPlayer.create(context, R.raw.cell_5);
			mp_cell_5.start();
		}
		if(btn_16.onKeyPress(IOInput.input16))
		{
			Cell1_Buffer[5]= '1';
			radioB16.setChecked(true);
			
			mp_cell_6 = MediaPlayer.create(context, R.raw.cell_6);
			mp_cell_6.start();
		}
		
		//Input Cell2----------------------------------------------
		if(btn_21.onKeyPress(IOInput.input21))
		{
			Cell2_Buffer[0] = '1';
			radioB21.setChecked(true);

			mp_cell_1 = MediaPlayer.create(context, R.raw.cell_1);
			mp_cell_1.start();
		}
		
		if(btn_22.onKeyPress(IOInput.input22))
		{
			Cell2_Buffer[1]= '1';
			radioB22.setChecked(true);

			mp_cell_2 = MediaPlayer.create(context, R.raw.cell_2);
			mp_cell_2.start();
		}
		if(btn_23.onKeyPress(IOInput.input23))
		{
			Cell2_Buffer[2]= '1';
			radioB23.setChecked(true);
		
			mp_cell_3 = MediaPlayer.create(context, R.raw.cell_3);
			mp_cell_3.start();
		}
		if(btn_24.onKeyPress(IOInput.input24))
		{
			Cell2_Buffer[3]= '1';
			radioB24.setChecked(true);
		
			mp_cell_4 = MediaPlayer.create(context, R.raw.cell_4);
			mp_cell_4.start();
		}
		if(btn_25.onKeyPress(IOInput.input25))
		{
			Cell2_Buffer[4]= '1';
			radioB25.setChecked(true);
		
			mp_cell_5 = MediaPlayer.create(context, R.raw.cell_5);
			mp_cell_5.start();
		}
		if(btn_26.onKeyPress(IOInput.input26))
		{
			Cell2_Buffer[5]= '1';
			radioB26.setChecked(true);
			
			mp_cell_6 = MediaPlayer.create(context, R.raw.cell_6);
			mp_cell_6.start();
		}
		
		//Input Cell3----------------------------------------------
		if(btn_31.onKeyPress(IOInput.input31))
		{
			Cell3_Buffer[0] = '1';
			radioB31.setChecked(true);

			mp_cell_1 = MediaPlayer.create(context, R.raw.cell_1);
			mp_cell_1.start();
		}
		if(btn_32.onKeyPress(IOInput.input32))
		{
			Cell3_Buffer[1]= '1';
			radioB32.setChecked(true);

			mp_cell_2 = MediaPlayer.create(context, R.raw.cell_2);
			mp_cell_2.start();
		}
		if(btn_33.onKeyPress(IOInput.input33))
		{
			Cell3_Buffer[2]= '1';
			radioB33.setChecked(true);
		
			mp_cell_3 = MediaPlayer.create(context, R.raw.cell_3);
			mp_cell_3.start();
		}
		if(btn_34.onKeyPress(IOInput.input34))
		{
			Cell3_Buffer[3]= '1';
			radioB34.setChecked(true);
		
			mp_cell_4 = MediaPlayer.create(context, R.raw.cell_4);
			mp_cell_4.start();
		}
		if(btn_35.onKeyPress(IOInput.input35))
		{
			Cell3_Buffer[4]= '1';
			radioB35.setChecked(true);
		
			mp_cell_5 = MediaPlayer.create(context, R.raw.cell_5);
			mp_cell_5.start();
		}
		if(btn_36.onKeyPress(IOInput.input36))
		{
			Cell3_Buffer[5]= '1';
			radioB36.setChecked(true);
			
			mp_cell_6 = MediaPlayer.create(context, R.raw.cell_6);
			mp_cell_6.start();
		}
		
		
		//Input ControlButton----------------------------------------------
		
		if(btn_enter.onKeyPress(IOInput.enter))
		{
			
			//have buffer in 3 cell
			
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
			
			if(sumCell.equals("000000"))
			{
				tts.speech(cellList.toBrailleCharList().toString());
			}else{
				String speech = brailleDB.getSpeech(current_lang, sumCell);
				if(speech != null)
				{
					//Say Word
					tts.speech(speech);
				}else{
					//Say "Not Found"
					mp_ = MediaPlayer.create(context, R.raw.not_found);
					mp_.start();
				}
			}
			
		}
		
		if(btn_add.onKeyPress(IOInput.add))
		{
			onPressAdd();
		}
		
		if(btn_del.onKeyPress(IOInput.del))
		{
			onPressDel();
		}
		
		if(btn_clear.onKeyPress(IOInput.clear))
		{
			onPressClearAll();
			if(mp_btn_clear != null)
			{
				mp_btn_clear.stop();
			}
			mp_btn_clear = MediaPlayer.create(context, R.raw.cleared);
			mp_btn_clear.start();
		}
		
		if(btn_lang.onKeyPress(IOInput.lang))
		{
			onPressSwitchLang();
		}
		
	}
	
	private void onPressAdd()
	{
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
		
		if(sumCell.equals("000000"))
		{
			cellList.add(new BrailleCell(current_lang,new String(Cell1_Buffer)));
		}else{
			if(brailleDB.get(current_lang, sumCell) != null)
			{
				cellList.add(new BrailleCell(current_lang,new String(Cell1_Buffer)));
				
				if(add2)
				{
					cellList.add(new BrailleCell(current_lang,new String(Cell2_Buffer)));
				}
				
				if(add3)
				{
					cellList.add(new BrailleCell(current_lang,new String(Cell3_Buffer)));
				}
				
				//Say Word
				
				String speech = brailleDB.getSpeech(current_lang, sumCell);

				tts.speech(speech);
				
				tts.speech(activity.getString(R.string.say_add)+" "+speech);
			}else{
				//Say "Not Found"
				mp_ = MediaPlayer.create(context, R.raw.not_found);
				mp_.start();
			}
		}
		
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
	
	private void onPressDel()
	{
		if(mp_btn_del != null)
		{
			mp_btn_del.stop();
		}
		
		if((new String(Cell1_Buffer).equals("000000")) && (new String(Cell2_Buffer).equals("000000")) && (new String(Cell3_Buffer).equals("000000")))
		{
			
			BrailleCharList bCharList = cellList.toBrailleCharList();
			if(bCharList.size()>=1)
			{
				BrailleChar LastChar = bCharList.get(bCharList.size()-1);
				tts.speech(activity.getString(R.string.say_delete)+" "+LastChar.speech());
			}else{
				mp_btn_del = MediaPlayer.create(context, R.raw.deteled);
			}
			
			cellList.del();
			
		}else{
			
			mp_btn_del = MediaPlayer.create(context, R.raw.cancel);
			
		}
		
		mp_btn_del.start();
		
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
		if(mp_btn_lang != null)
		{
			mp_btn_lang.stop();
		}
		
		if(current_lang == "TH" )
		{
			current_lang = "EN";
			mp_btn_lang = MediaPlayer.create(context, R.raw.eng);
			mp_btn_lang.start();
		}else{
			current_lang = "TH";
			mp_btn_lang = MediaPlayer.create(context, R.raw.thai);
			mp_btn_lang.start();
		}
	}
	
	*/
	
	
	
	


}
