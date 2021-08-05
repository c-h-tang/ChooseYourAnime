import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Bot extends Anime { // where the GUI is created and the user interacts with the program
    private static ArrayList<Anime> list = new ArrayList<>(); // list of Anime objects

    public Bot (String name) {
        super(name);
    }

    public static void test() {
        for (int i = 0; i < list.size(); i++) {
            System.out.printf("Name: %s\n", list.get(i).getName());
            System.out.printf("Japanese Name Name: %s\n", list.get(i).getJapName());
            System.out.printf("Number of Seasons: %d\n", list.get(i).getNumOfSeasons());
            System.out.printf("Number of Episodes: %s\n", Arrays.toString(list.get(i).getNumOfEpisodes()));
            System.out.printf("Number of Movies: %d\n", list.get(i).getNumOfMovies());
            System.out.printf("Number of OVAs: %d\n", list.get(i).getNumOfOVAs());
            System.out.printf("MAL Rating: %s\n", Arrays.toString(list.get(i).getMalRating()));
            System.out.printf("Personal Enjoyment Rating: %s\n", list.get(i).getSeriesEnjoymentRating());
            System.out.printf("Main Genres: %s\n", Arrays.toString(list.get(i).getMainGenre()));
            System.out.printf("Subgenres: %s\n", Arrays.toString(list.get(i).getSubgenres()));
            System.out.printf("Airing: %s\n", list.get(i).getAiring());
            System.out.printf("Completed: %s\n", list.get(i).getCompletedSeries());
            System.out.printf("Filler: %s\n", list.get(i).getFiller());
            System.out.println("\n\n");
        }
    }

    public static void collectData() {
        try {
            BufferedReader bfr = new BufferedReader(new FileReader("data.txt"));
            String line = bfr.readLine();
            while (line != null) {
                Object[] data = line.split(", ");
                Anime a = new Anime((String) data[0]);
                a.setJapName((String) data[1]);
                a.setNumOfSeasons(Integer.parseInt((String)data[2]));
                a.setNumOfMovies(Integer.parseInt((String)data[4]));
                a.setNumOfOVAs(Integer.parseInt((String)data[5]));
                a.setSeriesEnjoymentRating((String) data[7]);
                a.setAiring(Boolean.parseBoolean((String)data[10]));
                a.setCompletedSeries(Boolean.parseBoolean((String)data[11]));
                a.setFiller(Boolean.parseBoolean((String)data[12]));

                // setting Anime a values that are arrays (int or String)
                String[] malArray = ((String) data[6]).split(" - ");
                a.setMalRating(malArray);
                String[] genreArray = ((String) data[8]).split(" - ");
                a.setMainGenre(genreArray);
                String[] subgenreArray = ((String) data[9]).split(" - ");
                a.setSubgenres(subgenreArray);
                String[] episodesArray = ((String) data[3]).split(" - ");
                a.setNumOfEpisodes(episodesArray);

                list.add(a);
                line = bfr.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        collectData();
        //test();

        // initializes frame for opening page //
        JFrame openingPage = new JFrame("Choose Your Anime");
        openingPage.setSize(600 , 550);
        openingPage.setVisible(true);
        openingPage.setLocationRelativeTo(null);
        openingPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        openingPage.setResizable(false);
        Container contents = openingPage.getContentPane();
        contents.setLayout(new BorderLayout());
        openingPage.setIconImage(new ImageIcon("Spike.jpg").getImage());

        JLabel welcome = new JLabel("Welcome to Choose Your Anime!");
        welcome.setFont(new Font("Serif", Font.PLAIN, 20));
        welcome.setForeground(Color.WHITE);

        JPanel top = new JPanel();
        top.setBackground(new Color(50, 50, 250));
        top.setPreferredSize(new Dimension(600, 220));
        top.add(welcome);

        JPanel bottom = new JPanel();
        bottom.setPreferredSize(new Dimension(600, 330));
        bottom.setBackground(Color.WHITE);

        openingPage.add(top, BorderLayout.NORTH);
        openingPage.add(bottom, BorderLayout.SOUTH);
        contents.add(top, BorderLayout.NORTH);
        contents.add(bottom, BorderLayout.SOUTH);

        openingPage.revalidate();


    }
}
