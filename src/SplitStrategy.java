import java.util.List;

public interface SplitStrategy {
    public boolean validate (Double amount, List<Split> splitList);
}
