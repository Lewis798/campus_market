package com.yuanlrc.campus_market.entity.common;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="common_goods")
public class Goods extends BaseEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name="name",nullable=false,length=64)
    private String name;
    
    @Column(name="price",nullable=false)
    private Float price;
    
    @Column(name="status",nullable=false)
    private Integer status;
    
    @ManyToOne
    @JoinColumn(name="category_id")
    private GoodsCategory category;
    
    public static final int GOODS_STATUS_UP = 1;
    public static final int GOODS_STATUS_DOWN = 0;
    
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
    
    public Float getPrice() {
        return price;
    }
    
    public void setPrice(Float price) {
        this.price = price;
    }
    
    public Integer getStatus() {
        return status;
    }
    
    public void setStatus(Integer status) {
        this.status = status;
    }
    
    public GoodsCategory getCategory() {
        return category;
    }
    
    public void setCategory(GoodsCategory category) {
        this.category = category;
    }
} 