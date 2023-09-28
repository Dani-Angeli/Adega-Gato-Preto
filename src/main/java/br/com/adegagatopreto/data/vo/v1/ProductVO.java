package br.com.adegagatopreto.data.vo.v1;


import java.io.Serializable;
import java.util.Objects;

public class ProductVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private String type;
    private String size;
    private String buyValue;
    private String sellValue;
    private Integer quantity;

    public ProductVO() {
    }

    public ProductVO(Long id, String name, String type, String size, String buyValue, String sellValue, Integer quantity) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.size = size;
        this.buyValue = buyValue;
        this.sellValue = sellValue;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getBuyValue() {
        return buyValue;
    }

    public void setBuyValue(String buyValue) {
        this.buyValue = buyValue;
    }

    public String getSellValue() {
        return sellValue;
    }

    public void setSellValue(String sellValue) {
        this.sellValue = sellValue;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductVO product)) return false;
        return Objects.equals(getId(), product.getId()) && Objects.equals(getName(), product.getName()) && Objects.equals(getType(), product.getType()) && Objects.equals(getSize(), product.getSize()) && Objects.equals(getBuyValue(), product.getBuyValue()) && Objects.equals(getSellValue(), product.getSellValue()) && Objects.equals(getQuantity(), product.getQuantity());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getType(), getSize(), getBuyValue(), getSellValue(), getQuantity());
    }
}
