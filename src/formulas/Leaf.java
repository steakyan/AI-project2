package formulas;

import terms.*;

public class Leaf implements Formula{
	private Term Value;
	
	public Leaf(Term t){
		this.Value = t;
	}
	
	@Override
	public void Display() {		
		this.Value.Display();
	}

	@Override
	public int GetType() {
		return LEAF;
	}
	
}
