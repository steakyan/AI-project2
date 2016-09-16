package formulas;

import java.util.ArrayList;
import terms.*;

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
	
	@Override
	public ArrayList<Formula> GetValue() {
		ArrayList<Formula> ret = new ArrayList<Formula>();
		ret.add(this.Left);
		ret.add(this.Right);
		return ret;
	}

	@Override
	public Formula Subs(String t){
		this.Left.Subs(t);
		this.Right.Subs(t);
		return this;
	}

	@Override
	public Formula Subs(Subs t){
		this.Left.Subs(t);
		this.Right.Subs(t);
		return this;
	}
}
