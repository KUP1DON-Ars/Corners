import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Bot implements ActionListener {

    private static Timer timer;
    Analisys analisys = new Analisys();

    public Bot() {
        timeR();
    }

    private void timeR(){
        timer = new Timer(1200, this);//задержка перед ходом ИИ
        timer.start();
    }

    private void nextMoveBota(int a, int b, int x, int y){
        Buttons.board[a][b].setIcon(new ImageIcon());
        Buttons.board[x][y].setIcon(Buttons.blackFigure);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        nextMoveBota(analisys.getLastPosition_X(),analisys.getLastPosition_Y(),analisys.getNextPosition_X(),analisys.getNextPosition_Y());
        Buttons.move++;
        timer.stop();
    }
}
