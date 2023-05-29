package lt.viko.eif.vskuder.DAO;

import lt.viko.eif.vskuder.models.ProgramPolicy;

import java.sql.*;

public class ProgramPolicyDAO extends DAO{
    public static ProgramPolicy getProgramPolicy(int id) {
        String sql = "Select *" +
                "from ProgramPolicies" +
                "where ProgramID = " + id;

        ProgramPolicy o = null;

        try (Connection conn = DriverManager.getConnection(url, user, password)){
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                o = new ProgramPolicy();
                o.setProgramID(AIProgramDAO.getAIProgram(rs.getInt("ProgramID")));
                o.setPolicyID(PolicyDAO.getPolicy(rs.getInt("PolicyID")));
            }

            return o;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static ProgramPolicy createProgramPolicy(ProgramPolicy programPolicy) {
        String sql = "INSERT INTO ProgramPolicy (ProgramID, PolicyID) VALUES (?, ?)";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, programPolicy.getProgramID().getProgramID());
            stmt.setInt(2, programPolicy.getPolicyID().getPolicyID());

            int affectedRows = stmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating programPolicy failed, no rows affected.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return programPolicy;
    }

    public static boolean deleteProgramPolicy(int programID, int policyID) {
        String sql = "DELETE FROM ProgramPolicy WHERE ProgramID = ? AND PolicyID = ?";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, programID);
            stmt.setInt(2, policyID);

            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
