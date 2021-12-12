package main.bomberman.entities.character.enermy.ai;

import main.bomberman.board.BoardGame;
import main.bomberman.entities.character.Bomber;
import main.bomberman.entities.character.enermy.Enemy;

public class AISpecial extends AI{
    private final Bomber bomber;
    private final Enemy enemy;
    private int[][] _map;
    private boolean canSolve;

    public AISpecial(Enemy e, Bomber b){
        bomber = b;
        enemy = e;
        canSolve = false;
    }

    private int init() {

        _map = BoardGame.getMap();

        int xB = (bomber.getPositionX() + bomber.getWidth() / 2) / bomber.getWidth();
        int yB = (bomber.getPositionY() + bomber.getHeight() * 2 / 3) / bomber.getHeight();
        int xE = (enemy.getPositionX() + enemy.getWidth() / 3) / enemy.getWidth();
        int yE = (enemy.getPositionY() + enemy.getHeight() / 3) / enemy.getHeight();

        if (!canSolve) {
            //System.out.println("no way");
            int _height = _map.length;
            int _width = _map[0].length;
            Percolation percolation = new Percolation(_height, _width);
            for (int i = 0; i < _height; i++) {
                for (int j = 0; j < _width; j++) {
                    if (_map[i][j] == 1) {
                        percolation.open(i, j);
                    }
                }
            }
            if (percolation.isConnected(yE, xE, yB, xB)) {
                canSolve = true;
                //searchBomber = new SearchBomber(new Boardx(_map, yE, xE, yB, xB));
            }
        } else {
            //System.out.println(xB + " " + yB + " E: " + xE + " " + yE);
            //if(searchBomber.emptyWay())
            SearchBomber searchBomber = new SearchBomber(new Boardx(_map, yE, xE, yB, xB));
            return searchBomber.getDir();
        }

        return random.nextInt(4);
    }

    @Override
    public int calcDirection() {
        return init();
    }
}
