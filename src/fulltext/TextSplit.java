package fulltext;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

import android.content.Context;



public class TextSplit {
	private static TextSplit uniqInstance;
	
	private LongLexTo tokenizer = null;
	
	public static synchronized TextSplit getInstance() {
	    if (uniqInstance == null) {
	      uniqInstance = new TextSplit();
	    }
	    return uniqInstance;
	}
	
	public void init(Context context)
	{
		try {
			tokenizer=new LongLexTo("lexitron.txt",context);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	public ArrayList<String> Split(String Text)
	{
		tokenizer.wordInstance(Text);
		Vector typeList=tokenizer.getTypeList();
		
		ArrayList<String> stringList = new ArrayList<String>();
		
		int begin, end = 0, type; 
		
		begin=tokenizer.first();
        int i=0;
        while(tokenizer.hasNext()) {
            end=tokenizer.next();
            type=((Integer)typeList.elementAt(i++)).intValue();
            String getText = Text.substring(begin, end);
            if(!getText.trim().isEmpty())
            {
            	stringList.add(getText);
            }
            begin=end;
        }
		
		return stringList;
	}
	
	public ArrayList<TextSplitResult> Split2(String Text)
	{
		tokenizer.wordInstance(Text);
		Vector typeList=tokenizer.getTypeList();
		
		ArrayList<TextSplitResult> stringListTextSplitResult = new ArrayList<TextSplitResult>();
		
		int begin, end = 0, type; 
		
		begin=tokenizer.first();
        int i=0;
        while(tokenizer.hasNext()) {
            end=tokenizer.next();
            type=((Integer)typeList.elementAt(i++)).intValue();
            String getText = Text.substring(begin, end);
            if(!getText.trim().isEmpty())
            {
            	stringListTextSplitResult.add(new TextSplitResult(type,getText));
            }
            begin=end;
        }
		
        return stringListTextSplitResult;

	}
	
}
