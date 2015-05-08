package com.LeonAndJacob.app;

import java.util.*;

/**
 * Created by jacobfalzon on 07/05/15.
 */
public class CompoundTransaction extends Transaction {

    private List<Transaction> transaction_list;

    public CompoundTransaction(String name_in)
    {
        super(name_in);
        this.transaction_list = new ArrayList<Transaction>();
    }

    public void addTransaction(Transaction trans_in)
    {
        transaction_list.add(trans_in);
    }

    @Override
    public boolean process()
    {
        boolean outcome=true;


        if (this.transaction_list.isEmpty())
        {
            outcome=false;

        }

        /*for (Transaction t : this.transaction_list)
        {
            if(!t.process())
            {
                outcome=false;
                throw new Exception("Transaction Failed");
            }

        }*/

        return outcome;
    }

    public boolean preDefine(String risk,Account depDestAccount, long depAmount ,List<Account> mainDestAccounts, List<Long> mainAmount, AccountDatabase db)
    {
        throw new UnsupportedOperationException();
    }

    public List<Transaction> getTransaction_list()
    {
        return transaction_list;
    }

}