package secureCrypto;
import java.security.NoSuchAlgorithmException;
import java.util.*;


class XORSimpleBase64 {
    static Scanner scn;

    public static void main(String[]args) throws NoSuchAlgorithmException {
        scn = new Scanner(System.in);

        System.out.println("1. Encryption\n2. Decryption");
        int choice = scn.nextInt();
        scn.nextLine();
        if (choice == 1) {
            System.out.println("Encryption");
            cipherEncryption();
        } else if (choice == 2) {
            System.out.println("Decryption");
            cipherDecryption();
        } else {
            System.out.println("Invalid choice");
        }
    }

    //key : f97fa7943320f29a9480b6a88b90e0f397860462
    //msg : 0856434617524b4d135f5d5e011254044a475957071615571807575317491647
    private static void cipherDecryption() {
        System.out.print("Enter message: ");
        String msg = scn.nextLine();

        System.out.print("Enter key: ");
        String key = scn.nextLine();

        String hexToDeci = "";
        for (int i = 0; i < msg.length() - 1; i+=2) {
            // split hex
            String output = msg.substring(i, (i+2));
            int decimal = Integer.parseInt(output, 16);
            hexToDeci += (char)decimal;
        }
        // decryption
        String decrypted = "";
        int keyIteration = 0;
        for (int i = 0; i < hexToDeci.length(); i++) {
            //XOR
            int temp = hexToDeci.charAt(i) ^ key.charAt(keyIteration);

            decrypted += (char)temp;
            keyIteration++;
            if (keyIteration >= key.length()) {
                // reset key
                keyIteration = 0;
            }
        }
        System.out.println("Decrypted message: " + decrypted);
    }

    private static void cipherEncryption() throws NoSuchAlgorithmException {
        System.out.print("Enter message: ");
        String msg = scn.nextLine();

        System.out.print("Enter key: ");
        String key = scn.nextLine();

        String encryptedHex = "";
        int keyIteration = 0;
        for (int i = 0; i < msg.length(); i++) {
            //XOR
            int temp = msg.charAt(i) ^ key.charAt(keyIteration);

            encryptedHex += String.format("%02x", (byte) temp);
            keyIteration++;
            if (keyIteration >= key.length()) {
                // reset key
                keyIteration = 0;
            }
        }
        System.out.println("----------------------------------------------------\n" +
                "Message length: " + msg.length() + "\nEncrypted Text: " + encryptedHex +
                "\nEncrypted text length: " + encryptedHex.length());

        // Bas64 encoding
        String encoded = Base64.getEncoder().withoutPadding().encodeToString(encryptedHex.getBytes());
        System.out.println("Base64: " + encoded + "\nBase64 length: " + encoded.length());

    }
}