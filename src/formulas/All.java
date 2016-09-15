package formulas;

import terms.*;

public class All implements Formula {
	private Term Var;
	private Formula Value;
	
	public All(Term x,Formula t){
		this.Var = x;
		this.Value = t;
	}
	
	@Override
	public int GetType() {
		return ALL;
	}

	@Override
	public void Display() {
		System.out.print("∀");
		Var.Display();
		Value.Display();
	}

}
