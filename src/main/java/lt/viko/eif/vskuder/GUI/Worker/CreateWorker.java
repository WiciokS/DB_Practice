package lt.viko.eif.vskuder.GUI.Worker;

import lt.viko.eif.vskuder.DAO.*;
import lt.viko.eif.vskuder.GUI.MainForm;
import lt.viko.eif.vskuder.models.*;

import javax.swing.*;
import java.awt.*;

public class CreateWorker {
    public static UserRole createUserRole() {
        JTextField userID = new JTextField();
        JTextField roleID = new JTextField();

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("User ID:"));
        panel.add(userID);
        panel.add(new JLabel("Role ID:"));
        panel.add(roleID);

        int result = JOptionPane.showConfirmDialog(null, panel, "Create User Role",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            return UserRoleDAO.createUserRole(
                    UserDAO.getUser(Integer.parseInt(userID.getText())),
                    RoleDAO.getRoleById(Integer.parseInt(roleID.getText()))
            );
        } else {
            return null;
        }
    }

    public static User createUser() {
        JTextField username = new JTextField();
        JTextField password = new JTextField();
        JTextField email = new JTextField();

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Username:"));
        panel.add(username);
        panel.add(new JLabel("Password:"));
        panel.add(password);
        panel.add(new JLabel("Email:"));
        panel.add(email);

        int result = JOptionPane.showConfirmDialog(null, panel, "Create User",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            return UserDAO.createUser(username.getText(), email.getText(), password.getText());
        } else {
            return null;
        }
    }

    public static RolePermission createRolePermission() {
        JTextField roleID = new JTextField();
        JTextField permissionID = new JTextField();

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Role ID:"));
        panel.add(roleID);
        panel.add(new JLabel("Permission ID:"));
        panel.add(permissionID);

        int result = JOptionPane.showConfirmDialog(null, panel, "Create Role Permission",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            return RolePermissionDAO.createRolePermission(
                    RoleDAO.getRoleById(Integer.parseInt(roleID.getText())),
                    PermissionDAO.getPermission(Integer.parseInt(permissionID.getText()))
            );
        } else {
            return null;
        }
    }

    public static Role createRole() {
        JTextField roleName = new JTextField();

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Role Name:"));
        panel.add(roleName);

        int result = JOptionPane.showConfirmDialog(null, panel, "Create Role",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            return RoleDAO.createRole(roleName.getText());
        } else {
            return null;
        }
    }

    public static ProgramPolicy createProgramPolicy() {
        JTextField programID = new JTextField();
        JTextField policyID = new JTextField();

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Program ID:"));
        panel.add(programID);
        panel.add(new JLabel("Policy ID:"));
        panel.add(policyID);

        int result = JOptionPane.showConfirmDialog(null, panel, "Create Program Policy",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            return ProgramPolicyDAO.createProgramPolicy(
                    AIProgramDAO.getAIProgram(Integer.parseInt(programID.getText())),
                    PolicyDAO.getPolicy(Integer.parseInt(policyID.getText()))
            );
        } else {
            return null;
        }
    }

    public static ProgramDeveloper createProgramDeveloper() {
        JTextField programID = new JTextField();
        JTextField developerID = new JTextField();

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Program ID:"));
        panel.add(programID);
        panel.add(new JLabel("Developer ID:"));
        panel.add(developerID);

        int result = JOptionPane.showConfirmDialog(null, panel, "Create Program Developer",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            ProgramDeveloper o = new ProgramDeveloper();
            o.setProgramID(AIProgramDAO.getAIProgram(Integer.parseInt(programID.getText())));
            o.setDeveloperID(DeveloperDAO.getDeveloper(Integer.parseInt(developerID.getText())));
            return ProgramDeveloperDAO.createProgramDeveloper(o);
        } else {
            return null;
        }
    }

    public static Policy createPolicy() {
        JTextField policyName = new JTextField();
        JTextField policyDescription = new JTextField();

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Policy Name:"));
        panel.add(policyName);
        panel.add(new JLabel("Policy Description ID:"));
        panel.add(policyDescription);

        int result = JOptionPane.showConfirmDialog(null, panel, "Create Policy",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            Description d;
            if (policyDescription.getText().equals("new")){
                d = createDescription();
            } else {
                d = DescriptionDAO.getDescription(Integer.parseInt(policyDescription.getText()));
            }
            return PolicyDAO.createPolicy(policyName.getText(), d);
        } else {
            return null;
        }
    }

    public static Permission createPermission() {
        JTextField permissionName = new JTextField();

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Permission Name:"));
        panel.add(permissionName);

        int result = JOptionPane.showConfirmDialog(null, panel, "Create Permission",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            return PermissionDAO.createPermission(permissionName.getText());
        } else {
            return null;
        }
    }

    public static PremiumUserProgramAssociation createPremiumUserProgramAssociation() {
        JTextField programID = new JTextField();
        JTextField userID = new JTextField();

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Program ID:"));
        panel.add(programID);
        panel.add(new JLabel("User ID:"));
        panel.add(userID);

        int result = JOptionPane.showConfirmDialog(null, panel, "Create Premium User Program Association",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            return ProgramListPremiumDAO.createPremiumUserProgramAssociation(
                    UserDAO.getUser(Integer.parseInt(userID.getText())),
                    AIProgramDAO.getAIProgram(Integer.parseInt(programID.getText()))
            );
        } else {
            return null;
        }
    }

    public static RegisteredUserProgramAssociation createRegisteredUserProgramAssociation() {
        JTextField programID = new JTextField();
        JTextField userID = new JTextField();

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Program ID:"));
        panel.add(programID);
        panel.add(new JLabel("User ID:"));
        panel.add(userID);

        int result = JOptionPane.showConfirmDialog(null, panel, "Create Registered User Program Association",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            return ProgramListRegisteredDAO.createRegisteredUserProgramAssociation(
                    UserDAO.getUser(Integer.parseInt(userID.getText())),
                    AIProgramDAO.getAIProgram(Integer.parseInt(programID.getText()))
            );
        } else {
            return null;
        }
    }

    public static GuestProgramAssociation createGuestProgramAssociation() {
        JTextField programID = new JTextField();
        JTextField userID = new JTextField();

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Program ID:"));
        panel.add(programID);
        panel.add(new JLabel("Guest ID:"));
        panel.add(userID);

        int result = JOptionPane.showConfirmDialog(null, panel, "Create Guest Program Association",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            GuestProgramAssociation o = new GuestProgramAssociation();
            o.setProgramID(AIProgramDAO.getAIProgram(Integer.parseInt(programID.getText())));
            o.setGuestID(GuestDAO.getGuest(Integer.parseInt(userID.getText())));
            return ProgramListGuestDAO.createGuestProgramAssociation(o);
        } else {
            return null;
        }
    }

    public static Guest createGuest() {
        return GuestDAO.createGuest(new Guest());
    }

    public static GlobalRating createGlobalRating() {
        JTextField rating = new JTextField();
        JTextField programID = new JTextField();

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Rating:"));
        panel.add(rating);
        panel.add(new JLabel("Program ID:"));
        panel.add(programID);

        int result = JOptionPane.showConfirmDialog(null, panel, "Create Global Rating",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            return GlobalRatingDAO.createGlobalRating(
                    AIProgramDAO.getAIProgram(Integer.parseInt(programID.getText())),
                    Float.parseFloat(rating.getText())
            );
        } else {
            return null;
        }
    }

    public static Developer createDeveloper() {
        JTextField name = new JTextField();
        JTextField email = new JTextField();

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Name:"));
        panel.add(name);
        panel.add(new JLabel("Email:"));
        panel.add(email);

        int result = JOptionPane.showConfirmDialog(null, panel, "Create Developer",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            return DeveloperDAO.createDeveloper(name.getText(), email.getText());
        } else {
            return null;
        }
    }

    public static AuditTrail createAuditTrail() {
        JTextField action = new JTextField();
        JTextField userID = new JTextField();
        JTextField timestamp = new JTextField();

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Action:"));
        panel.add(action);
        panel.add(new JLabel("User ID:"));
        panel.add(userID);
        panel.add(new JLabel("Timestamp:"));
        panel.add(timestamp);

        int result = JOptionPane.showConfirmDialog(null, panel, "Create Audit Trail",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            AuditTrail o = new AuditTrail();
            o.setAction(action.getText());
            o.setRegisteredID(UserDAO.getUser(Integer.parseInt(userID.getText())));
            o.setTimestamp(timestamp.getText());
            return AuditTrailDAO.createAuditTrail(o);
        } else {
            return null;
        }
    }

    public static AIProgramReview createAIProgramReview() {
        JTextField reviewField = new JTextField();
        JTextField programIdField = new JTextField();
        JTextField userIdField = new JTextField();

        JPanel fieldsPanel = new JPanel(new GridLayout(0, 1));
        fieldsPanel.add(new JLabel("Review:"));
        fieldsPanel.add(reviewField);
        fieldsPanel.add(new JLabel("Program ID:"));
        fieldsPanel.add(programIdField);
        fieldsPanel.add(new JLabel("User ID:"));
        fieldsPanel.add(userIdField);

        int result = JOptionPane.showConfirmDialog(null, fieldsPanel, "Enter AIProgramReview Fields",
                JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String reviewInput = reviewField.getText();
            String programIdInput = programIdField.getText();
            String userIdInput = userIdField.getText();

            String review = reviewInput;

            AIProgram program;
            if (programIdInput.equals("new")){
                program = createAIProgram();
            } else {
                program = AIProgramDAO.getAIProgram(Integer.parseInt(programIdInput));
            }

            User user;
            if (userIdInput.equals("new")){
                user = createUser();
            } else {
                user = UserDAO.getUser(Integer.parseInt(userIdInput));
            }

            AIProgramReview aiProgramReview = new AIProgramReview();

            aiProgramReview.setReview(review);
            aiProgramReview.setProgramID(program);
            aiProgramReview.setUserID(user);
            return AIProgramReviewDAO.createAIProgramReview(aiProgramReview);
        } else {
            return null;
        }
    }

    public static AIProgramRating createAIProgramRating() {
        JTextField ratingField = new JTextField();
        JTextField programIdField = new JTextField();
        JTextField userIdField = new JTextField();

        JPanel fieldsPanel = new JPanel(new GridLayout(0, 1));
        fieldsPanel.add(new JLabel("Rating:"));
        fieldsPanel.add(ratingField);
        fieldsPanel.add(new JLabel("Program ID:"));
        fieldsPanel.add(programIdField);
        fieldsPanel.add(new JLabel("User ID:"));
        fieldsPanel.add(userIdField);

        int result = JOptionPane.showConfirmDialog(null, fieldsPanel, "Enter AIProgramRating Fields",
                JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String ratingInput = ratingField.getText();
            String programIdInput = programIdField.getText();
            String userIdInput = userIdField.getText();

            int rating = Integer.parseInt(ratingInput);
            AIProgram program;
            if (programIdInput.equals("new")){
                program = createAIProgram();
            } else {
                program = AIProgramDAO.getAIProgram(Integer.parseInt(programIdInput));
            }

            User user;
            if (userIdInput.equals("new")){
                user = createUser();
            } else {
                user = UserDAO.getUser(Integer.parseInt(userIdInput));
            }

            AIProgramRating o = new AIProgramRating();
            o.setRating(rating);
            o.setProgramID(program);
            o.setUserID(user);

            return AIProgramRatingDAO.createAIProgramRating(o);
        } else {
            return null;
        }
    }

    public static AIProgram createAIProgram() {
        JTextField programNameField = new JTextField();
        JTextField programTypeField = new JTextField();
        JTextField descriptionField = new JTextField();

        JPanel fieldsPanel = new JPanel(new GridLayout(0, 1));
        fieldsPanel.add(new JLabel("Program Name:"));
        fieldsPanel.add(programNameField);
        fieldsPanel.add(new JLabel("Program Type ID:"));
        fieldsPanel.add(programTypeField);
        fieldsPanel.add(new JLabel("Description ID:"));
        fieldsPanel.add(descriptionField);

        int result = JOptionPane.showConfirmDialog(null, fieldsPanel, "Enter AIProgram Fields",
                JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String programName = programNameField.getText();
            String programTypeInput = programTypeField.getText();
            String descriptionInput = descriptionField.getText();

            AIProgramType programType;
            if (programTypeInput.equals("new")) {
                programType = createAIProgramType();
            } else {
                int programTypeID = Integer.parseInt(programTypeInput);
                programType = AIProgramTypeDAO.getAIProgramType(programTypeID);
            }

            Description description;
            if (descriptionInput.equals("new")) {
                description = createDescription();
            } else {
                int descriptionID = Integer.parseInt(descriptionInput);
                description = DescriptionDAO.getDescription(descriptionID);
            }

            return AIProgramDAO.createAIProgram(programName, programType, description);
        } else {
            return null;
        }
    }

    public static Description createDescription() {
        JTextField descriptionField = new JTextField();

        JPanel fieldsPanel = new JPanel(new GridLayout(0, 1));
        fieldsPanel.add(new JLabel("Description:"));
        fieldsPanel.add(descriptionField);

        int result = JOptionPane.showConfirmDialog(null, fieldsPanel, "Enter Description Fields",
                JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String descriptionInput = descriptionField.getText();
            return DescriptionDAO.createDescription(descriptionInput);
        } else {
            return null;
        }
    }

    public static AIProgramType createAIProgramType() {
        JTextField programTypeField = new JTextField();

        JPanel fieldsPanel = new JPanel(new GridLayout(0, 1));
        fieldsPanel.add(new JLabel("Program Type:"));
        fieldsPanel.add(programTypeField);

        int result = JOptionPane.showConfirmDialog(null, fieldsPanel, "Enter AIProgramType Fields",
                JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String programTypeInput = programTypeField.getText();
            return AIProgramTypeDAO.createAIProgramType(programTypeInput);
        } else {
            return null;
        }
    }
}
