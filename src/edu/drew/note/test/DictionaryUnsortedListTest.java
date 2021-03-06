package edu.drew.note.test;

import static org.junit.Assert.*;
import junit.framework.TestCase;

import org.junit.Test;

import edu.drew.note.ArrayListCollection;
import edu.drew.note.DictionaryUnsortedList;
import edu.drew.note.Note;

public class DictionaryUnsortedListTest extends TestCase {
	private static final int SIZE = 100;
	private Note[] array = new Note[SIZE];
	private DictionaryUnsortedList notes = new DictionaryUnsortedList();
	
	@Override
	protected void setUp() {
		array = new Note[SIZE];
		for (int i = 0; i < SIZE; i++)
			array[i] = new Note();
	}

	private Note addOneElement() {
		notes = new DictionaryUnsortedList();
		Note n = new Note();
		notes.add(n);
		return n;
	}
	
	private int addManyElements() {
		notes = new DictionaryUnsortedList();
		for (int i = 0; i < SIZE; i++)
			notes.add(array[i]);
		return SIZE;
	}
	
	@Test
	public void testAddNullElement() {
		int n = notes.getSize();
		// no exceptions!
		notes.add(null);
		assertEquals(n, notes.getSize());
	}
	
	@Test
	public void testIsEmpty() {
		notes = new DictionaryUnsortedList();
		assertTrue(notes.isEmpty());
	}
	
	@Test
	public void testIsNotEmpty() {
		notes = new DictionaryUnsortedList();
		notes.add(new Note());
		assertFalse(notes.isEmpty());
	}
	
	@Test
	public void testSizeOne() {
		notes = new DictionaryUnsortedList();
		notes.add(new Note());
		assertEquals(1, notes.getSize());
	}
	
	@Test
	public void testSizeMany() {
		int size = addManyElements();
		assertEquals(size, notes.getSize());
	}
	
	@Test
	public void testAddOne() {
		Note n = addOneElement();
		assertEquals(1, notes.getSize());
		assertTrue(notes.contains(n));
	}
	
	@Test
	public void testAddMany() {
		int size = addManyElements();
		assertEquals(size, notes.getSize());
		for (int i = 0; i < size; i++) {
			assertTrue(notes.contains(array[i]));
			assertTrue(notes.contains(array[i].getID()));
		}
	}
	
	@Test
	public void testLookupOne() {
		Note n = addOneElement();
		assertEquals(n, notes.lookup(n.getID()));
		assertTrue(notes.contains(n));
	}
	
	@Test
	public void testLookupMany() {
		int size = addManyElements();
		assertEquals(size, notes.getSize());
		for (int i = 0; i < size; i++) {
			assertEquals(array[i], notes.lookup(array[i].getID()));
		}
	}
	
	@Test
	public void testRemoveOneNote() {
		Note n = addOneElement();
		notes.remove(n);
		assertFalse(notes.contains(n));
	}
	
	@Test
	public void testRemoveOneNoteByID() {
		Note n = addOneElement();
		notes.remove(n.getID());
		assertFalse(notes.contains(n.getID()));
	}
	
	@Test
	public void testRemoveEmpty() {
		notes = new DictionaryUnsortedList();
		Note n = new Note();
		// below should throw no exceptions!
		notes.remove(n.getID()); 
		notes.remove(n);
		notes.remove(null);
	}
	
	@Test
	public void testRemoveManyNotes() {
		int size = addManyElements();
		for (int i = 0; i < size; i++) {
			notes.remove(array[i]);
			assertFalse(notes.contains(array[i]));
		}
	}

	@Test
	public void testRemoveManyNotesByID() {
		int size = addManyElements();
		for (int i = 0; i < size; i++) {
			notes.remove(array[i].getID());
			assertFalse(notes.contains(array[i].getID()));
		}
	}
	
	@Test
	public void testToArrayEmpty() {
		notes = new DictionaryUnsortedList();
		Note[] a = notes.toArray();
		assertEquals(0, a.length);
	}
	
	@Test
	public void testToArrayOne() {
		Note n = addOneElement();
		Note[] a = notes.toArray();
		assertEquals(1, a.length);
		assertEquals(n, a[0]);
	}

	@Test
	public void testToArrayMany() {
		int size = addManyElements();
		Note[] a = notes.toArray();
		assertEquals(size, a.length);
		// since the input order is the sorted order, 
		// should be able to check all
		for (int i = 0; i < size; i++) {
			assertEquals(array[i], a[i]);
		}
	}

}
