package com.pihen.d3restapi.beans;

import java.io.Serializable;

public class ItemType implements Serializable {

		private Boolean twoHanded;
		private String id;
		
		public Boolean getTwoHanded() {
			return twoHanded;
		}
		public void setTwoHanded(Boolean twoHanded) {
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
}
