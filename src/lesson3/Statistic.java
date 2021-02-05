package lesson3;

public class Statistic {
    private String maker;
    private int totalSold;
    private int totalMoney;

    public Statistic() {
    }

    public Statistic(String maker, int totalSold, int totalMoney) {
        this.maker = maker;
        this.totalSold = totalSold;
        this.totalMoney = totalMoney;
    }

    @Override
    public String toString() {
        return "Statistic{" +
                "maker='" + maker + '\'' +
                ", totalSold=" + totalSold +
                ", totalMoney=" + totalMoney +
                '}';
    }
}
