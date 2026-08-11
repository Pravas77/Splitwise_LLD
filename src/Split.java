import java.util.Map;

public class Split {
    private User oweUser;
    private Double oweAmount;

    public Split(User oweUser, Double oweAmount) {
        this.oweUser = oweUser;
        this.oweAmount = oweAmount;
    }

    public User getOweUser() {
        return oweUser;
    }

    public void setOweUser(User oweUser) {
        this.oweUser = oweUser;
    }

    public Double getOweAmount() {
        return oweAmount;
    }

    public void setOweAmount(Double oweAmount) {
        this.oweAmount = oweAmount;
    }
}
