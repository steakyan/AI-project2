package formulas;

import java.util.ArrayList;
import terms.*;

public class CheckFormula {
	public final static int AND = 0;
	public final static int OR = 1;
	public final static int NOT = 2;
	public final static int IMPLY = 3;
	public final static int ALL = 4;
	public final static int EXIST = 5;
	public final static int BRA = 6;
	public final static int LEAF = 7;
	
	public ArrayList<Formula> ConvertFormula(Formula x){
		x = CheckImply(x); //x.Display();System.out.println();
		x = NotToAtom(x);//x.Display();System.out.println();
		x = NormalVar(x);
		x = ToLeft(x);
		x = DeleteExist(x,new ArrayList<Term>());
		x = DeleteAll(x);
		x = ChangeToAnd(x);
		ArrayList<Formula> ret = Divide(x);
		for(int i = 0;i<ret.size();i++){
			ret.set(i,NormalVar(ret.get(i)));
		}
		return ret;
	}
	
	public Formula CheckImply(Formula x){
		if(x.GetType() == LEAF) return x;
		if(x.GetType() == IMPLY){
			Formula res ;
			Formula left = CheckImply(x.GetValue().get(0));
			Formula right = CheckImply(x.GetValue().get(1));
			Not t = new Not(left);		
			Or s = new Or(t,right);
			res = s;
			return res;
		}
		else{			
			Formula res;
			ArrayList<Formula> ret = new ArrayList<Formula>(); 
			for(int i = 0;i<x.GetValue().size();i++){
				ret.add(CheckImply(x.GetValue().get(i)));
			}
			switch(x.GetType()){
			// this part actually can be rewritten as a  SetValue function!
			case AND: res = new And(ret.get(0),ret.get(1)); break;
			case OR: res = new Or(ret.get(0),ret.get(1)); break;
			case NOT: res = new Not(ret.get(0)); break;
			case IMPLY: res = new Imply(ret.get(0),ret.get(1)); break;
			case ALL: res = new All(((All) x).GetVar(),ret.get(0)); break;
			case EXIST: res =  new Exist(((Exist) x).GetVar(),ret.get(0)); break;
			case BRA: res = new Bra(ret.get(0)); break;
			case LEAF: res = x ; break;
			default: res = x;
			}
			return res;
		}
	}	
	
	public Formula NotToAtom(Formula x){
		if(x.GetType() == LEAF) return x;
		if(x.GetType() == NOT){
			Formula res = null;
			if(x.GetValue().get(0).GetType() == NOT){
				res = NotToAtom(x.GetValue().get(0).GetValue().get(0));
			}
			else if(x.GetValue().get(0).GetType() == EXIST){
				Formula t1 = x.GetValue().get(0).GetValue().get(0);
				Not t2 = new Not(t1);
				All t3 = new All(((Exist) x.GetValue().get(0)).GetVar(),NotToAtom(t2));
				res = t3;			
			}
			else if(x.GetValue().get(0).GetType() == ALL){
				Formula t1 = x.GetValue().get(0).GetValue().get(0);
				Not t2 = new Not(t1);
				Exist t3 = new Exist(((All) x.GetValue().get(0)).GetVar(),NotToAtom(t2));
				res = t3;
			}
			else if(x.GetValue().get(0).GetType() == AND){
				Not t1 = new Not(x.GetValue().get(0).GetValue().get(0));
				Not t2 = new Not(x.GetValue().get(0).GetValue().get(1));
				Or t3 = new Or(NotToAtom(t1),NotToAtom(t2));
				res = t3;
			}
			else if(x.GetValue().get(0).GetType() == OR){
				Not t1 = new Not(x.GetValue().get(0).GetValue().get(0));
				Not t2 = new Not(x.GetValue().get(0).GetValue().get(1));
				And t3 = new And(NotToAtom(t1),NotToAtom(t2));
				res = t3;
			}
			else if(x.GetValue().get(0).GetType() == BRA){
				Formula t1 = x.GetValue().get(0).GetValue().get(0);
				Not t2 = new Not(t1);
				res = NotToAtom(t2);
				res = new Bra(res);
			}	
			else if(x.GetValue().get(0).GetType() == LEAF){
				return x;
			}
			
			return res;
		}
		else{
			Formula res;
			ArrayList<Formula> ret = new ArrayList<Formula>(); 
			for(int i = 0;i<x.GetValue().size();i++){
				ret.add(NotToAtom(x.GetValue().get(i)));
			}
			switch(x.GetType()){
			case AND: res = new And(ret.get(0),ret.get(1)); break;
			case OR: res = new Or(ret.get(0),ret.get(1)); break;
			case NOT: res = new Not(ret.get(0)); break;
			case IMPLY: res = new Imply(ret.get(0),ret.get(1)); break;
			case ALL: res = new All(((All) x).GetVar(),ret.get(0)); break;
			case EXIST: res =  new Exist(((Exist) x).GetVar(),ret.get(0)); break;
			case BRA: res = new Bra(ret.get(0)); break;
			case LEAF: res = x ; break;
			default: res = x;
			}
			return res;
		}
	}
	
