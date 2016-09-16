package formulas;

import java.util.ArrayList;
import terms.*;

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
	
	@Override
	public ArrayList<Formula> GetValue() {
		ArrayList<Formula> ret = new ArrayList<Formula>();
		ret.add(this.Value);
		return ret;
	}
	
	@Override
	public Formula Subs(String t){
		this.Value.Subs(t);
		return this;
	}
	
	@Override
	public Formula Subs(Subs t){
		this.Value.Subs(t);
		return this;
	}
}
