error id: file:///C:/Users/flore/Uni/Y4/Sem2/GameDev/bugShooter/Bug-shooter/src/PopUp.java:java/awt/Window#dispose().
file:///C:/Users/flore/Uni/Y4/Sem2/GameDev/bugShooter/Bug-shooter/src/PopUp.java
empty definition using pc, found symbol in pc: java/awt/Window#dispose().
empty definition using semanticdb
empty definition using fallback
non-local guesses:

offset: 1061
uri: file:///C:/Users/flore/Uni/Y4/Sem2/GameDev/bugShooter/Bug-shooter/src/PopUp.java
text:
```scala
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

public class PopUp extends JDialog {

    private boolean accepted = false;
    private boolean pressedY = false;
    private boolean pressedN = false;
    private Controller controllerK = Controller.getInstance();

    public PopUp(JFrame parent, String text) {
        super(parent, "Please choose", true); //true -> modal dialog

        setSize(400, 250);
        setLocationRelativeTo(parent); //center bsed on parent
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        JLabel label = new JLabel(text, SwingConstants.CENTER);
        add(label, BorderLayout.CENTER);
        JButton yesBtn = new JButton("Yes");
        JButton noBtn = new JButton("No");

       

        yesBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              pressedY = true;
              dispo@@se(); //closes pop up
            }
        });

        noBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              pressedN = false;
              dispose(); //closes pop up
            }
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

empty definition using pc, found symbol in pc: java/awt/Window#dispose().