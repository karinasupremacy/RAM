package controller;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import models.RamItems;

public class FileController {

    private static final String FILE_NAME = "input.dat";

    // Lưu danh sách vào file nhị phân sử dụng ObjectOutputStream
public void saveToFile(List<RamItems> rams) {
    File file = new File(FILE_NAME);

    try (FileOutputStream fos = new FileOutputStream(file);
         ObjectOutputStream objectOut = new ObjectOutputStream(fos)) {
        objectOut.writeObject(rams);
    } catch (IOException e) {
        System.out.println("Error writing to file: " + e.getMessage());
    }
}
    
    public static List<RamItems> loadFromFile() throws IOException, ClassNotFoundException {
        File file = new File(FILE_NAME);
        if (file.exists()) {
            try (FileInputStream fis = new FileInputStream(file);
                 ObjectInputStream ois = new ObjectInputStream(fis)) {
                return (List<RamItems>) ois.readObject();
            }
        } else {
            // Nếu file không tồn tại, trả về danh sách trống
            System.out.println("File not found, returning empty RAM list.");
            return new ArrayList<>();
        }
    }
    
    
}
