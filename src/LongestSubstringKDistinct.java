import java.util.HashMap;
import java.util.Map;

public class LongestSubstringKDistinct {
    public static void main(String[] args) {
        System.out.println("Length of the longest substring: " + findLength("araaci", 2));
        System.out.println("Length of the longest substring: " + findLength("araaci", 1));
        System.out.println("Length of the longest substring: " + findLength("cbbebi", 3));
    }
    // time complexity: O(N + N ) => O(N)
    // space complexity: O (K)
    static int findLength(String str, int k){
        if(str == null || str.length() == 0){
            throw new IllegalArgumentException();
        }
        int maxLength = 0, windowStart = 0;
        Map<Character, Integer> characterFrequencyMap = new HashMap<>();
        for (int windowEnd = 0; windowEnd < str.length(); windowEnd++) {    // O (N)
            char rightChar = str.charAt(windowEnd);
            // count frequency
            characterFrequencyMap.put(rightChar, characterFrequencyMap.getOrDefault(rightChar, 0));

            // shrink sliding window
            while (characterFrequencyMap.size() > k){
                char leftChar = str.charAt(windowStart);
                characterFrequencyMap.put(leftChar, characterFrequencyMap.get(leftChar)-1);

                if(characterFrequencyMap.get(leftChar) == 0){
                    // remove
                    characterFrequencyMap.remove(leftChar);
                }
                windowStart++;
            }
            // remember the max Length so far
            maxLength = Math.max(maxLength, windowEnd - windowStart + 1);
        }
        return maxLength;
    }
}
