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
        int i;

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

    /*public void addAccount(int account_number_in, String account_name_in,int account_balance_in)
    {
        Scanner sc = new Scanner(System.in);

        boolean valid =true;

        int i;

        if(account_balance_in<0)
        {
            do
            {
                System.out.println("Invalid Balance! Re-input a Positive Balance");
                account_balance_in= sc.nextInt();

            }while(account_balance_in<0);
        }

        do
        {
           for (i=0; i<account_database.size();i++)
           {
               if(account_number_in==account_database.get(i).get_Account_Number())
               {
                   valid =false;
               }
           }

           if(valid)
           {
               Account to_add = new Account(account_number_in,account_name_in,account_balance_in);

               account_database.add(to_add);
           }
           else
           {
               System.out.println("Re-input another Account Number");
               account_number_in= sc.nextInt();
           }


        }while (valid==false);


    }*/

    public boolean addAccount(Account account_in)
    {
        Scanner sc = new Scanner(System.in);

        boolean valid=true;

        int i;

        if(account_in.get_Account_Balance()<0)
        {
            /*do
            {
                System.out.println("Invalid Balance! Re-input a Positive Balance");
                int altered_Balance =sc.nextInt();
                account_in.set_Account_Balance(altered_Balance);

            }while(account_in.get_Account_Balance()<0);*/

            valid =false;

        }

        //do
        //{
            for (i=0; i<account_database.size();i++)
            {
                if(account_in.get_Account_Number()==account_database.get(i).get_Account_Number())
                {
                    valid =false;
                }
            }

            if(valid==true)
            {
                account_database.add(account_in);
            }
            /*else
            {
                System.out.println("Re-input another Account Number");
                int altered_Number;
                altered_Number=sc.nextInt();
                account_in.set_Account_Number(altered_Number);
            }*/


       // }while (!valid);

        return valid;
    }

    public int getSize()
    {
        return account_database.size();
    }
}
