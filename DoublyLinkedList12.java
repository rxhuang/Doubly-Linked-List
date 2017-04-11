/**
 * Title: class DoublyLinkedList12
 * Description: creates doubly linked lists using nodes
 *  NAME: Ruoxin Huang
 *  ID: A99084753
 *  LOGIN: cs12whl
 */

import java.util.*;
public class DoublyLinkedList12<E> extends AbstractList<E> {
    private int nelems;
    private Node head;
    private Node tail;

    protected class Node {
        E data;
        Node next;
        Node prev;

        /** Constructor to create singleton Node */
        public Node(E element)
        {
            this.data = element;
        }

        /** Constructor to create singleton link it between previous and next 
         *   @param element Element to add, can be null
         *   @param prevNode predecessor Node, can be null
         *   @param nextNode successor Node, can be null 
         */
        public Node(E element, Node prevNode, Node nextNode)
        {
            data = element;
            next = nextNode;
            prev = prevNode;
        }

        /** Remove this node from the list. Update previous and next nodes */
        public void remove()
        {
            next = null;
            prev = null;
        }

        /** Set the previous node in the list
         *  @param p new previous node
         */
        public void setPrev(Node p)
        {
            prev = p;
        }

        /** Set the next node in the list
         *  @param n new next node
         */
        public void setNext(Node n)
        {
            next = n;
        }

        /** Set the element 
         *  @param e new element 
         */
        public void setElement(E e)
        {
            data = e;
        }

        /** Accessor to get the next Node in the list */
        public Node getNext()
        {
            return next; 
        }

        /** Accessor to get the prev Node in the list */
        public Node getPrev()
        {
            return prev; 
        } 

        /** Accessor to get the Nodes Element */
        public E getElement()
        {
            return data; 
        } 
    }

    /** ListIterator implementation */ 
    protected class MyListIterator implements ListIterator<E> {

        private boolean forward;
        private boolean canRemove;
        private Node left,right; // Cursor sits between these two nodes
        private int idx;        // Tracks current position. what next() would
        // return 
        public MyListIterator()
        {   
            canRemove = false;
            forward = false;
            // Initiate iterator with left pointing to head and right pointing to first node
            left = head; 
            right = head.getNext();
            idx = 0;
        }

        @Override
        /** Add an element
         *  @param e new element 
         */
        public void add(E e) throws  NullPointerException
        {
            if(e==null){
                throw new NullPointerException();
            }
            if(isEmpty()) // If list is empty
            {
                Node temp = new Node(e, head, tail);
                head.setNext(temp);
                tail.setPrev(temp);
                temp.setNext(tail);
                temp.setPrev(head);
            }else{ // If list is not empty
                Node temp = new Node(e, left, left.getNext());
                left.getNext().setPrev(temp);
                temp.setNext(left.getNext());
                left.setNext(temp);
                temp.setPrev(left);
            }
            canRemove = false;
            nelems++;
        }

        @Override
        /** whether next node exists
         *  @return true if there exist next node
         */
        public boolean hasNext()
        {
            if(right.getElement()!=null) // If next node is not null
            {
                return true;
            }else{
                return false;
            }
             
        }

        @Override
        /** whether previous node exists
         *  @return true if there exist previous node
         */
        public boolean hasPrevious()
        {
            if(left.getElement()!=null) // If previous node is not null
            {
                return true;
            }else{
                return false;
            } 
        }

        @Override
        /** moves iterator forward
         *  @return nex element
         */
        public E next() throws NoSuchElementException
        {
            if(!hasNext()){ // If next node exists
                throw new NoSuchElementException();
            }
            Node temp = right;
            // Move iterator forward
            left = right;
            right = right.getNext();
            idx++;
            canRemove = true;
            forward = true;
            return temp.getElement();  
        }

        @Override
        /** gets next index
         *  @return index of next element 
         */
        public int nextIndex()
        {
            return idx; 
        }

        @Override
         /** moves iterator backward
         *  @return previous element
         */
        public E previous() throws NoSuchElementException
        {
            if(!hasNext()){ // If previous node exists
                throw new NoSuchElementException();
            }
            Node temp = left.getPrev();
            // Move iterator backward
            right = left;
            left = left.getPrev();
            idx--;
            canRemove = true;
            forward = false;
            return getNth(idx).getElement();  
        }

        @Override
        /** gets previous index
         *  @return index of previous element 
         */
        public int previousIndex()
        {
            return idx-1;  
        }

        @Override
        /** removes left node if iterator moving forward and right if moving backward
         */
        public void remove() throws IllegalStateException
        {
            // Catches exceptions
            if(!canRemove){
                throw new IllegalStateException();
            }
           
            if(forward) // If moving forward
            {
                left.getPrev().setNext(right);
                right.setPrev(left.getPrev());
            }else{ // If moving backwards
                right.getNext().setPrev(left);
                left.setNext(right.getNext());
            }
        }

        @Override
        /** set left node to e if iterator moving forward and right if moving backward
         *  @param data to set the node to
         */
        public void set(E e) 
        throws NullPointerException,IllegalStateException
        {
            // Catches exceptions
            if(!canRemove){
                throw new IllegalStateException();
            }
            if(e==null){
                throw new NullPointerException();
            }
            
            if(forward) // If moving forward
            {
                left.setElement(e);
            }else{ // If moving backward
                right.setElement(e);
            }
        }

    }

