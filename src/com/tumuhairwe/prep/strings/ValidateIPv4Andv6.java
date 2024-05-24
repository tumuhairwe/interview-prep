package com.tumuhairwe.prep.strings;

import java.util.Arrays;
import java.util.List;

/**
 * LeetCode 468
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
    public static void main(String[] args) {
        String[] valid_ipV4 = new String[]{"172.16.254.1", "192.168.1.0"};
        for (String ipV4 : valid_ipV4){
            System.out.println("Is v4 VALID == " + validIPAddress(ipV4));
        }

        String[] invalid_ipv4 = new String[]{"192.168.01.1", "192.168@1.1", "192.168.1.00"};
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
                "02001:0db8:85a3:0000:0000:8a2e:0370:7334"};
        for (String ipV6 : invalid_ipv6){
            System.out.println("Is v6 INVALID == " + validIPAddress(ipV6));
        }
    }
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
        final int EXACT_NUMBER_OF_TOKENS = 4;
        boolean validV4 = true;

        // 1. split into tokens (with regex)
        String[] tokens = ip.split("\\.");
        if(tokens.length != EXACT_NUMBER_OF_TOKENS){
            return false;
        }
        // 2. validate segment by segment;
        for (String segment : tokens) {
            validV4 &= isValidV4Segment(segment);
        }

        return validV4;
    }
    static boolean isValidV4Segment(String segment){
        int MIN_LENGTH = 1;
        int MAX_LENGTH = 255;

        char[] s = segment.toCharArray();
        for(int i=0; i<s.length; i++){
            if(!Character.isDigit(s[i])){
                return false;
            }
            else if(s[i] == '0' && i != 0){   // disallow zero alone (must be '01')
                return false;
            }
        }

        try{
            int num = Integer.parseInt(segment);
            if(segment.length() < MIN_LENGTH || num > MAX_LENGTH){
                return false;
            }
        }
        catch(Exception e){
            return false;   // failed to parse number e.g. x1
        }

        return true;
    }
    static boolean isValidV6Address(String ip){
        final int EXACT_NUMBER_OF_TOKENS = 8;
        final Character V6_SEPARATOR= ':';

        // 0. validate ip address format
//        int separatorCount = 0;
//        for (int i = 0; i < ip.length(); i++) {
//            if(ip.toCharArray()[i] == V6_SEPARATOR){
//                separatorCount++;
//            }
//        }

        String[] tokens = ip.split(":");
        if(tokens.length != EXACT_NUMBER_OF_TOKENS
        //|| separatorCount + 1 < tokens.length
        ){
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
        int MIN_LENGTH = 1;
        int MAX_LENGTH = 9999;
        int MAX_SEGMENT_LENGTH = 4;

        // 0. validate segment length
        if(segment.length() != 0 && segment.length() > MAX_SEGMENT_LENGTH){
            return false;
        }

        try{
            // 1. if numeric: check boundaries
            int num = Integer.parseInt(segment);
            if(segment.length() < MIN_LENGTH || num > MAX_LENGTH){
                return false;
            }
        }
        catch(Exception e){
            // 1. if non-numeric -> validate allowable characters
            List<Character> allowableChars = List.of('A', 'B', 'C', 'D', 'E', 'F');
            char[] s = segment.toCharArray();
            for(int i=0; i<s.length; i++){
                if(!Character.isDigit(s[i]) && !allowableChars.contains(Character.toUpperCase(s[i]))){
                    return false;   // is non-numeric and non-hex
                }
            }
        }

        return true;
    }
}
