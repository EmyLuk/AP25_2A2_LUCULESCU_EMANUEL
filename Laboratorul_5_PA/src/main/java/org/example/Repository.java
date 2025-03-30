package org.example;

import lombok.*;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor

public class Repository {
    private final List<Image> images;
    public void addImage(Image image) {
        images.add(image);
    }
    public void displayImage(Image image) {
        File imageFile = new File(image.path());
        if (!imageFile.exists()) {
            System.err.println("Error: The file " + image.path() + " does not exist.");
            return;
        }
        if (Desktop.isDesktopSupported()) {
            try {
                Desktop.getDesktop().open(imageFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Desktop is not supported on this system.");
        }
    }
    public List<Image> getImages() {
        return new ArrayList<>(images);
    }
}