package org.armory.d3.ui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Map;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultRowSorter;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.table.TableRowSorter;

import org.armory.d3.services.D3ArmoryControler;
import org.armory.d3.ui.components.ItemPanelDetails;
import org.armory.d3.ui.components.StuffComparCellRenderer;
import org.armory.d3.ui.model.ItemDetailsModel;
import org.armory.d3.ui.model.StuffComparaisonModel;

import ca.odell.glazedlists.GlazedLists;
import ca.odell.glazedlists.matchers.TextMatcherEditor;
import ca.odell.glazedlists.swing.AutoCompleteSupport;

import com.pihen.d3restapi.beans.Attributs;
import com.pihen.d3restapi.beans.Item;
import com.pihen.d3restapi.beans.MinMaxBonus;
import com.pihen.d3restapi.service.util.EnumerationStuff;
import com.pihen.d3restapi.service.util.RawsAttributes;
import com.pihen.d3restapi.service.util.StuffCalculator;
import com.pihen.d3restapi.service.util.StuffCalculator.KEY;

public class ItemCreatorFrame extends javax.swing.JFrame {
	private JPanel panneauGauche;
	private ItemPanelDetails itemPanelDetails;
	private JLabel lblSokets;
	private JButton btnSauvegarder;
	private JPanel panneauDetailDPS;
	private JPanel panneauTotalGauche;
	private JPanel panneauDPS;
	private JTextField txtMax;
	private JTextField txtMin;
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
	private EnumerationStuff gear;
	private JTable table;


	
	public ItemCreatorFrame(Item i,EnumerationStuff e) {
		super();
		this.gear=e;
		tableauSpecItemModel =new ItemDetailsModel(i);
		initGUI();
	}
	
	
	public void initGUI() {
		try {
			
			setLocationRelativeTo(null);
			setTitle("Item Builder");
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			this.setIconImage(new ImageIcon(getClass().getResource("/org/armory/d3/ui/resources/icone.jpg")).getImage());
			
			{
				{
					panneauTotalGauche = new JPanel();
					getContentPane().add(panneauTotalGauche, BorderLayout.WEST);
					panneauTotalGauche.setLayout(new BorderLayout(0, 0));
					{
						panneauGauche = new JPanel();
						panneauTotalGauche.add(panneauGauche, BorderLayout.CENTER);
						BorderLayout panneauGaucheLayout = new BorderLayout();
						panneauGauche.setLayout(panneauGaucheLayout);
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
								lblName = new JLabel("Name :");
								panneauHaut.add(lblName);
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
										new DefaultComboBoxModel( Item.getWeaponDefaultAS().keySet().toArray(new String[Item.getWeaponDefaultAS().size()]) );
								
								
								if(getItem().getType()!=null)
									cboTypeModel.setSelectedItem(getItem().getType().getId());
								
								cboType = new JComboBox();
								panneauHaut.add(cboType);
								cboType.setModel(cboTypeModel);
						
								cboType.setPreferredSize(new java.awt.Dimension(148, 75));
								cboType.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent evt) {
										
										if(((JComboBox)evt.getSource()).getSelectedIndex()>=19)
										{
											getItem().setArmor(new MinMaxBonus(0));
										}
										else
										{
											getItem().setDps(new MinMaxBonus(0));
											getItem().addAttributesRaw("Attacks_Per_Second_Item", new MinMaxBonus(Item.getWeaponDefaultAS().get(((JComboBox)evt.getSource()).getSelectedItem().toString())));
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
								ComboBoxModel cboQualityModel = new DefaultComboBoxModel(new String[] {"Normal", "Magical","Rare","Legendary","Legendary Set"});
								cboQuality = new JComboBox();
								cboQuality.setModel(cboQualityModel);
								panneauHaut.add(cboQuality);
								
								if(getItem().getDisplayColor()==null)
								{
									getItem().setTypeOfObject("Normal");
								}
								else
								{
									cboQualityModel.setSelectedItem(getItem().getRarity());
								}
								
								cboQuality.setPreferredSize(new java.awt.Dimension(148, 75));
								cboQuality.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent evt) {
										getItem().setTypeOfObject(((JComboBox)evt.getSource()).getSelectedItem().toString());
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
								cboAttributs = new JComboBox<Attributs>();
								cboAttributs.setEditable(true);
								panneauHaut.add(cboAttributs);
								cboAttributs.setPreferredSize(new java.awt.Dimension(294, 21));
								AutoCompleteSupport support = AutoCompleteSupport.install(cboAttributs, GlazedLists.eventListOf(new RawsAttributes().getAttributs()));
													support.setStrict(true);
													support.setFilterMode(TextMatcherEditor.CONTAINS);
									
								
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
								txtLevel = new JTextField(""+getItem().getItemLevel());
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
								lblArmor = new JLabel("Armor :");
								panneauHaut.add(lblArmor);
								lblArmor.setName("lblArmor");
							}
							{
								
								txtArmor = new JTextField(""+getItem().getArmor());
								panneauHaut.add(txtArmor);
								txtArmor.addKeyListener(new KeyAdapter() {
									public void keyReleased(KeyEvent evt) {
										double val = Double.valueOf(txtArmor.getText());
										getItem().setArmor(new MinMaxBonus(val));
										getItem().addAttributesRaw("Armor_Item", new MinMaxBonus(val));
										
										refreshItem();
									}
								});
							}
							{
								lblDPS = new JLabel("MIN MAX / AS : ");
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
									txtMin = new JTextField(""+getItem().getMinDamage());
									panneauDPS.add(txtMin);
									txtMin.setPreferredSize(new java.awt.Dimension(43, 23));
									txtMin.addKeyListener(new KeyAdapter() {
										public void keyReleased(KeyEvent evt) {
											
											getItem().setMinDamage(new MinMaxBonus(Double.valueOf(txtMin.getText())));
											//getItem().addAttributesRaw("Damage_Weapon_Min#Physical", new MinMaxBonus(Double.valueOf(txtMin.getText())));
											getItem().setDps(new MinMaxBonus(calcWeaponDPS(Double.valueOf(txtMin.getText()),Double.valueOf(txtMax.getText()),Double.valueOf(txtAS.getText()))));
											refreshItem();
										}
										
									});
								}
								
								
								{
									txtMax = new JTextField(""+getItem().getMaxDamage());
									panneauDPS.add(txtMax);
									txtMax.setPreferredSize(new java.awt.Dimension(45, 23));
									txtMax.addKeyListener(new KeyAdapter() {
										public void keyReleased(KeyEvent evt) {
											getItem().setMaxDamage(new MinMaxBonus(Double.valueOf(txtMax.getText())));
											getItem().setDps(new MinMaxBonus(calcWeaponDPS(Double.valueOf(txtMin.getText()),Double.valueOf(txtMax.getText()),Double.valueOf(txtAS.getText()))));
											//getItem().addAttributesRaw("Damage_Weapon_Delta#Physical", new MinMaxBonus(Double.valueOf(txtMax.getText())-Double.valueOf(txtMin.getText())));
											refreshItem();
										}
									});
								}
								{
									txtAS = new JTextField(""+getItem().getAttacksPerSecond());
									panneauDPS.add(txtAS);
									txtAS.setPreferredSize(new java.awt.Dimension(47, 23));
									txtAS.addKeyListener(new KeyAdapter() {
										public void keyReleased(KeyEvent evt) {
											getItem().setAttacksPerSecond(new MinMaxBonus(Double.valueOf(txtAS.getText())));
											getItem().setDps(new MinMaxBonus(calcWeaponDPS(Double.valueOf(txtMin.getText()),Double.valueOf(txtMax.getText()),Double.valueOf(txtAS.getText()))));
											
											getItem().addAttributesRaw("Damage_Weapon_Delta#Physical", new MinMaxBonus(Double.valueOf(txtMax.getText())-Double.valueOf(txtMin.getText())));
											getItem().addAttributesRaw("Attacks_Per_Second_Item_Bonus",new MinMaxBonus(getItem().getAttacksPerSecond().getMoyenne()-getItem().getAttributesRaw().get("Attacks_Per_Second_Item").getMoyenne()));
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
								DefaultRowSorter sorter = new TableRowSorter(tableauSpecItem.getModel());
								tableauSpecItem.setRowSorter(sorter);
								panneauDetailDPS = new JPanel();
								panneauTotalGauche.add(panneauDetailDPS, BorderLayout.SOUTH);
								panneauDetailDPS.setBackground(Color.black);
								panneauDetailDPS.setPreferredSize(new java.awt.Dimension(862, 198));
								
								panneauDetailDPS.setLayout(new BorderLayout(0, 0));
								
								JScrollPane scrollPane = new JScrollPane();
								panneauDetailDPS.add(scrollPane, BorderLayout.CENTER);
								
								table = new JTable();
								table.setBackground(Color.BLACK);
								
								
								D3ArmoryControler.getInstance().getCalculator().calculate();
								StuffCalculator a = D3ArmoryControler.getInstance().getCalculator();
								StuffCalculator b = D3ArmoryControler.getInstance().getCalculator();
								a.calculate();
								b.calculate();
								StuffComparaisonModel mod = new StuffComparaisonModel();
								mod.setStuffCalc(a, b);
								table.setModel(mod);
								
								
								table.setDefaultRenderer(Object.class, new StuffComparCellRenderer());
								DefaultRowSorter sorter1 = new TableRowSorter(table.getModel());
								table.setRowSorter(sorter1);
								
								
								scrollPane.setViewportView(table);
								btnSauvegarder = new JButton();
								panneauDetailDPS.add(btnSauvegarder, BorderLayout.EAST);
								btnSauvegarder.setText("Save");
								btnSauvegarder.setPreferredSize(new java.awt.Dimension(71, 23));
								btnSauvegarder.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent evt) {
										D3ArmoryControler.getInstance().saveItem(getItem());
										btnSauvegarder.setEnabled(false);
									}
								});
								tableauSpecItem.addPropertyChangeListener(new PropertyChangeListener() {
									public void propertyChange(PropertyChangeEvent evt) {
										refreshItem();
									}
								});
							}
						}
						
					}
				}
				{
					
				
					{
						itemPanelDetails = new ItemPanelDetails();
						getContentPane().add(itemPanelDetails, BorderLayout.CENTER);
						itemPanelDetails.setPreferredSize(new java.awt.Dimension(379, 398));
						itemPanelDetails.setFlavEnable(false);
					}
				}

			}
			pack();
			//this.setSize(878, 613);
			setLocationRelativeTo(null);
			setVisible(true);
			setResizable(true);
			refreshItem();
			
		} catch (Exception e) {
		    e.printStackTrace();
		}
	}
	
	
	public String getDetailDiff(Map<KEY, Double> a,Map<KEY, Double> b)
	{
		String color="Green";
		StringBuffer temp = new StringBuffer();
		for(KEY k:KEY.values())
		{
			double val = b.get(k) - a.get(k);
			if(val <0)
				color="<font color='red'/>";
			if(val >0)
				color="<font color='green'/>+";
			if(val==0)
				color="<font color='gray'/> ";
	
			temp.append(color + StuffCalculator.format(val) +"</font><br/>");
			
		}
		
		return temp.toString();
	}
	
	
	public double calcWeaponDPS(Double min,Double max, Double as) {
		return ((min+max)/2)*as;
		
	}
	
	protected void refreshItem() {
			getItem().generateAttributsString();
			itemPanelDetails.showItem(getItem());
			
			StuffCalculator a = D3ArmoryControler.getInstance().getCalculator();
			StuffCalculator b = a.compareStuffWithItem(gear, getItem());
			
		
			((StuffComparaisonModel)this.table.getModel()).setStuffCalc(a,b);
			
			((StuffComparaisonModel)this.table.getModel()).fireTableDataChanged();
			
			btnSauvegarder.setEnabled(true);
	}
	
	
	public ItemPanelDetails getItemPanelDetails() {
		return itemPanelDetails;
	}

	public Item getItem()
	{
		return tableauSpecItemModel.getItem();
	}
}
