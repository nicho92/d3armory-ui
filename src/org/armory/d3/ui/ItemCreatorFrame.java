package org.armory.d3.ui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultRowSorter;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.table.TableRowSorter;

import org.armory.d3.services.D3ArmoryControler;
import org.armory.d3.ui.components.ItemPanelDetails;
import org.armory.d3.ui.components.StuffComparCellRenderer;
import org.armory.d3.ui.model.DetailsDPSModel;
import org.armory.d3.ui.model.ListeGemsModel;
import org.armory.d3.ui.model.StuffComparaisonModel;

import ca.odell.glazedlists.GlazedLists;
import ca.odell.glazedlists.matchers.TextMatcherEditor;
import ca.odell.glazedlists.swing.AutoCompleteSupport;

import com.pihen.d3restapi.beans.Attributs;
import com.pihen.d3restapi.beans.Item;
import com.pihen.d3restapi.beans.LegendarySet;
import com.pihen.d3restapi.beans.MinMaxBonus;
import com.pihen.d3restapi.service.util.EnumerationStuff;
import com.pihen.d3restapi.service.util.RawsAttributeFactory;
import com.pihen.d3restapi.service.util.StuffCalculator;

public class ItemCreatorFrame extends javax.swing.JDialog {
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
	private DetailsDPSModel tableauSpecItemModel;
	private EnumerationStuff gear;
	private JTable stuffcalcTable;
	private JLabel lblNewLabel;
	private JComboBox cboLegendarySet;

	StuffCalculator b;
	private JCheckBox chk2h;
	
	public ItemCreatorFrame(Item i,EnumerationStuff e) {
		super();
		this.gear=e;
		tableauSpecItemModel =new DetailsDPSModel(i);
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
						panneauTotalGauche.add(panneauGauche, BorderLayout.NORTH);
						BorderLayout panneauGaucheLayout = new BorderLayout();
						panneauGauche.setLayout(panneauGaucheLayout);
						panneauGauche.setPreferredSize(new Dimension(0, 398));
						{
							panneauHaut = new JPanel();
							GridLayout panneauHautLayout = new GridLayout(7, 2);
							panneauHaut.setLayout(panneauHautLayout);
							panneauGauche.add(panneauHaut, BorderLayout.CENTER);
							
							panneauHautLayout.setHgap(1);
							panneauHautLayout.setVgap(5);
							panneauHautLayout.setColumns(2);
							panneauHautLayout.setRows(8);
								lblName = new JLabel("Name :");
								panneauHaut.add(lblName);
							{
								txtNom = new JTextField(getItem().getName());
								panneauHaut.add(txtNom);
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
							
								ComboBoxModel cboTypeModel = 
										new DefaultComboBoxModel( Item.getWeaponDefaultAS().keySet().toArray(new String[Item.getWeaponDefaultAS().size()]) );
								
								
								if(getItem().getType()!=null)
									cboTypeModel.setSelectedItem(getItem().getType().getId());
								
								cboType = new JComboBox();
								panneauHaut.add(cboType);
								cboType.setModel(cboTypeModel);
						
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
							
							
								lblQuality = new JLabel();
								panneauHaut.add(lblQuality);
								lblQuality.setText("Quality :");
							
							
								ComboBoxModel cboQualityModel = new DefaultComboBoxModel(new String[] {"Normal", "Magical","Rare","Legendary","Legendary Set","Ancient Legendary","Ancient Legendary Set"});
								
								if(getItem().getDisplayColor()==null)
								{
									getItem().setTypeOfObject("Normal");
								}
								else
								{
									cboQualityModel.setSelectedItem(getItem().getRarity());
								}
							
							cboQuality = new JComboBox();
							cboQuality.setModel(cboQualityModel);
							panneauHaut.add(cboQuality);
							
							cboQuality.setPreferredSize(new java.awt.Dimension(148, 75));
							cboQuality.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent evt) {
									String choice =((JComboBox)evt.getSource()).getSelectedItem().toString();
									getItem().setTypeOfObject(choice);
									
									if(choice.contains("Set"))
									{
										cboLegendarySet.setEnabled(true);
									}
									else
									{
										cboLegendarySet.setEnabled(false);
										getItem().setSet(null);	
									}
									
									
									refreshItem();
								}
							});
							{
								lblNewLabel = new JLabel("Legendary Set");
								panneauHaut.add(lblNewLabel);
							}
							{
								
							}
							
							
							
							List<Item> l = D3ArmoryControler.getInstance().getCalculator().getAllItems();
							Set<LegendarySet> slugs = new HashSet<LegendarySet>(); 
							
							for(Item i : l){
								if(i!=null)
									if(i.isSetObjects())
										slugs.add(i.getSet());
							}
							
