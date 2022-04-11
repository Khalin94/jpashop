package jpabook.jpashop.domain;

import javax.persistence.*;

@Entity
public class Delivery extends BasedEntity{

    @Id
    @GeneratedValue
    @Column(name = "DELIVERY_ID")
    private Long id;
    @Column(name = "CITY")
    private String city;
    @Column(name = "ZIPCODE")
    private String zipcode;
    @Column(name = "STATUS")
    private String status;

    // 일대일 양방향 매핑
    // 주인X
    // order의 값을 바꿔도 주인이 아니면 order의 값이 바뀌지 않는다 (read만 가능)
    @OneToOne(mappedBy = "delivery")
    private Order order;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Long getId() {
        return id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
