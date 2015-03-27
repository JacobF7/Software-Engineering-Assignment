package com.LeonAndJacob.app;

import org.junit.Test;
import org.junit.Assert;
import org.junit.Before;

/**
 * Unit test for simple App.
 */
public class AccountTest  {

    Account acc;

    @Before
    public void Setup()
    {
         acc = new Account();
    }

    @Test
    public void adjusdBalanceNegativelyTest()
    {

        Assert.assertEquals(false, acc.adjustBalance(-100));
    }
}
