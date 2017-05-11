package edu.iup.cosc310.wordhash;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import edu.iup.cosc310.util.HashTable;
import edu.iup.cosc310.util.ItemIterator;

public class WordHash {
	public static <K> void main(String[] args) throws FileNotFoundException {
		if (args.length != 1) {
			System.out.println("Please enter args[0]");
			return;
		}
		int lineNo = 0;
		int pageNo = 0;

		HashTable table = new HashTable();
		Scanner in = new Scanner(new File(args[0]));
		in.useDelimiter("\\W");

		
		

		while (in.hasNext()) {
			
			while (in.hasNext() && lineNo < 40) {
			
			String word = in.next();
			Integer count = (Integer) table.get(word);
			if (count == null) {
				table.put(word, 1);
			} else {
				table.put(word, count + 1);
			}
			
		}

		}

		ItemIterator<String> iter = table.keys();
		while (iter.hasMoreItems()) {
			K key = (K) iter.nextItem();
			System.out.println(key + ":" + table.get(key));
		}

	}

}
