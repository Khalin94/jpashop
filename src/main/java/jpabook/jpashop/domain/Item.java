package jpabook.jpashop.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS) // 조인 전략으로 상속
public abstract class Item extends BasedEntity{ // 해당 엔티티를 직접 만들일이 없으므로 추상클래스로 만든다.

    @Id
    @GeneratedValue
    @Column(name = "ITEM_ID")
    private Long id;
    private String name;
    private int price;
    private int stockQuantity;

    //다대다 양방향 매핑
    // 실무에서는 안쓰는 것을 추천한다..(중간 테이블에 컬럼 등을 추가할 수가 없다)
    // 다대다 대신 다대일, 일대다로 매핑하는 것을 추천한다.
    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }


    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", stockQuantity=" + stockQuantity +
                ", categories=" + categories +
                "} " + super.toString();
    }
}
