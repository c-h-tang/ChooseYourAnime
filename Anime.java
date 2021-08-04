public class Anime { // anime Objects that contain basic information about animes such as name, number of episodes, genres, etc
    private String name; // the English name of the anime series
    private String japName; // the Japanese name, written in Romaji, of the anime series
    private int numOfSeasons; // number of seasons of the series
    private String[] numOfEpisodes; // number of episodes by season of a series
    private int numOfMovies; // total number of movies from the series
    private int numOfOVAs; // number of OVAs, or Original Video Animations, of the series
    private String[] malRating; // ratings per season from MAL, the largest anime database and community
    private String seriesEnjoymentRating; // my personal enjoyment rating out of 10
    private String[] mainGenre; // top three genres associated with the series
    private String[] subgenres; // less prominent genres of the series
    private boolean airing; // whether the anime is currently airing (Summer 2021)
    private boolean completedSeries; // whether this series has been completed in comparison to its manga counterpart
    private boolean filler; // whether the series contains filler, or non-canon, episodes
    // private int whenToDrop; // denotes at what episode the anime should be dropped if the user doesn't like it so far (based on personal experience)

    public Anime (String name) { // creates an Anime Object with just a name, other data is left blank and will be filled after Object creation
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
        // this.whenToDrop = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJapName() {
        return japName;
    }

    public void setJapName(String japName) {
        this.japName = japName;
    }

    public int getNumOfSeasons() {
        return numOfSeasons;
    }

    public void setNumOfSeasons(int numOfSeasons) {
        this.numOfSeasons = numOfSeasons;
    }

    public String[] getNumOfEpisodes() {
        return numOfEpisodes;
    }

    public void setNumOfEpisodes(String[] numOfEpisodes) {
        this.numOfEpisodes = numOfEpisodes;
    }

    public int getNumOfMovies() {
        return numOfMovies;
    }

    public void setNumOfMovies(int numOfMovies) {
        this.numOfMovies = numOfMovies;
    }

    public int getNumOfOVAs() {
        return numOfOVAs;
    }

    public void setNumOfOVAs(int numOfOVAs) {
        this.numOfOVAs = numOfOVAs;
    }

    public String[] getMalRating() {
        return malRating;
    }

    public void setMalRating(String[] malRating) {
        this.malRating = malRating;
    }

    public String getSeriesEnjoymentRating() {
        return seriesEnjoymentRating;
    }

    public void setSeriesEnjoymentRating(String seriesEnjoymentRating) { this.seriesEnjoymentRating = seriesEnjoymentRating; }

    public String[] getMainGenre() {
        return mainGenre;
    }

    public void setMainGenre(String[] mainGenre) {
        this.mainGenre = mainGenre;
    }

    public String[] getSubgenres() {
        return subgenres;
    }

    public void setSubgenres(String[] subgenres) {
        this.subgenres = subgenres;
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
