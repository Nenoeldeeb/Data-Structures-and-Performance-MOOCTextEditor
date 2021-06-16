/**
 *
 */
package textgen;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * @author UC San Diego MOOC team
 *
 */
public class MyLinkedListTester {

	private static final int LONG_LIST_LENGTH = 10;

	MyLinkedList<String> shortList;
	MyLinkedList<Integer> emptyList;
	MyLinkedList<Integer> longerList;
	MyLinkedList<Integer> list1;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp () throws Exception {
		// Feel free to use these lists, or add your own
		shortList = new MyLinkedList<String> ();
		shortList.add ("A");
		shortList.add ("B");
		emptyList = new MyLinkedList<Integer> ();
		longerList = new MyLinkedList<Integer> ();
		for (int i = 0; i < LONG_LIST_LENGTH; i++) {
			longerList.add (i);
		}
		list1 = new MyLinkedList<Integer> ();
		list1.add (65);
		list1.add (21);
		list1.add (42);

	}


	/** Test if the get method is working correctly.
	 */
	/*You should not need to add much to this method.
	 * We provide it as an example of a thorough test. */
	@Test
	public void testGet () {
		//test empty list, get should throw an exception
		try {
			emptyList.get (0);
			fail ("Check out of bounds");
		} catch (IndexOutOfBoundsException e) {

		}

		// test short list, first contents, then out of bounds
		assertEquals ("Check first", "A", shortList.get (0));
		assertEquals ("Check second", "B", shortList.get (1));

		try {
			shortList.get (-1);
			fail ("Check out of bounds");
		} catch (IndexOutOfBoundsException e) {

		}
		try {
			shortList.get (2);
			fail ("Check out of bounds");
		} catch (IndexOutOfBoundsException e) {

		}
		// test longer list contents
		for (int i = 0; i < LONG_LIST_LENGTH; i++) {
			assertEquals ("Check " + i + " element", (Integer) i, longerList.get (i));
		}

		// test off the end of the longer array
		try {
			longerList.get (-1);
			fail ("Check out of bounds");
		} catch (IndexOutOfBoundsException e) {

		}
		try {
			longerList.get (LONG_LIST_LENGTH);
			fail ("Check out of bounds");
		} catch (IndexOutOfBoundsException e) {
		}

	}


	/** Test removing an element from the list.
	 * We've included the example from the concept challenge.
	 * You will want to add more tests.  */
	@Test
	public void testRemove () {
		int a = list1.remove (0);
		assertEquals ("Remove: check a is correct ", 65, a);
		assertEquals ("Remove: check element 0 is correct ", (Integer) 21, list1.get (0));
		assertEquals ("Remove: check size is correct ", 2, list1.size ());

		try {
			emptyList.remove (0);
			fail ("Check index validation.");
		} catch (IndexOutOfBoundsException e) {

		}
		try {
			shortList.remove (-1);
			fail ("Check index validation.");
		} catch (IndexOutOfBoundsException e) {

		}
		try {
			longerList.remove (10);
			fail ("Check index validation.");
		} catch (IndexOutOfBoundsException e) {

		}
		int b = longerList.remove (5);
		assertEquals ("Remove: check b is correct ", 5, b);
		assertEquals ("Remove: check element 5 is correct ", (Integer) 6, longerList.get (5));
		assertEquals ("Remove: check size is correct ", 9, longerList.size ());
		int c = longerList.remove (8);
		assertEquals ("Remove: check c is correct ", 9, c);
		assertEquals ("Remove: check element 7 is correct ", (Integer) 8, longerList.get (7));
		assertEquals ("Remove: check size is correct ", 8, longerList.size ());
	}

	/** Test adding an element into the end of the list, specifically
	 *  public boolean add(E element)
	 * */
	@Test
	public void testAddEnd () {
		try {
			shortList.add (null);
			fail ("Check the element if null or not");
		} catch (NullPointerException e) {

		}
		shortList.add ("N");
		assertEquals ("Check third", "N", shortList.get (2));
		emptyList.add (5);
		emptyList.add (8);
		assertEquals ("Check second", (Integer) 8, emptyList.get (1));
		assertEquals ("Check empty", (Integer) 5, emptyList.get (0));
	}


	/** Test the size of the list */
	@Test
	public void testSize () {
		assertEquals ("Check short size", 2, shortList.size ());
		assertEquals ("Check empty size", 0, emptyList.size ());
		assertEquals ("Check longer size", 10, longerList.size ());
	}


	/** Test adding an element into the list at a specified index,
	 * specifically:
	 * public void add(int index, E element)
	 * */
	@Test
	public void testAddAtIndex () {
		try {
			shortList.add (1, null);
			fail ("Check null validation");
		} catch (NullPointerException e) {

		}
		try {
			emptyList.add (1, 7);
			fail ("Check index validation");
		} catch (IndexOutOfBoundsException e) {

		}
		try {
			longerList.add (-1, 15);
			fail ("Check index validation");
		} catch (IndexOutOfBoundsException e) {

		}
		emptyList.add (0, 8);
		emptyList.add (1, 15);
		shortList.add (2, "N");
		longerList.add (5, 20);
		longerList.add (0, 888);
		assertEquals ("Check longer first add", (Integer) 888, longerList.get (0));
		assertEquals ("Check longer sixth add", (Integer) 20, longerList.get (6));
		assertEquals ("Check empty first add", (Integer) 8, emptyList.get (0));
		assertEquals ("Check empty second add", (Integer) 15, emptyList.get (1));
		assertEquals ("Check short last add", "N", shortList.get (2));
	}

	/** Test setting an element in the list */
	@Test
	public void testSet () {
		try {
			list1.set (0, null);
			fail ("Check null validation.");
		} catch (NullPointerException e) {

		}
		try {
			emptyList.set (0, 15);
			fail ("Check index validation.");
		} catch (IndexOutOfBoundsException e) {

		}
		try {
			list1.set (-1, 15);
			fail ("Check index validation.");
		} catch (IndexOutOfBoundsException e) {

		}
		try {
			longerList.set (10, 10);
			fail ("Check index validation.");
		} catch (IndexOutOfBoundsException e) {

		}
		assertEquals ("Check short index 0", "A", shortList.set (0, "S"));
		assertEquals ("Check short index 0", "S", shortList.get (0));
		longerList.set (5, 15);
		longerList.set (9, 19);
		longerList.set (8, 18);
		assertEquals ("Check longer index 5", (Integer) 15, longerList.get (5));
		assertEquals ("Check longer index 9", (Integer) 19, longerList.get (9));
		assertEquals ("Check longer index 8", (Integer) 18, longerList.get (8));
	}


	// TODO: Optionally add more test methods.

}
