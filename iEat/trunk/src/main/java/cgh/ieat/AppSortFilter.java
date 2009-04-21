package cgh.ieat;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.*;

public class AppSortFilter
{
    public static enum SortType {
        RECIPE("Recipe Name"),
        DATE("Date Added"),
        MEAL_TYPE("Meal Type"),
        MAIN_INGRED("Main Ingredient");

        private final String display;
        SortType(String x)
        {
            this.display = x;
        }
        public String display() { return this.display; }
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
    }
    
    protected static final String[] types = new String[] {"apples", "oranges", "you", "me"};
    protected static final String[] mainIngred = new String[] {"apples", "oranges", "you", "me"};
    protected static final String[] ingred = new String[] {"apples", "oranges", "you", "me"};
    protected static final String[] month = new String[] {"apples", "oranges", "you", "me"};
    
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
        ExpandItem item0 = new ExpandItem (bar, SWT.NONE, 0);
        item0.setText("Sorting Options");
        item0.setHeight(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
        item0.setControl(composite);
        item0.setImage(image);
        
        // Second item
        composite = new Composite (bar, SWT.NONE);
        layout = new GridLayout (2, false);
        layout.marginLeft = layout.marginTop = layout.marginRight = layout.marginBottom = 10;
        layout.verticalSpacing = 10;
        composite.setLayout(layout);
        Label label = new Label (composite, SWT.NONE);
        label.setText(FilterType.MEAL_TYPE.display());
        Combo combo = new Combo (composite, SWT.READ_ONLY);
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
                App.refilter(FilterType.MEAL_TYPE, shell, types[selection]);
            }

            public void widgetDefaultSelected(SelectionEvent e)
            { } // Do nothing
        });
        label = new Label (composite, SWT.NONE);
        label.setText(FilterType.MAIN_INGRED.display());
        combo = new Combo (composite, SWT.READ_ONLY);
        combo.setItems (mainIngred);
        combo.addFocusListener(new FocusListener(){

            public void focusGained(FocusEvent e)
            {
                // Do refresh
                ((Combo)e.widget).setItems(mainIngred);
            }

            public void focusLost(FocusEvent e)
            { } // Do nothing 
        });
        combo.addSelectionListener(new SelectionAdapter()
        {
            public void widgetSelected(SelectionEvent e)
            {
                int selection  = ((Combo)e.widget).getSelectionIndex();
                App.refilter(FilterType.MAIN_INGRED, shell, mainIngred[selection]);
            }

            public void widgetDefaultSelected(SelectionEvent e)
            { } // Do nothing
        });
        label = new Label (composite, SWT.NONE);
        label.setText(FilterType.INGRED.display());
        combo = new Combo (composite, SWT.READ_ONLY);
        combo.setItems (ingred);
        combo.addFocusListener(new FocusListener(){

            public void focusGained(FocusEvent e)
            {
                // Do refresh
                ((Combo)e.widget).setItems(ingred);
            }

            public void focusLost(FocusEvent e)
            { } // Do nothing 
        });
        combo.addSelectionListener(new SelectionAdapter()
        {
            public void widgetSelected(SelectionEvent e)
            {
                int selection  = ((Combo)e.widget).getSelectionIndex();
                App.refilter(FilterType.INGRED, shell, ingred[selection]);
            }

            public void widgetDefaultSelected(SelectionEvent e)
            { } // Do nothing
        });
        label = new Label (composite, SWT.NONE);
        label.setText(FilterType.MONTH_ADDED.display());
        combo = new Combo (composite, SWT.READ_ONLY);
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
                App.refilter(FilterType.MONTH_ADDED, shell, month[selection]);
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
        Button refreshButtons = new Button (composite, SWT.PUSH);
        refreshButtons.setText("Refresh Stats");
        refreshButtons.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
                doUpdate();
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
    }
    
    static Label recipeCnt;
    static Label lastEntry;

    protected static void doUpdate()
    {
        if (recipeCnt != null)
            recipeCnt.setText(App.getStats()[0]);
        if (lastEntry != null)
            lastEntry.setText(App.getStats()[1]);
        App.shell.layout(true, true);        
    }
}
