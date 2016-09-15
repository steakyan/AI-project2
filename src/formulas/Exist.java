package formulas;

import terms.Term;

public class Exist implements Formula{
	private Term Var;
	private Formula Value;
	
	public Exist(Term x,Formula t){
		this.Var = x;
		this.Value = t;
	}
	
	@Override
	public int GetType() {
		return EXIST;
	}

	@Override
	public void Display() {
		System.out.print("彐");
		Var.Display();
		Value.Display();
	}
}
