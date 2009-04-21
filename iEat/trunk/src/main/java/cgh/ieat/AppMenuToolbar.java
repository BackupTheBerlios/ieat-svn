package cgh.ieat;

import java.io.IOException;
import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTError;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import cgh.ieat.App;
import cgh.ieat.model.Recipe;

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
        MenuItem someItem = new MenuItem(menu, SWT.PUSH);
        someItem.setText("New Recipe...");
        someItem.addSelectionListener(new SelectionAdapter()
        {
            public void widgetSelected(SelectionEvent e)
            {
                RecipeEdit.createRecipe(null, shell);
            }
        });
        someItem = new MenuItem(menu, SWT.PUSH);
        someItem.setText("Edit Recipe...");
        someItem.addSelectionListener(new SelectionAdapter()
        {
            public void widgetSelected(SelectionEvent e)
            {
                if (App.getTable().getSelectionIndex() == -1)
                {
                    MessageBox box = new MessageBox(shell, SWT.ICON_ERROR);
                    box.setText("Error");
                    box.setMessage("No item selected!");
                    box.open();
                    return;
                }
                else
                {
                    Recipe r = App.getRecipes().get(App.getTable().getSelectionIndex());
                    RecipeEdit.createRecipe(r, shell);
                }
            }
        });
        someItem = new MenuItem(menu, SWT.PUSH);
        someItem.setText("Delete Recipe...");
        someItem.addSelectionListener(new SelectionAdapter()
        {
            public void widgetSelected(SelectionEvent e)
            {
                if (App.getTable().getSelectionIndex() == -1)
                {
                    MessageBox box = new MessageBox(shell, 34);
                    box.setText("Error");
                    box.setMessage("No item selected!");
                    box.open();
                    return;
                }
                else
                {
                    Recipe r = App.getRecipes().get(App.getTable().getSelectionIndex());
                    RecipeEdit.deleteRecipe(r, shell);
                }
            }
        });
        someItem = new MenuItem(menu, SWT.PUSH);
        someItem.setText("Print Recipe...");
        someItem.addSelectionListener(new SelectionAdapter()
        {
            public void widgetSelected(SelectionEvent e)
            {
                if (App.getTable().getSelectionIndex() == -1)
                {
                    MessageBox box = new MessageBox(shell, 34);
                    box.setText("Error");
                    box.setMessage("No item selected!");
                    box.open();
                    return;
                }
                else
                {
                    Recipe r = App.getRecipes().get(App.getTable().getSelectionIndex());
                    RecipeEdit.printRecipe(r, shell);
                }
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

    static protected void showHelp(Shell shell)
    {
        final Shell newShell = new Shell(shell.getDisplay());
        final Browser browser;
        try
        {
            browser = new Browser(newShell, SWT.NONE);
        }
        catch (SWTError ex)
        {
            System.out.println("Could not instantiate Browser: "
                + ex.getMessage());
            return;
        }
        newShell.setLayout(new FillLayout());
        String guide = "<HTML><HEAD><TITLE>HTML Test</TITLE></HEAD><BODY>"
            + "<P>Could not read user guide.</P>" + "</BODY></HTML>";
        try
        {
            guide = cgh.util.FileUtils.readFileAsString("userguide.htm");
        }
        catch (IOException e1)
        {
            e1.printStackTrace();
        }
        browser.setText(guide);
        newShell.open();
    }

    static protected void createHelpMenu(Menu parent, final Shell shell)
    {
        Menu menu = new Menu(parent);
        MenuItem header = new MenuItem(parent, 64);
        header.setText("Help");
        header.setMenu(menu);
        MenuItem item = new MenuItem(menu, 8);
        item.setText("Show User Guide...");
        item.addSelectionListener(new SelectionAdapter()
        {
            public void widgetSelected(SelectionEvent e)
            {
                showHelp(shell);
            }
        });
        item = new MenuItem(menu, 8);
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

        SelectionAdapter unimplementedListener = new SelectionAdapter()
        {
            public void widgetSelected(SelectionEvent e)
            {
                MessageBox box = new MessageBox(shell, 34);
                box.setText("Not Implemented");
                box.setMessage("Sorry this feature is not yet implemented :-(");
                box.open();
            }
        };

        // Add items
        ToolItem item = new ToolItem(toolBar, 2);
        item = new ToolItem(toolBar, 8);
        item.setImage(new Image(display, App.stockImageLocations[6]));
        item.setToolTipText("New Recipe");
        item.setText("New Recipe");
        item.addSelectionListener(new SelectionAdapter()
        {
            public void widgetSelected(SelectionEvent e)
            {
                RecipeEdit.createRecipe(null, shell);
            }
        });
        item = new ToolItem(toolBar, 8);
        item.setImage(new Image(display, App.stockImageLocations[13]));
        item.setToolTipText("Edit Recipe");
        item.setText("Edit Recipe");
        item.addSelectionListener(new SelectionAdapter()
        {
            public void widgetSelected(SelectionEvent e)
            {
                if (App.getTable().getSelectionIndex() == -1)
                {
                    MessageBox box = new MessageBox(shell, 34);
                    box.setText("Error");
                    box.setMessage("No item selected!");
                    box.open();
                    return;
                }
                else
                {
                    Recipe r = App.getRecipes().get(App.getTable().getSelectionIndex());
                    RecipeEdit.createRecipe(r, shell);
                }
            }
        });
        item = new ToolItem(toolBar, 8);
        item.setImage(new Image(display, App.stockImageLocations[8]));
        item.setToolTipText("Delete Recipe");
        item.setText("Delete Recipe");
        item.addSelectionListener(new SelectionAdapter()
        {
            public void widgetSelected(SelectionEvent e)
            {
                if (App.getTable().getSelectionIndex() == -1)
                {
                    MessageBox box = new MessageBox(shell, 34);
                    box.setText("Error");
                    box.setMessage("No item selected!");
                    box.open();
                    return;
                }
                else
                {
                    Recipe r = App.getRecipes().get(App.getTable().getSelectionIndex());
                    RecipeEdit.deleteRecipe(r, shell);
                }
            }
        });
        item = new ToolItem(toolBar, 2);
        item = new ToolItem(toolBar, 8);
        item.setImage(new Image(display, App.stockImageLocations[11]));
        item.setToolTipText("Print Recipe");
        item.setText("Print Recipe");
        item.addSelectionListener(new SelectionAdapter()
        {
            public void widgetSelected(SelectionEvent e)
            {
                if (App.getTable().getSelectionIndex() == -1)
                {
                    MessageBox box = new MessageBox(shell, 34);
                    box.setText("Error");
                    box.setMessage("No item selected!");
                    box.open();
                    return;
                }
                else
                {
                    Recipe r = App.getRecipes().get(App.getTable().getSelectionIndex());
                    RecipeEdit.printRecipe(r, shell);
                }
            }
        });
        item = new ToolItem(toolBar, 2);
        item = new ToolItem(toolBar, 8);
        item.setImage(display.getSystemImage(SWT.ICON_WARNING));
        item.setToolTipText("Backup");
        item.setText("Backup");
        item.addSelectionListener(unimplementedListener);
        item = new ToolItem(toolBar, 8);
        item.setImage(display.getSystemImage(SWT.ICON_QUESTION));
        item.setToolTipText("Help");
        item.setText("Help");
        item.addSelectionListener(new SelectionAdapter()
        {
            public void widgetSelected(SelectionEvent e)
            {
                showHelp(shell);
            }
        });
        item = new ToolItem(toolBar, 2);
        item = new ToolItem(toolBar, 8);
        item.setImage(display.getSystemImage(SWT.ICON_ERROR));
        item.setToolTipText("Quit");
        item.setText("Quit");
        item.addSelectionListener(new SelectionAdapter()
        {
            public void widgetSelected(SelectionEvent e)
            {
                shell.close();
            }
        });
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
