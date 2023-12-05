package br.com.adegagatopreto.data.vo.v1;


import br.com.adegagatopreto.enums.ActiveStatus;

import java.io.Serializable;
import java.util.Objects;

public class ProductVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private ActiveStatus status;
    private String barcode;
    private String name;
    private String type;
    private String size;
    private String buyValue;
    private String sellValue;
    private Integer quantity;

    public ProductVO() {
    }

    public ProductVO(Long id, ActiveStatus status, String barcode, String name, String type, String size, String buyValue, String sellValue, Integer quantity) {
        this.id = id;
        this.status = status;
        this.barcode = barcode;
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

    public ActiveStatus getStatus() {
        return status;
    }

    public void setStatus(ActiveStatus status) {
        this.status = status;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
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
        if (!(o instanceof ProductVO productVO)) return false;
        return Objects.equals(getId(), productVO.getId()) && getStatus() == productVO.getStatus() && Objects.equals(getBarcode(), productVO.getBarcode()) && Objects.equals(getName(), productVO.getName()) && Objects.equals(getType(), productVO.getType()) && Objects.equals(getSize(), productVO.getSize()) && Objects.equals(getBuyValue(), productVO.getBuyValue()) && Objects.equals(getSellValue(), productVO.getSellValue()) && Objects.equals(getQuantity(), productVO.getQuantity());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getStatus(), getBarcode(), getName(), getType(), getSize(), getBuyValue(), getSellValue(), getQuantity());
    }
}
