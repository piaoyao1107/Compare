import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompareNumber {


    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/newbanker_py?useSSL=false&serverTimezone=UTC";
    static final String USER = "root";
    static final String PASS = "Passw0rd";
//    static final String runFile = "prd.txt";
//    static final String runFile = "docu.txt";
    static final String runFile = "test.txt";


    public void update(String name,String number,String filename){
        Connection conn = null;
        Statement stmt = null;
        String column = "test_number";

        try{
            Class.forName(JDBC_DRIVER);

            // 连接数据库
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            // 执行查询
            stmt = conn.createStatement();
            if(name.equals("/cc:")){
                name = "cc";
            }else if (name.equals("/sc-web")){
                name = "sc-web";
            }else if (name.equals("/bs-web:")){
                name = "bs-web";
            }else if (name.equals("/fe_is")){
                name = "fe_is";
            }else if (name.equals("/is:")){
                name = "is";
            }
            if (filename.equals("prd.txt")){
                column = "prd_number";
            }else if (filename.equals("docu.txt")){
                column = "docu_number";
            }else if (filename.equals("text.txt")){
                column = "test_number";
            }
            String sql = "UPDATE compare_number set "+column+"='"+number+"' where name='"+name+"' and status='1';";
            stmt.executeUpdate(sql);

            // 完成后关闭
            stmt.close();
            conn.close();
        }catch(SQLException se){
            se.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        //System.out.println("Goodbye!");

    }


    static class Select{
        Connection conn = null;
        Statement stmt = null;
        private String name;

        public void select(){
            try{
                Class.forName(JDBC_DRIVER);

                // 连接数据库
                conn = DriverManager.getConnection(DB_URL,USER,PASS);

                // 执行查询
                //System.out.println(" 实例化Statement对象...");
                stmt = conn.createStatement();
                String sql = "select * from compare_number where id = '1'";
                ResultSet rs = stmt.executeQuery(sql);

                // 展开结果集数据库
                while(rs.next()){
                    name = rs.getString("name");
                    System.out.println("服务名称是: " + name);
                }

                // 完成后关闭
                rs.close();
                stmt.close();
                conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }catch(Exception e){
                e.printStackTrace();
            }finally{
                try{
                    if(stmt!=null) stmt.close();
                }catch(SQLException se2){
                }
                try{
                    if(conn!=null) conn.close();
                }catch(SQLException se){
                    se.printStackTrace();
                }
            }
            //System.out.println("Goodbye!");
        }
    }



    public void readMe(String name,String filename){
        //File file = new File("/Users/apple/Downloads/Chapter01/src/main/resources/prd.txt");
        File file = new File("/Users/apple/Downloads/Chapter01/src/main/resources/"+filename);
        BufferedReader reader = null;
        String tempString = null;
        int line =1;

        try {
            //System.out.println("以行为单位读取文件内容，一次读一整行：");
            reader = new BufferedReader(new FileReader(file));
            while ((tempString = reader.readLine()) != null) {
                //这里写获取版本号，写入jdbc的代码块
                if(tempString.contains(name)){
                    String number = tempString.substring(tempString.length()-12);
                    System.out.println(name+" >>> "+number);
                    CompareNumber compareNumber = new CompareNumber();
                    compareNumber.update(name,number,runFile);
                }

                line ++ ;
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            if(reader != null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static void main(String[] args){
        List<String> number=new ArrayList<String>();
        number.add("fe_bi");
        number.add("fe_gwy");
        number.add("fe_im");
        number.add("/fe_is");
        number.add("fe_is_pri");
        number.add("fe_is_wx");
        number.add("fe_market");
        number.add("fe_wbs_admin");
        number.add("fe_wbs_cms_admin");
        number.add("fe_wbs_config");
        number.add("fe_wbs_investor");
        number.add("fe_wbs_qyht");
        number.add("fe_wp");
        number.add("h5_bossstation");
        number.add("h5_is_app");
        number.add("h5_is_wechat");
        number.add("h5_mc_wbs");
        number.add("im-service");
        number.add("im-web");
        number.add("/is:");
        number.add("wbs-cms");
        number.add("batchload");
        number.add("/bs-web:");
        number.add("/cc:");
        number.add("cc-api");
        number.add("csc-web");
        number.add("drc-service");
        number.add("drc-web");
        number.add("fs");
        number.add("gio-service");
        number.add("gio-web");
        number.add("gwy-res-web");
        number.add("gwy-web");
        number.add("ibs");
        number.add("ie-admin");
        number.add("ie-service");
        number.add("ie-web");
        number.add("lc-admin");
        number.add("lc-service");
        number.add("lc-web");
        number.add("lm-admin");
        number.add("lm-service");
        number.add("lm-web");
        number.add("marketing-admin");
        number.add("marketing-api");
        number.add("marketing-center");
        number.add("message-center");
        number.add("message-service");
        number.add("message-web");
        number.add("nb-dts");
        number.add("nb-es");
        number.add("nbmq-consumer-provider");
        number.add("nbmq-sender-provider");
        number.add("obj-admin");
        number.add("obj-service");
        number.add("obj-web");
        number.add("open-api");
        number.add("openapi-auth");
        number.add("passport");
        number.add("pco-web");
        number.add("pdc-service");
        number.add("pdc-web");
        number.add("pe-center");
        number.add("rbw");
        number.add("rsc-web");
        number.add("sc-service");
        number.add("/sc-web");
        number.add("sop-service");
        number.add("sop-web");
        number.add("wbs-res-service");
        number.add("wbs-res-web");
        number.add("wbs-web");
        number.add("web-api");
        number.add("wechat-service");
        number.add("wechat-web");
        number.add("xxl-job-admin");
        number.add("share");
        number.add("ac-service");
        number.add("ac-web");
        for (int i = 0; i < number.size(); i++) {
            String name = number.get(i);
            CompareNumber compareNumber = new CompareNumber();
            compareNumber.readMe(name,runFile);

        }

    }

}
