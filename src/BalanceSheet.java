import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BalanceSheet {
    private double totalPayment;
    private double totalExpense;
    private Map<User, Balance> balanceMap;

    @Override
    public String toString() {
        return "BalanceSheet{" + "totalPayment=" + totalPayment + ", totalExpense=" + totalExpense + ", balanceMap=" + balanceMap + '}';
    }

    public BalanceSheet() {
        this.totalPayment = 0.0;
        this.totalExpense = 0.0;
        this.balanceMap = new ConcurrentHashMap<>();
    }

    public Double getTotalPayment() {
        return totalPayment;
    }

    public void setTotalPayment(Double totalPayment) {
        this.totalPayment = totalPayment;
    }

    public Double getTotalExpense() {
        return totalExpense;
    }

    public void setTotalExpense(Double totalExpense) {
        this.totalExpense = totalExpense;
    }

    public Map<User, Balance> getbalanceMap() {
        return balanceMap;
    }

    public void setbalanceMap(Map<User, Balance> balanceMap) {
        this.balanceMap = balanceMap;
    }

    public double getOweAmountAcrossUser(User user) {
        Balance balance = balanceMap.computeIfAbsent(user, k -> new Balance());
        return balance.getOweAmount();
    }

    public void setOweAmountAcrossUser(User user, double amount) {
        Balance balance = balanceMap.computeIfAbsent(user, k -> new Balance());
        balance.setOweAmount(amount);
    }

    public double getGetBackAmountAcrossUser(User user) {
        Balance balance = balanceMap.computeIfAbsent(user, k -> new Balance());
        return balance.getGetBackAmount();
    }

    public void setGetBackAmountAcrossUser(User user, double amount) {
        Balance balance = balanceMap.computeIfAbsent(user, k -> new Balance());
        balance.setGetBackAmount(amount);
    }

    public double getTotalOweAmount() {
        double sum = 0;
        for (Map.Entry<User, Balance> val : balanceMap.entrySet()) sum += getOweAmountAcrossUser(val.getKey());
        return sum;
    }

    public double getTotalGetBackAmount() {
        double sum = 0;
        for (Map.Entry<User, Balance> val : balanceMap.entrySet()) sum += getGetBackAmountAcrossUser(val.getKey());
        return sum;
    }
}
