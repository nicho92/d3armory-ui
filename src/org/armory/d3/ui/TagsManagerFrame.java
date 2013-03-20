package org.armory.d3.ui;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

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
public class TagsManagerFrame extends javax.swing.JFrame {
	private JPanel panelContenu;
	private JButton btnCancel;
	private JComboBox cboListServer;
	private JLabel lblServeur;
	private JTextField txtTag;
	private JLabel lblbattleTag;
	private JButton btnSave;
	private JPanel panneauBas;

	/**
	* Auto-generated main method to display this JFrame
	*/
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				TagsManagerFrame inst = new TagsManagerFrame();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}
	
	public TagsManagerFrame() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			{
				panelContenu = new JPanel();
				GridLayout panelContenuLayout = new GridLayout(2, 2);
				panelContenuLayout.setVgap(5);
				panelContenuLayout.setColumns(2);
				panelContenuLayout.setRows(2);
				panelContenu.setLayout(panelContenuLayout);
				getContentPane().add(panelContenu, BorderLayout.CENTER);
				panelContenu.setPreferredSize(new java.awt.Dimension(448, 147));
				{
					lblbattleTag = new JLabel();
					panelContenu.add(lblbattleTag);
					lblbattleTag.setName("lblbattleTag");
				}
				{
					txtTag = new JTextField();
					panelContenu.add(txtTag);
					txtTag.setPreferredSize(new java.awt.Dimension(221, 48));
				}
				{
					lblServeur = new JLabel();
					panelContenu.add(lblServeur);
					lblServeur.setName("lblServeur");
				}
				{
					ComboBoxModel cboListServerModel = 
							new DefaultComboBoxModel(
									new String[] { "us", "eu","ch" });
					cboListServer = new JComboBox();
					panelContenu.add(cboListServer);
					cboListServer.setModel(cboListServerModel);
				}
			}
			{
				panneauBas = new JPanel();
				getContentPane().add(panneauBas, BorderLayout.SOUTH);
				{
					btnSave = new JButton();
					panneauBas.add(btnSave);
					btnSave.setName("btnSave");
				}
				{
					btnCancel = new JButton();
					panneauBas.add(btnCancel);
					btnCancel.setName("btnCancel");
				}
			}
			pack();
			this.setSize(354, 134);
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			Application.getInstance().getContext().getResourceMap(getClass()).injectComponents(getContentPane());
		} catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}
	}

}
