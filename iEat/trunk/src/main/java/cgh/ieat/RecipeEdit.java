package cgh.ieat;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.ShellEvent;
import org.eclipse.swt.events.ShellListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.*;

import cgh.ieat.model.Recipe;

public class RecipeEdit
{
    public static void createRecipe(Recipe r, final Shell shell)
    {
        // Setup new shell, disable old
        final Shell newShell = new Shell(shell.getDisplay());
        GridLayout gridLayout = new GridLayout();
        gridLayout.numColumns = 3;
        newShell.setLayout(gridLayout);
        newShell.setSize(800, 600);
        newShell.addShellListener(new ShellListener(){

            public void shellActivated(ShellEvent shellevent)
            {}

            public void shellClosed(ShellEvent shellevent)
            {
                shell.setEnabled(true);
            }

            public void shellDeactivated(ShellEvent shellevent)
            {}

            public void shellDeiconified(ShellEvent shellevent)
            {}

            public void shellIconified(ShellEvent shellevent)
            {}
        });
        shell.setEnabled(false);
        
        // Construct edit window
        createEditWin(r, newShell);
        newShell.open();
    }
    
    private static void createEditWin(Recipe r, final Shell shell)
    {
        FocusListener listener = new FocusListener() {

            public void focusGained(FocusEvent focusevent)
            {
                ((Text)focusevent.widget).selectAll();
            }

            public void focusLost(FocusEvent focusevent)
            { }
        };
        
        // Basic Information
        GridLayout gridLayout = new GridLayout();
        gridLayout.numColumns = 3;
        Group basic = new Group(shell, SWT.SHADOW_NONE);
        basic.setLayout(gridLayout);
        basic.setText("Basic Information");
        
        boolean newRecipe = (r == null);
        if (newRecipe)
        {
            shell.setText("Create New Recipe");
            Label l = new Label(basic, SWT.NONE);
            l.setText("Recipe Name: ");
            Text t = new Text(basic, SWT.BORDER | SWT.SINGLE);
            t.setText("Recipe Name                                            ");
            t.addFocusListener(listener);
        }
        else
            shell.setText("Edit Recipe - " + r.getName());

        // TODO Add more information
    }

    public static void deleteRecipe(Recipe r, Shell shell)
    {
        App.getRecipes().remove(r);
        App.setContent();
        MessageBox box = new MessageBox(shell, 34);
        box.setText("Delete Confirmation");
        box.setMessage("Deleted Recipe " + r.getName());
        box.open();
    }

    public static void printRecipe(Recipe r, Shell shell)
    {
        //FIXME Do printing
        MessageBox box = new MessageBox(shell, 34);
        box.setText("Print Confirmation");
        box.setMessage("Printing Recipe " + r.getName() + " cannot be done with this software version.");
        box.open();
    }
    
}
