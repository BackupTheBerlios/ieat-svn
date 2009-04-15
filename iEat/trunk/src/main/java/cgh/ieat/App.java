package cgh.ieat;

import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class App
{
    final static int APP_WDTH = 800;
    final static int APP_HGTH = 600;
    
    final static int START_CNT = 4;

    public static void main(String[] args)
    {
        final Display display = new Display();
        final Image image = new Image(display, "iEat.gif");
        Shell shell;
        shell = new Shell(display);
        
        SlashScreenAppStart.startAppWithSplash(shell, display, image, START_CNT);
    }

}