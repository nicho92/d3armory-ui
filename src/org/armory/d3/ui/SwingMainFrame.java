package org.armory.d3.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.SplashScreen;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.net.URI;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.swing.DefaultRowSorter;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
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
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.TableRowSorter;
import javax.swing.tree.DefaultMutableTreeNode;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.armory.d3.services.D3ArmoryControler;
import org.armory.d3.services.D3ObjectRecorder;
import org.armory.d3.services.D3ProgressLeaderBoard;
import org.armory.d3.ui.components.*;
import org.armory.d3.ui.model.*;
import org.jdesktop.application.Application;

import com.pihen.d3restapi.beans.*;
import com.pihen.d3restapi.service.remote.exception.D3ServerCommunicationException;
import com.pihen.d3restapi.service.util.EnumerationStuff;
import com.pihen.d3restapi.service.util.StuffCalculator;


public class SwingMainFrame extends javax.swing.JFrame {

	private JMenuItem helpMenuItem;
	private JMenu jMenu5;
	private HeroPanel panneauDessinHero;
	private JScrollPane scrollFicheHeros;
	private JList<Hero> listeHeros;
	private JScrollPane scrollHeros;
	private JSplitPane splitPanneauFicheHero;
	private JProgressBar progressBar;
	private JPanel stateBar;
	private JPanel panneauInfoHero;
	private JTabbedPane ongletPane;
	private JTextField txtFiltrage;
	private JPanel panneauTableau;
	private JTable tableauExpert;
	private JScrollPane scrollTableau;
	private JSplitPane splitPanneauTableauHero;
	private ItemPanelDetails panelItemDetails;
	private JSplitPane splitTagsHeroes;

	private JScrollPane scrollTags;
	private JSplitPane jSplitPane1;
	private JMenuItem exitMenuItem;
	private JSeparator jSeparator2;
	private JMenuItem mnuSaveBuild;
	private JMenuItem newFileMenuItem;
	private JMenu jmiItemCreator;
	private JMenu jMenu3;
	private JMenuBar jMenuBar1;
	private ParangonPanel parangonPanel;
	private DefaultRowSorter sorter;
	private LootXlsTableModel mod ;
	private ListeHeroModel listeHerosModel;
	private TableauExpertModel tableaudetailModel;
	private JMenuItem jmiLocal;

	private Hero hero;
	private Map<EnumerationStuff,Item> stuffs;
	private FollowersPanel panelFollowers;
	private JScrollPane panneauTableauDescription;
	private JTable tableauDescriptionItems;
	private JScrollPane detailsPanel;
	private JTable tableauCalculator;
	private EHPPanel ehpPanel;
	private JPanel lootPanel;
	private JTable lootTable;
	private ListeTagTree tagsTree;
	private LadderPanel ladderPanel;
	TrayIcon trayIcon = new TrayIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/org/armory/d3/ui/resources/tab/herocomp.png")));
	final SystemTray tray = SystemTray.getSystemTray();
	private LootFactoryPanel lootFactoryPanel;
	private String msgUpdate="";
	private SeasonPanel seasonPanel;
	private JTabbedPane tabHeroes;
	
	
	static SwingMainFrame inst ;
	
	static final Logger logger = LogManager.getLogger(SwingMainFrame.class.getName());
	
	public ListeHeroModel getListeHerosModel() {
		return listeHerosModel;
	}

	
	
	public static void main(String[] args) {
		
	    final SplashScreen splash = SplashScreen.getSplashScreen();
	        if (splash != null) {
	             splash.createGraphics();
	        try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				logger.error(e.getStackTrace());
			}
	        splash.close();
	        }
	        
	        
	        
