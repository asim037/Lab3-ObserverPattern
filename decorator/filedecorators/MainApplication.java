/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package decorator.filedecorators;
import java.io.*;
import java.nio.charset.StandardCharsets;
interface DataSource {
    void writeData(String data);
    String readData();
}
class FileDataSource implements DataSource {
    private String filename;
    public FileDataSource(String filename) {
        this.filename = filename;
    }
    @Override
    public void writeData(String data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public String readData() {
        StringBuilder data = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                data.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data.toString();
    }
}
class DataSourceDecorator implements DataSource {
    protected DataSource wrappee;

    public DataSourceDecorator(DataSource source) {
        this.wrappee = source;
    }
    @Override
    public void writeData(String data) {
        wrappee.writeData(data);
    }
    @Override
    public String readData() {
        return wrappee.readData();
    }
}
class EncryptionDecorator extends DataSourceDecorator {
    public EncryptionDecorator(DataSource source) {
        super(source);
    }
    @Override
    public void writeData(String data) {
        String encryptedData = "Encrypted: " + data;
        super.writeData(encryptedData);
    }
    @Override
    public String readData() {
        String encryptedData = super.readData();
        return encryptedData.replace("Encrypted: ", "");
    }
}
class CompressionDecorator extends DataSourceDecorator {
    public CompressionDecorator(DataSource source) {
        super(source);
    }
    @Override
    public void writeData(String data) {
        String compressedData = "Compressed: " + data;
        super.writeData(compressedData);
    }
    @Override
    public String readData() {
        String compressedData = super.readData();
        return compressedData.replace("Compressed: ", "");
    }
}
class UTF8Decorator extends DataSourceDecorator {
    public UTF8Decorator(DataSource source) {
        super(source);
    }
    @Override
    public void writeData(String data) {
        byte[] utf8Bytes = data.getBytes(StandardCharsets.UTF_8);
        String utf8EncodedData = new String(utf8Bytes, StandardCharsets.UTF_8);
        super.writeData(utf8EncodedData);
    }
    @Override
    public String readData() {
        String utf8EncodedData = super.readData();
        byte[] utf8Bytes = utf8EncodedData.getBytes(StandardCharsets.UTF_8);
        return new String(utf8Bytes, StandardCharsets.UTF_8);
    }
}
public class MainApplication {
    public static void main(String[] args) {
        DataSource source = new FileDataSource("somefile.dat");
        source = new CompressionDecorator(source);
        source = new EncryptionDecorator(source);
        source = new UTF8Decorator(source);
        String data = "Sample data to write and read.";
        source.writeData(data);
        String loadedData = source.readData();
        System.out.println("Loaded Data:");
        System.out.println(loadedData);
    }
}
