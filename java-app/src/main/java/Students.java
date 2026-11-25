import java.sql.*;

public class Students {
    public static Connection getConn() throws SQLException {
        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/tp", "postgres", "postgres");
    }

    public static void addStudent(Connection conn, String name, double grade) throws SQLException {
        try (PreparedStatement pstmt = conn.prepareStatement("INSERT INTO students (name, grade) VALUES (?, ?)")) {
            pstmt.setString(1, name);
            pstmt.setDouble(2, grade);
            pstmt.executeUpdate();
        }
    }

    public static Double getStudent(Connection conn, String name) throws SQLException {
        try (PreparedStatement pstmt = conn.prepareStatement("SELECT grade FROM students WHERE name = ?")) {
            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) return rs.getDouble("grade");
            return null;
        }
    }

    public static Double averageGrade(Connection conn) throws SQLException {
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery("SELECT AVG(grade) FROM students")) {
            if (rs.next()) return rs.getDouble(1);
            return null;
        }
    }
}
