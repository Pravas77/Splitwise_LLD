public class Transaction {
    private User from;
    private User to;
    private Double transactionAmount;

    public Transaction(User from, User to, Double transactionAmount) {
        this.from = from;
        this.to = to;
        this.transactionAmount = transactionAmount;
    }

    public User getFrom() {
        return from;
    }

    public User getTo() {
        return to;
    }

    public Double getTransactionAmount() {
        return transactionAmount;
    }

    @Override
    public String toString() {
        return "Transaction{" + "from=" + from + ", to=" + to + ", transactionAmount=" + transactionAmount + '}';
    }
}
