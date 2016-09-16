package formulas;

import java.util.ArrayList;

import terms.*;

public class Leaf implements Formula{
	private Term Value;
	
	public Leaf(Term t){
		this.Value = t;
	}
	
	public Term GetTerm(){
		return this.Value;
	}
	
	@Override
	public void Display() {		
		this.Value.Display();
	}

	@Override
	public int GetType() {
		return LEAF;
	}
	
	@Override
	public ArrayList<Formula> GetValue() {
		ArrayList<Formula> ret = new ArrayList<Formula>();
		ret.add(this);
		return ret;
	}
	
	@Override
	public Formula Subs(String t){
		char[] x = t.toCharArray();
		for(int i = 0;i<x.length;i++) x[i] =(char)( ((int)x[i]) + 1);
		String s = String.valueOf(x);
		Var ss = new Var(s);
		Var tt = new Var(t);
		Subs sub = new Subs(ss,tt);
		this.Value = this.Value.SubsOne(sub);
		return this;
	}
	
	@Override
	public Formula Subs(Subs t){
		this.Value = this.Value.SubsOne(t);
		return this;
	}
}
