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
		ArrayList<Formula> src = new ArrayList<Formula>();
		src.addAll(One());
		src.addAll(Two());
		src.addAll(Three());
		src.addAll(Four());
		src.addAll(Five());	
		for(int i = 0;i<src.size();i++){
			src.get(i).Display();System.out.println();
		}
		System.out.println("-----------begin-------------");
		//System.out.println(new Dispel().BFSDispel(src));
		//System.out.println(new Dispel().GroupDispel(src));
		System.out.println(new Dispel().OneFirstDispel(src));
	}
	
	public static ArrayList<Formula> One(){
		CheckFormula asd = new CheckFormula();
		ArrayList<Formula> ret = new ArrayList<Formula>();
		Var x = new Var("x");
		Predi poor = new Predi("poor",x);
		Predi smart = new Predi("smart",x);
		Predi happy = new Predi("happy",x);
		Leaf t1 = new Leaf(poor);
		Leaf t2 = new Leaf(smart);
		Leaf t3 = new Leaf(happy);
		Not t4 = new Not(t1);
		And t5 = new And(t4,t2);
		Imply t6 = new Imply(t5,t3);
		Bra t7 = new Bra(t6);
		All t8 = new All(x,t7);
		//t8.Display();System.out.println();
		ret = asd.ConvertFormula(t8);
		for(int i = 0;i<ret.size();i++){
			//ret.get(i).Display();System.out.println();
		}
		return ret;
	}
	
	public static ArrayList<Formula> Two(){
		CheckFormula asd = new CheckFormula();
		ArrayList<Formula> ret = new ArrayList<Formula>();
		Var y = new Var("y");
		Predi read =new Predi("read",y);
		Predi smart = new Predi("smart",y);
		Leaf t1 = new Leaf(read);
		Leaf t2 = new Leaf(smart);
		Imply t3 = new Imply(t1,t2);
		Bra t4 = new Bra(t3);
		All t5 = new All(y,t4);
		//t5.Display();System.out.println();
		ret = asd.ConvertFormula(t5);
		for(int i = 0;i<ret.size();i++){
			//ret.get(i).Display();System.out.println();
		}
		return ret;
	}
	
	public static ArrayList<Formula> Three(){
		CheckFormula asd = new CheckFormula();
		ArrayList<Formula> ret = new ArrayList<Formula>();
		Const john = new Const("john");
		Predi read = new Predi("read",john);
		Predi poor = new Predi("poor",john);
		Leaf t1 = new Leaf(read);
		Leaf t2 = new Leaf(poor);
		Not t3 = new Not(t2);
		And t4 = new And(t1,t3);
		//t4.Display();System.out.println();
		ret = asd.ConvertFormula(t4);
		for(int i = 0;i<ret.size();i++){
			//ret.get(i).Display();System.out.println();
		}
		return ret;
	}
	
	public static ArrayList<Formula> Four(){
		CheckFormula asd = new CheckFormula();
		ArrayList<Formula> ret = new ArrayList<Formula>();
		Var z = new Var("z");
		Predi happy = new Predi("happy",z);
		Predi exciting = new Predi("exciting",z);
		Leaf t1 = new Leaf(happy);
		Leaf t2 = new Leaf(exciting);
		Imply t3 = new Imply(t1,t2);
		Bra t4 = new Bra(t3);
		All t5 = new All(z,t4);
		//t5.Display();System.out.println();
		ret = asd.ConvertFormula(t5);
		for(int i = 0;i<ret.size();i++){
			//ret.get(i).Display();System.out.println();
		}
		return ret;
	}
	
	public static ArrayList<Formula> Five(){
		CheckFormula asd = new CheckFormula();
		ArrayList<Formula> ret = new ArrayList<Formula>();
		Var w = new Var("w");
		Predi exciting = new Predi("exciting",w);
		Leaf t1 = new Leaf(exciting);
		Exist t2 = new Exist(w,t1);
		Not t3 = new Not(t2);
		//t3.Display();System.out.println();
		ret = asd.ConvertFormula(t3);
		for(int i = 0;i<ret.size();i++){
			//ret.get(i).Display();System.out.println();
		}
		return ret;
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
