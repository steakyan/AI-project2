package work;

import terms.*;
import formulas.*;

public class Main {
	public static void main(String[] args){
		Var x = new Var("x");
		Var y = new Var("y");
		Const bill = new Const("bill");
		Predi fax = new Predi("father",x);
		Predi mob = new Predi("mother",bill);
		Predi fab = new Predi("father",bill);
		Predi pa = new Predi("parents",x,fax,mob);
		Predi pb = new Predi("parents",bill,fab,y);
		pa.Display();System.out.println();		
		pb.Display();System.out.println();
		
		Subs t = new Subs(bill,x);
		Subs s = new Subs(mob,y);
		pa.SubsOne(t);
		pa.Display();System.out.println();
		pb.SubsOne(s);
		pb.Display();
	}
}
