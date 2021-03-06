package edu.zhoutt.mall.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    private Long id;

    private Long userId;

    private String name;

    private String phone;

    private String address;

    private Long addressId;

    private Long productId;

    private Integer number;

    private BigDecimal price;

    private BigDecimal total;

    private Integer status;

    private Date createTime;

    private Date updateTime;

}
