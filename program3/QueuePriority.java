import java.util.ArrayList;

public class QueuePriority<E extends Comparable<? super E>> { //Basic priority Queue
	private ArrayList<E> list;
	private ArrayList<String> valueList;
	
	public QueuePriority(){
		list = new ArrayList<E>();
		valueList = new ArrayList<String>();
	}
	
	public ArrayList<E> getList(){
		return list;
	}

	public String toString(){
		String s = "{";
		for(int x = 0; x < list.size(); x++)
			s += " " + list.get(x);
		s += " }\n";
		return s;

	}
	
	public E get(int x){
		return list.get(x);
	}
	
	public E remove(){
		E removed = null;
		if(list.size() > 0)
			removed = list.remove(0);
		return removed;
	}
	
	public void add(E value, String c){
		boolean notAdded = true;
		for(int x = 0; x < list.size();x++){
			int comp = value.compareTo(list.get(x));
			if(comp < 0){
				list.add(x, value);
				valueList.add(x, c);
				notAdded = false;
				break;
			}
		}
		if(notAdded){
			list.add(value);
			valueList.add(c);
		}
		
	}

	public String getString(int x){
		return valueList.get(x);
	}

	public void add(E value){
		boolean notAdded = true;
		for(int x = 0; x < list.size();x++){
			int comp = value.compareTo(list.get(x));
			if(comp < 0){
				list.add(x, value);
				notAdded = false;
				break;
			}
		}
		if(notAdded)
			list.add(value);
		
	}
	
	public boolean isEmpty(){
		return list.size() == 0;
	}
	
	public int size(){
		return list.size();
	}

}
