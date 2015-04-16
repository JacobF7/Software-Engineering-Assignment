package com.LeonAndJacob.app;

import java.util.ArrayList;

/**
 * Created by jacobfalzon on 27/03/15.
 */
public class TransactionManager
{
    private int numTransactionsProcessed;

    private AccountDatabase database;

    private ArrayList<LogItem> Transaction_Log = new ArrayList<LogItem>();

    protected TransactionManager(AccountDatabase database_in)
    {
        this.numTransactionsProcessed = 0;
        this.database = database_in;
    }

    public boolean processTransaction(int src, int dst, int amount)
    {
        Transaction trans = new Transaction(this.database,src,dst,amount);

        boolean outcome=true;

        int i;

        long current_time;

        for(i=0; i<Transaction_Log.size(); i++)
        {
            current_time = System.currentTimeMillis();

            if(current_time-(Transaction_Log.get(i).get_Transaction_Time())>=15000)
            {
                Transaction_Log.remove(i);
            }
            else if((src == Transaction_Log.get(i).get_sourceAccountNumber()) || (dst == Transaction_Log.get(i).get_destinationAccountNumber()))
            {
                outcome=false;
            }
        }

        if (outcome)
        {
            if(trans.process()==true)
            {
                this.numTransactionsProcessed++;

                //outcome is always true here
                //outcome=true;

                LogItem add_Log_Item = new LogItem(src,dst,System.currentTimeMillis());

                Transaction_Log.add(add_Log_Item);
            }
            else
            {
                outcome =false;
            }
        }

        return outcome;
    }
}
