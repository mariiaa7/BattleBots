package interfaces;
import java.util.ArrayList;

public class Sorter<T> {
	public Sorter(Comparable<T> c) {
		comparator = c;
		array = new ArrayList<T>();
	}
	
	public void add(T obj) {
		for(int i=0; i<array.size(); i++)
			if(comparator.compare(array.get(i), obj)>0) {
				array.add(i, obj);
				return;
			}
		array.add(obj);
	}
	
	public T getObj(int i) {
		return array.get(i);
	}
	
	private ArrayList<T> array;
	private Comparable<T> comparator; 
}
