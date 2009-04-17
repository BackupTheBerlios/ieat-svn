package cgh.ieat;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.*;

public class AppSort
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
        label.setImage(display.getSystemImage(SWT.ICON_ERROR));
        label = new Label (composite, SWT.NONE);
        label.setText("SWT.ICON_ERROR");
        label = new Label (composite, SWT.NONE);
        label.setImage(display.getSystemImage(SWT.ICON_INFORMATION));
        label = new Label (composite, SWT.NONE);
        label.setText("SWT.ICON_INFORMATION");
        label = new Label (composite, SWT.NONE);
        label.setImage(display.getSystemImage(SWT.ICON_WARNING));
        label = new Label (composite, SWT.NONE);
        label.setText("SWT.ICON_WARNING");
        label = new Label (composite, SWT.NONE);
        label.setImage(display.getSystemImage(SWT.ICON_QUESTION));
        label = new Label (composite, SWT.NONE);
        label.setText("SWT.ICON_QUESTION");
        ExpandItem item1 = new ExpandItem (bar, SWT.NONE, 1);
        item1.setText("Filter Options                         ");
        item1.setHeight(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
        item1.setControl(composite);
        item1.setImage(image);
               
        item0.setExpanded(true);
        item1.setExpanded(true);
        
        bar.setSpacing(8);
    }
}
