package by.teachmeskills.model;


import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Table;
import java.io.Serializable;

@Embeddable

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode

public class OrderItemKey implements Serializable {


    @Column(name = "book_id", nullable = false)
    private Long bookId;

    @Column(name = "order_id", nullable = false)
    private Long orderId;
}
