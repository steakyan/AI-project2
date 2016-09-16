package work;

import terms.*;

import java.util.ArrayList;

import formulas.*;

public class Main {
	public static void main(String[] args){	
		//TestUnify();
		TestFormula();
	}
	
	public static void TestFormula(){
		Var x = new Var("x");
		Var y = new Var("y");
		Predi f = new Predi("f",x);
		Predi g = new Predi("g",y);
		Leaf t1 = new Leaf(f);
		Leaf t2 = new Leaf(g);
		And t3 = new And(t1,t2);
		Bra t4 = new Bra(t3);
		Exist t5 = new Exist(x,t4);
		All t6 = new All(y,t5);
		t6.Display();System.out.println();
		new CheckFormula().DeleteExist(t6,new ArrayList<Term>()).Display();;
		
	}
	
	public static void TestUnify(){
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
		
		try{
			ArrayList<Subs> ret = pa.Unify(pb);
			for(int i = 0;i<ret.size();i++){
				ret.get(i).Display();
				System.out.println();
			}
		}catch(Exception e){
			System.out.println("Fail!");
			e.printStackTrace();
		}
	}
}
