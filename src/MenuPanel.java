import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPanel extends JFrame {

    private JMenuBar menuBar;

    public MenuPanel() {
        creatinMenuBar();
        creatinIkon();
    }

    private void creatinMenuBar(){
        menuBar = new JMenuBar();
        WindowPanel.panelUp.add(menuBar);
        menuBar.setBounds(0,0,MainWindow.WIDTH_HEIGHT,22);
    }

    private void creatinIkon(){
        JMenu mnNewMenu = new JMenu("Файл");
        menuBar.add(mnNewMenu);

        JMenuItem mntmNewMenuItem = new JMenuItem("Новая игра");
        mnNewMenu.add(mntmNewMenuItem);
        mntmNewMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                WindowPanel.panel.setVisible(false);
                WindowPanel.panelUp.setVisible(false);
                MainWindow.jf.setSize((MainWindow.WIDTH_HEIGHT/4)+ 14,(MainWindow.WIDTH_HEIGHT/4)+ 59);
                MainWindow.jf.setLocationRelativeTo(null);
                new WindowPanel();
            }
        });

        JMenuItem mntmNewMenuItem_1 = new JMenuItem("Выход");
        mnNewMenu.add(mntmNewMenuItem_1);
        mntmNewMenuItem_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(1);
            }
        });

        JMenu mnNewMenu_1 = new JMenu("Настройки");
        menuBar.add(mnNewMenu_1);

        JMenuItem mntmNewMenuItem_2 = new JMenuItem("Автор");
        mnNewMenu_1.add(mntmNewMenuItem_2);
        mntmNewMenuItem_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"Автор проекта Олесевич Александр Сергеевич");
            }
        });
        menuBar.updateUI();
    }
}



