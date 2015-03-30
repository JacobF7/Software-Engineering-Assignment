package com.LeonAndJacob.app;

/**
 * Created by jacobfalzon on 27/03/15.
 */
public class TransactionManager
{
    private int numTransactionsProcessed;

    public boolean processTransaction(int src, int dst, int amount)
    {
        Transaction trans = new Transaction(src,dst,amount);

        boolean outcome;

        if(trans.process()==true)
        {
            this.numTransactionsProcessed++;
            outcome=true;
        }
        else
        {
            outcome =false;
        }

        return outcome;
    }
}
