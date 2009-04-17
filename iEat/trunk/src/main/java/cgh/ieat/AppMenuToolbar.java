package cgh.ieat;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import cgh.ieat.App;

// Create menu and toolbars
public class AppMenuToolbar
{
    private static ArrayList<String> history = new ArrayList<String>();

    static protected void createFileMenu(Menu parent, final Shell shell)
    {
        Menu menu = new Menu(parent);
        MenuItem header = new MenuItem(parent, 64);
        header.setText("File");
        header.setMenu(menu);
        final MenuItem someItem = new MenuItem(menu, SWT.PUSH);
        someItem.setText("Some Item");
        // someItem.setSelection(simulateOnly);
        someItem.addSelectionListener(new SelectionAdapter()
        {

            public void widgetSelected(SelectionEvent e)
            {
                System.err.println("Called menu");
                // simulateOnly = simulateItem.getSelection();
            }

        });
        MenuItem item = new MenuItem(menu, 8);
        item.setText("Exit");
        item.addSelectionListener(new SelectionAdapter()
        {

            public void widgetSelected(SelectionEvent e)
            {
                shell.close();
            }

        });
    }

    static protected void createHelpMenu(Menu parent, final Shell shell)
    {
        Menu menu = new Menu(parent);
        MenuItem header = new MenuItem(parent, 64);
        header.setText("Help");
        header.setMenu(menu);
        MenuItem item = new MenuItem(menu, 8);
        item.setText("About");
        item.addSelectionListener(new SelectionAdapter()
        {

            public void widgetSelected(SelectionEvent e)
            {
                MessageBox box = new MessageBox(shell, 34);
                box.setText("iEat v1.0");
                box.setMessage("by Christopher Howard\n" + "Running on: "
                    + System.getProperty("os.name"));
                box.open();
            }

        });
    }

    protected static void createToolBar(final Shell shell, Object layoutData,
        Display display)
    {
        // Define toolbar
        ToolBar toolBar = new ToolBar(shell, 0x800000);
        toolBar.setLayoutData(layoutData);
        
        // Add items
        ToolItem item = new ToolItem(toolBar, 2);
        item = new ToolItem(toolBar, 8);
        item.setImage(new Image(display, App.stockImageLocations[9]));
        item.setToolTipText("Blah");
        item.addSelectionListener(new SelectionAdapter()
        {

            public void widgetSelected(SelectionEvent e)
            {
                MessageBox box = new MessageBox(shell, 34);
                box.setText("1");
                box.setMessage("1");
                box.open();
            }

        });
        item = new ToolItem(toolBar, 8);
        item.setImage(new Image(display, App.stockImageLocations[12]));
        item.setToolTipText("Blah 2");
        item.addSelectionListener(new SelectionAdapter()
        {

            public void widgetSelected(SelectionEvent e)
            {
                MessageBox box = new MessageBox(shell, 34);
                box.setText("2");
                box.setMessage("2");
                box.open();
            }

        });
        SelectionAdapter unimplementedListener = new SelectionAdapter()
        {

            public void widgetSelected(SelectionEvent e)
            {
                MessageBox box = new MessageBox(shell, 34);
                box.setText("-1!!!!");
                box.setMessage("-1!!!!");
                box.open();
            }

        };
        item = new ToolItem(toolBar, 2);
        item = new ToolItem(toolBar, 8);
        item.setImage(new Image(display, App.stockImageLocations[7]));
        item.setToolTipText("Blah 3");
        item.addSelectionListener(unimplementedListener);
        item = new ToolItem(toolBar, 8);
        item.setImage(new Image(display, App.stockImageLocations[6]));
        item.setToolTipText("Blah 4");
        item.addSelectionListener(unimplementedListener);
        item = new ToolItem(toolBar, 8);
        item.setImage(new Image(display, App.stockImageLocations[10]));
        item.setToolTipText("Blah 5");
        item.addSelectionListener(unimplementedListener);
        item = new ToolItem(toolBar, 2);
        item = new ToolItem(toolBar, 8);
        item.setImage(new Image(display, App.stockImageLocations[8]));
        item.setToolTipText("Blah 6");
        item.addSelectionListener(unimplementedListener);
        item = new ToolItem(toolBar, 8);
        item.setImage(new Image(display, App.stockImageLocations[13]));
        item.setToolTipText("Blah 7");
        item.addSelectionListener(unimplementedListener);
        item = new ToolItem(toolBar, 2);
        item = new ToolItem(toolBar, 8);
        item.setImage(new Image(display, App.stockImageLocations[14]));
        item.setToolTipText("Blah 8");
        item.addSelectionListener(unimplementedListener);
        item = new ToolItem(toolBar, 8);
        item.setImage(new Image(display, App.stockImageLocations[11]));
        item.setToolTipText("Blah 9");
        item.addSelectionListener(unimplementedListener);
    }
    
    protected static void createSearchView(final Shell parent, Object layoutData)
    {
        final Combo combo = new Combo(parent, 0);
        combo.setLayoutData(layoutData);
        combo.setToolTipText("Search Recipes");
        combo.setText("Search Recipes");

        combo.addSelectionListener(new SelectionAdapter()
        {
            public void widgetSelected(SelectionEvent e)
            {
                int selection = combo.getSelectionIndex();
                String[] hist = (String[]) history.toArray(new String[history
                    .size()]);
                if (hist == null)
                    return;

                if (selection >= 0 && selection < hist.length)
                    doSearch(hist[selection]);
            }

            public void widgetDefaultSelected(SelectionEvent e)
            {
                String text = combo.getText();
                if (text == null)
                    return;
                history.add(text);
                doSearch(text);
            }

            public void doSearch(String text)
            {
                // FIXME Add search
                MessageBox box = new MessageBox(parent, 34);
                box.setText("Search");
                box.setMessage("Doing search of : " + text);
                box.open();
                doRefresh();
            }

            public void doRefresh()
            {
                combo.removeAll();
                for (String s : history)
                    combo.add(s);
            }
        });
    }
}
