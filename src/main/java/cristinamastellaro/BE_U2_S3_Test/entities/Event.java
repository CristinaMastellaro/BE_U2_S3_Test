package cristinamastellaro.BE_U2_S3_Test.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "events")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Event {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private UUID id;
    @Column(nullable = false)
    private String title;
    private String description;
    @Column(nullable = false)
    private String place;
    @Column(nullable = false)
    private LocalDate date;
    @Column(nullable = false)
    private int maxNumPeople;

    @ManyToOne
    private Person creator;

}
