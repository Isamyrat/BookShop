package by.teachmeskills.model;

import lombok.*;
import javax.persistence.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
@EqualsAndHashCode(of = "id")
@Entity
@Builder
@ToString()
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Byte id;

    @Column(name = "name",nullable = false)
    private String name;

    @OneToMany(mappedBy = "category",fetch = FetchType.EAGER)
    private List<Book> books= new ArrayList<>();

    public Category(String name) {
        this.name = name;
    }
}
