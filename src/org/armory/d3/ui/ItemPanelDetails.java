package org.armory.d3.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Paint;
import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
	
	
	private JLabel getLblNomItem() {
		if(lblNomItem == null) {
			lblNomItem = new JLabel();
			lblNomItem.setHorizontalAlignment(JLabel.CENTER);
			lblNomItem.setBounds(46, 10, 274, 39);
			lblNomItem.setName("lblNomItem");
		}
		return lblNomItem;
	}

	public ItemPanelDetails()
	{
		this.setLayout(null);
		this.setPreferredSize(new java.awt.Dimension(338, 624));
		this.add(getLblNomItem());
		this.add(getLblIcon());
		Application.getInstance().getContext().getResourceMap(getClass()).injectComponents(this);
	}
	
	public void paintComponent(Graphics g)
	{
		 Graphics2D g2d = ( Graphics2D )g;
		 Image i = new ImageIcon(getClass().getResource("/org/armory/d3/ui/resources/itemBackground.png")).getImage();
		 g2d.drawImage(i, 0, 0, null);
	}

	public void showItem(Item item) {
		this.item=item;
		getLblNomItem().setText(item.getName());
		getLblNomItem().setForeground(ItemLabel.toColor(item.getDisplayColor()));
		getLblNomItem().setFont(new Font("Palatino Linotype", Font.BOLD, 18));
		
		
		
		
		
//		System.out.println(item.getName() + " ( "+ item.getTypeName() + ")");
// 		System.out.println("Niveau nécessaire " + item.getRequiredLevel());
// 		System.out.println("Niveau objet " + item.getItemLevel());
// 		
// 		if(item.isArmor())
// 			System.out.println("Armor " + item.getArmor());
// 		
// 		if(item.isWeapon()){
// 			System.out.println("DPS " + item.getDps());
// 			System.out.println("AttakSpeed " + item.getAttacksPerSecond());
// 			System.out.println("Min/Max damage " + item.getMinDamage().getMoyenne() + " " + item.getMaxDamage().getMoyenne());
// 		}
// 		for(int i=0;i<item.getAttributes().length;i++)
//			{
//				System.out.println(item.getAttributes()[i]);
//			}
// 		
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

}
