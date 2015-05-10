package com.LeonAndJacob.app;

import java.util.*;

/**
 * Created by jacobfalzon on 10/05/15.
 */
public class RiskPresets
{
    String risk_name="";
    int depSrcAccount;
    int mainSrcAccount;
    int commSrcAccount;
    int commDestAccount;
    double comm_percentage;

    public RiskPresets(String risk)
    {
        if(risk.equalsIgnoreCase("high"))
        {
            this.risk_name =risk.toLowerCase();
            this.depSrcAccount = 3123;
            this.mainSrcAccount = 3143;
            this.commSrcAccount = 6565;
            this.commDestAccount = 4444;
            this.comm_percentage = 0.1;
        }
        else if(risk.equalsIgnoreCase("low"))
        {
            this.risk_name =risk.toLowerCase();
            this.depSrcAccount = 8665;
            this.mainSrcAccount = 3133;
            this.commSrcAccount = 6588;
            this.commDestAccount = 4445;
            this.comm_percentage = 0.05;
        }
    }

    public RiskPresets()
    {
        ;
    }

    public boolean CustomRiskPreset(String risk_in, int depSrcAccount_in, int mainSrcAccount_in, int commSrcAccount_in,int commDestAccount_in,double comm_percentage_in)
    {
        //conflicting risk!
        if(risk_in.equalsIgnoreCase("high") || risk_in.equalsIgnoreCase("low"))
        {
            return false;
        }

        this.risk_name = risk_in;
        this.depSrcAccount = depSrcAccount_in;
        this.mainSrcAccount = mainSrcAccount_in;
        this.commSrcAccount = commSrcAccount_in;
        this.commDestAccount = commDestAccount_in;
        this.comm_percentage = comm_percentage_in;

        return true;
    }

}
