package com.tumuhairwe.prep.strings;

public class AddBinary {

    public static void main(String[] args) {
        String a = "1";
        String b = "111";
        System.out.println("Should be 1000:  " +addBinary(a, b));
    }

    /**
     * Solution summary
     * - Declare vars (StringBuilder, length_of_s1, length_of_s2 and initialize carry = 0)
     * - while length_of_s1 >= 0 or length_of_s2 >= 0
     *      - set sum to carry
     *      - calculate cumulative sum += the_char_at_index_of_str
     *      - if sum > 1, set carry to 1, else 0
     *      - append (sum % 2) to sBuilder
     * - if there's a carry after exiting the loop, add it to the sBuilder
     * - reverse sBuilder and return it
     */
    public static String addBinary(String a, String b){
        StringBuilder sb = new StringBuilder();
        int aLen = a.length() - 1;
        int bLen = b.length() - 1;
        int carry = 0;

        while(aLen >= 0 || bLen >= 0){
            int sum = carry;

            if(aLen >= 0){
                sum += a.charAt(aLen) - '0';
                aLen--;
            }
            if (bLen >= 0){
                sum += b.charAt(bLen) - '0';
                bLen--;
            }
            carry = sum > 1 ? 1: 0;
            sb.append(sum % 2);
        }

        if(carry != 0){
            sb.append(carry);
        }

        return sb.reverse().toString();
    }
}