	   D3ArmoryControler.getInstance().initEnv();
	   
	   
	    SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				inst = new SwingMainFrame();
				inst.setLookAndFeel(D3ArmoryControler.getInstance().getProperty("look", UIManager.getSystemLookAndFeelClassName()));
						
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
				inst.toFront();
			}
		});
		
		
	}
	
	public SwingMainFrame() {
		super();
		initGUI();

	}
	
	private void initGUI() {
		
		
		
		try {
			logger.debug("Lancement de l'application");
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setTitle("Diablo III -ROS- Manager ");
			this.setIconImage(new ImageIcon(getClass().getResource("/org/armory/d3/ui/resources/icone.jpg")).getImage());
			setExtendedState(MAXIMIZED_BOTH);
			
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
					jSplitPane1.add(getSplitPanneauFicheHero(), JSplitPane.RIGHT);
					{
						scrollFicheHeros = new JScrollPane();
						splitPanneauFicheHero.add(scrollFicheHeros, JSplitPane.RIGHT);
						
						tabHeroes = new JTabbedPane();
						
						
						tabHeroes.addTab("",getSplitPanneauTableauHero());
						
						splitPanneauFicheHero.add(tabHeroes, JSplitPane.LEFT);
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
					jMenu3.setText("File");
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
						
						jmiItemCreator=new JMenu("Item Creator");
						jMenu3.add(jmiItemCreator);
						for(final EnumerationStuff e : EnumerationStuff.values())
						{
							JMenuItem it = new JMenuItem(e.toString());
							it.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent evt) {
										if(D3ArmoryControler.getInstance().getSelectedHero(false)!=null)
											new ItemCreatorFrame(new Item(), e).setVisible(true);
									
								}
							});
							
							jmiItemCreator.add(it);

						}
						mnuSaveBuild=new JMenuItem("Save Build");
						mnuSaveBuild.setEnabled(false);
						
						mnuSaveBuild.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								JOptionPane pane = new JOptionPane();
								String name = pane.showInputDialog("Build name ?");
								HeroSkillContainer hsc = D3ArmoryControler.getInstance().getSelectedHero(false).getSkills();
								hsc.setNameBuild(name);
								hsc.setClassBuild(D3ArmoryControler.getInstance().getSelectedHero(false).getClazz());
								try {
									D3ArmoryControler.getInstance().getRecorder().saveBuild(hsc);
								} catch (Exception e) {
									JOptionPane.showMessageDialog(null, e,"ERREUR",JOptionPane.ERROR_MESSAGE);
								}
								mnuSaveBuild.setEnabled(false);
						}
					});
						
						jMenu3.add(mnuSaveBuild);	
						
						
						JMenuItem mnuClearCache = new JMenuItem("Clear cache");
						mnuClearCache.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								for(File f : new File(D3ObjectRecorder.SERIALISATION_HERO_DIR).listFiles())
								{
									f.delete();
								}
								JOptionPane.showMessageDialog(null, "Clear complete","Cache Cleaner",JOptionPane.INFORMATION_MESSAGE);
						}
					});
						
						jMenu3.add(mnuClearCache);
						
						
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
									D3ArmoryControler.getInstance().setProperty("look",it.getText());
								}
							});
						jMenu4.add(it);
					}
					jMenu5 = new JMenu("?");
					jMenuBar1.add(jMenu5);
					jMenu5.setName("jMenu5");
						helpMenuItem = new JMenuItem("About");
						jMenu5.add(helpMenuItem);
						
						helpMenuItem.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
									AboutFrame f = new AboutFrame();
									f.setVisible(true);
									f.setLocationRelativeTo(null);
							}
						});
						
						JMenuItem report = new JMenuItem("Report Bug");
						jMenu5.add(report);
						report.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								try {
									Desktop.getDesktop().browse(new URI(D3ArmoryControler.SOURCE_REPOSITORY));
								} catch (Exception e) {
									logger.error(e.getStackTrace());
									JOptionPane.showMessageDialog(null, e,"Erreur",JOptionPane.ERROR_MESSAGE);

								}
						}
					});
						
						
				}
			}
			Application.getInstance().getContext().getResourceMap(getClass()).injectComponents(getContentPane());
			
			if (SystemTray.isSupported()) {
				tray.add(trayIcon);
				
				
				if(D3ArmoryControler.getInstance().hasUpdateVersionApp())
					msgUpdate = "New Version Available !!";
				
				trayIcon.displayMessage("D3 Armory Calculator","Application started\n"+msgUpdate,TrayIcon.MessageType.INFO);
				trayIcon.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent e) {
						if(!SwingMainFrame.inst.isVisible())
							SwingMainFrame.inst.setVisible(true);
						else
							SwingMainFrame.inst.setVisible(false);
					}
				});
			}
			JMenu mnuUpdate = new JMenu(msgUpdate);
			JMenuItem mnuDlUpdate = new JMenuItem("Download new Version");
			mnuUpdate.add(mnuDlUpdate);
			mnuDlUpdate.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					try {
						Desktop.getDesktop().browse(new URI(D3ArmoryControler.APP_DOWNLOAD));
					} catch (Exception e) {
						logger.error(e.getStackTrace());
						JOptionPane.showMessageDialog(null, e.getMessage(),"Erreur",JOptionPane.ERROR_MESSAGE);

					}
				}
			});
			
			jMenuBar1.add(mnuUpdate);	
			
		} catch (Exception e) {
			logger.error(e.getStackTrace());
			e.printStackTrace();
		}
	}
	
	public JMenuItem getMnuSaveBuild() {
		return mnuSaveBuild;
	}


	public HeroPanel getPanneauDessinHero() {
		if(panneauDessinHero==null)
			{
				panneauDessinHero=new HeroPanel();
			}
		return panneauDessinHero;
	}


	public JScrollPane getScrollTags() {
		return scrollTags;
	}
	
	public JSplitPane getSplitPanneauFicheHero() {
		return splitPanneauFicheHero;
	}
	
	public JScrollPane getScrollHeros() {
		return scrollHeros;
	}
	
	public JList<Hero> getListeHeros() {
		if(listeHeros==null)
		{
			listeHeros=new JList<Hero>();
			listeHeros.setValueIsAdjusting(true);
			listeHeros.setCellRenderer(new HeroCellRenderer());
			listeHeros.setBackground(Color.BLACK);
			listeHeros.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent evt) {
					listeHerosMouseClicked(evt);
				}
			});
		}
		
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
			    		  getPanneauDessinHero().getLblLoader().setIcon(new ImageIcon(getClass().getResource("/org/armory/d3/ui/resources/loading.gif")));
			    		  
			    		  chargementHero();
			    		  getPanneauDessinHero().setHero(hero);
				    	
			    		  tabHeroes.setTitleAt(0, hero.getName());
			    		  
			    		  getPanneauDessinHero().getLblLoader().setIcon(null);
			    		  panelItemDetails.setCalculator(D3ArmoryControler.getInstance().getCalculator());
			    		  lootFactoryPanel.init(getPanelItemDetails());
			    		  
			    		  int index = getOngletPane().getSelectedIndex();
							if(index==5)
							{
								loadFollowers();
							}
			    		  
			    		} 
			    	  catch (Exception e) {
			    		  logger.error(e);
			    		  e.printStackTrace();
			  		}
			      }
			  }).start();
	}
	public synchronized void chargementHero(){
		 try {
			 logger.debug("Chargement du hero " + hero);
//			 Hero cached = D3ArmoryControler.getInstance().loadHero(hero.getId());
//			 if(cached!=null)
//			 {
//				if(cached.getLastUpdated().intValue()>=hero.getLastUpdated().intValue())
//				{
//					logger.debug("Loading from cache");
//					initHeroItems(true);
//					D3ArmoryControler.getInstance().setSelectedHero(cached);
//					hero=cached;
//				}
//				else
//				{
//					logger.debug("old cache : reLoading from Battle.Net");
//					initHeroItems(false);
//					D3ArmoryControler.getInstance().saveHero(hero);
//				}
//			 }
//			 else
			
			 logger.debug("No cache : Loading from Battle.Net");
			 initHeroItems(false);
			 D3ArmoryControler.getInstance().getRecorder().saveHero(hero);
			getTableauDescriptionItems().setModel(new ItemsDetailModel());
			getPanneauEHP().getTable().setModel(new EHPCalculatorModel(D3ArmoryControler.getInstance().getCalculator()));
			
			getMnuSaveBuild().setEnabled(true);
			
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e,"ERREUR",JOptionPane.ERROR_MESSAGE);
			  logger.error(e.getStackTrace());
			  e.printStackTrace();
		}
	}
	
	
	public void loadFollowers() throws D3ServerCommunicationException
	{
		getLblstatbar().setValue(0);
		
		
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
			
			
			if(templar.getSkills().get(0)!=null){
				SkillRune r = new SkillRune();
						  r.setSkill(templar.getSkills().get(0).getSkill());
						  
				getFollowersPanel().getLblTemplarSkill1().setSize(SkillLabel.SMALL);
				getFollowersPanel().getLblTemplarSkill1().setSkillRune(r);
			}
			
			if(templar.getSkills().get(1)!=null){
				SkillRune r = new SkillRune();
						  r.setSkill(templar.getSkills().get(1).getSkill());
						  
				getFollowersPanel().getLblTemplarSkill2().setSize(SkillLabel.SMALL);
				getFollowersPanel().getLblTemplarSkill2().setSkillRune(r);
			}
			if(templar.getSkills().get(2)!=null){
				SkillRune r = new SkillRune();
						  r.setSkill(templar.getSkills().get(2).getSkill());
						  
				getFollowersPanel().getLblTemplarSkill3().setSize(SkillLabel.SMALL);
				getFollowersPanel().getLblTemplarSkill3().setSkillRune(r);
			}
			if(templar.getSkills().get(3)!=null){
				SkillRune r = new SkillRune();
						  r.setSkill(templar.getSkills().get(3).getSkill());
						  
				getFollowersPanel().getLblTemplarSkill4().setSize(SkillLabel.SMALL);
				getFollowersPanel().getLblTemplarSkill4().setSkillRune(r);
			}
			
		}
	
		getLblstatbar().setValue(4);
		
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
			
			

			if(scoundrel.getSkills().get(0)!=null){
				SkillRune r = new SkillRune();
						  r.setSkill(scoundrel.getSkills().get(0).getSkill());
						  
				getFollowersPanel().getLblScoundrelSkill1().setSize(SkillLabel.SMALL);
				getFollowersPanel().getLblScoundrelSkill1().setSkillRune(r);
			}
			
			if(scoundrel.getSkills().get(1)!=null){
				SkillRune r = new SkillRune();
						  r.setSkill(scoundrel.getSkills().get(1).getSkill());
						  
				getFollowersPanel().getLblScoundrelSkill2().setSize(SkillLabel.SMALL);
				getFollowersPanel().getLblScoundrelSkill2().setSkillRune(r);
			}
			
			if(scoundrel.getSkills().get(2)!=null){
				SkillRune r = new SkillRune();
						  r.setSkill(scoundrel.getSkills().get(2).getSkill());
						  
				getFollowersPanel().getLblScoundrelSkill3().setSize(SkillLabel.SMALL);
				getFollowersPanel().getLblScoundrelSkill3().setSkillRune(r);
			}
			
			if(scoundrel.getSkills().get(3)!=null){
				SkillRune r = new SkillRune();
						  r.setSkill(scoundrel.getSkills().get(3).getSkill());
						  
				getFollowersPanel().getLblScoundrelSkill4().setSize(SkillLabel.SMALL);
				getFollowersPanel().getLblScoundrelSkill4().setSkillRune(r);
			}
			
			
		}
		
		getLblstatbar().setValue(8);
		
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
		
			
			if(echanteress.getSkills().get(0)!=null){
				SkillRune r = new SkillRune();
						  r.setSkill(echanteress.getSkills().get(0).getSkill());
						  
				getFollowersPanel().getLblEnchanteressSkill1().setSize(SkillLabel.SMALL);
				getFollowersPanel().getLblEnchanteressSkill1().setSkillRune(r);
			}
			
			if(echanteress.getSkills().get(1)!=null){
				SkillRune r = new SkillRune();
						  r.setSkill(echanteress.getSkills().get(1).getSkill());
						  
				getFollowersPanel().getLblEnchanteressSkill2().setSize(SkillLabel.SMALL);
				getFollowersPanel().getLblEnchanteressSkill2().setSkillRune(r);
			}
			
			if(echanteress.getSkills().get(2)!=null){
				SkillRune r = new SkillRune();
						  r.setSkill(echanteress.getSkills().get(2).getSkill());
						  
				getFollowersPanel().getLblEnchanteressSkill3().setSize(SkillLabel.SMALL);
				getFollowersPanel().getLblEnchanteressSkill3().setSkillRune(r);
			}
			
			if(echanteress.getSkills().get(3)!=null){
				SkillRune r = new SkillRune();
						  r.setSkill(echanteress.getSkills().get(3).getSkill());
						  
				getFollowersPanel().getLblEnchanteressSkill4().setSize(SkillLabel.SMALL);
				getFollowersPanel().getLblEnchanteressSkill4().setSkillRune(r);
			}
		}
		getLblstatbar().setValue(13);
		
		getFollowersPanel().repaint();
		
		getLblstatbar().setValue(0);
		
	}
	
	private void initHeroItems(boolean iscache) throws D3ServerCommunicationException
	{
		int val=1;
		
		getLblstatbar().setValue(val);
		logger.debug("Chargement des items cache = " + iscache);
		
		hero=D3ArmoryControler.getInstance().getHeroDetails(hero);
		
		D3ArmoryControler.getInstance().setSelectedHero(hero);
		
		Item head ;
		if(iscache)
			head=hero.getItems().getHead();
		else
			head = D3ArmoryControler.getInstance().getInstance().getItemDetails(hero.getItems().getHead());
	
		getPanneauDessinHero().getLblHead().setItem(head,EnumerationStuff.HEAD);
		hero.getItems().setHead(head);
		getPanneauDessinHero().getLblSocketHead().setItem(head,0);
		getLblstatbar().setValue(val++);
				
		Item foot;
		if(iscache)
			foot=hero.getItems().getFeet();
		else
			foot= D3ArmoryControler.getInstance().getInstance().getItemDetails(hero.getItems().getFeet());
		
		getPanneauDessinHero().getLblFoot().setItem(foot,EnumerationStuff.FEET);
		getPanneauDessinHero().getLblSocketBoot().setItem(foot,0);
		hero.getItems().setFeet(foot);
		//	getLblstatbar().setString(String.valueOf(foot));
		getLblstatbar().setValue(val++);
		
		Item gants ;
		if(iscache)
			gants=hero.getItems().getHands();
		else
			gants= D3ArmoryControler.getInstance().getInstance().getItemDetails(hero.getItems().getHands());
		
		getPanneauDessinHero().getLblGants().setItem(gants,EnumerationStuff.GANT);
		getPanneauDessinHero().getLblSocketGants().setItem(gants,0);
		hero.getItems().setHands(gants);
		//	getLblstatbar().setString(String.valueOf(gants));
		getLblstatbar().setValue(val++);
		
		Item neck;
		if(iscache)
			neck=hero.getItems().getNeck();
		else
			neck= D3ArmoryControler.getInstance().getInstance().getItemDetails(hero.getItems().getNeck());
		
		getPanneauDessinHero().getLblNeck().setItem(neck,EnumerationStuff.NECK);
		getPanneauDessinHero().getLblSocketNeck().setItem(neck,0);
		hero.getItems().setNeck(neck);
	//	getLblstatbar().setString(String.valueOf(neck));
		getLblstatbar().setValue(val++);
		
		
		Item ringleft;
		if(iscache)	
			ringleft=hero.getItems().getLeftFinger();
		else
			ringleft = D3ArmoryControler.getInstance().getInstance().getItemDetails(hero.getItems().getLeftFinger());
		getPanneauDessinHero().getLblRingLeft().setItem(ringleft,EnumerationStuff.RING_LEFT);
		getPanneauDessinHero().getLblSocketLeftRing().setItem(ringleft,0);
		hero.getItems().setLeftFinger(ringleft);
	//	getLblstatbar().setString(String.valueOf(ringleft));
		getLblstatbar().setValue(val++);
		
		
		Item ringright;
		if(iscache)	
			ringright=hero.getItems().getRightFinger();
		else
			ringright= D3ArmoryControler.getInstance().getInstance().getItemDetails(hero.getItems().getRightFinger());
		getPanneauDessinHero().getLblRingRight().setItem(ringright,EnumerationStuff.RING_RIGHT);
		getPanneauDessinHero().getLblSocketRightRing().setItem(ringright,0);
		hero.getItems().setRightFinger(ringright);
	//	getLblstatbar().setString(String.valueOf(ringright));
		getLblstatbar().setValue(val++);
		
		Item mainHand;
		if(iscache) //TODO WHY getType is null ???
			mainHand=hero.getItems().get(EnumerationStuff.MAIN_HAND);
		else	
			mainHand = D3ArmoryControler.getInstance().getInstance().getItemDetails(hero.getItems().getMainHand());
		
	//	getLblstatbar().setString(String.valueOf(mainHand));
		getLblstatbar().setValue(val++);
		
		Item offhand ;
		if(iscache)
			offhand=hero.getItems().get(EnumerationStuff.OFF_HAND);
		else
			offhand = D3ArmoryControler.getInstance().getInstance().getItemDetails(hero.getItems().getOffHand());
		
		getPanneauDessinHero().getLblMainHand().setItem(mainHand,EnumerationStuff.MAIN_HAND);
		getPanneauDessinHero().getLblSocketMainHand().setItem(mainHand,0);
		
		hero.getItems().setMainHand(mainHand);
		hero.getItems().setOffHand(offhand);
		
	//	getLblstatbar().setString(String.valueOf(offhand));
		getLblstatbar().setValue(val++);
		
		getPanneauDessinHero().getLblOffHand().setDisabled(false);
		if(mainHand!=null)
		{
			if(mainHand.nbSockets()==2)
				getPanneauDessinHero().getLblSocketMainHand2().setItem(mainHand,1);
			else
				getPanneauDessinHero().getLblSocketMainHand2().setItem(null,1);
			
			if(mainHand.getType().getTwoHanded() && hero.getItems().getOffHand()==null)
			{
				getPanneauDessinHero().getLblOffHand().setItem(hero.getItems().getMainHand(),EnumerationStuff.OFF_HAND);
				getPanneauDessinHero().getLblOffHand().setDisabled(true);
			}
			else
			{	
				getPanneauDessinHero().getLblOffHand().setItem(offhand,EnumerationStuff.OFF_HAND);
				getPanneauDessinHero().getLblSocketOffHand().setItem(offhand,0);
			}
		}	
		
		if(offhand!=null)
		{
			getPanneauDessinHero().getLblOffHand().setItem(offhand,EnumerationStuff.OFF_HAND);
			getPanneauDessinHero().getLblSocketOffHand().setItem(offhand,0);
		}

		Item torso;
		if(iscache)
			torso=hero.getItems().getTorso();
		else
			torso = D3ArmoryControler.getInstance().getItemDetails(hero.getItems().getTorso());
		getPanneauDessinHero().getLblTorso().setItem(torso,EnumerationStuff.TORSO);
		hero.getItems().setTorso(torso);
		
		if(torso!=null)
		{
			if(torso.nbSockets()==0)
			{
				getPanneauDessinHero().getLblSocketTorso1().setItem(torso,0);
				getPanneauDessinHero().getLblSocketTorso2().setItem(torso,0);
				getPanneauDessinHero().getLblSocketTorso3().setItem(torso,0);
			}
			
			if(torso.nbSockets()==1)
			{
				getPanneauDessinHero().getLblSocketTorso1().setItem(torso,0);
				getPanneauDessinHero().getLblSocketTorso2().setItem(null,0);
				getPanneauDessinHero().getLblSocketTorso3().setItem(null,0);
			}
			
			
			if(torso.nbSockets()==2)
			{
				getPanneauDessinHero().getLblSocketTorso1().setItem(torso,0);
				getPanneauDessinHero().getLblSocketTorso2().setItem(torso,1);
				getPanneauDessinHero().getLblSocketTorso3().setItem(null,0);
			}
			
			if(torso.nbSockets()>2)
			{
				getPanneauDessinHero().getLblSocketTorso1().setItem(torso,0);
				getPanneauDessinHero().getLblSocketTorso2().setItem(torso,1);
				getPanneauDessinHero().getLblSocketTorso3().setItem(torso,2);
			}
		}
		else
		{
			getPanneauDessinHero().getLblSocketTorso1().setItem(null,0);
			getPanneauDessinHero().getLblSocketTorso2().setItem(null,0);
			getPanneauDessinHero().getLblSocketTorso3().setItem(null,0);
		}
	//	getLblstatbar().setString(String.valueOf(torso));
		getLblstatbar().setValue(val++);
		
		
		Item legs ;
		if(iscache)
			legs=hero.getItems().getLegs();
		else
			legs = D3ArmoryControler.getInstance().getItemDetails(hero.getItems().getLegs());
		getPanneauDessinHero().getLblLegs().setItem(legs,EnumerationStuff.LEGS);
		hero.getItems().setLegs(legs);
		
		if(legs!=null)
		{
			if(legs.nbSockets()==0)
			{
				getPanneauDessinHero().getLblSocketLegs1().setItem(legs,0);
				getPanneauDessinHero().getLblSocketLegs2().setItem(legs,0);
			}
			if(legs.nbSockets()==1)
			{
				getPanneauDessinHero().getLblSocketLegs1().setItem(legs,0);
				getPanneauDessinHero().getLblSocketLegs2().setItem(null,0);
			}
			
			if(legs.nbSockets()==2)
			{
				getPanneauDessinHero().getLblSocketLegs1().setItem(legs,0);
				getPanneauDessinHero().getLblSocketLegs2().setItem(legs,1);
			}
		}
		else
		{
			getPanneauDessinHero().getLblSocketLegs1().setItem(null,0);
			getPanneauDessinHero().getLblSocketLegs2().setItem(null,0);
		}
		
	//	getLblstatbar().setString(String.valueOf(legs));
		getLblstatbar().setValue(val++);
		
		Item shoulders;
		if(iscache)
			shoulders = hero.getItems().getShoulders();
		else
			shoulders= D3ArmoryControler.getInstance().getItemDetails(hero.getItems().getShoulders());
		getPanneauDessinHero().getLblShoulders().setItem(shoulders,EnumerationStuff.SHOULDERS);
		hero.getItems().setShoulders(shoulders);
	//	getLblstatbar().setString(String.valueOf(shoulders));
		getLblstatbar().setValue(val++);
		
		
		Item bracers ;
		if(iscache)
			bracers = hero.getItems().getBracers();
		else
			bracers = D3ArmoryControler.getInstance().getItemDetails(hero.getItems().getBracers());
		getPanneauDessinHero().getLblBracers().setItem(bracers,EnumerationStuff.BRACER);
		hero.getItems().setBracers(bracers);
	//	getLblstatbar().setString(String.valueOf(bracers));
		getLblstatbar().setValue(val++);
		
		Item belt;
		if(iscache)
			belt = hero.getItems().getWaist();
		else
			belt = D3ArmoryControler.getInstance().getItemDetails(hero.getItems().getWaist());
		getPanneauDessinHero().getLblbelt().setItem(belt,EnumerationStuff.BELT);
		hero.getItems().setWaist(belt);
	//	getLblstatbar().setString(String.valueOf(belt));
		getLblstatbar().setValue(val++);
		
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
		  
		  
		  logger.debug("Calculate Stats Formulas");
		  D3ArmoryControler.getInstance().initCalculator(stuffs);
		  
		  
		((TableauExpertModel)getTableauExpert().getModel()).fireTableDataChanged();
		
		if(hero.isHardcore())
			getPanneauDessinHero().getLblHarcore().setText("Hardcore");
		else
			getPanneauDessinHero().getLblHarcore().setText("");
		
		
		getPanneauDessinHero().getLblLife().setText(formatRessourceVisibleValue(hero.getStats().getLife()));
		
		if(hero.getClazz().equals("demon-hunter"))
			getPanneauDessinHero().getLblRessources().setText("<html>"+hero.getStats().getPrimaryResource()+"<br/>"+hero.getStats().getSecondaryResource()+"<html>");
		else
			getPanneauDessinHero().getLblRessources().setText(""+hero.getStats().getPrimaryResource());
		
		String cachedMsg;
		if(iscache)
			cachedMsg="(cached)";
		else
			cachedMsg="";
		getPanneauDessinHero().getLblLastUpdate().setText(new SimpleDateFormat("dd/MM/yyyy HH:mm").format(hero.getLastUpdatedDate())  + " " + cachedMsg);
		
		initHeroInfoPanel();
		getPanneauDessinHero().repaint();
		
		getLblstatbar().setValue(0);
		
		logger.debug("Fin de chargement des items");
	}

	
	private void initHeroInfoPanel()
	{
		logger.debug("Init panel");
		getPanneauInfoHero().removeAll();
		
		
		FormatedJLabel lbl1 = new FormatedJLabel();
		lbl1.addText(getDetailHero(0), "white","#BDA6CD");
		lbl1.applyText();
		getPanneauInfoHero().add(lbl1);
		
		FormatedJLabel lbl2 = new FormatedJLabel();
		lbl2.addText(getDetailHero(1), "white","#BDA6CD");
		lbl2.applyText();
		getPanneauInfoHero().add(lbl2);
		
		FormatedJLabel lbl3 = new FormatedJLabel();
		lbl3.addText(getDetailHero(2), "white","#BDA6CD");
		lbl3.applyText();
		getPanneauInfoHero().add(lbl3);
		
		FormatedJLabel lbl4 = new FormatedJLabel();
		lbl4.addText(getDetailHero(3), "white","#BDA6CD");
		lbl4.applyText();
		getPanneauInfoHero().add(lbl4);
		
		FormatedJLabel lbl5 = new FormatedJLabel();
		lbl5.addText(getDetailHero(4), "white","#BDA6CD");
		lbl5.applyText();
		getPanneauInfoHero().add(lbl5);
		
		FormatedJLabel lbl7 = new FormatedJLabel();
		lbl7.addText(getDetailHero(5), "white","#BDA6CD");
		lbl7.applyText();
		getPanneauInfoHero().add(lbl7);
		
		FormatedJLabel lbl6 = new FormatedJLabel();
		lbl6.addText(getDetailHero(6), "white","#BDA6CD");
		lbl6.applyText();
		getPanneauInfoHero().add(lbl6);
	
		getPanneauDessinHero().getLblSkill1().setSkillRune(hero.getSkills().getActive().get(0));
		getPanneauDessinHero().getLblSkill2().setSkillRune(hero.getSkills().getActive().get(1));
				
		getPanneauDessinHero().getLblSkill3().setSkillRune(hero.getSkills().getActive().get(2));
		getPanneauDessinHero().getLblSkill4().setSkillRune(hero.getSkills().getActive().get(3));
		getPanneauDessinHero().getLblSkill5().setSkillRune(hero.getSkills().getActive().get(4));
		getPanneauDessinHero().getLblSkill6().setSkillRune(hero.getSkills().getActive().get(5));
		
		int nbpassif =hero.getSkills().getPassive().size();
		
		if(hero.getSkills().getPassive().get(0)!=null)
		{
			getPanneauDessinHero().getLblSkill7().setSkillRune(hero.getSkills().getPassive().get(0));
			getPanneauDessinHero().getLblSkill7().initRightClick(0);
		}
		
		if(hero.getSkills().getPassive().get(1)!=null)
		{
			getPanneauDessinHero().getLblSkill8().setSkillRune(hero.getSkills().getPassive().get(1));
			getPanneauDessinHero().getLblSkill8().initRightClick(1);
		}
		
		if(hero.getSkills().getPassive().get(2)!=null)
		{
			getPanneauDessinHero().getLblSkill9().setSkillRune(hero.getSkills().getPassive().get(2));
			getPanneauDessinHero().getLblSkill9().initRightClick(2);
		}
		
		if(nbpassif>3)
			if(hero.getSkills().getPassive().get(3)!=null)
			{
				getPanneauDessinHero().getLblSkill10().setSkillRune(hero.getSkills().getPassive().get(3));
				getPanneauDessinHero().getLblSkill10().initRightClick(3);
			}
		logger.debug("Fin d'initialisation du panel");
	}
	
	
	public static String formatRessourceVisibleValue(double number) {
		if(number<1000)
			return String.valueOf(number);
		
	    String r = new DecimalFormat("##0E0").format(number);
	    r = r.replaceAll("E[0-9]", "K");
	    return r.length()>4 ?  r.replaceAll("\\.[0-9]+", "") : r;
	}
	
	private String getDetailHero(int val) {
		StringBuffer temp = new StringBuffer();
		try{
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
			temp.append("Saison : " + hero.getSeasonCreated() +" <br/>");
			temp.append("End Season Parangon : " + D3ArmoryControler.getInstance().getEndSeasonParangonLevelSC(D3ArmoryControler.getInstance().getSeason()).getLevel() +" <br/>");
		}
		if(val==5)
		{
			temp.append("Time Played <br/>");
			
			TimePlayed tp = D3ArmoryControler.getInstance().getCurrentProfil().getTimePlayed();
			
			for(String s : tp.getPercentClazz().keySet())
			{
				temp.append(s + ": " + StuffCalculator.format(tp.getPercentClazz().get(s)) +" %<br/>");
			}
			//temp.append("End HC Season Parangon : " + D3ArmoryControler.getInstance().getEndSeasonParangonLevelHC(1).getLevel() +" <br/>");
		}
		if(val==6)
		{
			temp.append("D3Progress Ladder <br/>");
			
			D3ProgressLeaderBoard leadbord = new D3ProgressLeaderBoard(D3ArmoryControler.getInstance().getConf(),D3ArmoryControler.getInstance().getSelectedHero(false));
				temp.append("Parangon World : " + leadbord.getWorldParangonLevel()+" <br/>");
				temp.append("Parangon Region: " + leadbord.getRegionalParangonLevel()+" <br/>");
				temp.append("DPS World : " + leadbord.getWorldDPS()+" <br/>");
				temp.append("DPS Region: " + leadbord.getRegionalDPS()+" <br/>");
			
		
			//temp.append("End HC Season Parangon : " + D3ArmoryControler.getInstance().getEndSeasonParangonLevelHC(1).getLevel() +" <br/>");
		}
		
		
		
		}
		catch(Exception e)
		{
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return temp.toString();
	}

	
	
	private void newFileMenuItemActionPerformed(ActionEvent evt) {
		TagsManagerFrame f = new TagsManagerFrame(getListeTagTree());
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
				scrollTags.setViewportView(getListeTagTree());
				
			}
			{
				scrollHeros = new JScrollPane();
				splitTagsHeroes.add(scrollHeros, JSplitPane.RIGHT);
				{
					listeHerosModel = new ListeHeroModel(); 
					listeHeros = getListeHeros();
					scrollHeros.setViewportView(listeHeros);
					listeHeros.setModel(listeHerosModel);
					
				}
			}
		}
		return splitTagsHeroes;
	}
	
	public ListeTagTree getListeTagTree() {
		if(tagsTree ==null)
			tagsTree =new ListeTagTree();
			tagsTree.addTreeSelectionListener(new TreeSelectionListener() {
				
				@Override
				public void valueChanged(TreeSelectionEvent e) {
					 DefaultMutableTreeNode node = (DefaultMutableTreeNode)tagsTree.getLastSelectedPathComponent();
					 if (node == null)
						 return;
					 
				    if (node.isLeaf()) {
					    String region = node.getPath()[1].toString();
					    String selected_row = node.getPath()[2].toString()+"#"+region;
						String[] parser = selected_row.split("#");
						try {
							
							D3ArmoryControler.getInstance().getProperty("local", "en_EN");
							Profile p = D3ArmoryControler.getInstance().getProfil(new Tag(parser[2], parser[0], Long.parseLong(parser[1])));
							D3ArmoryControler.getInstance().setProfile(p);
							
							
							
							getSeasonPanel().init(p.getSeasonalProfiles());
							
							getListeHeros().removeAll();
							for(Hero h : p.getHeroes())
							{
								getListeHerosModel().addElement(h);
								listeHeros.revalidate();
							}
							
						} catch (D3ServerCommunicationException ex) {
							JOptionPane.showMessageDialog(null, ex,"ERREUR",JOptionPane.ERROR_MESSAGE);
						}
					    } 
					 
				}
			});
		
		return tagsTree;
	}


	public ItemPanelDetails getPanelItemDetails() {
		if(panelItemDetails == null) {
			panelItemDetails = new ItemPanelDetails();
			panelItemDetails.setMaximumSize(new Dimension(0, 0));
			panelItemDetails.setLayout(null);
			
		}
		return panelItemDetails;
	}
	
	private JSplitPane getSplitPanneauTableauHero() {
		if(splitPanneauTableauHero == null) {
			splitPanneauTableauHero = new JSplitPane();
			
			splitPanneauTableauHero.setOrientation(JSplitPane.VERTICAL_SPLIT);
			splitPanneauTableauHero.add(getPanneauDessinHero(), JSplitPane.TOP);
			splitPanneauTableauHero.add(getOngletPane(), JSplitPane.BOTTOM);

				
		}
		return splitPanneauTableauHero;
	}
	
	private JScrollPane getScrollTableau() {
		if(scrollTableau == null) {
			scrollTableau = new JScrollPane();
			scrollTableau.setViewportView(getTableauExpert());
		}
		return scrollTableau;
	}
	
	private JTable getTableauExpert() {
		if(tableauExpert == null) {
			tableauExpert = new JTable();
			sorter = new TableRowSorter(getTableauDetailsModel());
			tableauExpert.setRowSorter(sorter);
			tableauExpert.setModel(getTableauDetailsModel());
			tableauExpert.setOpaque(true);
		}
		return tableauExpert;
	}


	public TableauExpertModel getTableauDetailsModel() {
		if(tableaudetailModel==null)
		{
			tableaudetailModel= new TableauExpertModel();
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
		
			ongletPane.addTab("Blizzard Profil", new ImageIcon(getClass().getResource("/org/armory/d3/ui/resources/tab/blizz.jpg")), getPanneauInfoHero(), null); //Jlabel block
			ongletPane.addTab("Items", new ImageIcon(getClass().getResource("/org/armory/d3/ui/resources/tab/item.png")), getPanneauTableauDescription(), null);
			ongletPane.addTab("Informations", new ImageIcon(getClass().getResource("/org/armory/d3/ui/resources/tab/stats.gif")),getPanneauDetails(),null);
			ongletPane.addTab("Season", new ImageIcon(getClass().getResource("/org/armory/d3/ui/resources/tab/season.png")),getSeasonPanel(),null);
			ongletPane.addTab("Detailed EHP", new ImageIcon(getClass().getResource("/org/armory/d3/ui/resources/tab/ehp.png")),getPanneauEHP(),null);
			ongletPane.addTab("Followers", new ImageIcon(getClass().getResource("/org/armory/d3/ui/resources/tab/follower.png")), getFollowersPanel(), null);
			ongletPane.addTab("Parangon", new ImageIcon(getClass().getResource("/org/armory/d3/ui/resources/tab/parangon.png")),getPanneauParangon(),null);
			ongletPane.addTab("Expert", new ImageIcon(getClass().getResource("/org/armory/d3/ui/resources/tab/expert.png")), getPanneauTableau(), null);
			ongletPane.addTab("Loot Drop Rate", new ImageIcon(getClass().getResource("/org/armory/d3/ui/resources/tab/loot.png")), getPanneauLoot(), null);
			ongletPane.addTab("Legendary Gem Evolution", new ImageIcon(getClass().getResource("/org/armory/d3/ui/resources/tab/leggem.png")), new GemEvolutionChancePanel(), null);
			ongletPane.addTab("Gem Calculator", new ImageIcon(getClass().getResource("/org/armory/d3/ui/resources/tab/gem.png")), new GemCalculatorPanel(), null);
			ongletPane.addTab("Hero Comparator", new ImageIcon(getClass().getResource("/org/armory/d3/ui/resources/tab/herocomp.png")), new HeroComparatorPanel(),null);
			ongletPane.addTab("Ladder", new ImageIcon(getClass().getResource("/org/armory/d3/ui/resources/tab/ranking.png")), getLadderPanel(),null);
			ongletPane.addTab("Loot Factory", new ImageIcon(getClass().getResource("/org/armory/d3/ui/resources/tab/kadala.png")), getLootFactoryPanel(),null);
			
			ongletPane.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {//on charge les followers lors du clique sur l'onglet
					
					JTabbedPane sourceTabbedPane = (JTabbedPane) e.getSource();
					
					int index=sourceTabbedPane.getSelectedIndex();
					if(index==5)
					{		
						
							new Thread(new Runnable() {
								
								@Override
								public void run() {
									try {
									loadFollowers();
									} catch (D3ServerCommunicationException e1) {
										logger.error(e1);
									}
								
									
									
								}
							}).start();
							
						
					}	
					
				}
			});
		}
		return ongletPane;
	}
	
	
	private JPanel getPanneauLoot() {
		if(lootPanel==null){
			lootPanel=new JPanel();
			BorderLayout panneauTableauLayout = new BorderLayout();
			lootPanel.setLayout(panneauTableauLayout);
			lootTable = new JTable();
			mod = new LootXlsTableModel();
		
			lootTable.setModel(mod);
			
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					mod.init();
					mod.fireTableDataChanged();
				}
			});
			
			final TableRowSorter sorter = new TableRowSorter(mod);
			final JTextField txtFilter = new JTextField("Filter");
			 lootTable.setRowSorter(sorter);
			 
			txtFilter.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent evt) {
					txtFilter.setText("");
				}
			});
			
			txtFilter.addActionListener(new ActionListener() {
			      public void actionPerformed(ActionEvent e) {
			          String text = txtFilter.getText();
			          if (text.length() == 0) {
			        	sorter.setRowFilter(null);
			          } else {
			        	sorter.setRowFilter(RowFilter.regexFilter(text));
			          }
			         
			        }
			      });
			
			JScrollPane lootScrollPanel = new JScrollPane();
					lootPanel.add(txtFilter,BorderLayout.NORTH);
					lootPanel.add(lootScrollPanel,BorderLayout.CENTER);
				
				
				
				lootScrollPanel.setViewportView(lootTable);
				
				
				lootTable.addMouseListener(new java.awt.event.MouseAdapter() {
				
					public void mouseClicked(java.awt.event.MouseEvent evt) {
						 int row = lootTable.rowAtPoint(evt.getPoint());
						 String id = (String)lootTable.getValueAt(row, 0);
						 Item i = new Item();
						
						 i.setTooltipParams("item/"+D3ArmoryControler.getInstance().refactorItem(id));
						 i = D3ArmoryControler.getInstance().getItemDetails(i);
						 ItemLabel lab = new ItemLabel();
					        lab.setItem(i, null);
					        getPanelItemDetails().getLblIcon().setIcon(lab.getIcon());
					        getPanelItemDetails().getLblIcon().repaint();
						 getPanelItemDetails().showItem(i);
					}    
				
				
				
				});
				
				
				
				return lootPanel;
			}
		return lootPanel;
	}


	public EHPPanel getPanneauEHP() {
		if(ehpPanel==null){
			ehpPanel=new EHPPanel();
			ehpPanel.setMinimumSize(new Dimension(0, 0));
		}
		return ehpPanel;
	}


	private JScrollPane getPanneauDetails() {
		if(detailsPanel==null){
			detailsPanel=new JScrollPane();
			detailsPanel.setMinimumSize(new Dimension(0, 0));
			detailsPanel.setViewportView(getTableauDetailsCalc());
		}
		return detailsPanel;
	}
	


	public JTable getTableauDetailsCalc() {
		
		if(tableauCalculator==null)
		{
			CalculatorModel mod = new CalculatorModel();
			sorter = new TableRowSorter(mod);
			tableauCalculator=new JTable(mod);
			tableauCalculator.setRowSorter(sorter);
			return (tableauCalculator);
		}
		else
		{
			return tableauCalculator;
		}
	}


	private JPanel getPanneauParangon() {
		if(parangonPanel==null){
			parangonPanel=new ParangonPanel();
					}
		return parangonPanel;
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
			panneauInfoHero.setMinimumSize(new Dimension(0,0));
			panneauInfoHero.setBackground(Color.black);
			panneauInfoHero.setLayout(new FlowLayout());
			
			
		}
		return panneauInfoHero;
	}
	
	public void setLookAndFeel(String lookAndFeel)
	{
		try {
			UIManager.setLookAndFeel(lookAndFeel);
			D3ArmoryControler.getInstance().setProperty("look",lookAndFeel);
		} catch (Exception e) {
			logger.error(e.getStackTrace());
		}
		SwingUtilities.updateComponentTreeUI(this);
	}
	

	
	
	
	private FollowersPanel getFollowersPanel() {
		if(panelFollowers == null) {
			panelFollowers = new FollowersPanel();
			
		}
		return panelFollowers;
	}
	
	public SeasonPanel getSeasonPanel()
	{
			if(seasonPanel==null)
				seasonPanel=new SeasonPanel();
			
			return seasonPanel;
	}
	
	
	public JPanel getStateBar() {
		if(stateBar == null) {
			stateBar = new JPanel();
			BorderLayout stateBarLayout = new BorderLayout();
			stateBar.setLayout(stateBarLayout);
			stateBar.add(getLblstatbar(),BorderLayout.CENTER);
		}
		return stateBar;
	}
	
	public JPanel getLootFactoryPanel() {
		if(lootFactoryPanel == null) {
			lootFactoryPanel = new LootFactoryPanel();
		}
		return lootFactoryPanel;
	}
	
	public JProgressBar getLblstatbar() {
		if(progressBar == null) {
			progressBar = new JProgressBar();
			progressBar.setMinimum(0);
			progressBar.setMaximum(13);
			progressBar.setStringPainted(true);
		}
		return progressBar;
	}
	private JScrollPane getPanneauTableauDescription() {
		if (panneauTableauDescription == null) {
			panneauTableauDescription = new JScrollPane();
			panneauTableauDescription.setPreferredSize(new Dimension(0, 0));
			panneauTableauDescription.setViewportView(getTableauDescriptionItems());
			
		}
		return panneauTableauDescription;
	}
	
	private LadderPanel getLadderPanel()
	{
		if(ladderPanel==null)
			ladderPanel=new LadderPanel();
		
		
		ladderPanel.getLadderTable().addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
		        int row = ladderPanel.getLadderTable().rowAtPoint(evt.getPoint());
		        Ladder i = ((LadderModel)ladderPanel.getLadderTable().getModel()).getLadderAt(row);
		        String selected_row = i.getProfile();
				String[] parser = selected_row.split("#");
				try {
					
					D3ArmoryControler.getInstance().getProperty("local", "en_EN");
					Profile p = D3ArmoryControler.getInstance().getProfil(new Tag(parser[2], parser[0], Long.parseLong(parser[1])));
					D3ArmoryControler.getInstance().setProfile(p);
					getListeHeros().removeAll();
					for(Hero h : p.getHeroes())
					{
						getListeHerosModel().addElement(h);
						listeHeros.revalidate();
					}
					
				} catch (D3ServerCommunicationException ex) {
					JOptionPane.showMessageDialog(null, ex,"ERREUR",JOptionPane.ERROR_MESSAGE);
				}
			    } 
		});
		
		return ladderPanel;
	}
	
	
	private JTable getTableauDescriptionItems() {
		if (tableauDescriptionItems == null) {
			tableauDescriptionItems = new JTable();
			tableauDescriptionItems.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent evt) {
			        int row = tableauDescriptionItems.rowAtPoint(evt.getPoint());
			        Item i = ((ItemsDetailModel)tableauDescriptionItems.getModel()).getItemAt(row);
			        getPanelItemDetails().showItem(i);
			        ItemLabel lab = new ItemLabel();
			        lab.setItem(i, null);
			        getPanelItemDetails().getLblIcon().setIcon(lab.getIcon());
			        getPanelItemDetails().getLblIcon().repaint();
			        
			        getPanelItemDetails().repaint(); 
			        
			        
			    }
			});
		}
		return tableauDescriptionItems;
	}
}
