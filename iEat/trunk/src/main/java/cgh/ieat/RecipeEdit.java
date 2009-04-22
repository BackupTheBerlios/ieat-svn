package cgh.ieat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.ShellEvent;
import org.eclipse.swt.events.ShellListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.*;

import cgh.ieat.model.Ingredient;
import cgh.ieat.model.Recipe;

public class RecipeEdit
{
    public static void createRecipe(Recipe r, final Shell shell)
    {
        // Setup new shell, disable old
        final Shell newShell = new Shell(shell.getDisplay());
        GridLayout gridLayout = new GridLayout();
        gridLayout.numColumns = 1;
        newShell.setLayout(gridLayout);
        newShell.setSize(800, 600);
        newShell.addShellListener(new ShellListener()
        {

            public void shellActivated(ShellEvent shellevent)
            {
            }

            public void shellClosed(ShellEvent shellevent)
            {
                shell.setEnabled(true);
            }

            public void shellDeactivated(ShellEvent shellevent)
            {
            }

            public void shellDeiconified(ShellEvent shellevent)
            {
            }

            public void shellIconified(ShellEvent shellevent)
            {
            }
        });
        shell.setEnabled(false);

        // Construct edit window
        createEditWin(r, newShell);
        newShell.open();
    }

    private static void createEditWin(final Recipe r, final Shell shell)
    {
        FocusListener initalSelectListener = new FocusListener()
        {

            public void focusGained(FocusEvent focusevent)
            {
                ((Text) focusevent.widget).selectAll();
            }

            public void focusLost(FocusEvent focusevent)
            {
            }
        };

        // Basic Information
        GridLayout gridLayout = new GridLayout();
        gridLayout.numColumns = 2;
        Group basic = new Group(shell, SWT.SHADOW_NONE);
        basic.setLayout(gridLayout);
        basic.setText("Basic Information");
        
        Label l = new Label(basic, SWT.NONE);
        final Text nameT = new Text(basic, SWT.BORDER | SWT.SINGLE);
        l.setText("Recipe Name: ");
        boolean newRecipe = (r == null);
        if (newRecipe)
        {
            shell.setText("Create New Recipe");
            nameT.setText("                                                                ");
            nameT.addFocusListener(initalSelectListener);
        }
        else
        {
            shell.setText("Edit Recipe - " + r.getName());
            nameT.setText(r.getName());
            nameT.setEditable(false);
        }

        // Tags
        ArrayList<String> tagList = (r == null || r.getTags() == null) ? 
            new ArrayList<String>() : r.getTags();
        l = new Label(basic, SWT.NONE);
        l.setText("Recipe Tags: ");
        gridLayout = new GridLayout();
        gridLayout.numColumns = 2;
        Group tags = new Group(basic, SWT.SHADOW_NONE);
        tags.setLayout(gridLayout);
        final List list = new List(tags, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL);
        for (String s : tagList)
            list.add(s);
        Composite butCtrl = new Composite(tags, SWT.SHADOW_NONE);
        butCtrl.setLayout(new GridLayout());
        Button b = new Button(butCtrl, SWT.PUSH);
        b.setText("Add...");
        b.addSelectionListener(new SelectionAdapter()
        {
            public void widgetSelected(SelectionEvent e)
            {
                final Shell newShell = new Shell(shell.getDisplay());
                GridLayout gridLayout = new GridLayout();
                gridLayout.numColumns = 2;
                newShell.setLayout(gridLayout);
                newShell.setSize(300, 150);
                newShell.addShellListener(new ShellListener()
                {
                    public void shellActivated(ShellEvent shellevent)
                    { }

                    public void shellClosed(ShellEvent shellevent)
                    {
                        shell.setEnabled(true);
                        shell.layout(true, true);
                    }

                    public void shellDeactivated(ShellEvent shellevent)
                    { }

                    public void shellDeiconified(ShellEvent shellevent)
                    { }

                    public void shellIconified(ShellEvent shellevent)
                    { }
                });
                shell.setEnabled(false);

                // Construct edit window
                createTagAdd(list, newShell);
                newShell.open();
            }
        });
        b = new Button(butCtrl, SWT.PUSH);
        b.setText("Remove Selcted Tag");
        b.addSelectionListener(new SelectionAdapter()
        {
            public void widgetSelected(SelectionEvent e)
            {
                int[] ind = list.getSelectionIndices();
                if (ind != null && ind.length != 0)
                {
                    MessageBox box = new MessageBox(shell, SWT.ICON_INFORMATION);
                    box.setText("Confirmation");
                    box.setMessage("Removing selected " + ind.length + " tags.");
                    box.open();
                    list.remove(ind);
                }
            }
        });
        
        // Meal Type
        l = new Label(basic, SWT.NONE);
        l.setText("Meal Type: ");
        final Combo mtCombo = new Combo(basic, SWT.READ_ONLY);
        for (Recipe.MealType type : Recipe.MealType.values())
            mtCombo.add(type.display());
        if (r != null && r.getMealType() != null)
            mtCombo.select(mtCombo.indexOf(r.getMealType().display()));
        
        // Main Ingredient
        final ArrayList<Ingredient> ing = (r == null || r.getIngredients() == null) ? 
            new ArrayList<Ingredient>() : r.getIngredients();
        final ArrayList<Button> ingBut = new ArrayList<Button>();
        l = new Label(basic, SWT.NONE);
        l.setText("Main Ingredient: ");
        final Combo ingredCombo = new Combo(basic, SWT.READ_ONLY);
        for (Ingredient i : ing)
            ingredCombo.add(i.getItem());
        if (r != null && r.getMainIngredient() != null)
            ingredCombo.select(ingredCombo.indexOf(r.getMainIngredient().getItem()));
        
        // Ingredients & Instructions
        Composite ii = new Composite(shell, SWT.NONE);
        gridLayout = new GridLayout();
        gridLayout.numColumns = 2;
        ii.setLayout(gridLayout);
        
        final Group ingredients = new Group(ii, SWT.SHADOW_NONE);
        ingredients.setLayout(new GridLayout());
        ingredients.setText("Ingredients");
        Group iEntry = new Group(ingredients, SWT.SHADOW_NONE);
        iEntry.setText("New Ingredient");
        iEntry.setLayout(gridLayout);
        l = new Label(iEntry, SWT.NONE);
        l.setText("Amount: ");
        final Text iAmount = new Text(iEntry, SWT.BORDER | SWT.SINGLE);
        l = new Label(iEntry, SWT.NONE);
        l.setText("Item: ");
        final Text iItem = new Text(iEntry, SWT.BORDER | SWT.SINGLE);
        l = new Label(iEntry, SWT.NONE);
        b = new Button(iEntry, SWT.PUSH);
        b.setText("Add Ingredient");
        final Group iIngred = new Group(ingredients, SWT.SHADOW_NONE);
        iIngred.setLayout(new GridLayout());
        Button addB = new Button(iIngred, SWT.PUSH);
        for (Ingredient i : ing)
        {
            Button bb = new Button(iIngred, SWT.CHECK);
            bb.setText(i.getAmount() + " " + i.getItem());
            ingBut.add(bb);
        }
        b.addSelectionListener(new SelectionAdapter()
        {
            public void widgetSelected(SelectionEvent e)
            {
                // Validate
                if (iAmount.getText() != null && !iAmount.getText().isEmpty()
                    && iItem.getText() != null && !iItem.getText().isEmpty())
                {
                    ing.add(new Ingredient(iAmount.getText(), iItem.getText()));
                    
                    // When you add a new ingredient you'll need to update the combo box
                    ingredCombo.add(iItem.getText());
                    
                    // Add to display list
                    Button newB = new Button(iIngred, SWT.CHECK);
                    newB.setText(iAmount.getText() + " " + iItem.getText());
                    ingBut.add(newB);
                    shell.layout(true, true);
                    iAmount.setText("");
                    iItem.setText("");
                }
                else
                {
                    MessageBox box = new MessageBox(shell, SWT.ICON_ERROR);
                    box.setText("Error");
                    box.setMessage("Either a blank amount or item was entered.");
                    box.open();
                    return;
                }
            }
        });
        addB.setText("Delete Checked Ingredients");
        addB.addSelectionListener(new SelectionAdapter()
        {
            public void widgetSelected(SelectionEvent e)
            {
                ArrayList<Integer> index = new ArrayList<Integer>();
                for(Button bt : ingBut)
                {
                    // If checked then delete
                    if (bt.getSelection())
                    {
                        index.add(ingBut.indexOf(bt));
                    }
                }
                for(int i : index)
                {
                    ing.remove(i);
                    ingBut.remove(i).dispose();
                    ingredCombo.remove(i);
                }
                shell.layout(true, true);
            }
        });

        // Instructions
        final ArrayList<Text> instrBut = new ArrayList<Text>();
        final ArrayList<Group> instrGrp = new ArrayList<Group>();
        Group instructions = new Group(ii, SWT.SHADOW_NONE);
        instructions.setLayout(new GridLayout());
        instructions.setText("Instructions");
        final Group iEntry2 = new Group(instructions, SWT.SHADOW_NONE);
        iEntry2.setText("New Instruction");
        iEntry2.setLayout(gridLayout);
        l = new Label(iEntry2, SWT.NONE);
        l.setText("Instruction: ");
        final Text iInstruction = new Text(iEntry2, SWT.BORDER | SWT.MULTI);
        iInstruction.addFocusListener(initalSelectListener);
        iInstruction.setText("                                                                                  ");
        l = new Label(iEntry2, SWT.NONE);
        b = new Button(iEntry2, SWT.PUSH);
        b.setText("Add Instruction");
        final Group iInstruct = new Group(instructions, SWT.SHADOW_NONE);
        iInstruct.setLayout(new GridLayout());
        final ArrayList<String> instr = (r == null || r.getInstructions() == null) ? 
            new ArrayList<String>() : r.getInstructions();
        for (String i : instr)
        {
            final Group ttG = new Group(iInstruct, SWT.SHADOW_NONE);
            ttG.setLayout(gridLayout);
            ttG.setText(Integer.toString(instr.indexOf(i)));
            final Text tt = new Text(ttG, SWT.BORDER | SWT.MULTI);
            tt.addModifyListener(new ModifyListener(){

                public void modifyText(ModifyEvent modifyevent)
                {
                    shell.layout(true, true);
                }});
            tt.setText(i);
            instrBut.add(tt);
            instrGrp.add(ttG);
            Button delB = new Button(ttG, SWT.PUSH);
            delB.setText("Delete");
            delB.addSelectionListener(new SelectionAdapter() {
                public void widgetSelected(SelectionEvent e)
                {
                    instrBut.remove(tt);
                    instrGrp.remove(ttG);
                    ttG.dispose();
                    shell.layout(true, true);
                    for(Group g : instrGrp)
                        g.setText(Integer.toString(instrGrp.indexOf(g)));
                }
            });
            shell.layout(true, true);
        }
        b.addSelectionListener(new SelectionAdapter()
        {
            public void widgetSelected(SelectionEvent e)
            {
                // Validate
                String xx = iInstruction.getText().trim();
                if (xx != null && !xx.isEmpty())
                {
                    final Group ttG = new Group(iInstruct, SWT.SHADOW_NONE);
                    GridLayout gridLayout = new GridLayout();
                    gridLayout.numColumns = 2;
                    ttG.setLayout(gridLayout);
                    final Text tt = new Text(ttG, SWT.BORDER | SWT.MULTI);
                    tt.addModifyListener(new ModifyListener(){

                        public void modifyText(ModifyEvent modifyevent)
                        {
                            shell.layout(true, true);
                        }});
                    tt.setText(xx);
                    instrBut.add(tt);
                    ttG.setText(Integer.toString(instrBut.indexOf(tt)));
                    instrGrp.add(ttG);
                    Button delB = new Button(ttG, SWT.PUSH);
                    delB.setText("Delete");
                    delB.addSelectionListener(new SelectionAdapter() {
                        public void widgetSelected(SelectionEvent e)
                        {
                            instrBut.remove(tt);
                            instrGrp.remove(ttG);
                            ttG.dispose();
                            shell.layout(true, true);
                            for(Group g : instrGrp)
                                g.setText(Integer.toString(instrGrp.indexOf(g)));
                        }
                    });
                    shell.layout(true, true);
                    iInstruction.setText("                                                                                  ");
                }
                else
                {
                    MessageBox box = new MessageBox(shell, SWT.ICON_ERROR);
                    box.setText("Error");
                    box.setMessage("No Instruction Entered");
                    box.open();
                    return;
                }
            }
        });
        
        b = new Button(shell, SWT.PUSH);
        b.setText("Save Recipe");
        b.addSelectionListener(new SelectionAdapter()
        {
            public void widgetSelected(SelectionEvent e)
            {
                String name;
                if (r != null)
                {
                    App.removeRecipe(r);
                    name = r.getName();
                }
                else
                    name = nameT.getText();
                name = name.trim();
                
                // Verifications
                if (name == null || name.isEmpty())
                {
                    MessageBox box = new MessageBox(shell, SWT.ICON_ERROR);
                    box.setText("Error");
                    box.setMessage("Invalid Name Entered");
                    box.open();
                    return;
                }
                ArrayList<String> instFinal = new ArrayList<String>();
                for (Text tF : instrBut)
                    instFinal.add(tF.getText().trim());
                if (instFinal.isEmpty())
                {
                    MessageBox box = new MessageBox(shell, SWT.ICON_ERROR);
                    box.setText("Error");
                    box.setMessage("No Instructions Entered");
                    box.open();
                    return;
                }
                
                Recipe.MealType mt = (mtCombo.getSelectionIndex() == -1) ? null : 
                    Recipe.MealType.valueOf(mtCombo.getItem(mtCombo.getSelectionIndex()).toUpperCase());
                if (mt == null)
                {
                    MessageBox box = new MessageBox(shell, SWT.ICON_ERROR);
                    box.setText("Error");
                    box.setMessage("No Meal Type Entered");
                    box.open();
                    return;
                }
                
                if (ingredCombo.getSelectionIndex() == -1)
                {
                    MessageBox box = new MessageBox(shell, SWT.ICON_ERROR);
                    box.setText("Error");
                    box.setMessage("No Main Ingredient Entered");
                    box.open();
                    return;
                }
                
                if (list.getItemCount() == 0)
                {
                    MessageBox box = new MessageBox(shell, SWT.ICON_ERROR);
                    box.setText("Error");
                    box.setMessage("No Search Tags Entered");
                    box.open();
                    return;
                }
                
                Recipe r2 = new Recipe(name, 
                    new ArrayList<String>(Arrays.asList(list.getItems())),
                    mt,
                    ing,
                    ing.get(ingredCombo.getSelectionIndex()),
                    instFinal);
                App.getRecipes().add(r2);
                AppSortFilter.doUpdate();
                App.saveData();
                shell.layout(true, true);
                shell.close();
                App.setContent();
            }
        });
    }

