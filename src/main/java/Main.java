import JDBC.ConfigJDBC;

public class Main {
  public static void main(String[] args) {
    ConfigJDBC jdbc = new ConfigJDBC();
    jdbc.getConnection();
  }
}
