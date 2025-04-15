package com.tumuhairwe.prep.pramp;

/**
 * LeetCode 2043 (medium)
 * You have been tasked with writing a program for a popular bank that will automate
 * all its incoming transactions (transfer, deposit & withdraw).
 * The bank has N accounts number from 1 to N.
 * The initial balance of each account is stored in a 0-indexed int[]
 * with the (i+1)the account having an initial balance of balance[i]
 *
 * - Execute all the valid transactions
 *   - Given account numbers are between 1 -> N
 *   - The amount of money withdrawn or transferred from is <= the balance of the account
 * - Implement the Bank class
 *   - constructor() initializes the objecct with 0-index int[]
 *   - boolean transfer(int account1, int account2, long money) transfers money from acct1 to acct2.
 *      Return true if the tx is possible, false otherwise
 *   - boolean deposit(int account, money) deposits money into the account.
 *      Return true if the tx is possible, false otherwise
 *   - boolean withdraw(int account, long money) withdraws money from account
 *      Return true if the tx is possible, false otherwise
 * ref: https://leetcode.com/problems/simple-bank-system/description/?envType=company&envId=coinbase&favoriteSlug=coinbase-thirty-days
 */
class Bank {
    private long[] balances;
    public Bank(long[] bals){
        balances = new long[bals.length + 1];
        for (int i = 0; i < balances.length; i++) {
            balances[i] = bals[i];
        }
    }

    public boolean transfer(int acct1, int acct2, long money){
        if(acct1 < 1 || acct1 >= balances.length){
            return false;
        }
        if(acct2 < 1 | acct2 >= balances.length){
            return false;
        }

        if(balances[acct1] - money < 0){
            return false;
        }

        balances[acct1] -= money;
        balances[acct2] += money;
        return true;
    }
    public boolean deposit(int acct, long money){
        if(acct < 1 || acct >= balances.length){
            return false;
        }
        balances[acct] += money;
        return true;
    }
    public boolean withdraw(int acct, long money){
        if(acct < 1 || acct >= balances.length){
            return false;
        }
        if(balances[acct] < money){
            return false;
        }
        balances[acct] -= money;
        return true;
    }
}
