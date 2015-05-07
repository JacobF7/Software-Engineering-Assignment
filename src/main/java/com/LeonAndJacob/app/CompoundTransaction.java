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
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean process()
    {
        throw new UnsupportedOperationException();
    }

    public List<Transaction> getTransaction_list()
    {
        throw new UnsupportedOperationException();//return transaction_list;
    }

}
