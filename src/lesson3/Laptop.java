package lesson3;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Laptop {
    public List<Information> information = new ArrayList<>();
    public Connection connection = null;
    public void connecti(String sql) throws SQLException{
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
        ResultSet rs = statement.executeQuery(sql);
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
        }
    }
    public void findPrice(int x, int y) throws SQLException {
        String sql ="SELECT * FROM store_cms_plusplus.laptop where price>"+x+" and price <="+y+";";
        connecti(sql);
        byte count=0;
        for (Information inf : information) {
                count ++;
                System.out.println(inf.toString());
            }
        if(count==0) System.out.println("Không có sản phẩm nào");
    }
    public void findPrice(int x) throws SQLException {
        byte choose = 0;
        byte count=0;
        String sql1="SELECT * from store_cms_plusplus.laptop where price >="+x+";";
        String sql2="SELECT * from store_cms_plusplus.laptop where price <="+x+";";
        Scanner scanner = new Scanner(System.in);
        System.out.println("nhap 1 để tìm các Laptop có giá từ " + x + ",nhập 0 để lấy các Laptop có giá nhỏ hơn "+x+": ");
        choose = scanner.nextByte();
        if (choose == 1) {
            connecti(sql1);
            for (Information inf : information) {
                if (inf.getPrice() >= x) {
                    System.out.println(inf.toString());
                    count++;
                }
            }
            if(count==0) System.out.println("Không có sản phẩm nào phù hợp");
        } else {
            connecti(sql2);
            for (Information inf : information) {
                if (inf.getPrice() <= x) {
                    System.out.println(inf.toString());
                    count++;
                }
            }
            if(count==0) System.out.println("Không có sản phẩm nào phù hợp");
        }
    }
    public void findHardMaker(String x,String y) throws SQLException{
        byte count=0;
        String sql1="SELECT * FROM store_cms_plusplus.laptop where ssd is not null and maker=\""+y+"\";";
        String sql2="SELECT * FROM store_cms_plusplus.laptop where hdd is not null and maker=\""+y+"\";";
        if(x.equalsIgnoreCase("ssd")){
            connecti(sql1);
            for(Information inf: information){
                System.out.println(inf.toString());
            }
        }
        else{
            connecti(sql2);
            for(Information inf:information){
                System.out.println(inf.toString());
            }
        }
        if(count==0) System.out.println("Không có sản phẩm phù hợp");
    }
}

