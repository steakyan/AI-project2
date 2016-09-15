package formulas;

public class Not implements Formula{
	private Formula Value;
	
	public Not(Formula t){
		this.Value = t;
	}
	
	@Override
	public int GetType() {
		return NOT;
	}

	@Override
	public void Display() {
		System.out.print("﹁");
		this.Value.Display();		
	}
	

}
