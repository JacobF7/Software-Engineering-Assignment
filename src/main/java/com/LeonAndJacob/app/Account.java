package com.LeonAndJacob.app;

public class Account
{
    private int accountNumber;

    private String accountName;

    private long accountBalance;

    public boolean adjustBalance(long amount)
    {
       throw new UnsupportedOperationException();
    }

    public Account(int account_Number,String Name,long initialBalance)
    {
        this.accountNumber = account_Number;

        this.accountName = Name;

        this.accountBalance= initialBalance;

    }

    public Account()
    {
        accountNumber = 0;

        accountName = null;

        accountBalance = 0;


    }
}
