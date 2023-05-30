package lt.viko.eif.vskuder.GUI.Worker;

import lt.viko.eif.vskuder.DAO.AIProgramDAO;
import lt.viko.eif.vskuder.DAO.AIProgramTypeDAO;
import lt.viko.eif.vskuder.models.AIProgramType;

import javax.swing.*;

public class DeleteWorker {
    public static boolean deleteAIProgram(int id) {
        JCheckBox checkBox = new JCheckBox("Delete AI Program?");

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(new JLabel("Delete AI Program?"));
        panel.add(checkBox);

        boolean deletedSomething = false;

        int result = JOptionPane.showConfirmDialog(null, checkBox, "Delete AI Program", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            deletedSomething = AIProgramDAO.deleteAIProgram(id);
            if (deletedSomething){
                deleteAIProgramType(id);
                deleteDescription(id);
            }
            return deletedSomething;
        } else {
            return false;
        }
    }

    public static boolean deleteAIProgramType(int id) {
        JCheckBox checkBox = new JCheckBox("Delete AI Program Type?");

        AIProgramType type = AIProgramTypeDAO.getAIProgramType(id);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(new JLabel("Delete AI Program Type " + type.getTypeName() + "?"));
        panel.add(checkBox);

        int result = JOptionPane.showConfirmDialog(null, checkBox, "Delete AI Program Type", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            return AIProgramTypeDAO.deleteAIProgramType(id);
        } else {
            return false;
        }
    }

    public static boolean deleteDescription(int id) {
        JCheckBox checkBox = new JCheckBox("Delete Description?");

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(new JLabel("Delete Description?"));
        panel.add(checkBox);

        int result = JOptionPane.showConfirmDialog(null, checkBox, "Delete Description", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            return AIProgramTypeDAO.deleteAIProgramType(id);
        } else {
            return false;
        }
    }
}
