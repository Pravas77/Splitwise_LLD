import java.util.List;

public class PercentageSplitStrategy implements SplitStrategy{
    @Override
    public boolean validate(Double amount, List<Split> splitList) {
        return false;
    }
}
