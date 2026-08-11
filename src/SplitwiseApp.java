import java.util.List;

public class SplitwiseApp {
    private UserManager userManager;
    private GroupManager groupManager;
    private SplitFactory splitFactory;

    public SplitwiseApp(UserManager userManager, GroupManager groupManager, SplitFactory splitFactory) {
        this.userManager = userManager;
        this.groupManager = groupManager;
        this.splitFactory = splitFactory;
    }


    public BalanceSheet getBalanceSheet(User user, Group group) {
        return groupManager.getBalanceSheet(user, group);
    }

    public Double totalOweAmount(User user, Group group) {
        return groupManager.totalOweAmount(user, group);
    }

    public Double totalGetBackAmount(User user, Group group) {
        return groupManager.totalGetBackAmount(user, group);
    }

    public Expense createExpense(User paidByUser, Double amount, List<Split> splitList, SplitType splitType, Group group) {
        SplitStrategy splitStrategy = splitFactory.getSplitStrategy(splitType);
        if (!splitStrategy.validate(amount, splitList)) throw new RuntimeException("Please select correct splitType");

        return groupManager.createExpense(paidByUser, amount, splitList, group);
    }

    public List<Transaction> simplifyDebt(Group group) {
        return groupManager.simplifyDebt(group);
    }
}
