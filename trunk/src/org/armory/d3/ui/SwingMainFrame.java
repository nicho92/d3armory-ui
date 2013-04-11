package org.armory.d3.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.SplashScreen;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultRowSorter;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.table.TableRowSorter;

import org.armory.d3.beans.Hero;
import org.armory.d3.beans.Item;
import org.armory.d3.beans.Profile;
import org.armory.d3.services.D3ArmoryControler;
import org.armory.d3.ui.components.HeroCellRenderer;
import org.armory.d3.ui.components.HeroPanel;
import org.armory.d3.ui.components.ItemLabel;
import org.armory.d3.ui.components.ItemPanelDetails;
import org.armory.d3.ui.components.SkillLabel;
import org.armory.d3.ui.components.SocketLabel;
import org.armory.d3.ui.model.ListeHeroModel;
import org.armory.d3.ui.model.TableauDetailsModel;
import org.jdesktop.application.Application;

import com.sdfteam.d3armory.service.remote.exception.D3ServerCommunicationException;
import com.sdfteam.d3armory.service.util.EnumerationStuff;

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
public class SwingMainFrame extends javax.swing.JFrame {

	private JMenuItem helpMenuItem;
	private JMenu jMenu5;
	private HeroPanel panneauDessinHero;
	private JScrollPane scrollFicheHeros;
	private JList listeHeros;
	private JScrollPane scrollHeros;
	private JSplitPane splitPanneauFicheHero;
	private ItemLabel lblTorso;
	private SocketLabel lblSocketMainHand;
	private JTextField txtFiltrage;
	private JPanel panneauTableau;
	private JTable tableauDetails;
	private JScrollPane scrollTableau;
	private JSplitPane splitPanneauTableauHero;

	private ItemPanelDetails panelItemDetails;
	private JSplitPane splitTagsHeroes;
	private SocketLabel lblSocketMainHand2;
	private SocketLabel lblSocketLegs2;
	private SocketLabel lblSocketLegs1;
	private SocketLabel lblSocketTorso3;
	private SocketLabel lblSocketTorso2;
	private SocketLabel lblSocketTorso1;
	private SocketLabel lblSocketOffHand;
	private SocketLabel lblSocketRightRing;
	private SocketLabel lblSocketLeftRing;
	private SocketLabel lblSocketNeck;
	private SocketLabel lblSocketGants;
	private SocketLabel lblSocketBoot;
	private SocketLabel lblSocketHead;
	private ItemLabel lblHarcore;
	private ItemLabel lblHead;
	private ItemLabel lblOffHand;
	private ItemLabel lblMainHand;
	private ItemLabel lblFoot;
	private ItemLabel lblLegs;
	private ItemLabel lblbelt;
	private ItemLabel lblRingLeft;
	private ItemLabel lblRingRight;
	private ItemLabel lblBracers;
	private ItemLabel lblNeck;
	private ItemLabel lblGants;
	private ItemLabel lblShoulders;
	private ItemLabel lblParangonLevel;
	private ItemLabel lblInformationClasseNiveau;
	private ItemLabel lblNom;

	private JList listeTags;
	private JScrollPane scrollTags;
	private JSplitPane jSplitPane1;
	private JMenuItem exitMenuItem;
	private JSeparator jSeparator2;
	private JMenuItem newFileMenuItem;
	private JMenu jMenu3;
	private JMenuBar jMenuBar1;

	private DefaultComboBoxModel listeTagsModel;
	private DefaultRowSorter sorter;
	
	private ListeHeroModel listeHerosModel;
	private TableauDetailsModel tableaudetailModel;
	private JMenuItem jmiLocal;
	private SkillLabel labSkilL1;
	private SkillLabel labSkilL2;
	private SkillLabel labSkilL3;
	private SkillLabel labSkilL4;
	private SkillLabel labSkilL5;
	private SkillLabel labSkilL6;
	private SkillLabel labSkilL7;
	private SkillLabel labSkilL8;
	private SkillLabel labSkilL9; 
	
	public ListeHeroModel getListeHerosModel() {
		return listeHerosModel;
	}

	
	public static void main(String[] args) {
		
		 final SplashScreen splash = SplashScreen.getSplashScreen();
	        if (splash != null) {
	             Graphics2D g = splash.createGraphics();
	        try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	        splash.close();
	        }
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				SwingMainFrame inst = new SwingMainFrame();
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				SwingUtilities.updateComponentTreeUI(inst);

				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}
	
