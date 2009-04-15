package cgh.util;

public class MutableInteger
{
    private int i;

    public MutableInteger()
    {
        this.i = 0;
    }
    
    public MutableInteger(int i)
    {
        this.i = i;
    }
    
    public void increment()
    {
        this.i += 1;
    }
    
    public void decrement()
    {
        this.i -= 1;
    }
    
    public int getValue()
    {
        return this.i;
    }
    
    public void setValue(int x)
    {
        this.i = x;
    }
    
    public String toString()
    {
        return Integer.toString(this.i);
    }

    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + i;
        return result;
    }

    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final MutableInteger other = (MutableInteger) obj;
        if (i != other.i)
            return false;
        return true;
    }
    
    
}
