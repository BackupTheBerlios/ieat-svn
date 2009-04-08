package cgh.ieat;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.swt.widgets.Shell;

public class App
{
    final static int START_CNT = 4;
    final static int SPLASH_WDTH = 100;
    
    final static int APP_WDTH = 800;
    final static int APP_HGTH = 600;

    public static void main(String[] args)
    {
        final Display display = new Display();
        final boolean[] appControl = new boolean[]
            {
                false
            };
        final Image image = new Image(display, "iEat.gif");
        final Shell splash = new Shell(SWT.ON_TOP);
        final ProgressBar bar = new ProgressBar(splash, SWT.BOLD);
        
        // Configure the splash screen
        bar.setMaximum(START_CNT);
        Label label = new Label(splash, SWT.BOLD);
        label.setImage(image);
        FormLayout layout = new FormLayout();
        splash.setLayout(layout);
        FormData labelData = new FormData();
        labelData.right = new FormAttachment(SPLASH_WDTH, 0);
        labelData.left = new FormAttachment(8, 0);
        labelData.bottom = new FormAttachment(SPLASH_WDTH, 0);
        label.setLayoutData(labelData);
        FormData progressData = new FormData();
        progressData.left = new FormAttachment(0, 5);
        progressData.right = new FormAttachment(SPLASH_WDTH, -5);
        progressData.bottom = new FormAttachment(SPLASH_WDTH, -5);
        bar.setLayoutData(progressData);
        splash.pack();

        // Determines some screen placement info
        Rectangle splashRect = splash.getBounds();
        Rectangle displayRect = display.getMonitors()[0].getBounds();
        final int x = (displayRect.width - splashRect.width) / 2;
        final int y = (displayRect.height - splashRect.height) / 2;
        
        // Open the splash and start app
        splash.setLocation(x, y);
        splash.open();
        display.asyncExec(new Runnable()
        {
            public void run()
            {
                // Start up open application
                Shell shell;
                shell = new Shell(display);
                shell.setSize(APP_WDTH, APP_HGTH);
                shell.setLocation(x - ((APP_WDTH/2) - SPLASH_WDTH), y - ((APP_HGTH/2) - SPLASH_WDTH));
                shell.addListener(SWT.Close, new Listener()
                {
                    public void handleEvent(Event e)
                    {
                        appControl[0] = true;
                    }
                });
                shell.open();
                
                // Delay startup and show progress bar/splash
                for (int i = 0; i <= START_CNT; i++)
                {
                    bar.setSelection(i);
                    try
                    {
                        Thread.sleep(1000);
                    }
                    catch (Throwable e)
                    {
                    }
                }

                // Close splash and cleanup
                splash.close();
                image.dispose();
            }
        });
        while (!appControl[0])
        {
            if (!display.readAndDispatch())
                display.sleep();
        }
        display.dispose();
    }

}