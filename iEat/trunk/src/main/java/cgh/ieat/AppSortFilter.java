package cgh.ieat;

import java.util.ArrayList;
import java.util.HashSet;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.*;

import cgh.ieat.model.Ingredient;
import cgh.ieat.model.Recipe;
import cgh.ieat.model.Recipe.MealType;

public class AppSortFilter
{
    public static enum SortType {
        RECIPE("Recipe Name"),
        DATE("Last Updated"),
        MEAL_TYPE("Meal Type"),
        TAGS("Tags"),
        NUM_ING("Number Ingredients"),
        NUM_INST("Number Instructions"),
        MAIN_INGRED("Main Ingredient");

        private final String display;
        SortType(String x)
        {
            this.display = x;
        }
        public String display() { return this.display; }
        public int getCol(){
            switch(this) {
                case RECIPE:   return 0;
                case MEAL_TYPE:  return 1;
                case MAIN_INGRED:  return 2;
                case TAGS: return 3;
                case NUM_ING:  return 4;
                case NUM_INST:  return 5;
                case DATE:  return 6;
            }
            return -1;
        }
    }
    
    public static enum FilterType {
        INGRED("By Ingredient: "),
        MEAL_TYPE("By Meal Type: "),
        MAIN_INGRED("By Main Ingredient: "),
        MONTH_ADDED("By Month Added: ");

        private final String display;
        FilterType(String x)
        {
            this.display = x;
        }
        public String display() { return this.display; }
        public boolean matches(Recipe r, String filterText)
        {
            switch(this) {
                case INGRED:
                    {
                        for (Ingredient i : r.getIngredients())
                            if (i.getItem().equals(filterText))
                                return true;
                    }
                case MEAL_TYPE:  return r.getMealType().display().equals(filterText);
                case MAIN_INGRED:  return r.getMainIngredient().getItem().equals(filterText);
                case MONTH_ADDED: return filterText.startsWith(Integer.toString(r.getLastUpdate().getMonth()));
            }
            return false;
        }
    }
    
    protected static final String[] types = new String[] {MealType.DESERT.display(), MealType.LUNCH.display(), 
                    MealType.BREAKFAST.display(), MealType.APPITIZER.display(), MealType.SNACK.display(), 
                    MealType.SOUP.display(), MealType.SALAD.display(), MealType.DESERT.display()};
    protected static final String[] month = new String[] {"01 - Jan", "02 - Feb", "03 - Mar", "04 - Apr",
                                                            "05 - May", "06 - Jun", "07 - Jul", "08 - Aug",
                                                            "09 - Sep", "10 - Oct", "11 - Nov", "12 - Dec"};
    
