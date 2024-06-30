package onlinevotingsystem;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class VoterProfile extends JFrame {
    private JLabel nameLabel, idLabel;
    private JTextField nameField, idField;
    private JButton saveButton, backButton;

    public VoterProfile() {
        setTitle("Voter Profile");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 2, 10, 10));

        nameLabel = new JLabel("Name:");
        idLabel = new JLabel("ID:");

        nameField = new JTextField(20);
        idField = new JTextField(20);

        saveButton = new JButton("Save");
        backButton = new JButton("Back to Dashboard");

        add(nameLabel);
        add(nameField);
        add(idLabel);
        add(idField);
        add(saveButton);
        add(backButton);

        loadProfile();

        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveProfile();
            }
        });

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the Voter Profile window
            }
        });

        setVisible(true);
    }

    private void loadProfile() {
        try (BufferedReader br = new BufferedReader(new FileReader("profile.txt"))) {
            String name = br.readLine().split(": ")[1];
            String id = br.readLine().split(": ")[1];
            nameField.setText(name);
            idField.setText(id);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveProfile() {
        String name = nameField.getText();
        String id = idField.getText();

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("profile.txt"))) {
            bw.write("Name: " + name + "\n");
            bw.write("ID: " + id + "\n");
            JOptionPane.showMessageDialog(this, "Profile updated successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new VoterProfile();
    }
}
}