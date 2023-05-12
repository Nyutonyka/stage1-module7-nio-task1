package com.epam.mjc.nio;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileReader {

    public Profile getDataFromFile(File file) {
        Path path = file.toPath();
        Profile profile = null;
        String name = "";
        int age = 0;
        String email = "";
        long phone = 0L;
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                switch (parts[0]) {
                    case "Name":
                        name = parts[1].trim();
                        break;
                    case "Age":
                        age = Integer.parseInt(parts[1].trim());
                        break;
                    case "Email":
                        email = parts[1].trim();
                        break;
                    case "Phone":
                        phone = Long.parseLong(parts[1].trim());
                        break;
                    default:
                        System.out.println("Invalid input.");
                        break;
                }
            }
            profile = new Profile(name, age, email, phone);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return profile;
    }

    public static void main(String[] args) {
        FileReader fr = new FileReader();
        Path path = Path.of("src/main/resources/Profile.txt");
        File file = path.toFile();
        System.out.println(fr.getDataFromFile(file));
    }
}