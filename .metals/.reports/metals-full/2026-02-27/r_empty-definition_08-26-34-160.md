error id: file:///C:/Users/flore/Uni/Y4/Sem2/GameDev/bugShooter/Bug-shooter/src/PopUp.java:java/awt/event/ActionListener#
file:///C:/Users/flore/Uni/Y4/Sem2/GameDev/bugShooter/Bug-shooter/src/PopUp.java
empty definition using pc, found symbol in pc: java/awt/event/ActionListener#
empty definition using semanticdb
empty definition using fallback
non-local guesses:

offset: 1000
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

    public PopUp(JFrame parent, String text, String firstBtnText, String seconfBtnText) {
        super(parent, "Please choose", true); //true -> modal dialog = blocks game until player chooses, will stop ANY game logic until player chooses!!!!

        setSize(400, 250);
        setLocationRelativeTo(parent); //center bsed on parent
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        JLabel label = new JLabel(text, SwingConstants.CENTER);
        add(label, BorderLayout.CENTER);
        JButton yesBtn = new JButton("[alt + y] Bigger");
        JButton noBtn = new JButton("[alt + n] Faster");

        yesBtn.setMnemonic('y');
        noBtn.setMnemonic('n');

        yesBtn.addActionListener(new ActionListene@@r() {
            @Override
            public void actionPerformed(ActionEvent e) {
              accepted = true;
              dispose(); //closes pop up
            }
        });

        noBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              accepted = false;
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

empty definition using pc, found symbol in pc: java/awt/event/ActionListener#