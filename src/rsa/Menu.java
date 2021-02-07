package rsa;
import java.io.IOException;
import java.util.Scanner;

public class Menu {
    KeyHandling key = new KeyHandling();
    TheCrypt crypt = new TheCrypt();

    void menu() throws IOException, ClassNotFoundException {
        System.out.println("<<<<<RSA message Crypto>>>>>");
        System.out.println("1. Generate keys");
        System.out.println("2. Encrypt message");
        System.out.println("3. decrypt message");
        System.out.println("0. exit");
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        switch (choice) {
            case 1:
                choice = 1;
                System.out.println("Please input key file name: ");
                String filename = sc.next();
                int bitLength = 4096;
                key.generateKeys(filename, bitLength);
                System.out.println("Back to menu? Y/N");
                String back = sc.next();
                if (back.equals("Y")){ menu(); }else break;

            case 2:
                choice = 2;
                System.out.println("Encrypt from File or input message? File/Msg");
                String FileMsg = sc.next();
                if (FileMsg.equals("File")){
                    System.out.println("Input filename: ");
                    filename = sc.next();
                    String message = crypt.getMsg(filename + ".txt");
                    System.out.println("And what key do you want to encrypt whit");
                    String fileName = sc.next();
                    KeyPair publicKey = key.readKey(fileName + "_pub.key");
                    String encrypted = crypt.encrypt(message, publicKey);
                    sc.reset();
                    System.out.println("What filename do you want to save message to? ");
                    String msgFileName = sc.next();
                    crypt.saveMsg(msgFileName, encrypted);
                    sc.reset();
                }else {
                    System.out.println("Please input message to encrypt: ");
                    String message = sc.next();
                    sc.reset();
                    sc.reset();System.out.println("And what key do you want to encrypt whit");
                    String fileName = sc.next();
                    KeyPair publicKey = key.readKey(fileName + "_pub.key");
                    String encrypted = crypt.encrypt(message, publicKey);
                    sc.reset();
                    System.out.println("What filename do you want to save message to? ");
                    String msgFileName = sc.next();
                    crypt.saveMsg(msgFileName, encrypted);
                    sc.reset();
                }
                System.out.println("Back to menu? Y/N");
                back = sc.next();
                if (back.equals("Y")){ menu(); }else break;

            case 3:
                choice = 3;
                System.out.println("Witch file do you want to decrypt? ");
                String input = sc.next();
                String decryptable = crypt.getCrypted(input + ".txt");
                sc.reset();
                System.out.println("what key-set do you want to decrypt whit?");
                String keyName = sc.next();
                KeyPair privateKey = key.readKey(keyName + "_priv.key");
                sc.reset();
                System.out.println("Your message was decrypted and the result is: " + crypt.decrypt(decryptable, privateKey));
                System.out.println("Back to menu? Y/N");
                back = sc.next();
                if (back.equals("Y")){menu();} else break;
            case 4:
                sc.close();
                break;
        }
    }
}
