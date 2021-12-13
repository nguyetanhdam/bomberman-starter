package main.bomberman.entities.character.enemy.ai;

public class AILow extends AI{

    /**
     * trả về hướng random*/
    @Override
    public int calcDirection() {
        return random.nextInt(4);
    }
}
