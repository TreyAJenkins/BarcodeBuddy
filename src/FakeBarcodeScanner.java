import java.util.Scanner;

public class FakeBarcodeScanner extends BarcodeScanner {

    private Scanner scanner;

    public FakeBarcodeScanner() {
        scanner = new Scanner(System.in);
    }

    @Override
    public String scan() {
        System.out.print("Enter barcode: ");
        return scanner.nextLine();
    }

    @Override
    public void close() {
        super.close();
        scanner.close();
    }
}