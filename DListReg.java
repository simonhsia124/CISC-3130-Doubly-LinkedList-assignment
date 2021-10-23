import java.util.Iterator;

import java.util.*;
public class DListReg implements Iterable<String> {

	public static void main(String[] args) {
		DListReg aList = new DListReg();
		aList.addFirst("Hello"); //adds hello to the front of the list
		aList.addFirst("Goodbye"); //adds goodbye to the front of the list
		aList.addLast("GoodDay"); // adds GoodDay to the end of the list
		aList.addLast("GoodMorning"); //adds GoodMorning to the end of the list
		aList.addLast("Good Night"); //adds Good Night to the end of the list
		aList.addLast("Good Evening"); //adds Good Evening to the end of the list
		aList.addLast("Brooklyn College is a good college");//adds Brooklyn College is a good college to the end of the list
		System.out.println(aList.size());  //returns the full size of the list
		System.out.println(aList.getLast());  //Returns Brooklyn College is a good college
		System.out.println(aList.getFirst());  //Returns Goodbye
		System.out.println(aList.removeFirst()); //Returns Goodbye
		System.out.println(aList.removeLast());  //Returns Brooklyn College is a good college
		System.out.println(aList.contains("Good Night")); //returns true
		System.out.println(aList.contains("Hello")); //returns true
		System.out.println(aList.contains("Goodbye")); //returns true
		System.out.println(aList.contains("GoodDay")); //returns true
		System.out.println(aList.contains("GoodMorning")); //returns true
		System.out.println(aList.contains("Good Evening")); //returns true
		System.out.println(aList.contains("Brooklyn College is a good college")); //returns true
		System.out.println(aList.contains("Night")); //returns false
		System.out.println(aList.indexOf("GoodMorning")); //Returns 3 by itself
		System.out.println(aList.set(9, "okay")); //returns index outof bounds exception error
		System.out.println(aList.set(1, "Hi")); // returns Hello
		System.out.println(aList.get(2)); //returns GoodDay
		//This iterator iterates through the entire List
		/*Iterator<String> iterator = aList.iterator();
		 	while(iterator.hasNext()) {
		 		System.out.println(iterator.next());
		} */

	}
	//Class of DListNode that holds fields
		 private class DListNode {
		 public String data; 
		 public DListNode next;
		 public DListNode previous;
		 }
		 private DListNode nil;
		 private int numElements = 0;
		 //Constructor for the Empty DList
		 public DListReg() {
		 nil = new DListNode();
		 nil.previous = nil;
		 nil.next = nil;
		 nil.data = null;
		 }
		 //Adds an object to the front of a DList
		 public void addFirst(String elem) {
			 DListNode newNode = new DListNode();
			 newNode.data = elem;
			 numElements++;
			 if (nil.next == nil) {
				 nil.next = newNode;
				 nil.previous = newNode;
			 } else {
			 newNode.next = nil.next;
			 newNode.previous = nil;
			 nil.next.previous = newNode;
			 nil.next = newNode;
			 }
		 }
		 //Adds an object to the end of a DList
		 public void addLast(String elem) {
			 DListNode newNode = new DListNode();
			 newNode.data = elem;
			 numElements++;
			 if (nil.previous == nil) {
				 nil.next = newNode;
				 nil.previous = newNode;
			 } else {
				 newNode.previous = nil.previous;
				 newNode.next = nil;
				 nil.previous.next = newNode;
				 nil.previous = newNode;
			 }
		 }
		 //Returns the first Object in a DList
		 public String getFirst() {
			 if (isEmpty())
				 throw new NoSuchElementException();
			 return nil.next.data;
		 }
		 //Returns the last object in a DList.
		 public String getLast() {
			 if(isEmpty())
				 throw new NoSuchElementException();
			 return nil.previous.data;
		 }
		 //Removes the first item in a DList
		 public String removeFirst() {
			 if(isEmpty())
				 throw new NoSuchElementException();
			 String fOld = nil.next.data;
			 nil.next = nil.next.next;
			 nil.next.previous = nil;
			 numElements--;
			 return fOld;
		 }
	
		//Removes the last item in a DList
		 public String removeLast() {
			 if(isEmpty())
				 throw new NoSuchElementException();
			 String LastOld = nil.previous.data;
			 nil.previous = nil.previous.previous;
			 nil.previous.next = nil;
			 numElements--;
			 return LastOld;
		 }
		//Checks if the size of any list is empty if used 
		 private boolean isEmpty() {
				return size() == 0;
			}
		 //Returns the data of the object at a specific index
		 public String get(int index) {
			 if(index > size() || index < 0)
				 throw new IndexOutOfBoundsException();
			 DListNode iter = nil.next;
			 int getCounter = 0;
			 while (iter != nil && getCounter < index) {
				 iter = iter.next;
				 getCounter++;
			 }
			 return iter.data;
		 }
		 /*At a specific index chosen by the user
		 The value is changed and then the old value at that index 
		 is to be returned */
		 public String set(int index, String value) {
			 if(index >= size() || index < 0)
				 throw new IndexOutOfBoundsException();
			 DListNode iter3 = nil.next;
			 int setCounter = 0;
			 while(iter3 != nil && setCounter < index) {
				 iter3 = iter3.next;
				 setCounter++; 
			 }
			 String oValue = iter3.data;
			 iter3.data = value;
			 return oValue;
		 }
		 //Returns true or false if the DList does or does not contains the parameter
		 public boolean contains(Object obj) {
			 if (obj instanceof String) {
				 DListNode iter = nil.next;
				 while(iter != nil) {
					 if (iter.data.equals(obj)) {
						 return true;
					 } 
					 iter = iter.next;
				 }
			 }
			 return false;
		 }
		 //Returns the size of your DList
		 public int size() {
			 return numElements;
		 }
		 /*returns  the index of the Obj thats being searched for
		 If the object is found it returns the index value
		 otherwise it returns -1 */
		 public int indexOf(Object obj) {
			 int tracker = 0;
			 if(obj instanceof String) {
				 DListNode iter2 = nil.next;
				 while(iter2 != nil) {
					 if(iter2.data.equals(obj))
						 return tracker;
					 iter2 = iter2.next;
					 tracker++;	
				 }
			 }
			 return -1;
		 }
		 //Used to iterate through the DList
		 private class DListIterator implements Iterator<String> {
		 private DListNode pointer;


		 public DListIterator() {
			 if(nil.next ==nil)
				 pointer = nil;
			 else
				 pointer = nil.next;

		 	}
		  //Returns true or false if the list has more then 1 value inside of it
		public boolean hasNext() {
				return pointer != nil;
			}

		//Returns the next value in the list
		public String next() {
				String currentPointer = pointer.data;
				pointer = pointer.next;
				return currentPointer;
			}

		}
		//Iterates through through the entire list
		public Iterator<String> iterator() {
			return new DListIterator();
		} 

	}

