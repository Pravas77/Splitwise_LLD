import java.util.*;

public class Group {
    private int id;
    private Map<User, BalanceSheet> balanceSheetMap;

    public Group(int id, Map<User, BalanceSheet> balanceSheetMap) {
        this.id = id;
        this.balanceSheetMap = balanceSheetMap;
    }


    public BalanceSheet getBalanceSheet(User user) {
        return balanceSheetMap.get(user);
    }


    public void createExpense(User user, double amount, List<Split> splitList) {

        BalanceSheet userBalanceSheet = balanceSheetMap.get(user);
        Double totalPayment = userBalanceSheet.getTotalPayment();
        userBalanceSheet.setTotalPayment(totalPayment + amount);

        for (Split split : splitList) {

            User oweUser = split.getOweUser();
            Double oweAmount = split.getOweAmount();
            BalanceSheet oweUseBalanceSheet = balanceSheetMap.get(oweUser);

            if (oweUser.equals(user)) {
                Double totalExpense = userBalanceSheet.getTotalExpense();
                userBalanceSheet.setTotalExpense(totalExpense + oweAmount);
                continue;
            }

            double currentGetBackAmount = userBalanceSheet.getGetBackAmountAcrossUser(oweUser);
            userBalanceSheet.setGetBackAmountAcrossUser(oweUser, currentGetBackAmount + oweAmount);

            double currentOweAmount = oweUseBalanceSheet.getOweAmountAcrossUser(user);
            oweUseBalanceSheet.setOweAmountAcrossUser(user, currentOweAmount + oweAmount);
        }
    }


    public List<Transaction> simplifyDebt() {

        PriorityQueue<Settlement> positiveQueue = new PriorityQueue<>((a, b) -> Double.compare(b.getAmount(), a.getAmount()));
        PriorityQueue<Settlement> negativeQueue = new PriorityQueue<>((a, b) -> Double.compare(a.getAmount(), b.getAmount()));

        for (Map.Entry<User, BalanceSheet> val : balanceSheetMap.entrySet()) {
            User user = val.getKey();
            BalanceSheet balanceSheet = val.getValue();

            double amount = balanceSheet.getTotalGetBackAmount() - balanceSheet.getTotalOweAmount();
            if (amount < 0.0) negativeQueue.offer(new Settlement(amount, user));
            else if (amount > 0.0) positiveQueue.offer(new Settlement(amount, user));
        }

        List<Transaction> transactions = new ArrayList<>();
        while (!positiveQueue.isEmpty() && !negativeQueue.isEmpty()) {

            Settlement toSettlement = positiveQueue.poll();
            double toAmount = toSettlement.getAmount();
            User toUser = toSettlement.getUser();

            Settlement fromSettlement = negativeQueue.poll();
            double fromAmount = fromSettlement.getAmount();
            User fromUser = fromSettlement.getUser();

            if (Math.abs(fromAmount) > toAmount) {
                negativeQueue.offer(new Settlement(fromAmount + toAmount, fromUser));
                transactions.add(new Transaction(fromUser, toUser, toAmount));
            } else if (Math.abs(fromAmount) < toAmount) {
                positiveQueue.offer(new Settlement(fromAmount + toAmount, toUser));
                transactions.add(new Transaction(fromUser, toUser, Math.abs(fromAmount)));
            } else transactions.add(new Transaction(fromUser, toUser, toAmount));
        }
        return transactions;
    }

}

