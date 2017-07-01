package org.armory.d3.ui.components.panels;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.armory.d3.services.D3ArmoryControler;
import org.armory.d3.ui.components.ItemLabel;
import org.armory.d3.ui.components.SkillLabel;
import org.armory.d3.ui.components.SocketLabel;

import com.pihen.d3restapi.beans.Hero;
import com.pihen.d3restapi.service.util.EnumerationStuff;

public class HeroPanel extends JPanel {

	private SkillLabel labSkilL1;
	private SkillLabel labSkilL2;
	private SkillLabel labSkilL3;
	private SkillLabel labSkilL4;
	private SkillLabel labSkilL5;
	private SkillLabel labSkilL6;
	private SkillLabel labSkilL7;
	private SkillLabel labSkilL8;
	private SkillLabel labSkilL9;
	private SkillLabel labSkilL10;
	
	public SocketLabel lblSocketMainHand2;
	public SocketLabel lblSocketLegs2;
	public SocketLabel lblSocketLegs1;
	public SocketLabel lblSocketTorso3;
	public SocketLabel lblSocketTorso2;
	public SocketLabel lblSocketTorso1;
	public SocketLabel lblSocketOffHand;
	public SocketLabel lblSocketRightRing;
	public SocketLabel lblSocketLeftRing;
	public SocketLabel lblSocketNeck;
	public SocketLabel lblSocketGants;
	public SocketLabel lblSocketBoot;
	public SocketLabel lblSocketHead;
	public SocketLabel lblSocketMainHand;

	
	public ItemLabel lblHarcore;
	public ItemLabel lblHead;
	public ItemLabel lblOffHand;
	public ItemLabel lblMainHand;
	public ItemLabel lblFoot;
	public ItemLabel lblLegs;
	public ItemLabel lblbelt;
	public ItemLabel lblRingLeft;
	public ItemLabel lblRingRight;
	public ItemLabel lblBracers;
	public ItemLabel lblNeck;
	public ItemLabel lblGants;
	public ItemLabel lblTorso;
	public ItemLabel lblShoulders;
	
	public JLabel lblParangonLevel;
	public JLabel lblInformationClasseNiveau;
	public JLabel lblNom;
	private JLabel lblLastUpdate;
	private JLabel lblGuilde;
	
	private JLabel lblLoader;

	private JLabel lblLife;
	private JLabel lblRessources;

	private String imagePath;

	private Hero hero;
	
	
	@Override
	public String getName() {
		if(hero !=null)
			return hero.getName();
		
		return super.getName();
	}
	
	public void setHero(Hero h)
	{
		this.hero=h;
		getLblNomHero().setText(hero.getName());
		getLblInformationClasseNiveau().setText(hero.getClazz() +" Level : " + hero.getLevel());
		getLblParangonLevel().setText("("+hero.getParagonLevel()+")");
		getLblGuild().setText("<"+D3ArmoryControler.getInstance().getCurrentProfil().getGuildName()+">");
	}
	
	
	public void initPane()
	{
		add(getLblHead());
		add(getLblShoulders());
		add(getLblNeck());
		add(getLblGants());
		add(getLblTorso());
		add(getLblBracers());
		add(getLblbelt());
		add(getLblLegs());
		add(getLblFoot());
		add(getLblRingLeft());
		add(getLblRingRight());
		add(getLblMainHand());
		add(getLblOffHand());
		//click
		add(getLblSkill1());
		add(getLblSkill2());
		//actif
		add(getLblSkill3());
		add(getLblSkill4());
		add(getLblSkill5());
		add(getLblSkill6());
				
		//passif
		add(getLblSkill7());
		add(getLblSkill8());
		add(getLblSkill9());
		add(getLblSkill10());
		
		
		add(getLblNomHero());
		add(getLblInformationClasseNiveau());
		add(getLblParangonLevel());
		add(getLblHarcore());
		add(getLblLastUpdate());
		add(getLblLoader());
		add(getLblGuild());

		add(getLblLife());
		add(getLblRessources());
	}
	
