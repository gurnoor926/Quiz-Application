import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.sql.*;

public class Leaderboard extends JFrame {
    private JTabbedPane tabbedPane;
    private Connection conn;

    // Quiz types
    private String[] quizTypes = {
            "geography", "computer science", "programming", "riddles", "history",
            "english", "general knowledge", "social science", "science"
    };

    public Leaderboard() {
        setTitle("Quiz Leaderboard");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Initialize database connection
        connectToDatabase();

        // Create tabbed pane
        tabbedPane = new JTabbedPane();

        // Add a tab for each quiz type
        for (String quizType : quizTypes) {
            JPanel panel = createLeaderboardPanel(quizType);
            tabbedPane.addTab(quizType, panel);
        }

        add(tabbedPane);
        setVisible(true);
    }

    private void connectToDatabase() {
        try {
            // Update these with your database credentials
            String url = "jdbc:postgresql://localhost:5432/quizDb";
            String user = "postgres";
            String password = "Manak6142";
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,
                    "Database connection failed: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private JPanel createLeaderboardPanel(String quizType) {
        JPanel panel = new JPanel(new BorderLayout());

        // Create table model
        String[] columnNames = {"Rank", "Username", "Score"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make table read-only
            }
        };

        // Create table
        JTable table = new JTable(model);
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.setRowHeight(30);
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));
        table.getTableHeader().setBackground(new Color(70, 130, 180));
        table.getTableHeader().setForeground(Color.WHITE);

        // Set column widths
        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(80);  // Rank
        columnModel.getColumn(1).setPreferredWidth(300); // Username
        columnModel.getColumn(2).setPreferredWidth(100); // Score

        // Center align rank and score columns
        var centerRenderer = new javax.swing.table.DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);

        // Load data from database
        loadLeaderboardData(model, quizType);

        // Add table to scroll pane
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Add refresh button
        JButton refreshButton = new JButton("Refresh");
        refreshButton.setFont(new Font("Arial", Font.BOLD, 14));
        refreshButton.addActionListener(e -> {
            model.setRowCount(0); // Clear existing data
            loadLeaderboardData(model, quizType);
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(refreshButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    private void loadLeaderboardData(DefaultTableModel model, String quizType) {
        if (conn == null) {
            JOptionPane.showMessageDialog(this,
                    "No database connection available",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String query = "SELECT username, score FROM leaderboard " +
                "WHERE quiz_type = ? " +
                "ORDER BY score DESC";

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, quizType);
            ResultSet rs = pstmt.executeQuery();

            int rank = 1;
            while (rs.next()) {
                String username = rs.getString("username");
                int score = rs.getInt("score");

                model.addRow(new Object[]{rank, username, score});
                rank++;
            }

            if (rank == 1) {
                // No data found
                model.addRow(new Object[]{"", "No records found", ""});
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,
                    "Error loading leaderboard data: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    // Close database connection when window is closed
    @Override
    public void dispose() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        super.dispose();
    }

   /* public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> new Leaderboard());
    }*/
}