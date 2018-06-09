package librarys.database;


import librarys.log.logLib;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class mysqlConn {

    public Connection con;

    public mysqlConn(String connect, String username, String pass) {
        if (!mysqlConnect(connect, username, pass))
            try {
                throw new Exception("数据库连接失败");
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                logLib.logError("数据库连接失败");
            }
    }

    public Boolean mysqlConnect(String connect, String username, String pass) {
        try {
            logLib.logInfo("mysqlConnect 连接到mysql数据库：" + connect);
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
            return false;
        }
        String url = "jdbc:mysql://" + connect;
        try {
            con = DriverManager.getConnection(url, username, pass);
            return con.isClosed() == false;
        } catch (SQLException e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public boolean Insert(String sql) {
        int i = 0;
        try {
            Statement statement = con.createStatement(1004, 1008);
            i = statement.executeUpdate(sql);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        boolean result = (i != 0);
        return result;
    }

    public boolean Delete(String sql) {
        int i = 0;
        try {
            Statement statement = con.createStatement(1004, 1008);
            i = statement.executeUpdate(sql);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        boolean result = (i != 0);
        return result;
    }

    public void closeDBcon() {
        try {
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public boolean Update(String sql) {
        int i = 0;
        try {
            Statement statement = con.createStatement(1004, 1008);
            i = statement.executeUpdate(sql);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        boolean result = (i != 0);
        return result;
    }

    public List<String> query(String sql) {
        try {
            // 改动部分
            Statement statement = con.createStatement(1004, 1008);
            // Statement statement = con.createStatement();
            if (statement != null) {
                ResultSet res = statement.executeQuery(sql);
                ResultSetMetaData rsmd = res.getMetaData();
                int columnCount = rsmd.getColumnCount();
                List<String> result = new ArrayList<String>();
                while (res.isLast() == false) {
                    String resulttemp = "";
                    res.next();
                    if (res.getRow() > 0) {
                        for (int i = 1; i <= columnCount; i++) {
                            String tempresult = String.valueOf(res.getObject(i));
                            if (resulttemp.equals("")) {
                                resulttemp = tempresult;
                            } else {
                                resulttemp = resulttemp + "&" + tempresult;
                            }
                        }
                    } else {
                        res.close();
                        statement.close();
                        statement = null;
                        return result;
//						throw new java.sql.SQLException("query\nSQL:" + sql + "\nResult:结果集为空");
                    }
                    result.add(resulttemp);
                }
                res.close();
                statement.close();
                statement = null;
                return result;
            } else {
                Logger.getLogger(mysqlConn.class.getName()).log(Level.SEVERE, null, "oracle statement create failed");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

}

