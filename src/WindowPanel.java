import javax.swing.*;
import java.awt.*;

public class WindowPanel{

    public static JPanel panel;
    public static JPanel panelUp;
    public static JPanel startPanel;

    public WindowPanel() {
        panel = new JPanel();
        panelUp = new JPanel();
        startPanel = new JPanel();
        addStartPanel();
        new StarPanel();
    }

    private static void creatPanel(JPanel pan, int x, int y, int h, int l, Color col, boolean visebabl){
        pan.setLayout(null);
        MainWindow.jf.add(pan);
        pan.setBounds(x,y,h,l);
        pan.setVisible(visebabl);
        pan.setBackground(col);
    }

    public static void addPanels(){
        creatPanel(panel,0,22,MainWindow.WIDTH_HEIGHT,MainWindow.WIDTH_HEIGHT,Color.orange, false);//оснавная панель
        creatPanel(panelUp,0,0,MainWindow.WIDTH_HEIGHT, 22, Color.GRAY, false);//дополнительная панель, которая сверху
    }

    private void addStartPanel(){
        creatPanel(startPanel,0,0,MainWindow.WIDTH_HEIGHT/4,MainWindow.WIDTH_HEIGHT/4+22,Color.gray,true);
    }
}