	public Formula NormalVar(Formula x){
		ArrayList<String> ret = new ArrayList<String>();
		ArrayList<Formula> t = x.GetValue();
		
		if(x.GetType() == LEAF) return x;
		else{
			for(int i = 0;i<t.size();i++){
				t.set(i, NormalVar(t.get(i)));
			}
		}
		
		for(int i = 0;i<t.size();i++){
			ArrayList<String> temp = GetName(t.get(i));
			ArrayList<String> repeat = CheckRepeat(ret,temp);
			if(repeat.size() != 0){				
				for(int j = 0;j<repeat.size();j++){
					t.set(i,t.get(i).Subs(repeat.get(j)));
				}
				i--;
			}
			else{				
				ret.addAll(temp);
			}
		}
		
		Formula res;		
		switch(x.GetType()){
		case AND: res = new And(t.get(0),t.get(1)); break;
		case OR: res = new Or(t.get(0),t.get(1)); break;
		case NOT: res = new Not(t.get(0)); break;
		case IMPLY: res = new Imply(t.get(0),t.get(1)); break;
		case ALL: res = new All(((All) x).GetVar(),t.get(0)); break;
		case EXIST: res =  new Exist(((Exist) x).GetVar(),t.get(0)); break;
		case BRA: res = new Bra(t.get(0)); break;
		case LEAF: res = x ; break;
		default: res = x;
		}
		return res;
	}

	public Formula ToLeft(Formula x){
		if(x.GetType() == NOT || x.GetType() == IMPLY || x.GetType() == LEAF){
			return x;
		}
		else if(x.GetType() == ALL){
			((All) x).SetValue(ToLeft(x.GetValue().get(0)));
			return x;
		}
		else if(x.GetType() == EXIST){
			((Exist) x).SetValue(ToLeft(x.GetValue().get(0)));
			return x;
		}
		else if(x.GetType() == BRA){
			Formula ret = ToLeft(x.GetValue().get(0));
			if(ret.GetType() == ALL){
				Bra t1 = new Bra(ToLeft(ret.GetValue().get(0)));
				All t2 = new All(((All) ret).GetVar(),t1);
				return t2;
			}
			else if (ret.GetType() == EXIST){
				Bra t1 = new Bra(ToLeft(ret.GetValue().get(0)));
				Exist t2 = new Exist(((Exist) ret).GetVar(),t1);
				return t2;
			}
		}
		else if(x.GetType() == AND){
			Formula l = ToLeft(x.GetValue().get(0));
			Formula r = ToLeft(x.GetValue().get(1));
			if(l.GetType() == ALL){
				And t1 = new And(l.GetValue().get(0),r);
				All t2 = new All( ((All) l).GetVar(), ToLeft(t1));
				return t2;
			}
			else if(l.GetType() == EXIST){
				And t1 = new And(l.GetValue().get(0),r);
				Exist t2 = new Exist( ((Exist) l).GetVar(), ToLeft(t1));
				return t2;
			}
			else if(r.GetType() == ALL){
				And t1 = new And(l,r.GetValue().get(0));
				All t2 = new All( ((All) r).GetVar(), ToLeft(t1));
				return t2;
			}
			else if(r.GetType() == EXIST){
				And t1 = new And(l,r.GetValue().get(0));
				Exist t2 = new Exist( ((Exist) r).GetVar(), ToLeft(t1));
				return t2;
			}			
		}
		else if(x.GetType() == OR){
			Formula l = ToLeft(x.GetValue().get(0));
			Formula r = ToLeft(x.GetValue().get(1));
			if(l.GetType() == ALL){
				Or t1 = new Or(l.GetValue().get(0),r);
				All t2 = new All( ((All) l).GetVar(), ToLeft(t1));
				return t2;
			}
			else if(l.GetType() == EXIST){
				Or t1 = new Or(l.GetValue().get(0),r);
				Exist t2 = new Exist( ((Exist) l).GetVar(), ToLeft(t1));
				return t2;
			}
			else if(r.GetType() == ALL){
				Or t1 = new Or(l,r.GetValue().get(0));
				All t2 = new All( ((All) r).GetVar(), ToLeft(t1));
				return t2;
			}
			else if(r.GetType() == EXIST){
				Or t1 = new Or(l,r.GetValue().get(0));
				Exist t2 = new Exist( ((Exist) r).GetVar(), ToLeft(t1));
				return t2;
			}
		}
		return x;
	}
	
