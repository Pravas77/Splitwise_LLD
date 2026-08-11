import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        System.out.println("Hello");

        User user1 = new User(1);
        User user2 = new User(2);
        User user3 = new User(3);
        User user4 = new User(4);
        UserManager userManager = new UserManager(List.of(user1, user2, user3, user4));


        Group group1 = new Group(1,List.of(user1, user2, user3, user4));
        GroupManager groupManager = new GroupManager(List.of(group1));


        SplitFactory splitFactory = new SplitFactory(Map.of(
                SplitType.EQUAL,new EqualSplitStrategy(),
                SplitType.UNEQUAL,new UnequalSplitStrategy(),
                SplitType.PERCENTAGE,new PercentageSplitStrategy()
        ));


        SplitwiseApp splitwiseApp = new SplitwiseApp(userManager,groupManager,splitFactory);


        splitwiseApp.createExpense(
                user1,
                400.0,
                List.of(
                        new Split(user1,100.0),
                        new Split(user2,100.0),
                        new Split(user3,100.0),
                        new Split(user4,100.0)),
                SplitType.EQUAL,
                group1
                );


        splitwiseApp.createExpense(
                user2,
                400.0,
                List.of(
                        new Split(user1,100.0),
                        new Split(user2,100.0),
                        new Split(user3,100.0),
                        new Split(user4,100.0)),
                SplitType.EQUAL,
                group1
        );



        List<Transaction> transactions = splitwiseApp.simplifyDebt(group1);
        for (Transaction transaction : transactions){
            System.out.println(transaction);
        }



        Double user1Net = splitwiseApp.totalGetBackAmount(user1,group1) - splitwiseApp.totalOweAmount(user1,group1);
        Double user2Net = splitwiseApp.totalGetBackAmount(user2,group1) - splitwiseApp.totalOweAmount(user2,group1);
        Double user3Net = splitwiseApp.totalGetBackAmount(user3,group1) - splitwiseApp.totalOweAmount(user3,group1);
        Double user4Net = splitwiseApp.totalGetBackAmount(user4,group1) - splitwiseApp.totalOweAmount(user4,group1);

        System.out.println(user1 + " " +user1Net);
        System.out.println(user2 + " " +user2Net);
        System.out.println(user3 + " " +user3Net);
        System.out.println(user4 + " " +user4Net);



        BalanceSheet balanceSheet1 = splitwiseApp.getBalanceSheet(user1,group1);
        BalanceSheet balanceSheet2 = splitwiseApp.getBalanceSheet(user2,group1);
        BalanceSheet balanceSheet3 = splitwiseApp.getBalanceSheet(user3,group1);
        BalanceSheet balanceSheet4 = splitwiseApp.getBalanceSheet(user4,group1);

        System.out.println(balanceSheet1);
        System.out.println(balanceSheet2);
        System.out.println(balanceSheet3);
        System.out.println(balanceSheet4);



    }
}