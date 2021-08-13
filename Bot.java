import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Bot extends Anime { // where the GUI is created and the user interacts with the program
    private static ArrayList<Anime> list = new ArrayList<>(); // list of Anime objects

    public Bot (String name) {
        super(name);
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

            BufferedReader b = new BufferedReader(new FileReader("fillerFile.txt"));
            String lineRead = b.readLine();
            int i = 0;
            while(lineRead != null) {
                lineRead = b.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String changeToJPEGName(String name) { // removes spaces from name of Anime object to format it for jpegs
        name = name.replaceAll("\\s", "");
        name = name.replaceAll(":", "");
        return name;
    }

    public static String showAllGenres(Anime a) {
        String allGenres = "Genres: ";

        String[] both = Arrays.copyOf(a.getMainGenre(), a.getMainGenre().length + a.getSubgenres().length);
        System.arraycopy(a.getSubgenres(), 0, both, a.getMainGenre().length, a.getSubgenres().length);

        for (int i = 0; i < both.length; i++) {
            if (both[i] != "") {
                allGenres = allGenres + both[i];
            }
            if (i != both.length - 1 && !both[i + 1].equals("")) {
                allGenres = allGenres + ", ";
            }
        }
        return allGenres;
    }

    public static void randomize(JFrame openingPage, String searchedWord) {
        ArrayList<Integer> matchingIndexes = new ArrayList<Integer>();
        Random rand = new Random();
        int randomInteger = rand.nextInt(list.size());
        matchingIndexes.add(randomInteger);
        openingPage.setVisible(false);
        System.out.println("It worked - RANDOM");
        showAnimePanel(matchingIndexes, searchedWord, openingPage, true, "RANDOM");
    }

    public static void alphabetize(JFrame openingPage, String searchedWord) {
        String[] names = new String[list.size()];

        for (int i = 0; i < list.size(); i++) {
            names[i] = list.get(i).getName();
        }
        Arrays.sort(names);
        System.out.println(Arrays.toString(names));

        ArrayList<Integer> topTenInt = new ArrayList<>();
        for (String s: names) {
            int x = 0;
            for (Anime anime: list) {
                if (anime.getName().equals(s)) {
                    topTenInt.add(x);
                } else {
                    x++;
                }
            }
        }
        showAnimePanel(topTenInt, searchedWord, openingPage, false, "ALPHABETIZE");
    }

    public static void findTopTenEnjoyment(JFrame openingPage, String searchedWord) { // find the top 10 most enjoyed anime in the database
        int i = 0;
        double[] ar = new double[list.size()];
        for(Anime a: list) {
            ar[i++] = Double.parseDouble(a.getSeriesEnjoymentRating());
        }
        Arrays.sort(ar);

        double[] topTen = new double[10];
        for (int j = 0; j < 10; j++) {
            topTen[j] = ar[ar.length - 1 - j];
        }

        ArrayList<Anime> topTenList = new ArrayList<>();
        for (int j = 0; j < 10; j++) {
            for (Anime an: list) {
                if (!topTenList.contains(an) && Double.parseDouble(an.getSeriesEnjoymentRating()) == topTen[j]) {
                    topTenList.add(an);
                }
            }
        }

        ArrayList<Integer> topTenInt = new ArrayList<>();
        for (Anime an: topTenList) {
            int x = 0;
            for (Anime anime: list) {
                if (anime == an) {
                    topTenInt.add(x);
                } else {
                    x++;
                }
            }
        }
        showAnimePanel(topTenInt, searchedWord, openingPage, false, "ENJOYMENT");
    }

    public static void findTopTenMAL(JFrame openingPage, String searchedWord) { // finds top 10 anime based on series MAL rating
        double[] averageMALRatings = new double[list.size()];
        int i = 0;

        for (Anime a: list) {
            if (a.getMalRating().length == 1) {
                averageMALRatings[i++] = Double.parseDouble(a.getMalRating()[0]);
                a.setAverageMAL(Double.parseDouble(a.getMalRating()[0]));
            } else {
                double total = 0;
                int denom = 0;
                for(int j = 0; j < a.getMalRating().length; j++) {
                    if (!a.getMalRating()[j].contains(" ")) {
                        total += Double.parseDouble(a.getMalRating()[j]);
                        denom++;
                    } else {
                        String[] s = a.getMalRating()[j].split(" ");
                        for (int x = 0; x < 2; x++) {
                            total += Double.parseDouble(s[x]);
                            denom++;
                        }
                    }
                }
                double average = total / denom;
                double roundedAverage = Double.parseDouble(String.format("%.3f", average));
                averageMALRatings[i++] = roundedAverage;
                a.setAverageMAL(roundedAverage);
            }
        }

        Arrays.sort(averageMALRatings);

        double[] topTen = new double[10];

        for (int k = 0; k < 10; k++) {
            topTen[k] = averageMALRatings[averageMALRatings.length - 1 - k];
        }
        ArrayList<Anime> topTenMAL = new ArrayList<>();
        for (double av: topTen) {
            for(Anime anime: list) {
                if (!topTenMAL.contains(anime) && anime.getAverageMAL() == av) {
                    topTenMAL.add(anime);
                    break;
                }
            }
        }

        ArrayList<Integer> topTenInt = new ArrayList<>();
        for (Anime an: topTenMAL) {
            int x = 0;
            for (Anime anime: list) {
                if (anime == an) {
                    topTenInt.add(x);
                } else {
                    x++;
                }
            }
        }
        showAnimePanel(topTenInt, searchedWord, openingPage, false, "MAL");
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
        openingPage.setIconImage(new ImageIcon("Images/Spike.jpg").getImage());

        // title
        JLabel welcome = new JLabel("Welcome to Choose Your Anime!"); // welcome text
        welcome.setFont(new Font("Serif", Font.PLAIN, 20));
        welcome.setForeground(Color.WHITE);

        // text field that allows users to search for an anime title
        JTextField searchInput = new JTextField("", 20);  // input if user chooses to search for a specific anime
        JButton search = new JButton("Search");    // sign up button
        searchInput.setColumns(28);

        // creates upper layer of opening window
        JPanel top = new JPanel();
        top.setBackground(new Color(0, 78, 250));
        top.setPreferredSize(new Dimension(600, 220));
        top.add(welcome);

        // searching text field initiations
        JPanel searchFeature = new JPanel();
        searchFeature.setBackground(new Color(0, 78, 250));
        top.setPreferredSize(new Dimension(600, 70));
        searchFeature.add(searchInput, BorderLayout.CENTER);
        searchFeature.add(search, BorderLayout.EAST);

        //buttons on opening page
        JButton randomize = new JButton("Randomize");
        randomize.setPreferredSize(new Dimension(170, 100));
        JButton customSearch = new JButton("Genre Search");
        customSearch.setPreferredSize(new Dimension(170, 100));
        JButton seeList = new JButton("See All");
        seeList.setPreferredSize(new Dimension(170, 100));
        JButton topTenMAL = new JButton("MAL's Top 10");
        topTenMAL.setPreferredSize(new Dimension(170, 100));
        JButton topTenEnjoyment = new JButton("My Top 10");
        topTenEnjoyment.setPreferredSize(new Dimension(170, 100));
        JButton alphabetized = new JButton("Alphabetized");
        alphabetized.setPreferredSize(new Dimension(170, 100));

        // bottom panel that contains buttons
        JPanel bottom = new JPanel(new GridLayout(2, 1));
        bottom.setPreferredSize(new Dimension(600, 330));
        bottom.setBackground(Color.WHITE);
        JPanel botTop = new JPanel();
        JPanel botBot = new JPanel();
        botTop.add(randomize);
        botTop.add(customSearch);
        botTop.add(seeList);
        botBot.add(topTenMAL);
        botBot.add(topTenEnjoyment);
        botBot.add(alphabetized);
        bottom.add(botTop);
        bottom.add(botBot);

        contents.add(top, BorderLayout.NORTH);
        contents.add(searchFeature, BorderLayout.CENTER);
        contents.add(bottom, BorderLayout.SOUTH);

        openingPage.revalidate();

        search.addActionListener(e -> { // enacts searching algorithm
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
                showAnimePanel(matchingIndexes, searchInput.getText(), openingPage, false, "SEARCH");
                searchInput.setText("");
            }
        });

        randomize.addActionListener(e -> { // enacts looking at all anime algorithm
            System.out.println("It worked - RANDOMIZE");
            randomize(openingPage, searchInput.getText());
        });

        alphabetized.addActionListener(e -> { // alphabetizes anime by English name
            System.out.println("It worked - ALPHABETIZED");
            alphabetize(openingPage, searchInput.getText());
        });

        topTenEnjoyment.addActionListener(e -> { // enacts top 10 enjoyed anime algorithm
            System.out.println("It worked - ENJOYMENT");
            findTopTenEnjoyment(openingPage, searchInput.getText());
        });

        topTenMAL.addActionListener(e -> { // enacts top 10 enjoyed anime algorithm
            System.out.println("It worked - MAL");
            findTopTenMAL(openingPage, searchInput.getText());
        });

        seeList.addActionListener(e -> { // enacts looking at all anime algorithm
            ArrayList<Integer> matchingIndexes = new ArrayList<Integer>();
            for (int i = 0; i < list.size(); i++) {
                matchingIndexes.add(i);
            }
            openingPage.setVisible(false);
            System.out.println("It worked - ALL");
            showAnimePanel(matchingIndexes, searchInput.getText(), openingPage, false, "ALL");
        });
    }

    public static void showAnimePanel(ArrayList<Integer> matchingIndexes, String searchedWord, JFrame openingPage, boolean random, String type) { // shows anime info in grid-like fashion
        String titleBar = "";
        if (type.equals("RANDOM")) {
            titleBar = "Random Anime Found: " + list.get(matchingIndexes.get(0)).getName();
        } else if (type.equals("ALL")) {
            titleBar = "All Anime in Database";
        } else if (type.equals("SEARCH")) {
            titleBar = "Search Results for '" + searchedWord + "'";
        } else if (type.equals("ENJOYMENT")) {
            titleBar = "Top 10 Anime by Enjoyment Rating";
        } else if (type.equals("ALPHABETIZE")) {
            titleBar = "Alphabetized List of Anime";
        } else if (type.equals("MAL")) {
            titleBar = "Top 10 Anime by MyAnimeList Rating";
        }
        JFrame searchResults = new JFrame(titleBar);
        searchResults.setSize(600, 550);
        searchResults.setVisible(true);
        searchResults.setLocationRelativeTo(null);
        searchResults.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        searchResults.setResizable(false);
        Container contents2 = searchResults.getContentPane();
        contents2.setLayout(new BorderLayout());
        searchResults.setIconImage(new ImageIcon("Images/Spike.jpg").getImage());

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
                imageName = changeToJPEGName(imageName);
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
            synopsis.setPreferredSize(new Dimension(250, 95));
            synopsis.setLineWrap(true);
            synopsis.setWrapStyleWord(true);
            synopsis.setEditable(false);
            synopsis.setFont(new Font("Serif", Font.BOLD, 14));

            // text that shows genres
            String allGenres = "";
            allGenres = showAllGenres(list.get(num));
            JTextArea genres = new JTextArea(allGenres);
            genres.setPreferredSize(new Dimension(250, 25));
            genres.setLineWrap(true);
            genres.setWrapStyleWord(true);
            genres.setEditable(false);
            genres.setFont(new Font("Serif", Font.BOLD, 14));

            // text that shows MAL and PE rating
            JTextArea ratings = new JTextArea("\nMAL Rating: " + Arrays.toString(list.get(num).getMalRating()) + "\n" + "Personal Enjoyment Rating: " + list.get(num).getSeriesEnjoymentRating());
            ratings.setPreferredSize(new Dimension(250, 40));
            ratings.setLineWrap(true);
            ratings.setWrapStyleWord(true);
            ratings.setEditable(false);
            ratings.setFont(new Font("Serif", Font.BOLD, 14));

            // text that shows filler episodes (if applicable)
            JTextArea filler = new JTextArea("\nFiller: N/A");
            filler.setPreferredSize(new Dimension(250, 25));
            filler.setLineWrap(true);
            filler.setWrapStyleWord(true);
            filler.setEditable(false);
            filler.setFont(new Font("Serif", Font.BOLD, 14));

            // buttons to send user to webpage
            JPanel buttonPanel = new JPanel();
            buttonPanel.setBackground(Color.WHITE);
            JButton mal = new JButton("MAL");
            JButton wcostream = new JButton("Watch Subbed");
            mal.setPreferredSize(new Dimension(120, 25));
            wcostream.setPreferredSize(new Dimension(120, 25));
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
            rightPanel.add(genres);
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
        JButton randomizeButton = new JButton("Randomize");

        backButton.addActionListener(e2 -> {
            searchResults.dispose();
            openingPage.setVisible(true);
        });

        randomizeButton.addActionListener(e2 -> {
            searchResults.dispose();
            randomize(openingPage, searchedWord);
        });

        middle.removeAll();
        middle.add(animePanel);

        // creates the scroll bar on the right of the page
        JScrollPane scroll = new JScrollPane(middle, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);    // scroll bar
        BorderLayout layout = (BorderLayout) contents2.getLayout();  // middle portion
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
        if (random) {
            upper.add(randomizeButton);
        }
        contents2.add(upper, BorderLayout.NORTH);
        contents2.revalidate();
        searchResults.revalidate();
    }
}