package br.com.adegagatopreto.model;

import br.com.adegagatopreto.enums.ActiveStatus;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "product")
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ActiveStatus status;
    @Column(nullable = false)
    private Integer barcode;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String type;
    @Column(nullable = false)
    private String size;
    @Column(nullable = false)
    private String buyValue;
    @Column(nullable = false)
    private String sellValue;
    @Column(nullable = false)
    private Integer quantity;

    public Product() {
    }

    public Product(Long id, ActiveStatus status, Integer barcode, String name, String type, String size, String buyValue, String sellValue, Integer quantity) {
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

    public Integer getBarcode() {
        return barcode;
    }

    public void setBarcode(Integer barcode) {
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
        if (!(o instanceof Product product)) return false;
        return Objects.equals(getId(), product.getId()) && getStatus() == product.getStatus() && Objects.equals(getBarcode(), product.getBarcode()) && Objects.equals(getName(), product.getName()) && Objects.equals(getType(), product.getType()) && Objects.equals(getSize(), product.getSize()) && Objects.equals(getBuyValue(), product.getBuyValue()) && Objects.equals(getSellValue(), product.getSellValue()) && Objects.equals(getQuantity(), product.getQuantity());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getStatus(), getBarcode(), getName(), getType(), getSize(), getBuyValue(), getSellValue(), getQuantity());
    }
}
