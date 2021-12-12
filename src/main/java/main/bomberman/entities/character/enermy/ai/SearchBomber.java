package main.bomberman.entities.character.enermy.ai;

import java.util.PriorityQueue;
import java.util.Random;
import java.util.Stack;

public class SearchBomber {

    private Stack<Boardx> path = new Stack<>();

    public SearchBomber(Boardx initial){
        if(initial == null)
            throw new IllegalArgumentException();
        SearchNode origin = new SearchNode(initial, null);

        PriorityQueue<SearchNode> list = new PriorityQueue<>();

        list.add(origin);

        while (true){
            origin = list.poll();
            if(origin.boardx.isGoal()) {
                break;
            }
            else {
                for(Boardx b : origin.boardx.neighbors()){
                    if(origin.prev == null || !b.equals(origin.prev.boardx)) {
                        list.add(new SearchNode(b, origin));
                    }
                }
            }
        }

        convert(origin);
    }

    private void convert(SearchNode temp){
        while (temp != null){
            path.push(temp.boardx);
            temp = temp.prev;
        }
    }

    public int getDir(){
        if(path.size() >= 2) {
            Boardx oldB = path.pop();
            Boardx newB = path.peek();
            if (oldB.getX() > newB.getX()) {
                return 3;
            } else if (oldB.getX() < newB.getX()) {
                return 0;
            } else {
                if (oldB.getY() > newB.getY()) {
                    return 1;
                } else {
                    return 2;
                }
            }
        }
        Random r = new Random();
        return r.nextInt(4);
    }

    public void print(){
        for(Boardx boardx : path){
            boardx.print();
        }
    }

    public boolean emptyWay(){
        return path.size() < 2;
    }
}
