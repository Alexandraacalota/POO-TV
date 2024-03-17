package input;

public class FiltersInput {
    private SortInput sort;
    private ContainsInput contains;

    /** Returns the sort properties of the filter action */
    public SortInput getSort() {
        return sort;
    }

    /** Returns the contains properties of the filter action */
    public ContainsInput getContains() {
        return contains;
    }

    /** Sets the sort properties of the filter action */
    public void setSort(final SortInput sort) {
        this.sort = sort;
    }

    /** Sets the contains properties of the filter action */
    public void setContains(final ContainsInput contains) {
        this.contains = contains;
    }
}
