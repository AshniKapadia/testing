import java.util.*;

public class myll<T extends Comparable<T>> implements Iterable<T>{
	private myNode<T> head;//stores the first node in the list

	public myll(){//class constructor for first node
		head = null;
	}

	public void ins(T d){//inserts a new node in the list
		head = new myNode<T>(d, head);
	}

	public void insAlpha(T d){//inserts a new node while maintaining alphabetical order
		if((head== null)||(head.getData().compareTo(d) > 0)){//case if data belongs before any node
			head = new myNode<T>(d,head);
		}else{
			myNode<T> pred = head;//stores previous node
			myNode<T> x = pred.getNext();//stores testing node
			
			while(x != null){//case if data belongs somewhere in list
				if(x.getData().compareTo(d) > 0){
					myNode<T> place = new myNode<T>(d,x);
					pred.setNext(place);
					return;
				}
				pred = x;
				x = x.getNext();
			}
			if(x == null){//case if data belongs at end of list
				myNode<T> place = new myNode<T>(d,null);
				pred.setNext(place);
				return;
			}
		}
	}
						

	public void printList(){//prints list
	}

	public boolean contains(T d){//returns boolean value if item is in list
		myNode<T> x = head;
		while(x!= null){
			if(x.getData().equals(d)){
				return true;}//case if data is found
			x = x.getNext();
		}  
		return false;//case if data isnt found
	}

	public boolean delete(T key){//deletes node from list and returns boolean if completed
		myNode<T> pred = head;//stores previous node
		boolean done = false;//stores value if node was deleted
		while((pred != null)&&(pred.getData().equals(key))){//deletes first node if necessary
			head = pred.getNext();
			pred = pred.getNext();
			done = true;}
		while((pred != null)&&(this.contains(key))){//goes through list for data to delete
			myNode<T> x = pred.getNext();//stores testing node
			if((x != null)&&(x.getData().equals(key))){//case if data is found
				pred.setNext(x.getNext());
				done = true;
			}else{
			pred = pred.getNext();}
		}
		return done;
	}





	public void mergeSort(){//accessor for the recursive method
		this.head = recms(head);
	}
	private myNode<T> recms(myNode<T> nod){//recursive method for sorting
		myNode<T> L1 = nod;
		if(nod!=null&&nod.getNext()!= null){//if there is at leas two in the list
			double halfsies = 0;
			for(int og = 0; nod!= null; og++){//loop for finding the index of the halfway point
				nod = nod.getNext();
				halfsies += 0.5;
			}
			myNode<T> newHalf;
			nod = L1;//resets node to head of sublist
			for(int i = 0; i< halfsies-1; i++){
				nod = nod.getNext();
			}
			newHalf = nod.getNext();//stores node containing lower half of luist
			nod.setNext(null);//original list is now cut in half

			myNode<T> L2 = newHalf;
			//lists are splitt

			L1 = recms(L1);//recurses on upper list
			L2 = recms(L2);//recurses on lower list
			nod = merge(L1, L2);//merges lists
		}
		return nod;
	}
		

	private myNode<T> merge( myNode<T> headyone, myNode<T> headytwo ){//merges two lists into one sorted, using their head nodes
		myNode<T> newEnd = null;//stores the end node of the new list
		myNode<T> newList = null;//stores the head node of the new list
			if(headyone!=null&&headyone.getData().compareTo(headytwo.getData())<=0){
				newList = headyone; headyone = headyone.getNext();//stores first node as head node
			}else if(headytwo!=null){
				newList = headytwo; headytwo = headytwo.getNext();//stores second node as head node
			}
		myNode<T> hold;//stores node that needs to be attached
		while(headyone!=null && headytwo!=null){//continues until one node is empty
			if(headyone.getData().compareTo(headytwo.getData())<=0){//condition if firstlist wins
				hold = headyone;
				headyone = headyone.getNext();//replaces head of first list
			}else{//condition if second list wins
				hold = headytwo;
				headytwo = headytwo.getNext();//replaces head of second list
			}
			if(newEnd!=null){//case if adding node to list of 2+
				newEnd.setNext(hold);//sets newEnd as proper node with rest of list attached
				newEnd = newEnd.getNext();//increments newEnd down newList
				newEnd.setNext(null);
			}else{//case if adding to a list of one
				newEnd = hold;
				newList.setNext(newEnd);
			}				
		}
		if(newEnd == null){//finishes unfinished 1 element
			if(headyone!=null){//finishes unfinished list
				newList.setNext(headyone);
			}else if(headytwo!=null){
				newList.setNext(headytwo);
			}
		}else{
			if(headyone!=null){//finishes unfinished list
				newEnd.setNext(headyone);
			}else if(headytwo!=null){
				newEnd.setNext(headytwo);
			}
		}
		return newList;//returns head node of merged list
	}








	public Iterator<T> iterator(){
		return new myStructureiterator<T>(this.head);
	}

	private class myStructureiterator<T extends Comparable<T>> implements Iterator<T>{
		private myNode<T> c;//stores the node containing the next data

		public myStructureiterator(myNode<T> h){//constructor for iterator
			this.c = h;
		}
		public void remove(){}//supposed to remove an element
		public boolean hasNext(){//determines if there is a next element
			return c!= null;
		}
		public T next(){//returns the data of the current element
			T r = c.getData();
			c = c.getNext();
			return r;
		}
	}
}