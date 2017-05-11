package edu.iup.cosc310.util;

import java.util.Iterator;

public class HashTableTest {

	public static void main(String[] args) {
		
		HashTable table = new HashTable();
		
		table.put(1, 0);
		table.put(2, 1);
		table.put(10, 2);
		table.remove(1);
		
		ItemIterator iter = table.keys();
		while (iter.hasMoreItems()) 
			 {
			System.out.print(iter.nextItem() + " "); 
		}
		System.out.println();
		
		
		
	}

}
