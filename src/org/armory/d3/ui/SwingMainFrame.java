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
	private PaintedLabel lblTorso;
	private PaintedLabel lblHarcore;
	private PaintedLabel lblHead;
	private PaintedLabel lblOffHand;
	private PaintedLabel lblMainHand;
	private PaintedLabel lblFoot;
	private PaintedLabel lblLegs;
	private PaintedLabel lblbelt;
	private PaintedLabel lblRingLeft;
	private PaintedLabel lblRingRight;
	private PaintedLabel lblBracers;
	private PaintedLabel lblNeck;
	private PaintedLabel lblGants;
	private PaintedLabel lblShoulders;
	private PaintedLabel lblParangonLevel;
	private PaintedLabel lblInformationClasseNiveau;
	private PaintedLabel lblNom;

	private JList listeTags;
	private JScrollPane scrollTags;
	private JSplitPane jSplitPane1;
	private JMenuItem exitMenuItem;
	private JSeparator jSeparator2;
	private JMenuItem saveMenuItem;
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
							
							listeHeros.addMouseListener(new MouseAdapter() {
								public void mouseClicked(MouseEvent evt) {
									try {
										listeHerosMouseClicked(evt);
									} catch (D3ServerCommunicationException e) {
										// TODO Auto-generated catch block
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

								panneauDessinHero.setPreferredSize(new java.awt.Dimension(989, 590));
								panneauDessinHero.setLayout(null);
								panneauDessinHero.setSize(994, 645);
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
									lblNom = new PaintedLabel();
									panneauDessinHero.add(lblNom);
									lblNom.setBounds(466, 80, 314, 43);
									lblNom.setName("lblNom");
									lblNom.setHorizontalAlignment(JLabel.CENTER);
								}
								{
									lblInformationClasseNiveau = new PaintedLabel();
									panneauDessinHero.add(lblInformationClasseNiveau);
									lblInformationClasseNiveau.setBounds(521, 20, 222, 16);
									lblInformationClasseNiveau.setName("lblInformationClasseNiveau");
									lblInformationClasseNiveau.setHorizontalAlignment(JLabel.HORIZONTAL);
								}
								{
									lblParangonLevel = new PaintedLabel();
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
								new DefaultComboBoxModel(
										new String[] { "nicho92#2603#eu" });
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
			this.setSize(1407, 858);
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
					}
					{
						saveMenuItem = new JMenuItem();
						jMenu3.add(saveMenuItem);
						saveMenuItem.setText("Save");
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
		lblHead.setItem(hero.getItems().getHead());
		lblGants.setItem(hero.getItems().getHands());
		lblShoulders.setItem(hero.getItems().getShoulders());
		lblNeck.setItem(hero.getItems().getNeck());
		lblBracers.setItem(hero.getItems().getBracers());
		lblTorso.setItem(hero.getItems().getTorso());
		lblRingRight.setItem(hero.getItems().getRightFinger());
		lblRingLeft.setItem(hero.getItems().getLeftFinger());
		lblbelt.setItem(hero.getItems().getWaist());
		lblLegs.setItem(hero.getItems().getLegs());
		lblFoot.setItem(hero.getItems().getFeet());
		lblMainHand.setItem(hero.getItems().getMainHand());
		
		D3ArmoryControler.getInstance().getConf().setItemId(hero.getItems().getMainHand().getItemID());
		RemoteService<Item> itemService = new SpringRemoteService(Item.class);
		Item mainHand = itemService.receiveEntity(D3ArmoryControler.getInstance().getConf());
		
		if(mainHand.getType().getTwoHanded()&&hero.getItems().getOffHand()==null)
			lblOffHand.setItem(hero.getItems().getMainHand());
		else	
			lblOffHand.setItem(hero.getItems().getOffHand());
			
		if(hero.isHardcore())
			lblHarcore.setText("Extrême");
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
	
	private PaintedLabel getLblShoulders() {
		if(lblShoulders == null) {
			lblShoulders = new PaintedLabel();
			lblShoulders.setBounds(502, 179, 75, 89);
		}
		return lblShoulders;
	}
	
	private PaintedLabel getLblGants() {
		if(lblGants == null) {
			lblGants = new PaintedLabel();
			lblGants.setBounds(490, 274, 61, 98);
		}
		return lblGants;
	}
	
	private PaintedLabel getLblNeck() {
		if(lblNeck == null) {
			lblNeck = new PaintedLabel();
			lblNeck.setBounds(679, 206, 56, 50);
		}
		return lblNeck;
	}
	
	private PaintedLabel getLblBracers() {
		if(lblBracers == null) {
			lblBracers = new PaintedLabel();
			lblBracers.setBounds(702, 282, 66, 90);
		}
		return lblBracers;
	}
	
	private PaintedLabel getLblTorso() {
		if(lblTorso == null) {
			lblTorso = new PaintedLabel();
			lblTorso.setBounds(589, 229, 78, 113);
		}
		return lblTorso;
	}
	
	private PaintedLabel getLblRingRight() {
		if(lblRingRight == null) {
			lblRingRight = new PaintedLabel();
			lblRingRight.setBounds(502, 385, 37, 43);
		}
		return lblRingRight;
	}
	
	private PaintedLabel getLblRingLeft() {
		if(lblRingLeft == null) {
			lblRingLeft = new PaintedLabel();
			lblRingLeft.setBounds(719, 381, 38, 41);
		}
		return lblRingLeft;
	}
	
	private PaintedLabel getLblbelt() {
		if(lblbelt == null) {
			lblbelt = new PaintedLabel();
			lblbelt.setBounds(589, 347, 78, 32);
		}
		return lblbelt;
	}
	
	private PaintedLabel getLblLegs() {
		if(lblLegs == null) {
			lblLegs = new PaintedLabel();
			lblLegs.setBounds(589, 391, 78, 84);
		}
		return lblLegs;
	}
	
	private PaintedLabel getLblFoot() {
		if(lblFoot == null) {
			lblFoot = new PaintedLabel();
			lblFoot.setBounds(589, 481, 78, 89);
		}
		return lblFoot;
	}
	
	private PaintedLabel getLblMainHand() {
		if(lblMainHand == null) {
			lblMainHand = new PaintedLabel();
			lblMainHand.setBounds(490, 434, 67, 136);
		}
		return lblMainHand;
	}
	
	private PaintedLabel getLblOffHand() {
		if(lblOffHand == null) {
			lblOffHand = new PaintedLabel();
			lblOffHand.setBounds(702, 434, 73, 133);
		}
		return lblOffHand;
	}
	
	private PaintedLabel getLblHead() {
		if(lblHead == null) {
			lblHead = new PaintedLabel();
			lblHead.setBounds(594, 146, 67, 77);
		}
		return lblHead;
	}
	
	private PaintedLabel getLblHarcore() {
		if(lblHarcore == null) {
			lblHarcore = new PaintedLabel();
			lblHarcore.setText("");
			lblHarcore.setBounds(539, 42, 180, 18);
			lblHarcore.setName("lblHarcore");
		}
		return lblHarcore;
	}

}