    public static void createTagAdd(final List list, final Shell shell)
    {
        shell.setText("Add Tag");

        // Get all tags
        HashSet<String> existingTags = new HashSet<String>();
        for (Recipe r : App.getRecipes())
            if (r != null)
                existingTags.addAll(r.getTags());

        final Button button1 = new Button(shell, SWT.RADIO);
        final Combo combo = new Combo(shell, SWT.READ_ONLY);
        final Button button2 = new Button(shell, SWT.RADIO);
        final Text t = new Text(shell, SWT.BORDER | SWT.SINGLE);
        combo.setEnabled(false);
        t.setEnabled(false);

        button1.setText("Add Existing Tag:");
        button1.addListener(SWT.Selection, new Listener()
        {
            public void handleEvent(Event event)
            {
                combo.setEnabled(true);
                t.setText("");
                t.setEnabled(false);
            }
        });
        combo.setItems(existingTags.toArray(new String[existingTags.size()]));
        button2.setText("Add New Tag:");
        button2.addListener(SWT.Selection, new Listener()
        {
            public void handleEvent(Event event)
            {
                combo.clearSelection();
                combo.setEnabled(false);
                t.setEnabled(true);
            }
        });
        Button b = new Button(shell, SWT.PUSH);
        b.setText("Add Tag");
        b.addSelectionListener(new SelectionAdapter()
        {
            public void widgetSelected(SelectionEvent e)
            {
                String newTag = null;
                if (button1.getSelection())
                {
                    int index = combo.getSelectionIndex();
                    newTag = (index == -1) ? null : combo.getItem(index);
                }
                if (button2.getSelection())
                    newTag = t.getText();

                // Validate response
                if (newTag == null || newTag.isEmpty())
                {
                    MessageBox box = new MessageBox(shell, SWT.ICON_ERROR);
                    box.setText("Error");
                    box.setMessage("No Tag Entered!");
                    box.open();
                    return;
                }

                list.add(newTag);
                shell.close();
            }
        });
    }

    public static void deleteRecipe(Recipe r, Shell shell)
    {
        App.removeRecipe(r);
        App.setContent();
        MessageBox box = new MessageBox(shell, 34);
        box.setText("Delete Confirmation");
        box.setMessage("Deleted Recipe " + r.getName());
        box.open();
    }

    public static void printRecipe(Recipe r, Shell shell)
    {
        // FIXME Do printing
        MessageBox box = new MessageBox(shell, 34);
        box.setText("Print Confirmation");
        box.setMessage("Printing Recipe " + r.getName()
            + " cannot be done with this software version.");
        box.open();
    }

}
