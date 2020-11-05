package by.teachmeskills.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table (name ="orders")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Builder
public class Orders {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name="time_of_order",nullable = false)
    private LocalDateTime time_of_order;

    @Column (name="user_name",nullable = false)
    private String user_name;

    @Column(name = "type_of_payment",nullable = false)
    private String type_of_payment;

    @Column (name="user_phone",nullable = false)
    private String user_phone;

    @Column (name="order_price",nullable = false)
    private Integer order_price;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orders_items = new ArrayList<>();

    @Column(name = "paid_up")
    private Boolean paid_up;

}
