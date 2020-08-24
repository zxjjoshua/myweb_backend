import com.sun.org.apache.xpath.internal.functions.FuncFalse;
import org.postgresql.*;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import DB.Table;

public class PostgreSQLJDBC {
    private Connection c;
    private String host;
    private String port;
    private String db;
    private String username;
    private String passwd;
    private String PostgreDBurl;
    private PostgreSQLJDBC(PostgreSQLJDBCBuilder builder){
        this.host= builder.host;
        this.port=builder.port;
        this.db= builder.db;
        this.username= builder.username;
        this.passwd= builder.passwd;
        this.PostgreDBurl= builder.PostgreDBurl;

    }



    public static void main(String args[]){
//        Connection c=null;

        PostgreSQLJDBC jdbc=new PostgreSQLJDBCBuilder().build();
        jdbc.Connect();


//        String host="localhost";
//        String port="5432";
//        String db="postgres";
//        String username="postgres";
//        String passwd="qweqwe";
//        String PostgreDBurl="jdbc:postgresql:"+"//"+host+":"+port+"/"+db;
//        Properties props=new Properties();
//        props.setProperty("user", username);
//        props.setProperty("password",passwd);
//        props.setProperty("ssl","true");

    }

    ///////////////////////
    //  connection status
    //

    public boolean Connect(){

        Properties props=new Properties();
        props.setProperty("user", this.username);
        props.setProperty("password",this.passwd);
        System.out.println(this.PostgreDBurl);
        try{
            Class.forName("org.postgresql.Driver");
            this.c =DriverManager.getConnection(this.PostgreDBurl, props);
        }catch (Exception e){
            e.printStackTrace();
            System.err.println(e.getClass().getName()+":"+e.getMessage());
            System.exit(0);
        }
        System.out.println("conenction succ");
        return true;
    }


    ///////
    //      db operation: DURW
    ///

    public boolean dropTable(String tableName){
        try{
            Statement s = this.c.createStatement();
            String sql="drop table "+tableName;
            s.execute(sql);
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;

    }

    private boolean createTable(String tablename){
        try{
            Statement s=this.c.createStatement();
            Table table=new Table.TableBuilder("blogs")
                    .addProperty("title", "vahrchar(50)")
                    .addProperty("date", "date")
                    .addProperty("url", "varvhar(200)")
                    .build();
            String sql=table.getString();
            System.out.println(sql);
        }catch (Exception e){
            e.printStackTrace();
        }
        return true;

    }





    ////////////////////////////////////////////////
    //      PostgreSQLJDBCBuilder()
    //
    ////////////////////////////////////////////////

    public static class PostgreSQLJDBCBuilder{
        private String host;
        private String port;
        private String db;
        private String username;
        private String passwd;
        private String PostgreDBurl;
        public PostgreSQLJDBCBuilder(){
            this.host="localhost";
            this.port="5432";
            this.db="postgres";
            this.username="postgres";
            this.passwd="qweqwe";
        }

        public PostgreSQLJDBCBuilder setHost(String host){
            this.host=host;
            return this;
        }
        public PostgreSQLJDBCBuilder setPort(int port){
            this.port=Integer.toString(port);
            return this;
        }

        public PostgreSQLJDBCBuilder setDBname(String DBname){
            this.db=DBname;
            return this;
        }
        public PostgreSQLJDBCBuilder setUsername(String Username){
            this.username=Username;
            return this;
        }
        public PostgreSQLJDBCBuilder setPasswd(String Passwd){
            this.passwd=Passwd;
            return this;
        }

        public PostgreSQLJDBC build(){
            this.PostgreDBurl="jdbc:postgresql:"+"//"+this.host+":"+this.port+"/"+this.db;
            return new PostgreSQLJDBC(this);
        }

    }
}
