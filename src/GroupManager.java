import java.util.List;

public class GroupManager {
    private List<Group> groups;

    public GroupManager(List<Group> groups) {
        this.groups = groups;
    }

    public BalanceSheet getBalanceSheet(User user, Group group) {
        return group.getBalanceSheet(user);
    }

    public Double totalOweAmount(User user, Group group) {
        return group.totalOweAmount(user);
    }

    public Double totalGetBackAmount(User user, Group group) {
        return group.totalGetBackAmount(user);
    }

    public Expense createExpense(User paidByUser, Double amount, List<Split> splitList, Group group) {
        return group.createExpense(paidByUser, amount, splitList);
    }

    public List<Transaction> simplifyDebt(Group group) {
        return group.simplifyDebt();
    }


}
