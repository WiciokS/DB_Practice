package lt.viko.eif.vskuder.GUI;

import lt.viko.eif.vskuder.GUI.Worker.GetWorker;
import lt.viko.eif.vskuder.GUI.Worker.UpdateWorker;
import lt.viko.eif.vskuder.GUI.Worker.DeleteWorker;
import lt.viko.eif.vskuder.sql.UniqueMethods;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

import static lt.viko.eif.vskuder.GUI.Worker.CreateWorker.*;

public class MainForm {
    private JTextArea textField1;
    private JPanel panel1;
    private JButton getButton;
    private JButton createButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton searchButton;
    private JButton countButton;
    private JButton existsButton;
    private JButton joinButton;
    private JButton orderByButton;

    public MainForm() {
        JFrame frame = new JFrame("MainForm");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(500, 700);
        textField1.setMaximumSize(textField1.getPreferredSize());
        frame.setVisible(true);
        getButton.addActionListener(e -> getButtonFunc());
        createButton.addActionListener(e -> createButtonFunc());
        updateButton.addActionListener(e -> updateButtonFunc());
        deleteButton.addActionListener(e -> deleteButtonFunc());
        searchButton.addActionListener(e -> searchButtonFunc());
        countButton.addActionListener(e -> countButtonFunc());
        existsButton.addActionListener(e -> existButtonFunc());
        joinButton.addActionListener(e -> joinButtonFunc());
        orderByButton.addActionListener(e -> orderByButtonFunc());
    }

    private void orderByButtonFunc() {
        try {
            UniqueMethods uniqueMethods = new UniqueMethods();
            String tableName = JOptionPane.showInputDialog("Enter table name");
            String columnName = JOptionPane.showInputDialog("Enter column name");
            String ascendingStr = JOptionPane.showInputDialog("Order ascending? (true/false)");
            boolean ascending = Boolean.parseBoolean(ascendingStr);
            String result = uniqueMethods.orderBy(tableName, columnName, ascending);
            textField1.setText(result);
        } catch (SQLException ex) {
            textField1.setText("Error: " + ex.getMessage());
        }
    }

    private void joinButtonFunc() {
        try {
            UniqueMethods uniqueMethods = new UniqueMethods();
            String table1 = JOptionPane.showInputDialog("Enter first table name");
            String table2 = JOptionPane.showInputDialog("Enter second table name");
            String table1Column = JOptionPane.showInputDialog("Enter first table column name for join");
            String table2Column = JOptionPane.showInputDialog("Enter second table column name for join");
            String result = uniqueMethods.join(table1, table2, table1Column, table2Column);
            textField1.setText(result);
        } catch (SQLException ex) {
            textField1.setText("Error: " + ex.getMessage());
        }
    }

    private void existButtonFunc() {
        try {
            UniqueMethods uniqueMethods = new UniqueMethods();
            String tableName = JOptionPane.showInputDialog("Enter table name");
            String columnName = JOptionPane.showInputDialog("Enter column name");
            String value = JOptionPane.showInputDialog("Enter value");
            boolean exists = uniqueMethods.exists(tableName, columnName, value);
            textField1.setText("Exists: " + exists);
        } catch (SQLException ex) {
            textField1.setText("Error: " + ex.getMessage());
        }
    }

    private void countButtonFunc() {
        try {
            UniqueMethods uniqueMethods = new UniqueMethods();
            String tableName = JOptionPane.showInputDialog("Enter table name");
            int count = uniqueMethods.count(tableName);
            textField1.setText("Count: " + count);
        } catch (SQLException ex) {
            textField1.setText("Error: " + ex.getMessage());
        }
    }

    private void searchButtonFunc() {
        try {
            UniqueMethods uniqueMethods = new UniqueMethods();
            String tableName = JOptionPane.showInputDialog("Enter table name");
            String columnName = JOptionPane.showInputDialog("Enter column name");
            String searchValue = JOptionPane.showInputDialog("Enter search value");
            String result = uniqueMethods.search(tableName, columnName, searchValue);
            textField1.setText(result);
        } catch (SQLException ex) {
            textField1.setText("Error: " + ex.getMessage());
        }
    }

