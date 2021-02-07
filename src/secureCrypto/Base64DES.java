package secureCrypto;
import javax.crypto.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

public class Base64DES {

    public static void main(String[] args) throws NoSuchAlgorithmException, IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchPaddingException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
        SecretKey desKey = keyGenerator.generateKey();

        // DES Encryption + Base64
        String msg = "Message";

        byte[] encMsgBytes = encryptWithDES(msg, desKey);
        String encMsgStr = Arrays.toString(encMsgBytes);

        System.out.println("Message length: " + msg.length() + "\nDES bytes length: " + encMsgBytes.length +
                "\nDES message string: " + encMsgStr + "\nDES message string length: " + encMsgStr.length());

        String encMsgBase64 = desMsgBase64Encoded(encMsgBytes); // final output

        byte[] decodedEncMsg = decodeBase64toDESMsg(encMsgBase64);
        String decodedStr = decryptDES(decodedEncMsg, desKey);
    }

    static byte[] encryptWithDES(String msg, SecretKey desKey) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {

        Cipher desCipher = Cipher.getInstance("DES/ECB/PKCS5Padding");

        desCipher.init(Cipher.ENCRYPT_MODE, desKey);

        byte[] msgBytes = msg.getBytes();

        return desCipher.doFinal(msgBytes);
    }

    static String decryptDES(byte[] encryptedMsg, SecretKey desKey) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher desCipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        desCipher.init(Cipher.DECRYPT_MODE, desKey);

        return new String(desCipher.doFinal(encryptedMsg));
    }

    static String desMsgBase64Encoded(byte[] encryptedBytes) {
        String encoded = Base64.getEncoder().withoutPadding().encodeToString(encryptedBytes);
        System.out.println("\nBase64 length: " + encoded.length());

        return encoded;
    }

    static byte[] decodeBase64toDESMsg(String base64EncMsg) {
        return Base64.getDecoder().decode(base64EncMsg);
    }

}