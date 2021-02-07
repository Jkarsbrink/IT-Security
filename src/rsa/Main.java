package rsa;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Menu meny = new Menu();
        try {
            meny.menu();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
