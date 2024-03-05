package com.tumuhairwe.prep.other;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class MapFunction implements java.util.function.Function<Integer, Position>{
    private final Map<Integer, Position> positionsRepository = new HashMap<>();

    public MapFunction(){
        init();
    }
    private void init(){
        this.positionsRepository.put(1,new Position(1, 7,null));
        this.positionsRepository.put(2,new Position(2, 5,null));

        // cycle
        this.positionsRepository.put(3,new Position(3, null,5));
        this.positionsRepository.put(5,new Position(5, null,3));

        this.positionsRepository.put(7,new Position(7, 11,null));
        this.positionsRepository.put(11,new Position(11, 20,null));
    }
    @Override
    public Position apply(Integer startingPosition) {
        Stack<Position> positions = new Stack<>();
        int sourceToLookup = startingPosition;

        do {
            Position p = this.positionsRepository.get(sourceToLookup);
            positions.add(p);

            if(p.getRedirectTo() != null){
                positions.add(p);
            }
        } while (positions.size() > 1);

        return positions.pop();
    }
}
