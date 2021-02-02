package lesson3;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Mobile {
    public List<Information> information = new ArrayList<>();
    public Connection connection = null;

    public void addDB() throws SQLException {
        System.out.println("-------- MySQL JDBC Connection Demo ------------");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found !!");
            return;
        }
        System.out.println("MySQL JDBC Driver Registered!");
        try {
            connection = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/store_cms_plusplus?characterEncoding=utf8", "root", "123");
            System.out.println("SQL Connection to database established!");
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console" + e);
            return;
        }
        Statement statement = connection.createStatement();
        String query = "SELECT * FROM store_cms_plusplus.laptop;";
        ResultSet rs = statement.executeQuery(query);
        int total = 0;
        while (rs.next()) {
            String name = rs.getString(2);
            String maker = rs.getString(4);
            String type = rs.getString(5);
            String ram = rs.getString(6);
            String cpu = rs.getString(7);
            String ssd = rs.getString(8);
            String hdd = rs.getString(9);
            float price = rs.getFloat(10);
            String card = rs.getString(11);
            Information inf = new Information(name, maker, type, ram, cpu, ssd, hdd, price, card);
            information.add(inf);
//            System.out.println(information.get(total).toString());
//            total++;
        }

    }

    public void findPrice(int x, int y) {
        for (Information inf : information) {
            if (inf.getPrice() >= x && inf.getPrice() <= y) {
                System.out.println(inf.toString());
            }
        }
    }
    public void findPrice(int x) {
        byte choose = 0;
        byte count=0;
        Scanner scanner = new Scanner(System.in);
        System.out.println("nhap 1 để tìm các điện thoại có giá từ " + x + ",nhập 0 để lấy các điện thoại có giá nhỏ hơn"+x+": ");
        choose = scanner.nextByte();
        if (choose == 1) {
            for (Information inf : information) {
                if (inf.getPrice() >= x) {
                    System.out.println(inf.toString());
                    count++;
                }
            }
            if(count==0) System.out.println("Không có sản phẩm nào phù hợp");
        } else {
            for (Information inf : information) {
                if (inf.getPrice() <= x) {
                    System.out.println(inf.toString());
                    count++;
                }
            }
            if(count==0) System.out.println("Không có sản phẩm nào phù hợp");
        }
    }
    public void findHardMaker(String x,String y){
        byte count=0;
        for (Information inf: information){
            if(x.equalsIgnoreCase("ssd")==true){
                if(inf.getSsd()!=null&&inf.getMaker().equalsIgnoreCase(y)){
                    System.out.println(inf.toString());
                    count++;
                }
            }
            else{
                if (inf.getSsd() == null&&inf.getMaker().equalsIgnoreCase(y)) {
                    System.out.println(inf.toString());
                    count++;
                }
            }
        }
        if(count==0) System.out.println("Không có sản phẩm phù hợp");
    }
}

