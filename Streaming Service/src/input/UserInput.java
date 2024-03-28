package input;

import java.util.ArrayList;

public final class UserInput {
    private CredentialsInput credentials;
    private int tokensCount;
    private int numFreePremiumMovies;
    private ArrayList<MoviesInput> purchasedMovies;
    private ArrayList<MoviesInput> watchedMovies;
    private ArrayList<MoviesInput> likedMovies;
    private ArrayList<MoviesInput> ratedMovies;
    public UserInput(final CredentialsInput credentials) {
        this.credentials = credentials;
    }

    public void setNumFreePremiumMovies(final int numFreePremiumMovies) {
        this.numFreePremiumMovies = numFreePremiumMovies;
    }

    public int getTokensCount() {
        return tokensCount;
    }

    public void setTokensCount(final int tokensCount) {
        this.tokensCount = tokensCount;
    }

    public int getNumFreePremiumMovies() {
        return numFreePremiumMovies;
    }

    public ArrayList<MoviesInput> getPurchasedMovies() {
        return purchasedMovies;
    }

    public void setPurchasedMovies(final ArrayList<MoviesInput> purchasedMovies) {
        this.purchasedMovies = purchasedMovies;
    }

    public ArrayList<MoviesInput> getWatchedMovies() {
        return watchedMovies;
    }

    public void setWatchedMovies(final ArrayList<MoviesInput> watchedMovies) {
        this.watchedMovies = watchedMovies;
    }

    public ArrayList<MoviesInput> getLikedMovies() {
        return likedMovies;
    }

    public void setLikedMovies(final ArrayList<MoviesInput> likedMovies) {
        this.likedMovies = likedMovies;
    }

    public ArrayList<MoviesInput> getRatedMovies() {
        return ratedMovies;
    }

    public void setRatedMovies(final ArrayList<MoviesInput> ratedMovies) {
        this.ratedMovies = ratedMovies;
    }

    public UserInput() {
    }

    public CredentialsInput getCredentials() {
        return credentials;
    }

    public void setCredentials(final CredentialsInput credentials) {
        this.credentials = credentials;
    }
}
