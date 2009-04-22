package cgh.ieat.model;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import cgh.util.Utilities;

public class Recipe implements Serializable
{
    private static final long serialVersionUID = 1L;
    public static final String[] headers = new String[]
        {
            "Recipe Name", "MealType", "Main Ingredient",
            "Tags", "Num Ingredients", "Num Instructions",
            "Last Updated"
        };
    
    public static enum MealType {
        DINNER("Dinner"),
        LUNCH("Lunch"),
        BREAKFAST("Breakfast"),
        APPITIZER("Appitizer"),
        SNACK("Snack"),
        SOUP("Soup"),
        SALAD("Salad"),
        DESERT("Desert");

        private final String display;
        MealType(String x)
        {
            this.display = x;
        }
        public String display() { return this.display; }
    }

    private String name;
    private ArrayList<String> tags = new ArrayList<String>();
    private MealType mealType;
    private ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
    private Ingredient mainIngredient;
    private ArrayList<String> instructions = new ArrayList<String>();
    private Date lastUpdate;
    
    public Recipe(String name, ArrayList<String> tags, MealType mealType,
        ArrayList<Ingredient> ingredients, Ingredient mainIngredient,
        ArrayList<String> instructions)
    {
        super();
        this.name = name;
        this.tags = tags;
        this.mealType = mealType;
        this.ingredients = ingredients;
        this.mainIngredient = mainIngredient;
        this.instructions = instructions;
        this.lastUpdate = new Date();
    }
    public String[] values()
    {
        DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT);
        return new String[]
            {
                getName(), getMealType().display(), getMainIngredient().getItem(),
                getCSVTags(), Integer.toString(getIngredients().size()), 
                Integer.toString(getInstructions().size()),
                df.format(getLastUpdate())
            };
    }
    private ArrayList<String> getTokens(String s)
    {
        return new ArrayList<String>(Arrays.asList(s.split("\\s")));
    }
    public ArrayList<String> searchTokens()
    {
        ArrayList<String> s = new ArrayList<String>();
        
        s.addAll(getTokens(getName()));
        s.addAll(getTokens(getMealType().display()));
        s.addAll(getTokens(getMainIngredient().getAmount()));
        s.addAll(getTokens(getMainIngredient().getItem()));
        DateFormat df = DateFormat.getDateInstance(DateFormat.LONG);
        s.addAll(getTokens(df.format(getLastUpdate())));
        for (String t : getTags())
            s.addAll(getTokens(t));
        for (String t : getInstructions())
            s.addAll(getTokens(t));
        for (Ingredient t : getIngredients())
        {
            s.addAll(getTokens(t.getAmount()));
            s.addAll(getTokens(t.getItem()));
        }
        
        return s;
    }
    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result
            + ((ingredients == null) ? 0 : ingredients.hashCode());
        result = prime * result
            + ((instructions == null) ? 0 : instructions.hashCode());
        result = prime * result
            + ((lastUpdate == null) ? 0 : lastUpdate.hashCode());
        result = prime * result
            + ((mainIngredient == null) ? 0 : mainIngredient.hashCode());
        result = prime * result
            + ((mealType == null) ? 0 : mealType.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((tags == null) ? 0 : tags.hashCode());
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
        Recipe other = (Recipe) obj;
        if (ingredients == null)
        {
            if (other.ingredients != null)
                return false;
        }
        else if (!ingredients.equals(other.ingredients))
            return false;
        if (instructions == null)
        {
            if (other.instructions != null)
                return false;
        }
        else if (!instructions.equals(other.instructions))
            return false;
        if (lastUpdate == null)
        {
            if (other.lastUpdate != null)
                return false;
        }
        else if (!lastUpdate.equals(other.lastUpdate))
            return false;
        if (mainIngredient == null)
        {
            if (other.mainIngredient != null)
                return false;
        }
        else if (!mainIngredient.equals(other.mainIngredient))
            return false;
        if (mealType == null)
        {
            if (other.mealType != null)
                return false;
        }
        else if (!mealType.equals(other.mealType))
            return false;
        if (name == null)
        {
            if (other.name != null)
                return false;
        }
        else if (!name.equals(other.name))
            return false;
        if (tags == null)
        {
            if (other.tags != null)
                return false;
        }
        else if (!tags.equals(other.tags))
            return false;
        return true;
    }
    public String getName()
    {
        return name;
    }
    public ArrayList<String> getTags()
    {
        return tags;
    }
    public MealType getMealType()
    {
        return mealType;
    }
    public ArrayList<Ingredient> getIngredients()
    {
        return ingredients;
    }
    public Ingredient getMainIngredient()
    {
        return mainIngredient;
    }
    public ArrayList<String> getInstructions()
    {
        return instructions;
    }
    public Date getLastUpdate()
    {
        return lastUpdate;
    }
    
    public String getCSVTags()
    {
        return Utilities.createCSVString(tags.toArray(new String[tags.size()]), false);
    }
}
