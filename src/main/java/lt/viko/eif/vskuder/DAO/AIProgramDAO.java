package lt.viko.eif.vskuder.DAO;

import lt.viko.eif.vskuder.models.AIProgram;
import lt.viko.eif.vskuder.models.AIProgramType;
import lt.viko.eif.vskuder.models.Description;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AIProgramDAO extends DAO{
    public static AIProgram getAIProgram(int id){
        String sql = "Select *" +
                "from AIPrograms" +
                "where ProgramID = " + id;

        AIProgram o = null;

        try (Connection conn = DriverManager.getConnection(url, user, password)){
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                o = new AIProgram();
                o.setProgramID(rs.getInt("ProgramID"));
                o.setProgramName(rs.getString("ProgramName"));
                o.setProgramType(AIProgramTypeDAO.getAIProgramType(rs.getInt("ProgramType")));
                o.setDescription(DescriptionDAO.getDescription(rs.getInt("Description")));
            }

            return o;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static AIProgram createAIProgram(String programName, AIProgramType programType, Description description){
        String sql = "INSERT INTO AIPrograms (ProgramName, TypeID, DescriptionID) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, programName);
            stmt.setInt(2, programType.getTypeID());
            stmt.setInt(3, description.getDescriptionID());

            int rowsInserted = stmt.executeUpdate();

            if (rowsInserted > 0) {
                AIProgram newProgram = new AIProgram();
                newProgram.setProgramName(programName);
                newProgram.setProgramType(programType);
                newProgram.setDescription(description);
                return newProgram;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean deleteAIProgram(int id) {
        String sql = "DELETE FROM AIPrograms WHERE ProgramID = ?";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean updateAIProgram(AIProgram program) {
        String sql = "UPDATE AIPrograms SET ProgramName = ?, TypeID = ?, DescriptionID = ? WHERE ProgramID = ?";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, program.getProgramName());
            stmt.setInt(2, program.getProgramType().getTypeID());
            stmt.setInt(3, program.getDescription().getDescriptionID());
            stmt.setInt(4, program.getProgramID());

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<AIProgram> getAllAIPrograms() {
        List<AIProgram> programs = new ArrayList();
        String sql = "SELECT * FROM AIPrograms";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                AIProgram program = new AIProgram();
                program.setProgramID(rs.getInt("ProgramID"));
                program.setProgramName(rs.getString("ProgramName"));

                int typeId = rs.getInt("TypeID");
                AIProgramType type = AIProgramTypeDAO.getAIProgramType(typeId);
                program.setProgramType(type);

                int descriptionId = rs.getInt("DescriptionID");
                Description description = DescriptionDAO.getDescription(descriptionId);
                program.setDescription(description);

                programs.add(program);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return programs;
    }
}