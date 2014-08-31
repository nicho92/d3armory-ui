package org.armory.d3.ui.components;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Paint;
import java.text.DecimalFormat;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import com.pihen.d3restapi.beans.AttributsContainer;
import com.pihen.d3restapi.beans.DisplayableItemAttributs;
import com.pihen.d3restapi.beans.Item;
import com.pihen.d3restapi.beans.LegendarySet;
import com.pihen.d3restapi.beans.Ranks;
import com.pihen.d3restapi.service.util.StuffCalculator;
import java.awt.Component;


public class ItemPanelDetails extends JPanel {

	private Item item;
	private JLabel lblIcon;
	private JLabel lblNomItem;
	private JTextPane lblTextItem;
	private JLabel lblStatArmorDPS;
	private JLabel lblTypeItemAD;
	private JLabel lblTypeItem;
	private FormatedJLabel lblDetailWeapon;
	private FormatedJLabel lblItemLevel;
	private JLabel lblDetailSet;
	private FormatedJLabel lblDetailItem;
	private SocketLabel lblSock1;
	private SocketLabel lblSock2;
	private SocketLabel lblSock3;
	private boolean flavEnable=true;
	private StuffCalculator calc;
	
	public void setFlavEnable(boolean flavEnable) {
		this.flavEnable = flavEnable;
	}

	public ItemPanelDetails()
	{
		init();
	}
	public ItemPanelDetails(StuffCalculator calc)
	{
		init();
		this.calc=calc;
	}

	public void setCalculator(StuffCalculator cal)
	{
		calc=cal;
	}
	private void init()
	{
		this.setLayout(null);
		this.add(getLblNomItem());
		this.add(getLblIcon());
		this.add(getLblTextItem());
		this.add(getLblTypeItem());
		this.add(getLblStatArmorDPS());
		this.add(getLblTypeItemAD());
		this.add(getLblDetailWeapon());
		this.add(getLblDetailItem());
		this.add(getLblSock1());
		this.add(getLblSock2());
		this.add(getLblSock3());
		this.add(getLblDetailSet());
		this.add(getLblItemLevel());
		this.setBackground(Color.BLACK);
	}
	
	public void paintComponent(Graphics g)
	{
		 super.paintComponent(g);
		 Graphics2D g2d = (Graphics2D) g;
		 
		 if(item!=null)
		 {

			 Image i = new ImageIcon(getClass().getResource("/org/armory/d3/ui/resources/item-"+item.getDisplayColor()+".png")).getImage();
			 g2d.drawImage(i, 0, 7, null);
			
			
			 if(item.isArmor())
			 {
				 g2d.drawImage(new ImageIcon(getClass().getResource("/org/armory/d3/ui/resources/armor.jpg")).getImage(), 100, 85, null);
			 }
			 else
			 {
				 if(!item.getEnchantedWeapon().equals(""))
					 g2d.drawImage(new ImageIcon(getClass().getResource("/org/armory/d3/ui/resources/"+item.getEnchantedWeapon().toLowerCase()+".jpg")).getImage(), 40, 85, null);
			 }
		 }
	}
	
