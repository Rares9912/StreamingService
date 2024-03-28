package input;

public final class FiltersInput {
    private SortInput sort = new SortInput();
    private ContainsInput contains = new ContainsInput();

    public FiltersInput() {
    }

    public SortInput getSort() {
        return sort;
    }

    public void setSort(final SortInput sort) {
        this.sort = sort;
    }

    public ContainsInput getContains() {
        return contains;
    }

    public void setContains(final ContainsInput contains) {
        this.contains = contains;
    }
}
