package jpabook.jpashop.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.*;

@Entity
public class Category extends BaseEntity{

    @Id @GeneratedValue
    private Long id;

    private String name;

    /**
     * 셀프 양방향 맵핑
     */
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "PARENT_ID")
    private Category parent;

    @OneToMany(mappedBy = "parent")
    private List<Category> child = new ArrayList<>();

    /**
     * 다대다 맵핑.
     * 실무에서는 @ManyToMany를 사용하지 말것
     */
    @ManyToMany
    @JoinTable(name = "CATEGORY_ITEM"
            , joinColumns = @JoinColumn(name = "CATEGORY_ID")
            , inverseJoinColumns = @JoinColumn(name = "ITEM_ID"))
    private List<Item> items = new ArrayList<>();


}
