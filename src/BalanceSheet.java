import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BalanceSheet {
    private Double totalPayment;
    private Double totalExpense;
    private Map<User,Balance> userVsBalance;

    @Override
    public String toString() {
        return "BalanceSheet{" +
                "totalPayment=" + totalPayment +
                ", totalExpense=" + totalExpense +
                ", userVsBalance=" + userVsBalance +
                '}';
    }

    public BalanceSheet() {
        this.totalPayment = 0.0;
        this.totalExpense = 0.0;
        this.userVsBalance = new ConcurrentHashMap<>();
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

    public Map<User, Balance> getUserVsBalance() {
        return userVsBalance;
    }

    public void setUserVsBalance(Map<User, Balance> userVsBalance) {
        this.userVsBalance = userVsBalance;
    }
}
