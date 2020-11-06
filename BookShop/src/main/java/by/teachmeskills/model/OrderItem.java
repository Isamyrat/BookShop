package by.teachmeskills.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table (name = "orders_item")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode (of = "id")
@Builder
public class OrderItem {

    @EmbeddedId
    private OrderItemKey id;

     @OneToOne (optional = false, cascade = CascadeType.ALL)
     @MapsId("bookId")
     @JoinColumn(name="book_id")
    private Book book;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("orderId")
    @JoinColumn(name = "order_id", nullable=false)
    private Orders order;

   @Column(name = "price_of_item",nullable = false)
    private Double price;

}
