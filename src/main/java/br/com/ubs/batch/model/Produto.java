package br.com.ubs.batch.model;
import java.io.Serializable;
import java.math.BigDecimal;

import br.com.ubs.batch.dto.ProdutoDto;

public class Produto implements Serializable{
	
	private static final long serialVersionUID = -8930807240418159908L;
	private String product;
	private BigDecimal quantity;
	private BigDecimal price;
	private BigDecimal volume;
	private String type;
	private String industry;
	private String origin;
	private String file_import_name;
	
	public Produto() {}
	
	public Produto(Integer id, String product, BigDecimal quantity, BigDecimal price, BigDecimal volume, String type, String industry,
			String origin, String file_import_name) {
		super();
		this.product = product;
		this.quantity = quantity;
		this.price = price;
		this.volume = volume;
		this.type = type;
		this.industry = industry;
		this.origin = origin;
		this.file_import_name = file_import_name;
	}
	
	public Produto(Integer id, String product, BigDecimal quantity, BigDecimal price, String type, String industry,
			String origin, String file_import_name) {
		super();
		
		this.product = product;
		this.quantity = quantity;
		this.price = price;
		this.volume = this.price.multiply(this.quantity);
		this.type = type;
		this.industry = industry;
		this.origin = origin;
		this.file_import_name = file_import_name;
	}
	
	public Produto(String product, BigDecimal quantity, BigDecimal price, String type, String industry,
			String origin, String file_import_name) {
		super();
		this.product = product;
		this.quantity = quantity;
		this.price = price;
		this.volume = this.price.multiply(this.quantity);
		this.type = type;
		this.industry = industry;
		this.origin = origin;
		this.file_import_name = file_import_name;
	}
	
	public Produto(ProdutoDto produtoDto, String file_import_name) {
		super();
		this.product = produtoDto.getProduct();
		this.quantity = produtoDto.getQuantity();
		this.price = new BigDecimal(produtoDto.getPrice().substring(1)) ;
		this.volume = this.quantity.multiply(this.price);
		this.type = produtoDto.getType();
		this.industry = produtoDto.getIndustry();
		this.origin = produtoDto.getOrigin();
		this.file_import_name = file_import_name;
	}
	
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public BigDecimal getQuantity() {
		return quantity;
	}
	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getIndustry() {
		return industry;
	}
	public void setIndustry(String industry) {
		this.industry = industry;
	}
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public BigDecimal getVolume() {
		return volume;
	}
	public void setVolume(BigDecimal volume) {
		this.volume = volume;
	}
	public String getFile_import_name() {
		return file_import_name;
	}
	public void setFile_import_name(String file_import_name) {
		this.file_import_name = file_import_name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((file_import_name == null) ? 0 : file_import_name.hashCode());
		result = prime * result + ((industry == null) ? 0 : industry.hashCode());
		result = prime * result + ((origin == null) ? 0 : origin.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((product == null) ? 0 : product.hashCode());
		result = prime * result + ((quantity == null) ? 0 : quantity.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + ((volume == null) ? 0 : volume.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		if (file_import_name == null) {
			if (other.file_import_name != null)
				return false;
		} else if (!file_import_name.equals(other.file_import_name))
			return false;
		if (industry == null) {
			if (other.industry != null)
				return false;
		} else if (!industry.equals(other.industry))
			return false;
		if (origin == null) {
			if (other.origin != null)
				return false;
		} else if (!origin.equals(other.origin))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		if (quantity == null) {
			if (other.quantity != null)
				return false;
		} else if (!quantity.equals(other.quantity))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		if (volume == null) {
			if (other.volume != null)
				return false;
		} else if (!volume.equals(other.volume))
			return false;
		return true;
	}
}
