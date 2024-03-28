package input;

public final class ActionsInput {
    private String type;
    private String page;
    private String feature;
    private CredentialsInput credentials;
    private String startsWith;
    private int count;
    private String movie;
    private int rate;
    private FiltersInput filters;
    private boolean isError;

    public ActionsInput(final String type, final String page, final String feature,
                        final CredentialsInput credentials, final String startsWith,
                        final int count, final String movie, final int rate,
                        final FiltersInput filters) {
        this.type = type;
        this.page = page;
        this.feature = feature;
        this.credentials = credentials;
        this.startsWith = startsWith;
        this.count = count;
        this.movie = movie;
        this.rate = rate;
        this.filters = filters;
    }

    public ActionsInput() {
    }

    public boolean isError() {
        return isError;
    }

    public void setError(final boolean error) {
        isError = error;
    }

    public FiltersInput getFilters() {
        return filters;
    }

    public void setFilters(final FiltersInput filters) {
        this.filters = filters;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(final int rate) {
        this.rate = rate;
    }

    public String getMovie() {
        return movie;
    }

    public void setMovie(final String movie) {
        this.movie = movie;
    }

    public String getStartsWith() {
        return startsWith;
    }

    public void setStartsWith(final String startsWith) {
        this.startsWith = startsWith;
    }

    public int getCount() {
        return count;
    }

    public void setCount(final int count) {
        this.count = count;
    }

    public String getType() {
        return type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public String getPage() {
        return page;
    }

    public void setPage(final String page) {
        this.page = page;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(final String feature) {
        this.feature = feature;
    }

    public CredentialsInput getCredentials() {
        return credentials;
    }

    public void setCredentials(final CredentialsInput credentials) {
        this.credentials = credentials;
    }
}
