import java.util.ArrayList;

public class School {
	
	static ArrayList<Integer> classes = new ArrayList<Integer>();
	
	static ArrayList<Integer> Ids = new ArrayList<Integer>();

	public static void addClass(int a){
		if(!classes.contains(a))
			classes.add(a);
		classes.sort(null);
		
		
	}
	
	public static void addId(int id) throws Exception{
		if(Ids.contains(id))
			throw new Exception();
		Ids.add(id);
		
	}
	
	public static boolean isContain(int id){
		return Ids.contains(id);
	}

}