    protected static void createSortFilter(final Composite shell, Display display)
    {
        ExpandBar bar = new ExpandBar (shell, SWT.V_SCROLL);
        Image image = display.getSystemImage(SWT.ICON_INFORMATION);
        
        // First item
        Composite composite = new Composite (bar, SWT.NONE);
        GridLayout layout = new GridLayout ();
        layout.marginLeft = layout.marginTop = layout.marginRight = layout.marginBottom = 10;
        layout.verticalSpacing = 10;
        composite.setLayout(layout);
        ArrayList<Button> buttons = new ArrayList<Button>();
        Button button1 = new Button (composite, SWT.RADIO);
        button1.setText(SortType.RECIPE.display());
        button1.addListener(SWT.Selection, new Listener(){
            public void handleEvent(Event event)
            {
                if (((Button)event.widget).getSelection())
                    App.resort(SortType.RECIPE, shell);
            }
        });
        buttons.add(button1);
        Button button3 = new Button (composite, SWT.RADIO);
        button3.setText(SortType.MEAL_TYPE.display());
        button3.addListener(SWT.Selection, new Listener(){
            public void handleEvent(Event event)
            {
                if (((Button)event.widget).getSelection())
                    App.resort(SortType.MEAL_TYPE, shell);
            }
        });
        buttons.add(button3);
        Button button4 = new Button (composite, SWT.RADIO);
        button4.setText(SortType.MAIN_INGRED.display());
        button4.addListener(SWT.Selection, new Listener(){
            public void handleEvent(Event event)
            {
                if (((Button)event.widget).getSelection())
                    App.resort(SortType.MAIN_INGRED, shell);
            }
        });
        buttons.add(button4);
        Button button5 = new Button (composite, SWT.RADIO);
        button5.setText(SortType.TAGS.display());
        button5.addListener(SWT.Selection, new Listener(){
            public void handleEvent(Event event)
            {
                if (((Button)event.widget).getSelection())
                    App.resort(SortType.TAGS, shell);
            }
        });
        buttons.add(button5);
        Button button6 = new Button (composite, SWT.RADIO);
        button6.setText(SortType.NUM_ING.display());
        button6.addListener(SWT.Selection, new Listener(){
            public void handleEvent(Event event)
            {
                if (((Button)event.widget).getSelection())
                    App.resort(SortType.NUM_ING, shell);
            }
        });
        buttons.add(button6);
        Button button7 = new Button (composite, SWT.RADIO);
        button7.setText(SortType.NUM_INST.display());
        button7.addListener(SWT.Selection, new Listener(){
            public void handleEvent(Event event)
            {
                if (((Button)event.widget).getSelection())
                    App.resort(SortType.NUM_INST, shell);
            }
        });
        buttons.add(button7);
        Button button2 = new Button (composite, SWT.RADIO);
        button2.setText(SortType.DATE.display());
        button2.addListener(SWT.Selection, new Listener(){
            public void handleEvent(Event event)
            {
                if (((Button)event.widget).getSelection())
                    App.resort(SortType.DATE, shell);
            }
        });
        buttons.add(button2);
        ExpandItem item0 = new ExpandItem (bar, SWT.NONE, 0);
        item0.setText("Sorting Options");
        item0.setHeight(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
        item0.setControl(composite);
        item0.setImage(image);
        
        // Second item
        final ArrayList<Combo> combos = new ArrayList<Combo>();
        composite = new Composite (bar, SWT.NONE);
        layout = new GridLayout (2, false);
        layout.marginLeft = layout.marginTop = layout.marginRight = layout.marginBottom = 10;
        layout.verticalSpacing = 10;
        composite.setLayout(layout);
        Label label = new Label (composite, SWT.NONE);
        label.setText(FilterType.MEAL_TYPE.display());
        Combo combo = new Combo (composite, SWT.READ_ONLY);
        combos.add(combo);
        combo.setItems (types);
        combo.addFocusListener(new FocusListener(){

            public void focusGained(FocusEvent e)
            {
                // Do refresh
                ((Combo)e.widget).setItems(types);
            }

            public void focusLost(FocusEvent e)
            { } // Do nothing 
        });
        combo.addSelectionListener(new SelectionAdapter()
        {
            public void widgetSelected(SelectionEvent e)
            {
                int selection  = ((Combo)e.widget).getSelectionIndex();
                App.refilter(FilterType.MEAL_TYPE, types[selection]);
            }

            public void widgetDefaultSelected(SelectionEvent e)
            { } // Do nothing
        });
        label = new Label (composite, SWT.NONE);
        label.setText(FilterType.MAIN_INGRED.display());
        mainCombo = new Combo (composite, SWT.READ_ONLY);
        combos.add(mainCombo);
        mainCombo.addSelectionListener(new SelectionAdapter()
        {
            public void widgetSelected(SelectionEvent e)
            {
                int selection  = ((Combo)e.widget).getSelectionIndex();
                App.refilter(FilterType.MAIN_INGRED, ((Combo)e.widget).getItem(selection));
            }

            public void widgetDefaultSelected(SelectionEvent e)
            { } // Do nothing
        });
        label = new Label (composite, SWT.NONE);
        label.setText(FilterType.INGRED.display());
        ingrCombo = new Combo (composite, SWT.READ_ONLY);
        combos.add(ingrCombo);
        ingrCombo.addSelectionListener(new SelectionAdapter()
        {
            public void widgetSelected(SelectionEvent e)
            {
                int selection  = ((Combo)e.widget).getSelectionIndex();
                App.refilter(FilterType.INGRED, ((Combo)e.widget).getItem(selection));
            }

            public void widgetDefaultSelected(SelectionEvent e)
            { } // Do nothing
        });
        doUpdate();
        label = new Label (composite, SWT.NONE);
        label.setText(FilterType.MONTH_ADDED.display());
        combo = new Combo (composite, SWT.READ_ONLY);
        combos.add(combo);
        combo.setItems (month);
        combo.addFocusListener(new FocusListener(){

            public void focusGained(FocusEvent e)
            {
                // Do refresh
                ((Combo)e.widget).setItems(month);
            }

            public void focusLost(FocusEvent e)
            { } // Do nothing 
        });
        combo.addSelectionListener(new SelectionAdapter()
        {
            public void widgetSelected(SelectionEvent e)
            {
                int selection  = ((Combo)e.widget).getSelectionIndex();
                App.refilter(FilterType.MONTH_ADDED, month[selection]);
            }

            public void widgetDefaultSelected(SelectionEvent e)
            { } // Do nothing
        });
        ExpandItem item1 = new ExpandItem (bar, SWT.NONE, 1);
        item1.setText("Filter Options                         ");
        item1.setHeight(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
        item1.setControl(composite);
        item1.setImage(image);
        
        // Section 3
        composite = new Composite (bar, SWT.NONE);
        layout = new GridLayout (2, false);
        layout.marginLeft = layout.marginTop = layout.marginRight = layout.marginBottom = 10;
        layout.verticalSpacing = 10;
        composite.setLayout(layout);
        label = new Label (composite, SWT.NONE);
        label.setText("Number of Recipes: ");
        recipeCnt = new Label (composite, SWT.NONE);
        label = new Label (composite, SWT.NONE);
        label.setText("Last Recipe Entry: ");
        lastEntry = new Label (composite, SWT.NONE);
        recipeCnt.setText(App.getStats()[0]);
        lastEntry.setText(App.getStats()[1]);
        Button resetButtons = new Button (composite, SWT.PUSH);
        resetButtons.setText("Reset Recipe View");
        resetButtons.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
                // Reset Sort Filter Buttons
                for (Combo c : combos)
                    c.deselectAll();
                App.readData();
                App.setContent();
            }
        });
        ExpandItem item2 = new ExpandItem (bar, SWT.NONE, 2);
        item2.setText("iEat Status");
        item2.setHeight(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
        item2.setControl(composite);
        item2.setImage(image);
               
        item0.setExpanded(true);
        item1.setExpanded(true);
        item2.setExpanded(true);
        
        bar.setSpacing(8);
        App.shell.layout(true, true);
    }
    
    static Label recipeCnt;
    static Label lastEntry;
    static Combo mainCombo;
    static Combo ingrCombo;

    protected static void doUpdate()
    {
        if (recipeCnt != null)
            recipeCnt.setText(App.getStats()[0]);
        if (lastEntry != null)
            lastEntry.setText(App.getStats()[1]);
        
        // Grab all ingredients
        HashSet<String> existingIngr = new HashSet<String>();
        HashSet<String> existingMainIngr = new HashSet<String>();
        for (Recipe r : App.getRecipes())
            if (r != null)
            {
                for (Ingredient i : r.getIngredients())
                    existingIngr.add(i.getItem());
                existingMainIngr.add(r.getMainIngredient().getItem());
            }
        mainCombo.setItems (existingMainIngr.toArray(new String[existingMainIngr.size()]));
        ingrCombo.setItems (existingIngr.toArray(new String[existingIngr.size()]));
        
        App.shell.layout(true, true);
    }
}
