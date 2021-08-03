public class Anime { // anime Objects that contain basic information about animes such as name, number of episodes, genres, etc
    private String name; // the English name of the anime series
    private String japName; // the Japanese name, written in Romaji, of the anime series
    private int numOfSeasons; // number of seasons of the series
    private int[] numOfEpisodes; // number of episodes by season of a series
    private int numOfMovies; // total number of movies from the series
    private int numOfOVAs; // number of OVAs, or Original Video Animations, of the series
    private String[] malRating; // ratings per season from MAL, the largest anime database and community
    private String seriesEnjoymentRating; // my personal enjoyment rating out of 10
    private String[] mainGenre; // top three genres associated with the series
    private String[] subgenres; // less prominent genres of the series
    private boolean airing; // whether the anime is currently airing (Summer 2021)
    private boolean completedSeries; // whether this series has been completed in comparison to its manga counterpart
    private boolean filler; // whether the series contains filler, or non-canon, episodes

    public Anime (String name) {
        super();
        this.name = name;
        this.japName = null;
        this.numOfSeasons = 0;
        this.numOfEpisodes = null;
        this.numOfMovies = 0;
        this.numOfOVAs = 0;
        this.malRating = null;
        this.seriesEnjoymentRating = null;
        this.mainGenre = null;
        this.subgenres = null;
        this.airing = false;
        this.completedSeries = false;
        this.filler = false;
    }

    public String getName() {
        return name;
    }

    public String getJapName() {
        return japName;
    }

    public int getNumOfSeasons() {
        return numOfSeasons;
    }

    public int[] getNumOfEpisodes() {
        return numOfEpisodes;
    }

    public int getNumOfMovies() {
        return numOfMovies;
    }

    public int getNumOfOVAs() {
        return numOfOVAs;
    }

    public String[] getMalRating() {
        return malRating;
    }

    public String getSeriesEnjoymentRating() {
        return seriesEnjoymentRating;
    }

    public String[] getMainGenre() {
        return mainGenre;
    }

    public String[] getSubgenres() {
        return subgenres;
    }

    public boolean getAiring() {
        return airing;
    }

    public void setAiring(boolean status) {
        this.airing = status;
    }

    public boolean getCompletedSeries() {
        return completedSeries;
    }

    public void setCompletedSeries(boolean status) {
        this.completedSeries = status;
    }

    public boolean getFiller() {
        return filler;
    }

    public void setFiller(boolean status) {
        this.filler = status;
    }
}
