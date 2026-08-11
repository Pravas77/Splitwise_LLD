public class Balance {
    private Double oweAmount;
    private Double getBackAmount;

    public Balance() {
        this.oweAmount = 0.0;
        this.getBackAmount = 0.0;
    }

    public Double getOweAmount() {
        return oweAmount;
    }

    public void setOweAmount(Double oweAmount) {
        this.oweAmount = oweAmount;
    }

    public Double getGetBackAmount() {
        return getBackAmount;
    }

    public void setGetBackAmount(Double getBackAmount) {
        this.getBackAmount = getBackAmount;
    }

    @Override
    public String toString() {
        return "Balance{" +
                "oweAmount=" + oweAmount +
                ", getBackAmount=" + getBackAmount +
                '}';
    }
}
