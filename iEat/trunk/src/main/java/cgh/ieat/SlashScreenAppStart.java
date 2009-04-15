package cgh.ieat;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
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

/**
 * Class to abstract out the splash screen
 * @author Nixus
 *
 */
public class SlashScreenAppStart
{
    final static int SPLASH_WDTH = 100;
    
    public static void startAppWithSplash(final Shell app, final Display display, final Image image, final int seconds)
    {
        final boolean[] appControl = new boolean[]
            {
                false
            };
        final Shell splash = new Shell(SWT.ON_TOP);
        final ProgressBar bar = new ProgressBar(splash, SWT.BOLD);
        
        // Configure the splash screen
        bar.setMaximum(seconds);
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
                // Start up and open application
                Point appSize = app.getSize();
                app.setSize(appSize.x, appSize.y);
                app.setLocation(x - ((appSize.x/2) - SPLASH_WDTH), y - ((appSize.y/2) - SPLASH_WDTH));
                app.addListener(SWT.Close, new Listener()
                {
                    public void handleEvent(Event e)
                    {
                        appControl[0] = true;
                    }
                });
                app.open();
                
                // Delay startup and show progress bar/splash
                for (int i = 0; i <= seconds; i++)
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
