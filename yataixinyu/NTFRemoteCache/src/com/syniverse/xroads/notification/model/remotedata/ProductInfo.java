package com.syniverse.xroads.notification.model.remotedata;

import java.io.Serializable;

public class ProductInfo implements Serializable {

	private static final long serialVersionUID = 1L;
	private String prodId;
	private String prodName;

	public String getProdId() {
		return prodId;
	}

	public void setProdId(String prodId) {
		this.prodId = prodId;
	}

	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	@Override
	public String toString() {
		return "ProductInfo [prodId=" + prodId + ", prodName=" + prodName + "]";
	}

}
