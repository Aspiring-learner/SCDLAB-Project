package onlinevotingsystem;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class CandidateList extends JFrame {
    private JList<String> candidateList;
    private DefaultListModel<String> listModel;
    private JButton backButton;
    private JPanel panel;

    public CandidateList() {
        setTitle("Candidate List");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        listModel = new DefaultListModel<>();
        loadCandidates();

        candidateList = new JList<>(listModel);
        candidateList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        candidateList.setFont(new Font("Arial", Font.PLAIN, 14));
        
        JScrollPane scrollPane = new JScrollPane(candidateList);

        backButton = new JButton("Back to Dashboard");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the Candidate List window
            }
        });

        // Panel to hold the list and button
        panel = new JPanel(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(backButton, BorderLayout.SOUTH);

        add(panel);
        setVisible(true);
    }

    private void loadCandidates() {
        try (BufferedReader br = new BufferedReader(new FileReader("candidates.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                listModel.addElement(line);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error loading candidates: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new CandidateList();
    }
}
