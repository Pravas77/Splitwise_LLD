import java.util.List;

public class SplitwiseApp {
    private UserManager userManager;
    private GroupManager groupManager;

    public SplitwiseApp(UserManager userManager, GroupManager groupManager) {
        this.userManager = userManager;
        this.groupManager = groupManager;
    }

    public BalanceSheet getBalanceSheet(User user, Group group) {
        return groupManager.getBalanceSheet(user, group);
    }

    public void createExpense(User user, double amount, List<Split> splitList, Group group, SplitStrategy splitStrategy) {
        if (!splitStrategy.validate(amount, splitList))
            throw new RuntimeException("Please select correct split strategy");
        groupManager.createExpense(user, amount, splitList, group);
    }

    public List<Transaction> simplifyDebt(Group group) {
        return groupManager.simplifyDebt(group);
    }

}
