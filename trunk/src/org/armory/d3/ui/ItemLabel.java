package org.armory.d3.ui;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Paint;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;
import java.util.Iterator;
import java.util.Set;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import org.armory.d3.beans.Gem;
import org.armory.d3.beans.Item;
import org.armory.d3.beans.LegendarySetItem;

public class ItemLabel extends JLabel implements MouseListener {
	
    Item item;

    public ItemLabel()
    {
    	addMouseListener(this);
    }
    
    
	public Item getItem() {
		return item;
	}

	public void setItem(Item i) {
		this.item = i;
	}

	
	public Image getImage(){
		if(item != null)
			try {
				URL url = new URL("http://media.blizzard.com/d3/icons/items/large/"+item.getIcon()+".png");
				return new ImageIcon(url).getImage();
			} catch (Exception e1) {
				e1.printStackTrace();
				return new ImageIcon().getImage();
			}
			return null;
	}
	
	public Icon getIcon() {
		setHorizontalAlignment(JLabel.CENTER);
		setVerticalAlignment(JLabel.CENTER);

		if(item != null)
		try {
			URL url = new URL("http://media.blizzard.com/d3/icons/items/large/"+item.getIcon()+".png");
			return new ImageIcon(url);
		} catch (Exception e1) {
			return new ImageIcon();
		}
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
    
	 public void paint( Graphics g )
	  {
		 if(item!=null){
			    int width = getWidth();
			    int height = getHeight();
		
			    Color end=toColor(item.getDisplayColor()); 
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
    
	  public Color toColor(String s)
	  {
		  if(s==null)
			  return Color.white;
		  
		  
			switch (s) {
				case "orange":return new Color(210,105,30);
				case "yellow":return new Color(255,215,0);
				case "green":return new Color(107,142,35);
				case "blue":return new Color(65,105,225);
				case "white":return Color.white;
				case "grey":return Color.gray;
			default:return Color.white;
	  }
	 	
}

	@Override
	public void mouseClicked(MouseEvent e) {
		
 		System.out.println(item.getName() + " ( "+ item.getTypeName() + ")");
 		System.out.println("Niveau nécessaire " + item.getRequiredLevel());
 		System.out.println("Niveau objet " + item.getItemLevel());
 		
 		if(item.isArmor())
 			System.out.println("Armor " + item.getArmor());
 		
 		if(item.isWeapon()){
 			System.out.println("DPS " + item.getDps());
 			System.out.println("AttakSpeed " + item.getAttacksPerSecond());
 			System.out.println("Min/Max damage " + item.getMinDamage().getMoyenne() + " " + item.getMaxDamage().getMoyenne());
 		}
 		for(int i=0;i<item.getAttributes().length;i++)
			{
				System.out.println(item.getAttributes()[i]);
			}
 		
 		System.out.println("Socket " + item.nbSockets());
 		
 		if(item.nbGems()>0)
 		{
 			for(int i=0;i<item.getGems().length;i++)
				{
					Gem gem = item.getGems()[i];
 				System.out.print(gem.getItem().getName() + " ");
 				for(int j=0;j<gem.getAttributes().length;j++)
					{
						System.out.println(gem.getAttributes()[j]);
					}
				}
 		}
 		
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	 	
}
