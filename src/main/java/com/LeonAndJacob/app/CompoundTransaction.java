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

    public boolean preDefine(RiskPresets preset,Account depDestAccount, long depAmount ,List<Account> mainDestAccounts, List<Long> mainAmount, AccountDatabase db)
    {
        int depSrcAccount, mainSrcAccount, commSrcAccount,commDestAccount;
        String risk;
        double comm_percentage;

        if(preset.risk_name.equals("")) //no such preset or no custom preset created before-hand
        {
            return false;
        }
        else
        {
            risk =preset.risk_name;
            depSrcAccount = preset.depSrcAccount;
            mainSrcAccount = preset.mainSrcAccount;
            commSrcAccount = preset.commSrcAccount;
            commDestAccount = preset.commDestAccount;
            comm_percentage = preset.comm_percentage;
        }

        //RISK NAME ATTRIBUTE IS ALWAYS IN LOWER CASE

        //Create Atomic Deposit Transaction
        Transaction dep = new Transaction("Deposit Transaction",db,depSrcAccount,depDestAccount.get_Account_Number(),depAmount);
        dep.setRisk(risk);

        //list of destination accounts must be equal to the number of amounts
        if(mainDestAccounts.size()!=mainAmount.size())
        {
            return false;
        }

        //Creating Compound Main Transaction
        CompoundTransaction main = new CompoundTransaction("Main Compound");
        main.setRisk(risk);
        int i =0;
        long total =0;
        for(Account a: mainDestAccounts)
        {
            Transaction m = new Transaction("Main Transaction",db,mainSrcAccount,a.get_Account_Number(),mainAmount.get(i));
            m.setRisk(risk);
            main.addTransaction(m);
            total+=mainAmount.get(i);
            i++;
        }


        //Commission Transaction being created
        CompoundTransaction comm = new CompoundTransaction("Commission Compound");
        comm.setRisk(risk);

        Transaction comm_atomic_transaction = new Transaction("Commission Transaction",db,commSrcAccount,commDestAccount,(long)(comm_percentage*total));
        comm_atomic_transaction.setRisk(risk);
        comm.addTransaction(comm_atomic_transaction);


        //Reset Transaction List to Empty
        List<Transaction> transactionslist = this.getTransaction_list() ;
        transactionslist = new ArrayList<Transaction>();

        this.setRisk(risk);
        this.addTransaction(dep);
        this.addTransaction(main);
        this.addTransaction(comm);

        return true;

    }

    public List<Transaction> traverse()
    {
        List<Transaction> result = new ArrayList<Transaction>();

        for(Transaction t_iterate: this.getTransaction_list())
        {
            if (t_iterate instanceof CompoundTransaction)
            {
                CompoundTransaction ct_iterate = (CompoundTransaction) t_iterate;
                result.addAll(ct_iterate.traverse());
            }
            else
            {
                result.add(t_iterate);
            }
        }

        return result;

    }

    public List<Transaction> getTransaction_list()
    {
        return transaction_list;
    }

}