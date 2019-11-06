import java.sql.*;

public class MySQLDemo {

    // MySQL 8.0 以上版本 - JDBC 驱动名及数据库 URL
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/newbanker_py?useSSL=false&serverTimezone=UTC";

    static final String USER = "root";
    static final String PASS = "Passw0rd";

    Connection conn = null;
    Statement stmt = null;
    private String name;

//    public void select(){
//        try{
//            Class.forName(JDBC_DRIVER);
//
//            // 打开链接
//            conn = DriverManager.getConnection(DB_URL,USER,PASS);
//
//            // 执行查询
//            stmt = conn.createStatement();
//            String sql = "select name from compare_number where status = '1'";
//            ResultSet rs = stmt.executeQuery(sql);
//
//            // 展开结果集数据库
//            while(rs.next()){
//                name = rs.getString("name");
//                System.out.println("服务名称是: " + name);
//            }
//
//            // 完成后关闭
//            rs.close();
//            stmt.close();
//            conn.close();
//        }catch(SQLException se){
//            se.printStackTrace();
//        }catch(Exception e){
//            e.printStackTrace();
//        }finally{
//            try{
//                if(stmt!=null) stmt.close();
//            }catch(SQLException se2){
//            }
//            try{
//                if(conn!=null) conn.close();
//            }catch(SQLException se){
//                se.printStackTrace();
//            }
//        }
//        //System.out.println("Goodbye!");
//    }

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        try{
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);

            // 打开链接
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            // 执行查询
            stmt = conn.createStatement();
            String sql;

            /*
            * 查询mysql
            */
            sql = "select * from compare_number where status = '1';";
            ResultSet rs = stmt.executeQuery(sql);

            // 展开结果集数据库
            while(rs.next()){
                // 通过字段检索
                String name = rs.getString("name");
                System.out.print("number.add(\"" + name+"\");");
                System.out.println("\n");
            }

            // 完成后关闭
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }finally{
            // 关闭资源
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }// 什么都不做
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        System.out.println("Goodbye!");
    }

}
