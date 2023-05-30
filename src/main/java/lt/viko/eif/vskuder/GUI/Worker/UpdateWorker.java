package lt.viko.eif.vskuder.GUI.Worker;

import lt.viko.eif.vskuder.DAO.AIProgramDAO;
import lt.viko.eif.vskuder.DAO.AIProgramTypeDAO;
import lt.viko.eif.vskuder.DAO.DescriptionDAO;
import lt.viko.eif.vskuder.models.AIProgram;
import lt.viko.eif.vskuder.models.AIProgramType;
import lt.viko.eif.vskuder.models.Description;

import javax.swing.*;

public class UpdateWorker {
    public static boolean updateAIProgram(int id) {
        JTextField name = new JTextField();
        JTextField type = new JTextField();
        JTextField description = new JTextField();

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(new JLabel("Name:"));
        panel.add(name);
        panel.add(new JLabel("Type:"));
        panel.add(type);
        panel.add(new JLabel("Description:"));
        panel.add(description);

        int result = JOptionPane.showConfirmDialog(null, panel, "Update AI Program",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            AIProgram check = AIProgramDAO.getAIProgram(id);
            AIProgram newAIProgram = new AIProgram();
            newAIProgram.setProgramID(id);
            boolean updated = false;
            if (name.getText().equals("")) {
                newAIProgram.setProgramName(check.getProgramName());
            } else {
                newAIProgram.setProgramName(name.getText());
                updated = true;
            }
            if (type.getText().equals("")) {
                newAIProgram.setProgramType(check.getProgramType());
            } else {
                updated = true;
                if (type.getText().equals("new")){
                    newAIProgram.setProgramType(CreateWorker.createAIProgramType());
                } else {
                    newAIProgram.setProgramType(AIProgramTypeDAO.getAIProgramType(Integer.parseInt(type.getText())));
                }
            }
            if (description.getText().equals("")) {
                newAIProgram.setDescription(check.getDescription());
            } else {
                updated = true;
                if (description.getText().equals("new")){
                    newAIProgram.setDescription(CreateWorker.createDescription());
                } else {
                    newAIProgram.setDescription(DescriptionDAO.getDescription(Integer.parseInt(description.getText())));
                }
            }
            AIProgramDAO.updateAIProgram(newAIProgram);
            return updated;
        } else {
            return false;
        }
    }

    public static boolean updateAIProgramType(int id) {
        JTextField name = new JTextField();

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(new JLabel("Name:"));
        panel.add(name);

        int result = JOptionPane.showConfirmDialog(null, panel, "Update AI Program Type",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            AIProgramType check = AIProgramTypeDAO.getAIProgramType(id);
            AIProgramType newAIProgramType = new AIProgramType();
            boolean updated = false;
            if (name.getText().equals("")) {
                newAIProgramType.setTypeName(check.getTypeName());
            } else {
                newAIProgramType.setTypeName(name.getText());
                updated = true;
            }
            AIProgramTypeDAO.updateAIProgramType(newAIProgramType);
            return updated;
        } else {
            return false;
        }
    }

    public static boolean updateDescription(int id) {
        JTextField description = new JTextField();

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(new JLabel("Description:"));
        panel.add(description);

        int result = JOptionPane.showConfirmDialog(null, panel, "Update Description",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            Description check = DescriptionDAO.getDescription(id);
            Description newDescription = new Description();
            boolean updated = false;
            if (description.getText().equals("")) {
                newDescription.setDescriptionText(check.getDescriptionText());
            } else {
                newDescription.setDescriptionText(description.getText());
                updated = true;
            }
            DescriptionDAO.updateDescription(newDescription);
            return updated;
        } else {
            return false;
        }
    }
}
