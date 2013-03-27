package org.armory.d3.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Paint;
import java.text.DecimalFormat;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import org.armory.d3.beans.Item;
import org.jdesktop.application.Application;

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
public class ItemPanelDetails extends JPanel {

	private Item item;
	private JLabel lblIcon;
	private JLabel lblNomItem;
	private JTextPane lblTextItem;
	private JLabel lblStatArmorDPS;
	private JLabel lblTypeItemAD;
	private JLabel lblTypeItem;
	private FormatedJLabel lblDetailWeapon;
	private FormatedJLabel lblDetailItem;

	public ItemPanelDetails()
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

		this.setBackground(Color.BLACK);

		Application.getInstance().getContext().getResourceMap(getClass()).injectComponents(this);
	}
	
	public void paintComponent(Graphics g)
	{
		 super.paintComponent(g);
		 Graphics2D g2d = ( Graphics2D )g;
		 Image i = new ImageIcon(getClass().getResource("/org/armory/d3/ui/resources/itemBackground.png")).getImage();
		 g2d.drawImage(i, 0, 0, null);
		 
		 if(item!=null)
			 if(item.isArmor())
			 {
				 g2d.drawImage(new ImageIcon(getClass().getResource("/org/armory/d3/ui/resources/armor.jpg")).getImage(), 100, 85, null);
			 }
			 else
			 {
				 if(!item.getEnchantedWeapon().equals(""))
					 g2d.drawImage(new ImageIcon(getClass().getResource("/org/armory/d3/ui/resources/"+item.getEnchantedWeapon()+".jpg")).getImage(), 100, 85, null);
			 }
	}
	
	public void showItem(Item item) {
		try{
			this.item=item;
		
		getLblNomItem().setText(item.getName());
		getLblNomItem().setForeground(ItemLabel.toColor(item.getDisplayColor()));
		getLblNomItem().setFont(new Font("Palatino Linotype", Font.BOLD, 18));
		resizeFont(getLblNomItem(),Font.BOLD);
		
		getLblTextItem().setForeground(new Color(138,75,8));
		getLblTextItem().setBorder(new LineBorder(new Color(138,75,8)));
		getLblTextItem().setFont(new Font("Palatino Linotype", Font.ITALIC, 16));
		getLblTextItem().setBounds(0, this.getHeight()-100, getLblTextItem().getParent().getWidth() , 100);
		getLblTextItem().setText(item.getFlavorText());
		
		getLblTypeItem().setForeground(ItemLabel.toColor(item.getDisplayColor()));
		getLblTypeItem().setFont(new Font("Palatino Linotype", Font.PLAIN, 16));
		getLblTypeItem().setText(item.getTypeName());
		
		getLblStatArmorDPS().setForeground(Color.WHITE);
		getLblStatArmorDPS().setFont(new Font("Palatino Linotype", Font.PLAIN, 40));
		
		getLblTypeItemAD().setForeground(Color.GRAY);
		
		if(item.isArmor()){
			getLblStatArmorDPS().setText(new DecimalFormat("#0").format(item.getArmor().getMoyenne()));
			getLblTypeItemAD().setText("Armor");
			getLblDetailWeapon().setText("");
			
		}
		else if(item.isWeapon()){
			getLblStatArmorDPS().setText(new DecimalFormat("#0.0").format(item.getDps().getMoyenne()));
			getLblTypeItemAD().setText("Damage Per Second");
			getLblDetailWeapon().setHtmlText(new DecimalFormat("#0").format(item.getMinDamage().getMoyenne())+" - "+new DecimalFormat("#0").format(item.getMaxDamage().getMoyenne())+" points de degats<br/> "+new DecimalFormat("#0.00").format(item.getAttacksPerSecond().getMoyenne())+" vitesse d'attaque","gray","white");
		}
		else
		{
			getLblStatArmorDPS().setText("");
			getLblTypeItemAD().setText("");
			getLblDetailWeapon().setText("");
			getLblDetailWeapon().setHtmlText("","","");
		}
		
		StringBuffer temp = new StringBuffer();
		for(int i=0;i<item.getAttributes().length;i++)
			{
				temp.append(item.getAttributes()[i]+" <br/> ");
			}
	 		
		
		getLblDetailItem().setHtmlText(temp.toString(),"#5869D7","#BDA6CD");
		
		
		
//		
//		
// 		System.out.println("Niveau nécessaire " + item.getRequiredLevel());
// 		System.out.println("Niveau objet " + item.getItemLevel());
// 		System.out.println("------------"+item.getFlavorText());
// 		System.out.println("Socket " + item.nbSockets());
// 		
// 		if(item.nbGems()>0)
// 		{
// 			for(int i=0;i<item.getGems().length;i++)
//				{
//					Gem gem = item.getGems()[i];
// 				System.out.print(gem.getItem().getName() + " ");
// 				for(int j=0;j<gem.getAttributes().length;j++)
//					{
//						System.out.println(gem.getAttributes()[j]);
//					}
//				}
// 		}
		}
		catch(NullPointerException e){}
		
	}
	private void resizeFont(JLabel lbl,int type) {
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
							switch (item.getDisplayColor()) {
								case "orange":return new LineBorder(Color.orange, 1, true);
								case "yellow":return new LineBorder(Color.yellow, 1, true);
								case "green":return new LineBorder(Color.green, 1, true);
								case "blue":return new LineBorder(new Color(30,144,255), 1, true);
								case "white":return new LineBorder(Color.white, 1, true);
								case "grey":return new LineBorder(Color.gray, 1, true);
								default:return new LineBorder(Color.white, 0, true);
							}
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
	
	private JLabel getLblStatArmorDPS() {
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

	private FormatedJLabel getLblDetailWeapon(){
		if(lblDetailWeapon==null){
			lblDetailWeapon=new FormatedJLabel();
			lblDetailWeapon.setBounds(112, 161, 267, 65);
		}
		return lblDetailWeapon;
	}
	
	private FormatedJLabel getLblDetailItem() {
		if(lblDetailItem == null) {
			lblDetailItem = new FormatedJLabel();
			lblDetailItem.setBounds(12, 238, 300, 176);
			lblDetailItem.setName("lblDetailItem");
		}
		return lblDetailItem;
	}

}
