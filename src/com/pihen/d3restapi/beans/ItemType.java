package com.pihen.d3restapi.beans;

import java.io.Serializable;

import com.pihen.d3restapi.service.remote.RemoteEntity;

public class ItemType extends RemoteEntity implements Serializable {


	private static final long serialVersionUID = 4940049017774518737L;
	
	
		private boolean twoHanded;
		private String id;
		
		public boolean getTwoHanded() {
			return twoHanded;
		}
		public void setTwoHanded(boolean twoHanded) {
			this.twoHanded = twoHanded;
		}
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		
		
		public String toString()
		{
			return getId(); 
		}
		
		public ItemType(){
			
		}
}
