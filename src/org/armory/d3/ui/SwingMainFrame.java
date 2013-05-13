package org.armory.d3.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.SplashScreen;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultRowSorter;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.table.TableRowSorter;

import org.armory.d3.beans.Follower;
import org.armory.d3.beans.FollowersList;
import org.armory.d3.beans.Hero;
import org.armory.d3.beans.Item;
import org.armory.d3.beans.Profile;
import org.armory.d3.services.D3ArmoryControler;
import org.armory.d3.services.StuffCalculator;
import org.armory.d3.ui.components.FollowersPanel;
import org.armory.d3.ui.components.FormatedJLabel;
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
	private JLabel lblstatbar;
	private JPanel stateBar;
	private JLabel lblLife;
	private JLabel lblLastUpdate;
	private JPanel panneauInfoHero;
	private JTabbedPane ongletPane;
	private JTextField txtFiltrage;
	private JPanel panneauTableau;
	private JTable tableauDetails;
	private JScrollPane scrollTableau;
	private JSplitPane splitPanneauTableauHero;
	private JLabel lblLoader;
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
	private JList<String> listeTags;
	private JScrollPane scrollTags;
	private JSplitPane jSplitPane1;
	private JMenuItem exitMenuItem;
	private JSeparator jSeparator2;
	private JMenuItem newFileMenuItem;
	private JMenuItem jmiItemCreator;
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
	private Hero hero;
	private JPanel panneauDPS;
	private Map<EnumerationStuff,Item> stuffs;
	private JLabel lblRessources;
	private FollowersPanel panelFollowers;

	
	public ListeHeroModel getListeHerosModel() {
		return listeHerosModel;
	}

	
	public static void main(String[] args) {
		
	    final SplashScreen splash = SplashScreen.getSplashScreen();
	        if (splash != null) {
	             splash.createGraphics();
	        try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	        splash.close();
	        }
	   
	    try{    
	    File repconf = new File("conf");
	    if(!repconf.exists())
	    {
	    	repconf.mkdir();
	    	new File(repconf.getAbsolutePath()+"/items").mkdir();
	    	new File(repconf.getAbsolutePath()+"/local.d3armory").createNewFile();
	    	new File(repconf.getAbsolutePath()+"/tags.d3armory").createNewFile();
	    	D3ArmoryControler.getInstance().setLocal("en_EN");
	    }
	    }
	    catch(IOException e)
	    {
	    	JOptionPane.showMessageDialog(null, e);
	    }
	        
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				SwingMainFrame inst = new SwingMainFrame();
				inst.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
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
			this.setSize(1820, 915);
			UIManager.put("Table.alternateRowColor", Color.decode("#E1E4F2"));
			BorderLayout thisLayout = new BorderLayout();
			getContentPane().setLayout(thisLayout);
			{
				
				jSplitPane1 = new JSplitPane();
				getContentPane().add(jSplitPane1, BorderLayout.CENTER);
				getContentPane().add(getStateBar(), BorderLayout.SOUTH);
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
				
					
					JMenu jMenu4 = new JMenu("Look");
					jMenuBar1.add(jMenu4);
					
					for(LookAndFeelInfo ui : UIManager.getInstalledLookAndFeels())
					{
						final JMenuItem it = new JMenuItem(ui.getClassName());
							it.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									setLookAndFeel(it.getText());
									
								}
							});
						jMenu4.add(it);
					}
					jMenu5 = new JMenu("?");
					jMenuBar1.add(jMenu5);
					jMenu5.setName("jMenu5");
					{
						helpMenuItem = new JMenuItem("About");
						jMenu5.add(helpMenuItem);
						
						helpMenuItem.setName("helpMenuItem");
						helpMenuItem.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
									AboutFrame f = new AboutFrame();
									f.setVisible(true);
									f.setLocationRelativeTo(null);
							}
						});
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
	
	private void listeHerosMouseClicked(MouseEvent evt)  {
		hero = (Hero)((JList) evt.getSource()).getSelectedValue();
		
			new Thread(new Runnable() {
			      public void run() {
			    	  try {	
			    		  getLblLoader().setIcon(new ImageIcon(getClass().getResource("/org/armory/d3/ui/resources/loading.gif")));
			    		  chargementHero();
			    		  Thread.sleep(1000);
			    		  getLblLoader().setIcon(null);
			    		} 
			    	  catch (Exception e) {
			  			e.printStackTrace();
			  		}
			      }
			  }).start();
	}
	
	public void chargementHero(){
		 try {
			lblstatbar.setText("Loading Item"); 
			loadItems();
			lblstatbar.setText("Loading Followers");
			loadFollowers();
			lblstatbar.setText("CalculDPS");
			refreshDPS();
			lblstatbar.setText("");
		} catch (D3ServerCommunicationException e) {
			e.printStackTrace();
		}
	}
	
	public void refreshDPS() 
	{
		getPanneauDPS().removeAll();
		FormatedJLabel lbl5 = new FormatedJLabel();
		lbl5.setHtmlText(getDetailDPS(), "white","#BDA6CD");
		getPanneauDPS().add(lbl5);
	}
	
	public void loadFollowers() throws D3ServerCommunicationException
	{
		FollowersList liste = hero.getFollowers();
		
		Follower templar = liste.getTemplar();
		
		if(templar!=null){
			Item neck = D3ArmoryControler.getInstance().getItemDetails(templar.getItems().getNeck());
			getFollowersPanel().getLblTemplarNeck().setItem(neck, EnumerationStuff.NECK);
			
			Item special = D3ArmoryControler.getInstance().getItemDetails(templar.getItems().getSpecial());
			getFollowersPanel().getLblTemplarObject().setItem(special, null);
			
			Item mh = D3ArmoryControler.getInstance().getItemDetails(templar.getItems().getMainHand());
			getFollowersPanel().getLblTemplarMH().setItem(mh, EnumerationStuff.MAIN_HAND);
			
			Item oh = D3ArmoryControler.getInstance().getItemDetails(templar.getItems().getOffHand());
			getFollowersPanel().getLblTemplarOH().setItem(oh, EnumerationStuff.OFF_HAND);
			
			Item r1 = D3ArmoryControler.getInstance().getItemDetails(templar.getItems().getRightFinger());
			getFollowersPanel().getLblTemplarRing1().setItem(r1, EnumerationStuff.RING_RIGHT);
	
			Item r2 = D3ArmoryControler.getInstance().getItemDetails(templar.getItems().getLeftFinger());
			getFollowersPanel().getLblTemplarRing2().setItem(r2, EnumerationStuff.RING_LEFT);
		}
	
		Follower scoundrel = liste.getScoundrel();
		if(scoundrel!=null)
		{
			Item neckS = D3ArmoryControler.getInstance().getItemDetails(scoundrel.getItems().getNeck());
			getFollowersPanel().getLblScoundrelNeck().setItem(neckS, EnumerationStuff.NECK);
			
			Item specialS = D3ArmoryControler.getInstance().getItemDetails(scoundrel.getItems().getSpecial());
			getFollowersPanel().getLblScoundrelObject().setItem(specialS, null);
			
			Item mhS = D3ArmoryControler.getInstance().getItemDetails(scoundrel.getItems().getMainHand());
			getFollowersPanel().getLblScoundrelMH().setItem(mhS, EnumerationStuff.MAIN_HAND);
			
			Item ohS = D3ArmoryControler.getInstance().getItemDetails(scoundrel.getItems().getOffHand());
			getFollowersPanel().getLblScoundrelOH().setItem(ohS, EnumerationStuff.OFF_HAND);
			if(mhS!=null)
			{
				if(mhS.getType().getTwoHanded() && ohS==null)
				{
					getFollowersPanel().getLblScoundrelOH().setItem(mhS, EnumerationStuff.OFF_HAND);
					getFollowersPanel().getLblScoundrelOH().setDisabled(true);
				}
				else
				{
					getFollowersPanel().getLblScoundrelOH().setItem(ohS, EnumerationStuff.OFF_HAND);
				}
			}
			
			Item r1S = D3ArmoryControler.getInstance().getItemDetails(scoundrel.getItems().getRightFinger());
			getFollowersPanel().getLblScoundrelRing1().setItem(r1S, EnumerationStuff.RING_RIGHT);
	
			Item r2S = D3ArmoryControler.getInstance().getItemDetails(scoundrel.getItems().getLeftFinger());
			getFollowersPanel().getLblScoundrelRing2().setItem(r2S, EnumerationStuff.RING_LEFT);
		}
		
		
		Follower echanteress = liste.getEnchantress();
		if(echanteress!=null)
		{
			Item neckE = D3ArmoryControler.getInstance().getItemDetails(echanteress.getItems().getNeck());
			getFollowersPanel().getLblEnchanteressNeck().setItem(neckE, EnumerationStuff.NECK);
			
			Item specialE = D3ArmoryControler.getInstance().getItemDetails(echanteress.getItems().getSpecial());
			getFollowersPanel().getLblEnchanteressObject().setItem(specialE, null);
			
			Item mhE = D3ArmoryControler.getInstance().getItemDetails(echanteress.getItems().getMainHand());
			getFollowersPanel().getLblEnchanteressMH().setItem(mhE, EnumerationStuff.MAIN_HAND);
			
			Item ohE = D3ArmoryControler.getInstance().getItemDetails(echanteress.getItems().getOffHand());
			getFollowersPanel().getLblEnchanteressOH().setItem(ohE, EnumerationStuff.OFF_HAND);
			
			if(mhE!=null)
			{
				if(mhE.getType().getTwoHanded() && ohE==null)
				{
					getFollowersPanel().getLblEnchanteressOH().setItem(mhE, EnumerationStuff.OFF_HAND);
					getFollowersPanel().getLblEnchanteressOH().setDisabled(true);
				}
				else
				{
					getFollowersPanel().getLblEnchanteressOH().setItem(ohE, EnumerationStuff.OFF_HAND);
				}
			}
			
			
			
			Item r1E = D3ArmoryControler.getInstance().getItemDetails(echanteress.getItems().getRightFinger());
			getFollowersPanel().getLblEnchanteressRing1().setItem(r1E, EnumerationStuff.RING_RIGHT);
	
			Item r2E = D3ArmoryControler.getInstance().getItemDetails(echanteress.getItems().getLeftFinger());
			getFollowersPanel().getLblEnchanteressRing2().setItem(r2E, EnumerationStuff.RING_LEFT);
		
		}
		getFollowersPanel().repaint();
		
	}
	
	
	
	private void loadItems() throws D3ServerCommunicationException
	{
		D3ArmoryControler.getInstance().setSelectedHero(hero);
		hero=D3ArmoryControler.getInstance().getHeroDetails(hero);
		lblNom.setText(hero.getName());
		getPanneauInfoHero().removeAll();
		lblInformationClasseNiveau.setText(hero.getClazz() +" Level : " + hero.getLevel());
		lblParangonLevel.setText("("+hero.getParagonLevel()+")");
		lblParangonLevel.setBounds(749, 20, 51, 16);

		
		FormatedJLabel lbl1 = new FormatedJLabel();
		lbl1.setHtmlText(getDetailHero(0), "white","#BDA6CD");
		getPanneauInfoHero().add(lbl1);
		
		FormatedJLabel lbl2 = new FormatedJLabel();
		lbl2.setHtmlText(getDetailHero(1), "white","#BDA6CD");
		getPanneauInfoHero().add(lbl2);
		
		FormatedJLabel lbl3 = new FormatedJLabel();
		lbl3.setHtmlText(getDetailHero(2), "white","#BDA6CD");
		getPanneauInfoHero().add(lbl3);
		
		FormatedJLabel lbl4 = new FormatedJLabel();
		lbl4.setHtmlText(getDetailHero(3), "white","#BDA6CD");
		getPanneauInfoHero().add(lbl4);
		
		FormatedJLabel lbl5 = new FormatedJLabel();
		lbl5.setHtmlText(getDetailHero(4), "white","#BDA6CD");
		getPanneauInfoHero().add(lbl5);
	
		lblstatbar.setText("Loading : skills");
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
		lblHead.setItem(head,EnumerationStuff.HEAD);
		lblSocketHead.setItem(head,0);
				
		Item foot = D3ArmoryControler.getInstance().getInstance().getItemDetails(hero.getItems().getFeet());
		lblFoot.setItem(foot,EnumerationStuff.FEET);
		lblSocketBoot.setItem(foot,0);
		
		Item gants = D3ArmoryControler.getInstance().getInstance().getItemDetails(hero.getItems().getHands());
		lblGants.setItem(gants,EnumerationStuff.GANT);
		lblSocketGants.setItem(gants,0);
		
		Item neck = D3ArmoryControler.getInstance().getInstance().getItemDetails(hero.getItems().getNeck());
		lblNeck.setItem(neck,EnumerationStuff.NECK);
		lblSocketNeck.setItem(neck,0);
		
		Item ringright = D3ArmoryControler.getInstance().getInstance().getItemDetails(hero.getItems().getLeftFinger());
		lblRingRight.setItem(ringright,EnumerationStuff.RING_RIGHT);
		lblSocketRightRing.setItem(ringright,0);
		
		Item ringleft = D3ArmoryControler.getInstance().getInstance().getItemDetails(hero.getItems().getRightFinger());
		lblRingLeft.setItem(ringleft,EnumerationStuff.RING_LEFT);
		lblSocketLeftRing.setItem(ringleft,0);
		

		Item mainHand = D3ArmoryControler.getInstance().getInstance().getItemDetails(hero.getItems().getMainHand());
	
		Item offhand = D3ArmoryControler.getInstance().getInstance().getItemDetails(hero.getItems().getOffHand());
		lblMainHand.setItem(mainHand,EnumerationStuff.MAIN_HAND);
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
				lblOffHand.setItem(hero.getItems().getMainHand(),EnumerationStuff.OFF_HAND);
				lblOffHand.setDisabled(true);
			}
			else
			{	
				lblOffHand.setItem(offhand,EnumerationStuff.OFF_HAND);
				lblSocketOffHand.setItem(offhand,0);
			}
		}	
		
		if(offhand!=null)
		{
			lblOffHand.setItem(offhand,EnumerationStuff.OFF_HAND);
			lblSocketOffHand.setItem(offhand,0);
		}

		Item torso = D3ArmoryControler.getInstance().getInstance().getItemDetails(hero.getItems().getTorso());
		lblTorso.setItem(torso,EnumerationStuff.TORSO);
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
		lblLegs.setItem(legs,EnumerationStuff.LEGS);
		
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
		
		Item shoulders= D3ArmoryControler.getInstance().getItemDetails(hero.getItems().getShoulders());
		lblShoulders.setItem(shoulders,EnumerationStuff.SHOULDERS);
		Item bracers = D3ArmoryControler.getInstance().getItemDetails(hero.getItems().getBracers());
		lblBracers.setItem(bracers,EnumerationStuff.BRACER);
		Item belt = D3ArmoryControler.getInstance().getItemDetails(hero.getItems().getWaist());
		lblbelt.setItem(belt,EnumerationStuff.BELT);
		
		stuffs = new HashMap<EnumerationStuff, Item>();
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
		
		D3ArmoryControler.getInstance().initCalculator(stuffs);
		
		((TableauDetailsModel)getTableauDetails().getModel()).fireTableDataChanged();
		
		if(hero.isHardcore())
			lblHarcore.setText("Hardcore");
		else
			lblHarcore.setText("");
		
		
		getLblLife().setText(format(hero.getStats().getLife()));
		
		if(hero.getClazz().equals("demon-hunter"))
			getLblRessources().setText("<html>"+hero.getStats().getPrimaryResource()+"<br/>"+hero.getStats().getSecondaryResource()+"<html>");
		else
			getLblRessources().setText(""+hero.getStats().getPrimaryResource());
		
		Date d = new Date();
		d.setTime(hero.getLastUpdated().longValue()*1000);
		getLblLastUpdate().setText(new SimpleDateFormat("dd/MM/yyyy HH:mm").format(d));
		
		
		panneauDessinHero.repaint();
	}
	
	private String format(double number) {
		if(number<1000)
			return String.valueOf(number);
		
	    String r = new DecimalFormat("##0E0").format(number);
	    r = r.replaceAll("E[0-9]", "k");
	    return r.length()>4 ?  r.replaceAll("\\.[0-9]+", "") : r;
	}
	
	private String getDetailDPS()
	{
		D3ArmoryControler.getInstance().getCalculator().calculate();
		Iterator<String> keys = D3ArmoryControler.getInstance().getCalculator().getStats().keySet().iterator();
		
		StringBuffer temp = new StringBuffer();
		while(keys.hasNext())
		{
			String key = keys.next();
			temp.append(key +" : " + StuffCalculator.format(D3ArmoryControler.getInstance().getCalculator().getStats().get(key)) +" <br/>" );
		}
		
		return temp.toString();
		
	}
	
	private String getDetailHero(int val) {
		StringBuffer temp = new StringBuffer();
		
		if(val==0){
			temp.append("Strength : " + hero.getStats().getStrength() +" <br/>");
			temp.append("Intel : " + hero.getStats().getIntelligence() +" <br/>");
			temp.append("Dex : " + hero.getStats().getDexterity() +" <br/>");
			temp.append("Vita : " + hero.getStats().getVitality() +" <br/>");
			temp.append("Armor : " + hero.getStats().getArmor() +" <br/>");
			temp.append("Life : " + hero.getStats().getLife() +" <br/>");
		}
		if(val==1)
		{
			temp.append("Physical Resist : " + hero.getStats().getPhysicalResist() +" <br/>");
			temp.append("Arcan Resist : " + hero.getStats().getArcaneResist() +" <br/>");
			temp.append("Cold Resist : " + hero.getStats().getColdResist() +" <br/>");
			temp.append("Light Resist : " + hero.getStats().getLightningResist() +" <br/>");
			temp.append("Fire Resist : " + hero.getStats().getFireResist() +" <br/>");
			temp.append("Poison Resist : " + hero.getStats().getPoisonResist() +" <br/>");
		}
		if(val==2)
		{
			temp.append("Blocage : " + hero.getStats().getBlockChance()*100 +"% <br/>");
			temp.append("Blocage Min : " + hero.getStats().getBlockAmountMin() +" <br/>");
			temp.append("Blocage Max : " + hero.getStats().getBlockAmountMax() +" <br/>");
			temp.append("Damage Reduc: " + StuffCalculator.format(hero.getStats().getDamageReduction()*100) +"% <br/>");
			temp.append("Magic Find : " + StuffCalculator.format(hero.getStats().getMagicFind()*100) +"% <br/>");
			temp.append("Gold Find : " + StuffCalculator.format(hero.getStats().getGoldFind()*100) +"% <br/>");
		}
		if(val==3)
		{
			temp.append("Chance Crit : " + StuffCalculator.format(hero.getStats().getCritChance()*100) +"% <br/>");
			temp.append("Crit Damage : " + StuffCalculator.format(hero.getStats().getCritDamage()*100) +"% <br/>");
			temp.append("Damage Increase : " + StuffCalculator.format(hero.getStats().getDamageIncrease()*100) +"% <br/>");
			temp.append("Life on hit : " + hero.getStats().getLifeOnHit() +" <br/>");
			temp.append("Life per Kill : " + hero.getStats().getLifePerKill() +" <br/>");
			temp.append("Life Steal : " + StuffCalculator.format(hero.getStats().getLifeSteal()*100) +"% <br/>");
		}
		if(val==4)
		{
			temp.append("DPS : " + hero.getStats().getDamage() +" <br/>");
			temp.append("Elites Kill : " + hero.getKills().getElites() +" <br/>");
			
		}
		
		return temp.toString();
	}


	private void listeTagMouseClicked(MouseEvent evt) {
		if(SwingUtilities.isRightMouseButton(evt))
		{
			listeTags.setSelectedIndex(listeTags.locationToIndex(evt.getPoint()));
			JPopupMenu popupMenu = new JPopupMenu();
			JMenuItem mnu = new JMenuItem("Delete");
			mnu.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					D3ArmoryControler.getInstance().removeTags(listeTags.getSelectedIndex());
					listeTags.removeAll();
					listeTags.setModel( new DefaultComboBoxModel(D3ArmoryControler.getInstance().getListTags().toArray()));
				}
			});
			popupMenu.add(mnu);
			popupMenu.show(evt.getComponent(),evt.getX(), evt.getY());
			return;
		}

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
			
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (D3ServerCommunicationException e) {
			e.printStackTrace();
		}
	}
	
	
	
	private JLabel getLblLoader()
	{
		if(lblLoader==null){
			lblLoader = new JLabel();
			lblLoader.setBounds(0, 0, 128, 128);
		}
		return lblLoader;
	}
	
	
	private SkillLabel getLblSkill1()
	{
		if(labSkilL1==null){
			labSkilL1 = new SkillLabel(false);
			labSkilL1.setBounds(42, 395, 64, 64);
		}
		return labSkilL1;
	}
	
	private SkillLabel getLblSkill2()
	{
		if(labSkilL2==null){
			labSkilL2 = new SkillLabel(false);
			labSkilL2.setBounds(108, 395, 64, 64);
		}
		return labSkilL2;
	}
	private SkillLabel getLblSkill3()
	{
		if(labSkilL3==null){
			labSkilL3 = new SkillLabel(false);
			labSkilL3.setBounds(42, 462, 64, 64);
		}
		return labSkilL3;
	}
	private SkillLabel getLblSkill4()
	{
		if(labSkilL4==null){
			labSkilL4 = new SkillLabel(false);
			labSkilL4.setBounds(108, 462, 64, 64);
		}
		return labSkilL4;
	}
	private SkillLabel getLblSkill5()
	{
		if(labSkilL5==null){
			labSkilL5 = new SkillLabel(false);
			labSkilL5.setBounds(174, 462, 64, 64);
		}
		return labSkilL5;
	}
	private SkillLabel getLblSkill6()
	{
		if(labSkilL6==null){
			labSkilL6 = new SkillLabel(false);
			labSkilL6.setBounds(240, 462, 64, 64);
		}
		return labSkilL6;
	}
	
	private SkillLabel getLblSkill7()
	{
		if(labSkilL7==null){
			labSkilL7 = new SkillLabel(true);
			labSkilL7.setBounds(42, 528, 64, 64);
		}
		return labSkilL7;
	}
	private SkillLabel getLblSkill8()
	{
		if(labSkilL8==null){
			labSkilL8 = new SkillLabel(true);
			labSkilL8.setBounds(108, 528, 64, 64);
		}
		return labSkilL8;
	}
	private SkillLabel getLblSkill9()
	{
		if(labSkilL9==null){
			labSkilL9 = new SkillLabel(true);
			labSkilL9.setBounds(174, 528, 64, 64);
		}
		return labSkilL9;
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
			lblMainHand.add(getLblSocketMainHand2());
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
	
//	protected void newItemCreatorActionPerformed(ActionEvent evt) {
//		ongletPane.setSelectedComponent(getPanneauDPS());
//		ItemCreatorFrame f = new ItemCreatorFrame();
//		f.setVisible(true);
//		
//		
//	}
	
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
								listeHerosMouseClicked(evt);
						}
					});
				}
			}
		}
		return splitTagsHeroes;
	}
	
	public ItemPanelDetails getPanelItemDetails() {
		if(panelItemDetails == null) {
			panelItemDetails = new ItemPanelDetails();
			panelItemDetails.setName("panelItemDetails");
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
				splitPanneauTableauHero.setPreferredSize(new java.awt.Dimension(980, 612));
				splitPanneauTableauHero.setSize(980, 645);
				splitPanneauTableauHero.add(panneauDessinHero, JSplitPane.TOP);
				splitPanneauTableauHero.add(getOngletPane(), JSplitPane.BOTTOM);

				panneauDessinHero.setLayout(null);
				panneauDessinHero.setSize(994, 645);
				panneauDessinHero.setName("panneauDessinHero");
				panneauDessinHero.setPreferredSize(new java.awt.Dimension(1000, 645));
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
				
				panneauDessinHero.add(getLblLoader());
				
				
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
					panneauDessinHero.add(getLblLastUpdate());
					panneauDessinHero.add(getLblLife());
					panneauDessinHero.add(getLblRessources());
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
			//scrollTableau.setPreferredSize(new java.awt.Dimension(977, 202));
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


	public TableauDetailsModel getTableauDetailsModel() {
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
	
	private JTabbedPane getOngletPane() {
		if(ongletPane == null) {
			ongletPane = new JTabbedPane();
			ongletPane.setPreferredSize(new java.awt.Dimension(0, 0));
			ongletPane.addTab("General", null, getPanneauInfoHero(), null);
			ongletPane.addTab("Stuff", null, getPanneauTableau(), null);
			ongletPane.addTab("Info", null, getPanneauDPS(), null);
			ongletPane.addTab("Followers", null, getFollowersPanel(), null);
		}
		return ongletPane;
	}
	
	public void reloadInfo()
	{
		
	}
	
	public JPanel getPanneauInfoHero() {
		if(panneauInfoHero == null) {
			panneauInfoHero = new JPanel() {
				
				protected void paintComponent(Graphics g) {
					super.paintComponent(g);
					Image bg = new ImageIcon(getClass().getResource("/org/armory/d3/ui/resources/bottom.jpg")).getImage();
					g.drawImage(bg,0,0,null);

				}
			};
			panneauInfoHero.setBackground(Color.black);
			panneauInfoHero.setLayout(new FlowLayout());
			
			
		}
		return panneauInfoHero;
	}
	
	private JPanel getPanneauDPS() {
		if(panneauDPS == null) {
			panneauDPS = new JPanel() {
				
				protected void paintComponent(Graphics g) {
					super.paintComponent(g);
					Image bg = new ImageIcon(getClass().getResource("/org/armory/d3/ui/resources/bottom.jpg")).getImage();
					g.drawImage(bg,0,0,null);

				}
			};
			panneauDPS.setBackground(Color.black);
			panneauDPS.setLayout(new FlowLayout(FlowLayout.LEFT));
			
			
		}
		return panneauDPS;
	}
	
	public void setLookAndFeel(String lookAndFeel)
	{
		try {
			UIManager.setLookAndFeel(lookAndFeel);
		} catch (Exception e) {
			e.printStackTrace();
		}
		SwingUtilities.updateComponentTreeUI(this);

	}
	
	private JLabel getLblLastUpdate() {
		if(lblLastUpdate == null) {
			lblLastUpdate = new JLabel();
			lblLastUpdate.setBounds(757, 583, 183, 16);
			lblLastUpdate.setForeground(Color.GREEN);
		}
		return lblLastUpdate;
	}
	
	private JLabel getLblLife() {
		if(lblLife == null) {
			lblLife = new JLabel() {

				public void paint(Graphics g) {
					Image bg = new ImageIcon(getClass().getResource("/org/armory/d3/ui/resources/ressource_life.png")).getImage();
					if(hero!=null)
						g.drawImage(bg,0,0,null);
					super.paintComponent(g);
				}
				
				
			};
			lblLife.setForeground(Color.WHITE);
			lblLife.setHorizontalAlignment(JLabel.CENTER);
			lblLife.setBounds(320, 540, 50, 50);

		}
		return lblLife;
	}
	
	private JLabel getLblRessources() {
		if(lblRessources == null) {
			lblRessources = new JLabel(){

				public void paint(Graphics g) {
					if(hero!=null)
					{
						Image bg = new ImageIcon(getClass().getResource("/org/armory/d3/ui/resources/ressource_"+hero.getClazz()+".png")).getImage();
						g.drawImage(bg,0,0,null);
					}
					super.paintComponent(g);
				}
			};
			lblRessources.setBounds(391, 540, 50, 50);
			lblRessources.setHorizontalAlignment(JLabel.CENTER);
			lblRessources.setForeground(Color.WHITE);
		}
		return lblRessources;
	}
	
	private FollowersPanel getFollowersPanel() {
		if(panelFollowers == null) {
			panelFollowers = new FollowersPanel();
		}
		return panelFollowers;
	}
	
	public JPanel getStateBar() {
		if(stateBar == null) {
			stateBar = new JPanel();
			FlowLayout stateBarLayout = new FlowLayout();
			stateBar.setLayout(stateBarLayout);
			stateBar.add(getLblstatbar());
		}
		return stateBar;
	}
	
	public JLabel getLblstatbar() {
		if(lblstatbar == null) {
			lblstatbar = new JLabel();
			lblstatbar.setName("lblstatbar");
		}
		return lblstatbar;
	}
}
