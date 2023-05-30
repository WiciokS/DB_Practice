package lt.viko.eif.vskuder.DAO;

import lt.viko.eif.vskuder.models.ProgramDeveloper;

import java.sql.*;

public class ProgramDeveloperDAO extends DAO{
    public final String database_name = "ProgramDevelopers";
    public static ProgramDeveloper getProgramDeveloper(int id) {
        String sql = "Select * " +
                "from ProgramDevelopers " +
                "where ProgramID = " + id;

        ProgramDeveloper o = null;

        try (Connection conn = DriverManager.getConnection(url, user, password)){
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                o = new ProgramDeveloper();
                o.setProgramID(AIProgramDAO.getAIProgram(rs.getInt("ProgramID")));
                o.setDeveloperID(DeveloperDAO.getDeveloper(rs.getInt("DeveloperID")));
            }

            return o;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static ProgramDeveloper createProgramDeveloper(ProgramDeveloper programDeveloper) {
        String sql = "INSERT INTO ProgramDevelopers (ProgramID, DeveloperID) VALUES (?, ?)";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, programDeveloper.getProgramID().getProgramID());
            stmt.setInt(2, programDeveloper.getDeveloperID().getDeveloperID());

            int affectedRows = stmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating program developer failed, no rows affected.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return programDeveloper;
    }

    public static boolean deleteProgramDeveloper(int programID, int developerID) {
        String sql = "DELETE FROM ProgramDevelopers WHERE ProgramID = ? AND DeveloperID = ?";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, programID);
            stmt.setInt(2, developerID);

            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
