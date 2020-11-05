package by.teachmeskills.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table (name = "order_item")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode (of = "id")
@Builder
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

     @OneToOne (optional = false, cascade = CascadeType.ALL)
     @JoinColumn(name="book_id")
    private Book book;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable=false)
    private Orders order;

   @Column(name = "price",nullable = false)
    private double price;

}