	public HeroPanel()
	{
		setLayout(null);
		//setPreferredSize(new java.awt.Dimension(1055, 556));
		setPreferredSize(new java.awt.Dimension(1000, 645));
		initPane();
	
		
	
	}
	
	public JLabel getLblParangonLevel() {
		if(lblParangonLevel==null)
		{
			lblParangonLevel=new JLabel();
			
			lblParangonLevel.setBounds(749, 20, 51, 16);

			lblParangonLevel.setHorizontalAlignment(JLabel.HORIZONTAL);
			Font ft = new Font("Palatino Linotype",Font.PLAIN,14);
			lblParangonLevel.setForeground(new Color(165,145,194));
			lblParangonLevel.setFont(ft);
		}
		
		return lblParangonLevel;
	}

	public JLabel getLblLoader()
	{
		if(lblLoader==null){
			lblLoader = new JLabel();
			lblLoader.setBounds(0, 0, 128, 128);
		}
		return lblLoader;
	}
	

	public JLabel getLblInformationClasseNiveau() {
		if(lblInformationClasseNiveau==null)
		{
			lblInformationClasseNiveau=new JLabel();
			lblInformationClasseNiveau.setBounds(521, 20, 222, 16);
			lblInformationClasseNiveau.setHorizontalAlignment(JLabel.HORIZONTAL);
			Font ft = new Font("Palatino Linotype",Font.BOLD,14);
			lblInformationClasseNiveau.setForeground(new Color(212,180,115));
			lblInformationClasseNiveau.setFont(ft);
		}
		
		return lblInformationClasseNiveau;
	}


	public JLabel getLblLastUpdate() {
		if(lblLastUpdate == null) {
			lblLastUpdate = new JLabel();
			lblLastUpdate.setBounds(757, 583, 183, 16);
			lblLastUpdate.setForeground(Color.GREEN);
		}
		return lblLastUpdate;
	}
	



	public JLabel getLblNomHero() {
		if(lblNom == null) {
			lblNom = new JLabel();
			lblNom.setForeground(Color.WHITE);
			Font ft = new Font("Palatino Linotype",Font.BOLD,36);
			lblNom.setFont(ft);
			lblNom.setBounds(466, 80, 314, 43);
			lblNom.setHorizontalAlignment(JLabel.CENTER);
		}
		return lblNom;
	}
	
