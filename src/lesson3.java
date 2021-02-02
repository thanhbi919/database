import java.sql.SQLException;

public class lesson3 {
    public static void main(String[] args) throws SQLException {
        FindPrice fp = new FindPrice();
        fp.addDB();
        fp.findPrice(10000000);
    }


}