							cboLegendarySet = new JComboBox(slugs.toArray());
							
							cboLegendarySet.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent evt) {
									LegendarySet choice = (LegendarySet)((JComboBox)evt.getSource()).getSelectedItem();
									
									getItem().setSet(choice);									
									refreshItem();
								}
							});
							
							if(getItem().isSetObjects())
								cboLegendarySet.setEnabled(true);
							else
								cboLegendarySet.setEnabled(false);
								panneauHaut.add(cboLegendarySet);
								lblAttributes = new JLabel();
								panneauHaut.add(lblAttributes);
								lblAttributes.setText("Attributs :");
								cboAttributs = new JComboBox<Attributs>();
								cboAttributs.setEditable(true);
								panneauHaut.add(cboAttributs);
								AutoCompleteSupport support = AutoCompleteSupport.install(cboAttributs, GlazedLists.eventListOf(new RawsAttributeFactory().getAttributs()));
													support.setStrict(true);
													support.setFilterMode(TextMatcherEditor.CONTAINS);
									
								
								cboAttributs.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent evt) {
										Attributs a = (Attributs)cboAttributs.getSelectedItem();
										
										String oldValue="0-0";
										if(a.getLibelle().startsWith("+X-X"))
										{
											String key_min="Damage_Weapon_Min";
											String key_delta="Damage_Weapon_Delta";
										
											if(a.getLibelle().equals("+X-X Damage"))
											{
												key_min="Damage_Min";
												key_delta="Damage_Delta";
											}
											
												String input = "";
												String element = a.getId().split("#")[1];
												double min=0;
												double max=0;
												
												try{
												 min = getItem().getAttributesRaw().get(key_min+"#"+element).getMoyenne();
												 max = getItem().getAttributesRaw().get(key_delta+"#"+element).getMoyenne();
												 oldValue=min+"-"+(min+max);
												}
												catch(Exception e)
												{
													//e.printStackTrace();
												}
												
												 input= JOptionPane.showInputDialog("Damage MIN-MAX",oldValue);
												 if(input==null)
													 input = oldValue;
												 
												 String value[] = input.split("-");
											     min=Double.parseDouble(value[0]);
												 max=Double.parseDouble(value[1]);
											
											
											a.setValue(new MinMaxBonus(min));
											Attributs b = new Attributs(key_delta+"#"+element, "",false);
													  b.setValue(new MinMaxBonus(max-min));
											
											getItem().addAttributs(a);
											getItem().addAttributs(b);
										}
										else
										{
											int pourcent = 1;
											if(a.getLibelle().contains("%"))
												pourcent=100;
											
											
											a.setValue(new MinMaxBonus(Double.parseDouble(JOptionPane.showInputDialog("Value",a.getValue()))/pourcent));
											getItem().addAttributs(a);
										}
											
										
										tableauSpecItemModel.fireTableDataChanged();
									
										
										refreshItem();
									}
								});
						
							
								lblArmor = new JLabel("Armor :");
								panneauHaut.add(lblArmor);
								lblArmor.setName("lblArmor");
								
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
								lblDPS = new JLabel("MIN-MAX / AS : ");
								panneauHaut.add(lblDPS);
								lblDPS.setName("lblDPS");
								panneauDPS = new JPanel();
								FlowLayout panneauDPSLayout = new FlowLayout();
								panneauDPS.setLayout(panneauDPSLayout);
								panneauHaut.add(panneauDPS);
								
								panneauDPSLayout.setAlignment(FlowLayout.LEFT);
								{
									try{
										txtMin = new JTextField(""+new DecimalFormat("#0").format(getItem().getMinDamage().getMoyenne()));
									}
									catch(Exception e)
									{
										txtMin = new JTextField("");
									}
									panneauDPS.add(txtMin);
									txtMin.setPreferredSize(new java.awt.Dimension(43, 23));
									txtMin.addKeyListener(new KeyAdapter() {
										public void keyReleased(KeyEvent evt) {
											//TODO difference between elemental damage and default physical damage
											getItem().setMinDamage(new MinMaxBonus(Double.valueOf(txtMin.getText())));
											getItem().setDps(new MinMaxBonus(calcWeaponDPS(Double.valueOf(txtMin.getText()),Double.valueOf(txtMax.getText()),Double.valueOf(txtAS.getText()))));
											refreshItem();
										}
										
									});
								
								
								{
									try {
										txtMax = new JTextField(""+new DecimalFormat("#0").format(getItem().getMaxDamage().getMoyenne()));
									} catch (Exception e) {
										txtMax=new JTextField("");
									}
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
									try {
										txtAS = new JTextField(String.valueOf(new DecimalFormat("#0.00").format(getItem().getAttacksPerSecond().getMoyenne())));
									} catch (Exception e1) {
										txtAS = new JTextField("");
									}
									panneauDPS.add(txtAS);
									txtAS.setPreferredSize(new java.awt.Dimension(47, 23));
									chk2h = new JCheckBox("2H ?");
									if(getItem().getType()!=null)
									{
									chk2h.setSelected(getItem().getType().getTwoHanded());
									}
									else
									{
										chk2h.setSelected(false);
									}
									chk2h.addActionListener(new ActionListener() {
										public void actionPerformed(ActionEvent e) {
											getItem().getType().setTwoHanded(chk2h.isSelected());
											refreshItem();
										}
									});
									panneauDPS.add(chk2h);
									
									JLabel lblGems = new JLabel("Gems :");
									panneauHaut.add(lblGems);
									
									JPanel panneauGems = new JPanel();
									FlowLayout flowLayout = (FlowLayout) panneauGems.getLayout();
									flowLayout.setAlignment(FlowLayout.LEFT);
									panneauHaut.add(panneauGems);
									
									ListeGemsModel mod1 = new ListeGemsModel();
									mod1.setItem(getItem());
								
									
									JComboBox cboGem1 = new JComboBox(mod1);
									panneauGems.add(cboGem1);
									
									JComboBox cboGem2 = new JComboBox(mod1);
									panneauGems.add(cboGem2);
									
									JComboBox cboGem3 = new JComboBox(mod1);
									panneauGems.add(cboGem3);
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
								sorter.toggleSortOrder(0);
								
								tableauSpecItem.setRowSorter(sorter);
								panneauDetailDPS = new JPanel();
								panneauTotalGauche.add(panneauDetailDPS, BorderLayout.CENTER);
								panneauDetailDPS.setBackground(Color.black);
								panneauDetailDPS.setPreferredSize(new Dimension(500, 198));
								
								panneauDetailDPS.setLayout(new BorderLayout(0, 0));
								
								JScrollPane scrollPane = new JScrollPane();
								panneauDetailDPS.add(scrollPane, BorderLayout.CENTER);
								
								stuffcalcTable = new JTable();
								stuffcalcTable.setBackground(Color.BLACK);
								
								

								StuffCalculator a = D3ArmoryControler.getInstance().getCalculator();
								StuffCalculator b = D3ArmoryControler.getInstance().getCalculator();
								a.calculate();
								b.calculate();
								StuffComparaisonModel mod = new StuffComparaisonModel();
								mod.setStuffCalc(a, b);
								stuffcalcTable.setModel(mod);
								
								
								stuffcalcTable.setDefaultRenderer(Object.class, new StuffComparCellRenderer());
								DefaultRowSorter sorter1 = new TableRowSorter(stuffcalcTable.getModel());
								stuffcalcTable.setRowSorter(sorter1);
								sorter1.toggleSortOrder(0);
								
								scrollPane.setViewportView(stuffcalcTable);
								
								JPanel panel = new JPanel();
								panneauDetailDPS.add(panel, BorderLayout.EAST);
								
								JButton btnClear = new JButton("Clear");
								btnClear.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent arg0) {//vidage des champs
										getItem().setAttributesRaw(new HashMap<String,MinMaxBonus>());
										getItem().setAttributes(null);
										tableauSpecItemModel.fireTableDataChanged();
										refreshItem();
									}
								});
								panel.setLayout(new GridLayout(2, 2, 0, 0));
								panel.add(btnClear);
								btnSauvegarder = new JButton();
								panel.add(btnSauvegarder);
								btnSauvegarder.setText("Save");
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
						itemPanelDetails = new ItemPanelDetails(b);
						getContentPane().add(itemPanelDetails, BorderLayout.CENTER);
						itemPanelDetails.setPreferredSize(new Dimension(420, 398));
						itemPanelDetails.setFlavEnable(false);
					}
				}

			}
			pack();
			setLocationRelativeTo(null);
			setVisible(true);
			setResizable(true);
			refreshItem();
			
		} catch (Exception e) {
		    e.printStackTrace();
		}
	}
	
	
	
	public double calcWeaponDPS(Double min,Double max, Double as) {
		return ((min+max)/2)*as;
		
	}
	
	protected void refreshItem() {
		
			getItem().generateDisplayableAttributs();

			
			StuffCalculator a = D3ArmoryControler.getInstance().getCalculator();
			b = a.compareStuffWithItem(gear, getItem());
			itemPanelDetails.setCalculator(b);
		
			((StuffComparaisonModel)this.stuffcalcTable.getModel()).setStuffCalc(a,b);
			
			((StuffComparaisonModel)this.stuffcalcTable.getModel()).fireTableDataChanged();
			itemPanelDetails.showItem(getItem());
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
