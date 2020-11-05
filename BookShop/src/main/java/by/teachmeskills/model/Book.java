package by.teachmeskills.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@ToString(exclude = {"publisher","category","carts"})
@Table(name = "book")
@Builder
@Access(AccessType.FIELD)
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name = null;

    @Column(name = "author", nullable = false)
    private String author = null;

    @Column(name = "price", nullable = false)
    private Double price = null;

    @Column(name = "describe", nullable = false)
    private String describe= null;

    @Column(name = "year_of_publication", nullable = false)
    private Integer year_of_publication = null;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publisher_id")
    private Publisher publisher = null;

    @Column(name = "count_in_storage", nullable = false)
    private Integer count_in_storage = null;

    @OneToOne(mappedBy = "book")
    OrderItem order_item;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category = null;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "cart_book",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "cart_id"))
    private List<Cart> carts = new ArrayList<>();
}
