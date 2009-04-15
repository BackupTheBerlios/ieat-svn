package cgh.ieat;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.*;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.*;

public class App
{
    final static int START_CNT = 4;

    public static void main(String[] args)
    {
        final Display display = new Display();
        final Image image = new Image(display, "iEat.gif");
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
        shell.setImage(new Image(display, "generic_example.gif"));

        // Menubar
        Menu bar = new Menu(shell, 2);
        shell.setMenuBar(bar);
        createFileMenu(bar, shell);
        createHelpMenu(bar, shell);

        // Create window
        GridLayout gridLayout = new GridLayout();
        gridLayout.numColumns = 3;
        gridLayout.marginHeight = gridLayout.marginWidth = 0;
        shell.setLayout(gridLayout);

        // GridData gridData = new GridData(256);
        // gridData.widthHint = 185;
        // createComboView(shell, gridData);

        // Make toolbar
        GridData gridData = new GridData(256);
        gridData.horizontalSpan = 2;
        createToolBar(shell, gridData, display);

        SashForm sashForm = new SashForm(shell, 0);
        sashForm.setOrientation(256);
        gridData = new GridData(1808);
        gridData.horizontalSpan = 3;
        sashForm.setLayoutData(gridData);
        createSortView(sashForm);
        createTableView(sashForm);
        
         sashForm.setWeights(new int[]
         {
         2, 5
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

    private static void createTableView(Composite parent)
    {
        Composite composite = new Composite(parent, 0);
        GridLayout gridLayout = new GridLayout();
        gridLayout.numColumns = 1;
        gridLayout.marginHeight = gridLayout.marginWidth = 2;
        gridLayout.horizontalSpacing = gridLayout.verticalSpacing = 0;
        composite.setLayout(gridLayout);
        Label tableContentsOfLabel = new Label(composite, 2048);
        tableContentsOfLabel.setLayoutData(new GridData(784));
        final Table table = new Table(composite, 0x10b02);
        table.setLayoutData(new GridData(1808));

        table.setHeaderVisible(true);
    }

    private static void createSortView(Composite parent)
    {
        Composite composite = new Composite(parent, 0);
        GridLayout gridLayout = new GridLayout();
        gridLayout.numColumns = 1;
        gridLayout.marginHeight = gridLayout.marginWidth = 2;
        gridLayout.horizontalSpacing = gridLayout.verticalSpacing = 0;
        composite.setLayout(gridLayout);
        Label treeScopeLabel = new Label(composite, 2048);
        treeScopeLabel.setText("Blah Tree");
        treeScopeLabel.setLayoutData(new GridData(784));
        final Tree tree = new Tree(composite, 2820);
        tree.setLayoutData(new GridData(1808));

    }

    static private void createFileMenu(Menu parent, final Shell shell)
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

    static private void createHelpMenu(Menu parent, final Shell shell)
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

    public static final String stockImageLocations[] =
        {
            "generic_example.gif", "icon_ClosedDrive.gif",
            "icon_ClosedFolder.gif", "icon_File.gif", "icon_OpenDrive.gif",
            "icon_OpenFolder.gif", "cmd_Copy.gif", "cmd_Cut.gif",
            "cmd_Delete.gif", "cmd_Parent.gif", "cmd_Paste.gif",
            "cmd_Print.gif", "cmd_Refresh.gif", "cmd_Rename.gif",
            "cmd_Search.gif"
        };

    private static void createToolBar(final Shell shell, Object layoutData,
        Display display)
    {
        ToolBar toolBar = new ToolBar(shell, 0);
        toolBar.setLayoutData(layoutData);
        ToolItem item = new ToolItem(toolBar, 2);
        item = new ToolItem(toolBar, 8);
        item.setImage(new Image(display, stockImageLocations[9]));
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
        item.setImage(new Image(display, stockImageLocations[12]));
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
        item.setImage(new Image(display, stockImageLocations[7]));
        item.setToolTipText("Blah 3");
        item.addSelectionListener(unimplementedListener);
        item = new ToolItem(toolBar, 8);
        item.setImage(new Image(display, stockImageLocations[6]));
        item.setToolTipText("Blah 4");
        item.addSelectionListener(unimplementedListener);
        item = new ToolItem(toolBar, 8);
        item.setImage(new Image(display, stockImageLocations[10]));
        item.setToolTipText("Blah 5");
        item.addSelectionListener(unimplementedListener);
        item = new ToolItem(toolBar, 2);
        item = new ToolItem(toolBar, 8);
        item.setImage(new Image(display, stockImageLocations[8]));
        item.setToolTipText("Blah 6");
        item.addSelectionListener(unimplementedListener);
        item = new ToolItem(toolBar, 8);
        item.setImage(new Image(display, stockImageLocations[13]));
        item.setToolTipText("Blah 7");
        item.addSelectionListener(unimplementedListener);
        item = new ToolItem(toolBar, 2);
        item = new ToolItem(toolBar, 8);
        item.setImage(new Image(display, stockImageLocations[14]));
        item.setToolTipText("Blah 8");
        item.addSelectionListener(unimplementedListener);
        item = new ToolItem(toolBar, 8);
        item.setImage(new Image(display, stockImageLocations[11]));
        item.setToolTipText("Blah 9");
        item.addSelectionListener(unimplementedListener);
    }
}