	public void showItem(Item item) {
		try{
			
		this.item=item;
		getLblNomItem().setText(item.getName());
		getLblNomItem().setForeground(ItemLabel.toColor(item.getDisplayColor()));
		getLblNomItem().setFont(new Font("Palatino Linotype", Font.BOLD, 18));
		resizeFont(getLblNomItem(),Font.BOLD);
		
		
		if(flavEnable==true){
			getLblTextItem().setForeground(new Color(138,75,8));
			getLblTextItem().setBorder(new LineBorder(new Color(138,75,8)));
			getLblTextItem().setFont(new Font("Palatino Linotype", Font.ITALIC, 16));
			getLblTextItem().setBounds(0, this.getHeight()-100, getLblTextItem().getParent().getWidth() , 100);
			getLblTextItem().setText(item.getFlavorText());
		}
		
		getLblTypeItem().setForeground(ItemLabel.toColor(item.getDisplayColor()));
		getLblTypeItem().setFont(new Font("Palatino Linotype", Font.PLAIN, 16));
		getLblTypeItem().setText(item.getTypeName());
		resizeFont(getLblTypeItem(), Font.PLAIN);
		
		String levelItem = "Level: "+item.getItemLevel()+" <br/>Level Min: "+item.getRequiredLevel();
		getLblItemLevel().init();
		getLblItemLevel().addText(levelItem, "#BAB348", "white");
		getLblItemLevel().applyText();
		getLblStatArmorDPS().setForeground(Color.WHITE);
		getLblStatArmorDPS().setFont(new Font("Palatino Linotype", Font.PLAIN, 40));
		
		getLblTypeItemAD().setForeground(Color.GRAY);
		
		if(item.isArmor())
		{


            getLblStatArmorDPS().setText(new DecimalFormat("#0").format(item.getRealArmor().getMoyenne()));
            getLblTypeItemAD().setText("Armor");
            getLblDetailWeapon().setText("");
            
          
			if(item.isShield())//getType().getId().endsWith("Shield"))
			{
				getLblDetailWeapon().init();
				getLblDetailWeapon().addText("+"+new DecimalFormat("#0.0").format(item.getRealBlockChance()*100)+" % Chance to Block<br/> "+new DecimalFormat("#0").format(item.getRealBlockMin())+" - "+new DecimalFormat("#0").format(item.getRealBlockMax())+" Block Amount","gray","white");
				getLblDetailWeapon().applyText();
			}
		}
		else 
		if(item.isWeapon()){
			double mindmg=item.getRealMin();
			double maxdmg=item.getRealMax();
			
			getLblStatArmorDPS().setText(new DecimalFormat("#0.0").format(item.getRealDPS()));
			getLblTypeItemAD().setText("Damage Per Second");
					
			
			getLblDetailWeapon().init();
			getLblDetailWeapon().addText(new DecimalFormat("#0").format(mindmg)+" - "+new DecimalFormat("#0").format(maxdmg)+" points de degats<br/> "+new DecimalFormat("#0.00").format(item.getRealAttacksPerSecond().getMoyenne())+" vitesse d'attaque","gray","white");
			getLblDetailWeapon().applyText();
		}
//		else
//		{
//			getLblStatArmorDPS().setText("");
//			getLblTypeItemAD().setText("");
//			getLblDetailWeapon().setText("");
//			getLblDetailWeapon().init();
//			getLblDetailWeapon().addText("", "", "");
//			getLblDetailWeapon().applyText();
//		}
		
		
		List<DisplayableItemAttributs> prim = item.getAttributes().getPrimary();
		List<DisplayableItemAttributs> sec = item.getAttributes().getSecondary();
		List<DisplayableItemAttributs> pass = item.getAttributes().getPassive();
		
		getLblDetailItem().init();
		
		if(prim !=null){
			getLblDetailItem().addText("Primaire ","white","white");
			for(DisplayableItemAttributs i : prim)
			{
				getLblDetailItem().addText(i.getText(), i.getColor(), "#BDA6CD");
			}
		}
		if(sec!=null){
			getLblDetailItem().addText("Secondaire ","white","white");
			for(DisplayableItemAttributs i : sec)
			{
				getLblDetailItem().addText(i.getText(), i.getColor(), "#BDA6CD");
			}
		}
		if(pass !=null){
			getLblDetailItem().addText("Passif ","white","white");
			for(DisplayableItemAttributs i : pass)
			{
				getLblDetailItem().addText(i.getText(), i.getColor(), "#BDA6CD");
			}
		}
		getLblDetailItem().applyText();
		
		updateSocketLabel();
		
		updateSetPanel();
	
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	
	
	private void updateSetPanel() {

		//Affichage panneau set legendaire
		if(item.isSetObjects())
		{
			StringBuffer tempset = new StringBuffer();
			tempset.append("<html><font color='#02FF00'>"+item.getSet().getName()+"</font><br/>");
			
			List<Item> sets = item.getSet().getItems();
			for(Item i:sets)
			{
				if(calc.getAllItems().contains(i))
					tempset.append("&nbsp;&nbsp;&nbsp;<font color='#02FF00'>" + i.getName()+"</font><br/>");
				else
					tempset.append("&nbsp;&nbsp;&nbsp;" + i.getName()+"<br/>");
			}
			int nbstuff=new LegendarySet().getStuffSetsNbPieces(calc.getAllItems(),item.getSet());
			
			for(int z=0;z<item.getSet().getRanks().size();z++ )
			{
					Ranks r = item.getSet().getRanks().get(z);
					
					if(nbstuff>=Integer.parseInt(r.getRequired()))
						tempset.append("<font color='#02FF00'>");
					else
						tempset.append("<font color='white'>");
					
					tempset.append("set ("+r.getRequired()+")<br/>");
					
					AttributsContainer con = r.getAttributes();
					
					for(DisplayableItemAttributs d : con.getPrimary())
						tempset.append("&nbsp;&nbsp;"+d.getText()+"<br/>");
					for(DisplayableItemAttributs d : con.getSecondary())
						tempset.append("&nbsp;&nbsp;"+d.getText()+"<br/>");
					for(DisplayableItemAttributs d : con.getPassive())
						tempset.append("&nbsp;&nbsp;"+d.getText()+"<br/>");
					
					tempset.append("</font>");
					
			}
			
			tempset.append("</html>");	
			getLblDetailSet().setText(tempset.toString());
			
		}
		else
		{
			getLblDetailSet().setText("");
		}
		
		
	}



	private void updateSocketLabel() {
		if(item.nbSockets()>0)
		{
			lblSock1.setVisible(true);
			lblSock1.setItem(item,0);
			
			if(item.getGems().size()>0)
			{
				
				if(!item.getGems().get(0).isJewel())//TODO text a faire évoluer pour les gem levelup
				{
					lblSock1.setForeground(Color.WHITE);
					lblSock1.setSize(300, 30);
					if(item.getGems().get(0).getAttributes().getSecondary().size()>0)
						lblSock1.setText(item.getGems().get(0).getAttributes().getSecondary().get(0).getText());
					else	
						lblSock1.setText(item.getGems().get(0).getAttributes().getPrimary().get(0).getText());
					
					
					
				}
				else
				{
					lblSock1.setForeground(new Color(223,116,1));
					lblSock1.setSize(450, 80);
					String val = "<html><b>"+item.getGems().get(0).getItem().getName()+"</b> ("+item.getGems().get(0).getJewelRank()+"):<br/>";
					
					val+="* "+item.getGems().get(0).getAttributes().getPassive().get(0).getText()+"<br>";
					
					if(item.getGems().get(0).getJewelRank().intValue()<item.getGems().get(0).getJewelSecondaryEffectUnlockRank().intValue())
						val+="<font color='red'>* "+item.getGems().get(0).getAttributes().getPassive().get(1).getText()+"</font><br>";
					else
						val+="* "+item.getGems().get(0).getAttributes().getPassive().get(1).getText()+"<br>";
					
					
//					for( DisplayableItemAttributs s : item.getGems().get(0).getAttributes().getPassive())
//					{
//						
//						if(item.getGems().get(0).getJewelRank().intValue()<item.getGems().get(0).getJewelSecondaryEffectUnlockRank().intValue())
//							val+="<font color='red'>* "+s.getText()+"</font><br>";
//						else
//							val+="* "+s.getText()+"<br>";
//					}
						
					lblSock1.setText(val+"</html>");
				
					
				}
				
			}
			else
			{
				lblSock1.setText("            empty");
			}
		}
		else
		{
			lblSock1.setVisible(false);
		}
		
		if(item.nbSockets()>1)
		{
			lblSock2.setVisible(true);
			lblSock2.setItem(item,1);
			if(item.getGems().size()>1)
				lblSock2.setText(item.getGems().get(1).getAttributes().getPrimary().get(0).getText());
			else
				lblSock2.setText("            empty");
		}
		else
		{
			lblSock2.setVisible(false);
		}
		
		if(item.nbSockets()>2)
		{
			lblSock3.setVisible(true);
			lblSock3.setItem(item,2);
			if(item.getGems().size()>2)
				lblSock3.setText(item.getGems().get(2).getAttributes().getPrimary().get(0).getText());
			else
				lblSock3.setText("            empty");
		}
		else
		{
			lblSock3.setVisible(false);
		}
	}

	private void resizeFont(JLabel lbl,int type) {
		try{
			Font labelFont =lbl.getFont();
			String labelText = lbl.getText();
			int stringWidth = lbl.getFontMetrics(labelFont).stringWidth(labelText);
			int componentWidth = lbl.getWidth();
			double widthRatio = (double)componentWidth / (double)stringWidth;
			int newFontSize = (int)(labelFont.getSize() * widthRatio);
			int componentHeight = lbl.getHeight();
			int fontSizeToUse = Math.min(newFontSize, componentHeight);
			if(fontSizeToUse>18) //taille max
				fontSizeToUse=18;
			lbl.setFont(new Font(labelFont.getName(), type, fontSizeToUse));
		}
		catch(Exception e)
		{	
			//e.printStackTrace();
		}
		
		
	}

	public JLabel getLblIcon() {
		if(lblIcon == null) {
			lblIcon = new JLabel() {
				
				 public void paint( Graphics g )
				  {
					 if(item!=null){
						    int width = getWidth();
						    int height = getHeight();
					
						    Color end=ItemLabel.toColor(item.getDisplayColor()); 
						    Color start=Color.BLACK;
						    GradientPaint paint = new GradientPaint( 0, 0, start, width, height, end, true );
						    Graphics2D g2d = ( Graphics2D )g;
						    Paint oldPaint = g2d.getPaint();
						    	g2d.setPaint( paint );
						    	g2d.fillRect( 0, 0, width, height );
						    	g2d.setPaint( oldPaint );
						    	
				  	}
				    super.paint( g );
				  }
				 
				 
				 public Icon getIcon() {
						setHorizontalAlignment(JLabel.CENTER);
						setVerticalAlignment(JLabel.CENTER);
						return super.getIcon();
				}
					
				 
				 public Border getBorder() {
						
						try{
						if(item != null)
						{
							if("orange".equals(item.getDisplayColor()))
								return new LineBorder(new Color(223,116,1), 1, true);
							if("yellow".equals(item.getDisplayColor()))
									return new LineBorder(Color.yellow, 1, true);
							if("green".equals(item.getDisplayColor()))
									return new LineBorder(Color.green, 1, true);
							if("blue".equals(item.getDisplayColor()))
									return new LineBorder(new Color(30,144,255), 1, true);
							if("white".equals(item.getDisplayColor()))
								return new LineBorder(Color.white, 1, true);
							if("grey".equals(item.getDisplayColor()))
								return new LineBorder(Color.gray, 1, true);
						
						}
						return super.getBorder();
						}
						catch(Exception e)
						{
							return new LineBorder(Color.white,0,true);
						}
					}
				
			};
			lblIcon.setBounds(10, 58, 90, 168);
			lblIcon.setName("lblIcon");
		}
		return lblIcon;
	}

	public JLabel getLblNomItem() {
		if(lblNomItem == null) {
			lblNomItem = new JLabel();
			lblNomItem.setHorizontalAlignment(JLabel.CENTER);
			lblNomItem.setBounds(46, 10, 274, 39);
			lblNomItem.setName("lblNomItem");
		}
		return lblNomItem;
	}

	public JLabel getLblTypeItem() {
		if(lblTypeItem == null) {
			lblTypeItem = new JLabel();
			lblTypeItem.setHorizontalAlignment(JLabel.LEFT);
			lblTypeItem.setBounds(112, 61, 200, 20);
			lblTypeItem.setName("lblTypeItem");
		}
		return lblTypeItem;
	}
	
	public JTextPane getLblTextItem() {
		if(lblTextItem == null) {
			lblTextItem = new JTextPane();
			lblTextItem.setEditable(false);
			lblTextItem.setBackground(Color.BLACK);
			lblTextItem.setName("lblTextItem");
		}
		return lblTextItem;
	}
	
	public JLabel getLblStatArmorDPS() {
		if(lblStatArmorDPS == null) {
			lblStatArmorDPS = new JLabel();
			lblStatArmorDPS.setBounds(112, 93, 200, 61);
			lblStatArmorDPS.setName("lblStatArmorDPS");
		}
		return lblStatArmorDPS;
	}
	
	private JLabel getLblTypeItemAD() {
		if(lblTypeItemAD == null) {
			lblTypeItemAD = new JLabel();
			lblTypeItemAD.setBounds(112, 140, 144, 15);
		}
		return lblTypeItemAD;
	}

	public FormatedJLabel getLblDetailWeapon(){
		if(lblDetailWeapon==null){
			lblDetailWeapon=new FormatedJLabel();
			lblDetailWeapon.setBounds(112, 161, 267, 65);
		}
		return lblDetailWeapon;
	}
	
	private FormatedJLabel getLblDetailItem() {
		if(lblDetailItem == null) {
			lblDetailItem = new FormatedJLabel();
			lblDetailItem.setBounds(12, 238, 325, 200);
			lblDetailItem.setName("lblDetailItem");
		}
		return lblDetailItem;
	}
	
	private SocketLabel getLblSock1() {
		if(lblSock1==null) 
		{
			lblSock1 = new SocketLabel(JLabel.LEFT);
		//	lblSock1.setAlignmentX(Component.CENTER_ALIGNMENT);
			lblSock1.setBounds(12, 380, 300, 30);
			lblSock1.setForeground(Color.white);
		}
		
		return lblSock1;
	}
	
	private SocketLabel getLblSock2() {
		if(lblSock2==null) 
		{
			lblSock2 = new SocketLabel(JLabel.LEFT);
			lblSock2.setBounds(12, 350+60, 300, 30);
			lblSock2.setForeground(Color.white);
		}
		
		return lblSock2;
	}
	
	private SocketLabel getLblSock3() {
		if(lblSock3==null) 
		{
			lblSock3 = new SocketLabel(JLabel.LEFT);
			lblSock3.setBounds(12, 350+90, 300, 30);
			lblSock3.setForeground(Color.white);
		}
		return lblSock3;

	}

	private JLabel getLblDetailSet()
	{
		if(lblDetailSet==null)
		{
			lblDetailSet = new FormatedJLabel();
			lblDetailSet.setBounds(12, 480, 360,300);
			lblDetailSet.setForeground(Color.WHITE);
		}
		
		return lblDetailSet;
	}
	
	private FormatedJLabel getLblItemLevel() {
		if(lblItemLevel == null) {
			lblItemLevel = new FormatedJLabel("",SwingConstants.RIGHT);
			lblItemLevel.setBounds(260, 75, 100,80);
			
		}
		return lblItemLevel;
	}

}
