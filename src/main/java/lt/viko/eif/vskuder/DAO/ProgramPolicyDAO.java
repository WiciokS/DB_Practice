package lt.viko.eif.vskuder.DAO;

import lt.viko.eif.vskuder.models.ProgramPolicy;
import lt.viko.eif.vskuder.models.AIProgram;
import lt.viko.eif.vskuder.models.Policy;

import java.sql.*;

public class ProgramPolicyDAO extends DAO{

    public final String database_name = "ProgramPolicies";
    public static ProgramPolicy getProgramPolicy(int id) {
        String sql = "Select * " +
                "from ProgramPolicies " +
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

    public static ProgramPolicy createProgramPolicy(AIProgram aiProgram, Policy policy) {
        String sql = "INSERT INTO ProgramPolicies (ProgramID, PolicyID) VALUES (?, ?)";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, aiProgram.getProgramID());
            stmt.setInt(2, policy.getPolicyID());
            int rowsInserted = stmt.executeUpdate();

            if (rowsInserted > 0) {
                ProgramPolicy newProgramPolicy = new ProgramPolicy();
                newProgramPolicy.setProgramID(aiProgram);
                newProgramPolicy.setPolicyID(policy);
                return newProgramPolicy;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static boolean deleteProgramPolicy(int programID, int policyID) {
        String sql = "DELETE FROM ProgramPolicies WHERE ProgramID = ? AND PolicyID = ?";

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
