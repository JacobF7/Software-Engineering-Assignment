package com.LeonAndJacob.app;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;


/**
 * Unit test for simple App.
 */
public class Testing  {

    static Account acc,acc2,acc3;
    static AccountDatabase acc_db;
    static Transaction trans;
    static TransactionManager trans_mang;

    @BeforeClass
    public static void SetupBeforeClass()
    {
        acc_db = new AccountDatabase();

        trans_mang = new TransactionManager();

        acc = new Account(1,"Jacob&Leon",100);

        acc2 = new Account(2,"Jacob&Leon2",1000);
    }

    @Before
    public void SetupBefore()
    {
        // Reset Balance Before Each Test
        acc.set_Account_Balance(100);

        acc2.set_Account_Balance(1000);
    }


    //Account Class Tests
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


    //AccountDatabase Class Tests

    @Test
    public void AddAccountValid()
    {
        Assert.assertEquals(true,acc_db.addAccount(acc));
    }

    //Account Number Already Used
    @Test
    public void AddAccountInvalid1()
    {
        acc_db.addAccount(acc2);

        acc3 = new Account(2,"Jacob&Leon3",4000);

        Assert.assertEquals(false,acc_db.addAccount(acc3));
    }

    //Negative Balance
    @Test
    public void AddAccountInvalid2()
    {
        acc3 = new Account(3,"Jacob&Leon4",-4000);

        Assert.assertEquals(false,acc_db.addAccount(acc3));
    }

    @Test
    public void getAccountTest()
    {
        Assert.assertEquals(acc,acc_db.getAccount(acc.get_Account_Number()));
    }

     /*
    @Test
    public void getAccountTestNegatively()
    {
        Assert.assertEquals(null,acc_db.getAccount(-1));
    }
    */


    @Test
    public void getSizeTest()
    {
        //acc and acc2 were previously added, note that the database is static
        acc3 = new Account(3,"Jacob&Leon3",2000);

        acc_db.addAccount(acc3);

        Assert.assertEquals(3,acc_db.getSize());
    }

    //Transaction Class Tests

    //sufficient funds for transaction
    @Test
    public void processTestA()
    {
        //pass transaction
        trans = new Transaction(acc.get_Account_Number(),acc2.get_Account_Number(),30);

        Assert.assertEquals(true,trans.process());

    }

    //insufficient funds for transaction
    @Test
    public void processTestB()
    {
        //failed transaction
        trans = new Transaction(acc.get_Account_Number(),acc2.get_Account_Number(),200);

        Assert.assertEquals(false,trans.process());
    }

    //Case Transaction Object is created with non existent Account, with Account Number 5
    @Test
    public void processTestC()
    {
        //failed transaction
        trans = new Transaction(acc.get_Account_Number(),5,10);

        Assert.assertEquals(false,trans.process());
    }

    //TransactionManager Class Tests

    //insufficient funds for transaction
    @Test
    public void processTransactionManagerCaseA()
    {
        acc_db.addAccount(acc);
        acc_db.addAccount(acc2);

        Assert.assertEquals(false,trans_mang.processTransaction(acc.get_Account_Number(),acc2.get_Account_Number(),200));
    }

    //sufficient funds for transaction
    @Test
    public void processTransactionManagerCaseB()
    {
        Assert.assertEquals(true,trans_mang.processTransaction(acc.get_Account_Number(),acc2.get_Account_Number(),30));
    }

    //15 seconds Test Fail since "acc2",i.e.the destination account, previously made a transaction
    @Test
    public void processTransactionManagerCaseC()
    {
        acc3 = new Account(3,"Jacob&Leon3",2000);
        acc_db.addAccount(acc3);


        trans_mang.processTransaction(acc.get_Account_Number(), acc2.get_Account_Number(), 30);

        Assert.assertEquals(false,trans_mang.processTransaction(acc3.get_Account_Number(),acc2.get_Account_Number(),30));
    }

    //15 seconds Test Fail since "acc",i.e.the source account, previously made a transaction
    @Test
    public void processTransactionManagerCaseD()
    {
        trans_mang.processTransaction(acc.get_Account_Number(), acc2.get_Account_Number(), 30);

        Assert.assertEquals(false,trans_mang.processTransaction(acc.get_Account_Number(),acc3.get_Account_Number(),30));
    }

    //15 seconds Test Pass
    @Test
    public void processTransactionManagerCaseE() throws InterruptedException
    {
        trans_mang.processTransaction(acc.get_Account_Number(),acc2.get_Account_Number(),30);

        Thread.sleep(15000);

        Assert.assertEquals(true,trans_mang.processTransaction(acc.get_Account_Number(),acc2.get_Account_Number(),30));
    }

}
