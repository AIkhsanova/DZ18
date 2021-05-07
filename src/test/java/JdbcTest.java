
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.fintech.qa.homework.utils.BeforeUtils;
import ru.fintech.qa.homework.utils.db.jdbc.JdbcClient;
import ru.fintech.qa.homework.utils.db.jdbc.JdbcDbService;

import java.sql.Connection;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class JdbcTest {

    private static Connection connection;

    @BeforeAll
    public static void beforeAll() {
        BeforeUtils.createData();

    }

    @BeforeEach
    public final void getConnection() {
        connection = new JdbcClient().getConnection();
    }

    @AfterEach
    public final void closeConnection() {
        try {
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    @Test
    public void animal10Test() {
        Assertions.assertEquals(10, new JdbcDbService().executeQueryCount("id", "select * from animal", connection));
    }


    @Test
    public void animalId10Test() {
        List<Integer> values = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        values.forEach(value -> Assertions.assertEquals(0, new JdbcDbService()
                .executeUpdate("insert into animal(id) values (" + value + ")", connection)));

    }

    @Test
    public void workmanNameNullTest() {
        String name = null;
        Assertions.assertEquals(0, new JdbcDbService().
                executeUpdate("insert into workman(id, \"name\") values (7," + name + ")", connection));
    }

    @Test
    public void placesAdd6Test() {
        new JdbcDbService().executeUpdate("insert into places(id) values (6)", connection);
        Assertions.assertEquals(6, new JdbcDbService().
                executeQueryCount("id", "select * from places", connection));
    }

    @Test
    public void zooTest() {
        Assertions.assertEquals(3, new JdbcDbService().executeQueryCount("id", "select * from zoo", connection));
        String name = new JdbcDbService().executeQueryGetColumn("name", "select * from zoo where id=1", connection);
        Assertions.assertEquals("Центральный", name);
        name = new JdbcDbService().executeQueryGetColumn("name", "select * from zoo where id=2", connection);
        Assertions.assertEquals("Северный", name);
        name = new JdbcDbService().executeQueryGetColumn("name", "select * from zoo where id=3", connection);
        Assertions.assertEquals("Западный", name);
    }

}
