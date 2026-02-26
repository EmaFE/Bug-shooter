error id: file:///C:/Users/flore/Uni/Y4/Sem2/GameDev/bugShooter/Bug-shooter/src/PopUp.java:javax/swing/JDialog#setDefaultCloseOperation().
file:///C:/Users/flore/Uni/Y4/Sem2/GameDev/bugShooter/Bug-shooter/src/PopUp.java
empty definition using pc, found symbol in pc: javax/swing/JDialog#setDefaultCloseOperation().
empty definition using semanticdb
empty definition using fallback
non-local guesses:

offset: 387
uri: file:///C:/Users/flore/Uni/Y4/Sem2/GameDev/bugShooter/Bug-shooter/src/PopUp.java
text:
```scala
import javax.swing.*;
import java.awt.*;

public class PopUp extends JDialog {

    private boolean accepted = false;

    public PopUp(JFrame parent, String text) {
        super(parent, "Please choose", true); // true -> modal dialog

        setSize(400, 250);
        setLocationRelativeTo(parent); //center bsed on parent
        setLayout(new BorderLayout());
        setDefaultClo@@seOperation(JDialog.DISPOSE_ON_CLOSE);

        JLabel label = new JLabel(text, SwingConstants.CENTER);
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

empty definition using pc, found symbol in pc: javax/swing/JDialog#setDefaultCloseOperation().