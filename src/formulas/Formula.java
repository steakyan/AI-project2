package formulas;

public interface Formula {
	public final static int AND = 0;
	public final static int OR = 1;
	public final static int NOT = 2;
	public final static int IMPLY = 3;
	public final static int ALL = 4;
	public final static int EXIST = 5;
	public final static int BRA = 6;
	public final static int LEAF = 7;
	
	abstract public int GetType();
	abstract public void Display();
}
