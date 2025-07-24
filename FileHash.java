import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class FileHash {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java FileHash <filename>");
            return;
        }

        String filename = args[0];

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            HashTester ht = new HashTester("SHA-256");

            String line;
            while ((line = reader.readLine()) != null) {
                ht.update(line); // uses HashTester.update(String)
            }

            String hashResult = ht.formatHashValue(ht.digest());
            System.out.println("SHA-256 Hash: " + hashResult);

        } catch (IOException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
