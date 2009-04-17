package cgh.ieat;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.*;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.*;

import cgh.ieat.AppMenuToolbar;
import cgh.ieat.AppSort.SortType;

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

        // Create the content/search areass
        SashForm sashForm = new SashForm(shell, SWT.HORIZONTAL);
        sashForm.setOrientation(256);
        gridData = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
        gridData.horizontalSpan = 3;
        sashForm.setLayoutData(gridData);
        createSortView(sashForm, display);
        createTableView(sashForm);

        sashForm.setWeights(new int[]
            {
                3, 10
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

    // The two views of the main window
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
    

    private static void createSortView(Composite parent, Display display)
    {
        Composite composite = new Composite(parent, 0);
        GridLayout gridLayout = new GridLayout();
        gridLayout.numColumns = 1;
        gridLayout.marginHeight = gridLayout.marginWidth = 2;
        gridLayout.horizontalSpacing = gridLayout.verticalSpacing = 0;
        composite.setLayout(gridLayout);

        AppSort.createSortFilter(composite, display);
    }
    
    public static void resort(SortType t, Composite shell)
    {
        //FIXME to implement sorting
        System.err.println(t);
    }
}