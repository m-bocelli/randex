package edu.udel.cisc675.randex;
public class TextMatcher {    
    private TextMatcher() {}

    /* Determines whether the sequence of characters in chars starting
       at offset off matches the chars of sought. */
    public static boolean match(char[] chars, int off, char[] sought) {
		  for (int i=0; i<sought.length; i++) {
			  if (off+i >= chars.length || sought[i] != chars[off+i])
			    return false;
		  }
		  return true;
    }
}