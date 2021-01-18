package com.llc.seckill.activity.entity;

import javax.persistence.*;

@Entity
@Table(name = "commodity")
public class Commodity {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "commodity_name")
    private String commodityName;

    @Column(name = "commodity_description")
    private String commodityDesc;

    @Column(name = "commodity_price")
    private Integer commodityPrice;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }

    public String getCommodityDesc() {
        return commodityDesc;
    }

    public void setCommodityDesc(String commodityDesc) {
        this.commodityDesc = commodityDesc;
    }

    public Integer getCommodityPrice() {
        return commodityPrice;
    }

    public void setCommodityPrice(Integer commodityPrice) {
        this.commodityPrice = commodityPrice;
    }

}
