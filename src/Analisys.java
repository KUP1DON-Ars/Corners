public class Analisys {

    private int [][] setka;
    private int [][] price;
    private int lastPosition_X;
    private int lastPosition_Y;
    private int nextPosition_X;
    private int nextPosition_Y;

    public Analisys() {
        transferIn_2d();
        priceField();
        AnalisysTrafficBota();
    }

    public int getLastPosition_X() {
        return lastPosition_X;
    }

    public int getLastPosition_Y() {
        return lastPosition_Y;
    }

    public int getNextPosition_X() {
        return nextPosition_X;
    }

    public int getNextPosition_Y() {
        return nextPosition_Y;
    }

    private void transferIn_2d(){
        setka = new int[Buttons.board.length][Buttons.board.length];
        for (int i = 0; i < setka.length; i++) {
            for (int j = 0; j < setka[i].length; j++) {
                if (Buttons.board[i][j].getIcon() == Buttons.whiteFigure){
                    setka[i][j] = 1;
                }
                else if (Buttons.board[i][j].getIcon() == Buttons.blackFigure){
                    setka[i][j] = 2;
                }
                else {
                    setka[i][j] = 0;
                }
            }
        }
    }

    private void priceField(){
        price = new int[][]{{6, 7, 6, 5, 4, 3, 2, 1},
                            {7, 8, 7, 6, 5, 4, 3, 2},
                            {8, 9, 8, 7, 6, 5, 4, 3},
                            {9, 10, 9, 8, 7, 6, 5, 4},
                            {10, 11, 10, 9, 8, 7, 6, 5},
                            {11, 12, 11, 10, 9, 8, 7, 6},
                            {12, 13, 12, 11, 10, 9, 8, 7},
                            {13, 12, 11, 10, 9, 8, 7, 6}};
    }

    private void AnalisysTrafficBota(){
        int [] peremen = new int[5];//maxSum, last_x, last_y, next_x, next_y;
        for (int i = 0; i < setka.length; i++) {
            for (int j = 0; j < setka[i].length; j++) {
                peremen = leftAnalisysTraffic(i, j, peremen);
                peremen = downAnalisysTraffic(i, j, peremen);
            }
        }
        nextMoveBot(peremen);
    }

    private int [] leftAnalisysTraffic(int i, int j, int [] peremen){
        if (setka[i][j] == 2){
            int promMaxSum = 0;
            int h = 0;
            for (int k = 1; k < setka.length; k++) {
                if (j - k >= 0){
                    if (setka[i][j - 1] != 2 && setka[i][j - 1] != 1){
                        promMaxSum = price[i][j - 1];
                        peremen = leftComparison(promMaxSum, i, j,1, peremen);
                        break;
                    }
                    else if (k != 1 && setka[i][j - k] != 2 && setka[i][j - k] != 1){
                        break;
                    }
                    else {
                        if (setka[i][j - k] == 2 || setka[i][j - k] == 1){
                            if (j - k - 1 < 0){
                                break;
                            }
                            if (setka[i][j - k - 1] == 2 || setka[i][j - k - 1] == 1){
                                break;
                            }
                            else {
                                k++;
                                promMaxSum += (price[i][j - k] + price[i][j - k + 1]);
                                h = k;
                            }
                        }
                    }
                }
                else break;
            }
            if (h > 1){
                peremen = leftComparison(promMaxSum, i, j, h, peremen);
            }
        }
        return peremen;
    }

    private int [] leftComparison(int promMaxSum, int i, int j, int k, int [] peremen){
        if (promMaxSum > peremen[0]){
            peremen[0] = promMaxSum;
            peremen[3] = i;
            peremen[4] = j - k;
            peremen[1] = i;
            peremen[2] = j;
        }
        return peremen;
    }

    private int [] downAnalisysTraffic(int i, int j, int [] peremen){
        if (setka[i][j] == 2){
            int promMaxSum = 0;
            int h = 0;
            for (int k = 1; k < setka.length; k++) {
                if (i + k < setka.length){
                    if (setka[i + 1][j] != 2 && setka[i + 1][j] != 1){
                        promMaxSum = price[i + 1][j];
                        peremen = downComparison(promMaxSum, i, j,1, peremen);
                        break;
                    }
                    else if (k != 1 && setka[i + k][j] != 2 && setka[i + k][j] != 1){
                        break;
                    }
                    else {
                        if (setka[i + k][j] == 2 || setka[i + k][j] == 1){
                            if (i + k + 1 >= setka.length){
                                break;
                            }
                            if (setka[i + k + 1][j] == 2 || setka[i + k + 1][j] == 1){
                                break;
                            }
                            else {
                                k++;
                                promMaxSum += (price[i + k][j] + price[i + k - 1][j]);
                                h = k;
                            }
                        }
                    }
                }
                else break;
            }
            if (h > 1){
                peremen = downComparison(promMaxSum, i, j, h, peremen);
            }
        }
        return peremen;
    }

    private int [] downComparison(int promMaxSum, int i, int j, int k, int [] peremen){
        if (promMaxSum > peremen[0]){
            peremen[0] = promMaxSum;
            peremen[3] = i + k;
            peremen[4] = j;
            peremen[1] = i;
            peremen[2] = j;
        }
        return peremen;
    }

    private void nextMoveBot(int [] peremen){
        lastPosition_X = peremen[1];
        lastPosition_Y = peremen[2];
        nextPosition_X = peremen[3];
        nextPosition_Y = peremen[4];
    }

    private void vivodMassiva(int [][] x){
        for (int i = 0; i < x.length; i++) {
            for (int j = 0; j < x[i].length; j++) {
                System.out.print(x[i][j] + " ");
            }
            System.out.println();
        }
    }
}
