package lesson3;

import com.mysql.cj.jdbc.interceptors.ResultSetScannerInterceptor;
import com.mysql.cj.jdbc.result.UpdatableResultSet;
import com.mysql.cj.xdevapi.UpdateStatement;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Laptop {
    public List<Information> information = new ArrayList<>();
    public Connection connection = null;
    public List<Information> connecti(String sql) throws SQLException{
        System.out.println("-------- MySQL JDBC Connection Demo ------------");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found !!");
            return null;
        }
        System.out.println("MySQL JDBC Driver Registered!");
        try {
            connection = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/store_cms_plusplus?characterEncoding=utf8", "root", "123");
            System.out.println("SQL Connection to database established!");
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console" + e);
            return null;
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
        return information;
    }
    public List<Information> findPrice(float x, float y) throws SQLException {
        String sql ="SELECT * FROM store_cms_plusplus.laptop where price>"+x+" and price <="+y+";";
        return connecti(sql);
//        byte count=0;
//        for (Information inf : information) {
//                count ++;
//                System.out.println(inf.toString());
//            }
//        if(count==0) System.out.println("Không có sản phẩm nào");
    }
    public List<Information> findPrice(int x) throws SQLException {
        byte choose = 0;
        byte count=0;
        String sql1="SELECT * from store_cms_plusplus.laptop where price >="+x+";";
        String sql2="SELECT * from store_cms_plusplus.laptop where price <="+x+";";
        Scanner scanner = new Scanner(System.in);
        System.out.println("nhap 1 để tìm các Laptop có giá từ " + x + ",nhập 0 để lấy các Laptop có giá nhỏ hơn "+x+": ");
        choose = scanner.nextByte();
        if (choose == 1) {
            return connecti(sql1);
//            for (Information inf : information) {
//                if (inf.getPrice() >= x) {
//                    System.out.println(inf.toString());
//                    count++;
//                }
//            }
//            if(count==0) System.out.println("Không có sản phẩm nào phù hợp");
        } else {
            return connecti(sql2);
//            for (Information inf : information) {
//                if (inf.getPrice() <= x) {
//                    System.out.println(inf.toString());
//                    count++;
//                }
//            }
//            if(count==0) System.out.println("Không có sản phẩm nào phù hợp");
        }
    }
    public List<Information> findHardMaker(String x,String y) throws SQLException{
        byte count=0;
        String sql1="SELECT * FROM store_cms_plusplus.laptop where ssd is not null and maker=\""+y+"\" ;";
        String sql2="SELECT * FROM store_cms_plusplus.laptop where hdd is not null and maker=\""+y+"\" ;";
        if(x.equalsIgnoreCase("ssd")){
           return connecti(sql1);
//            for(Information inf: information){
//                System.out.println(inf.toString());
//            }
        }
        else{
           return connecti(sql2);
//            for(Information inf:information){
//                System.out.println(inf.toString());
//            }
        }
//        if(count==0) System.out.println("Không có sản phẩm phù hợp");
    }
//    public List<Information> findLaptopByCondition(Float minPrice, Float maxPrice, String ssd, String hdd, String ram) throws SQLException{
//        //gia tu
//        String sql="SELECT * FROM store_cms_plusplus.laptop where ("
//                +minPrice+
//                " is null or price>="
//                +minPrice+
//                ") and ("
//                +maxPrice+
//                " is null or price<="
//                +maxPrice+
//                ") and (\""
//                +ssd+
//                "\" is null or ssd =\""
//                +ssd+
//                "\") and (\""
//                +hdd+
//                "\" is null or hdd =\""
//                +hdd+
//                "\" ) and (\""
//                +ram+
//                "\" is null or ram = \""
//                +ram+
//                "\");";
//        return connecti(sql);
//    }
    public List<Counter> getCounterbyMaker() throws SQLException{
        List<Counter> counters = new ArrayList<Counter>();
        String sql="SELECT maker,count(*) AS total FROM store_cms_plusplus.laptop group by maker";
        System.out.println("-------- MySQL JDBC Connection Demo ------------");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found !!");
            return null;
        }
        System.out.println("MySQL JDBC Driver Registered!");
        try {
            connection = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/store_cms_plusplus?characterEncoding=utf8", "root", "123");
            System.out.println("SQL Connection to database established!");
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console" + e);
            return null;
        }
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            String maker = rs.getString(1);
            Integer total = rs.getInt(2);
            Counter ct= new Counter(maker,total);
            counters.add(ct);
        }
        return counters;
    }
    public List<Statistic> getStatisticbyMaker() throws SQLException{
        List<Statistic> statistics = new ArrayList<Statistic>();
        String sql="SELECT maker, sum(sold) totalSold, sum(sold*price) as totalMoney FROM laptop group by maker ;";
        System.out.println("-------- MySQL JDBC Connection Demo ------------");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found !!");
            return null;
        }
        System.out.println("MySQL JDBC Driver Registered!");
        try {
            connection = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/store_cms_plusplus?characterEncoding=utf8", "root", "123");
            System.out.println("SQL Connection to database established!");
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console" + e);
            return null;
        }
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            String maker = rs.getString(1);
            Integer totalSold = rs.getInt(2);
            Integer totalMoney = rs.getInt(3);
            Statistic st= new Statistic(maker,totalSold,totalMoney);
            statistics.add(st);
        }
        return statistics;
    }
    public void updateSold() throws SQLException{
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập id:");
        int id =scanner.nextInt();
        System.out.println("nhập số lượng:");
        int sold =scanner.nextInt();
        String sql="UPDATE laptop SET sold ="+sold+" where id ="+id+";";
//        String sql ="SELECT * FROM laptop;";
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
        PreparedStatement statement = connection.prepareStatement(sql);
        int rs = statement.executeUpdate();
    }
}

