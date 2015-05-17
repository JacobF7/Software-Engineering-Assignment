package com.LeonAndJacob.app;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jacobfalzon on 17/05/15.
 */
public class Iterator
{

    private List<Transaction> traversal_result;

    private int index =0;


    public Iterator(CompoundTransaction ct_in)
    {
        this.traversal_result = traverse(ct_in);
    }


    private List<Transaction> traverse(CompoundTransaction ct_in)
    {
        List<Transaction> result = new ArrayList<Transaction>();

        for(Transaction t_iterate: ct_in.getTransaction_list())
        {
            if (t_iterate instanceof CompoundTransaction)
            {
                CompoundTransaction ct_iterate = (CompoundTransaction) t_iterate;
                result.addAll(this.traverse(ct_iterate));
            }
            else
            {
                result.add(t_iterate);
            }
        }

        return result;

    }

    public Transaction next()
    {
        Transaction outcome = null;

        if(this.index<traversal_result.size())
        {
            outcome= this.traversal_result.get(this.index++);
        }

        return outcome;
    }

    public List<Transaction> getTraversal_result()
    {
        return traversal_result;
    }


}
