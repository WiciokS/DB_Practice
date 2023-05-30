package lt.viko.eif.vskuder.DAO;

public class DAO {
    protected static String url = "jdbc:mysql://localhost:3306/db_practice";

    protected static String user = "root";

    protected static String password = "root";

    protected static String database_name = "";

    public DAO(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    public DAO(){

    }
}
