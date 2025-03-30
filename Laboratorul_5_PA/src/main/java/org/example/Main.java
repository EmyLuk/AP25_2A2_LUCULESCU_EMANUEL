package org.example;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        var repository = new Repository(new ArrayList<>());
        Image img = new Image(
                "Desktop",
                LocalDate.of(2024, 3, 25),
                List.of("example", "tag"),
                "C:\\AP25_2A2_LUCULESCU_EMANUEL\\Laboratorul_5_PA\\image\\e28VfFi.jpg"
        );

        repository.addImage(img);
        repository.displayImage(repository.getImages().get(0));
    }
}