	public Formula DeleteExist(Formula x ,ArrayList<Term> TermAll){
		if(x.GetType() == LEAF){
			return x;
		}
		else if(x.GetType() == EXIST){
			((Exist) x).SetValue(DeleteExist(x.GetValue().get(0),TermAll));
			
			if(TermAll.size() == 0){
				return x.GetValue().get(0);
			}
			else{
				Term t1 = ((Exist) x).GetVar();
				Predi t2 = new Predi(t1.GetName(),TermAll);
				Subs t3 = new Subs(t2,t1);
				return x.GetValue().get(0).Subs(t3);
			}
		}
		else{
			Formula res;
			ArrayList<Formula> ret = new ArrayList<Formula>(); 
			if(x.GetType() == ALL){
				TermAll.add( ((All) x).GetVar());
			}
			
			for(int i = 0;i<x.GetValue().size();i++){
				ret.add(DeleteExist(x.GetValue().get(i) , TermAll));
			}
			switch(x.GetType()){
			case AND: res = new And(ret.get(0),ret.get(1)); break;
			case OR: res = new Or(ret.get(0),ret.get(1)); break;
			case NOT: res = new Not(ret.get(0)); break;
			case IMPLY: res = new Imply(ret.get(0),ret.get(1)); break;
			case ALL: res = new All(((All) x).GetVar(),ret.get(0)); break;
			case EXIST: res =  new Exist(((Exist) x).GetVar(),ret.get(0)); break;
			case BRA: res = new Bra(ret.get(0)); break;
			case LEAF: res = x ; break;
			default: res = x;
			}
			return res;
		}
	}
	
	public Formula DeleteAll(Formula x){
		if(x.GetType() == LEAF) return x;
		else if(x.GetType() == ALL){
			return DeleteAll(x.GetValue().get(0));
		}
		else{
			Formula res;
			ArrayList<Formula> ret = new ArrayList<Formula>(); 			
			for(int i = 0;i<x.GetValue().size();i++){
				ret.add(DeleteAll(x.GetValue().get(i)));
			}
			switch(x.GetType()){
			case AND: res = new And(ret.get(0),ret.get(1)); break;
			case OR: res = new Or(ret.get(0),ret.get(1)); break;
			case NOT: res = new Not(ret.get(0)); break;
			case IMPLY: res = new Imply(ret.get(0),ret.get(1)); break;
			case ALL: res = new All(((All) x).GetVar(),ret.get(0)); break;
			case EXIST: res =  new Exist(((Exist) x).GetVar(),ret.get(0)); break;
			case BRA: res = new Bra(ret.get(0)); break;
			case LEAF: res = x ; break;
			default: res = x;
			}
			return res;
		}
	}

