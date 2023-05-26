import java.sql.*;

public class ExecuteQuery01 {

    public static void main(String[] args) throws SQLException {

        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "364136");
        Statement statement = connection.createStatement();


        //1. Örnek:  region id'si 1 olan "country name" değerlerini çağırın.

        System.out.println("\n===== 2. Örnek =====\n");


        String sql1 = "select country_name from countries where region_id=1;";
        boolean r1 = statement.execute(sql1);
        System.out.println("r1 = " + r1);//true==> DQL ıle data cagırıyoruz


        //Datayı cagırıp okumak ıcın  executeQuery methodunu kullanmalıyız.execute() methodu sadece true yada false doner.
        ResultSet resultSet = statement.executeQuery(sql1);

        while (resultSet.next()){
            System.out.println(resultSet.getString(1));//sutun ısmını de koyabılırız ındexi de

        }

        //2.Örnek: "region_id"nin 2'den büyük olduğu "country_id" ve "country_name" değerlerini çağırın.

        System.out.println("\n===== 2. Örnek =====\n");

        String sql2 ="select country_id,country_name from countries where region_id >2";
        ResultSet resultSet2= statement.executeQuery(sql2);

        while(resultSet2.next()){
            System.out.println(resultSet2.getString(1)+ "--"+ resultSet2.getString(2));

        }

        //3.Örnek: "number_of_employees" değeri en düşük olan satırın tüm değerlerini çağırın.

        System.out.println("\n===== 3. Örnek =====\n");

        String sql3 ="select * from companies where number_of_employees = (select min(number_of_employees)  from companies)";
       ResultSet resultSet3 = statement.executeQuery(sql3);
       //resultSet3.next();
       //System.out.println("resultSet3.getInt(2) = " + resultSet3.getObject(2));

       while(resultSet3.next()){
           System.out.println(resultSet3.getObject(1)+ "--"+ resultSet3.getObject(2)+"--"+ resultSet3.getObject(3));
        }

       connection.close();
       statement.close();





    }
}
