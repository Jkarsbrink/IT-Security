package crypto.challenges;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Challenge4 {
    Challenge4(){
        try{
            doIt();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void doIt() throws FileNotFoundException {
        File file = new File("C:\\Users\\juliu\\OneDrive\\Skrivbord\\KyH\\GitHub\\src\\crypto\\challenges\\Challenge4.txt");
        Scanner sc = new Scanner(file);

        int q = 0;
        String[] asd = new String[327];
        while (sc.hasNextLine()){
            asd[q++] = sc.nextLine();
        }
        sc.close();

        HashMap<Integer, String> highScore = new HashMap<>();
        int highestScore = 0;

        for (String s : asd) {
            StringBuilder result = new StringBuilder();
            byte [] deHexFeedString = hexStringToByteArray(s);

            for (byte b: deHexFeedString) {
                result.append((char) b);
            }
            for (int i = 0; i < 256; i++) {
                StringBuilder julle = new StringBuilder();
                char x = (char) i;

                for(int j = 0; j < result.length(); j++){
                    julle.append((char) (result.charAt(j) ^x));
                }
                julle.append(x);

                String res1 = julle.toString();
                int score = checkValueFromString(res1);

                highScore.put(score, res1);

                if(score >= highestScore){
                    highestScore = score;
                }
            }
            System.out.println(highScore.get(highestScore));
            System.out.println("Score: " + highestScore);
        }
    }

    private byte[] hexStringToByteArray(String s) {
        int len = s.length();
        if(len % 2 !=0){
            s = "0" + s;
            len++;
        }
        byte[] data = new byte[len/2];
        for (int i = 0; i < len; i += 2) {
            data[i/2] = (byte) ((Character.digit(s.charAt(i),16) <<4)
                + Character.digit(s.charAt(i+1),16));
        }
        return data;
    }

    private int checkValueFromString(String s) {
        int score = 0;
        for (int i = 0; i < s.length(); i++) {
            byte x = (byte) (s.charAt(i));
            if(x>97 && x < 122 || x ==32){
                score++;
            }
        }
        return score;
    }

    public static void main(String[] args) {
        new Challenge4();
    }

}
