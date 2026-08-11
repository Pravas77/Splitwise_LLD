import java.util.List;

public class Expense {
    private User paidBy;
    private Double amount;
    private List<Split> splits;

    public Expense(User paidBy, Double amount, List<Split> splits) {
        this.paidBy = paidBy;
        this.amount = amount;
        this.splits = splits;
    }

    public User getPaidBy() {
        return paidBy;
    }

    public void setPaidBy(User paidBy) {
        this.paidBy = paidBy;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public List<Split> getSplits() {
        return splits;
    }

    public void setSplits(List<Split> splits) {
        this.splits = splits;
    }
}
