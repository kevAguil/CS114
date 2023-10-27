package edu.njit.cs114;
import java.io.IOException;
import java.util.*;

/**
 * Author: Kevin Aguilar
 * Date created: 10/30/2022
 */
public class AnagramFinderHashTableImpl extends AbstractAnagramFinder {

    private static final int [] primes = new int []
            {2 , 3 , 5 , 7 , 11 , 13 , 17 , 19 ,
            23 , 29 , 31 , 37 , 41 , 43 , 47 ,
            53 , 59 , 61 , 67 , 71 , 73 , 79 ,
            83 , 89 , 97 , 101};

    private Map<Character,Integer> letterMap;

    /**
     * To be completed: Initialize anagramTable
     */
    private Map<Long, ArrayList<String>> anagramTable = new HashMap<>();


    private void buildLetterMap() {
        letterMap = new HashMap<>();
        for(int i = 0; i < primes.length; i++)
        {
            char letter = (char) ((int) 'a'  +  i);
            letterMap.put(letter, primes[i]);
        }
    }

    public AnagramFinderHashTableImpl() {
        buildLetterMap();
    }

    /**
     * Finds hash code for a word using prime number factors
     * @param word
     * @return
     */
    public Long myHashCode(String word) {
        Long product  = 1L;
        for(int i = 0; i < word.length(); i++)
        {
            product = product *letterMap.get(word.charAt(i));
        }
        return product;
    }

    /**
     * Add the word to the anagram table using hash code
     * @param word
     */
    @Override
    public void addWord(String word) {
        Long hashCode = myHashCode(word);
        ArrayList<String> wordList = anagramTable.get(hashCode);
        if(wordList == null)
        {
            wordList = new ArrayList<>();
            anagramTable.put(hashCode, wordList);
        }
        if(!wordList.contains(word))
        {
            wordList.add(word);
        }
    }

    @Override
    public void clear() {
        anagramTable.clear();
    }


    /**
     * Return list of groups of anagram words which have most anagrams
     * @return
     * @throws Exception
     */
    @Override
    public List<List<String>> getMostAnagrams() {
        ArrayList<List<String>> mostAnagramsList = new ArrayList<>();

        int maxAnagrams = 0;

        for(ArrayList<String> anagrams : anagramTable.values())
        {
            if(anagrams.size() > maxAnagrams)
            {
                maxAnagrams = anagrams.size();
                continue;
            }
        }

        for(ArrayList<String> anagrams : anagramTable.values())
        {
            if(anagrams.size() == maxAnagrams)
            {
                mostAnagramsList.add(anagrams);
                continue;
            }
        }

        return mostAnagramsList;
    }

    public static void main(String [] args) {
        AnagramFinderHashTableImpl finder = new AnagramFinderHashTableImpl();
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