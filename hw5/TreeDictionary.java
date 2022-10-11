package edu.pitt.cs.as5;

import java.util.*;
import java.io.*;

/**
 * *****************************************************************************
 * Assignment 5 CS0445
 * *****************************************************************************
 * Boggle
 * *****************************************************************************
 * <p>
 * This class stores a dictionary containing words that can be used in a Boggle
 * game. It is a trie tree (http://en.wikipedia.org/wiki/Trie).
 *
 * @author Constantinos Costa (costa.c@cs.pitt.edu)
 * @date Thursday, November 21, 2019
 *****************************************************************************/
class TreeDictionary implements DictionaryInterface {
    Node root = new Node();

    private class Node {
        Node[] links = new Node[26];
        String word;
    }

    /**
     * Create the TreeDictionary from the file
     *
     * @param fname the file name to be loaded
     * @use addWord method
     */
    public TreeDictionary(String fname) throws Exception {
        Scanner in = new Scanner(new File(fname));
        // TODO: Fill the code add all the words using an iterator on the 'in' check BoggleDictionary
        while(in.hasNext()){
          String word = in.next();
          word = word.toUpperCase();
          addWord(word);
        }
    }

    /**
     * This method add a word in the dictionary specified by
     * buildDictionary.
     *
     * @param word the word to be added
     */
    @Override
    public void addWord(String word) {
        // TODO: Fill the code - Iterate over the characters in the word and create a trie to mark the word set the Node.word=word
        // TODO: Hint you can calculate the position using the ASCII (subtract 'A' )

        int index;
        Node current = root;
        for(int i=0; i<word.length(); i++){
          index = word.charAt(i) - 'A';
          if(current.links[index]==null){
            current.links[index] = new Node();
          }
          current = current.links[index];
        }
        current.word = word;
    }

    /**
     * This method checks if a word is in the dictionary specified by
     * buildDictionary using the tree traversal similar with what we saw in the class.
     *
     * @param word the word to be checked
     * @return true when the word is in the dictionary, false otherwise
     * @see BoggleGUI
     */
    @Override
    public boolean checkWord(String word) {

        // TODO: Fill the code - Remember the node's word is null if it's not a word

      Node current = root;

        for(int i=0; i<word.length(); i++){
          int index = word.charAt(i)-'A';

        if(current.links[index]!=null){
          current= current.links[index];
        }else{
          return false;
      }
    }
    return  word.equals(current.word);

  }


    /**
     * This method checks if a word in the tree starts
     * with the specified word
     *
     * @param word the word to be checked
     * @return true when a word match the beginning only of a
     * word in the dictionary, false otherwise
     * @see BoggleGUI
     */
    @Override
    public boolean checkWordBeginning(String word) {
        // TODO: Fill the code - Check the children of the trie
        // TODO: Hint you can calculate the position using the ASCII (subtract 'A' )
        Node current = root;


        for(int i=0; i<word.length(); i++){
            int index = word.charAt(i)-'A';
            if(current.links[index]==null){
              return false;
            }else{
              current = current.links[index];
            }
        }
        for(int i=0;i < (current.links).length; i++){
            if(current.links[i]!=null){
              return true;
            }
        }
        return false;
    }



    public static void main(String[] args) throws Exception {
        TreeDictionary td = new TreeDictionary(args[0]);

        @SuppressWarnings("resource")
        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.print("Enter word: ");
            String word = in.next();
            word = word.toUpperCase();
            if (td.checkWord(word))
                System.out.println("Word found");
                // for(int i=1; i<word.length()+1; i++){
          			// 	String sub = word.substring(0,i);
          			// 	System.out.println("Substring: " + sub + ", checkedWordBeginning: " + td.checkWordBeginning(sub));
          			// }
        }

    }

}
