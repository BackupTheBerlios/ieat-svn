package cgh.ieat.model;

public class Recipe
{
    public static final String[] headers = new String[]
        {
            "Name", "Name"
        };

    private int x;
    public Recipe(int x)
    {
        this.x = x;
    }
    public String[] values()
    {
        return new String[]
            {
                getName(), "XXX"
            };
    }

    public String getName()
    {
        return Integer.toString(this.x);
    }
}
