package edu.iup.cosc310.util;

import java.util.NoSuchElementException;

public class HashTable<K, V> implements DictionaryI {
/**
 * Implementation of a hash table
 * @author Meghan Cowan
 *
 * @param <K>
 * @param <V>
 */
//Create entry object, containing a key and a value
	public class HashEntry<K, V> {

		private Object key;
		private Object value;

		public HashEntry(Object key, Object value) {
			this.key = key;
			this.value = value;
		}

		public Object getKey() {
			return key;
		}

		public Object getValue() {
			return value;
		}

	}
//Use array of entries to create a table
	private int noKeys = 0;
	private static final int TABLE_SIZE = 151;
	private HashEntry<K, V>[] table;
	
//Use null entry in deleted cases
	private final HashEntry<K, V> deleted = new HashEntry<K, V>(null, null);
/**
 * Construct a HashTable
 */
	public HashTable() {
		table = new HashEntry[TABLE_SIZE];
	}
/**
 * Perform hashing function to determine location in table
 * @param key
 * @return
 */
	public int hash(Object key) {
		return key.hashCode() % table.length;
	}
/**
 * Find the position of a key
 * @param key
 * @return index
 */
	private int find(Object key) {
		int index = hash(key);

		if (index < 0) {
			index += table.length;
		}

		while (table[index] != null) {
			if (table[index].getKey().equals(key)) {
				return index;
			}
			index++;
			if (index >= table.length) {
				index = 0;
			}
		}
		return index;
	}
/**
 * Put an object in the table or update the value of an existing object
 */
	public Object put(Object key, Object value) {
		int index = find(key);

		if (table[index] == null || table[index].equals(deleted)) {
			table[index] = new HashEntry(key, value);
			noKeys++;
		} else if (table[index].key.equals(key)) {
			table[index].value = value;
		} else {
			index++;
			put(key, value);
		}
		V oldVal = (V) table[index].value;
		table[index].value = value;
		return oldVal;
	}
/**
 * Get the value of a key
 */
	public Object get(Object key) {
		int index = find(key);
		if (table[index] != null) {
			return table[index].value;
		} else {
			return null;
		}
	}
/**
 * Remove entry by key
 */
	public Object remove(Object key) {
		int index = find(key);
		if (table[index].key.equals(key)) {
			Object oldVal = table[index].value;
			table[index] = deleted;
			noKeys--;
			return oldVal;
		} 
		return null;

	}
/**
 * ItermIterator 
 */
	public ItemIterator keys() {
		// TODO Auto-generated method stub
		return new HashIterator<K>();
	}
/**
 * Test if empty
 */
	public boolean isEmpty() {
		return noKeys == 0;
	}
/**
 * Find number of keys
 */
	public int noKeys() {
		return noKeys;
	}
/**
 * HashTable iterator
 * @author Meghan
 *
 * @param <E>
 */
	private class HashIterator<E> implements ItemIterator<E> {

		int index;

		public HashIterator() {
			super();
			this.index = 0;
		}

		public boolean hasMoreItems() {
			while (index < table.length && (table[index] == null || table[index] == deleted)) {
				index++;
			}
			
			return index < table.length ;
		}

		public E nextItem() {
			if (hasMoreItems() == false) {
				throw new NoSuchElementException();
			}
				return (E) table[index++].getKey();
		   }


	}

}
