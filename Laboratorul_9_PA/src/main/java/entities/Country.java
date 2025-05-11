package entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "countries")
@NamedQuery(name = "Country.findByName", query = "SELECT c FROM Country c WHERE c.name LIKE :name")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String code;
    private String continent;
}
