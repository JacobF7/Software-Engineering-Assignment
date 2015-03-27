package com.LeonAndJacob.app;

import org.junit.Test;
import org.junit.Assert;
import org.junit.Before;

/**
 * Unit test for simple App.
 */
public class Testing  {

    Account acc;
    AccountDatabase acc_db;
    Transaction trans;
    TransactionManager trans_mang;

    @Before
    public void Setup()
    {
        acc = new Account();

        acc_db = new AccountDatabase();

        trans = new Transaction();

        trans_mang = new TransactionManager();
    }

    @Test
    public void adjusdBalanceNegativelyTest()
    {
        Assert.assertEquals(false, acc.adjustBalance(-300));
    }
}
