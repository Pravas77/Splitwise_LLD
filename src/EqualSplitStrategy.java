import java.util.List;

public class EqualSplitStrategy implements SplitStrategy {
    @Override
    public boolean validate(Double amount, List<Split> splitList) {
        int persons = splitList.size();
        Double cost = amount / persons;
        for (Split split : splitList) if (Math.abs(cost - split.getOweAmount()) >= 0.01) return false;
        return true;
    }
}
