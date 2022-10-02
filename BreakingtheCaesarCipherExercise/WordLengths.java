
/**
 * Write a description of WordLengths here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class WordLengths
{
    public void countWordLengths(FileResource resource, int[] counts)
    {
        for(String word : resource.words())
        {
            int wordlength = word.length();
            
            if (!Character.isLetter(word.charAt(0)) && !Character.isLetter(word.charAt(word.length() - 1)))
            {
                wordlength = word.length() - 2;
            }
            else if (!Character.isLetter(word.charAt(word.length() - 1)))
            {
                wordlength = word.length() - 1;
            }
            
            if (wordlength >= counts.length) {                 
                wordlength = counts.length - 1;       
            }      
            if (wordlength > 0 ) {            
                counts[wordlength] ++;        
            }
        }
    }
    void testCountWordLengths(){
        int[] counts = new int[31];
        
        FileResource resource = new FileResource();
        countWordLengths(resource,counts);
        
        for(int k=0; k < counts.length; k++){
            System.out.println(counts[k] + " words of length " + k);
        }
    }
}

