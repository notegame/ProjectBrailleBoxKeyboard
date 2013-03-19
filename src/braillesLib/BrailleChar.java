package braillesLib;

public class BrailleChar {
	
	private String Language;
	private String BitString;
	
	public BrailleChar(String Language,String BitString) {
		// TODO Auto-generated constructor stub
		this.Language = Language;
		this.BitString = BitString;
	}
	
	public String getLanguage()
	{
		return Language;
	}
	
	public String getBitString()
	{
		return BitString;
	}
	
	public String toText()
	{
		return BrailleDB.getInstance().get(Language, BitString);
	}
	
	public String speech()
	{
		return BrailleDB.getInstance().getSpeech(Language, BitString);
	}
	
	public Boolean isTHVowelCenter()
	{
		return BrailleDB.getInstance().isTHVowelCenter(BitString);
	}
	
	public Boolean isTHVowelBack()
	{
		return BrailleDB.getInstance().isTHVowelBack(BitString);
	}
	
	public Boolean isTHTone()
	{
		return BrailleDB.getInstance().isTHTone(BitString);
	}
	
	
	public Boolean isConsonant()
	{
		return BrailleDB.getInstance().isConsonant(BitString);
	}
	
	public boolean equals(Object obj) {
		return BitString.equals(obj);
	}
	
	public boolean equals(String Language, Object obj) {
		// TODO Auto-generated method stub
		if(BitString.equals(obj) && Language.equals(this.Language)){
			return true;
		}else{
			return false;
		}
	}
	
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.BitString;
	}
	
}
