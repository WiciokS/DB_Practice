package lt.viko.eif.vskuder.DAO;

import lt.viko.eif.vskuder.models.Description;
import lt.viko.eif.vskuder.models.Policy;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PolicyDAO extends DAO{
    public static Policy getPolicy(int policyID) {
        String sql = "SELECT * FROM policy WHERE policyID = " + policyID;
        Policy policy = new Policy();

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                policy.setPolicyID(rs.getInt("policyID"));
                policy.setPolicyName(rs.getString("policyName"));
                policy.setPolicyDescription(DescriptionDAO.getDescription(rs.getInt("Description")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return policy;
    }

    public static Policy createPolicy(String policyName, Description policyDescription){
        String sql = "INSERT INTO Policies (PolicyName, PolicyDescription) VALUES (?, ?)";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, policyName);
            stmt.setInt(2, policyDescription.getDescriptionID());

            int rowsInserted = stmt.executeUpdate();
            if(rowsInserted > 0) {
                Policy newPolicy = new Policy();
                newPolicy.setPolicyName(policyName);
                newPolicy.setPolicyDescription(policyDescription);
                return newPolicy;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean deletePolicy(int policyId) {
        String sql = "DELETE FROM Policies WHERE PolicyID = ?";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, policyId);

            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean updatePolicy(Policy policy) {
        String sql = "UPDATE Policies SET PolicyName = ?, PolicyDescription = ? WHERE PolicyID = ?";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, policy.getPolicyName());
            stmt.setInt(2, policy.getPolicyDescription().getDescriptionID());
            stmt.setInt(3, policy.getPolicyID());

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<Policy> getAllPolicies() {
        List<Policy> policies = new ArrayList();
        String sql = "SELECT * FROM Policies";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Policy policy = new Policy();
                policy.setPolicyID(rs.getInt("PolicyID"));
                policy.setPolicyName(rs.getString("PolicyName"));
                Description description = DescriptionDAO.getDescription(rs.getInt("PolicyDescription"));
                policy.setPolicyDescription(description);
                policies.add(policy);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return policies;
    }
}
