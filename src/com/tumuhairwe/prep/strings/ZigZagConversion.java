package com.tumuhairwe.prep.strings;

public class ZigZagConversion {

    public static void main(String[] args) {
        String s = "PAYPALISHIRING";
        System.out.println(convert(s, 3));
    }

    /**
     * Solution summary
     * - Create array of StringBuffer .. and initialize with StringBuffer objects (threadsafe + StringBuilder will run out of memory)
     * - use a charIndex to track the char to be inserted into the sb
     * - while charIndex < s.length()
     *      - iterate downward (from up going down) and append s.charAt(charIndex) to stringBuffer in each row (as long as charIndex < s.length())
     *      - iterate upward (from down up) and append s.charAt(charIndex) to the stringBugger in each row
     * - At the end combine all the stringBuffer on every row into a single stringBuffer
     * - return sBuffer.toStringI()
     */
    static String convert(String s, int numRows){
        //0. create array of stringBuffer for each row
        StringBuffer[] rows = new StringBuffer[numRows];
        for (int i = 0; i < numRows; i++) {
            rows[i] = new StringBuffer();
        }

        //1. iterate down and up in zig zag form
        int charIndex = 0;
        while(charIndex< s.length()){
            // iterate downward
            for (int downwardIndex=0; downwardIndex<numRows && charIndex<s.length(); downwardIndex++){
                rows[downwardIndex].append(s.charAt(charIndex++));
            }

            // iterate upward
            for (int upwardIndex=numRows-2; upwardIndex>0&& charIndex < s.length(); upwardIndex--){
                rows[upwardIndex].append(s.charAt(charIndex++));
            }
        }

        //5. convert array into stringBuffer
        StringBuffer sb = new StringBuffer();
        for (int j = 0; j < rows.length; j++) {
            sb.append(rows[j]);
        }

        // return stringBuffer as string
        return sb.toString();
    }
}
