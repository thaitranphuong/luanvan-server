package com.luanvan.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "import_details")
public class ImportDetailEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;	
	
	private int quantity;
	private double price;
	
	@ManyToOne
	@JoinColumn(name = "product_detail_size_id")
	private ProductDetailSizeEntity productDetailSize;
	
	@ManyToOne
	@JoinColumn(name = "import_id")
	private ImportEntity _import;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public ProductDetailSizeEntity getProductDetailSize() {
		return productDetailSize;
	}

	public void setProductDetailSize(ProductDetailSizeEntity productDetailSize) {
		this.productDetailSize = productDetailSize;
	}

	public ImportEntity get_import() {
		return _import;
	}

	public void set_import(ImportEntity _import) {
		this._import = _import;
	}
	
	
}
