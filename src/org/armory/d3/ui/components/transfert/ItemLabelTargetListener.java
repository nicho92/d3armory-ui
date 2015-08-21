package org.armory.d3.ui.components.transfert;

import java.awt.Cursor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragSource;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.io.IOException;

import org.armory.d3.ui.ItemCreatorFrame;
import org.armory.d3.ui.components.ItemLabel;

import com.pihen.d3restapi.beans.Item;

public class ItemLabelTargetListener extends DropTargetAdapter {

    private DropTarget dropTarget;
    private ItemLabel itlab;

    public ItemLabelTargetListener(ItemLabel il) {
    	itlab = il;
    	dropTarget = new DropTarget(il, DnDConstants.ACTION_COPY, this, true, null);
    }

    
    
    public void reject()
    {
    	itlab.setCursor(DragSource.DefaultCopyNoDrop);
    }
    
    @Override
    public void dragExit(DropTargetEvent dte) {
	    itlab.setCursor(Cursor.getDefaultCursor());
    }
    
    public void dragOver(DropTargetDragEvent event) {
    	
    	 Transferable tr = event.getTransferable();
    	 try {
			Item i = (Item)tr.getTransferData(TransferableItem.itemFlavor);
			switch(itlab.getGear())
			{
				case BELT:
						if(!i.getType().toString().contains("Belt"))
						{
							reject();
							event.rejectDrag();
						}
						break;
				case BRACER:
						if(!i.getType().toString().contains("Bracers"))
						{
							
							reject();
							event.rejectDrag();
						}
						break;
				case FEET:
						if(!i.getType().toString().contains("Boots"))
						{
							reject();
							event.rejectDrag();
						}
						break;
				case GANT:
						if(!i.getType().toString().contains("Gloves"))
						{
							
							reject();
							event.rejectDrag();
						}
						break;
				case HEAD:
						if(!i.getType().toString().contains("Helm"))
						{
							reject();
							event.rejectDrag();
						}
						break;
				case LEGS:
						if(!i.getType().toString().contains("Legs"))
						{
							reject();
							event.rejectDrag();
						}
						break;
				case MAIN_HAND:
						if(!i.isWeapon())
						{
							
							reject();
							event.rejectDrag();
						}
						break;
				case NECK:
					if(!i.getType().toString().contains("Amulet"))
					{
						
						reject();
						event.rejectDrag();
					}
					break;
				case OFF_HAND:
					if(!(i.isWeapon()||i.getType().toString().contains("Orb")||i.getType().toString().contains("Shield")||i.getType().toString().contains("Mojo")||i.getType().toString().contains("Quiver")))
					{
						reject();
						event.rejectDrag();
					}
					break;
				case RING_LEFT:
						if(!i.getType().toString().contains("Ring"))
						{
							reject();
							event.rejectDrag();
						}
						break;
				case RING_RIGHT:
						if(!i.getType().toString().contains("Ring"))
						{
							reject();
							event.rejectDrag();
						}
						break;
				case SHOULDERS:
					if(!i.getType().toString().contains("Shoulders"))
					{
						
						reject();
						event.rejectDrag();
					}
					break;
				case TORSO:
					if(!(i.getType().toString().contains("Chest")||i.getType().toString().contains("Cloak")))
					{
						
						reject();
						event.rejectDrag();
					}
					break;
				default:
					break;
			}
			
    	 } 
    	 catch (UnsupportedFlavorException | IOException e) {
			//e.printStackTrace();
		}
    }
    
  public void drop(DropTargetDropEvent event) {
    try {

      Transferable tr = event.getTransferable();
      Item i = (Item) tr.getTransferData(TransferableItem.itemFlavor);

        if (event.isDataFlavorSupported(TransferableItem.itemFlavor)) {

          event.acceptDrop(DnDConstants.ACTION_COPY);
          this.itlab.setItem(i);
          this.itlab.repaint();
          ItemCreatorFrame itemBuilderFrame = new ItemCreatorFrame(i,itlab.getGear());
          ItemLabel temp = new ItemLabel();
          temp.setItem(i);
          itemBuilderFrame.getItemPanelDetails().getLblIcon().setIcon(temp.getIcon());
			 
          //D3ArmoryControler.getInstance().getCalculator().getStuffs().put(itlab.getGear(), i);
          //D3ArmoryControler.getInstance().getCalculator().init();
          //D3ArmoryControler.getInstance().getCalculator().calculate();
          
          
          event.dropComplete(true);
          return;
        }
        
        
      event.rejectDrop();
      
      
    } catch (Exception e) {
      e.printStackTrace();
      event.rejectDrop();
    }
  }
}
