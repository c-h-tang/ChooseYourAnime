import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Bot extends Anime { // where the GUI is created and the user interacts with the program
    private static ArrayList<Anime> list = new ArrayList<>(); // list of Anime objects

    public Bot (String name) {
        super(name);
    }

    public static String[] stringArrayConversion(Object o) {
        Object[] arr = ((String) o).split(" | ");
        String[] result = new String[arr.length];
        System.arraycopy(arr, 0, result, 0, arr.length);
        return result;
    }

    public static void collectData() {
        try {
            BufferedReader bfr = new BufferedReader(new FileReader("data.txt"));
            String line = bfr.readLine();
            while (line != null) {
                Object[] data = line.split(", ");
                Anime a = new Anime((String) data[0]);
                a.setJapName((String) data[1]);
                a.setNumOfSeasons(Integer.valueOf((String)data[2]));
                a.setNumOfMovies(Integer.valueOf((String)data[4]));
                a.setNumOfOVAs(Integer.valueOf((String)data[5]));
                a.setSeriesEnjoymentRating((String) data[7]);
                a.setAiring(Boolean.parseBoolean((String)data[10]));
                a.setCompletedSeries(Boolean.parseBoolean((String)data[11]));
                a.setFiller(Boolean.parseBoolean((String)data[12]));

                // setting Anime a values that are arrays (int or String)
                String[] malArray = stringArrayConversion(data[6]);
                a.setMalRating(malArray);
                String[] genreArray = stringArrayConversion(data[8]);
                a.setMainGenre(genreArray);
                String[] subgenreArray = stringArrayConversion(data[9]);
                a.setSubgenres(subgenreArray);
                String[] episodesArray = stringArrayConversion(data[3]);
                a.setNumOfEpisodes(episodesArray);


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
        for (int i = 0; i < list.size(); i++) {
            System.out.printf("Name: %s\n", list.get(i).getName());
            System.out.printf("Jap Name: %s\n", list.get(i).getJapName());
            System.out.printf("Number of Seasons: %d\n", list.get(i).getNumOfSeasons());
            System.out.printf("Number of Episodes: %s\n", Arrays.toString(list.get(i).getNumOfEpisodes()));

        }
    }
}
