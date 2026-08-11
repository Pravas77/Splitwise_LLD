import java.util.List;

public class UnequalSplitStrategy implements SplitStrategy{
    @Override
    public boolean validate(Double amount, List<Split> splitList) {
        return false;
    }
}
