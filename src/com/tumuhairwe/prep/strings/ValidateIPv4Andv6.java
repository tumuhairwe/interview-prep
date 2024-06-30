package com.tumuhairwe.prep.strings;

import java.util.Arrays;
import java.util.List;

/**
 * LeetCode 468 (medium)
 * ref: https://leetcode.com/problems/validate-ip-address/description/
 *
 * Given a string query, determine if its IP-v4 or IP-v6
 *
 * v4 rules
 * - must be in the form "x1.x2.x3.x4"
 * - 0 <= x <= 255
 * - Can NOT contain leading zeros
 * - e.g.
 *      "192.168.1.1" and "192.168.1.0" are valid IPv4 addresses while
 *      "192.168.01.1", "192.168.1.00", and "192.168@1.1" are invalid
 *
 *  v6 rules
 *  - must be in the form "x1:x2:x3:x4:x5:x6:x7:x8"
 *  - 1 <= xi.length <= 4
 *  - xi is a hexadecimal string which may contain digits,
 *          lowercase English letter ('a' to 'f')
 *          and upper-case English letters ('A' to 'F').
 *  - Leading zeros are allowed in xi.
 *  - e.g. "2001:0db8:85a3:0000:0000:8a2e:0370:7334" and "2001:db8:85a3:0:0:8A2E:0370:7334" are valid IPv6 addresses,
 *  while "2001:0db8:85a3::8A2E:037j:7334" and "02001:0db8:85a3:0000:0000:8a2e:0370:7334" are invalid IPv6 addresses.
 */
public class ValidateIPv4Andv6 {
    /**
     * Integer.toString(n,8) // decimal to octal
     * Integer.toString(n,2) // decimal to binary
     * Integer.toString(n,16) //decimal to Hex
     */
    public static void main(String[] args) {
        String[] valid_ipV4 = new String[]{"255.255.255.255"};
        for (String ipV4 : valid_ipV4){
            System.out.println("Is v4 VALID == " + validIPAddress(ipV4));
        }

        String[] invalid_ipv4 = new String[]{"192.168.01.1", "192.168@1.1", "192.168.1.00", "256.256.256.256", "01.01.01.02"};
        for (String ipV4 : invalid_ipv4){
            System.out.println("Is v4 INVALID == " + validIPAddress(ipV4));
        }

        System.out.println("------");
        String[] valid_ipv6 = new String[]{"2001:0db8:85a3:0000:0000:8a2e:0370:7334",
                "2001:db8:85a3:0:0:8A2E:0370:7334",
        "2001:0db8:85a3:0:0:8A2E:0370:7334"};
        for (String ipV6 : valid_ipv6){
            System.out.println("Is v6 VALID == " + validIPAddress(ipV6));
        }
        String[] invalid_ipv6 = new String[]{"2001:0db8:85a3::8A2E:037j:7334",
                "02001:0db8:85a3:0000:0000:8a2e:0370:7334",
                "2001:0db8:85a3:0:0:8A2E:0370:7334:"};
        for (String ipV6 : invalid_ipv6){
            System.out.println("Is v6 INVALID == " + validIPAddress(ipV6));
        }
    }

    /**
     * Solution summary
     * -v4
     *    - Global rules
     *      - When Regex split, token count must be 4
     *      - Can not start or end with .
     *   - Segment rules
     *      - 1 <= segment_length <= 3
     *      - must be numeric
     *      - can't have leading zeros
     *      - number can't be >255
     * -v6
     *    - Global rules
     *      - When Regex split, token count must be 8
     *      - Can not start or end with :
     *    - Segment rules
     *      - 1 <= segment_length <= 4
     *      - must be hexadecimal (i.e. allows only 0-9 and a-f)
     */
    public static String validIPAddress(String queryIP) {
        if(isValidV4Address(queryIP)){
            return "IPv4";
        }
        else if(isValidV6Address(queryIP)){
            return "IPv6";
        }
        else {
            return "Neither";
        }
    }

    static boolean isValidV4Address(String ip){
        int EXACT_TOKEN_COUNT = 4;

        // 1. split into tokens (with regex)
        String[] tokens = ip.split("\\.");
        if(tokens.length != EXACT_TOKEN_COUNT || ip.startsWith(".") || ip.endsWith(".")){
            return false;
        }
        // 3. validate segment by segment;
        boolean validV4 = true;
        for (String segment : tokens) {
            validV4 &= isValidV4Segment(segment);
        }

        return validV4;
    }
    static boolean isValidV4Segment(String segment){
        if(segment.isBlank() || segment.length() > 3){
            return false;
        }
        if (!segment.matches("^[0-9]*$")){
            return false;
        }

        //// disallow zero alone (must be '01')
        boolean hasLeadingZero = (segment.length() > 1 && segment.charAt(0) =='0');
        boolean isOutOfRange = (Integer.parseInt(segment) > 255);

        return !hasLeadingZero && !isOutOfRange;
    }
    static boolean isValidV6Address(String ip){
        // 0. set up initial state
        int EXACT_TOKEN_COUNT = 8;
        String[] tokens = ip.split("\\:");
        if(tokens.length != EXACT_TOKEN_COUNT || ip.startsWith(":") || ip.endsWith(":")){
            return false;
        }

        // 3. validate segment by segment;
        boolean validV6 = true;
        for (String segment : tokens){
            validV6 &= isValidV6Segment(segment);
        }

        return validV6;
    }
    static boolean isValidV6Segment(String segment){
        List<Character> allowableChars = List.of('A', 'B', 'C', 'D', 'E', 'F');

        // 0. validate segment length
        if(segment.isBlank() || segment.length() > 4){
            return false;
        }
        // 1. if non-numeric: -> validate allowable characters  // alphanumeric regex = "^[a-zA-Z0-9]+$"
        for(char c : segment.toUpperCase().toCharArray()){
            if(Character.isAlphabetic(c) && !allowableChars.contains(c)){
                return false;
            }
        }

        return true;
    }
}
