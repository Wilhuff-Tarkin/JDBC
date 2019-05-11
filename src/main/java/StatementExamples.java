import java.sql.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class StatementExamples {


    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        getConnection();
        Statement statement = getConnection().createStatement();
        statement.execute("CREATE TABLE if not exists movies(" +
                "id smallint unsigned not null auto_increment, " +
                "name varchar(20) not null, " +
                "productionYear int, " +
                "primary key (id) )");

        createEmployeeTable();
        fillEmployeeTable();
   //     deleteEmployeeWithSalary();
     //   deleteEmployeeTable();
     //   deleteEmployees();
        selectAllMovies();
        selectAllMoviesNameSorted();
        selectAllMoviesAfter1990();
        selectAllMoviesOnLetterB();

//        statement.executeUpdate("INSERT INTO movies (name, productionYear) " + "VALUES ('Star Wars', 1989)");
//        statement.executeUpdate("INSERT INTO movies (name, productionYear) " + "VALUES ('Star Wars2', 1990)");
//        statement.executeUpdate("INSERT INTO movies (name, productionYear) " + "VALUES ('Zardoz - Star Wars3', 1991)");
//        statement.executeUpdate("INSERT INTO movies (name, productionYear) " + "VALUES ('Bloody Sport', 1991)");
//        statement.executeUpdate("INSERT INTO movies (name, productionYear) " + "VALUES ('Matrix', 1992)");

//        PreparedStatement preparedStatement = getConnection().prepareStatement("INSERT INTO movies (name, productionYear) VALUES (?,?)");
//        preparedStatement.setString(1, "Dirty Harry");
//        preparedStatement.setInt(2, 1979);
//        preparedStatement.executeUpdate();


    }

    private static void selectAllMoviesOnLetterB() throws SQLException, ClassNotFoundException {
        PreparedStatement preparedStatement = getConnection().prepareStatement("SELECT name, productionYear FROM movies WHERE name LIKE 'B%';");
        ResultSet resultSet = preparedStatement.executeQuery();
        System.out.println("##### Movies starting with B:");
        System.out.println();


        while (resultSet.next()){
            String name = resultSet.getString("name");
            int year = resultSet.getInt("productionYear");
            System.out.println(name + " " + year);
        }
        System.out.println();
    }

    private static void selectAllMoviesAfter1990() throws SQLException, ClassNotFoundException {
        PreparedStatement preparedStatement = getConnection().prepareStatement("SELECT name, productionYear FROM movies WHERE productionYear > 1990 ORDER BY productionYear;");
        ResultSet resultSet = preparedStatement.executeQuery();
        System.out.println("##### Movies after 1990:");
        System.out.println();


        while (resultSet.next()){
            String name = resultSet.getString("name");
            int year = resultSet.getInt("productionYear");
            System.out.println(name + " " + year);
        }
        System.out.println();


    }

    private static void selectAllMoviesNameSorted() throws SQLException, ClassNotFoundException {

               PreparedStatement preparedStatement = getConnection().prepareStatement("SELECT name, productionYear FROM movies ORDER BY name");
        ResultSet resultSet = preparedStatement.executeQuery();
        System.out.println("##### Sorted by name:");
        System.out.println();


        while (resultSet.next()){
            String name = resultSet.getString("name");
            int year = resultSet.getInt("productionYear");
            System.out.println(name + " " + year);
        }
        System.out.println();

    }



    private static void selectAllMovies() throws SQLException, ClassNotFoundException {

        PreparedStatement preparedStatement = getConnection().prepareStatement("SELECT name, productionYear FROM movies");
        ResultSet resultSet = preparedStatement.executeQuery();
        System.out.println("##### All movies:" );
        System.out.println();
        List allMoviesList = new LinkedList();

        while (resultSet.next()){
            String name = resultSet.getString("name");
            int productionYear = resultSet.getInt("productionYear");
            System.out.println(name + " " + productionYear);
            Movie movie = new Movie(name, productionYear);
            allMoviesList.add(movie);
        }

        System.out.println(Arrays.toString(allMoviesList.toArray()));
        System.out.println();

    }

    private static void deleteEmployees() throws SQLException, ClassNotFoundException {

        PreparedStatement preparedStatement = getConnection().prepareStatement("INSERT INTO Employees (name, salary) VALUES (?,?)");

        for (int i = 0; i < 5; i++) {
            preparedStatement.setString(1, "Ryszard Walizka" + i);
            preparedStatement.setInt(2, 3000 + i*3);
            preparedStatement.executeUpdate();
        }
    }

    private static void deleteEmployeeTable() throws SQLException, ClassNotFoundException {
        Statement statement2 = getConnection().createStatement();
        statement2.execute("DROP TABLE Employee");


    }

    private static void deleteEmployeeWithSalary() throws SQLException, ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("podaj maksymalną wysokosc pensji - pracownicy z nisza pensja zostana usunieci");
        int salary = scanner.nextInt();

        Statement statement2 = getConnection().createStatement();
        statement2.execute("DELETE FROM employees WHERE salary < " + salary);
    }


    private static void fillEmployeeTable() throws SQLException, ClassNotFoundException {

        PreparedStatement preparedStatement = getConnection().prepareStatement("INSERT INTO Employees (name, salary) VALUES (?,?)");

        for (int i = 0; i < 5; i++) {
            preparedStatement.setString(1, "Ryszard Walizka" + i);
            preparedStatement.setInt(2, 3000 + i*3);
            preparedStatement.executeUpdate();
        }
    }

    private static void createEmployeeTable() throws SQLException, ClassNotFoundException {
        Statement statement2 = getConnection().createStatement();
        statement2.execute("CREATE TABLE if not exists Employees(" +
                "id smallint unsigned not null auto_increment, " +
                "name varchar(20) not null, " +
                "salary int, " +
                "primary key (id) )");
    }

    private static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mytestdb?serverTimezone=UTC&logger=com.mysql.cj.log.Slf4JLogger&profileSQL=true","root", "mentlik221");
        return connection;
    }
}
