import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FortuneTellerUI extends JFrame implements MouseListener {
    public JTextField questionField;
    public JButton tryAgainButton;
    public boolean shakeEnabled;
    public FortuneTeller fortuneTeller;
    public JLabel eightBallLabel;
    public Object shakeButton;
    public JLabel questionJLabel;

    public FortuneTellerUI() {
        super("Fortune Teller");
        shakeEnabled = true;
        fortuneTeller = new FortuneTeller();
        JLabel questionLabel = new JLabel("Ask a yes or no question, then click anywhere on the screen to shake.");
        questionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        questionLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
        questionLabel.setForeground(Color.magenta);

        questionField = new JTextField(100);
        questionField.setFont(new Font("Times New Roman", Font.BOLD, 25));
        questionField.setForeground(Color.BLUE);

        tryAgainButton = new JButton("Try Again");
        tryAgainButton.setHorizontalAlignment(SwingConstants.CENTER);
        tryAgainButton.setFont(new Font("Bank_Gothic", Font.PLAIN, 18));
        tryAgainButton.setForeground(Color.RED);
        tryAgainButton.setBackground(Color.DARK_GRAY);
        tryAgainButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resetUI();
            }
        });
        tryAgainButton.setVisible(false); // set as initially hidden

        ImageIcon eightBallIcon = new ImageIcon("8ball.png");
        eightBallLabel = new JLabel(eightBallIcon);
        eightBallLabel.addMouseListener(this); // add MouseListener to eightBallLabel

        JPanel panel = new JPanel(new BorderLayout());
        panel.setPreferredSize(new Dimension(200, 300));
        panel.add(eightBallLabel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new GridLayout(3, 1));
        centerPanel.add(questionField);

        centerPanel.add(questionLabel, SwingConstants.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout()); // Change layout to FlowLayout
        buttonPanel.add(Box.createHorizontalGlue());
        buttonPanel.add(tryAgainButton);
        tryAgainButton.setVisible(false); // hide tryAgainButton initially

        centerPanel.add(buttonPanel);
        panel.add(centerPanel, BorderLayout.SOUTH);

        add(panel);
        panel.setBackground(Color.black);
        setSize(900, 900);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void resetUI() {
        questionField.setVisible(true);
        tryAgainButton.setVisible(false);
        getContentPane().removeAll();
        JPanel panel = new JPanel(new BorderLayout());
        panel.setPreferredSize(new Dimension(200, 300));
        panel.add(eightBallLabel, BorderLayout.NORTH);
        JPanel centerPanel = new JPanel(new GridLayout(3, 1));
        centerPanel.add(questionField);
        JPanel buttonPanel = new JPanel(new FlowLayout()); // Change layout to FlowLayout
        buttonPanel.add(Box.createHorizontalGlue()); // Add flexible component to push button to center
        buttonPanel.add(tryAgainButton);
        tryAgainButton.setVisible(false);

        centerPanel.add(buttonPanel);
        panel.setBackground(Color.black);
        centerPanel.setBackground(Color.black);
        panel.add(centerPanel, BorderLayout.SOUTH);
        getContentPane().add(panel);
        questionField.setText("");
        revalidate();
        repaint();
    }

    public void mousePressed(MouseEvent e) {
        if (questionField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a question", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Thread animationThread = new Thread(() -> {
            int x = eightBallLabel.getX();
            int y = eightBallLabel.getY();
            for (int i = 0; i < 30; i++) {
                if (i % 2 == 0) {
                    x += 10;
                } else {
                    x -= 10;
                }
                try {
                    Thread.sleep(50);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                eightBallLabel.setLocation(x, y);
            }
            eightBallLabel.setLocation(x, y);

            // Callback method to display fortune after animation is completed
            SwingUtilities.invokeLater(() -> {
                String fortune = fortuneTeller.getFortune();
                Image fortunepic = fortuneTeller.getImage();

                JPanel panel = new JPanel(new BorderLayout());
                JLabel fortuneLabel = new JLabel(fortune);
                JLabel imageLabel = new JLabel(new ImageIcon(fortunepic));
                fortuneLabel.setHorizontalAlignment(SwingConstants.CENTER);
                fortuneLabel.setFont(new Font("Impact", Font.BOLD, 35));
                fortuneLabel.setForeground(Color.MAGENTA);
                panel.add(fortuneLabel, BorderLayout.NORTH);
                panel.add(imageLabel, BorderLayout.CENTER);
                getContentPane().removeAll();
                JPanel centerPanel = new JPanel(new GridLayout(3, 1));
                centerPanel.add(questionField);
                JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
                questionField.setVisible(false);
                buttonPanel.add(tryAgainButton);
                tryAgainButton.setVisible(true);

                centerPanel.add(buttonPanel);
                panel.setBackground(Color.BLACK);
                centerPanel.setBackground(Color.BLACK);
                panel.add(centerPanel, BorderLayout.SOUTH);
                getContentPane().add(panel);
                revalidate();
                repaint();
            });
        });

        // Start the animation thread
        animationThread.start();
    }

    // empty implementations
    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseClicked(MouseEvent e) {

    }
}
