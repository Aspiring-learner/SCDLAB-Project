package onlinevotingsystem;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class VoterDashboard extends JFrame {
    private JLabel welcomeLabel;
    private JButton viewCandidatesButton, profileButton, logoutButton;

    public VoterDashboard() {
        setTitle("Voter Dashboard");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Welcome label
        welcomeLabel = new JLabel("Welcome to the Voter Dashboard", JLabel.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(welcomeLabel, BorderLayout.NORTH);

        // Buttons panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 1, 10, 10));
        
        viewCandidatesButton = new JButton("View Candidates");
        profileButton = new JButton("Profile");
        logoutButton = new JButton("Logout");

        buttonPanel.add(viewCandidatesButton);
        buttonPanel.add(profileButton);
        buttonPanel.add(logoutButton);
        
        add(buttonPanel, BorderLayout.CENTER);

        // Add action listeners
        viewCandidatesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new CandidateList();
            }
        });

        profileButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new VoterProfile();
            }
        });

        logoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Handle logout (e.g., close application or return to login screen)
                System.exit(0);
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new VoterDashboard();
    }
}
