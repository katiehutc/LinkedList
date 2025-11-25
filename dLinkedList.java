/**a linked list container with double-linked nodes - conserves memory (no buffer space)
   d oberle 10/2021
*/
public class dLinkedList<anyType> implements ListInterface<anyType>
{
/** Data field: a reference to the first node in the list */
   private dListNode<anyType> head;

   /** Data field: the size of the list, updated upon addition and subtraction */
   private int listSize;

/**
 * No arg constructor initializes the dLinkedList to an empty list.
 *
 */
   public dLinkedList()
   {
      head = null;
      listSize = 0;
   }

/**
 * Adds a new element to the front of the LinkedList.
 *
 * @param  x a non-null Object.
 */
   public void addFirst(anyType x)	//DONE			
   {
       if (head==null){							//if list is empty
         head = new dListNode(x, null, null);
       }
       else{
         dListNode<anyType> temp = new dListNode(x, null, head);
         head.setPrev(temp);
         head = temp;
       }
      listSize++;
   }

/**
 * Adds a new element to the end of the LinkedList.
 *
 * @param  x a non-null Object.
 */
   public void addLast(anyType x)//DONE
   {
      dListNode<anyType> current = head;
      while(current.getNext() != null){
         current = current.getNext(); 
      }
      current.setNext(new dListNode(x, current.getPrev(), null));
      listSize++;
   }

/**
 * Retrieve the first node in the LinkedList if the head is not null
 *
 * @return the value of the first node in the List, or null if the head is null
 */
   public anyType getFirst()
   {
      if (head==null)							//if list is empty
         return null;
      return head.getValue();
   }

/**
 * Retrieve the last node in the LinkedList if the head is not null
 *
 * @return the value of the last node in the List, or null if the head is null
 */
   public anyType getLast()
   {
      if (head==null)							//if list is empty
         return null; 
      dListNode<anyType> current = head;
      while(current.getNext()!= null)		//make current go to the last element
         current = current.getNext();
      return current.getValue();
   }

/**
 * Remove the first node in the LinkedList and return its value if the head is not null
 *
 * @return the value of the node removed from the List, or null if the LinkedList is empty
 */
   public anyType removeFirst()
   {
      if (head==null){				   //if list is empty
         return null;
      }
      dListNode<anyType> temp = head;
      
      dListNode<anyType> current = head.getNext();
      current.setPrev(null);
      head = current;
      listSize--;
      return (anyType)temp;						//temporary code to keep the file compiling
   }
//****************************************************************

/**
 * Remove the last element of the list and return its value if the list is not empty
 *
 * @return the value of the element removed, or null if the list is empty
 */
   public anyType removeLast() 
   {
   
      if(head==null){					//if list is empty
         return null;
      }
      dListNode<anyType> current = head; 
      while(current.getNext().getNext()!= null){ //current points to the node before the last node
         current = current.getNext(); 
      }
      anyType temp = (anyType)(current.getNext().getValue());
      current.setNext(null);	
      listSize--;	
      return temp;				
   }

/**
 * Returns the number of logical elements stored in the LinkedList.
 *
 * @return the size of the LinkedList.
 */
   public int size()
   {
      return listSize;
   }

/**
 * Finds the Object that resides at a given index
 *
 * @param index a value such that index is greater or equal to 0 and index is less than size()
 * @return the value stored in the node at the given index, or null if the list is empty or invalid index
 */
   public anyType get(int index)		
   {
      if(head==null){					//if list is empty
         return null;
      }
      else{
         int num = 0;
         dListNode<anyType> current = head;
         while(num < index){
            current = current.getNext();
            num++;
         }
         return current.getValue();
      }
   }	

/**
 * Change the Object that resides at a given index to a new value
 *
 * @param index a value such that index is greater or equal to 0 and index is less than size()
 * @param x a non-null Object
 * @return the old value stored in the node at the given index, or null if the list is empty or invalid index
 */
   public anyType set(int index, anyType x)
   {
      if(head==null){					//if list is empty
         return null;
      }

      int num = 0;
      dListNode<anyType> current = head;
      while(num < index){
         current = current.getNext();
      }
      dListNode<anyType> temp = current; 
      current.setValue(x);
      
      return temp.getValue();						//temporary code to keep the file compiling
   }	
//****************************************************************

/**
 * Add a new element at the end of the list
 *
 * @param x a non-null Object
 * @return true
 */
   public boolean add(anyType x)
   {
      addLast(x);
      return true;			
   }	

/**
 * Add a new element at a given index
 *
 * @param index a value such that index is greater or equal to 0 and index is less than size()
 * @param x a non-null Object
 * @return if the element was added successfully, false if the index is invalid
 */
   public boolean add(int index, anyType x)
   {
      if((head==null && index != 0) || (index < 0 || index > this.size())){					//if list is empty
         return false;
      }
      else{
         int num = 0;
         dListNode<anyType> current = head;
         while(num < index - 1){
            current = current.getNext();
            num++;
         }
         current.setNext(new dListNode(x, current, current.getNext()));
         (current.getNext().getNext()).setPrev(current.getNext());
         listSize++;
         return true;
      }		
   }	

/**
 * Remove a node that resides at a given index and return its value
 *
 * @param index a value such that index is greater or equal to 0 and index is less than size()
 * @return the value of the element removed, or null if the list is empty or invalid index
 */
   public anyType remove(int index)		
   {
      if((head==null) || (index < 0 || index > this.size())){					//if list is empty
         return null;
      }
      else{
         int num = 0;
         dListNode<anyType> current = head;
         while(num < index){
            current = current.getNext();
            num++;
         }
         dListNode<anyType> temp = current;
         (current.getNext()).setPrev(current.getPrev());
         (current.getPrev()).setNext(current.getNext());
         listSize--;
         return temp.getValue();
      }
   }	
	

/**
 * Returns a String of all the elements in the List in the form [a0, a1, a2, . . . , an-1]
 *
 * @return String of all the list elements separated by a comma
 */
   public String toString()
   {
      String ans = "[";									//start with left bookend						
      dListNode<anyType> current =  head;
      while(current != null)
      {
         ans += current.getValue().toString();
         current = current.getNext();
         if (current != null)							//don't add comma after the last element
            ans += ",";
      }
      ans += "]";											//end with right bookend
      return ans;
   }

/**
 * Finds if the dLinkedList is empty (true) or contains items (false).
 *
 * @return whether or not the dLinkedList is empty.
 */
   public boolean isEmpty()
   {
      if (head == null)
         return true;
      return false;
   }

}