package org.armory.d3.ui;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import javax.swing.WindowConstants;

import org.armory.d3.beans.Gem;
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
public class GemsCalculatorFrame extends javax.swing.JFrame {
	private JPanel panneauHaut;
	private JComboBox cboTargetGem;
	private JLabel lbltargetGem;
	private JComboBox cboStartGem;
	private JLabel lblDepart;

	/**
	* Auto-generated main method to display this JFrame
	*/
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				GemsCalculatorFrame inst = new GemsCalculatorFrame();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}
	
	public GemsCalculatorFrame() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			{
				panneauHaut = new JPanel();
				GridBagLayout panneauHautLayout = new GridBagLayout();
				panneauHautLayout.rowWeights = new double[] {0.0, 0.0, 0.1, 0.1};
				panneauHautLayout.rowHeights = new int[] {32, 26, 7, 7};
				panneauHautLayout.columnWeights = new double[] {0.1, 0.1, 0.1, 0.1};
				panneauHautLayout.columnWidths = new int[] {7, 7, 7, 7};
				panneauHaut.setLayout(panneauHautLayout);
				getContentPane().add(panneauHaut, BorderLayout.CENTER);
				{
					lblDepart = new JLabel();
					panneauHaut.add(lblDepart, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					lblDepart.setName("lblDepart");
				}
				{
					ComboBoxModel cboStartGemModel = 
							new DefaultComboBoxModel(Gem.getType());
					cboStartGem = new JComboBox();
					panneauHaut.add(getCboStartGem(), new GridBagConstraints(1, 0, 2, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					cboStartGem.setModel(cboStartGemModel);
				}
				{
					lbltargetGem = new JLabel();
					panneauHaut.add(lbltargetGem, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					lbltargetGem.setName("lbltargetGem");
				}
				{
					ComboBoxModel cboTargetGemModel = 
							new DefaultComboBoxModel( Gem.getType() );
					cboTargetGem = new JComboBox();
					panneauHaut.add(getCboTargetGem(), new GridBagConstraints(1, 1, 2, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					cboTargetGem.setModel(cboTargetGemModel);
				}
			}
			pack();
			this.setSize(371, 300);
			Application.getInstance().getContext().getResourceMap(getClass()).injectComponents(getContentPane());
		} catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}
	}
	
	public JComboBox getCboStartGem() {
		return cboStartGem;
	}
	
	public JComboBox getCboTargetGem() {
		return cboTargetGem;
	}

}