    private void deleteButtonFunc() {
        Object[] options = {
                "AIProgram",
                "AIProgramRating",
                "AIProgramReview",
                "AIProgramType",
                "AuditTrail",
                "Description",
                "Developer",
                "GlobalRating",
                "Guest",
                "Permission",
                "Policy",
                "ProgramDeveloper",
                "ProgramPolicy",
                "Role",
                "RolePermission",
                "User",
                "UserRole"
        };

        // Create a panel with a horizontal layout
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Add the retrieval options as buttons to the panel
        for (Object option : options) {
            JButton button = new JButton(option.toString());
            panel.add(button);

            // Add ActionListener to each button
            button.addActionListener(a -> {
                try {
                    int id = Integer.parseInt(JOptionPane.showInputDialog("Enter id"));

                    if (option.equals("AIProgram")) {
                        textField1.setText(String.valueOf(DeleteWorker.deleteAIProgram(id)));
                    } else if (option.equals("AIProgramType")) {
                        textField1.setText(String.valueOf(DeleteWorker.deleteAIProgramType(id)));
                    } else if (option.equals("Description")) {
                        textField1.setText(String.valueOf(DeleteWorker.deleteDescription(id)));
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                    // Close the dialog box after input is processed
                    Window window = SwingUtilities.getWindowAncestor(panel);
                    window.dispose();
                }
            });
        }

        // Create a scrollable pane for the panel
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setPreferredSize(new Dimension(400, 200));

        // Show the dialog box with the scrollable pane
        JOptionPane.showMessageDialog(null, scrollPane, "Choose table", JOptionPane.PLAIN_MESSAGE);

        // Close the dialog box after input is processed
        Window window = SwingUtilities.getWindowAncestor(panel);
        window.dispose();
    }

    private void updateButtonFunc() {
        Object[] options = {
                "AIProgram",
                "AIProgramRating",
                "AIProgramReview",
                "AIProgramType",
                "AuditTrail",
                "Description",
                "Developer",
                "GlobalRating",
                "Guest",
                "Permission",
                "Policy",
                "ProgramDeveloper",
                "ProgramPolicy",
                "Role",
                "RolePermission",
                "User",
                "UserRole"
        };

        // Create a panel with a horizontal layout
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Add the retrieval options as buttons to the panel
        for (Object option : options) {
            JButton button = new JButton(option.toString());
            panel.add(button);

            // Add ActionListener to each button
            button.addActionListener(a -> {
                try {
                    int id = Integer.parseInt(JOptionPane.showInputDialog("Enter id"));

                    if (option.equals("AIProgram")) {
                        textField1.setText(String.valueOf(UpdateWorker.updateAIProgram(id)));
                    } else if (option.equals("AIProgramType")) {
                        textField1.setText(String.valueOf(UpdateWorker.updateAIProgramType(id)));
                    } else if (option.equals("Description")) {
                        textField1.setText(String.valueOf(UpdateWorker.updateDescription(id)));
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                    // Close the dialog box after input is processed
                    Window window = SwingUtilities.getWindowAncestor(panel);
                    window.dispose();
                }
            });
        }

        // Create a scrollable pane for the panel
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setPreferredSize(new Dimension(400, 200));

        // Show the dialog box with the scrollable pane
        JOptionPane.showMessageDialog(null, scrollPane, "Choose table", JOptionPane.PLAIN_MESSAGE);

        // Close the dialog box after input is processed
        Window window = SwingUtilities.getWindowAncestor(panel);
        window.dispose();

    }

    public void getButtonFunc(){
        Object[] options = {
                "AIProgram",
                "AIProgramRating",
                "AIProgramReview",
                "AIProgramType",
                "AuditTrail",
                "Description",
                "Developer",
                "GlobalRating",
                "Guest",
                "List of AIPrograms for guests",
                "List of AIPrograms for registered users",
                "List of AIPrograms for premium users",
                "Permission",
                "Policy",
                "ProgramDeveloper",
                "ProgramPolicy",
                "Role",
                "RolePermission",
                "User",
                "UserRole"
        };

        // Create a panel with a horizontal layout
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Add the retrieval options as buttons to the panel
        for (Object option : options) {
            JButton button = new JButton(option.toString());
            panel.add(button);

            // Add ActionListener to each button
            button.addActionListener(a -> {
                try {
                    int id = Integer.parseInt(JOptionPane.showInputDialog("Enter id"));

                    if (option.equals("AIProgram")) {
                        textField1.setText(GetWorker.getAIProgram(id).toString());
                    } else if (option.equals("AIProgramRating")) {
                        textField1.setText(GetWorker.getAIProgramRating(id).toString());
                    } else if (option.equals("AIProgramReview")) {
                        textField1.setText(GetWorker.getAIProgramReview(id).toString());
                    } else if (option.equals("AIProgramType")) {
                        textField1.setText(GetWorker.getAIProgramType(id).toString());
                    } else if (option.equals("AuditTrail")) {
                        textField1.setText(GetWorker.getAuditTrail(id).toString());
                    } else if (option.equals("Description")) {
                        textField1.setText(GetWorker.getDescription(id).toString());
                    } else if (option.equals("Developer")) {
                        textField1.setText(GetWorker.getDeveloper(id).toString());
                    } else if (option.equals("GlobalRating")) {
                        textField1.setText(GetWorker.getGlobalRating(id).toString());
                    } else if (option.equals("Guest")) {
                        textField1.setText(GetWorker.getGuest(id).toString());
                    } else if (option.equals("List of AIPrograms for guests")) {
                        textField1.setText(GetWorker.getGuestProgramList());
                    } else if (option.equals("List of AIPrograms for registered users")) {
                        textField1.setText(GetWorker.getRegisteredUserProgramList());
                    } else if (option.equals("List of AIPrograms for premium users")) {
                        textField1.setText(GetWorker.getPremiumUserProgramList());
                    } else if (option.equals("Permission")) {
                        textField1.setText(GetWorker.getPermission(id).toString());
                    } else if (option.equals("Policy")) {
                        textField1.setText(GetWorker.getPolicy(id).toString());
                    } else if (option.equals("ProgramDeveloper")) {
                        textField1.setText(GetWorker.getProgramDeveloper(id).toString());
                    } else if (option.equals("ProgramPolicy")) {
                        textField1.setText(GetWorker.getProgramPolicy(id).toString());
                    } else if (option.equals("Role")) {
                        textField1.setText(GetWorker.getRole(id).toString());
                    } else if (option.equals("RolePermission")) {
                        textField1.setText(GetWorker.getRolePermission(id).toString());
                    } else if (option.equals("User")) {
                        textField1.setText(GetWorker.getUser(id).toString());
                    } else if (option.equals("UserRole")) {
                        textField1.setText(GetWorker.getUserRole(id).toString());
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                    // Close the dialog box after input is processed
                    Window window = SwingUtilities.getWindowAncestor(panel);
                    window.dispose();
                }
            });
        }

        // Create a scrollable pane for the panel
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setPreferredSize(new Dimension(400, 200));

        // Show the dialog box with the scrollable pane
        JOptionPane.showMessageDialog(null, scrollPane, "Choose table", JOptionPane.PLAIN_MESSAGE);

        // Close the dialog box after input is processed
        Window window = SwingUtilities.getWindowAncestor(panel);
        window.dispose();
    }

    public void createButtonFunc(){
        Object[] options = {
                "AIProgram",
                "AIProgramRating",
                "AIProgramReview",
                "AIProgramType",
                "AuditTrail",
                "Description",
                "Developer",
                "GlobalRating",
                "Guest",
                "List of AIPrograms for guests",
                "List of AIPrograms for registered users",
                "List of AIPrograms for premium users",
                "Permission",
                "Policy",
                "ProgramDeveloper",
                "ProgramPolicy",
                "Role",
                "RolePermission",
                "User",
                "UserRole"
        };

        // Create a panel with a vertical layout
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Add the creation options as buttons to the panel
        for (Object option : options) {
            JButton button = new JButton(option.toString());
            panel.add(button);

            // Add ActionListener to each button
            button.addActionListener(a -> {
                try {
                    if (option.equals("AIProgram")) {
                        textField1.setText(createAIProgram().toString());
                    } else if (option.equals("AIProgramRating")) {
                        textField1.setText(createAIProgramRating().toString());
                    } else if (option.equals("AIProgramReview")) {
                        textField1.setText(createAIProgramReview().toString());
                    } else if (option.equals("AIProgramType")) {
                        textField1.setText(createAIProgramType().toString());
                    } else if (option.equals("AuditTrail")) {
                        textField1.setText(createAuditTrail().toString());
                    } else if (option.equals("Description")) {
                        textField1.setText(createDescription().toString());
                    } else if (option.equals("Developer")) {
                        textField1.setText(createDeveloper().toString());
                    } else if (option.equals("GlobalRating")) {
                        textField1.setText(createGlobalRating().toString());
                    } else if (option.equals("Guest")) {
                        textField1.setText(createGuest().toString());
                    } else if (option.equals("List of AIPrograms for guests")) {
                        textField1.setText(createGuestProgramAssociation().toString());
                    } else if (option.equals("List of AIPrograms for registered users")) {
                        textField1.setText(createRegisteredUserProgramAssociation().toString());
                    } else if (option.equals("List of AIPrograms for premium users")) {
                        textField1.setText(createPremiumUserProgramAssociation().toString());
                    } else if (option.equals("Permission")) {
                        textField1.setText(createPermission().toString());
                    } else if (option.equals("Policy")) {
                        textField1.setText(createPolicy().toString());
                    } else if (option.equals("ProgramDeveloper")) {
                        textField1.setText(createProgramDeveloper().toString());
                    } else if (option.equals("ProgramPolicy")) {
                        textField1.setText(createProgramPolicy().toString());
                    } else if (option.equals("Role")) {
                        textField1.setText(createRole().toString());
                    } else if (option.equals("RolePermission")) {
                        textField1.setText(createRolePermission().toString());
                    } else if (option.equals("User")) {
                        textField1.setText(createUser().toString());
                    } else if (option.equals("UserRole")) {
                        textField1.setText(createUserRole().toString());
                    }

                    // Close the dialog box after input is processed
                    Window window = SwingUtilities.getWindowAncestor(panel);
                    window.dispose();

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                }
            });
        }

        // Create a scrollable pane for the panel
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setPreferredSize(new Dimension(400, 200));

        // Show the dialog box with the scrollable pane
        JOptionPane.showMessageDialog(null, scrollPane, "Choose table", JOptionPane.PLAIN_MESSAGE);
        // Close the dialog box after input is processed
        Window window = SwingUtilities.getWindowAncestor(panel);
        window.dispose();
    }






}
