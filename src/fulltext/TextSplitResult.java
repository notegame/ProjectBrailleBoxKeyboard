package fulltext;

public class TextSplitResult {
	
	private String Text;
	private int Type;
	
	public TextSplitResult(int type,String text) {
		this.Text = text;
		this.Type = type;
		// TODO Auto-generated constructor stub
	}
	
	public int getType()
	{
		return Type;
	}
	
	public Boolean isKnow()
	{
		if(Type == 1 || Type == 2)
		{
			return true;
		}else{
			return false;
		}
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return Text;
	}
	
}
