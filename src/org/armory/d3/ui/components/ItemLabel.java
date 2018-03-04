package org.armory.d3.ui.components;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Paint;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragSource;
import java.awt.dnd.DragSourceDragEvent;
import java.awt.dnd.DragSourceDropEvent;
import java.awt.dnd.DragSourceEvent;
import java.awt.dnd.DragSourceListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.swing.GrayFilter;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JSeparator;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.armory.d3.services.D3ArmoryControler;
import org.armory.d3.ui.ItemCreatorFrame;
import org.armory.d3.ui.SwingMainFrame;
import org.armory.d3.ui.components.transfert.ItemLabelTargetListener;
import org.armory.d3.ui.components.transfert.TransferableItem;

import com.pihen.d3restapi.beans.Item;
import com.pihen.d3restapi.beans.Tag;
import com.pihen.d3restapi.service.util.EnumerationStuff;


public class ItemLabel extends JLabel implements MouseListener, Cloneable, DragGestureListener{
	
    private Item item;
 	private boolean disabled;
    private EnumerationStuff gear;
    public static String SIZE_LARGE="large";
    public static String SIZE_SMALL="small";
    private String size;
	private boolean isDraggable=false;
	private boolean isDropable=true;
	private boolean isOverDetailed=true;
	private boolean transparent=false;
	
	static final Logger logger = LogManager.getLogger(ItemLabel.class.getName());

	
	
    public boolean isOverDetailed() {
		return isOverDetailed;
	}

	public void setOverDetailed(boolean isOverDetailed) {
		this.isOverDetailed = isOverDetailed;
	}

	public boolean isDraggable() {
		return isDraggable;
	}

	public void enabledDraggable(boolean isDraggable) {
		this.isDraggable = isDraggable;
		
		DragSource ds = new DragSource();
		   ds.createDefaultDragGestureRecognizer(this,DnDConstants.ACTION_COPY, this);
		  
		
	}
	
	public void dragGestureRecognized(DragGestureEvent event) 
    {
        Cursor cursor = null;
        if (event.getDragAction() == DnDConstants.ACTION_COPY) {
            cursor = DragSource.DefaultCopyDrop;
        }
        event.startDrag(cursor, new TransferableItem(this));
    }
	
	
	
	

	public boolean isDropable() {
		return isDropable;
	}

	public void enabledDropable(boolean isDropable) {
		this.isDropable = isDropable;
		new ItemLabelTargetListener(this);
	}

	

	public ItemLabel copy()
	{
		ItemLabel itLab = new ItemLabel();
				  itLab.setItem(this.getItem(), this.getGear());
				  itLab.enabledDropable(false);
				  itLab.enabledDraggable(true);
		
		return itLab;
	}
	
	
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

	public void setItem(Item i)
	{
		  this.item=i;
	}
    
