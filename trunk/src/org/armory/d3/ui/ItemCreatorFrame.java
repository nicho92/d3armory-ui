package org.armory.d3.ui;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
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

import org.armory.d3.beans.Attributs;
import org.armory.d3.beans.Item;
import org.armory.d3.beans.MinMaxBonus;
import org.armory.d3.ui.components.AutoCompletion;
import org.armory.d3.ui.components.ItemPanelDetails;
import org.armory.d3.ui.model.ItemDetailsModel;
import org.armory.d3.ui.model.ItemDetailsModel;
import org.jdesktop.application.Application;

import com.sdfteam.d3armory.service.util.RawsAttributes;


public class ItemCreatorFrame extends javax.swing.JFrame {
	private JPanel panneauGauche;
	private ItemPanelDetails itemPanelDetails;
	private JLabel lblSokets;
	private JPanel panneauDPS;
	private JTextField txtMax;
	private JTextField txtMin;
	private JLabel lblMinMax;
	private JLabel lblDPS;
	private JTextField txtArmor;
	private JLabel lblArmor;
	private JTextField txtLevel;
	private JLabel lblLevelItem;
	private JTable tableauSpecItem;
	private JPanel panneauHaut;
	private JTextField txtAS;
	private JScrollPane panneauBas;
	private JComboBox<Attributs> cboAttributs;
	private JLabel lblAttributes;
	private JComboBox cboSockets;
	private JComboBox cboQuality;
	private JLabel lblQuality;
	private JComboBox cboType;
	private JLabel lblType;
	private JTextField txtNom;
	private JLabel lblName;
	private ItemDetailsModel tableauSpecItemModel;
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				ItemCreatorFrame inst = new ItemCreatorFrame();
				inst.setVisible(true);
			}
		});
	}
	
	public ItemCreatorFrame() {
		//super();
		Item i = new Item();
		tableauSpecItemModel =new ItemDetailsModel(i);
		initGUI();
	}
	
	public ItemCreatorFrame(Item i) {
		super();
		tableauSpecItemModel =new ItemDetailsModel(i);
		tableauSpecItemModel.setItem(i);
		initGUI();
	}
	
	
	private void initGUI() {
		try {
			
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
				panneauGauche.setPreferredSize(new java.awt.Dimension(461, 398));
				{
					panneauHaut = new JPanel();
					GridLayout panneauHautLayout = new GridLayout(7, 2);
					panneauHaut.setLayout(panneauHautLayout);
					panneauGauche.add(panneauHaut, BorderLayout.CENTER);
					
					panneauHautLayout.setHgap(5);
					panneauHautLayout.setVgap(5);
					panneauHautLayout.setColumns(2);
					panneauHautLayout.setRows(7);
					{
						lblName = new JLabel();
						panneauHaut.add(lblName);
						lblName.setText("Name :");
					}
					{
						txtNom = new JTextField(getItem().getName());
						panneauHaut.add(txtNom);
						txtNom.setPreferredSize(new java.awt.Dimension(148, 75));
						txtNom.addKeyListener(new KeyAdapter() {
							public void keyReleased(KeyEvent evt) {
								getItem().setName(txtNom.getText());
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
								getItem().setIcon(((JComboBox)evt.getSource()).getSelectedItem().toString()+"_206_demonhunter_male");
								if(((JComboBox)evt.getSource()).getSelectedIndex()>=19)
								{
									getItem().setArmor(new MinMaxBonus(0));
								}
								else
								{
									getItem().setDps(new MinMaxBonus(0));
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

						if(getItem().getDisplayColor()==null)
							getItem().setDisplayColor("White");

						cboQuality.setModel(cboQualityModel);
						cboQuality.setPreferredSize(new java.awt.Dimension(148, 75));
						cboQuality.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								getItem().setDisplayColor(((JComboBox)evt.getSource()).getSelectedItem().toString());
								refreshItem();
							}
						});
					}
					{
						lblAttributes = new JLabel();
						panneauHaut.add(lblAttributes);
						lblAttributes.setText("Attributs :");
						lblAttributes.setPreferredSize(new java.awt.Dimension(190, 21));
					}
					{
						ComboBoxModel<Attributs> cboAttributsModel = new DefaultComboBoxModel<Attributs>(new RawsAttributes().getAttributs());
						cboAttributs = new JComboBox<Attributs>();
						cboAttributs.setEditable(true);
						panneauHaut.add(cboAttributs);
						//AutoCompletion.enable(cboAttributs);
						cboAttributs.setModel(cboAttributsModel);
						cboAttributs.setPreferredSize(new java.awt.Dimension(294, 21));
						cboAttributs.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								getItem().addAttributs((Attributs)cboAttributs.getSelectedItem());
								
								tableauSpecItemModel.fireTableDataChanged();
								refreshItem();
							}
						});
						
						
					}
					{
						lblLevelItem = new JLabel();
						panneauHaut.add(lblLevelItem);
						lblLevelItem.setText("Level :");
					}
					{
						txtLevel = new JTextField();
						panneauHaut.add(txtLevel);
						txtLevel.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								long leve = Long.parseLong(txtLevel.getText());
								getItem().setItemLevel(leve);
								if(leve<=60)
									getItem().setRequiredLevel(Long.parseLong(txtLevel.getText())-1);
								else
									getItem().setRequiredLevel(60);
								refreshItem();
							}
						});
					}
					{
						lblArmor = new JLabel();
						panneauHaut.add(lblArmor);
						lblArmor.setName("lblArmor");
					}
					{
						txtArmor = new JTextField();
						panneauHaut.add(txtArmor);
						txtArmor.addKeyListener(new KeyAdapter() {
							public void keyReleased(KeyEvent evt) {
								
								//double val = Double.valueOf(txtArmor.getText());
								//getItem().setArmor(new MinMaxBonus(val));
								refreshItem();
							}
						});
					}
					{
						lblDPS = new JLabel();
						panneauHaut.add(lblDPS);
						lblDPS.setName("lblDPS");
					}
					{
						panneauDPS = new JPanel();
						FlowLayout panneauDPSLayout = new FlowLayout();
						panneauDPS.setLayout(panneauDPSLayout);
						panneauHaut.add(panneauDPS);
						
						panneauDPSLayout.setAlignment(FlowLayout.LEFT);
						{
							lblMinMax = new JLabel();
							panneauDPS.add(lblMinMax);
							lblMinMax.setName("lblMinMax");
						}
						{
							txtMin = new JTextField("0");
							panneauDPS.add(txtMin);
							txtMin.setPreferredSize(new java.awt.Dimension(43, 23));
							txtMin.addKeyListener(new KeyAdapter() {
								public void keyReleased(KeyEvent evt) {
									
									getItem().setMinDamage(new MinMaxBonus(Double.valueOf(txtMin.getText())));
									getItem().addAttributesRaw("Damage_Weapon_Min#Physical", new MinMaxBonus(Double.valueOf(txtMin.getText())));
									getItem().setDps(new MinMaxBonus(calcdps(Double.valueOf(txtMin.getText()),Double.valueOf(txtMax.getText()),Double.valueOf(txtAS.getText()))));
									refreshItem();
								}

							});
						}
					

						{
							txtMax = new JTextField("0");
							panneauDPS.add(txtMax);
							txtMax.setPreferredSize(new java.awt.Dimension(45, 23));
							txtMax.addKeyListener(new KeyAdapter() {
								public void keyReleased(KeyEvent evt) {
									getItem().setMaxDamage(new MinMaxBonus(Double.valueOf(txtMax.getText())));
									getItem().setDps(new MinMaxBonus(calcdps(Double.valueOf(txtMin.getText()),Double.valueOf(txtMax.getText()),Double.valueOf(txtAS.getText()))));
									getItem().addAttributesRaw("Damage_Weapon_Delta#Physical", new MinMaxBonus(Double.valueOf(txtMax.getText())-Double.valueOf(txtMin.getText())));
									refreshItem();
								}
							});
						}
						{
							txtAS = new JTextField("1");
							panneauDPS.add(txtAS);
							txtAS.setPreferredSize(new java.awt.Dimension(47, 23));
							txtAS.addKeyListener(new KeyAdapter() {
								public void keyReleased(KeyEvent evt) {
									getItem().setAttacksPerSecond(new MinMaxBonus(Double.valueOf(txtAS.getText())));
									getItem().setDps(new MinMaxBonus(calcdps(Double.valueOf(txtMin.getText()),Double.valueOf(txtMax.getText()),Double.valueOf(txtAS.getText()))));
									getItem().addAttributesRaw("Damage_Weapon_Delta#Physical", new MinMaxBonus(Double.valueOf(txtMax.getText())-Double.valueOf(txtMin.getText())));
									refreshItem();
								}
							});
						}
					}
				}
				{
					panneauBas = new JScrollPane();
					panneauGauche.add(panneauBas, BorderLayout.SOUTH);
					panneauBas.setPreferredSize(new java.awt.Dimension(486, 197));

					{
						
						tableauSpecItem = new JTable();
						panneauBas.setViewportView(tableauSpecItem);
						tableauSpecItem.setModel(tableauSpecItemModel);
						tableauSpecItem.addPropertyChangeListener(new PropertyChangeListener() {
							public void propertyChange(PropertyChangeEvent evt) {
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
			//pack();
			this.setSize(870, 437);
			refreshItem();
			setVisible(true);
			
			
			
			Application.getInstance().getContext().getResourceMap(getClass()).injectComponents(getContentPane());
		} catch (Exception e) {
		    e.printStackTrace();
		}
	}
	public double calcdps(Double min,Double max, Double as) {
		return ((min+max)/2)*as;
		
	}
	protected void refreshItem() {
			getItem().generateAttributsString();
		itemPanelDetails.showItem(getItem());
		
	}
	public ItemPanelDetails getItemPanelDetails() {
		return itemPanelDetails;
	}


	public Item getItem()
	{
		return tableauSpecItemModel.getItem();
	}

		
}
