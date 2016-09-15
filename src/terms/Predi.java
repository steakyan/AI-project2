package terms;

import java.util.ArrayList;

public class Predi implements Term{
	//it may make sense to maintain a symbol of the predicates 
	private String PrediName;
	private ArrayList<Term> Params;
	
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
		
	public ArrayList<Subs> Unify(Predi b){
		if(this.GetName().equals(b.GetName()) == false){
			System.out.println("Fail!");
			System.exit(-1);
			return null;
		}
		else{
			return DoUnify(this.GetParams(),b.GetParams());
		}
	}
	
	public ArrayList<Subs> DoUnify(ArrayList<Term> a,ArrayList<Term> b){
		ArrayList<Subs> res = new ArrayList<Subs>();
		
		if(a.size() != b.size()){
			System.out.println("Fail!");
			System.exit(-1);
		}
		else{
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
						System.out.println("Fail");
						System.exit(-1);
					}
				}
				else{
					
				}
			}
		}
		return res;
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
	public void Display(){
		System.out.print( PrediName + "(" );
		for(int i = 0;i< Params.size();i++){
			Params.get(i).Display();
			if(i != Params.size() - 1) System.out.print(",");
		}
		System.out.print(")");
	}
	
	@Override
	public int GetType(){
		return Predi;
	}
}
