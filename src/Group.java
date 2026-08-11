import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Group {
    private int groupId;
    private List<User> userList;
    private List<Expense> expenseList;
    private Map<User, BalanceSheet> balanceSheetMap;
    private Object moniter;

    public Group(int groupId, List<User> userList) {
        this.groupId = groupId;
        this.userList = userList;
        this.expenseList = new ArrayList<>();
        this.balanceSheetMap = new ConcurrentHashMap<>();
        this.moniter = new Object();

    }


    public BalanceSheet getBalanceSheet(User user) {
        return balanceSheetMap.getOrDefault(user, new BalanceSheet());
    }


    public Double totalOweAmount(User user) {
        Double totalOweAmount = 0.0;
        for (Map.Entry<User, Balance> balanceSheet : balanceSheetMap.get(user).getUserVsBalance().entrySet()) {
            totalOweAmount += balanceSheet.getValue().getOweAmount();
        }
        return totalOweAmount;
    }


    public Double totalGetBackAmount(User user) {
        Double totalGetBackAmount = 0.0;
        for (Map.Entry<User, Balance> balanceSheet : balanceSheetMap.get(user).getUserVsBalance().entrySet()) {
            totalGetBackAmount += balanceSheet.getValue().getGetBackAmount();
        }
        return totalGetBackAmount;
    }


    public Expense createExpense(User paidByUser, Double amount, List<Split> splitList) {

        BalanceSheet paidByUserBalanceSheet = balanceSheetMap.computeIfAbsent(paidByUser, k -> new BalanceSheet());

        Double totalPayment = paidByUserBalanceSheet.getTotalPayment();
        paidByUserBalanceSheet.setTotalPayment(totalPayment + amount);

        for (Split split : splitList) {
            User oweUser = split.getOweUser();
            Double oweAmount = split.getOweAmount();

            if (oweUser.equals(paidByUser)) {
                Double totalExpense = paidByUserBalanceSheet.getTotalExpense();
                paidByUserBalanceSheet.setTotalExpense(totalExpense + oweAmount);
                continue;
            }


            // (update getBackAmount field of oweUser) in paidByUser BalanceSheet
            Double getBackAmountOfMapping = paidByUserBalanceSheet.getUserVsBalance().computeIfAbsent(oweUser, k -> new Balance()).getGetBackAmount();
            paidByUserBalanceSheet.getUserVsBalance().get(oweUser).setGetBackAmount(getBackAmountOfMapping + oweAmount);


            // (update oweAmount field of paidByUser) in oweUser BalanceSheet
            BalanceSheet oweUserBalanceSheet = balanceSheetMap.computeIfAbsent(oweUser, k -> new BalanceSheet());
            Double oweAmountOfMapping = oweUserBalanceSheet.getUserVsBalance().computeIfAbsent(paidByUser, k -> new Balance()).getOweAmount();
            oweUserBalanceSheet.getUserVsBalance().get(paidByUser).setOweAmount(oweAmountOfMapping + oweAmount);
        }

        Expense expense = new Expense(paidByUser, amount, splitList);
        synchronized (moniter) {
            expenseList.add(expense);
        }
        return expense;


    }


    public List<Transaction> simplifyDebt() {


        PriorityQueue<Pair<Double, User>> positiveQueue = new PriorityQueue<>((a, b) -> Double.compare(b.getFirst(), a.getFirst()));
        PriorityQueue<Pair<Double, User>> negativeQueue = new PriorityQueue<>((a, b) -> Double.compare(a.getFirst(), b.getFirst()));

        for (User user : balanceSheetMap.keySet()) {
            Double totalDeficit = totalGetBackAmount(user) - totalOweAmount(user);
            if (totalDeficit < 0.0) negativeQueue.offer(new Pair<>(totalDeficit, user));
            else if (totalDeficit > 0.0) positiveQueue.offer(new Pair<>(totalDeficit, user));
        }


        List<Transaction> transactions = new ArrayList<>();
        while (!positiveQueue.isEmpty() && !negativeQueue.isEmpty()) {
            Pair<Double, User> toEntry = positiveQueue.poll();
            Double toAmount = toEntry.getFirst();
            User toUser = toEntry.getSecond();

            Pair<Double, User> fromEntry = negativeQueue.poll();
            Double fromAmount = fromEntry.getFirst();
            User fromUser = fromEntry.getSecond();

            if (toAmount > Math.abs(fromAmount)) {
                positiveQueue.offer(new Pair<>(toAmount + fromAmount, toUser));
                transactions.add(new Transaction(fromUser, toUser, Math.abs(fromAmount)));

            } else if (toAmount < Math.abs(fromAmount)) {
                negativeQueue.offer(new Pair<>(toAmount + fromAmount, fromUser));
                transactions.add(new Transaction(fromUser, toUser, toAmount));

            } else transactions.add(new Transaction(fromUser, toUser, toAmount));

        }

        return transactions;
    }


}

