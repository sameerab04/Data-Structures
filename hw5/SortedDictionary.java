package edu.pitt.cs.as5;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * *****************************************************************************
 * Assignment 5 CS0445
 * *****************************************************************************
 * Boggle
 * *****************************************************************************
 * <p>
 * This class stores a dictionary containing words that can be used in a Boggle
 * game.
 *
 * @author Constantinos Costa (costa.c@cs.pitt.edu)
 * @date Thursday, November 21, 2019
 *****************************************************************************/
public class SortedDictionary implements DictionaryInterface {
	/**
	 * Method to get dictionary from file; dictionary is sorted and only has valid
	 * length words.
	 *
	 * @param wordList A list of legal words in Boggle
	 * @see BoggleGUI
	 */
	private ArrayList<String> dictionary; // Stores dictionary

	SortedDictionary() {
		dictionary = new ArrayList<String>();
	}

	SortedDictionary(String fname) throws Exception {
		dictionary = new ArrayList<String>();
		this.buildDictionary(fname);
	}

	/**
	 * Create the SortedDictionary from the file
	 *
	 * @param fname the file name to be loaded
	 * @use quickSort method to sort the end result
	 */
	public void buildDictionary(String fname) throws Exception {
		Scanner in = new Scanner(new File(fname));

		// TODO: Fill the code add all the words using an iterator on the 'in' check
		// BoggleDictionary
		while (in.hasNext()) {
			String word = in.next();
			word = word.toUpperCase();
			addWord(word);
		}

		// TODO: Fill the code(Hint: Sort the List - Call the quickSort)
		quickSort(dictionary, 0, dictionary.size()-1);
	}

	/**
	 * This method checks if a word is in the dictionary specified by
	 * buildDictionary using binary search.
	 *
	 * @param wordToCheck the word to be checked
	 * @return true when the word is in the dictionary, false otherwise
	 * @see BoggleGUI
	 */
	@Override
	public boolean checkWord(String wordToCheck) {

		return BinarySearch(dictionary, 0, dictionary.size() - 1, wordToCheck);
	}

	@Override
	public void addWord(String word) {
		dictionary.add(word);
		//quickSort(dictionary, 0, dictionary.size() - 1);
	}

	/**
	 * This method checks if a word in the tree starts with the specified word
	 *
	 * @param word the word to be checked
	 * @return true when a word match the beginning only of a word in the
	 *         dictionary, false otherwise
	 * @see BoggleGUI
	 */
	@Override
	public boolean checkWordBeginning(String word) {
		// TODO: Fill the code - You should use the binarySearch method that returns the
		// position
		boolean startsWith = false;
		int index = BinarySearch(dictionary, word);
		if(index>=0){
			String start = dictionary.get(index);
			if(start.startsWith(word)){
				startsWith =true;
		}else if(index!=0 && dictionary.get(index+1).startsWith(word)){
			startsWith = true;
		}else if(index!=dictionary.size()-1 && dictionary.get(index-1).startsWith(word)){
			startsWith = true;
		}
	}
		return startsWith;
	}

	/**
	 * This method sorts a ArrayList of strings using Quicksort.
	 *
	 * @param dat  the ArrayList to be sorted low the leftmost boundary of the
	 *             ArrayList to be sorted high the rightmost boundary of the
	 *             ArrayList to be sorted
	 * @param low
	 * @param high
	 * @return void
	 */

	void quickSort(ArrayList<String> dat, int low, int high) {
		// TODO: Fill the necessary code - Check the slides in the course website
		String pivot;
		int pivotIndex;
	//	System.out.println("QuickSorting");
		if (low >= high) {
			return;
		}
		pivotIndex = (low + high) / 2;
		//pivot = dat.get(pivotIndex);
		swap(dat, pivotIndex, high);
		pivotIndex = partition(dat, low, high);

		if (dat.get(high).compareTo(dat.get(pivotIndex)) < 0) {
			swap(dat, pivotIndex, high);
		}
		quickSort(dat, low, pivotIndex - 1);
		quickSort(dat, pivotIndex + 1, high);
	//	System.out.println("Done quick sort");
	}

