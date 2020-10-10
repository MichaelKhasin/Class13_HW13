import java.sql.*;

public class DBTest {
    private static final String USER_NAME = "hQm6Iojltz";
    private static final String DATABASE_NAME = "hQm6Iojltz";
    private static final String PASSWORD = "LNxw98Cdk8";
    private static final String PORT = "3306";
    private static final String SERVER = "remotemysql.com";

    public static void main(String[] args) throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:mysql://"+SERVER+":"+PORT, USER_NAME, PASSWORD);

//        createTable(con);
//        insertDog(con, "Rex", 10, "huskey");
//        getTableContent(con);
//        deleteDogByBreed(con, "huskey");
//        updateDogAge(con,15, "bulldog");
        con.close();
    }

    private static void createTable(Connection con) throws SQLException {
        String statementToExecute = "CREATE TABLE " + DATABASE_NAME + ".`dogs`(`name` VARCHAR(40) NOT NULL,`age` INT, `breed` VARCHAR(30) NOT NULL);";
        con.createStatement().execute(statementToExecute);
    }

    private static void insertDog(Connection con, String name, int age, String breed) throws SQLException {
        String statementToExecute = "INSERT INTO " + DATABASE_NAME + ".dogs (`name`, `age`, `breed`) VALUES ('" + name + "', '" + age + "', '" + breed + "');";
        con.createStatement().execute(statementToExecute);
    }

    private static void getTableContent(Connection con) throws SQLException {
        String statementToExecute = "SELECT * FROM " + DATABASE_NAME + ".dogs;";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(statementToExecute);
        while(rs.next()){
            //Retrieve by column name
            String name = rs.getString("name");
            int age  = rs.getInt("age");
            String breed = rs.getString("breed");

            //Display values
            System.out.print("\n" + "Name: " + name);
            System.out.print(", Age: " + age);
            System.out.print(", Breed: " + breed);
        }
        rs.close();
    }
    private static void deleteDogByBreed(Connection con, String breed) throws SQLException {
        String statementToExecute = "DELETE FROM `" + DATABASE_NAME + "`.`dogs` WHERE `breed`='"+breed+"';";
        con.createStatement().execute(statementToExecute);
    }

    private static void updateDogAge(Connection con, int age, String breed) throws SQLException {
        String statementToExecute = "UPDATE `" + DATABASE_NAME + "`.`dogs` SET `age`='"+age+"' WHERE `breed`='"+breed+"';";
        con.createStatement().executeUpdate(statementToExecute);
    }
}