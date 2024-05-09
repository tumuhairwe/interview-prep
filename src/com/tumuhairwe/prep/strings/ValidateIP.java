package com.tumuhairwe.prep.strings;

/**
 * Given a string (ip-address), validate if its a valid ip address
 * An IP is valid if its of the form w.x.y.z
 *
 * Solution
 * - Iterate over the characters
 * a) check if its a number or a DOT
 * - keep count of the number of dots
 * - decrement number of dots ... if the count is negative, -> false
 **/
class ValidateIP {

    private static Character DOT = '.';
    public static void main(String[] args) {
        String ip = "123.24.59.99";
        boolean result = validateIPManual(ip);
        System.out.println(result);
    }

    static boolean validateIPRegex(String ip) {
        String[] tokens = ip.split("\\.");
        if(tokens.length > 4){
            return false;
        }
        for (String token : tokens){
            try {
                int i = Integer.parseInt(token);
                if(i > 255 || i < 0){
                    return false;
                }
            }catch (Exception e){
                return false;
            }
        }
        return true;
    }
    static boolean validateIPManual(String ip) {
        String[] tokens = ip.split("\\.");
        if(tokens.length > 4){
            return false;
        }
//        for (int i = 0; i < tokens.length; i++) {
//            try{
//                int val = Integer.parseInt(tokens[i]);
//                if (val <=0 || val > 255){
//                    return false;
//                }
//            }catch (Exception e){
//                return false;
//            }
//        }
//        return true;

        char[] chars = ip.toCharArray(); //
        int numberOfDots = 0;
        int MAX_NUMBER_OF_DOTS = 4;
        int lastDotIndex = 0;

        for (int i = 0; i < chars.length; i++) {
            // increment dot counter
            if (chars[i] == DOT) {
                lastDotIndex = i;
                numberOfDots++;

                if (numberOfDots > MAX_NUMBER_OF_DOTS) {
                    return false;
                }
                continue;
            }

            // a
            if (!Character.isDigit(chars[i]) && chars[i] != DOT) {
                return false;
            }
            // a
            else if (Character.isDigit(chars[i])) {
                // 123.245.111.999 (lastdot = 0, i =4)
                String digits = "";
                if (i > 0) {
                    if (lastDotIndex > 0) {
                        digits = ip.substring(lastDotIndex + 1, i + 1); // 123
                    } else {
                        digits = ip.substring(lastDotIndex + 1, i + 1); // 123
                    }
                } else if (i == 0) {
                    digits = "" + chars[i];
                }

                //ip.substring(lastDotIndex, i); // 123
                if (!digits.matches("[0-9]+")) {
                    return false;  // abc.123.000.11993
                } else {
                    int num = Integer.parseInt(digits);
                    if (num < 0 || num > 255) {
                        return false;
                    }
                }
            }
        }

        return true;
    }
}
