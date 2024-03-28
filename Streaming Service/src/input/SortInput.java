package input;

public final class SortInput {
    private String rating = new String();
    private String duration = new String();

    public SortInput() {
    }

    public String getRating() {
        return rating;
    }

    public void setRating(final String rating) {
        this.rating = rating;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(final String duration) {
        this.duration = duration;
    }
}
