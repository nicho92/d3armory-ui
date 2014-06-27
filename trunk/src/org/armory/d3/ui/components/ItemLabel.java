package org.armory.d3.ui.components;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Paint;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.net.URL;
import java.util.List;

import javax.swing.GrayFilter;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JSeparator;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import org.armory.d3.services.D3ArmoryControler;
import org.armory.d3.ui.ItemCreatorFrame;
import org.armory.d3.ui.SwingMainFrame;

import com.pihen.d3restapi.beans.Item;
import com.pihen.d3restapi.service.util.EnumerationStuff;


public class ItemLabel extends JLabel implements MouseListener {
	
    private Item item;
 	private boolean disabled;
    private EnumerationStuff gear;
    public static String SIZE_LARGE="large";
    public static String SIZE_SMALL="small";
    private String size;
    
    public ItemLabel()
    {
    	size=SIZE_LARGE;
    	addMouseListener(this);
    }
    
    public ItemLabel(String size)
    {
    	this.size=size;
    	addMouseListener(this);
    	
    }
    
    public Item getItem() {
		return item;
	}

	public void setItem(Item i,EnumerationStuff e) {
		this.item = i;
		this.gear = e;
		this.add(new JLabel("COUCOU"));
	}

	
	public EnumerationStuff getGear() {
		return gear;
	}

	public void setGear(EnumerationStuff gear) {
		this.gear = gear;
	}

	public Icon getIcon(boolean off,String size) {
		setHorizontalAlignment(JLabel.CENTER);
		setVerticalAlignment(JLabel.CENTER);

		if(item != null)
		try {
			URL url = new URL("http://media.blizzard.com/d3/icons/items/"+size+"/"+item.getIcon()+".png");
			ImageIcon i = new ImageIcon(url);
			if(off==false)
				return i;
			else
				return new ImageIcon(GrayFilter.createDisabledImage((i).getImage()));
		
		} catch (Exception e1) {
			return new ImageIcon();
		}
		return super.getIcon();
	}
	
	public Icon getTransmogIcon(String size) {
		setHorizontalAlignment(JLabel.CENTER);
		setVerticalAlignment(JLabel.CENTER);

		if(item != null)
		try {
			URL url = new URL("http://media.blizzard.com/d3/icons/items/"+size+"/"+item.getTransmogItem().getIcon()+".png");
			ImageIcon i = new ImageIcon(url);
			return i;
		
		} catch (Exception e1) {
			return new ImageIcon();
		}
		return super.getIcon();
	}
	
	
	public Icon getIcon() {
		return getIcon(disabled,size);
	}
	
	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
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
					
					return new LineBorder(Color.white, 0, true);
				
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
			    
			    	
			    	if(item.getTransmogItem()!=null)
					{
			    		Image i = new ImageIcon(getClass().getResource("/org/armory/d3/ui/resources/bg-transmog.gif")).getImage();
			    		g2d.drawImage(i, this.getWidth()-13, 0, null);
					}
						
			    	
	  	}
	    super.paint( g );
	  }
    
	  public static Color toColor(String s)
	  {
		  if(s==null)
			  return Color.white;
		  
		  
				if (s.equals("orange"))
					return new Color(210,105,30);
				if (s.equals("yellow"))
					return new Color(255,215,0);
				if (s.equals("green"))
					return new Color(107,142,35);
				if (s.equals("blue"))
					return new Color(65,105,225);
				if (s.equals("white"))
					return Color.white;
				if (s.equals("grey"))
					return Color.gray;
			
				return Color.white;
	 }


	public void mouseClicked(MouseEvent e) {
		if(item.getTransmogItem()!=null)
		{
			((SwingMainFrame)this.getTopLevelAncestor()).getPanelItemDetails().showItem(item.getTransmogItem());
			((SwingMainFrame)this.getTopLevelAncestor()).getPanelItemDetails().getLblIcon().setIcon(this.getTransmogIcon(SIZE_LARGE));
			((SwingMainFrame)this.getTopLevelAncestor()).getPanelItemDetails().repaint();
		}
		
	}

	public void mouseEntered(MouseEvent e) {
		if(item==null)
			return;
		
		((SwingMainFrame)this.getTopLevelAncestor()).getPanelItemDetails().showItem(item);
		((SwingMainFrame)this.getTopLevelAncestor()).getPanelItemDetails().getLblIcon().setIcon(this.getIcon(false,SIZE_LARGE));
		((SwingMainFrame)this.getTopLevelAncestor()).getPanelItemDetails().repaint();
		
		if(item!=null)
			D3ArmoryControler.getInstance().setSelectedItem(item);
		 
	}

	public void mouseExited(MouseEvent e) {
		//details.removeAll();
	}

	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void mousePressed(MouseEvent e) {
		if(SwingUtilities.isRightMouseButton(e))
		{
			List<String> listeTag= D3ArmoryControler.getInstance().getListTags();
			JPopupMenu popupMenu = new JPopupMenu();
			
			if(item==null)
				popupMenu.add(new JMenuItem("Compare gear with"));
			else
				popupMenu.add(new JMenuItem("Compare " + item.getType().getId() + " with"));
			
				popupMenu.add(new JSeparator());
			JMenuItem itNewItem = new JMenuItem("New Item");
				itNewItem.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						Item i = new Item();
						
						if(item!=null)
							i =  (Item) item.clone();
						initWindowBuilder(i,gear);
					}
				}
				);
			  popupMenu.add(itNewItem);
			  popupMenu.add(createMenu(listeTag));
			  popupMenu.add(createMenu(D3ArmoryControler.getInstance().getListeFileItem()));
			  popupMenu.show(e.getComponent(),e.getX(), e.getY());
		}
		
	}
	protected void initWindowBuilder(Item i, EnumerationStuff g) {
		ItemCreatorFrame itemBuilderFrame = new ItemCreatorFrame(i,g);
						 itemBuilderFrame.getItemPanelDetails().getLblIcon().setIcon(getIcon(false,SIZE_LARGE));
		
	}

	private JMenu createMenu(File[] listeFileItem) {
		 JMenu m = new JMenu("Saved Item");
		  for(final File f:listeFileItem)
		  {	 
			  JMenuItem a = new JMenuItem(f.getName().substring(0, f.getName().lastIndexOf(".")));
			    m.add(a);
		  		a.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Item i = D3ArmoryControler.getInstance().loadItem(f);
					initWindowBuilder(i,gear);
					
				}
			});
		  }
		  return m;
	}

	public JMenu createMenu(List<String> listeTag){
		  JMenu m = new JMenu("Profile");
		  for(String tag:listeTag)
		  {
			  m.add(new JMenuItem(tag));
			 
		  }
		  
		  return m;
	}

	 	
}
