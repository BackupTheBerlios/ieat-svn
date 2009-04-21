package cgh.ieat;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Locale;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.*;

import cgh.ieat.AppMenuToolbar;
import cgh.ieat.AppSortFilter.FilterType;
import cgh.ieat.AppSortFilter.SortType;
import cgh.ieat.model.Recipe;

public class App
{
    final static int START_CNT = 1;

    public static final String stockImageLocations[] =
        {
            "generic_example.gif", // 0
            "icon_ClosedDrive.gif", "icon_ClosedFolder.gif", "icon_File.gif",
            "icon_OpenDrive.gif", // 4
            "icon_OpenFolder.gif", "cmd_Copy.gif", "cmd_Cut.gif", // 7
            "cmd_Delete.gif", "cmd_Parent.gif", // 9
            "cmd_Paste.gif", "cmd_Print.gif", // 11
            "cmd_Refresh.gif", "cmd_Rename.gif", "cmd_Search.gif", "iEat.gif" // 15
        };

    public static void main(String[] args)
    {
        final Display display = new Display();
        final Image image = new Image(display, stockImageLocations[15]);
        Shell shell;
        shell = new Shell(display);

        // Construct application
        createShellContents(shell, display);

        // Start up the app with a splash screen.
        SlashScreenAppStart
            .startAppWithSplash(shell, display, image, START_CNT);
    }

    public static void createShellContents(Shell shell, Display display)
    {
        shell.setText("iEat - Provided by CTech");
        shell.setImage(new Image(display, stockImageLocations[0]));

        // Menubar
        Menu bar = new Menu(shell, 2);
        shell.setMenuBar(bar);
        AppMenuToolbar.createFileMenu(bar, shell);
        AppMenuToolbar.createHelpMenu(bar, shell);

        // Create overall window
        GridLayout gridLayout = new GridLayout();
        gridLayout.numColumns = 3;
        gridLayout.marginHeight = gridLayout.marginWidth = 0;
        shell.setLayout(gridLayout);

        // Make toolbar
        GridData gridData = new GridData(256);
        gridData.widthHint = 185;
        AppMenuToolbar.createSearchView(shell, gridData);
        gridData = new GridData(256);
        gridData.horizontalSpan = 2;
        AppMenuToolbar.createToolBar(shell, gridData, display);

        // Create the content/search areas
        SashForm sashForm = new SashForm(shell, SWT.HORIZONTAL);
        sashForm.setOrientation(256);
        gridData = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
        gridData.horizontalSpan = 3;
        sashForm.setLayoutData(gridData);
        createSortView(sashForm, display);
        createTableView(sashForm);

        sashForm.setWeights(new int[]
            {
                5, 20
            });
        Label numObjectsLabel = new Label(shell, 2048);
        gridData = new GridData(784);
        gridData.widthHint = 185;
        numObjectsLabel.setLayoutData(gridData);
        Label diskSpaceLabel = new Label(shell, 2048);
        gridData = new GridData(784);
        gridData.horizontalSpan = 2;
        diskSpaceLabel.setLayoutData(gridData);
    }
    
    static Table table;
    static Label tableContentsOfLabel;
    static final ArrayList<Recipe> recipes = new ArrayList<Recipe>();
    
    protected static Table getTable()
    {
        return table;
    }
    
    protected static ArrayList<Recipe> getRecipes()
    {
        return recipes;
    }
    
    // The two views of the main window
    private static void createTableView(Composite parent)
    {
        Composite composite = new Composite(parent, 0);
        GridLayout gridLayout = new GridLayout();
        gridLayout.numColumns = 1;
        gridLayout.marginHeight = gridLayout.marginWidth = 2;
        gridLayout.horizontalSpacing = gridLayout.verticalSpacing = 0;
        composite.setLayout(gridLayout);
        
        tableContentsOfLabel = new Label(composite, 2048);
        tableContentsOfLabel.setLayoutData(new GridData(784));
        
        table = new Table (composite, SWT.MULTI | SWT.BORDER | SWT.FULL_SELECTION);
        table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        table.setHeaderVisible(true);
        table.setLinesVisible(true);
        
        // Setup headers
        for (int z = 0; z < Recipe.headers.length; z++) {
            TableColumn column = new TableColumn (table, SWT.NONE);
            column.setText(Recipe.headers[z]);
            column.addListener(SWT.Selection, new Listener() {
                public void handleEvent(Event e) {
                    TableItem[] items = table.getItems();
                    Collator collator = Collator.getInstance(Locale.getDefault());
                    TableColumn column = (TableColumn)e.widget;
                    int index = table.indexOf(column);
                    for (int i = 0; i < items.length; i++) {
                        String value1 = items[i].getText(index);
                        
                        for (int j = 0; j < i; j++){
                            String value2 = items[j].getText(index);
                            String[] values = new String[Recipe.headers.length];
                            for (int v = 0; v < Recipe.headers.length; v++)
                                values[v] = items[i].getText(v);
                            if (collator.compare(value1, value2) > 0) {
                                items[i].dispose();
                                TableItem item = new TableItem(table, SWT.NONE, j);
                                item.setText(values);
                                items = table.getItems();
                                table.setSortDirection(SWT.DOWN);
                                break;
                            }
                            else if (collator.compare(value1, value2) < 0) {
                                items[i].dispose();
                                TableItem item = new TableItem(table, SWT.NONE, j);
                                item.setText(values);
                                items = table.getItems();
                                table.setSortDirection(SWT.UP);
                                break;
                            }
                        }
                    }
                    table.setSortColumn(column);
                }
            });
        }
        
        // Initially display all the recipes
        setContent();
    }
    
    protected static void setContent()
    {
        table.setSelection(new TableItem[0]);
        table.setItemCount(0);
        for (int i = 0; i < recipes.size(); i++) {
            TableItem item = new TableItem (table, SWT.NONE);
            item.setText(recipes.get(i).values());
        }
        tableContentsOfLabel.setText("Number Recipes Displayed: " + table.getItemCount());
        
        // Must pack columns w/ to display
        for (int i = 0; i < Recipe.headers.length; i++) {
            table.getColumn(i).pack ();
        }
    }

    private static void createSortView(Composite parent, Display display)
    {
        Composite composite = new Composite(parent, 0);
        GridLayout gridLayout = new GridLayout();
        gridLayout.numColumns = 1;
        gridLayout.marginHeight = gridLayout.marginWidth = 2;
        gridLayout.horizontalSpacing = gridLayout.verticalSpacing = 0;
        composite.setLayout(gridLayout);

        AppSortFilter.createSortFilter(composite, display);
    }
    
    public static void resort(SortType t, Composite shell)
    {
        // YOu'll need to modify the recipes list and t hen call setContent()
        //FIXME to implement sorting
        System.err.println(t);
        // setContent(); Then call this on returned set!
    }
    
    public static void refilter(FilterType t, Composite shell, String filterText)
    {
        //FIXME to implement sorting
        System.err.println(t + " - " + filterText);
    }
}