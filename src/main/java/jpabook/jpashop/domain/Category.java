package jpabook.jpashop.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Category {

    @Id
    @GeneratedValue
    @Column(name = "CATEGORY_ID")
    private Long id;

    private String name;

    // self join
    @ManyToOne
    @JoinColumn(name = "PARENT_ID")
    private Category parent;

    // self join
    @OneToMany(mappedBy = "parent")
    private List<Category> child = new ArrayList<>();

    // 다대다 매핑
    // 실무에서는 안쓰는 것을 추천한다..(중간 테이블에 컬럼 등을 추가할 수가 없다)
    // 다대다 대신 다대일, 일대다로 매핑하는 것을 추천한다.
    @ManyToMany
    @JoinTable(name = "CATEGORY_ITEM",
            joinColumns = @JoinColumn(name = "CATEGORY_ID"),
            inverseJoinColumns = @JoinColumn(name = "ITEM_ID")
    )
    private List<Item> items = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getParent() {
        return parent;
    }

    public void setParent(Category parent) {
        this.parent = parent;
    }

    public List<Category> getChild() {
        return child;
    }

    public void setChild(List<Category> child) {
        this.child = child;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
