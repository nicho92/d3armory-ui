package org.armory.d3.ui.components.transfert;

import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;

import org.armory.d3.services.D3ArmoryControler;
import org.armory.d3.ui.ItemCreatorFrame;
import org.armory.d3.ui.components.ItemLabel;

import com.pihen.d3restapi.beans.Item;

public class ItemLabelTargetListener extends DropTargetAdapter {

    private DropTarget dropTarget;
    private ItemLabel itlab;

    public ItemLabelTargetListener(ItemLabel il) {
    	this.itlab = il;
    	dropTarget = new DropTarget(il, DnDConstants.ACTION_COPY, this, true, null);
  }

    
    public void dragOver(DropTargetDragEvent event) {
    		
    	 Transferable tr = event.getTransferable();
    	 if(((ItemLabel)dropTarget.getComponent()).getGear()!=itlab.getGear())
    		 event.rejectDrag();
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
          D3ArmoryControler.getInstance().getCalculator().calculate();
          
          
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
