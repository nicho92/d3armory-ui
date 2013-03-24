package org.armory.d3.ui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.ListModel;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.armory.d3.beans.Hero;
import org.armory.d3.beans.Item;
import org.armory.d3.beans.Profile;
import org.armory.d3.services.D3ArmoryControler;
import org.armory.d3.ui.model.ListeHeroModel;
import org.jdesktop.application.Application;

import com.sdfteam.d3armory.service.configuration.Configuration;
import com.sdfteam.d3armory.service.remote.RemoteService;
import com.sdfteam.d3armory.service.remote.SpringRemoteService;
import com.sdfteam.d3armory.service.remote.exception.D3ServerCommunicationException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	private JTable tableauDetailHeros;
	private HeroPanel panneauDessinHero;
	private JSplitPane scrollDetailHero;
	private JScrollPane scrollFicheHeros;
	private JList listeHeros;
	private JScrollPane scrollHeros;
	private JSplitPane jSplitPane2;
	private ItemLabel lblTorso;
	private SocketLabel lblSocketMainHand;
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

	
	private ListeHeroModel listeHerosModel; 
	
	public ListeHeroModel getListeHerosModel() {
		return listeHerosModel;
	}

	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				SwingMainFrame inst = new SwingMainFrame();
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
			BoxLayout thisLayout = new BoxLayout(getContentPane(), javax.swing.BoxLayout.X_AXIS);
			getContentPane().setLayout(thisLayout);
			{
				
				jSplitPane1 = new JSplitPane();
				getContentPane().add(jSplitPane1);
				{
					jSplitPane2 = new JSplitPane();
					jSplitPane1.add(getJSplitPane2(), JSplitPane.RIGHT);
					{
						scrollHeros = new JScrollPane();
						jSplitPane2.add(scrollHeros, JSplitPane.LEFT);
						{
							listeHerosModel = new ListeHeroModel(); 
							listeHeros = new JList();
							scrollHeros.setViewportView(getListeHeros());
							listeHeros.setModel(listeHerosModel);
							HeroCellRenderer renderer = new HeroCellRenderer();
						    listeHeros.setCellRenderer(renderer);
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
					{
						scrollFicheHeros = new JScrollPane();
						jSplitPane2.add(getScrollFicheHeros(), JSplitPane.RIGHT);
						{
							scrollDetailHero = new JSplitPane();
							scrollDetailHero.setOrientation(JSplitPane.VERTICAL_SPLIT);
							scrollFicheHeros.setViewportView(getScrollDetailHero());
							{
								panneauDessinHero = new HeroPanel();
								scrollDetailHero.add(panneauDessinHero, JSplitPane.LEFT);

								panneauDessinHero.setLayout(null);
								panneauDessinHero.setSize(994, 645);
								panneauDessinHero.setPreferredSize(new java.awt.Dimension(916, 661));
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
								{
									lblNom = new ItemLabel();
									panneauDessinHero.add(lblNom);
									lblNom.setBounds(466, 80, 314, 43);
									lblNom.setName("lblNom");
									lblNom.setHorizontalAlignment(JLabel.CENTER);
								}
								{
									lblInformationClasseNiveau = new ItemLabel();
									panneauDessinHero.add(lblInformationClasseNiveau);
									lblInformationClasseNiveau.setBounds(521, 20, 222, 16);
									lblInformationClasseNiveau.setName("lblInformationClasseNiveau");
									lblInformationClasseNiveau.setHorizontalAlignment(JLabel.HORIZONTAL);
								}
								{
									lblParangonLevel = new ItemLabel();
									panneauDessinHero.add(lblParangonLevel);
									panneauDessinHero.add(getLblHarcore());
									lblParangonLevel.setBounds(692, 20, 51, 16);
									lblParangonLevel.setName("lblParangonLevel");
								}
								
							}
							
							{
								TableModel tableauDetailHerosModel = 
										new DefaultTableModel(
												new String[][] { { "One", "Two" }, { "Three", "Four" } },
												new String[] { "Column 1", "Column 2" });
								tableauDetailHeros = new JTable();
								scrollDetailHero.add(tableauDetailHeros, JSplitPane.BOTTOM);
								tableauDetailHeros.setModel(tableauDetailHerosModel);

							}
						}
					}
				}
				{
					scrollTags = new JScrollPane();
					jSplitPane1.add(getScrollTags(), JSplitPane.LEFT);
					{
						ListModel listeTagsModel = 
								new DefaultComboBoxModel(D3ArmoryControler.getInstance().getListTags().toArray());
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
			}
			this.setSize(1400, 858);
			{
				jMenuBar1 = new JMenuBar();
				setJMenuBar(jMenuBar1);
				{
					jMenu3 = new JMenu();
					jMenuBar1.add(jMenu3);
					jMenu3.setText("File");
					{
						newFileMenuItem = new JMenuItem();
						jMenu3.add(newFileMenuItem);
						newFileMenuItem.setText("New");
						newFileMenuItem.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								newFileMenuItemActionPerformed(evt);
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
		return jSplitPane2;
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
	
	public JSplitPane getScrollDetailHero() {
		return scrollDetailHero;
	}
	
	public JTable getTableauDetailHeros() {
		return tableauDetailHeros;
	}
	
	private void listeHerosMouseClicked(MouseEvent evt) throws D3ServerCommunicationException {
		Hero hero = (Hero)((JList) evt.getSource()).getSelectedValue();
		D3ArmoryControler.getInstance().setSelectedHero(hero);
		hero=D3ArmoryControler.getInstance().getHeroDetails(hero);
		lblNom.setText(hero.getName());
		lblInformationClasseNiveau.setText(hero.getClazz() +" de niveau " + hero.getLevel());
		lblParangonLevel.setText("("+hero.getParagonLevel()+")");
		lblParangonLevel.setBounds(749, 20, 51, 16);
		
		
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
		Item offhand = D3ArmoryControler.getInstance().getInstance().getItemDetails(hero.getItems().getOffHand());
		lblMainHand.setItem(mainHand);
		lblSocketMainHand.setItem(mainHand,0);
		
		if(mainHand!=null)
		{
			if(mainHand.getType().getTwoHanded() && hero.getItems().getOffHand()==null)
			{
				lblOffHand.setItem(hero.getItems().getMainHand());
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
		
		lblShoulders.setItem(D3ArmoryControler.getInstance().getInstance().getItemDetails(hero.getItems().getShoulders()));
		lblBracers.setItem(D3ArmoryControler.getInstance().getInstance().getItemDetails(hero.getItems().getBracers()));
		lblbelt.setItem(D3ArmoryControler.getInstance().getInstance().getItemDetails(hero.getItems().getWaist()));
		
		if(hero.isHardcore())
			lblHarcore.setText("Hardcore");
		else
			lblHarcore.setText("");
		
		panneauDessinHero.repaint();		

	}
	
	private void listeTagMouseClicked(MouseEvent evt) {
		String selected_row = ((JList) evt.getSource()).getSelectedValue().toString();
		String[] parser = selected_row.split("#");
		try {
			Profile p = D3ArmoryControler.getInstance().getProfil(parser[2]+".battle.net", parser[0], Long.parseLong(parser[1]), "fr_FR");
			
			getListeHeros().removeAll();
			for(Hero h : p.getHeroes())
			{
				getListeHerosModel().addElement(h);
			}
			
		} catch (NumberFormatException | D3ServerCommunicationException e) {
			e.printStackTrace();
		}
	}
	
	private ItemLabel getLblShoulders() {
		if(lblShoulders == null) {
			lblShoulders = new ItemLabel();
			lblShoulders.setBounds(502, 179, 75, 89);
		}
		return lblShoulders;
	}
	
	private ItemLabel getLblGants() {
		if(lblGants == null) {
			lblGants = new ItemLabel();
			lblGants.setBounds(490, 274, 61, 98);
			lblGants.add(getLblSocketGants());
		}
		return lblGants;
	}
	
	private ItemLabel getLblNeck() {
		if(lblNeck == null) {
			lblNeck = new ItemLabel();
			lblNeck.setBounds(679, 206, 56, 50);
			lblNeck.add(getLblSocketNeck());
		}
		return lblNeck;
	}
	
	private ItemLabel getLblBracers() {
		if(lblBracers == null) {
			lblBracers = new ItemLabel();
			lblBracers.setBounds(702, 282, 66, 90);
		}
		return lblBracers;
	}
	
	private ItemLabel getLblTorso() {
		if(lblTorso == null) {
			lblTorso = new ItemLabel();
			lblTorso.setBounds(589, 229, 78, 113);
			lblTorso.add(getLblSocketTorso1());
			lblTorso.add(getLblSocketTorso2());
			lblTorso.add(getLblSocketTorso3());
		}
		return lblTorso;
	}
	
	private ItemLabel getLblRingRight() {
		if(lblRingRight == null) {
			lblRingRight = new ItemLabel();
			lblRingRight.setBounds(502, 385, 37, 37);
			lblRingRight.add(getLblSocketRightRing());
		}
		return lblRingRight;
	}
	
	private ItemLabel getLblRingLeft() {
		if(lblRingLeft == null) {
			lblRingLeft = new ItemLabel();
			lblRingLeft.setBounds(719, 381, 38, 41);
			lblRingLeft.add(getLblSocketLeftRing());
		}
		return lblRingLeft;
	}
	
	private ItemLabel getLblbelt() {
		if(lblbelt == null) {
			lblbelt = new ItemLabel();
			lblbelt.setBounds(589, 347, 78, 32);
		}
		return lblbelt;
	}
	
	private ItemLabel getLblLegs() {
		if(lblLegs == null) {
			lblLegs = new ItemLabel();
			lblLegs.setBounds(589, 391, 78, 84);
			lblLegs.add(getLblSocketLegs1());
			lblLegs.add(getLblSocketLegs2());
		}
		return lblLegs;
	}
	
	private ItemLabel getLblFoot() {
		if(lblFoot == null) {
			lblFoot = new ItemLabel();
			lblFoot.setBounds(589, 481, 78, 89);
			lblFoot.add(getLblSocketBoot());
		}
		return lblFoot;
	}
	
	private ItemLabel getLblMainHand() {
		if(lblMainHand == null) {
			lblMainHand = new ItemLabel();
			lblMainHand.setBounds(490, 434, 67, 136);
			lblMainHand.add(getLblSocketMainHand());
		}
		return lblMainHand;
	}
	
	private ItemLabel getLblOffHand() {
		if(lblOffHand == null) {
			lblOffHand = new ItemLabel();
			lblOffHand.setBounds(702, 434, 73, 133);
			lblOffHand.add(getLblSocketOffHand());
		}
		return lblOffHand;
	}
	
	private ItemLabel getLblHead() {
		if(lblHead == null) {
			lblHead = new ItemLabel();
			lblHead.setBounds(594, 148, 67, 77);
			lblHead.add(getLblSocketHead());
		}
		return lblHead;
	}
	
	private ItemLabel getLblHarcore() {
		if(lblHarcore == null) {
			lblHarcore = new ItemLabel();
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
			lblSocketMainHand.setBounds(0, 0, getLblMainHand().getWidth(), getLblMainHand().getHeight());
		}
		return lblSocketMainHand;
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
		TagsManagerFrame f = new TagsManagerFrame();
		f.setVisible(true);
	}

}
