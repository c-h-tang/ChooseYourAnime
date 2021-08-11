import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;

public class Bot extends Anime { // where the GUI is created and the user interacts with the program
    private static ArrayList<Anime> list = new ArrayList<>(); // list of Anime objects

    public Bot (String name) {
        super(name);
    }

    public static String toImageFormat(String imageName) {
        return "Images/" + imageName;
    }

    public static void test() { // lists out all the information collected in ArrayList<Anime> for each line in data.txt
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
            System.out.printf("MAL Link: %s\n", list.get(i).getMALURL());
            System.out.printf("Gogoanime Link: %s\n", list.get(i).getGogoanimeURL());
            System.out.println("\n\n");
        }
    }

    public static void collectData() { // creates an Anime object with all the data in each line and adds it to ArrayList<Anime> list
        try {
            BufferedReader bfr = new BufferedReader(new FileReader("data.txt"));
            String line = bfr.readLine();
            while (line != null) {
                Object[] data = line.split(", ");
                Anime a = new Anime((String) data[0]);
                a.setJapName((String) data[1]);
                a.setNumOfSeasons(Integer.parseInt((String) data[2]));
                a.setNumOfMovies(Integer.parseInt((String) data[4]));
                a.setNumOfOVAs(Integer.parseInt((String) data[5]));
                a.setSeriesEnjoymentRating((String) data[7]);
                a.setAiring(Boolean.parseBoolean((String) data[10]));
                a.setCompletedSeries(Boolean.parseBoolean((String) data[11]));
                a.setFiller(Boolean.parseBoolean((String) data[12]));
                a.setMALURL((String) data[13]);
                a.setGogoanimeURL((String) data[14]);

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

    public static String removeSpaces(String name) {
        name = name.replaceAll("\\s", "");
        return name;
    }


    public static void main(String[] args) {
        collectData();
        // test();

        // initializes frame for opening page //
        JFrame openingPage = new JFrame("Choose Your Anime");
        openingPage.setSize(600, 550);
        openingPage.setVisible(true);
        openingPage.setLocationRelativeTo(null);
        openingPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        openingPage.setResizable(false);
        Container contents = openingPage.getContentPane();
        contents.setLayout(new BorderLayout());
        openingPage.setIconImage(new ImageIcon(toImageFormat("Spike.jpg")).getImage());


        JLabel welcome = new JLabel("Welcome to Choose Your Anime!"); // welcome text
        welcome.setFont(new Font("Serif", Font.PLAIN, 20));
        welcome.setForeground(Color.WHITE);

        JTextField searchInput = new JTextField("", 20);  // input if user chooses to search for a specific anime
        JButton search = new JButton("Search");    // sign up button
        searchInput.setColumns(28);

        JPanel top = new JPanel();
        top.setBackground(new Color(0, 78, 250));
        top.setPreferredSize(new Dimension(600, 220));
        top.add(welcome);

        JPanel searchFeature = new JPanel();
        searchFeature.setBackground(new Color(0, 78, 250));
        top.setPreferredSize(new Dimension(600, 70));
        searchFeature.add(searchInput, BorderLayout.CENTER);
        searchFeature.add(search, BorderLayout.EAST);

        JButton randomize = new JButton("Randomize");
        randomize.setPreferredSize(new Dimension(170, 100));
        JButton customSearch = new JButton("Genre Search");
        customSearch.setPreferredSize(new Dimension(170, 100));
        JButton seeList = new JButton("See All");
        seeList.setPreferredSize(new Dimension(170, 100));

        JPanel bottom = new JPanel();
        bottom.setPreferredSize(new Dimension(600, 330));
        bottom.setBackground(Color.WHITE);
        bottom.add(randomize, BorderLayout.CENTER);
        bottom.add(customSearch, BorderLayout.CENTER);
        bottom.add(seeList, BorderLayout.CENTER);

        contents.add(top, BorderLayout.NORTH);
        contents.add(searchFeature, BorderLayout.CENTER);
        contents.add(bottom, BorderLayout.SOUTH);

        openingPage.revalidate();

        search.addActionListener(e -> {
            boolean inDatabase = false;
            ArrayList<Integer> matchingIndexes = new ArrayList<Integer>();
            for (Anime a : list) {
                if (a.getName().toLowerCase().contains(String.valueOf(searchInput.getText().toLowerCase())) || a.getJapName().toLowerCase().contains(String.valueOf(searchInput.getText().toLowerCase()))) {
                    matchingIndexes.add(list.indexOf(a));
                    System.out.println("Added " + a.getName());
                    inDatabase = true;
                }
            }
            if (!inDatabase) {
                JOptionPane.showMessageDialog(null, "'" + searchInput.getText() + "'" + " not in the database!  Please try again.",
                        "Database Error", JOptionPane.ERROR_MESSAGE);
                searchInput.setText("");
            } else {
                openingPage.setVisible(false);
                System.out.println("It worked - SEARCH");
                showAnimePanel(matchingIndexes, searchInput.getText(), openingPage);
                searchInput.setText("");
            }
        });

        seeList.addActionListener(e -> {
            ArrayList<Integer> matchingIndexes = new ArrayList<Integer>();
            for (int i = 0; i < list.size(); i++) {
                matchingIndexes.add(i);
            }
            openingPage.setVisible(false);
            System.out.println("It worked - ALL");
            showAnimePanel(matchingIndexes, searchInput.getText(), openingPage);
        });
    }

    public static void showAnimePanel(ArrayList<Integer> matchingIndexes, String searchedWord, JFrame openingPage) {
        JFrame searchResults = new JFrame("Search Results for " + "'" + searchedWord + "'");
        searchResults.setSize(600, 550);
        searchResults.setVisible(true);
        searchResults.setLocationRelativeTo(null);
        searchResults.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        searchResults.setResizable(false);
        Container contents2 = searchResults.getContentPane();
        contents2.setLayout(new BorderLayout());
        searchResults.setIconImage(new ImageIcon(toImageFormat("Spike.jpg")).getImage());

        GridLayout animeGrid = new GridLayout(0, 1,
                0, 5); // grid for holding comments

        JPanel animePanel = new JPanel(animeGrid);  // panel for holding grid

        JPanel middle = new JPanel();   // middle portion of frame

        for(int num: matchingIndexes) {
            JPanel overall = new JPanel(); // entire layout of each anime panel
            overall.setLayout(new BoxLayout(overall, BoxLayout.Y_AXIS));

            JPanel first = new JPanel();
            first.setBackground(Color.white);
            first.setPreferredSize((new Dimension(550, 50)));
            first.setBorder(BorderFactory.createMatteBorder(1, 1, 0, 1, Color.BLACK));

            JPanel second = new JPanel(new GridLayout(1, 2));
            second.setPreferredSize(new Dimension(550, 350));
            second.setBackground(Color.WHITE);
            second.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, Color.BLACK));


            JPanel leftPanel = new JPanel(); // panel for left side contents
            leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
            leftPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
            leftPanel.setPreferredSize(new Dimension(300, 350));
            leftPanel.setBackground(Color.WHITE);

            JPanel rightPanel = new JPanel(); // panel for right side contents
            rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
            rightPanel.setPreferredSize(new Dimension(250, 350));
            rightPanel.setBackground(Color.WHITE);

            // left side
            JLabel animeName = new JLabel(list.get(num).getName()); // name of anime
            animeName.setFont(new Font("Serif", Font.PLAIN, 25));
            animeName.setForeground(Color.BLACK);
            first.add(animeName, BorderLayout.NORTH);
            overall.add(first);

            String imageName = list.get(num).getName();
            if (imageName.contains(" ")) {
                imageName = removeSpaces(imageName);
            }

            // left side
            ImageIcon icon = new ImageIcon("Images/" + imageName + ".jpg");
            JLabel label = new JLabel();
            label.setIcon(icon);
            label.setFont(new Font("Serif", Font.BOLD, 14));
            label.setText(String.format("<html>  Japanese Name: %s<br>  Number of Seasons: %s<br>  Number of Episodes: %s<br>  Number of Movies: %d<br>  Number of OVAs: %d</html>",
                    list.get(num).getJapName(), list.get(num).getNumOfSeasons(), Arrays.toString(list.get(num).getNumOfEpisodes()), list.get(num).getNumOfMovies(), list.get(num).getNumOfOVAs()));
            label.setHorizontalTextPosition(JLabel.CENTER);
            label.setVerticalTextPosition(JLabel.BOTTOM);
            label.setVerticalAlignment(JLabel.TOP);
            label.setHorizontalAlignment(JLabel.LEFT);
            leftPanel.add(label);

            // right side
            // text that shows summary/synopsis
            String t = "Synopsis: Hello, this is a fake synopsis that will contain real and accurate text depending on the anime shown. ccccccccccccccccccccccc ssssssssssssssssssssssssssssssss aa dddddd rrrrr tt t yyyyy";
            JTextArea synopsis = new JTextArea(t);
            synopsis.setPreferredSize(new Dimension(250, 120));
            synopsis.setLineWrap(true);
            synopsis.setWrapStyleWord(true);
            synopsis.setEditable(false);
            synopsis.setFont(new Font("Serif", Font.BOLD, 14));

            // text that shows MAL and PE rating
            JTextArea ratings = new JTextArea("\nMAL Rating: " + Arrays.toString(list.get(num).getMalRating()) + "\n" + "Personal Enjoyment Rating: " + list.get(num).getSeriesEnjoymentRating());
            ratings.setPreferredSize(new Dimension(250, 40));
            ratings.setLineWrap(true);
            ratings.setWrapStyleWord(true);
            ratings.setEditable(false);
            ratings.setFont(new Font("Serif", Font.BOLD, 14));

            // text that shows filler episodes (if applicable)
            JTextArea filler = new JTextArea("\nFiller: N/A");
            filler.setPreferredSize(new Dimension(250, 40));
            filler.setLineWrap(true);
            filler.setWrapStyleWord(true);
            filler.setEditable(false);
            filler.setFont(new Font("Serif", Font.BOLD, 14));

            // buttons to send user to webpage
            JPanel buttonPanel = new JPanel();
            buttonPanel.setBackground(Color.WHITE);
            JButton mal = new JButton("MAL");
            JButton wcostream = new JButton("Watch Subbed");
            mal.setPreferredSize(new Dimension(120, 30));
            wcostream.setPreferredSize(new Dimension(120, 30));
            buttonPanel.add(mal);
            buttonPanel.add(wcostream);

            mal.addActionListener(e2 -> { // sends user to MAL website when pressed
                try {
                    URI uri = new URI(list.get(num).getMALURL());
                    java.awt.Desktop.getDesktop().browse(uri);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            });

            wcostream.addActionListener(e2 -> { // sends user to watching portal when pressed
                try {
                    URI uri = new URI(list.get(num).getGogoanimeURL());
                    java.awt.Desktop.getDesktop().browse(uri);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            });

            rightPanel.add(synopsis);
            rightPanel.add(ratings);
            rightPanel.add(filler);
            rightPanel.add(buttonPanel);

            second.add(leftPanel);
            second.add(rightPanel);
            overall.add(second);
            animePanel.add(overall);
        }

        // button to return to home
        JButton backButton = new JButton("Back to Home");

        backButton.addActionListener(e2 -> {
            searchResults.dispose();
            openingPage.setVisible(true);
        });

        middle.removeAll();
        middle.add(animePanel);

        // creates the scroll bar on the right of the page
        JScrollPane scroll = new JScrollPane(middle, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);    // scroll bar for comments
        BorderLayout layout = (BorderLayout) contents2.getLayout();  // middle portion with comments
        if (Arrays.asList(contents2.getComponents()).contains(
                layout.getLayoutComponent(BorderLayout.CENTER))) {
            contents2.remove(layout.getLayoutComponent(BorderLayout.CENTER));
        }
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                scroll.getViewport().setViewPosition( new Point(0, 0) );
            }
        });
        contents2.add(scroll, BorderLayout.CENTER);

        JPanel upper = new JPanel();  // top portion of frame
        upper.add(backButton, BorderLayout.WEST);
        contents2.add(upper, BorderLayout.NORTH);
        contents2.revalidate();
        searchResults.revalidate();
    }
}