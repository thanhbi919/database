package lesson3;

public class Counter {
    private String maker;
    private Integer total;

    public Counter(String maker, Integer total) {
        this.maker = maker;
        this.total = total;
    }

    public Counter() {
    }

    @Override
    public String toString() {
        return "Counter{" +
                "maker='" + maker + '\'' +
                ", total=" + total +
                '}';
    }
}
