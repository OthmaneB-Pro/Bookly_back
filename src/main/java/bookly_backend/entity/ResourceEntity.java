package bookly_backend.entity;

import bookly_backend.enums.Type;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "resource")
public class ResourceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated
    private Type type;
    private String title;
    private String description;
    private Integer capacity;
    private Date date;
    private Boolean availability;
    @ManyToOne
    private UserEntity userId;
}
