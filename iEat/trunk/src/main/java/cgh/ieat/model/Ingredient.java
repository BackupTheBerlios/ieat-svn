package cgh.ieat.model;

import java.io.Serializable;

public class Ingredient implements Serializable
{
    private static final long serialVersionUID = 1L;
    private String amount;
    private String item;
    public Ingredient(String amount, String item)
    {
        super();
        this.amount = amount;
        this.item = item;
    }
    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((amount == null) ? 0 : amount.hashCode());
        result = prime * result + ((item == null) ? 0 : item.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Ingredient other = (Ingredient) obj;
        if (amount == null)
        {
            if (other.amount != null)
                return false;
        }
        else if (!amount.equals(other.amount))
            return false;
        if (item == null)
        {
            if (other.item != null)
                return false;
        }
        else if (!item.equals(other.item))
            return false;
        return true;
    }
    public String getAmount()
    {
        return amount;
    }
    public String getItem()
    {
        return item;
    }
}
