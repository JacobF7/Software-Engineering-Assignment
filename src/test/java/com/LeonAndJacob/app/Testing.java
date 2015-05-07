package com.LeonAndJacob.app;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;


/**
 * Unit test for simple App.
 */
public class Testing  {

    Account acc,acc2;
    Account acc3;
    AccountDatabase acc_db;
    Transaction trans;
    TransactionManager trans_mang;
    CompoundTransaction ct,ct1;


    /*@BeforeClass
    public static void SetupBeforeClass()
    {
        acc = new Account(1,"Jacob&Leon",100);

        acc2 = new Account(2,"Jacob&Leon2",1000);
    }*/


    @Before
    public void SetupBefore()
    {
        acc_db = new AccountDatabase();

        trans_mang = new TransactionManager(acc_db);

        acc = new Account(1,"Jacob&Leon",1000);

        acc2 = new Account(2,"Jacob&Leon2",1000);

        // Reset Balance Before Each Test
        //acc.set_Account_Balance(100);

        //acc2.set_Account_Balance(1000);
    }


    //Account Class Tests
    @Test
    public void adjustBalanceNegativelyTest()
    {
        Assert.assertEquals(false, acc.adjustBalance(-3000));
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
        acc_db.addAccount(acc);
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
        acc_db.addAccount(acc);
        acc_db.addAccount(acc2);

        Assert.assertEquals(2,acc_db.getSize());
    }

    //Transaction Class Tests

    //sufficient funds for transaction
    @Test
    public void processTestA()
    {
        acc_db.addAccount(acc);
        acc_db.addAccount(acc2);

        //pass transaction
        trans = new Transaction(acc_db,acc.get_Account_Number(),acc2.get_Account_Number(),300);

        Assert.assertEquals(true,trans.process());
    }

    //insufficient funds for transaction
    @Test
    public void processTestB()
    {
        acc_db.addAccount(acc);
        acc_db.addAccount(acc2);

        //failed transaction
        trans = new Transaction(acc_db,acc.get_Account_Number(),acc2.get_Account_Number(),2000);

        Assert.assertEquals(false,trans.process());
    }

    //Case Transaction Object is created with non existent Account, with Account Number 5
    @Test
    public void processTestC()
    {
        acc_db.addAccount(acc);
        acc_db.addAccount(acc2);

        //failed transaction
        trans = new Transaction(acc_db,acc.get_Account_Number(),5,10);

        Assert.assertEquals(false,trans.process());
    }

    //TransactionManager Class Tests

    //insufficient funds for transaction
    @Test
    public void processTransactionManagerCaseA()
    {
        acc_db.addAccount(acc);
        acc_db.addAccount(acc2);

        Assert.assertEquals(false,trans_mang.processTransaction(acc.get_Account_Number(),acc2.get_Account_Number(),2000));
    }

    //sufficient funds for transaction
    @Test
    public void processTransactionManagerCaseB()
    {
        acc_db.addAccount(acc);
        acc_db.addAccount(acc2);

        Assert.assertEquals(true,trans_mang.processTransaction(acc.get_Account_Number(),acc2.get_Account_Number(),30));
    }

    //15 seconds Test Fail since "acc2",i.e.the destination account, previously made a transaction
    @Test
    public void processTransactionManagerCaseC()
    {
        acc_db.addAccount(acc);
        acc_db.addAccount(acc2);

        acc3 = new Account(3,"Jacob&Leon3",2000);
        acc_db.addAccount(acc3);


        trans_mang.processTransaction(acc.get_Account_Number(), acc2.get_Account_Number(), 30);

        Assert.assertEquals(false,trans_mang.processTransaction(acc3.get_Account_Number(),acc2.get_Account_Number(),30));
    }

    //15 seconds Test Fail since "acc",i.e.the source account, previously made a transaction
    @Test
    public void processTransactionManagerCaseD()
    {
        acc_db.addAccount(acc);
        acc_db.addAccount(acc2);

        acc3 = new Account(3,"Jacob&Leon3",2000);
        acc_db.addAccount(acc3);

        trans_mang.processTransaction(acc.get_Account_Number(), acc2.get_Account_Number(), 30);

        Assert.assertEquals(false,trans_mang.processTransaction(acc.get_Account_Number(),acc3.get_Account_Number(),30));
    }

