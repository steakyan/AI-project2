package terms;

import java.util.ArrayList;

public class Var implements Term {
	private String VarName;
	private ArrayList<String> VarField;
	
	public Var(String name){
		this.VarName = name;
		this.VarField = new ArrayList<String>();
	}
	
	public Var(String name,ArrayList<String> field){
		this.VarName = name;
		this.VarField = field;
	}
	
	@Override
	public boolean IfContainVar(Term a1){
		assert(a1.GetType() == Var);
		if(a1.GetName().equals(this.VarName) == true) return true;
		else return false;
	}
	
	@Override
	public Term SubsOne(Subs t){
		if(t.GetOldOne().GetName().equals(this.GetName()) == true){
			return t.GetNewOne();
		}
		else
			return this;
	}
	
	@Override
	public String GetName(){
		return this.VarName;
	}
	
	@Override
	public void Display(){
		System.out.print(VarName);
	}
	
	@Override
	public int GetType(){
		return Var;
	}

}
