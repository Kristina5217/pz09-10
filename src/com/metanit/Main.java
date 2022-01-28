package com.metanit;

import java.applet.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main extends Applet implements Runnable
{
    private Thread _Multi = null;
    boolean going = true;
    private boolean _fStandAlone = false;
    public static void main(String args[]) {
        MultiFrame frame = new MultiFrame("Title");
        frame.show(); frame.hide();
        frame.resize(frame.insets().left + frame.insets().right + 320,
                frame.insets().top + frame.insets().bottom + 240);
        Main applet_Combi = new Main();
        frame.add("Center", applet_Combi);
        applet_Combi._fStandAlone = true;
        applet_Combi.start();
        frame.show();
    }

    public String getAppletInfo()
    {	return "Name: CombiApplet\r\n" +
            "";
    }

    public void paint(Graphics g)
    {
        g.drawString("Running: " + Math.random(), 10, 20);
    }

    public void start()
    {
        if (_Multi == null)
        {	_Multi = new Thread(this);
            _Multi.start();
        }
    }

    public void stop()
    {
        if (_Multi != null)
        {	going = false;
            _Multi = null;
        }
    }

    public void run()
    {
        while (true)
        {	try
        {
            repaint();
            Thread.sleep(500);
        }
        catch (InterruptedException e)
        {
            stop();
        }
        }
    }
}

class MultiFrame extends Frame
{
    public MultiFrame(String str)
    {	super (str);
        addWindowListener(new MyWindowAdapter());
    }
    class MyWindowAdapter extends WindowAdapter
    {
        public void windowClosing(WindowEvent e)
        {
            dispose();
            System.exit(0);
        }
    }
}
