package org.armory.d3.ui;
import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;

import org.armory.d3.services.RSSReader;


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
					lblInfo.setEditable(false);
					lblInfo.setText("Nicolas Pihen - 2013 - <a href='mailto:nicolas.pihen@gmail.com'>nicolas.pihen@gmail.com</a><br/>" + new RSSReader().parse());
					lblInfo.setPreferredSize(new java.awt.Dimension(459, 116));
					lblInfo.setCaretPosition(0);
				}
			}
			this.setSize(475, 311);
		} catch (Exception e) {
		    e.printStackTrace();
		}
	}

}
