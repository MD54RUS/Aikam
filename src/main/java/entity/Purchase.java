package entity;

import java.util.Date;

public class Purchase {
    private Long id;
    private Long customerId;
    private Long goodsId;
    private Date date;

    public void setId(Long id) {
        this.id = id;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public Long getGoodsId() {
        return goodsId;
    }
}
