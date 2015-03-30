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

    public void set_Account_Number(int account_Number_in)
    {
        this.accountNumber=account_Number_in;
    }

    public void  set_Account_Name(String account_Name_in)
    {
        this.accountName=account_Name_in;
    }

    public  void set_Account_Balance(int account_Balance_in)
    {
        this.accountBalance=account_Balance_in;
    }

    public int get_Account_Number()
    {
        return this.accountNumber;
    }

    public String get_Account_Name()
    {
        return this.accountName;
    }

    public  long get_Account_Balance()
    {
        return this.accountBalance;
    }


}
