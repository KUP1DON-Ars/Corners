import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StarPanel {

    JButton b1, b2, b3;
    Buttons buttons = new Buttons();

    public StarPanel() {
        b1 = new JButton();
        b2 = new JButton();
        b3 = new JButton();
        addButtons();
        buttonPush();
    }

    private void button(JButton but, String title, int x, int y){
        but.setFont(new Font("Times New Roman", Font.BOLD, 14));
        but.setText(title);
        WindowPanel.startPanel.add(but);
        but.setBounds(x, y, MainWindow.SIZE*2,MainWindow.SIZE/2);
        but.setFocusable(false);
        but.setVisible(true);
    }

    private void addButtons(){
        button(b1,"Игра 1X1", 10,10);
        button(b2,"Игра против ИИ", 10,MainWindow.SIZE/2+40);
        button(b3,"Выход", 10,MainWindow.SIZE+70);
    }

    private void buttonPush(){
        but_b1_b2(b1, 1);
        but_b1_b2(b2, 2);
        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(1);
            }
        });
    }

    private void but_b1_b2(JButton but, int a){
        but.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sizeWindow();
                WindowPanel.addPanels();
                new MenuPanel();
                buttons.start();
                WindowPanel.panel.setVisible(true);
                WindowPanel.panelUp.setVisible(true);
                if (a == 2){
                    Buttons.ii = true;
                }
            }
        });
    }

    private void sizeWindow(){
        MainWindow.jf.setSize(MainWindow.WIDTH_HEIGHT+ 14,MainWindow.WIDTH_HEIGHT+ 59);
        MainWindow.jf.setLocationRelativeTo(null);
        WindowPanel.startPanel.setVisible(false);

    }
}