	public SwingMainFrame() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		try {
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setTitle("Diablo III Profile");
			this.setIconImage(new ImageIcon(getClass().getResource("/org/armory/d3/ui/resources/icone.jpg")).getImage());
			UIManager.put("Table.alternateRowColor", Color.decode("#E1E4F2"));
			BoxLayout thisLayout = new BoxLayout(getContentPane(), javax.swing.BoxLayout.X_AXIS);
			getContentPane().setLayout(thisLayout);
			{
				
				jSplitPane1 = new JSplitPane();
				getContentPane().add(jSplitPane1);
				{
					splitPanneauFicheHero = new JSplitPane();
					jSplitPane1.add(getSplitTagsHeroes(), JSplitPane.LEFT);
					jSplitPane1.add(getJSplitPane2(), JSplitPane.RIGHT);
					{
						scrollFicheHeros = new JScrollPane();
						splitPanneauFicheHero.add(scrollFicheHeros, JSplitPane.RIGHT);
						splitPanneauFicheHero.add(getSplitPanneauTableauHero(), JSplitPane.LEFT);
						scrollFicheHeros.setSize(250, 815);
						scrollFicheHeros.setViewportView(getPanelItemDetails());
					}
				}
			}
			this.setSize(1820, 915);
			{
				jMenuBar1 = new JMenuBar();
				setJMenuBar(jMenuBar1);
				{
					jMenu3 = new JMenu();
					jMenuBar1.add(jMenu3);
					jMenu3.setText("Application");
					{
						newFileMenuItem = new JMenuItem();
						jMenu3.add(newFileMenuItem);
						newFileMenuItem.setText("Tags");
						newFileMenuItem.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								newFileMenuItemActionPerformed(evt);
							}
						});
						
						jmiLocal = new JMenuItem();
						jMenu3.add(jmiLocal);
						jmiLocal.setText("Local");
						jmiLocal.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								newLocalActionPerformed(evt);
							}
						});
						
					}
					{
						jSeparator2 = new JSeparator();
						jMenu3.add(jSeparator2);
					}
					{
						exitMenuItem = new JMenuItem();
						jMenu3.add(exitMenuItem);
						exitMenuItem.setText("Exit");
						exitMenuItem.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								System.exit(0);
							}
						});
					}
				}
				{
					jMenu5 = new JMenu();
					jMenuBar1.add(jMenu5);
					;
					jMenu5.setName("jMenu5");
					{
						helpMenuItem = new JMenuItem();
						jMenu5.add(helpMenuItem);
						;
						helpMenuItem.setName("helpMenuItem");
					}
				}
			}
			Application.getInstance().getContext().getResourceMap(getClass()).injectComponents(getContentPane());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public HeroPanel getPanneauDessinHero() {
		return panneauDessinHero;
	}


	public JScrollPane getScrollTags() {
		return scrollTags;
	}
	
	public JSplitPane getJSplitPane2() {
		return splitPanneauFicheHero;
	}
	
	public JScrollPane getScrollHeros() {
		return scrollHeros;
	}
	
	public JList getListeHeros() {
		return listeHeros;
	}
	
	public JScrollPane getScrollFicheHeros() {
		return scrollFicheHeros;
	}
	
	
	
	private void listeHerosMouseClicked(MouseEvent evt) throws D3ServerCommunicationException {
		Hero hero = (Hero)((JList) evt.getSource()).getSelectedValue();
		D3ArmoryControler.getInstance().setSelectedHero(hero);
		hero=D3ArmoryControler.getInstance().getHeroDetails(hero);
		lblNom.setText(hero.getName());
		lblInformationClasseNiveau.setText(hero.getClazz() +" de niveau " + hero.getLevel());
		lblParangonLevel.setText("("+hero.getParagonLevel()+")");
		lblParangonLevel.setBounds(749, 20, 51, 16);
		getLblSkill1().setSkillRune(hero.getSkills().getActive().get(0));
		getLblSkill2().setSkillRune(hero.getSkills().getActive().get(1));
				
		getLblSkill3().setSkillRune(hero.getSkills().getActive().get(2));
		getLblSkill4().setSkillRune(hero.getSkills().getActive().get(3));
		getLblSkill5().setSkillRune(hero.getSkills().getActive().get(4));
		getLblSkill6().setSkillRune(hero.getSkills().getActive().get(5));
		
		getLblSkill7().setSkillRune(hero.getSkills().getPassive().get(0));
		getLblSkill8().setSkillRune(hero.getSkills().getPassive().get(1));
		getLblSkill9().setSkillRune(hero.getSkills().getPassive().get(2));
		
		
		Item head = D3ArmoryControler.getInstance().getInstance().getItemDetails(hero.getItems().getHead());
		lblHead.setItem(head);
		lblSocketHead.setItem(head,0);
		
		Item foot = D3ArmoryControler.getInstance().getInstance().getItemDetails(hero.getItems().getFeet());
		lblFoot.setItem(foot);
		lblSocketBoot.setItem(foot,0);
		
		Item gants = D3ArmoryControler.getInstance().getInstance().getItemDetails(hero.getItems().getHands());
		lblGants.setItem(gants);
		lblSocketGants.setItem(gants,0);
		
		
		Item neck = D3ArmoryControler.getInstance().getInstance().getItemDetails(hero.getItems().getNeck());
		lblNeck.setItem(neck);
		lblSocketNeck.setItem(neck,0);
		
		Item ringright = D3ArmoryControler.getInstance().getInstance().getItemDetails(hero.getItems().getLeftFinger());
		lblRingRight.setItem(ringright);
		lblSocketRightRing.setItem(ringright,0);
		
		Item ringleft = D3ArmoryControler.getInstance().getInstance().getItemDetails(hero.getItems().getRightFinger());
		lblRingLeft.setItem(ringleft);
		lblSocketLeftRing.setItem(ringleft,0);
		

		Item mainHand = D3ArmoryControler.getInstance().getInstance().getItemDetails(hero.getItems().getMainHand());
		if(mainHand!=null)
			mainHand.setMainHand(true);
		Item offhand = D3ArmoryControler.getInstance().getInstance().getItemDetails(hero.getItems().getOffHand());
		lblMainHand.setItem(mainHand);
		lblSocketMainHand.setItem(mainHand,0);
		
		lblOffHand.setDisabled(false);
		if(mainHand!=null)
		{
			if(mainHand.nbSockets()==2)
				lblSocketMainHand2.setItem(mainHand,1);
			else
				lblSocketMainHand2.setItem(null,1);
			
			if(mainHand.getType().getTwoHanded() && hero.getItems().getOffHand()==null)
			{
				lblOffHand.setItem(hero.getItems().getMainHand());
				lblOffHand.setDisabled(true);
			}
			else
			{	
				lblOffHand.setItem(offhand);
				lblSocketOffHand.setItem(offhand,0);
			}
		}	
		
		if(offhand!=null)
		{
			lblOffHand.setItem(offhand);
			lblSocketOffHand.setItem(offhand,0);
		}

		Item torso = D3ArmoryControler.getInstance().getInstance().getItemDetails(hero.getItems().getTorso());
		lblTorso.setItem(torso);
		if(torso!=null)
		{
			if(torso.nbSockets()==0)
			{
				lblSocketTorso1.setItem(torso,0);
				lblSocketTorso2.setItem(torso,0);
				lblSocketTorso3.setItem(torso,0);
			}
			
			if(torso.nbSockets()==1)
			{
				lblSocketTorso1.setItem(torso,0);
				lblSocketTorso2.setItem(null,0);
				lblSocketTorso3.setItem(null,0);
			}
			
			
			if(torso.nbSockets()==2)
			{
				lblSocketTorso1.setItem(torso,0);
				lblSocketTorso2.setItem(torso,1);
				lblSocketTorso3.setItem(null,0);
			}
			
			if(torso.nbSockets()>2)
			{
				lblSocketTorso1.setItem(torso,0);
				lblSocketTorso2.setItem(torso,1);
				lblSocketTorso3.setItem(torso,2);
			}
		}
		else
		{
			lblSocketTorso1.setItem(null,0);
			lblSocketTorso2.setItem(null,0);
			lblSocketTorso3.setItem(null,0);
		}
		Item legs = D3ArmoryControler.getInstance().getInstance().getItemDetails(hero.getItems().getLegs());
		lblLegs.setItem(legs);
		
		if(legs!=null)
		{
			if(legs.nbSockets()==0)
			{
				lblSocketLegs1.setItem(legs,0);
				lblSocketLegs2.setItem(legs,0);
			}
			if(legs.nbSockets()==1)
			{
				lblSocketLegs1.setItem(legs,0);
				lblSocketLegs2.setItem(null,0);
			}
			
			if(legs.nbSockets()==2)
			{
				lblSocketLegs1.setItem(legs,0);
				lblSocketLegs2.setItem(legs,1);
			}
		}
		else
		{
			lblSocketLegs1.setItem(null,0);
			lblSocketLegs2.setItem(null,0);
		}
		
		Item shoulders= D3ArmoryControler.getInstance().getInstance().getItemDetails(hero.getItems().getShoulders());
		lblShoulders.setItem(shoulders);
		Item bracers = D3ArmoryControler.getInstance().getInstance().getItemDetails(hero.getItems().getBracers());
		lblBracers.setItem(bracers);
		Item belt = D3ArmoryControler.getInstance().getInstance().getItemDetails(hero.getItems().getWaist());
		lblbelt.setItem(belt);
		
		Map<EnumerationStuff,Item> stuffs = new HashMap<EnumerationStuff, Item>();
		  stuffs.put(EnumerationStuff.HEAD, head);
		  stuffs.put(EnumerationStuff.SHOULDERS, shoulders);
		  stuffs.put(EnumerationStuff.NECK, neck);
		  stuffs.put(EnumerationStuff.TORSO, torso);
		  stuffs.put(EnumerationStuff.GANT, gants);
		  stuffs.put(EnumerationStuff.BRACER, bracers);
		  stuffs.put(EnumerationStuff.BELT, belt);
		  stuffs.put(EnumerationStuff.LEGS, legs);
		  stuffs.put(EnumerationStuff.RING_RIGHT, ringright);
		  stuffs.put(EnumerationStuff.RING_LEFT, ringleft);
		  stuffs.put(EnumerationStuff.MAIN_HAND, mainHand);
		  stuffs.put(EnumerationStuff.OFF_HAND, offhand);
		  stuffs.put(EnumerationStuff.FEET, foot);
		
		D3ArmoryControler.getInstance().getInstance().initCalculator(stuffs);
		
		((TableauDetailsModel)getTableauDetails().getModel()).fireTableDataChanged();
		
		if(hero.isHardcore())
			lblHarcore.setText("Hardcore");
		else
			lblHarcore.setText("");
		
		
		
		panneauDessinHero.repaint();	
		StringBuffer temp = new StringBuffer();
						temp.append("DPS : " + hero.getStats().getDamage() +" <br/>");				
						temp.append("Strength : " + hero.getStats().getStrength() +" <br/>");
						temp.append("Intel : " + hero.getStats().getIntelligence() +" <br/>");
						temp.append("Dex : " + hero.getStats().getDexterity() +" <br/>");
						temp.append("Vita : " + hero.getStats().getVitality() +" <br/>");
						temp.append("Armor : " + hero.getStats().getArmor() +" <br/>");
						
		panneauDessinHero.getLblInfoHero().setHtmlText(temp.toString(), "#5869D7","#BDA6CD");

		
		
	}
	
	private void listeTagMouseClicked(MouseEvent evt) {
		String selected_row = ((JList) evt.getSource()).getSelectedValue().toString();
		String[] parser = selected_row.split("#");
		try {
			
			D3ArmoryControler.getInstance().loadLocal();
			Profile p = D3ArmoryControler.getInstance().getProfil(parser[2]+".battle.net", parser[0], Long.parseLong(parser[1]));
			
			getListeHeros().removeAll();
			for(Hero h : p.getHeroes())
			{
				getListeHerosModel().addElement(h);
			}
			
		} catch (NumberFormatException | D3ServerCommunicationException e) {
			e.printStackTrace();
		}
	}
	
	private SkillLabel getLblSkill1()
	{
		if(labSkilL1==null){
			labSkilL1 = new SkillLabel();
			labSkilL1.setBounds(42, 395, 64, 64);
		}
		return labSkilL1;
	}
	
	private SkillLabel getLblSkill2()
	{
		if(labSkilL2==null){
			labSkilL2 = new SkillLabel();
			labSkilL2.setBounds(108, 395, 64, 64);
		}
		return labSkilL2;
	}
	private SkillLabel getLblSkill3()
	{
		if(labSkilL3==null){
			labSkilL3 = new SkillLabel();
			labSkilL3.setBounds(42, 462, 64, 64);
		}
		return labSkilL3;
	}
	private SkillLabel getLblSkill4()
	{
		if(labSkilL4==null){
			labSkilL4 = new SkillLabel();
			labSkilL4.setBounds(108, 462, 64, 64);
		}
		return labSkilL4;
	}
	private SkillLabel getLblSkill5()
	{
		if(labSkilL5==null){
			labSkilL5 = new SkillLabel();
			labSkilL5.setBounds(174, 462, 64, 64);
		}
		return labSkilL5;
	}
	private SkillLabel getLblSkill6()
	{
		if(labSkilL6==null){
			labSkilL6 = new SkillLabel();
			labSkilL6.setBounds(240, 462, 64, 64);
		}
		return labSkilL6;
	}
	
	private SkillLabel getLblSkill7()
	{
		if(labSkilL7==null){
			labSkilL7 = new SkillLabel();
			labSkilL7.setBounds(42, 528, 64, 64);
		}
		return labSkilL7;
	}
	private SkillLabel getLblSkill8()
	{
		if(labSkilL8==null){
			labSkilL8 = new SkillLabel();
			labSkilL8.setBounds(108, 528, 64, 64);
		}
		return labSkilL8;
	}
	private SkillLabel getLblSkill9()
	{
		if(labSkilL9==null){
			labSkilL9 = new SkillLabel();
			labSkilL9.setBounds(174, 528, 64, 64);
		}
		return labSkilL9;
	}
	
	
	private ItemLabel getLblShoulders() {
		if(lblShoulders == null) {
			lblShoulders = new ItemLabel(getPanelItemDetails());
			lblShoulders.setBounds(502, 179, 75, 89);
		}
		return lblShoulders;
	}
	
	private ItemLabel getLblGants() {
		if(lblGants == null) {
			lblGants = new ItemLabel(getPanelItemDetails());
			lblGants.setBounds(490, 274, 61, 98);
			lblGants.add(getLblSocketGants());
		}
		return lblGants;
	}
	
	private ItemLabel getLblNeck() {
		if(lblNeck == null) {
			lblNeck = new ItemLabel(getPanelItemDetails());
			lblNeck.setBounds(679, 206, 56, 50);
			lblNeck.add(getLblSocketNeck());
		}
		return lblNeck;
	}
	
	private ItemLabel getLblBracers() {
		if(lblBracers == null) {
			lblBracers = new ItemLabel(getPanelItemDetails());
			lblBracers.setBounds(702, 282, 66, 90);
		}
		return lblBracers;
	}
	
	private ItemLabel getLblTorso() {
		if(lblTorso == null) {
			lblTorso = new ItemLabel(getPanelItemDetails());
			lblTorso.setBounds(589, 229, 78, 113);
			lblTorso.add(getLblSocketTorso1());
			lblTorso.add(getLblSocketTorso2());
			lblTorso.add(getLblSocketTorso3());
		}
		return lblTorso;
	}
	
	private ItemLabel getLblRingRight() {
		if(lblRingRight == null) {
			lblRingRight = new ItemLabel(getPanelItemDetails());
			lblRingRight.setBounds(502, 385, 37, 37);
			lblRingRight.add(getLblSocketRightRing());
		}
		return lblRingRight;
	}
	
	private ItemLabel getLblRingLeft() {
		if(lblRingLeft == null) {
			lblRingLeft = new ItemLabel(getPanelItemDetails());
			lblRingLeft.setBounds(719, 381, 38, 41);
			lblRingLeft.add(getLblSocketLeftRing());
		}
		return lblRingLeft;
	}
	
	private ItemLabel getLblbelt() {
		if(lblbelt == null) {
			lblbelt = new ItemLabel(getPanelItemDetails());
			lblbelt.setBounds(589, 347, 78, 32);
		}
		return lblbelt;
	}
	
	private ItemLabel getLblLegs() {
		if(lblLegs == null) {
			lblLegs = new ItemLabel(getPanelItemDetails());
			lblLegs.setBounds(589, 391, 78, 84);
			lblLegs.add(getLblSocketLegs1());
			lblLegs.add(getLblSocketLegs2());
		}
		return lblLegs;
	}
	
	private ItemLabel getLblFoot() {
		if(lblFoot == null) {
			lblFoot = new ItemLabel(getPanelItemDetails());
			lblFoot.setBounds(589, 481, 78, 89);
			lblFoot.add(getLblSocketBoot());
		}
		return lblFoot;
	}
	
	private ItemLabel getLblMainHand() {
		if(lblMainHand == null) {
			lblMainHand = new ItemLabel(getPanelItemDetails());
			lblMainHand.setBounds(490, 434, 67, 136);
			lblMainHand.add(getLblSocketMainHand());
			lblMainHand.add(getLblSocketMainHand2());
		}
		return lblMainHand;
	}
	
	private ItemLabel getLblOffHand() {
		if(lblOffHand == null) {
			lblOffHand = new ItemLabel(getPanelItemDetails());
			lblOffHand.setBounds(702, 434, 73, 133);
			lblOffHand.add(getLblSocketOffHand());
		}
		return lblOffHand;
	}
	
	private ItemLabel getLblHead() {
		if(lblHead == null) {
			lblHead = new ItemLabel(getPanelItemDetails());
			lblHead.setBounds(594, 148, 67, 77);
			lblHead.add(getLblSocketHead());
		}
		return lblHead;
	}
	
	private ItemLabel getLblHarcore() {
		if(lblHarcore == null) {
			lblHarcore = new ItemLabel(getPanelItemDetails());
			lblHarcore.setText("");
			lblHarcore.setBounds(539, 42, 180, 18);
			lblHarcore.setName("lblHarcore");
		}
		return lblHarcore;
	}
	
	private SocketLabel getLblSocketHead() {
		if(lblSocketHead == null) {
			lblSocketHead = new SocketLabel();
			lblSocketHead.setBounds(0, 0, getLblHead().getWidth(), getLblHead().getHeight());
			lblSocketHead.setName("lblSocketHead");
		}
		return lblSocketHead;
	}
	
	private SocketLabel getLblSocketBoot() {
		if(lblSocketBoot == null) {
			lblSocketBoot = new SocketLabel();
			lblSocketBoot.setBounds(0, 0, getLblFoot().getWidth(), getLblFoot().getHeight());
			lblSocketBoot.setName("lblSocketBoot");
		}
		return lblSocketBoot;
	}
	
	private SocketLabel getLblSocketGants() {
		if(lblSocketGants == null) {
			lblSocketGants = new SocketLabel();
			lblSocketGants.setBounds(0, 0, getLblGants().getWidth(), getLblGants().getHeight());
		}
		return lblSocketGants;
	}
	
	private SocketLabel getLblSocketNeck() {
		if(lblSocketNeck == null) {
			lblSocketNeck = new SocketLabel();
			lblSocketNeck.setBounds(0, 0,  getLblNeck().getWidth(), getLblNeck().getHeight());
		}
		return lblSocketNeck;
	}
	
	private SocketLabel getLblSocketLeftRing() {
		if(lblSocketLeftRing == null) {
			lblSocketLeftRing = new SocketLabel();
			lblSocketLeftRing.setBounds(0, 0,  getLblRingLeft().getWidth(), getLblRingLeft().getHeight());
		}
		return lblSocketLeftRing;
	}
	
	private SocketLabel getLblSocketRightRing() {
		if(lblSocketRightRing == null) {
			lblSocketRightRing = new SocketLabel();
			lblSocketRightRing.setBounds(0,0,  getLblRingRight().getWidth(), getLblRingRight().getHeight());
		}
		return lblSocketRightRing;
	}
	
	private SocketLabel getLblSocketMainHand() {
		if(lblSocketMainHand == null) {
			lblSocketMainHand = new SocketLabel();
			lblSocketMainHand.setBounds(0, 40, getLblMainHand().getWidth(), 38);
		}
		return lblSocketMainHand;
	}
	
	private SocketLabel getLblSocketMainHand2() {
		if(lblSocketMainHand2 == null) {
			lblSocketMainHand2 = new SocketLabel();
			lblSocketMainHand2.setBounds(0, 80, getLblMainHand().getWidth(), 38);
		}
		return lblSocketMainHand2;
	}
	private SocketLabel getLblSocketOffHand() {
		if(lblSocketOffHand == null) {
			lblSocketOffHand = new SocketLabel();
			lblSocketOffHand.setBounds(0, 0, getLblOffHand().getWidth(), getLblOffHand().getHeight());
		}
		return lblSocketOffHand;
	}
	
	private SocketLabel getLblSocketTorso1() {
		if(lblSocketTorso1 == null) {
			lblSocketTorso1 = new SocketLabel();
			lblSocketTorso1.setBounds(0, 0, getLblTorso().getWidth(), 38);
		}
		return lblSocketTorso1;
	}
	
	private SocketLabel getLblSocketTorso2() {
		if(lblSocketTorso2 == null) {
			lblSocketTorso2 = new SocketLabel();
			lblSocketTorso2.setBounds(0, 37, getLblTorso().getWidth(), 38);
		}
		return lblSocketTorso2;
	}
	
	private SocketLabel getLblSocketTorso3() {
		if(lblSocketTorso3 == null) {
			lblSocketTorso3 = new SocketLabel();
			lblSocketTorso3.setBounds(0, 74, getLblTorso().getWidth(), 38);
		}
		return lblSocketTorso3;
	}
	
	private SocketLabel getLblSocketLegs1() {
		if(lblSocketLegs1 == null) {
			lblSocketLegs1 = new SocketLabel();
			lblSocketLegs1.setBounds(0, 10, getLblLegs().getWidth(), 35);

		}
		return lblSocketLegs1;
	}
	
	private SocketLabel getLblSocketLegs2() {
		if(lblSocketLegs2 == null) {
			lblSocketLegs2 = new SocketLabel();
			lblSocketLegs2.setBounds(0, 45, getLblLegs().getWidth(), 28);
		}
		return lblSocketLegs2;
	}
	
	private void newFileMenuItemActionPerformed(ActionEvent evt) {
		TagsManagerFrame f = new TagsManagerFrame(listeTagsModel);
		f.setVisible(true);
	}
	
	private void newLocalActionPerformed(ActionEvent evt) {
		LocaleManagerFrame f = new LocaleManagerFrame();
		f.setVisible(true);
	}

	
	
	private JSplitPane getSplitTagsHeroes() {
		if(splitTagsHeroes == null) {
			splitTagsHeroes = new JSplitPane();
			{
				scrollTags = new JScrollPane();
				splitTagsHeroes.add(scrollTags, JSplitPane.LEFT);
				scrollTags.setSize(250, 788);
				{
					listeTagsModel = new DefaultComboBoxModel(D3ArmoryControler.getInstance().getListTags().toArray());
					listeTags = new JList();
					scrollTags.setViewportView(listeTags);
					listeTags.setModel(listeTagsModel);
					listeTags.addMouseListener(new MouseAdapter() {
						public void mouseClicked(MouseEvent evt) {
							listeTagMouseClicked(evt);
						}
					});
					
				}
			}
			{
				scrollHeros = new JScrollPane();
				splitTagsHeroes.add(scrollHeros, JSplitPane.RIGHT);
				{
					listeHerosModel = new ListeHeroModel(); 
					listeHeros = new JList();
					scrollHeros.setViewportView(getListeHeros());
					listeHeros.setModel(listeHerosModel);
					
					listeHeros.setCellRenderer(new HeroCellRenderer());
					listeHeros.setName("listeHeros");
					listeHeros.setSize(130, 812);
					listeHeros.setPreferredSize(new Dimension(150,812));
					listeHeros.addMouseListener(new MouseAdapter() {
						public void mouseClicked(MouseEvent evt) {
							try {
								listeHerosMouseClicked(evt);
							} catch (D3ServerCommunicationException e) {
								e.printStackTrace();
							}
						}
					});
				}
			}
		}
		return splitTagsHeroes;
	}
	
	private ItemPanelDetails getPanelItemDetails() {
		if(panelItemDetails == null) {
			panelItemDetails = new ItemPanelDetails();
			panelItemDetails.setName("panelItemDetails");
			panelItemDetails.setSize(360, 812);
			panelItemDetails.setLayout(null);
			panelItemDetails.setPreferredSize(new java.awt.Dimension(0, 0));
		}
		return panelItemDetails;
	}
	
	private JSplitPane getSplitPanneauTableauHero() {
		if(splitPanneauTableauHero == null) {
			splitPanneauTableauHero = new JSplitPane();
			{
				panneauDessinHero = new HeroPanel();
				splitPanneauTableauHero.setOrientation(JSplitPane.VERTICAL_SPLIT);
				splitPanneauTableauHero.add(getPanneauTableau(), JSplitPane.BOTTOM);
				splitPanneauTableauHero.add(panneauDessinHero, JSplitPane.TOP);

				panneauDessinHero.setLayout(null);
				panneauDessinHero.setSize(994, 645);
				panneauDessinHero.setPreferredSize(new java.awt.Dimension(993, 600));
				panneauDessinHero.setName("panneauDessinHero");
				panneauDessinHero.add(getLblHead());
				panneauDessinHero.add(getLblShoulders());
				panneauDessinHero.add(getLblNeck());
				panneauDessinHero.add(getLblGants());
				panneauDessinHero.add(getLblTorso());
				panneauDessinHero.add(getLblBracers());
				panneauDessinHero.add(getLblbelt());
				panneauDessinHero.add(getLblLegs());
				panneauDessinHero.add(getLblFoot());
				panneauDessinHero.add(getLblRingLeft());
				panneauDessinHero.add(getLblRingRight());
				panneauDessinHero.add(getLblMainHand());
				panneauDessinHero.add(getLblOffHand());
				//click
				panneauDessinHero.add(getLblSkill1());
				panneauDessinHero.add(getLblSkill2());
				
				//actif
				panneauDessinHero.add(getLblSkill3());
				panneauDessinHero.add(getLblSkill4());
				panneauDessinHero.add(getLblSkill5());
				panneauDessinHero.add(getLblSkill6());
				
				
				//actif
				panneauDessinHero.add(getLblSkill7());
				panneauDessinHero.add(getLblSkill8());
				panneauDessinHero.add(getLblSkill9());
				

				
				
				{
					lblNom = new ItemLabel(getPanelItemDetails());
					panneauDessinHero.add(lblNom);
					lblNom.setBounds(466, 80, 314, 43);
					lblNom.setName("lblNom");
					lblNom.setHorizontalAlignment(JLabel.CENTER);
				}
				{
					lblInformationClasseNiveau = new ItemLabel(getPanelItemDetails());
					panneauDessinHero.add(lblInformationClasseNiveau);
					lblInformationClasseNiveau.setBounds(521, 20, 222, 16);
					lblInformationClasseNiveau.setName("lblInformationClasseNiveau");
					lblInformationClasseNiveau.setHorizontalAlignment(JLabel.HORIZONTAL);
				}
				{
					lblParangonLevel = new ItemLabel(getPanelItemDetails());
					panneauDessinHero.add(lblParangonLevel);
					panneauDessinHero.add(getLblHarcore());
					lblParangonLevel.setBounds(692, 20, 51, 16);
					lblParangonLevel.setName("lblParangonLevel");
				}
				
			}
		}
		return splitPanneauTableauHero;
	}
	
	private JScrollPane getScrollTableau() {
		if(scrollTableau == null) {
			scrollTableau = new JScrollPane();
			scrollTableau.setPreferredSize(new java.awt.Dimension(977, 202));
			scrollTableau.setName("scrollTableau");
			scrollTableau.setViewportView(getTableauDetails());
		}
		return scrollTableau;
	}
	
	private JTable getTableauDetails() {
		if(tableauDetails == null) {
			tableauDetails = new JTable();
			sorter = new TableRowSorter(getTableauDetailsModel());
			tableauDetails.setRowSorter(sorter);
			tableauDetails.setModel(getTableauDetailsModel());
			tableauDetails.setOpaque(true);
		}
		return tableauDetails;
	}


	private TableauDetailsModel getTableauDetailsModel() {
		if(tableaudetailModel==null)
		{
			tableaudetailModel= new TableauDetailsModel();
		}
		return tableaudetailModel;
	}
	
	
	
	private JPanel getPanneauTableau() {
		if(panneauTableau == null) {
			panneauTableau = new JPanel();
			BorderLayout panneauTableauLayout = new BorderLayout();
			panneauTableau.setLayout(panneauTableauLayout);
			panneauTableau.add(getTxtFiltrage(), BorderLayout.NORTH);
			panneauTableau.add(getScrollTableau(), BorderLayout.CENTER);
		}
		return panneauTableau;
	}
	
	private JTextField getTxtFiltrage() {
		if(txtFiltrage == null) {
			txtFiltrage = new JTextField();
			txtFiltrage.setName("txtFiltrage");
			txtFiltrage.setPreferredSize(new java.awt.Dimension(993, 22));

			txtFiltrage.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent evt) {
					txtFiltrage.setText("");
				}
			});
			
			txtFiltrage.addActionListener(new ActionListener() {
			      public void actionPerformed(ActionEvent e) {
			          String text = txtFiltrage.getText();
			          if (text.length() == 0) {
			            sorter.setRowFilter(null);
			          } else {
			            sorter.setRowFilter(RowFilter.regexFilter(text));
			          }
			        }
			      });
			
		}
		return txtFiltrage;
	}

}
