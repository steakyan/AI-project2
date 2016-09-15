package terms;

import java.util.ArrayList;

public class Predi implements Term{
	//it may make sense to maintain a symbol of the predicates 
	private String PrediName;
	private ArrayList<Term> Params;
	
	public Predi(){
		
	}
	
	public Predi(String name){
		this.PrediName = name;
		this.Params = new ArrayList<Term>();
	}
	
	public Predi(String name,Term... args){
		this.PrediName = name;
		this.Params = new ArrayList<Term>();
		for(int i = 0;i<args.length;i++){
			this.Params.add(args[i]);
		}
	}
		
	public ArrayList<Subs> Unify(Predi b) throws Exception{
		if(this.GetName().equals(b.GetName()) == false){
			System.out.println("Fail!");
			throw new Exception();
		}
		else{
			try{
				ArrayList<Subs> ret = DoUnify(this.GetParams(),b.GetParams());
				return ret;
			}catch(Exception e){
				e.printStackTrace();
				throw new Exception();				
			}
		}
	}
	
	public ArrayList<Subs> DoUnify(ArrayList<Term> a,ArrayList<Term> b) throws Exception{
		ArrayList<Subs> res = new ArrayList<Subs>();
		
		if(a.size() != b.size()){
			System.out.println("Fail!");throw new Exception();
		}

		if(a.size() == 0){
			return null;
		}		
		else if(a.size() == 1){
			Term a1 = a.get(0);
			Term b1 = b.get(0);
			if(a1.GetType() == Const && b1.GetType() == Const){
				if(a1.GetName().equals(b1.GetName()) == true)
					return null;
				else{
					System.out.println("Fail");throw new Exception();
				}
			}
			else if(a1.GetType() == Var){
				Subs t = new Subs(b1,a1);
				res.add(t);
				if(b1.GetType() == Const){					
					return res;
				}
				else if(b1.GetType() == Var){
					if(b1.GetName().equals(a1.GetName()) == true)
						return null;
					else{
						return res;
					}
				}
				else if(b1.GetType() == Predi){
					if(b1.IfContainVar(a1) == true){
						System.out.println("Fail");	throw new Exception();
					}
					else{
						return res;
					}
				}
			}
			else if(b1.GetType() == Var){
				Subs t = new Subs(a1,b1);
				res.add(t);
				if(a1.GetType() == Const){					
					return res;
				}
				else if(a1.GetType() == Predi){
					if(a1.IfContainVar(b1) == true){
						System.out.println("Fail");
						throw new Exception();
					}
					else{
						return res;
					}
				}
			}
		}
		
		else{
			Term a1 = a.get(0);
			Term b1 = b.get(0);
			try{
				ArrayList<Term> aa = new ArrayList<Term>();aa.add(a1);
				ArrayList<Term> bb = new ArrayList<Term>();bb.add(b1);	
				ArrayList<Subs> ret = new Predi().DoUnify(aa, bb);				
				res.addAll(ret);
				ArrayList<Term> NewA = new ArrayList<Term>();
				ArrayList<Term> NewB = new ArrayList<Term>();
				if(res.size() == 1){
					for(int i = 1;i<a.size();i++){NewA.add(a.get(i).SubsOne(ret.get(0)));}
					for(int i = 1;i<b.size();i++){NewB.add(b.get(i).SubsOne(ret.get(0)));}
				}
				else{
					for(int i = 1;i<a.size();i++){NewA.add(a.get(i));}
					for(int i = 1;i<b.size();i++){NewB.add(b.get(i));}
				}
				res.addAll(new Predi().DoUnify(NewA,NewB));
				
			}catch(Exception e){
				e.printStackTrace();
				throw new Exception();				
			}
		}
		
		return res;
	}
	
	public boolean IfContainVar(Term a1){
		assert(a1.GetType() == Var);
		for(int i = 0;i<this.Params.size();i++){
			if(this.Params.get(i).IfContainVar(a1) == true)
				return true;
		}		
		return false;
	}
	
	public ArrayList<Term> ApplySubs(Subs t, ArrayList<Term> Olds){
		ArrayList<Term> News = new ArrayList<Term>();
		for(int i = 0;i<Olds.size();i++){
			 News.add(Olds.get(i).SubsOne(t));
		}
		return News;
	}
	
	@Override
	public Term SubsOne(Subs t){
		ArrayList<Term> News = new ArrayList<Term>();
		for(int i = 0;i<this.Params.size();i++){
			News.add(Params.get(i).SubsOne(t));
		}
		this.SetParams(News);
		return this;
	}
	
	@Override
	public String GetName(){
		return this.PrediName;
	}	
	
	public ArrayList<Term> GetParams(){
		return this.Params;
	}
	
	public void SetParams(ArrayList<Term> New){
		this.Params = New;
	}
	
	@Override
	public int GetType(){
		return Predi;
	}
	
	@Override
	public void Display(){
		System.out.print( PrediName + "(" );
		for(int i = 0;i< Params.size();i++){
			Params.get(i).Display();
			if(i != Params.size() - 1) System.out.print(",");
		}
		System.out.print(")");
	}
	
	
}
