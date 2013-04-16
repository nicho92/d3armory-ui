package org.armory.d3.ui;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import javax.swing.text.JTextComponent;

import org.armory.d3.beans.Attributs;
import org.armory.d3.beans.Item;
import org.armory.d3.beans.MinMaxBonus;
import org.armory.d3.ui.components.AutoCompletion;
import org.armory.d3.ui.components.ItemPanelDetails;
import org.armory.d3.ui.model.ItemDetailsModel;
import org.jdesktop.application.Application;

import com.sdfteam.d3armory.service.util.RawsAttributes;


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
public class ItemCreatorFrame extends javax.swing.JFrame {
	private JPanel panneauGauche;
	private ItemPanelDetails itemPanelDetails;
	private JLabel lblSokets;
	private JTable tableauSpecItem;
	private JPanel panneauHaut;
	private JScrollPane panneauBas;
	private JComboBox cboAttributs;
	private JLabel lblAttributes;
	private JComboBox cboSockets;
	private JComboBox cboQuality;
	private JLabel lblQuality;
	private JComboBox cboType;
	private JLabel lblType;
	private JTextField txtNom;
	private JLabel lblName;
	private Item i = new Item();
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				ItemCreatorFrame inst = new ItemCreatorFrame();
				
				inst.setVisible(true);
			}
		});
	}
	
	public ItemCreatorFrame() {
		super();
		initGUI();
	}
	
	public ItemCreatorFrame(Item i) {
		super();
		this.i=i;
		initGUI();
	}
	
	
	private void initGUI() {
		try {
			ItemDetailsModel tableauSpecItemModel =new ItemDetailsModel();
			setLocationRelativeTo(null);
			setTitle("Item Builder");
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			{
				panneauGauche = new JPanel();
				BorderLayout panneauGaucheLayout = new BorderLayout();
				panneauGauche.setLayout(panneauGaucheLayout);
				itemPanelDetails = new ItemPanelDetails();
				itemPanelDetails.setFlavEnable(false);
				getContentPane().add(panneauGauche, BorderLayout.CENTER);
				panneauGauche.setPreferredSize(new java.awt.Dimension(474, 398));
				{
					panneauHaut = new JPanel();
					GridLayout panneauHautLayout = new GridLayout(5, 2);
					panneauHautLayout.setHgap(5);
					panneauHautLayout.setVgap(5);
					panneauHautLayout.setColumns(2);
					panneauHautLayout.setRows(5);
					panneauHaut.setLayout(panneauHautLayout);
					panneauGauche.add(panneauHaut, BorderLayout.CENTER);
					panneauHaut.setPreferredSize(new java.awt.Dimension(455, 122));
					{
						lblName = new JLabel();
						panneauHaut.add(lblName);
						lblName.setText("Name :");
					}
					{
						txtNom = new JTextField(i.getName());
						panneauHaut.add(txtNom);
						txtNom.setPreferredSize(new java.awt.Dimension(148, 75));
						txtNom.addKeyListener(new KeyAdapter() {
							public void keyReleased(KeyEvent evt) {
								i.setName(txtNom.getText());
								refreshItem();
							}
						});
					}
					{
						lblType = new JLabel();
						panneauHaut.add(lblType);
						lblType.setText("Type :");
					}
					{
						ComboBoxModel cboTypeModel = 
								new DefaultComboBoxModel(
										new String[] { "Axe","HandXbow","Dagger","Mace","FistWeapon","MightyWeapon1H","Spear","Sword","CeremonialDagger","Wand","Axe2H","Bow","Daibo","Crossbow","Mace2H","MightyWeapon2H","Polearm","Staff","Sword2H",
												"ring","hat", "shoulders", "torso", "bracer", "glove", "belt", "pant", "boot" });
						cboType = new JComboBox();
						panneauHaut.add(cboType);
						cboType.setModel(cboTypeModel);
						cboType.setPreferredSize(new java.awt.Dimension(148, 75));
						cboType.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								i.setIcon(((JComboBox)evt.getSource()).getSelectedItem().toString()+"_206_demonhunter_male");
								if(((JComboBox)evt.getSource()).getSelectedIndex()>=19)
								{
									i.setArmor(new MinMaxBonus(0));
								}
								else
								{
									i.setDps(new MinMaxBonus(0));
								}
								refreshItem();
							}
						});
					}
					{
						lblQuality = new JLabel();
						panneauHaut.add(lblQuality);
						lblQuality.setText("Quality :");
					}
					{
						ComboBoxModel cboQualityModel = 
								new DefaultComboBoxModel(
										new String[] { "white", "blue","green","orange","yellow"});
						cboQuality = new JComboBox();
						panneauHaut.add(cboQuality);

						if(i.getDisplayColor()==null)
							i.setDisplayColor("White");

						cboQuality.setModel(cboQualityModel);
						cboQuality.setPreferredSize(new java.awt.Dimension(148, 75));
						cboQuality.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								i.setDisplayColor(((JComboBox)evt.getSource()).getSelectedItem().toString());
								refreshItem();
							}
						});
					}
					{
						lblAttributes = new JLabel();
						panneauHaut.add(lblAttributes);
						lblAttributes.setName("lblAttributes");
						lblAttributes.setPreferredSize(new java.awt.Dimension(190, 21));
					}
					{
						ComboBoxModel cboAttributsModel = new DefaultComboBoxModel(new RawsAttributes().getAttributs());
						cboAttributs = new JComboBox();
						cboAttributs.setEditable(true);
						panneauHaut.add(cboAttributs);
						AutoCompletion.enable(cboAttributs);
						cboAttributs.setModel(cboAttributsModel);
						cboAttributs.setPreferredSize(new java.awt.Dimension(294, 21));
						cboAttributs.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								
								((ItemDetailsModel)tableauSpecItem.getModel()).addAttributes((Attributs)cboAttributs.getSelectedItem());
								((ItemDetailsModel)tableauSpecItem.getModel()).fireTableDataChanged();
								
								i.addAttributesRaw(cboAttributs.getSelectedItem().toString(), new MinMaxBonus(0));
								i.setAttributes(((ItemDetailsModel)tableauSpecItem.getModel()).getAttributs());
								
								refreshItem();
							}
						});
						
						
					}
				}
				{
					panneauBas = new JScrollPane();
					panneauGauche.add(panneauBas, BorderLayout.SOUTH);
					panneauBas.setPreferredSize(new java.awt.Dimension(455, 272));
					

					{
						
						tableauSpecItem = new JTable();
						panneauBas.setViewportView(tableauSpecItem);
						tableauSpecItem.setModel(tableauSpecItemModel);
						tableauSpecItem.addPropertyChangeListener(new PropertyChangeListener() {
							public void propertyChange(PropertyChangeEvent evt) {
								  i.setAttributes(((ItemDetailsModel)tableauSpecItem.getModel()).getAttributs());
								  refreshItem();
						        }
						});
					}
				}

			}
			{
				
				getContentPane().add(itemPanelDetails, BorderLayout.EAST);
				itemPanelDetails.setPreferredSize(new java.awt.Dimension(368, 398));

			}
			pack();
			this.setSize(870, 437);
			refreshItem();
			setVisible(true);
			
			
			Application.getInstance().getContext().getResourceMap(getClass()).injectComponents(getContentPane());
		} catch (Exception e) {
		    e.printStackTrace();
		}
	}

	protected void refreshItem() {
		itemPanelDetails.showItem(i);
		
	}

		
}
