public class myNode<T extends Comparable<T>>{
	private T data;//stores the string for the node
	private myNode<T> next;//stores the next node in the list
	public myNode(T val, myNode<T> next){//class constructor
		this.data = val;
		this.next = next;
	}

	public T getData(){//returns the data in node
		return this.data;
	}

	public myNode<T> getNext(){//returns the next node
		 return this.next;
	}

	public void setNext(myNode<T> next){//sets the next node
		this.next = next;
	}
}