package org.example;

import lombok.*;
import java.util.stream.Collectors;
import java.util.*;

@Getter
@AllArgsConstructor
@Setter
@ToString

class Location implements Comparable<Location>{
    public enum type {
        friendly,
        neutral,
        enemy
    }

    private String name;
    private type type;
    @Override
    public int compareTo(Location o) {
        return this.name.compareTo(o.name);
    }

}

public class Main {
    public static void main(String[] args) {
        Location l1 = new Location("Village", Location.type.friendly);
        Location l2 = new Location("Desert", Location.type.neutral);
        Location l3 = new Location("Forest", Location.type.enemy);

        List<Location> locations = Arrays.asList(l1,l2,l3);
        TreeSet<Location> friendlyLocations = locations.stream().filter(location -> location.getType()== Location.type.friendly)
                        .collect(Collectors.toCollection(TreeSet::new));
        System.out.println("Friendly Locations: " + friendlyLocations);

        LinkedList<Location> enemyLocations = locations.stream()
                .filter(location -> location.getType() == Location.type.enemy)
                .sorted(Comparator.comparing(Location::getName))
                .collect(Collectors.toCollection(LinkedList::new));

        System.out.println("Enemy Locations: " + enemyLocations);
    }

}