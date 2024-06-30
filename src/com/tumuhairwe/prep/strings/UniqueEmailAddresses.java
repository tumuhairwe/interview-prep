package com.tumuhairwe.prep.strings;

import java.util.HashSet;
import java.util.Set;

/**
 * LeetCode 929 (easy). Unique Email Addresses
 *
 *  Given an array of strings emails where we send one email to each emails[i], return the number of different addresses that actually receive mails.
 *  Rules
 *  - ignore everything after a + in local_name
 *  - alice.z@email.com == alice@EMAIL.com == alice@email.com
 *
 * ref https://leetcode.com/problems/unique-email-addresses/description/
 */
public class UniqueEmailAddresses {
    public static void main(String[] args) {
        String[] emails = {"test.email+alex@leetcode.com","test.e.mail+bob.cathy@leetcode.com","testemail+david@lee.tcode.com"};
        System.out.println("Should be 2: " + numUniqueEmails(emails));
    }
    public static int numUniqueEmails(String[] emails) {
        Set<String> set = new HashSet<>();
        for(String email : emails){
            set.add(getFormattedEmail(email));
        }

        return set.size();
    }

    public static String getFormattedEmail(String email){
        String[] tokens = email.split("@");
        String name = tokens[0];
        String domain = tokens[1];

        name = name.split("\\+")[0];  // ignore everything after +
        name = name.replace(".", "");

        return name + "@" + domain;
    }
}
