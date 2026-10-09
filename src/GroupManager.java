import java.util.List;

public class GroupManager {
    private List<Group> groups;

    public GroupManager(List<Group> groups) {
        this.groups = groups;
    }

    public BalanceSheet getBalanceSheet(User user, Group group) {
        return group.getBalanceSheet(user);
    }

    public void createExpense(User user, double amount, List<Split> splitList, Group group) {
        group.createExpense(user, amount, splitList);
    }

    public List<Transaction> simplifyDebt(Group group) {
        return group.simplifyDebt();
    }

}
