import java.io.*;
import java.util.ArrayList;

public class Bot extends Anime { // where the GUI is created and the user interacts with the program
    private static ArrayList<Anime> list = new ArrayList<>(); // list of Anime objects

    public Bot (String name) {
        super(name);
    }

    public void arrayConversion(Object o) {

    }

    public static void collectData() {
        try {
            BufferedReader bfr = new BufferedReader(new FileReader("data.txt"));
            String line = bfr.readLine();
            while (line != null) {
                Object[] data = line.split(", ");
                Anime a = new Anime((String)data[0]);
                a.setJapName((String)data[1]);
                list.add(a);
                line = bfr.readLine();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        collectData();
        System.out.println(list.get(0).getJapName());
        System.out.println("Success");
    }
}
