package braillesLib;

public class BrailleSearchResult {
	
	private Boolean fine = false;
	private BrailleChar fineResult = null;
	private BrailleCharList charList = new BrailleCharList();
	
	public void setFineResult(BrailleChar fineResult) {
		this.fineResult = fineResult;
	}
	
	public BrailleChar getFineResult()
	{
		return fineResult;
	}
	
	public void add(BrailleChar bChar)
	{
		charList.add(bChar);
	}
	
	public Boolean fine()
	{
		if(fineResult != null)
		{
			return true;
		}else
		{
			return false;
		}
	}
	
	public int numRow()
	{
		return charList.size();
	}
	
	public BrailleCharList result()
	{
		return charList;
	}
}
