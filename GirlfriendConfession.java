import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class GirlfriendConfession extends JFrame {
    private JButton yesButton;
    private JButton noButton;
    private JLabel messageLabel;
    private JPanel mainPanel;
    private JPanel buttonPanel;
    private Random random = new Random();
    private int yesButtonSize = 1;
    private final int MAX_YES_SIZE = 3;
    
    public GirlfriendConfession() {
        // Set up the frame
        setTitle("Confession");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        
        // Create main panel with BorderLayout
        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Create the confession message
        messageLabel = new JLabel("Will you be my girlfriend?");
        messageLabel.setFont(new Font("Arial", Font.BOLD, 20));
        messageLabel.setHorizontalAlignment(JLabel.CENTER);
        messageLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        
        // Create buttons
        yesButton = new JButton("Yes");
        noButton = new JButton("No");
        
        // Style the buttons
        yesButton.setBackground(new Color(46, 204, 113));
        yesButton.setForeground(Color.WHITE);
        yesButton.setFocusPainted(false);
        yesButton.setFont(new Font("Arial", Font.BOLD, 14));
        
        noButton.setBackground(new Color(236, 240, 241));
        noButton.setForeground(Color.BLACK);
        noButton.setFocusPainted(false);
        noButton.setFont(new Font("Arial", Font.BOLD, 14));
        
        // Create button panel
        buttonPanel = new JPanel();
        buttonPanel.setLayout(null); // Use null layout for absolute positioning
        buttonPanel.setPreferredSize(new Dimension(400, 100));
        
        // Set initial button positions
        yesButton.setBounds(100, 30, 80, 40);
        noButton.setBounds(220, 30, 80, 40);
        
        // Add buttons to panel
        buttonPanel.add(yesButton);
        buttonPanel.add(noButton);
        
        // Add components to main panel
        mainPanel.add(messageLabel, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        
        // Add main panel to frame
        add(mainPanel);
        
        // Add action listener to Yes button
        yesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showSuccessMessage();
            }
        });
        
        // Add mouse listeners to No button
        noButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                moveNoButton();
                increaseYesButtonSize();
            }
        });
        
        // Make the frame visible
        setVisible(true);
    }
    
    private void moveNoButton() {
        // Get the size of the button panel
        int panelWidth = buttonPanel.getWidth() - noButton.getWidth();
        int panelHeight = buttonPanel.getHeight() - noButton.getHeight();
        
        // Generate random position within the panel
        int newX = random.nextInt(panelWidth);
        int newY = random.nextInt(panelHeight);
        
        // Move the No button
        noButton.setBounds(newX, newY, noButton.getWidth(), noButton.getHeight());
        buttonPanel.repaint();
    }
    
    private void increaseYesButtonSize() {
        if (yesButtonSize < MAX_YES_SIZE) {
            yesButtonSize++;
            
            // Increase button size
            int newWidth = 80 * yesButtonSize;
            int newHeight = 40 * yesButtonSize;
            
            // Recenter the button
            int newX = (buttonPanel.getWidth() - newWidth) / 2;
            int newY = (buttonPanel.getHeight() - newHeight) / 2;
            
            yesButton.setBounds(newX, newY, newWidth, newHeight);
            yesButton.setFont(new Font("Arial", Font.BOLD, 14 * yesButtonSize));
            
            buttonPanel.repaint();
        }
    }
    
    private void showSuccessMessage() {
        // Remove all components
        mainPanel.removeAll();
        
        // Create success panel
        JPanel successPanel = new JPanel(new BorderLayout());
        
        // Create success message
        JLabel successLabel = new JLabel("Yay! Thank you for saying yes! ❤️");
        successLabel.setFont(new Font("Arial", Font.BOLD, 20));
        successLabel.setHorizontalAlignment(JLabel.CENTER);
        successLabel.setForeground(new Color(231, 76, 60));
        successLabel.setBorder(BorderFactory.createEmptyBorder(50, 0, 50, 0));
        
        // Add to panel
        successPanel.add(successLabel, BorderLayout.CENTER);
        mainPanel.add(successPanel);
        
        // Refresh the frame
        mainPanel.revalidate();
        mainPanel.repaint();
    }
    
    public static void main(String[] args) {
        // Run the application on the Event Dispatch Thread
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GirlfriendConfession();
            }
        });
    }
}