import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;
import java.awt.*;
import java.awt.event.*;

public class Desktop extends JFrame {
    private JDesktopPane theDesktop;

    public Desktop() {
        super("Connect Four");
        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            // Nimbus not available, fallback to default
        }

        // Set up desktop
        ImageIcon icon = new ImageIcon(getClass().getResource("logo.png"));
        Image image = icon.getImage();

        theDesktop = new JDesktopPane() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(image, (getWidth() - image.getWidth(null)) / 2, (getHeight() - image.getHeight(null)) / 2 - 50, this); // Desplazo ligeramente hacia arriba para hacer espacio para los botones
            }
        };

        JButton btnStart = new JButton("Start");
        JButton btnInstructions = new JButton("Instructions");
        JButton btnCredits = new JButton("Credits");
        JButton playComputer = new JButton("Play vs Computer");
        btnStart.addActionListener(event -> {
            PlayerTurnsInternalFrame newTurn = new PlayerTurnsInternalFrame();
            newTurn.pack();
            theDesktop.add(newTurn);
            newTurn.setVisible(true);
        });


        btnInstructions.addActionListener(event -> JOptionPane.showMessageDialog(null, "Try to build a row of four checkers (horizontally, vertically or diagonally) while keeping your opponent from doing the same. The turns will be alternating.\n On the screen you'll see buttons with the letter 'A' which means they are available, and some in black with the letter 'U' which means they are unavailable, more buttons will become available as you play."));

        btnCredits.addActionListener(event -> JOptionPane.showMessageDialog(null, "Andrea Lima"));

        playComputer.addActionListener(event -> {
            Tablero newGame = new Tablero(true);
            newGame.setVisible(true);
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.add(btnStart);
        buttonPanel.add(playComputer);
        buttonPanel.add(btnInstructions);
        buttonPanel.add(btnCredits);

        theDesktop.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.insets = new Insets(200, 0, 0, 0);
        theDesktop.add(buttonPanel, gbc);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(theDesktop, BorderLayout.CENTER);

        setSize(600, 460);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String args[]) {
        SwingUtilities.invokeLater(() -> {
            Desktop application = new Desktop();
            application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        });
    }
}