	public void setItem(Item i,EnumerationStuff e) {
		this.item = i;
		this.gear = e;
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
		
		if(transparent)
			return new LineBorder(Color.black,0);
		
		
		
		try{
			if(item != null)
			{
					if("orange".equals(item.getDisplayColor()))
					{
				//		if(item.isAncientItem())
							if(item.isAncientPrimalItem())
								return new LineBorder(Color.RED, 1, true);
							else
								return new LineBorder(new Color(255,177,124), 1, true);
					//	else
					//		return new LineBorder(new Color(223,116,1), 1, true);
					}
					if("yellow".equals(item.getDisplayColor()))
							return new LineBorder(Color.yellow, 1, true);
					if("green".equals(item.getDisplayColor()))
					{
						//if(item.isAncientItem())
							if(item.isAncientPrimalItem())
								return new LineBorder(Color.RED, 1, true);
							else
								return new LineBorder(new Color(129,247,129), 1, true);
					//	else
					//		return new LineBorder(new Color(173,255,47), 1, true);
							
					}
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
		 if(item!=null)
		 {
			    int width = getWidth();
			    int height = getHeight();
		
			    
			   
			    Color end=toColor(item.getDisplayColor()); 
			    Color start=Color.BLACK;
			    GradientPaint paint = new GradientPaint( 0, 0, start, width, height, end, true );
			    Graphics2D g2d = ( Graphics2D )g;
			    Paint oldPaint = g2d.getPaint();
			    	g2d.setPaint( paint );
			    	if(!transparent){
			    		g2d.fillRect( 0, 0, width, height );
		 			}
			    		g2d.setPaint( oldPaint );
			    
			    	
			    	if(item.getTransmogItem()!=null)
					{
			    		Image i = new ImageIcon(getClass().getResource("/org/armory/d3/ui/bg-transmog.gif")).getImage();
			    		g2d.drawImage(i, this.getWidth()-13, 0, null);
					}
			    	
			    	if(item.isSocketAddedByGift())
					{
			    		Image i = new ImageIcon(getClass().getResource("/org/armory/d3/ui/gift.png")).getImage();
			    		g2d.drawImage(i, this.getWidth()-30, this.getHeight()-30, null);
					}
			    	
			    	if(item.getDyeColor()!=null)
			    	{
			    		try {
							URL url = new URL("http://media.blizzard.com/d3/icons/items/small/"+item.getDyeColor().getIcon()+".png");
							Image i = new ImageIcon(url).getImage();
							g2d.drawImage(i, 0, 0,15,15, null);
						} 
			    		catch (MalformedURLException e) {
							
						}
			    		
			    		
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
		
		if(isDraggable)
			this.setCursor(new Cursor(Cursor.MOVE_CURSOR));
		
		if(!isOverDetailed)
			return;
		
		if(item==null)
			return;
		
		//logger.debug("show - " + item + " URL -> " + item.getItemID());
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
	
	
	public void mousePressed(MouseEvent e) 
	{
		if(getGear()==null)
			return;
			
		if(SwingUtilities.isRightMouseButton(e))
		{
			List<Tag> listeTag= D3ArmoryControler.getInstance().getListTags();
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
				
				
				JMenuItem itSaveAsItem = new JMenuItem("Save As");
				itSaveAsItem.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {

						try {
							String name = JOptionPane.showInputDialog("Name for item ?",item.getName());
							item.setName(name);
							saveItem();
						} catch (Exception e1) {
							JOptionPane.showMessageDialog(null, e1,"ERREUR",JOptionPane.ERROR_MESSAGE);
						}
						
					}
				});
				
				JMenuItem itSaveItem = new JMenuItem("Save");
				itSaveItem.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {

						try {
							saveItem();
						} catch (Exception e1) {
							JOptionPane.showMessageDialog(null, e1,"ERREUR",JOptionPane.ERROR_MESSAGE);
						}
						
					}
				});
				
				
			  popupMenu.add(itNewItem);
			  popupMenu.add(createMenu(D3ArmoryControler.getInstance().getRecorder().listSavedItems()));
			  popupMenu.add(new JSeparator());
			  popupMenu.add(itSaveItem);
			  popupMenu.add(itSaveAsItem);
			  
			  popupMenu.show(e.getComponent(),e.getX(), e.getY());
		}
		
	}
	
	public void saveItem() throws Exception
	{
		D3ArmoryControler.getInstance().getRecorder().saveItem(item);
		((SwingMainFrame)getTopLevelAncestor()).getChestPanel().addComponent(this.copy());
		((SwingMainFrame)getTopLevelAncestor()).getChestPanel().repaint();
	}
	
	protected void initWindowBuilder(Item i, EnumerationStuff g) {
		ItemCreatorFrame itemBuilderFrame = new ItemCreatorFrame(i,g);
						 itemBuilderFrame.getItemPanelDetails().getLblIcon().setIcon(getIcon(false,SIZE_LARGE));
		
	}

	private JMenu createMenu(List<Item> listeFileItem) {
		 JMenu m = new JMenu("Saved Item");
		  for(final Item f:listeFileItem)
		  {	 
			  JMenuItem a = new JMenuItem(f.getName());
			    m.add(a);
		  		a.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						initWindowBuilder(f,gear);
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, e1,"ERREUR",JOptionPane.ERROR_MESSAGE);
					}
				}
			});
		  }
		  return m;
	}

	public void setTransparent(boolean b) {
		this.transparent=true;
		
	}
}
