import java.util.Scanner;

public class FakeBarcodeScanner extends BarcodeScanner {

    private String scan;

    public FakeBarcodeScanner(String scan) {
        this.scan = scan;
    }

    @Override
    public String scan() {
        return scan;
    }

    @Override
    public void close() {
        scan = null;
    }
}