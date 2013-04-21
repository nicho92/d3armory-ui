package org.armory.d3.ui;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import javax.swing.WindowConstants;

import org.armory.d3.services.RSSReader;
import org.jdesktop.application.Application;
import javax.swing.SwingUtilities;


/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class AboutFrame extends javax.swing.JFrame {
	private JEditorPane lblInfo;
	private JScrollPane jScrollPane1;
	private JLabel lblImage;

	public AboutFrame() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		try {
			BorderLayout thisLayout = new BorderLayout();
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			getContentPane().setLayout(thisLayout);
			{
				lblImage = new JLabel();
				getContentPane().add(lblImage, BorderLayout.CENTER);
				lblImage.setIcon(new ImageIcon(getClass().getResource("/org/armory/d3/ui/resources/about.png")));
				lblImage.setPreferredSize(new java.awt.Dimension(459, 140));
			}
			{
				jScrollPane1 = new JScrollPane();
				getContentPane().add(jScrollPane1, BorderLayout.SOUTH);
				jScrollPane1.setPreferredSize(new java.awt.Dimension(459, 116));
				{
					lblInfo = new JEditorPane();
					jScrollPane1.setViewportView(lblInfo);
					lblInfo.setContentType("text/html");
					lblInfo.setText("Nicolas Pihen - 2013 - <a href='mailto:nicolas.pihen@gmail.com'>nicolas.pihen@gmail.com</a><br/>" + new RSSReader().parse());
					lblInfo.setPreferredSize(new java.awt.Dimension(459, 116));
					lblInfo.setCaretPosition(0);
				}
			}
			this.setSize(475, 311);
		} catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}
	}

}
