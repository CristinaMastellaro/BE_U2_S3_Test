package cristinamastellaro.BE_U2_S3_Test.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "events")
@Getter
@Setter
@ToString
@NoArgsConstructor
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

    @ManyToMany(mappedBy = "eventsToPartecipate")
    @JsonIgnore
    private List<Person> peopleThatWillPartecipate;

    public Event(String title, String description, String place, LocalDate date, int maxNumPeople, Person creator) {
        this.title = title;
        this.description = description;
        this.place = place;
        this.date = date;
        this.maxNumPeople = maxNumPeople;
        this.creator = creator;
    }

}
