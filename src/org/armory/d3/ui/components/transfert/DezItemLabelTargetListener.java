package org.armory.d3.ui.components.transfert;

import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragSource;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import org.armory.d3.services.D3ArmoryControler;
import org.armory.d3.ui.ItemCreatorFrame;
import org.armory.d3.ui.SwingMainFrame;
import org.armory.d3.ui.components.ItemLabel;

import com.pihen.d3restapi.beans.Item;

public class DezItemLabelTargetListener extends DropTargetAdapter {

    private DropTarget dropTarget;
    private ItemLabel itlab;

    public DezItemLabelTargetListener(ItemLabel il) {
    	itlab = il;
    	dropTarget = new DropTarget(il, DnDConstants.ACTION_COPY, this, true, null);
    }
    
  public void drop(DropTargetDropEvent event) {
    try {

      Transferable tr = event.getTransferable();
      Item i = (Item) tr.getTransferData(TransferableItem.itemFlavor);

		        if (event.isDataFlavorSupported(TransferableItem.itemFlavor)) {
		        	event.acceptDrop(DnDConstants.ACTION_COPY);
		        	
			        if(D3ArmoryControler.getInstance().getRecorder().removeItem(i))
			        {
			        	JOptionPane.showMessageDialog(null, i.getName()+" deleted");
			        	SwingMainFrame.inst.getChestPanel().init();
						
			        }
			        event.dropComplete(true);
			        return;
		        }
		        
		        event.rejectDrop();
    	} 
    	catch (Exception e) {
    		event.rejectDrop();
    	}
  }
}
