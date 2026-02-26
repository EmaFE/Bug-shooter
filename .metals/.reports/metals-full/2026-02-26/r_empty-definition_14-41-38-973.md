error id: file:///C:/Users/flore/Uni/Y4/Sem2/GameDev/bugShooter/Bug-shooter/src/PopUp.java:javax/swing/SwingConstants#
file:///C:/Users/flore/Uni/Y4/Sem2/GameDev/bugShooter/Bug-shooter/src/PopUp.java
empty definition using pc, found symbol in pc: javax/swing/SwingConstants#
empty definition using semanticdb
empty definition using fallback
non-local guesses:

offset: 473
uri: file:///C:/Users/flore/Uni/Y4/Sem2/GameDev/bugShooter/Bug-shooter/src/PopUp.java
text:
```scala
import javax.swing.*;
import java.awt.*;

public class PopUp extends JDialog {

    private boolean accepted = false;

    public PopUp(JFrame parent, String message) {
        super(parent, "Confirm", true); // true -> modal dialog

        setLayout(new BorderLayout());
        setSize(300, 150);
        setLocationRelativeTo(parent); // center on parent
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        JLabel label = new JLabel(message, SwingConst@@ants.CENTER);
        label.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(label, BorderLayout.CENTER);

        JButton yesBtn = new JButton("Yes");
        JButton noBtn = new JButton("No");

        yesBtn.addActionListener(e -> {
            accepted = true;
            dispose(); // close dialog
        });

        noBtn.addActionListener(e -> {
            accepted = false;
            dispose();
        });

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(yesBtn);
        buttonPanel.add(noBtn);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    public boolean isAccepted() {
        return accepted;
    }
}
```


#### Short summary: 

empty definition using pc, found symbol in pc: javax/swing/SwingConstants#