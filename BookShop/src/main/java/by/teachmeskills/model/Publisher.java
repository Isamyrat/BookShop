package by.teachmeskills.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@ToString(exclude = "book")
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
@EqualsAndHashCode(of = "id")
@Entity
@Builder
@Table(name = "Publisher")
public class Publisher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Byte id;

    public String name;

    @OneToMany(mappedBy = "publisher")
    private List<Book> book = new ArrayList<>();

    public Publisher(String name) {
        this.name = name;
    }
}
