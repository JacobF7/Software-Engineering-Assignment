package com.LeonAndJacob.app;

import static com.LeonAndJacob.app.AccountDatabase.*;

/**
 * Created by jacobfalzon on 27/03/15.
 */
public class Transaction
{
    private AccountDatabase database;

    private int sourceAccountNumber;

    private int destinationAccountNumber;

    private long amount;

    public Transaction(AccountDatabase database_in,int source_Account_Number_In,int destination_Account_Number_In,long amount_in)
    {
        this.database = database_in;

        this.sourceAccountNumber=source_Account_Number_In;

        this.destinationAccountNumber=destination_Account_Number_In;

        this.amount=amount_in;
    }

    public boolean process()
    {
        boolean result;

        Account src = this.database.getAccount(sourceAccountNumber);

        Account dst = this.database.getAccount(destinationAccountNumber);


        if (src!=null && dst!= null)
        {
            boolean withdrawal = (this.database.getAccount(sourceAccountNumber)).adjustBalance(-(this.amount));

            if(withdrawal==false)
            {
                result=false;
            }
            else
            {
                boolean deposit = (this.database.getAccount(destinationAccountNumber)).adjustBalance(this.amount);

                //deposit should always be true as you are adjusting a balance positively,i.e result =true
                result = true&&deposit;
            }
        }
        else
        {
            result= false;
        }



       return result;
    }
}