	public JLabel getLblGuild() {
		if(lblGuilde == null) {
			lblGuilde = new JLabel();
			lblGuilde.setForeground(Color.GREEN);
			Font ft = new Font("Palatino Linotype",Font.BOLD,20);
			lblGuilde.setFont(ft);
			lblGuilde.setBounds(476, 123, 292, 23);
			lblGuilde.setHorizontalAlignment(JLabel.CENTER);
		}
		return lblGuilde;
	}
	
	
	public JLabel getLblRessources() {
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
	
	public JLabel getLblLife() {
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
	
	public SkillLabel getLblSkill1()
	{
		if(labSkilL1==null){
			labSkilL1 = new SkillLabel(false);
			labSkilL1.setBounds(42, 395, 64, 64);
		}
		return labSkilL1;
	}
	public SkillLabel getLblSkill2()
	{
		if(labSkilL2==null){
			labSkilL2 = new SkillLabel(false);
			labSkilL2.setBounds(108, 395, 64, 64);
		}
		return labSkilL2;
	}
	public SkillLabel getLblSkill3()
	{
		if(labSkilL3==null){
			labSkilL3 = new SkillLabel(false);
			labSkilL3.setBounds(42, 462, 64, 64);
		}
		return labSkilL3;
	}
	public SkillLabel getLblSkill4()
	{
		if(labSkilL4==null){
			labSkilL4 = new SkillLabel(false);
			labSkilL4.setBounds(108, 462, 64, 64);
		}
		return labSkilL4;
	}
	public SkillLabel getLblSkill5()
	{
		if(labSkilL5==null){
			labSkilL5 = new SkillLabel(false);
			labSkilL5.setBounds(174, 462, 64, 64);
		}
		return labSkilL5;
	}
	public SkillLabel getLblSkill6()
	{
		if(labSkilL6==null){
			labSkilL6 = new SkillLabel(false);
			labSkilL6.setBounds(240, 462, 64, 64);
		}
		return labSkilL6;
	}
	public SkillLabel getLblSkill7()
	{
		if(labSkilL7==null){
			labSkilL7 = new SkillLabel(true);
			labSkilL7.setBounds(42, 528, 64, 64);
		}
		return labSkilL7;
	}
	public SkillLabel getLblSkill8()
	{
		if(labSkilL8==null){
			labSkilL8 = new SkillLabel(true);
			labSkilL8.setBounds(108, 528, 64, 64);
		}
		return labSkilL8;
	}
	public SkillLabel getLblSkill9()
	{
		if(labSkilL9==null){
			labSkilL9 = new SkillLabel(true);
			labSkilL9.setBounds(174, 528, 64, 64);
		}
		return labSkilL9;
	}
	public SkillLabel getLblSkill10()
	{
		if(labSkilL10==null){
			labSkilL10 = new SkillLabel(true);
			labSkilL10.setBounds(240, 528, 64, 64);
		}
		return labSkilL10;
	}
	
	
	
	
	
	public ItemLabel getLblShoulders() {
		if(lblShoulders == null) {
			lblShoulders = new ItemLabel();
			lblShoulders.setBounds(502, 179, 75, 89);
			lblShoulders.setGear(EnumerationStuff.SHOULDERS);
			lblShoulders.enabledDropable(true);
		}
		return lblShoulders;
	}
	
	public ItemLabel getLblGants() {
		if(lblGants == null) {
			lblGants = new ItemLabel();
			lblGants.setBounds(490, 274, 61, 98);
			lblGants.add(getLblSocketGants());
			lblGants.setGear(EnumerationStuff.GANT);
			lblGants.enabledDropable(true);
		}
		return lblGants;
	}
	
	public ItemLabel getLblNeck() {
		if(lblNeck == null) {
			lblNeck = new ItemLabel();
			lblNeck.setBounds(679, 206, 56, 50);
			lblNeck.add(getLblSocketNeck());
			lblNeck.setGear(EnumerationStuff.NECK);
			lblNeck.enabledDropable(true);
		}
		return lblNeck;
	}
	
	public ItemLabel getLblBracers() {
		if(lblBracers == null) {
			lblBracers = new ItemLabel();
			lblBracers.setBounds(702, 282, 66, 90);
			lblBracers.setGear(EnumerationStuff.BRACER);
			lblBracers.enabledDropable(true);
		}
		return lblBracers;
	}
	
	public ItemLabel getLblTorso() {
		if(lblTorso == null) {
			lblTorso = new ItemLabel();
			lblTorso.setBounds(589, 229, 78, 113);
			lblTorso.add(getLblSocketTorso1());
			lblTorso.add(getLblSocketTorso2());
			lblTorso.add(getLblSocketTorso3());
			lblTorso.setGear(EnumerationStuff.TORSO);
			lblTorso.enabledDropable(true);
		}
		return lblTorso;
	}
	
	public ItemLabel getLblRingRight() {
		if(lblRingRight == null) {
			lblRingRight = new ItemLabel();
			lblRingRight.setBounds(502, 385, 37, 37);
			lblRingRight.add(getLblSocketRightRing());
			lblRingRight.setGear(EnumerationStuff.RING_RIGHT);
			lblRingRight.enabledDropable(true);
		}
		return lblRingRight;
	}
	
	public ItemLabel getLblRingLeft() {
		if(lblRingLeft == null) {
			lblRingLeft = new ItemLabel();
			lblRingLeft.setBounds(719, 381, 38, 41);
			lblRingLeft.add(getLblSocketLeftRing());
			lblRingLeft.setGear(EnumerationStuff.RING_LEFT);
			lblRingLeft.enabledDropable(true);
		}
		return lblRingLeft;
	}
	
	public ItemLabel getLblbelt() {
		if(lblbelt == null) {
			lblbelt = new ItemLabel();
			lblbelt.setBounds(589, 347, 78, 32);
			lblbelt.setGear(EnumerationStuff.BELT);
			lblbelt.enabledDropable(true);
		}
		return lblbelt;
	}
	
	public ItemLabel getLblLegs() {
		if(lblLegs == null) {
			lblLegs = new ItemLabel();
			lblLegs.setBounds(589, 391, 78, 84);
			lblLegs.add(getLblSocketLegs1());
			lblLegs.add(getLblSocketLegs2());
			lblLegs.setGear(EnumerationStuff.LEGS);
			lblLegs.enabledDropable(true);
		}
		return lblLegs;
	}
	
	public ItemLabel getLblFoot() {
		if(lblFoot == null) {
			lblFoot = new ItemLabel();
			lblFoot.setBounds(589, 481, 78, 89);
			lblFoot.add(getLblSocketBoot());
			lblFoot.setGear(EnumerationStuff.FEET);
			lblFoot.enabledDropable(true);
		}
		return lblFoot;
	}
	
	public ItemLabel getLblMainHand() {
		if(lblMainHand == null) {
			lblMainHand = new ItemLabel();
			lblMainHand.setBounds(490, 434, 67, 136);
			lblMainHand.add(getLblSocketMainHand());
			lblMainHand.add(getLblSocketMainHand2());
			lblMainHand.setGear(EnumerationStuff.MAIN_HAND);
			lblMainHand.enabledDropable(true);
		}
		return lblMainHand;
	}
	
	public ItemLabel getLblOffHand() {
		if(lblOffHand == null) {
			lblOffHand = new ItemLabel();
			lblOffHand.setBounds(702, 434, 73, 133);
			lblOffHand.add(getLblSocketOffHand());
			lblOffHand.setGear(EnumerationStuff.OFF_HAND);
			lblOffHand.enabledDropable(true);
		}
		return lblOffHand;
	}
	
	public ItemLabel getLblHead() {
		if(lblHead == null) {
			lblHead = new ItemLabel();
			lblHead.setBounds(594, 148, 67, 77);
			lblHead.add(getLblSocketHead());
			lblHead.setGear(EnumerationStuff.HEAD);
			lblHead.enabledDropable(true);
		}
		return lblHead;
	}
	
	public ItemLabel getLblHarcore() {
		if(lblHarcore == null) {
			lblHarcore = new ItemLabel();
			lblHarcore.setText("");
			lblHarcore.setBounds(539, 42, 180, 18);
			lblHarcore.setForeground(Color.RED);
			lblHarcore.setName("lblHarcore");
		}
		return lblHarcore;
	}
	
	public SocketLabel getLblSocketHead() {
		if(lblSocketHead == null) {
			lblSocketHead = new SocketLabel();
			lblSocketHead.setBounds(0, 0, getLblHead().getWidth(), getLblHead().getHeight());
			lblSocketHead.setName("lblSocketHead");
		}
		return lblSocketHead;
	}
	
	public SocketLabel getLblSocketBoot() {
		if(lblSocketBoot == null) {
			lblSocketBoot = new SocketLabel();
			lblSocketBoot.setBounds(0, 0, getLblFoot().getWidth(), getLblFoot().getHeight());
			lblSocketBoot.setName("lblSocketBoot");
		}
		return lblSocketBoot;
	}
	
	public SocketLabel getLblSocketGants() {
		if(lblSocketGants == null) {
			lblSocketGants = new SocketLabel();
			lblSocketGants.setBounds(0, 0, getLblGants().getWidth(), getLblGants().getHeight());
		}
		return lblSocketGants;
	}
	
	public SocketLabel getLblSocketNeck() {
		if(lblSocketNeck == null) {
			lblSocketNeck = new SocketLabel();
			lblSocketNeck.setBounds(0, 0,  getLblNeck().getWidth(), getLblNeck().getHeight());
		}
		return lblSocketNeck;
	}
	
	public SocketLabel getLblSocketLeftRing() {
		if(lblSocketLeftRing == null) {
			lblSocketLeftRing = new SocketLabel();
			lblSocketLeftRing.setBounds(0, 0,  getLblRingLeft().getWidth(), getLblRingLeft().getHeight());
		}
		return lblSocketLeftRing;
	}
	
	public SocketLabel getLblSocketRightRing() {
		if(lblSocketRightRing == null) {
			lblSocketRightRing = new SocketLabel();
			lblSocketRightRing.setBounds(0,0,  getLblRingRight().getWidth(), getLblRingRight().getHeight());
		}
		return lblSocketRightRing;
	}
	
	public SocketLabel getLblSocketMainHand() {
		if(lblSocketMainHand == null) {
			lblSocketMainHand = new SocketLabel();
			lblSocketMainHand.setBounds(0, 40, getLblMainHand().getWidth(), 38);
		}
		return lblSocketMainHand;
	}
	
	public SocketLabel getLblSocketMainHand2() {
		if(lblSocketMainHand2 == null) {
			lblSocketMainHand2 = new SocketLabel();
			lblSocketMainHand2.setBounds(0, 80, getLblMainHand().getWidth(), 38);
		}
		return lblSocketMainHand2;
	}
	public SocketLabel getLblSocketOffHand() {
		if(lblSocketOffHand == null) {
			lblSocketOffHand = new SocketLabel();
			lblSocketOffHand.setBounds(0, 0, getLblOffHand().getWidth(), getLblOffHand().getHeight());
		}
		return lblSocketOffHand;
	}
	
	public SocketLabel getLblSocketTorso1() {
		if(lblSocketTorso1 == null) {
			lblSocketTorso1 = new SocketLabel();
			lblSocketTorso1.setBounds(0, 0, getLblTorso().getWidth(), 38);
		}
		return lblSocketTorso1;
	}
	
	public SocketLabel getLblSocketTorso2() {
		if(lblSocketTorso2 == null) {
			lblSocketTorso2 = new SocketLabel();
			lblSocketTorso2.setBounds(0, 37, getLblTorso().getWidth(), 38);
		}
		return lblSocketTorso2;
	}
	
	public SocketLabel getLblSocketTorso3() {
		if(lblSocketTorso3 == null) {
			lblSocketTorso3 = new SocketLabel();
			lblSocketTorso3.setBounds(0, 74, getLblTorso().getWidth(), 38);
		}
		return lblSocketTorso3;
	}
	
	public SocketLabel getLblSocketLegs1() {
		if(lblSocketLegs1 == null) {
			lblSocketLegs1 = new SocketLabel();
			lblSocketLegs1.setBounds(0, 10, getLblLegs().getWidth(), 35);

		}
		return lblSocketLegs1;
	}
	
	public SocketLabel getLblSocketLegs2() {
		if(lblSocketLegs2 == null) {
			lblSocketLegs2 = new SocketLabel();
			lblSocketLegs2.setBounds(0, 45, getLblLegs().getWidth(), 28);
		}
		return lblSocketLegs2;
	}
	
	
	
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Hero hero=null;
		
			hero = D3ArmoryControler.getInstance().getSelectedHero(false);
		
		if(hero!=null){
				String classe = hero.getClazz();
				String sexe = hero.getSexe();
				
				if(classe.equals("necromancer"))
					classe="necro";
				
				imagePath="http://eu.battle.net/d3/static/images/profile/hero/paperdoll/"+classe+"-"+sexe+".jpg";
				Image image = null;
		
			 	try 
			 	{
			 	    URL url = new URL(imagePath);
			 	    image = ImageIO.read(url);
			 	    g.drawImage(image, 0, 0, null);
			 	  
			 	} 
			 	catch (IOException e) 
			 	{
			 		System.out.println(imagePath);
			 	   e.printStackTrace();
			 	}
		}
		else
		{
			Image bg = new ImageIcon(getClass().getResource("/org/armory/d3/ui/resources/default.jpg")).getImage();
			g.drawImage(bg,0,0,null);
		}
		
		// g.drawImage(new ImageIcon(getClass().getResource("/org/armory/d3/ui/resources/inventory-lines.png")).getImage(),462,167,null);
	}

}
