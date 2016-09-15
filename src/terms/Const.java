package terms;

public class Const implements Term{
	private String ConstName;

	public Const(String name){
		this.ConstName = name;
	}
	
	@Override
	public boolean IfContainVar(Term t){
		assert(t.GetType() == Var);
		return false;
	}
	
	@Override 
	public Term SubsOne(Subs t){
		return this;
	}
	
	@Override
	public String GetName(){
		return this.ConstName;
	}
	
	@Override
	public void Display() {
		System.out.print(ConstName);		
	}
	
	@Override
	public int GetType(){
		return Const;
	}
}
