package crypto.challenges;

public class Challenge3 {
    Challenge3(){
        doIt();
    }
    void doIt(){
        String feedString = "1b37373331363f78151b7f2b783431333d78397828372d363c78373e783a393b3736";
        String xorTestString;
        byte[] deHexedFeedString = hexStringToByteArray(feedString);
        StringBuilder resultFeedString =  new StringBuilder();
        for (byte b: deHexedFeedString) {
            resultFeedString.append((char) b);
        }
        System.out.println(resultFeedString);
        byte[] d = resultFeedString.toString().getBytes();
        for(char t= 'a'; t < 'z'; t++){
            xorTestString = generateTestCharacterToXorAgainst(t);
            System.out.println(xorTestString);
            byte[] l = xorTestString.getBytes();
            int clearLetter;
            StringBuilder resultString = new StringBuilder();
            for (int i = 0; i < d.length; i++) {
                byte letter = d[i];
                byte key = l[i];
                clearLetter = letter ^key;
                resultString.append((char) clearLetter);
            }
            System.out.println(resultString);
        }
    }

    private String generateTestCharacterToXorAgainst(char t) {
        String s = "";
        return (s + String.valueOf(t).repeat(35)).toUpperCase();
    }

    private byte[] hexStringToByteArray(String s) {
        int length = s.length();
        if(length % 2 != 0){
            s = "0" + s;
            length++;
        }
        byte[] data = new byte[length/2];

        for (int i = 0; i < length; i += 2) {
            data[i/2]= (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i+1),16));

        }
        return data;
    }

    public static void main(String[] args) {
        new Challenge3();
    }
}
