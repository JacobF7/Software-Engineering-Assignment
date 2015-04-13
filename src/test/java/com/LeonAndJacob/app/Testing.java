package com.LeonAndJacob.app;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;


/**
 * Unit test for simple App.
 */
public class Testing  {

    static Account acc,acc2,acc3,acc4;
    static AccountDatabase acc_db;
    static Transaction trans;
    static TransactionManager trans_mang;

    @BeforeClass
    public static void SetupBeforeClass()
    {
        //acc = new Account();

        acc_db = new AccountDatabase();

        //trans = new Transaction();

        trans_mang = new TransactionManager();

        acc = new Account(1,"Jacob&Leon",100);

        acc2 = new Account(2,"Jacob&Leon2",1000);

        Assert.assertEquals(true,acc_db.addAccount(acc));

        Assert.assertEquals(true,acc_db.addAccount(acc2));
    }

    @Before
    public void SetupBefore()
    {
        // Reset Balance Before Each Test
        acc.set_Account_Balance(100);

        acc2.set_Account_Balance(1000);

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

    //Add Account Tests

     /*@Test
    public void AddAccountValid1()
    {
        Assert.assertEquals(true,acc_db.addAccount(acc));
    }*/

    /*@Test
    public void AddAccountValid2()
    {
        Assert.assertEquals(true,acc_db.addAccount(acc2));
    }*/


    @Test
    public void getAccountTest()
    {
        Assert.assertEquals(acc,acc_db.getAccount(1));
    }

     /*
    @Test
    public void getAccountTestNegatively()
    {
        Assert.assertEquals(acc,acc_db.getAccount(-1));
    }
    */

    //Extra Add Account Test

    /*
    @Test
    public void AddAccountValid()
    {

        acc3 = new Account(3,"Jacob&Leon3",2000);

        Assert.assertEquals(true,acc_db.addAccount(acc3));
    }
    */

    //Account Number Already Used
    @Test
    public void AddAccountInvalid1()
    {
        acc3 = new Account(2,"Jacob&Leon3",4000);

        Assert.assertEquals(false,acc_db.addAccount(acc3));
    }

    //Negative Balance
    @Test
    public void AddAccountInvalid2()
    {
        acc4 = new Account(3,"Jacob&Leon4",-4000);

        Assert.assertEquals(false,acc_db.addAccount(acc4));
    }

    @Test
    public void getSizeTest()
    {
        Assert.assertEquals(2,acc_db.getSize());
    }


    @Test
    public void processTestA()
    {
        //pass transaction
        trans = new Transaction(acc.get_Account_Number(),acc2.get_Account_Number(),30);

        Assert.assertEquals(true,trans.process());

    }

    @Test
    public void processTestB()
    {
        //failed transaction
        trans = new Transaction(acc.get_Account_Number(),acc2.get_Account_Number(),200);

        Assert.assertEquals(false,trans.process());
    }

    //Case Transaction Object is created with non existent Account, with Account Number 11
    @Test
    public void processTestC()
    {
        //failed transaction
        trans = new Transaction(acc.get_Account_Number(),11,10);

        Assert.assertEquals(false,trans.process());
    }

    @Test
    public void processTransactionManagerWithdrawCaseA()
    {
        Assert.assertEquals(true,trans_mang.processTransaction(acc.get_Account_Number(),acc2.get_Account_Number(),30));
    }

    @Test
    public void processTransactionManagerWithdrawCaseB()
    {
        Assert.assertEquals(false,trans_mang.processTransaction(acc.get_Account_Number(),acc2.get_Account_Number(),200));
    }

    //15 seconds Test Fail since "acc2",i.e.the destination account, previously made a transaction
    @Test
    public void processTransactionManagerWithdrawCaseC() {
        trans_mang.processTransaction(acc.get_Account_Number(), acc2.get_Account_Number(), 30);

        Assert.assertEquals(false,trans_mang.processTransaction(acc3.get_Account_Number(),acc2.get_Account_Number(),30));
    }

    //15 seconds Test Fail since "acc",i.e.the source account, previously made a transaction
    @Test
    public void processTransactionManagerWithdrawCaseD() {
        trans_mang.processTransaction(acc.get_Account_Number(), acc2.get_Account_Number(), 30);

        Assert.assertEquals(false,trans_mang.processTransaction(acc.get_Account_Number(),acc3.get_Account_Number(),30));
    }

    //15 seconds Test Pass
    @Test
    public void processTransactionManagerWithdrawCaseE() throws InterruptedException {
        trans_mang.processTransaction(acc.get_Account_Number(),acc2.get_Account_Number(),30);

        Thread.sleep(15000);

        Assert.assertEquals(true,trans_mang.processTransaction(acc.get_Account_Number(),acc2.get_Account_Number(),30));
    }


}