	public Formula ChangeToAnd(Formula x){
		if(x.GetType() == LEAF){
			return x;
		}
		else if(x.GetType() == OR){
			Formula t1 = x.GetValue().get(0);
			Formula t2 = x.GetValue().get(1);
			Formula t3 = ChangeToAnd(t1);
			Formula t4 = ChangeToAnd(t2);
			if(t3.GetType() == BRA && t3.GetValue().get(0).GetType() == AND){
				Bra t5 = new Bra(new Or(t3.GetValue().get(0).GetValue().get(0),t4));
				Bra t6 = new Bra(new Or(t3.GetValue().get(0).GetValue().get(1),t4));
				And t7 = new And(ChangeToAnd(t5),ChangeToAnd(t6));
				return t7;
			}
			else if(t4.GetType() == BRA && t4.GetValue().get(0).GetType() == AND){
				Bra t5 = new Bra(new Or(t3,t4.GetValue().get(0).GetValue().get(0)));
				Bra t6 = new Bra(new Or(t3,t4.GetValue().get(0).GetValue().get(1)));
				And t7 = new And(ChangeToAnd(t5),ChangeToAnd(t6));
				return t7;
			}
			else{
				return new Or(t3,t4);
			}
		}
		else{
			Formula res;
			ArrayList<Formula> ret = new ArrayList<Formula>(); 			
			for(int i = 0;i<x.GetValue().size();i++){
				ret.add(ChangeToAnd(x.GetValue().get(i)));
			}
			switch(x.GetType()){
			case AND: res = new And(ret.get(0),ret.get(1)); break;
			case OR: res = new Or(ret.get(0),ret.get(1)); break;
			case NOT: res = new Not(ret.get(0)); break;
			case IMPLY: res = new Imply(ret.get(0),ret.get(1)); break;
			case ALL: res = new All(((All) x).GetVar(),ret.get(0)); break;
			case EXIST: res =  new Exist(((Exist) x).GetVar(),ret.get(0)); break;
			case BRA: res = new Bra(ret.get(0)); break;
			case LEAF: res = x ; break;
			default: res = x;
			}
			return res;
		}
	}
	
	public ArrayList<Formula> Divide(Formula x){
		ArrayList<Formula> ret = new ArrayList<Formula>();
		if(x.GetType() == BRA){
			return Divide(x.GetValue().get(0));
		}
		else if(x.GetType() != AND){
			ret.add(x);
			return ret;
		}
		else{
			ret.addAll(Divide(x.GetValue().get(0)));
			ret.addAll(Divide(x.GetValue().get(1)));
			return ret;
		}
	}
	
	public ArrayList<String> CheckRepeat(ArrayList<String> Src,ArrayList<String> New){
		ArrayList<String> repeat = new ArrayList<String>();
		for(int i = 0;i<New.size();i++){
			for(int j  = 0;j<Src.size();j++){
				if(New.get(i).equals(Src.get(j)) == true){
					repeat.add(New.get(i));
					break;
				}
			}
		}
		return repeat;
	}
	
	public ArrayList<String> GetName(Formula x){
		ArrayList<String> res = new ArrayList<String>();
		if(x.GetType() == ALL){
			res.add(((All) x).GetVar().GetName());
		}
		else if(  x.GetType() == EXIST){
			res.add(((Exist) x).GetVar().GetName());
		}
		return res;
	}
	
	public ArrayList<String> GetName(Term t){
		ArrayList<String> res = new ArrayList<String>();
		if(t.GetType() == 1){
			res.add(t.GetName());
		}
		else if(t.GetType() == 2){
			;
		}
		else if(t.GetType() == 0){
			ArrayList<Term> x = ((Predi) t).GetParams();
			for(int i = 0;i<x.size();i++){
				res.addAll(GetName(x.get(i)));
			}
		}
		return res;
	}

}