    //  Implementation of the DoublyLinkedList12 Class

    /** Only 0-argument constructor is define */
    public DoublyLinkedList12()
    {
        // The list is empty when initiated
        nelems = 0;
        // Create dummy nodes
        head = new Node(null); 
        tail = new Node(null);
        // Link head and tail together
        head.next = tail;
        tail.prev = head;
    }

    @Override
    /** returns the size of the list
     * @return the size of the list
     */ 
    public int size()
    {
        return nelems; 
    }

    @Override
    /** gets the data of the element at index 
     * @param index  where in the list to get
     * @return data of the element at index
     */ 
    public E get(int index) throws IndexOutOfBoundsException
    {
        Node temp = getNth(index);
        if(index<0||index>size()||size()==0){
            throw new IndexOutOfBoundsException();
        }
        return temp.getElement();
    }

    @Override
    /** Add an element to the list 
     * @param index  where in the list to add
     * @param data data to add
     * @throws IndexOutOfBoundsException
     * @throws NullPointerException
     */ 
    public void add(int index, E data) 
    throws IndexOutOfBoundsException,NullPointerException
    {
        // Check for exceptions
        if(index<0||index>size()){
            throw new IndexOutOfBoundsException();
        }
        if(data==null){
            throw new NullPointerException();
        }
        
        if(isEmpty()) // If added into an empty list
        {
            Node temp = new Node(data, head, tail);
            head.setNext(temp);
            tail.setPrev(temp);
            temp.setNext(tail);
            temp.setPrev(head);
        }else if(index==0){ // If added between head and 0
            Node temp = new Node(data, head, head.getNext());
            head.getNext().setPrev(temp);
            temp.setNext(head.getNext());
            head.setNext(temp);
            temp.setPrev(head);
        }else if(index==nelems){ // If added between size and tail
            Node temp = new Node(data, tail.getPrev(), tail);
            tail.getPrev().setNext(temp);
            temp.setPrev(tail.getPrev());
            tail.setPrev(temp);
            temp.setNext(tail);
        }else{ // If added between 2 nodes
            Node temp = new Node(data, getNth(index-1), getNth(index));
            temp.setNext(getNth(index));
            temp.setPrev(getNth(index-1));
            getNth(index-1).setNext(temp);
            getNth(index).setPrev(temp);
        }
        nelems++; 
    }

    /** Add an element to the end of the list 
     * @param data data to add
     * @throws NullPointerException
     */ 
    public boolean add(E data) throws NullPointerException
    {
        // Check for exception
        if(data==null){
            throw new NullPointerException();
        }
        
        if(isEmpty()) // Adding into empty list
        {
            Node temp = new Node(data, head, tail);
            head.setNext(temp);
            tail.setPrev(temp);
            temp.setNext(tail);
            temp.setPrev(head);
        }else{ // Adding into not empty list
            Node temp = new Node(data, tail.getPrev(), tail);
            tail.getPrev().setNext(temp);
            temp.setPrev(tail.getPrev());
            tail.setPrev(temp);
            temp.setNext(tail);
        }
        nelems++;
        return true; 
    }

    /** Set the element at an index in the list 
     * @param index  where in the list to add
     * @param data data to add
     * @return element that was previously at this index.
     * @throws IndexOutOfBoundsException
     * @throws NullPointerException
     */ 
    public E set(int index, E data) 
    throws IndexOutOfBoundsException,NullPointerException
    {
        // Cetch exceptions
        if(index<0||index>size()||size()==0){
            throw new IndexOutOfBoundsException();
        }
        if(data==null){
            throw new NullPointerException();
        }
        Node temp = getNth(index);
        E element = temp.getElement();
        temp.setElement(data);
        return element; 
    }

    /** Remove the element at an index in the list 
     * @param index  where in the list to add
     * @return element the data found
     * @throws IndexOutOfBoundsException
     */ 
    public E remove(int index) throws IndexOutOfBoundsException
    {
        // Check exception
        if(index<0||index>size()||size()==0){
            throw new IndexOutOfBoundsException();
        }
        // Get the data to be returned
        E element = getNth(index).getElement();
        // Link previous and next node together
        getNth(index).getNext().setPrev(getNth(index).getPrev());
        getNth(index).getPrev().setNext(getNth(index).getNext());
        nelems--;
        return element; 
    }

    /** Clear the linked list */
    public void clear()
    {
        head = null;
        tail = null;
        nelems=0;
    }

    /** Determine if the list empty 
     *  @return true if empty, false otherwise */
    public boolean isEmpty()
    {
        return nelems==0;  
    }

    public Iterator<E> QQQiterator()
    {
        return new MyListIterator();
    }

    public ListIterator<E> QQQlistIterator()
    {
        return new MyListIterator();
    }

    // Helper method to get the Node at the Nth index
    private Node getNth(int index) 
    {
        Node temp = head.getNext(); // Start at first index
        for(int i=0; i<index; i++) // Loop through the list to get to the index
        {
            temp = temp.getNext();
        }
        return temp;  
    }

    public Iterator<E> iterator()
    {
        return new MyListIterator();
    }

    public ListIterator<E> listIterator()
    {
        return new MyListIterator();
    }

}
// VIM: set the tabstop and shiftwidth to 4 
// vim:tw=78:ts=4:et:sw=4

