package formulas;

public class And implements Formula{
	private Formula Left;
	private Formula Right;
	
	public And(Formula l,Formula r){
		this.Left = l;
		this.Right = r;
	}
	
	@Override
	public void Display() {
		Left.Display();
		System.out.print("∧");
		Right.Display();
	}

	@Override
	public int GetType() {
		return AND;
	}
	
}
