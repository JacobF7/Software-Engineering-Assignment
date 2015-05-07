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

    public boolean processCompoundTransaction(CompoundTransaction trn_in)
    {
        boolean outcome = true;

        boolean sub_outcome=true;

        long current_time;

        try
        {
            if (trn_in.process())
            {
                for (Transaction t : trn_in.getTransaction_list())
                {
                    if (t.getAmount() != -1)// if not a Compound Transaction
                    {
                        int i;

                        for (i = 0; i < Transaction_Log.size(); i++)
                        {
                            current_time = System.currentTimeMillis();

                            if (current_time - (Transaction_Log.get(i).get_Transaction_Time()) >= 15000)
                            {
                                Transaction_Log.remove(i);
                            }
                            else if ((t.getSourceAccountNumber() == Transaction_Log.get(i).get_sourceAccountNumber()) || (t.getDestinationAccountNumber() == Transaction_Log.get(i).get_destinationAccountNumber()))
                            {
                                Thread.sleep(15000 - (current_time - Transaction_Log.get(i).get_Transaction_Time()));
                                break;
                            }
                        }

                        //Process Item
                        if (t.process())
                        {
                            this.numTransactionsProcessed++;

                            LogItem add_Log_Item = new LogItem(t.getSourceAccountNumber(), t.getDestinationAccountNumber(), System.currentTimeMillis());

                            Transaction_Log.add(add_Log_Item);
                        }
                        else
                        {
                            outcome = false;
                            throw new Exception("Transaction Failed");
                        }

                    }
                    else //if t is a compound transaction
                    {
                        sub_outcome =this.processCompoundTransaction((CompoundTransaction) t);
                    }
                }
            }
            else
            {
                outcome = false;
                throw new Exception("Transaction Failed");
            }
        }
        catch(Exception e)
        {
            System.out.println(e.toString());
            return outcome;
        }

        return outcome&&sub_outcome;
    }
}
