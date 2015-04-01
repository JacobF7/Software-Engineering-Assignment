package com.LeonAndJacob.app;

/**
 * Created by jacobfalzon on 01/04/15.
 */
public class LogItem
{
    private int sourceAccountNumber;

    private int destinationAccountNumber;

    private long Transaction_Time ;

    public LogItem(int sourceAccountNumber_In, int destinationAccountNumber_In, long Transaction_Time_In)
    {
        this.sourceAccountNumber = sourceAccountNumber_In;

        this.destinationAccountNumber=destinationAccountNumber_In;

       this.Transaction_Time = Transaction_Time_In ;
    }

    public void set_sourceAccountNumber(int sourceAccountNumber_In)
    {
        this.sourceAccountNumber=sourceAccountNumber_In;
    }

    public void  set_destinationAccountNumber(int destinationAccountNumber_In)
    {
        this.destinationAccountNumber=destinationAccountNumber_In;
    }

    public  void set_Transaction_Time(long Transaction_Time_in)
    {
        this.Transaction_Time= Transaction_Time_in;
    }

    public int get_sourceAccountNumber()
    {
        return this.sourceAccountNumber;
    }

    public int  get_destinationAccountNumber()
    {
        return this.destinationAccountNumber;
    }

    public long get_Transaction_Time()
    {
        return this.Transaction_Time;
    }

}
