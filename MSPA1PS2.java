//problem statement 2 develop a java program to store in a file the program should be reading the file content.
//mspa1_sheikhwasimuddin_iot63

import java.io.*;

public class MSPA1PS2{
    public static void main(String[] args) {
        String fileName = "example.txt";
        String content = "Hello, my name is sheikh wasimuddin";
        
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(content);
            System.out.println("Content written to file successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file");
            e.printStackTrace();
        }
        
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            System.out.println("Reading content from the file:");
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file.");
            e.printStackTrace();
        }
    }
}
    