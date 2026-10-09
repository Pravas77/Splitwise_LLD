public class Balance {
    private double oweAmount;
    private double getBackAmount;

    public Balance() {
        this.oweAmount = 0.0;
        this.getBackAmount = 0.0;
    }

    public double getOweAmount() {
        return oweAmount;
    }

    public void setOweAmount(double oweAmount) {
        this.oweAmount = oweAmount;
    }

    public double getGetBackAmount() {
        return getBackAmount;
    }

    public void setGetBackAmount(double getBackAmount) {
        this.getBackAmount = getBackAmount;
    }

    @Override
    public String toString() {
        return "Balance{" + "oweAmount=" + oweAmount + ", getBackAmount=" + getBackAmount + '}';
    }
}
