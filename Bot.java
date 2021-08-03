import java.io.*;

public class Bot extends Anime { // where the GUI is created and the user interacts with the program

    public Bot (String name) {
        super(name);
    }

    public static void collectData() {
        try {
            BufferedReader bfr = new BufferedReader(new FileReader("data.txt"));
            String line = bfr.readLine();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        collectData();
    }
}
