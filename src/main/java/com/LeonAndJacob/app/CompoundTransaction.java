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
        int depSrcAccount, mainSrcAccount, commSrcAccount,commDestAccount;
        double comm_percentage;


        if(risk.equalsIgnoreCase("high"))
        {
            depSrcAccount = 3123;
            mainSrcAccount = 3143;
            commSrcAccount = 6565;
            commDestAccount = 4444;
            comm_percentage = 0.1;
        }
        else if(risk.equalsIgnoreCase("low"))
        {
            depSrcAccount = 8665;
            mainSrcAccount = 3133;
            commSrcAccount = 6588;
            commDestAccount = 4445;
            comm_percentage = 0.05;
        }
        else
        {
            //in case of invalid risk
            return false;
        }

        //Create Atomic Deposit Transaction
        Transaction dep = new Transaction("Deposit Transaction",db,depSrcAccount,depDestAccount.get_Account_Number(),depAmount);
        dep.setRisk(risk.toLowerCase());

        //list of destination accounts must be equal to the number of amounts
        if(mainDestAccounts.size()!=mainAmount.size())
        {
            return false;
        }

        //Creating Compound Main Transaction
        CompoundTransaction main = new CompoundTransaction("Main Compound");
        main.setRisk(risk.toLowerCase());
        int i =0;
        long total =0;
        for(Account a: mainDestAccounts)
        {
            Transaction m = new Transaction("Main Transaction",db,mainSrcAccount,a.get_Account_Number(),mainAmount.get(i));
            m.setRisk(risk.toLowerCase());
            main.addTransaction(m);
            total+=mainAmount.get(i);
            i++;
        }


        //Commission Transaction being created
        CompoundTransaction comm = new CompoundTransaction("Commission Compound");
        comm.setRisk(risk.toLowerCase());

        Transaction comm_atomic_transaction = new Transaction("Commission Transaction",db,commSrcAccount,commDestAccount,(long)(comm_percentage*total));
        comm_atomic_transaction.setRisk(risk.toLowerCase());
        comm.addTransaction(comm_atomic_transaction);


        //Reset Transaction List to Empty
        List<Transaction> transactionslist = this.getTransaction_list() ;
        transactionslist = new ArrayList<Transaction>();

        this.setRisk(risk.toLowerCase());
        this.addTransaction(dep);
        this.addTransaction(main);
        this.addTransaction(comm);

        return true;

    }

    public List<Transaction> getTransaction_list()
    {
        return transaction_list;
    }

}