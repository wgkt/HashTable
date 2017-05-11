package edu.iup.cosc310.util;

import java.util.NoSuchElementException;

public class HashTable<K, V> implements DictionaryI {

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

	private int noKeys = 0;
	private static final int TABLE_SIZE = 51;
	private HashEntry<K, V>[] table;
	private HashEntry<K, V> deleted;

	public HashTable() {
		table = new HashEntry[TABLE_SIZE];

	}

	public int hash(Object key) {
		return key.hashCode();
	}

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

	public Object get(Object key) {
		int index = find(key);
		if (table[index] != null) {
			return table[index].value;
		} else {
			return null;
		}
	}

	public Object remove(Object key) {
		int index = find(key);
		
			if (table[index].key.equals(key)) {
				Object temp = table[index].value;
				table[index].key = deleted;
				noKeys--;
				return temp;
			} else {
				return null;
			}

	}

	public ItemIterator<K> keys() {
		return new HashIterator<K>();
	}

	public boolean isEmpty() {
		return noKeys == 0;
	}

	public int noKeys() {
		return noKeys;
	}

	private class HashIterator<E> implements ItemIterator<E> {

		int index;

		public HashIterator() {
			super();
			this.index = 0;
		}

		public boolean hasMoreItems() {
			while (index < table.length
					&& (table[index] == null || table[index] == deleted)) {
				index++;
			}

			return index < table.length;
		}

		public E nextItem() {
			if (hasMoreItems() == false) {
				throw new NoSuchElementException();
			}
			return (E) table[index++].getKey();
		}

	}

}