	/**
	 * Quicksort helper method that partitions a ArrayList into two halves based on
	 * a "pivot." All the elements less than the pivot are placed in the left half,
	 * all the rest are in the right half.
	 *
	 * @param data The ArrayList being sorted left The leftmost boundary right The
	 *             rightmost boundary
	 * @return the location of the pivot in the ArrayList
	 */
	public int partition(ArrayList<String> data, int left, int right) {
		// TODO: Fill the code
		// left and right represent the boundaries of elements we
		// haven't partitioned Our goal is to get to all of them
		// moving partitioned elements to either side as necessary.
		String pivot;
		int pivotIndex;
		//pivotIndex = (left + right) / 2;
		pivot = data.get(right);
		while (left < right) {

			while (data.get(left).compareTo(pivot) < 0 && left < right){
				left++;
			}
			while (pivot.compareTo(data.get(right)) <= 0 && left < right)
				right--;

			if (left == right)
				break;
			if (data.get(left).compareTo(pivot) >= 0)
				swap(data, left, right);
		}

		return left; // this is only a placeholder
	}

	/**
	 * This method swaps two elements in a ArrayList (regardless of their type).
	 *
	 * @param data The ArrayList i The position of one element j The position of the
	 *             other element
	 * @return void
	 */
	public void swap(ArrayList<String> data, int i, int j) {
		// TODO: Fill the code
		String temp = data.get(i);
		data.set(i, data.get(j));
		data.set(j, temp);
	}

	/**
	 * This method performs a recursive binary search on a ArrayList. It returns
	 * true if the search item is in the ArrayList and false otherwise.
	 *
	 * @param s The ArrayList to be searched front The leftmost boundary of the
	 *          ArrayList that we're still interested in back The rightmost boundary
	 *          item The thing we're searching for
	 * @return true when the item is in the ArrayList, false otherwise
	 */
	 boolean BinarySearch(ArrayList<String> s, int front, int back, String item) {
		// TODO: Fill the code - Remember the compareTo
		if (back < front) {
			return false;
		}
		int mid = (front + back) / 2;
		int result = item.compareTo(s.get(mid));
		if (result == 0) {
			return true;
		} else if (result < 0) {
			return BinarySearch(s, front, mid - 1, item);
		} else {
			return BinarySearch(s, mid + 1, back, item);
		}
	}

	/**
	 * This method performs a binary search on a ArrayList. It returns the index an
	 * item might be inserted or the item itself if it was found.
	 *
	 * @param s The ArrayList to be searched front The leftmost boundary of the
	 *          ArrayList that we're still interested in back The rightmost boundary
	 *          item The thing we're searching for
	 * @return the index of the desired location
	 */
	static int BinarySearch(ArrayList<String> s, String item) {
		 int mid, front = 0, end = s.size() - 1;

		while(front <= end){
				mid = (front+end)/2;
				if(item.compareTo(s.get(mid))==0){
					return mid;
				}else if(item.compareTo(s.get(mid))<0){
					end = mid -1;
				}else{
					front = mid + 1;
				}
		}
		return front;
	}
	// public void testSort(){
	// 	for(int i=0; i<dictionary.size()-1; i++){
	// 		String first = dictionary.get(i);
	// 		String second = dictionary.get(i+1);
	// 		if(first.compareTo(second)>0){
	// 			System.out.println("Wrong on index " + i);
	// 		}
	// 	}
	// 	System.out.println("Correctly sorted if no previous errors");
	// }

	public static void main(String[] args) throws Exception {
		SortedDictionary sd = new SortedDictionary();
		sd.buildDictionary(args[0]);

		sd.testSort();

		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		while (true) {
			System.out.print("Enter word: ");
			String word = in.next();
			word = word.toUpperCase();
			if (sd.checkWord(word))
				System.out.println("Word found");
			// for(int i=1; i<word.length()+1; i++){
			// 	String sub = word.substring(0,i);
			// 	System.out.println("Substring: " + sub + ", checkedWordBeginning: " + sd.checkWordBeginning(sub));
			// }
		}
	}
}
