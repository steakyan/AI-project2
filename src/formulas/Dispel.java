package formulas;

import java.util.ArrayList;

public class Dispel {
	public final static int AND = 0;
	public final static int OR = 1;
	public final static int NOT = 2;
	public final static int IMPLY = 3;
	public final static int ALL = 4;
	public final static int EXIST = 5;
	public final static int BRA = 6;
	public final static int LEAF = 7;
	
	public boolean OneFirstDispel(ArrayList<Formula> t){
		ArrayList<ArrayList<Formula>> src = new ArrayList<ArrayList<Formula>>();
		for(int i = 0;i<t.size();i++){
			src.add(Divide(t.get(i)));
		}
		//one first
		while(src.size() != 0){
			int index = -1;
			ArrayList<Formula> a = new ArrayList<Formula>();
			for(int i = 0;i<src.size();i++){
				if(src.get(i).size() == 1) {
					a = src.get(i);
					index = i;break;
				}
				if(i == src.size() - 1){
					index = 0;
					a = src.get(0);
				}
			}
			
			int ssize = src.size();
			for(int i = 0;i<ssize;i++){
				if(i != index){
					ArrayList<Formula> temp = DispelTwo(a,src.get(i));
					if(temp.size() != a.size() + src.get(i).size()){
						if(temp.size() == 0) return true;
						src.add(temp);
					}
				}
			}
			src.remove(index);
		}
		return false;
	}
	
	public boolean GroupDispel(ArrayList<Formula> t){
		ArrayList<ArrayList<Formula>> src = new ArrayList<ArrayList<Formula>>();
		for(int i = 0;i<t.size();i++){
			src.add(Divide(t.get(i)));
		}
		// ths src as the group
		ArrayList<ArrayList<Formula>> Get = new ArrayList<ArrayList<Formula>>();
		for(int i = 0;i<t.size();i++){
			Get.add(Divide(t.get(i)));
		}
		while(Get.size() != 0){
			ArrayList<Formula> a = Get.get(0);
			for(int i = 0;i<src.size();i++){
				ArrayList<Formula> temp = DispelTwo(a,src.get(i));
				if(temp.size() != a.size() + src.get(i).size()){
					if(temp.size() == 0) return true;
					Get.add(temp);
				}
			}
			Get.remove(0);
		}
		return false;
	}
	
	public boolean BFSDispel(ArrayList<Formula> t){
		ArrayList<ArrayList<Formula>> src = new ArrayList<ArrayList<Formula>>();
		for(int i = 0;i<t.size();i++){
			src.add(Divide(t.get(i)));
		}
		//bfs
		while(src.size() != 0){
			ArrayList<Formula> a = src.get(0);
			int ssize = src.size();
			for(int i = 1;i<ssize;i++){
				ArrayList<Formula> temp = DispelTwo(a,src.get(i));
				if(temp.size() != a.size() + src.get(i).size()){
					if(temp.size() == 0) return true;
					src.add(temp);
				}
			}
			src.remove(0);
		}
		return false;
	}
	
	public ArrayList<Formula> DispelTwo(ArrayList<Formula> a,ArrayList<Formula> b){
		ArrayList<Formula> ret = new ArrayList<Formula>();
		ArrayList<Formula> a1 = new ArrayList<Formula>();
		ArrayList<Formula> b1 = new ArrayList<Formula>();
		for(int i = 0;i<a.size();i++) a1.add(a.get(i));
		for(int i = 0;i<b.size();i++) b1.add(b.get(i));
		
		for(int i = 0;i<a1.size();i++){
			a1.get(i).Display();System.out.println();
		}
		for(int i = 0;i<b1.size();i++){
			b1.get(i).Display();System.out.println();
		}
		System.out.println("-----");
		
		for(int i = 0;i< a1.size();i++){
			for(int j = 0;j<b1.size();j++){
				if(IsSame(a1.get(i),b1.get(j)) == true){
					a1.remove(i);
					b1.remove(j);
					i--;
					break;
				}
			}
		}
		ret.addAll(a1);
		ret.addAll(b1);
		for(int i = 0;i<ret.size();i++){
			ret.get(i).Display();System.out.println();
		}
		System.out.println();
		return ret;
	}
	
	public ArrayList<Formula> Divide(Formula x){
		ArrayList<Formula> res = new ArrayList<Formula>();
		if(x.GetType() == BRA){
			return Divide(x.GetValue().get(0));
		}
		else if(x.GetType() == LEAF){
			res.add(x);
			return res;
		}
		else if(x.GetType() == NOT){
			res.add(x);
			return res;
		}
		else if(x.GetType() == OR){
			res.addAll(Divide(x.GetValue().get(0)));
			res.addAll(Divide(x.GetValue().get(1)));
			return res;
		}
		else
			return res;
	}
	
	public boolean IsSame(Formula a,Formula b){
		if( (a.GetType() == NOT && b.GetType() != NOT) ||  (a.GetType() != NOT && b.GetType() == NOT)){
			if(GetName(a).equals(GetName(b)) == true){
				return true;
			}
		}
		return false;
	}
	
	public String GetName(Formula x){
		if(x.GetType() == LEAF){
			return ((Leaf) x).GetTerm().GetName();
		}
		else if(x.GetType() == NOT){
			return GetName(x.GetValue().get(0));
		}
		else if(x.GetType() == BRA){
			return GetName(x.GetValue().get(0));
		}
		else 
			return null;
	}
}