    //15 seconds Test Pass
    @Test
    public void processTransactionManagerCaseE() throws InterruptedException
    {
        acc_db.addAccount(acc);
        acc_db.addAccount(acc2);
        trans_mang.processTransaction(acc.get_Account_Number(),acc2.get_Account_Number(),30);

        Thread.sleep(15000);

        Assert.assertEquals(true,trans_mang.processTransaction(acc.get_Account_Number(),acc2.get_Account_Number(),30));
    }


    //Additional getters Test
    @Test
    public void getAccountNameTest()
    {
        Assert.assertEquals("Jacob&Leon",acc.get_Account_Name());
    }



    // -----------------------------------------Assignment Part 2-----------------------------------------------

    //Compound Transaction Class Tests


    // AddTransaction Method Test
    @Test
    public void CompoundTransaction_AddTransactionTest()
    {
        ct = new CompoundTransaction("compound");

        acc_db.addAccount(acc);
        acc_db.addAccount(acc2);

        ct.addTransaction(new Transaction("atomic1", acc_db,acc.get_Account_Number(),acc2.get_Account_Number(),10));

        ct.addTransaction(new Transaction("atomic2",acc_db,acc.get_Account_Number(),acc2.get_Account_Number(),40));

        Assert.assertEquals(2, ct.getTransaction_list().size());
    }


    //process Method Test A
    //Non-Empty Compound Transaction
    @Test
    public void CompoundTransaction_process_TestA() throws InterruptedException
    {
        ct = new CompoundTransaction("compound");

        acc_db.addAccount(acc);
        acc_db.addAccount(acc2);

        ct.addTransaction(new Transaction("atomic1", acc_db,acc.get_Account_Number(),acc2.get_Account_Number(),10));

        ct.addTransaction(new Transaction("atomic2",acc_db,acc.get_Account_Number(),acc2.get_Account_Number(),40));

        Assert.assertEquals(true, ct.process());
    }


    //process Method Test B
    //Empty Compound transaction

    @Test
    public void CompoundTransaction_process_TestB() throws InterruptedException
    {
        ct = new CompoundTransaction("compound");

        Assert.assertEquals(false, ct.process());
    }




    // Transaction Manager Class Tests

    // processCompoundTransaction Method Test A
    // Successful Compound Transaction
    @Test
    public void processCompoundTransaction_TestA() throws InterruptedException {
        ct = new CompoundTransaction("compound");

        acc_db.addAccount(acc);
        acc_db.addAccount(acc2);

        ct.addTransaction(new Transaction("atomic1", acc_db,acc.get_Account_Number(),acc2.get_Account_Number(),10));

        ct.addTransaction(new Transaction("atomic2",acc_db,acc.get_Account_Number(),acc2.get_Account_Number(),40));

        Assert.assertEquals(true, trans_mang.processCompoundTransaction(ct));
    }


    // processCompoundTransaction Method Test B
    // Single Unsuccessful Transaction that fails the Compound Transaction
    @Test
    public void processCompoundTransaction_TestB() throws InterruptedException
    {
        ct = new CompoundTransaction("compound");

        acc_db.addAccount(acc);
        acc_db.addAccount(acc2);

        ct.addTransaction(new Transaction("atomic1", acc_db,acc.get_Account_Number(),acc2.get_Account_Number(),10001));

        ct.addTransaction(new Transaction("atomic2",acc_db,acc.get_Account_Number(),acc2.get_Account_Number(),40));

        Assert.assertEquals(false,trans_mang.processCompoundTransaction(ct));
    }

    // processCompoundTransaction Method Test C
    // Empty Compound Transaction List should fail the Compound Transaction
    @Test
    public void processCompoundTransaction_TestC() throws InterruptedException
    {
        ct = new CompoundTransaction("compound");

        Assert.assertEquals(false, trans_mang.processCompoundTransaction(ct));
    }


    //processCompoundTransaction Method Test D
    //Complex Nested Compound Transaction Test
    @Test
    public void processCompoundTransaction_TestD() throws InterruptedException {
        ct = new CompoundTransaction("compound1");
        ct1 = new CompoundTransaction("compound2");

        acc_db.addAccount(acc);
        acc_db.addAccount(acc2);

        ct.addTransaction(new Transaction("atomic1", acc_db,acc.get_Account_Number(),acc2.get_Account_Number(),100));
        ct.addTransaction(new Transaction("atomic2", acc_db,acc.get_Account_Number(),acc2.get_Account_Number(),100));

        ct1.addTransaction(new Transaction("atomic3",acc_db,acc.get_Account_Number(),acc2.get_Account_Number(),200));

        ct.addTransaction(ct1);

        Assert.assertEquals(true,trans_mang.processCompoundTransaction(ct));
    }

}
