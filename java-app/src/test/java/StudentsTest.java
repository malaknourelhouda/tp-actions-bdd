import org.junit.jupiter.api.*;
import java.sql.*;
import static org.junit.jupiter.api.Assertions.*;

public class StudentsTest {
    static Connection conn;

    @BeforeAll
    public static void setup() throws SQLException { conn = Students.getConn(); }

    @BeforeEach
    public void clear() throws SQLException {
        try (Statement stmt = conn.createStatement()) { stmt.execute("TRUNCATE students RESTART IDENTITY"); }
    }

    @Test
    public void testAddAndGet() throws SQLException {
        Students.addStudent(conn, "Alice", 15);
        assertEquals(15.0, Students.getStudent(conn, "Alice"));
    }

    @Test
    public void testGetMissing() throws SQLException {
        assertNull(Students.getStudent(conn, "Inconnu"));
    }

    @Test
    public void testMoyenne() throws SQLException {
        Students.addStudent(conn, "Amel", 10);
        Students.addStudent(conn, "Karim", 14);
        assertEquals(12.0, Students.averageGrade(conn));
    }
}
