package ldu.dao;

public class sqlInject {
    public String selectBySql(String username, String password) {
        String sql = "select * from Users where username = '" + username +"' and password = '" + password +"'";
        return sql;
    }
}
