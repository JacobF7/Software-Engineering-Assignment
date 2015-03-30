package com.LeonAndJacob.app;

import org.junit.Test;
import org.junit.Assert;
import org.junit.Before;

/**
 * Unit test for simple App.
 */
public class Testing  {

    Account acc,acc2;
    AccountDatabase acc_db;
    Transaction trans;
    TransactionManager trans_mang;

    @Before
    public void Setup()
    {
        //acc = new Account();

        acc_db = new AccountDatabase();

        trans = new Transaction();

        trans_mang = new TransactionManager();

        acc = new Account(acc_db.getSize(),"Jacob&Leon",100);

        acc2 = new Account(acc_db.getSize(),"Jacob&Leon2",100);

        acc_db.account_database.add(acc);

        acc_db.account_database.add(acc2);
    }

    @Test
    public void adjustBalanceNegativelyTest()
    {
        Assert.assertEquals(false, acc.adjustBalance(-300));
    }

    @Test
    public void adjustBalancePositivelyTest()
    {
        Assert.assertEquals(true, acc.adjustBalance(300));
    }

    /*
    @Test
    public void getAccountTestNegatively()
    {
        Assert.assertEquals(acc,acc_db.getAccount(-1));
    }
    */

    @Test
    public void getAccountTestPositively()
    {
        Assert.assertEquals(acc,acc_db.getAccount(1));
    }

    @Test
    public void getSizeTest()
    {
        Assert.assertEquals(2,acc_db.getSize());
    }

    @Test
    public void processTransactionManagerWithdrawCaseA()
    {
        Assert.assertEquals(true,trans_mang.processTransaction(1,2,200));
    }

    @Test
    public void processTransactionManagerWithdrawCaseB()
    {
        Assert.assertEquals(false,trans_mang.processTransaction(1,2,200));
    }

}
