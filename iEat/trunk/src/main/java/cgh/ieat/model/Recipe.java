package cgh.ieat.model;

import java.io.Serializable;
import java.util.ArrayList;

import cgh.util.Utilities;

public class Recipe implements Serializable
{
    private static final long serialVersionUID = 1L;
    public static final String[] headers = new String[]
        {
            "Recipe Name", "MealType", "Main Ingredient",
            "Tags", "Num Ingredients", "Num Instructions"
        };
    
    public static enum MealType {
        DINNER("Dinner"),
        LUNCH("Lunch"),
        BREAKFAST("Breakfast"),
        APP("Appitizer"),
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
    
    //FIXME remove this!!!!
    public Recipe(int x)
    {
        this.name = Integer.toString(x);
        this.mealType = MealType.DESERT;
        this.mainIngredient = new Ingredient("1/4 cub", "poop");
    }
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
    }
    public String[] values()
    {
        return new String[]
            {
                getName(), getMealType().display(), getMainIngredient().getItem(),
                getCSVTags(), Integer.toString(getIngredients().size()), 
                Integer.toString(getInstructions().size())
            };
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
    
    public String getCSVTags()
    {
        return Utilities.createCSVString(tags.toArray(new String[tags.size()]), false);
    }
}
