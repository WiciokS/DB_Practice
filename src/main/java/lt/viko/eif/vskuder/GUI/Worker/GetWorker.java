package lt.viko.eif.vskuder.GUI.Worker;

import lt.viko.eif.vskuder.DAO.*;
import lt.viko.eif.vskuder.models.*;

import java.util.List;

public class GetWorker {
    public static AIProgram getAIProgram(int id) {
        return AIProgramDAO.getAIProgram(id);
    }

    public static AIProgramRating getAIProgramRating(int id) {
        return AIProgramRatingDAO.getAIProgramRating(id);
    }

    public static AIProgramReview getAIProgramReview(int id) {
        return AIProgramReviewDAO.getAIProgramReview(id);
    }

    public static AIProgramType getAIProgramType(int id) {
        return AIProgramTypeDAO.getAIProgramType(id);
    }

    public static AuditTrail getAuditTrail(int id) {
        return AuditTrailDAO.getAuditTrail(id);
    }

    public static Description getDescription(int id) {
        return DescriptionDAO.getDescription(id);
    }

    public static Developer getDeveloper(int id) {
        return DeveloperDAO.getDeveloper(id);
    }

    public static GlobalRating getGlobalRating(int id) {
        return GlobalRatingDAO.getGlobalRating(id);
    }

    public static Guest getGuest(int id) {
        return GuestDAO.getGuest(id);
    }

    public static Permission getPermission(int id) {
        return PermissionDAO.getPermission(id);
    }

    public static Policy getPolicy(int id) {
        return PolicyDAO.getPolicy(id);
    }

    public static ProgramDeveloper getProgramDeveloper(int id) {
        return ProgramDeveloperDAO.getProgramDeveloper(id);
    }

    public static ProgramPolicy getProgramPolicy(int id) {
        return ProgramPolicyDAO.getProgramPolicy(id);
    }

    public static Role getRole(int id) {
        return RoleDAO.getRoleById(id);
    }

    public static RolePermission getRolePermission(int id) {
        return RolePermissionDAO.getRolePermission(id);
    }

    public static User getUser(int id) {
        return UserDAO.getUser(id);
    }

    public static UserRole getUserRole(int id) {
        return UserRoleDAO.getUserRole(id);
    }

    public static String getGuestProgramList() {
        List<GuestProgramAssociation> list = ProgramListGuestDAO.getGuestProgramAssociations();
        StringBuilder sb = new StringBuilder();
        for (GuestProgramAssociation association : list) {
            sb.append(association.toString()).append("\n");
        }
        return sb.toString();
    }

    public static String getRegisteredUserProgramList() {
        List<RegisteredUserProgramAssociation> list = ProgramListRegisteredDAO.getProgramListRegistered();
        StringBuilder sb = new StringBuilder();
        for (RegisteredUserProgramAssociation association : list) {
            sb.append(association.toString()).append("\n");
        }
        return sb.toString();
    }

    public static String getPremiumUserProgramList() {
        List<PremiumUserProgramAssociation> list = ProgramListPremiumDAO.getPremiumUserProgramAssociations();
        StringBuilder sb = new StringBuilder();
        for (PremiumUserProgramAssociation association : list) {
            sb.append(association.toString()).append("\n");
        }
        return sb.toString();
    }
}
