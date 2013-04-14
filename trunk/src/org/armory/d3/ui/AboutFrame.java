package org.armory.d3.ui;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import javax.swing.WindowConstants;
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
	private JLabel lblInfo;
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
				lblInfo = new JLabel();
				lblInfo.setText("Developpé par Nicolas Pihen - 2013");
				getContentPane().add(lblInfo, BorderLayout.SOUTH);
				lblInfo.setName("lblInfo");
			}
			{
				lblImage = new JLabel();
				getContentPane().add(lblImage, BorderLayout.CENTER);
				lblImage.setIcon(new ImageIcon(getClass().getResource("/org/armory/d3/ui/resources/about.png")));
				lblImage.setPreferredSize(new java.awt.Dimension(459, 168));
			}
			this.setSize(475, 236);
		} catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}
	}

}
