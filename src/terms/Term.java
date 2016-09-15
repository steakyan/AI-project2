package terms;

public interface Term {
	public static final int Predi = 0;
	public static final int Var = 1;
	public static final int Const = 2;
	
	abstract public Term SubsOne(Subs t);
	abstract public String GetName();
	abstract public void Display();
	abstract public int GetType();
	abstract public boolean IfContainVar(Term t);
}
