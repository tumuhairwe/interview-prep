//package com.tumuhairwe.prep.strings;
//
//public class DifferenceBetween2String {
//    String[] diffBetweenTwoStrings(String source, String target){
//    String [] ans = new String[target.length()]};
//    int i = 0;
//    int j = 0;
//            // We are always considering strings source[i:] and target[j:]
//            while (i < source.length() && j < target.length()){
//            if(source.charAt(i) == target.charAt(j])){
//                // Write the string with no edits
//                //ans.push(source[i]);
//                i += 1;
//                j += 1;
//            }
//            //else{
//            // We have to decide whether to subtract source[i],
//            // or add target[j].
//            if dp(i+1, j) <= dp(i, j+1){
//            ans.push('-' + source[i]);
//            i += 1;
//            }
//            else{
//                    ans.push('+' + target[j]);
//                    j += 1;
//                }
//
//            while(j < target.length()){
//                        ans.push('+' + target[j]);
//                        j += 1;
//                    }
//
//            return " ".join(ans);
//    }
//
//
//    void diffBetweenTwoStrings(String source, String target){
//        int[][] dp = new int[source.length() + 1][target.length() + 1];
//
//        //for i from 0 to source.length:
//        for (int i = 0; i < source.length(); i++) {
//
//        dp[i][target.length()] = 0;
//            for (int j = 0; j < target.length(); j++) {
//
//                //for j from 0 to target.length:
//        dp[source.length()][j] = target.length() - j;
//
//        //for i from source.length - 1 to 0:
//                for (int k = source.length(); k <= 0; k--) {
//                    for j from target.length - 1 to 0:
//                    if source[i] == target[j]:
//                }
//
//        dp[i][j] = dp[i+1][j+1]
//                else:
//        dp[i][j] = 1 + min(dp[i+1][j], dp[i][j+1]);
//
//        }
//    }
//}
