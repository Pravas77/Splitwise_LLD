import java.util.Map;

public class Split {
    private User oweUser;
    private double oweAmount;

    public Split(User oweUser, double oweAmount) {
        this.oweUser = oweUser;
        this.oweAmount = oweAmount;
    }

    public User getOweUser() {
        return oweUser;
    }

    public void setOweUser(User oweUser) {
        this.oweUser = oweUser;
    }

    public double getOweAmount() {
        return oweAmount;
    }

    public void setOweAmount(double oweAmount) {
        this.oweAmount = oweAmount;
    }
}
