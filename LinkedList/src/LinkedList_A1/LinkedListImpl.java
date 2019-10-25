/**
 * COMP 410
 *See inline comment descriptions for methods not described in interface.
 *
*/
package LinkedList_A1;

public class LinkedListImpl implements LIST_Interface {
  Node sentinel;//this will be the entry point to your linked list (the head)
  
  Node newNode;
  Node current;
  Node head;
  int numElts = 0;
  
  public LinkedListImpl(){//this constructor is needed for testing purposes. Please don't modify!
    sentinel=new Node(0); //Note that the root's data is not a true part of your data set!
  }
  
  //implement all methods in interface, and include the getRoot method we made for testing purposes. Feel free to implement private helper methods!
  
  public Node getRoot(){ //leave this method as is, used by the grader to grab your linkedList easily.
    return sentinel;
  }

@Override
public boolean insert(double elt, int index) {
		if(index < 0 || index > this.size()) {
			return false;
		}
		else if(this.size() == 0) {
			newNode = new Node(elt);
			sentinel.next = newNode;
			newNode.prev = sentinel;
			sentinel.prev = newNode;
			newNode.next = sentinel;
			head = newNode;
			numElts++;
			return true;
		}
		else if(index == numElts) {
			Iterate(index-1);
			newNode = new Node(elt);
			current.next = newNode;
			newNode.prev = current;
			
			newNode.next = sentinel;
			sentinel.prev = newNode;
			numElts++;
			return true;
		}
		else if(index == 0) {
			Iterate(index-1);
			newNode = new Node(elt);
			sentinel.next = newNode;
			newNode.prev = sentinel;
			
			newNode.next = head;
			head.prev = newNode;
			head = newNode;
			numElts++;
			
			return true;
		}
		
		//insert
		else{
		Iterate(index-1);
		newNode = new Node(elt);
		newNode.prev = current;
		current.next.prev = newNode;
		
		newNode.next = current.next;
		current.next = newNode;
		numElts++;
		return true;
		}
				
	
}
public Node Iterate(int index) {
	current = head;
	int counter = 0;
	while(current != sentinel) {
		if(counter < index) {
			current = current.next;
			counter++;
		}
		else {
			break;
		}
	}
	return current;
}

@Override
public boolean remove(int index) {
	if(index < 0 || index > numElts) {
		return false;
	}
	
	else if(index == 0  ) {
		Iterate(index);
		sentinel.next = current.next;
		current.next.prev = sentinel;
		current.next = head;
		numElts--;
		return true;
	}
	else if(index == numElts-1 ) {
		Iterate(index);
		current.prev.next = sentinel;
		sentinel.prev = current.prev;
		numElts--;
		return true;
		
	}
	else {
		Iterate(index);
		current.prev.next = current.next;
		current.next.prev = current.prev;
		numElts--;
		return true;
	}
	
}

@Override
public double get(int index) {
	if(index < 0 || index > numElts || numElts == 0) {
		return Double.NaN;
	}
	Iterate(index);
	
	return current.data;
}

@Override
public int size() {
	return numElts;
}

@Override
public boolean isEmpty() {
	if(numElts > 0) {
		return false;
	}
	else {
	return true;	
	}

}

@Override
public void clear() {
	
	sentinel.next = sentinel;
	sentinel.prev = sentinel;
	current = null;
	head = null;
	numElts = 0;
}
}