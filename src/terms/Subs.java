package terms;

import java.util.ArrayList;

public class Subs {
	private Term NewOne;
	private Term OldOne;
	
	public Subs(Term a,Term b){
		this.NewOne = a;
		this.OldOne = b;
	}
	

	public void Display(){
		this.NewOne.Display();
		System.out.print("/");
		this.OldOne.Display();
	}
	
	public Term GetNewOne(){
		return this.NewOne;
	}
	
	public Term GetOldOne(){
		return this.OldOne;
	}
}
