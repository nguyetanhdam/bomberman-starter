package uet.oop.bomberman.character.enemy.AI;

import uet.oop.bomberman.character.enemy.Enemy;
import uet.oop.bomberman.character.player.Player;
import uet.oop.bomberman.data.GameData;

public class AILow extends AI{
    public int calcDirection() {
        return random.nextInt(4);
    }
}
