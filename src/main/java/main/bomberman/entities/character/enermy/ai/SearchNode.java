package main.bomberman.entities.character.enermy.ai;

public class SearchNode implements Comparable<SearchNode>{
    Boardx boardx;
    SearchNode prev;
    int move;
    int manhattan;

    public SearchNode(Boardx bx, SearchNode prev){
        this.boardx = bx;
        this.prev = prev;

        if(prev == null){
            move = 0;
        }
        else {
            move = prev.move + 1;
        }
        manhattan = boardx.getManhattan();
    }

    @Override
    public int compareTo(SearchNode o) {
        int priority = (manhattan + move) - (o.manhattan + o.move);
        return priority == 0 ? (manhattan - o.manhattan) : priority;
    }
}