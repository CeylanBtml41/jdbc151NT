import java.sql.*;

public class ExecuteUpdate01 {

    public static void main(String[] args) throws SQLException {

        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "364136");
        Statement statement = connection.createStatement();


        String sql ="update companies set number_of_employees=16000 where (select avg(number_of_employees) from companies) >number_of_employees;";

       int guncellenenSatirSayisi = statement.executeUpdate(sql);//executeUpdate() methodu guncellenen satir sayisini int deger olarak doner
        System.out.println("guncellenenSatirSayisi = " + guncellenenSatirSayisi);


        //Güncelleme sonrası datayı okumak için DQL(Select) kullanıyoruz:

        String sql2="select * from companies";
        ResultSet resultSet = statement.executeQuery(sql2);

        while (resultSet.next()) {
            System.out.println(resultSet.getObject("company_id") + "--" +resultSet.getObject("company") + "--" + resultSet.getObject("number_of_employees"));
        }



        connection.close();
        statement.close();
    }
}
