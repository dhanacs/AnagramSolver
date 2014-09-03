// Code to get all the anagrams of a given word.

import java.io.File;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

public class AnagramSolver
{
  private String words[];
  private HashMap<String, Integer> wordMap; // Word to hash.

  private Hashtable<Integer, String> source; // Hash to word.
  private HashMap<Integer, String>  integerMap;

  // Constructor.
  public AnagramsSolver()
  {
    // Initialize.
    wordMap = new HashMap<String, Integer>();
    source = new Hashtable<Integer,String>(); // Hash to word.
    integerMap = new HashMap(source);

    // Read the words from file.
    try
    {
      Path filePath = new File("Words.txt").toPath();
      Charset charset = Charset.defaultCharset();
      List<String> stringList = Files.readAllLines(filePath, charset);
      words = stringList.toArray(new String[]{});
    }
    catch(Exception e)
    {
      System.out.println("Error reading words from the dictionary.");
    }
  }

  // Sort the characters of a given word.
  private String sortWord(String word)
  {
    char[] characters = word.toCharArray();
    Arrays.sort(characters);
    return new String(characters);
  }

  // Hash the dictionary words.
  public void hashWords()
  {
    // Populate the hash map for all words.
    for(int i = 0; i < words.length; ++i)
    {
      String originalWord = words[i];
      String sortedWord = sortWord(words[i]);
      Integer hashCode = sortedWord.hashCode();
      wordMap.put(words[i], hashCode);
      integerMap.put(hashCode, originalWord);
    }
  }

  // Print all the anagrams for the given word.
  public void printAnagrams(String scrambledWord)
  {
    String word;
    Integer hashCode = sortWord(scrambledWord).hashCode();

    for(int i = 0; i < words.length; ++i)
    {
      if(sortWord(words[i]).hashCode() == hashCode)
      {
        System.out.println(words[i]);
      }
    }
  }
}
