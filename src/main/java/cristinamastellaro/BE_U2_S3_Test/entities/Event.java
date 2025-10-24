package cristinamastellaro.BE_U2_S3_Test.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "events")
@Getter
@Setter
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
    private List<Person> peopleThatWillPartecipate = new ArrayList<>();

    public Event(String title, String description, String place, LocalDate date, int maxNumPeople, Person creator) {
        this.title = title;
        this.description = description;
        this.place = place;
        this.date = date;
        this.maxNumPeople = maxNumPeople;
        this.creator = creator;
    }

    @Override
    public String toString() {
        return "Event{" +
                "creator=" + creator +
                ", maxNumPeople=" + maxNumPeople +
                ", date=" + date +
                ", place='" + place + '\'' +
                ", description='" + description + '\'' +
                ", title='" + title + '\'' +
                ", id=" + id +
                '}';
    }
}
