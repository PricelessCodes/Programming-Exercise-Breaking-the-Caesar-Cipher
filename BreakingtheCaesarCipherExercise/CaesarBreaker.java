
/**
 * Write a description of CaesarBreaker here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class CaesarBreaker {
    
    public int[] countLetters(String s)
    {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int[] alphabetCount = new int[26];
        
        for (int i = 0; i < s.length(); i++)
        {
            char ch = Character.toUpperCase(s.charAt(i));
            int idx = alphabet.indexOf(ch);
            
            if (idx != -1)
            {
                alphabetCount[idx]++;
            }
        }
        
        return alphabetCount;
    }
    
    public int maxIndex(int[] alphabetCount)
    {
        int max = -1;
        int index = -1;
        
        for (int i = 0; i < alphabetCount.length; i++)
        {
            if (alphabetCount[i] > max)
            {
                max = alphabetCount[i];
                index = i;
            }
        }
        
        return index;
    }
    
    public String decrypt(String encrypted, int key)
    {
        CaesarCipher cc = new CaesarCipher();
        String message = cc.encrypt(encrypted, 26 - key);
        
        return message;
    }
    
    public String halfOfString(String message, int start)
    {
        String half = "";
        
        for (int i = start; i < message.length(); i+=2)
        {
            half += message.charAt(i);
        }
        
        return half;
    }
    
    public int getKey(String s)
    {
        int[] alphabetCount = countLetters(s);
        int index = maxIndex(alphabetCount);
        
        int dkey = index - 4;
        if (index < 4)
        {
            dkey = 26 - (4-index);
        }
        
        return dkey;
    }
    
    public String decryptTwoKeys(String encrypted)
    {
        String encreptedHalf1 = halfOfString(encrypted, 0);
        String encreptedHalf2 = halfOfString(encrypted, 1);
        
        int key1 = getKey(encreptedHalf1);
        int key2 = getKey(encreptedHalf2);
        System.out.println("Key 1: " + key1 + " - Key 2: " + key2);
        
        String decreptedHalf1 = decrypt(encreptedHalf1, key1);
        String decreptedHalf2 = decrypt(encreptedHalf2, key2);
        String decrypted = "";
        for (int i = 0; i < decreptedHalf1.length(); i++)
        {
            decrypted += decreptedHalf1.charAt(i);
            if (i < decreptedHalf2.length())
            {
                decrypted += decreptedHalf2.charAt(i);
            }
        }
        return decrypted;
    }
    
    public void testDecrypt()
    {
        //System.out.println(halfOfString("man", 1));
        //System.out.println(getKey("aaeeeeeaaaaeeeeawwaaaaaaeeaaeeeaddaaaaeeexaxbabeeeaaaa"));
        FileResource resource = new FileResource();
        System.out.println(decryptTwoKeys(resource.asString()));
    }
}
