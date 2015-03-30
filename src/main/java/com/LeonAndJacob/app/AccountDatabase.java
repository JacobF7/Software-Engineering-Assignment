package com.LeonAndJacob.app;

import java.util.*;

/**
 * Created by jacobfalzon on 27/03/15.
 */
public class AccountDatabase
{
    public static ArrayList<Account> account_database = new ArrayList<Account>();


    public static Account getAccount(int accountNumber)
    {
        int i =0;

        Account returned = null;

       for(i =0; i<account_database.size(); i++)
       {
           if(accountNumber == account_database.get(i).get_Account_Number())
           {
               returned = account_database.get(i);
           }
       }

       return returned;

    }

    public int getSize()
    {
        return account_database.size();
    }
}
