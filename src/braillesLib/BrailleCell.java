package braillesLib;

public class BrailleCell {
	
	private String Language;
	private String BitString;
	
	public BrailleCell(String Language,String BitString) {
		this.BitString = BitString;
		this.Language = Language;
	}
	
	public String getLanguage()
	{
		return Language;
	}
	
	public String getBitString()
	{
		return BitString;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return BitString;
	}
}
