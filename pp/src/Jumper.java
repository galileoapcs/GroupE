

import java.awt.Color;

import info.gridworld.actor.Actor;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Flower;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;


public class Jumper extends Bug
{
    private int steps;
    private int sideLength;

    public Jumper()
    {
        setColor(Color.RED);
    }
    public Jumper(int length)
    {
        steps = 0;
        sideLength = length;
    }
    public boolean canMove()
    {
        Grid<Actor> gr = getGrid();
        if (gr == null){
            return false;
        }
        Location loc = getLocation();
        Location next = loc.getAdjacentLocation(getDirection());
        Location nextnext = next.getAdjacentLocation(getDirection());
        if (!gr.isValid(next)){
        	
            return false;
    	}
        Actor neighbor = gr.get(next);
        if (neighbor instanceof Flower) {
    		if (!gr.isValid(nextnext)) {
    			
    			return false;
    		}
    		
        	moveTo(nextnext);
    		return true;
    	}
        
        if (!gr.isValid(nextnext)) {
        	if (neighbor instanceof Flower) {
        		move();
        		moveTo(nextnext);
        		return false;
        	}
        	return false;
        }
        

        Actor neighborNeighbor = gr.get(nextnext);
        
        if ( neighborNeighbor instanceof Flower) {
    		move();
    		return false;
    	}
        
        if (nextnext == null) {
        	if ( neighborNeighbor instanceof Flower) {
        		move();
        		return false;
        	}
        	else if (!gr.isValid(next)) {
        		return false;
        	}
        	else {
        		return true;
        	}
        }
    
        Flower flower = new Flower(getColor());
        return (neighbor == null || neighbor == flower);
        
        
    }
    public void jump()
    {
    	
    	if (canMove())
        {
            move();
            move();
            steps++;
         
        }
        else 
        {
        	turn();
        	turn();
        	steps = 0;
        }
    
    }
   
    public void move()
    {
        Grid<Actor> gr = getGrid();
        if (gr == null)
            return;
        Location loc = getLocation();
        Location next = loc.getAdjacentLocation(getDirection());
        if (gr.isValid(next))
            moveTo(next);
        else act();
        Flower flower = new Flower(getColor());
        flower.putSelfInGrid(gr, loc);
 
    }
}
