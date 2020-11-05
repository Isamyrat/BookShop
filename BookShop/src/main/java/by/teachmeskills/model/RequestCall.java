package by.teachmeskills.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "Request_call")
public class RequestCall {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "comment", nullable = false)
    private String comment;

    @Column(name = "phone_number",nullable = false)
    private Byte phoneNumber;

    @Column(name = "data",nullable = false)
    private LocalDateTime data;

    @Column(name = "admin_name",nullable = false)
    private String adminName;
}
