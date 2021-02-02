package lesson3;

import java.sql.SQLException;

public class lesson3 {
    public static void main(String[] args) throws SQLException {
        Laptop fp = new Laptop();
        fp.addDB();
//        fp.findPrice(10000000);
        fp.findHardMaker("ssd","asus");
    }


}

