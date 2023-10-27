
package edu.njit.cs114;
import java.io.IOException;
import java.util.*;
/**
 * Author: Kevin Aguilar
 * Date created: 1/11/2022
 */
public class AnagramFinderListImpl extends AbstractAnagramFinder {
    private List<WordArrPair> wordArrList = new ArrayList<>();
    private class WordArrPair implements Comparable<WordArrPair> {
        public final String word;
        public final char [] charArr;
        public WordArrPair(String word) {
            this.word = word;
            charArr = word.toCharArray();
            Arrays.sort(charArr);
        }
        public boolean isAnagram(WordArrPair wordArrPair) {
            /*
             * To be completed
             * Compare charArr already sorted (use Arrays.equals())
             */
        	if (Arrays.equals(this.charArr,wordArrPair.charArr))
        		return true;
            return false;
        }
        @Override
        public int compareTo(WordArrPair wordArrPair) {
            return this.word.compareTo(wordArrPair.word);
        }
    }
    @Override
    public void clear() {
        wordArrList.clear();
    }
    @Override
    public void addWord(String word) {
        /*
         * Create a word pair object and add it to list
         */
    	WordArrPair newPair = new WordArrPair(word);
    	wordArrList.add(newPair);
    }
    @Override
    public List<List<String>> getMostAnagrams() {
        Collections.sort(wordArrList);
        ArrayList<List<String>> mostAnagramsList = new ArrayList<>();
        ArrayList<List<String>> AnagramsList = new ArrayList<>();
        /**
         * To be completed
         * Repeatedly do this:
         * (a)Each wordPair in the list is compared to others to identify all its anagrams;
         * (b) add the anagram words to the same group;
         *      if there are no anagrams, single word forms a group
         * (c) remove these words from wordArrList
         */
       while(!wordArrList.isEmpty()) {
    	   ArrayList<String> stringArray = new ArrayList<String>();
        	
    	   for(WordArrPair wordArr2 : wordArrList) {
        		
        		if(wordArrList.get(0).isAnagram(wordArr2)) {
        			stringArray.add(wordArr2.word);
        		}       			
        		
        	}
    	   AnagramsList.add(stringArray);
    	   wordArrList.remove(0);   	   
       }
    	   
       
       int maxAnagrams = 0;

       for(List<String> stringArr : AnagramsList)
       {
           if(stringArr.size() > maxAnagrams)
           {
               maxAnagrams = stringArr.size();
               continue;
           }
       }

       for(List<String> stringArr : AnagramsList)
       {
           if(stringArr.size() == maxAnagrams)
           {
               mostAnagramsList.add(stringArr);
               continue;
           }
       }
     
       
        return mostAnagramsList;
    }
    public static void main(String [] args) {
        AnagramFinderListImpl finder = new AnagramFinderListImpl();
        finder.clear();
        long startTime = System.nanoTime();
        int nWords=0;
        try {
            nWords = finder.processDictionary("words.txt");
        } catch (IOException e) {
            e.printStackTrace ();
        }
        List<List<String>> anagramGroups = finder.getMostAnagrams();
        long estimatedTime = System.nanoTime () - startTime ;
        double seconds = ((double) estimatedTime /1000000000) ;
        System.out.println("Number of words : " + nWords);
        System.out.println("Number of groups of words with maximum anagrams : "
                + anagramGroups.size());
        if (!anagramGroups.isEmpty()) {
            System.out.println("Length of list of max anagrams : " + anagramGroups.get(0).size());
            for (List<String> anagramGroup : anagramGroups) {
                System.out.println("Anagram Group : "+ anagramGroup);
            }
        }
        System.out.println ("Time (seconds): " + seconds);
    }
}
