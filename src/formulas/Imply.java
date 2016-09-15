package formulas;

public class Imply implements Formula{
	private Formula Left;
	private Formula Right;
	
	public Imply(Formula l,Formula r){
		this.Left = l;
		this.Right = r;
	}
	
	@Override
	public int GetType() {
		return IMPLY;
	}

	@Override
	public void Display() {
		Left.Display();
		System.out.print("→");
		Right.Display();
	}
	
}
