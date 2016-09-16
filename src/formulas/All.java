package formulas;

import java.util.ArrayList;

import terms.*;

public class All implements Formula {
	private Term Var;
	private Formula Value;
	
	public All(Term x,Formula t){
		this.Var = x;
		this.Value = t;
	}
	
	public Term GetVar(){
		return this.Var;
	}
	
	public void SetValue(Formula t){
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
	
	@Override
	public ArrayList<Formula> GetValue() {
		ArrayList<Formula> ret = new ArrayList<Formula>();
		ret.add(this.Value);
		return ret;
	}

	@Override
	public Formula Subs(String t){
		if(this.Var.GetName().equals(t) == true){
			char[] x = t.toCharArray();
			for(int i = 0;i<x.length;i++) x[i] =(char)( ((int)x[i]) + 1);
			String s = String.valueOf(x);
			Var ss = new Var(s);
			Var tt = new Var(t);
			Subs sub = new Subs(ss,tt);
			this.Var = this.Var.SubsOne(sub);
		}
		this.Value.Subs(t);
		return this;
	}

	@Override
	public Formula Subs(Subs t){
		if(this.Var.GetName().equals(t.GetOldOne().GetName()) == true){			
			this.Var = this.Var.SubsOne(t);
		}
		this.Value.Subs(t);
		return this;
	}
}
