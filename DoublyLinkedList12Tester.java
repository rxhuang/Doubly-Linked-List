import org.junit.*;
import static org.junit.Assert.*;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.*;

/**
 *  Title: class LinkedListTester
 *  Description: JUnit test class for LinkedList class
 *  NAME: Ruoxin Huang
 *  ID: A99084753
 *  LOGIN: cs12whl
 * */

/*
 * You should modify the information above to add your name 
 * 
 * Finally, when your tester is complete, you will rename it DoublyLinkedList12Tester.java 
 * (renaming LinkedList to DoublyLinkedList12 everywhere in the file) so that you can
 * use it to test your DoublyLinkedList12 and MyListIterator classes.
 */
public class DoublyLinkedList12Tester
{
    private DoublyLinkedList12<Integer> empty;
    private DoublyLinkedList12<Integer> one;
    private DoublyLinkedList12<Integer> several;
    private DoublyLinkedList12<String>  slist;
    static final int DIM = 5;

    /**
     * Standard Test Fixture. An empty list, a list with one entry (0) and 
     * a list with several entries (0,1,2)
     */ 
    @Before
    public void setUp()
    {
        empty = new DoublyLinkedList12<Integer>();
        one = new DoublyLinkedList12<Integer>();
        one.add(0,new Integer(0));
        several = new DoublyLinkedList12<Integer>();
        // List: 1,2,3,...,Dim
        for (int i = DIM; i > 0; i--)
            several.add(0,new Integer(i));

        // List: "First","Last"
        slist = new DoublyLinkedList12<String>();
        slist.add(0,"First");
        slist.add(1,"Last");
    }

    /** Test if heads of the lists are correct */
    @Test
    public void testGetHead()
    {
        assertEquals("Check 0",new Integer(0),one.get(0));
        assertEquals("Check 0",new Integer(1),several.get(0));
    }

    /** Test if size of lists are correct */
    @Test
    public void testListSize()
    {
        assertEquals("Check Empty Size",0,empty.size());
        assertEquals("Check One Size",1,one.size());
        assertEquals("Check Several Size",DIM,several.size());
    }

    /** Test setting a specific entry */
    @Test
    public void testSet()
    {
        try 
        {
            several.set(several.size()+1,0);
            fail("Should have generated an exception!");  
        }
        catch(IndexOutOfBoundsException e)
        {
            //  normal
        }
        try 
        {
            several.set(several.size(),null);
            fail("Should have generated an exception!");  
        }
        catch(NullPointerException e)
        {
            //  normal
        }
        assertEquals("Check previous element is returned",new Integer(1), several.set(0,0)); // Check previous element is returned
        slist.set(1,"Final");
        assertEquals("Setting specific value", "Final",slist.get(1));
    }

    /** Test isEmpty */
    @Test
    public void testEmpty()
    {
        assertTrue("empty is empty",empty.isEmpty());
        assertTrue("one is not empty",!one.isEmpty());
        assertTrue("several is not empty",!several.isEmpty());
    }

    /** Test out of bounds exception on get */
    @Test
    public void testGetException()
    {
        try 
        {
            empty.get(0);
            // This is how you can test when an exception is supposed 
            // to be thrown
            fail("Should have generated an exception");  
        }
        catch(IndexOutOfBoundsException e)
        {
            //  normal
        }
    }

    /** Test iterator on empty list and several list */
    @Test
    public void testIterator()
    {
        int counter = 0;
        ListIterator<Integer> iter;
        for (iter = empty.listIterator(); iter.hasNext(); )
        {
            fail("Iterating empty list and found element");
        }
        counter = 0;
        for (iter = several.listIterator(); iter.hasNext(); iter.next())
            counter++;
        assertEquals("Iterator several count", counter, DIM);
    }

    /** Test add on empty list and several list */
    @Test
    public void testAddAtIndex()
    {
        try 
        {
            several.add(several.size()+1,0);
            fail("Should have generated an exception!");  
        }
        catch(IndexOutOfBoundsException e)
        {
            //  normal
        }
        try 
        {
            several.add(several.size(),null);
            fail("Should have generated an exception!");  
        }
        catch(NullPointerException e)
        {
            //  normal
        }
        empty.add(0,0); // Add into an empty list
        assertEquals("Check add 0 to beginning of empty", new Integer(0), empty.get(0));
        several.add(0,0); // Add between head and first node
        assertEquals("Check add 0 to beginning of several", new Integer(0), several.get(0));   
        several.add(several.size(),0); // Add between last node and tail
        assertEquals("Check add 0 to end of several", new Integer(0), several.get(several.size()-1));  
        several.add(1,0); // Add between frist node and second node
        assertEquals("Check add 0 to end of several", new Integer(0), several.get(1));   
    }

    /** Test add on empty list */
    @Test
    public void testAddAtEnd()
    {
        try 
        {
            several.add(null);
            fail("Should have generated an exception!");  
        }
        catch(NullPointerException e)
        {
            //  normal
        }
        assertTrue("Add 0 to empty, check empty has chenged", empty.add(0)); // Check if after adding returns true
        assertEquals("0 added to end of empty", new Integer(0), empty.get(empty.size()-1)); // Check adding into empty list
        several.add(0); // Adding into not empty list
        assertEquals("0 added to end of empty", new Integer(0), several.get(several.size()-1)); 
    }

