import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class KeyboardBarcodeScanner extends BarcodeScanner {

    private BufferedReader scanner;

    public KeyboardBarcodeScanner() {
        scanner = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public String scan() {
        try {
            if (!scanner.ready()) System.out.print("Enter barcode: ");
            return scanner.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    @Override
    public void close() {
        try {
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isReady() {
        try {
            if (scanner.ready()) return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}