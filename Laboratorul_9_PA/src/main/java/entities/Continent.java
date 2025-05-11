package entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "continents")
@NamedQuery(name = "Continent.findByName", query = "SELECT c FROM Continent c WHERE c.name LIKE :name")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Continent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
}
