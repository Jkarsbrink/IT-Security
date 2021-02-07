package crypto.challenges;

import java.util.Base64;

public class Challenge1 {

    Challenge1(){
        convert();
    }
    void convert(){
        String encryptedText = "49276d206b696c6c696e6720796f757220627261696e206c696b65206120706f69736f6e6f7573206d757368726f6f6d";
        String answerText = "SSdtIGtpbGxpbmcgeW91ciBicmFpbiBsaWtlIGEgcG9pc29ub3VzIG11c2hyb29t";

        byte[] a = hexStringToByteArray(encryptedText);

        StringBuilder fromASCIIToCharEncryptedText = new StringBuilder();
        for(byte x: a){
            fromASCIIToCharEncryptedText.append((char) x);
        }
        String dehexEncryptedText = fromASCIIToCharEncryptedText.toString();
        System.out.println("Text :" + dehexEncryptedText);

        String result = base64Encode(dehexEncryptedText);

        System.out.println(result);
        System.out.println(answerText);

        if(result.equals(answerText)){
            System.out.println("YEAY");
        }else {
            System.out.println("försök igen");
        }
    }

    private String base64Encode(String s) {
        return Base64.getEncoder().encodeToString(s.getBytes());
    }

    private byte[] hexStringToByteArray(String s) {
        int length = s.length();
        if(length % 2 != 0){
            s = "0" + s;
            length++;
        }
        byte[] data = new byte[length/2];
        for (int i =0; i <length; i+=2){
            data[i/2] = (byte) ((Character.digit(s.charAt(i),16) <<4) + Character.digit(s.charAt(i+1), 16));
        }
        return data;
    }

    public static void main(String[] args) {
        new Challenge1();
    }
}
