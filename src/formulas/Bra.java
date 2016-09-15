package formulas;

public class Bra implements Formula{
	private Formula Value;
	
	public Bra(Formula t){
		this.Value = t;
	}

	@Override
	public int GetType() {
		return BRA;
	}

	@Override
	public void Display() {
		System.out.print("(");
		this.Value.Display();
		System.out.print(")");
	}
	
	
}
