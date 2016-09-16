package terms;

import java.util.ArrayList;

public class Subs {
	private Term NewOne;
	private Term OldOne;
	
	public Subs(Term newone,Term oldone){
		this.NewOne = newone;
		this.OldOne = oldone;
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
