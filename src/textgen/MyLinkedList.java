package textgen;

import java.util.AbstractList;


/**
 * A class that implements a doubly linked list
 *
 * @param <E> The type of the elements stored in the list
 * @author UC San Diego Intermediate Programming MOOC team
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	/**
	 * Create a new empty LinkedList
	 */
	public MyLinkedList () {
		super ();
		size = 0;
		tail = new LLNode<> (null);
		head = new LLNode<> (null);
		head.next = tail;
		tail.prev = head;
	}

	/**
	 * Appends an element to the end of the list
	 *
	 * @param element The element to add
	 */
	public boolean add (E element) {
		if (element == null) {
			throw new NullPointerException ("You should input a valid element.\nNull doesn't allowed.");
		} else {
			add (size, element);
			return true;
		}
	}

	/**
	 * Get the element at position index
	 *
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E get (int index) {
		if (index < 0 || index >= size) throw new IndexOutOfBoundsException ("Incorrect element index.");
		LLNode<E> temp = head.next;
		for (int i = 0; i < size; i++) {
			if (index == i) return temp.data;
			temp = temp.next;
		}
		return null;
	}

	/**
	 * Add an element to the list at the specified index
	 * //* @param The index where the element should be added
	 *
	 * @param element The element to add
	 */
	public void add (int index, E element) {
		LLNode<E> temp = head.next;
		LLNode<E> newElement = new LLNode<> (element);
		if (element == null) throw new NullPointerException ("You should input valid element.\nNull doesn't allowed.");
		if (index < 0 || index > size) throw new IndexOutOfBoundsException ("Incorrect index.");
		if (size > 0 && size - index < index) {
			temp = tail;
			if (index < size) {
				for (int i = size; i > index; i--) {
					temp = temp.prev;
				}
			}
		}
		if (size > 0 && index > 0 && size - index >= index) {
			for (int i = 0; i < index; i++) {
				temp = temp.next;
			}
		}
		newElement.next = temp;
		newElement.prev = temp.prev;
		temp.prev.next = newElement;
		temp.prev = newElement;
		size++;
	}


	/**
	 * Return the size of the list
	 */
	public int size () {
		return size;
	}

	/**
	 * Remove a node at the specified index and return its data element.
	 *
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 */
	public E remove (int index) {
		LLNode<E> removed = head.next;
		LLNode<E> temp = head.next;
		if (index < 0 || index >= size) throw new IndexOutOfBoundsException ("Incorrect element index.");
		if (size != 0) {
			if (size - index < index) {
				temp = tail;
				removed = tail.prev;
				for (int i = size - 1; i >= 0; i--) {
					if (i == index) {
						break;
					}
					removed = removed.prev;
					temp = temp.prev;
				}
			} else {
				for (int i = 0; i < size; i++) {
					temp = temp.next;
					if (i == index) {
						break;
					}
					removed = removed.next;
				}
			}
			removed.prev.next = temp;
			temp.prev = removed.prev;
			removed.next = null;
			removed.prev = null;
			size--;
			return removed.data;
		}
		return null;
	}

	/**
	 * Set an index position in the list to a new element
	 *
	 * @param index   The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set (int index, E element) {
		LLNode<E> temp = head.next;
		if (element == null) throw new NullPointerException ("You should input valid element.\nNull doesn't allowed.");
		if (index < 0 || (index >= size || size == 0)) throw new IndexOutOfBoundsException ("Incorrect index.");
		if (size - index < index) {
			temp = tail.prev;
			if (index < size - 1) {
				for (int i = size - 1; i > index; i--) {
					temp = temp.prev;
				}
			}
		}
		if (index > 0 && size - index >= index) {
			for (int i = 0; i < index; i++) {
				temp = temp.next;
			}
		}
		if (true) {
			E item = temp.data;
			temp.data = element;
			return item;
		}
		return null;
	}

	@Override
	public String toString () {
		StringBuilder sb = new StringBuilder ("[ ");
		for (int i = 0; i < size; i++) {
			sb.append (this.get (i)).append (" , ");
		}
		return sb.append (" ]").toString ();
	}

}

class LLNode<E> {
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	// TODO: Add any other methods you think are useful here
	// E.g. you might want to add another constructor

	public LLNode (E e) {
		this.data = e;
		this.prev = null;
		this.next = null;
	}

	@Override
	public String toString () {
		return data.toString ();
	}

}
