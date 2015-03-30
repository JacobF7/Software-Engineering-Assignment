package com.LeonAndJacob.app;

/**
 * Created by jacobfalzon on 27/03/15.
 */
public class Transaction
{
    private int sourceAccountNumber;

    private int destinationAccountNumber;

    private long amount;

    public Transaction(int source_Account_Number_In,int destination_Account_Number_In,long amount_in)
    {
        this.sourceAccountNumber=source_Account_Number_In;

        this.destinationAccountNumber=destination_Account_Number_In;

        this.amount=amount_in;
    }

    public boolean process()
    {
       boolean result;

        boolean withdrawal = (AccountDatabase.getAccount(sourceAccountNumber)).adjustBalance(-(this.amount));

        if(withdrawal==false)
        {
            result=false;
        }
        else
        {
            boolean deposit = (AccountDatabase.getAccount(destinationAccountNumber)).adjustBalance(this.amount);

            result = true;
        }

       return result;
    }
}
