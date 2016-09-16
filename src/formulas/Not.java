package formulas;

import java.util.ArrayList;
import terms.*;

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
