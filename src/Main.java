import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        System.out.println("Hello");

        User user1 = new User(1);
        User user2 = new User(2);
        User user3 = new User(3);
        User user4 = new User(4);

        UserManager userManager = new UserManager(List.of(user1,user2,user3,user4));

        Group group1 = new Group(
                1,
                Map.of(
                        user1,new BalanceSheet(),
                        user2,new BalanceSheet(),
                        user3,new BalanceSheet(),
                        user4,new BalanceSheet())
        );

        GroupManager groupManager = new GroupManager(List.of(group1));

        SplitwiseApp splitwiseApp = new SplitwiseApp(userManager,groupManager);


        Thread thread1 = new Thread(() -> {
            splitwiseApp.createExpense(
                    user1,
                    400,
                    List.of(
                            new Split(user1,100),
                            new Split(user2,100),
                            new Split(user3,100),
                            new Split(user4,100)),
                    group1,
                    new EqualSplitStrategy());
        });

        Thread thread2 = new Thread(() -> {
            splitwiseApp.createExpense(
                    user4,
                    200,
                    List.of(
                            new Split(user1,50),
                            new Split(user2,50),
                            new Split(user3,50),
                            new Split(user4,50)),
                    group1,
                    new EqualSplitStrategy());
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println(user1 + " -> " + splitwiseApp.getBalanceSheet(user1,group1));
        System.out.println(user2 + " -> " + splitwiseApp.getBalanceSheet(user2,group1));
        System.out.println(user3 + " -> " + splitwiseApp.getBalanceSheet(user3,group1));
        System.out.println(user4 + " -> " + splitwiseApp.getBalanceSheet(user4,group1));

        System.out.println("Printing transaction.....");

        System.out.println(splitwiseApp.simplifyDebt(group1));
    }
}