package crypto.challenges;

public class Challenge2 {
    Challenge2() {
        doIt();
    }

    private void doIt() {
        String feedString = "1c0111001f010100061a024b53535009181c";
        String xorString = "686974207468652062756c6c277320657965";
        String rightResult = "746865206b696420646f6e277420706c6179";

        byte[] a = hexStringToByteArray(feedString);
        String dehexFeedString = new String(a);
        System.out.println("Dehex feedString: " + dehexFeedString);

        byte[] b = hexStringToByteArray(xorString);
        String dehexXorString = new String(b);
        System.out.println("dehex XorString: " + dehexXorString);

        byte[] k = dehexFeedString.getBytes();
        byte[] n = dehexXorString.getBytes();

        StringBuilder resultSB = new StringBuilder();
        for (int i = 0; i < k.length; i++) {
            resultSB.append((char) (k[i] ^ n[i]));
        }
        String result = resultSB.toString();
        System.out.println("Dehexed Result: " + result);

        byte[] x = result.getBytes();
        String eDetRätt = byteToHex(x);
        System.out.println("Hex result: " + eDetRätt.toLowerCase());
        System.out.println("Right answer: " +rightResult);
        if(rightResult.equals(eDetRätt.toLowerCase())){
            System.out.println("YEAY");
        }else{
            System.out.println("hahahah fel!");
        }
    }

    private String byteToHex(byte[] x) {
        char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();
        char[] hexChars = new char[x.length *2];
        for (int i = 0; i < x.length; i++) {
            int value = x[i] & 0xFF;
            hexChars[i*2] = HEX_ARRAY[value>>>4];
            hexChars[i*2 + 1] = HEX_ARRAY[value & 0x0F];
        }
        return new String(hexChars);
    }

    private byte[] hexStringToByteArray(String s) {
        int length = s.length();
        if (length % 2 != 0) {
            s = "0" + s;
            length++;
        }
        byte[] data = new byte[length / 2];
        for (int i = 0; i < length; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i + 1), 16));
        }
        return data;

    }

    public static void main(String[] args) {
        new Challenge2();
    }

}
