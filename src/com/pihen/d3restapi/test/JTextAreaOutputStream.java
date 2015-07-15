package com.pihen.d3restapi.test;

import java.awt.BorderLayout;
import java.awt.Container;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import org.armory.d3.console.D3Console;

public class JTextAreaOutputStream extends OutputStream
{
    private final JTextArea destination;

    public JTextAreaOutputStream (JTextArea destination)
    {
        if (destination == null)
            throw new IllegalArgumentException ("Destination is null");

        this.destination = destination;
    }

    @Override
    public void write(byte[] buffer, int offset, int length) throws IOException
    {
        final String text = new String (buffer, offset, length);
        SwingUtilities.invokeLater(new Runnable ()
            {
                @Override
                public void run() 
                {
                    destination.append (text);
                }
            });
    }

    @Override
    public void write(int b) throws IOException
    {
        write (new byte [] {(byte)b}, 0, 1);
    }

    public static void main (String[] args) throws Exception
    {
        JTextArea textArea = new JTextArea (25, 80);
        textArea.setEditable(true);
        
     
        JFrame frame = new JFrame ("stdout");
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        Container contentPane = frame.getContentPane ();
        contentPane.setLayout (new BorderLayout ());
        contentPane.add (
            new JScrollPane (
                textArea, 
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED),
            BorderLayout.CENTER);
        frame.pack ();
        frame.setVisible (true);
        
        JTextAreaOutputStream out = new JTextAreaOutputStream (textArea);
        System.setOut (new PrintStream (out));
        System.setIn(new InputStream() {
			
			@Override
			public int read() throws IOException {
				// TODO Auto-generated method stub
				return 0;
			}
		});
        new D3Console();
    }
}