    /** Test remove on several list */
    @Test
    public void testRemove()
    {
        try 
        {
            several.remove(several.size()+1);
            fail("Should have generated an exception!");  
        }
        catch(IndexOutOfBoundsException e)
        {
            //  normal
        }
        assertEquals("Check removed element is returned",new Integer(1), several.remove(0)); // Check removed element is returned
        assertEquals("Check the new size",DIM - 1, several.size()); // Check list resized
        assertEquals("Check the place of the removed index",new Integer(2), several.get(0)); // Check the index removed is now the node that was right to it 
    }

    /** Test clear on several list */
    @Test
    public void testClear()
    {
        several.clear();
        assertTrue("Check several is empty", several.isEmpty()); // Check if list is now empty
    }

    /** Test add on several list using listIterator */
    @Test
    public void testIteratorAdd()
    {
        ListIterator<Integer> iter=null;
        iter = several.listIterator();
        try 
        {
            iter.add(null);
            fail("Should have generated an exception!");  
        }
        catch(NullPointerException e)
        {
            //  normal
        }
        iter.add(0);
        assertEquals("Check 0 added to beginning of several", new Integer(0), several.get(0)); // Chech 0 added to beginning of several
    }

    /** Test hasNext on several list using listIterator*/
    @Test
    public void testHasPrev()
    {
        ListIterator<Integer> iter=null;
        iter = several.listIterator();
        iter.next(); // Move the iterator forward
        assertTrue("Check that several has a next nod", iter.hasPrevious()); // Check if there exist previous node
    }

    /** Test next on several list using listIterator*/
    @Test
    public void testNext()
    {
        ListIterator<Integer> iter=null;
        iter = several.listIterator();
        assertEquals("Check that 1 is the next value", new Integer(1), iter.next()); // Check if next element is 1
        assertEquals("Check that now 2 is the next value", new Integer(2), iter.next()); // Check if iterator moved forward
        // Move iterator to end of the list
        iter.next();
        iter.next();
        iter.next();
        try 
        {
            iter.next();
            fail("Should have generated an exception!");  
        }
        catch(NoSuchElementException e)
        {
            //  normal
        }
    }

    /** Test nextIndex on several list using listIterator*/
    @Test
    public void testNextIndex()
    {
        ListIterator<Integer> iter=null;
        iter = several.listIterator();
        assertEquals("Check that 0 is the next index", new Integer(0), (Integer)iter.nextIndex()); // Check the index of next node
        iter.next();
        assertEquals("Check that now 1 is the next index", new Integer(1), (Integer)iter.nextIndex()); // Check the index of next node after moving forward
    }

    @Test
    public void testPrevious()
    {
        ListIterator<Integer> iter=null;
        iter = empty.listIterator();
        // Check exception
        try 
        {
            iter.previous();
            fail("Should have generated an exception!");  
        }
        catch(NoSuchElementException e)
        {
            //  normal
        }
        iter = several.listIterator();
        // move the iterator forward 
        iter.next();
        iter.next();
        assertEquals("Check that 1 is the previous value", new Integer(2), iter.previous()); // Check if previous element is 1
        assertEquals("Check that 1 is the previous value", new Integer(1), iter.previous()); // Check if iterator moved backward
    }

    /** Test previousIndex on several list using listIterator*/
    @Test
    public void testPreviousIndex()
    {
        ListIterator<Integer> iter=null;
        iter = several.listIterator();
        assertEquals("Check that -1 is the previous index", new Integer(-1), (Integer)iter.previousIndex()); // Check the index of previous node
        iter.next();
        assertEquals("Check that now 0 is the previous index", new Integer(0), (Integer)iter.previousIndex()); // Check the index of previous node after moving
    }

    /** Test remove on several list using listIterator */
    @Test
    public void testIteratorRemove()
    {
        ListIterator<Integer> iter=null;
        iter = several.listIterator();
        try 
        {
            iter.remove();
            fail("Should have generated an exception!");  
        }
        catch(IllegalStateException e)
        {
            //  normal
        }
        // Move iterator forward and remove first node
        iter.next();
        iter.remove();
        assertEquals("Check that 1 has been removed and 2 is first number", new Integer(2), several.get(0)); // Check if first node is 2
        // Move iterator around and remove frist node
        iter.next();
        iter.previous();
        iter.remove();
        assertEquals("Check that 1 has been removed and 2 is first number", new Integer(3), several.get(0)); // Check if first node is 3
        iter.add(1);
        try // Cannot remove after adding an element
        {
            iter.remove();
            fail("Should have generated an exception!");  
        }
        catch(IllegalStateException e)
        {
            //  normal
        }
    }

    /** Test set on several list using listIterator */
    @Test
    public void testIteratorSet()
    {
        ListIterator<Integer> iter=null;
        iter = several.listIterator();
        try 
        {
            iter.set(3);
            fail("Should have generated an exception!");  
        }
        catch(IllegalStateException e)
        {
            //  normal
        }

        iter.next();
        iter.set(0);
        assertEquals("Check that 1 has been replaced by 0", new Integer(0), several.get(0)); // Check that first node is set to zero
        iter.next();
        iter.previous();
        iter.set(0);
        assertEquals("Check that 1 has been replaced by 0", new Integer(0), several.get(1)); // Check that second node is set to zero after moving iterator
        iter.add(1);
        try // Should fail because connot set after adding
        {
            iter.set(3);
            fail("Should have generated an exception!");  
        }
        catch(IllegalStateException e)
        {
            //  normal
        }
    }
}
