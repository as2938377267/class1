package util.DbUtil;

import com.pojo.Dep;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class DButil {

    //1. 实例化连接池
    public static Vector<Connection> connectionPool = new Vector<Connection>();

    //2. 初始化连接池
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            for (int i = 0; i < 10; i++) {
                Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/s82", "root", "123456");
                connectionPool.add(connection);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //3.取连接
    public static Connection getConnection() {
        if (connectionPool.size() == 0) {
            throw new RuntimeException("连接池为空请稍等");
        }
        Connection connection = connectionPool.get(0);
        connectionPool.remove(0);
        return connection;
    }

    //4.释放连接
    public static void releaseConnection(Connection connection) {
        connectionPool.add(connection);
    }

    //5.增删改
    public static int zsg(String sql, Object... p) {
        Connection connection = getConnection();
        int n = 0;
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            //处理问号
            for (int i = 0; i < p.length; i++) {
                ps.setObject(i + 1, p[i]);
            }
            n = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            releaseConnection(connection);
        }

        return n;

    }

    //查询   ResultSet-->List(dao)    定义方法的时候  sql没确定  就不知道sql所对应的类
    //   不知道sql语句查的是那张表  所以不能确定对应的类
    //  select depid , depname from dep --> Dep   select empid, empname from emp --> Emp
    public static  List query(String sql, Class c, Object... p) {
        Connection connection = getConnection();
        List list = new ArrayList();
        ResultSet rs = null;
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            for (int i = 0; i < p.length; i++) {
                ps.setObject(i + 1, p[i]);
            }
            rs = ps.executeQuery();
            //表   类       字段    属性       一条记录   对象
            ResultSetMetaData rsmd = rs.getMetaData();//得到metadata  rs是根据sql查出来的  rsmd  根据rs得到的
            int count = rsmd.getColumnCount(); //得到总列数//总的属性数    有几个属性就要给几个属性赋值   表中有6个列   sql查了3列     count?
            while (rs.next()) {//  循环一次就有一条记录   大循环确定的关系是  一条记录对应一个对象
                Object object = c.newInstance();// 产生的对象属性都是默认值  int 0 String null
                for (int i = 0; i < count; i++) {// 小循环
                    //rsmd.getColumnLabel(i+1)   得到结果集中查询的列的名字
                    // select depid , depname from dep --> Dep
                    String fieldname = rsmd.getColumnLabel(i+1);//   第一次   fieldname  depid   第二次 fieldname depname
                    //如果需要使用改方法    必须确保一个关系     数据中的字段名==类中的属性名
                    //  rsmd.getColumnLabel(i+1);  得到的是列名    fieldname 就是对应属性的名字
                    Field field = c.getDeclaredField(fieldname);//根据属性名得到属性    pojo中属性都是私有化属性
                    field.setAccessible(true);//设置私有属性可以赋值
                    /*
                        select depid , depname from dep --  Dep
                        depid  depname                      depid   depname
                        当sql语句确定的时候     所对应的类也确定了
                     */
                    field.set(object, rs.getObject(i+1));//就是把数据库中查出来的值  赋值给了对象的属性
                }


                //   把数据中查出来的数据   用java中的对象保存起来了

                list.add(object);//再把对象加入list集合中   此时   该list集合   就保存了一张表的数据

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            releaseConnection(connection);
        }

        return list;

    }
    //查询
    public static ResultSet query1(String sql, Object... p) {
        Connection connection = getConnection();
        ResultSet rs = null;
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            for (int i = 0; i < p.length; i++) {
                ps.setObject(i + 1, p[i]);
            }
            rs = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rs;

    }


    //聚合查询
    public static double uniqueQuery(String sql, Object... p) {
        Connection connection = getConnection();
        double d = 0;
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            for (int i = 0; i < p.length; i++) {
                ps.setObject(i + 1, p[i]);
            }

            ResultSet rs = ps.executeQuery();
            rs.next();

            d = rs.getDouble(1);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            releaseConnection(connection);
        }

        return d;

    }
    public static void main(String[] args) {
        String sql = "select depid,dname from dep" ;
        List<Dep> list = query(sql , Dep.class);
        for (Dep dep : list) {
            System.out.println(dep.getDepid()+"\t" + dep.getDepname());
        }}

}

