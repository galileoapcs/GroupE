import info.gridworld.actor.Actor;
import info.gridworld.actor.Flower;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import java.awt.Color;

public class TetrisBlock_Z extends TetrisBlock
{

	public TetrisBlock_Z()
	{
		super();
		TetrisBug b = new TetrisBug(Color.green);
		TetrisBug c = new TetrisBug(Color.yellow);
		b.putSelfInGrid(gr, new Location(2,6));
		c.putSelfInGrid(gr, new Location(1,6));
		blocks.add(b);
		blocks.add(c);
	}
	
	public boolean canMoveDown()
	{
		if(rotationPos == 0)
		{
			return canMove() && blocks.get(1).canMove();
		}
		else if(rotationPos == 1)
		{
			return canMove() && blocks.get(0).canMove() && blocks.get(1).canMove();
		}
		return false;
	}
	
	public void moveDown()
	{
		if(rotationPos == 0)
		{
			blocks.get(1).move();
			blocks.get(2).move();
			move();
			blocks.get(0).move();
		}
		else if(rotationPos == 1)
		{
			blocks.get(0).move();
			move();
			blocks.get(2).move();
			blocks.get(1).move();
		}	
	}
	
	public void moveLeft()
	{
		setDirection(270);
		for(TetrisBug tb : blocks)
		{
			tb.setDirection(270);
		}
		if(rotationPos == 0)
		{
			if(canMove() && blocks.get(0).canMove())
			{
				blocks.get(0).move();
				move();
				blocks.get(2).move();
				blocks.get(1).move();
			}
		}
		else if(rotationPos == 1)
		{
			if(blocks.get(0).canMove() && blocks.get(2).canMove())
			{
				blocks.get(0).move();
				move();
				blocks.get(2).move();
				blocks.get(1).move();
			}
		}
	}
	
	public void moveRight()
	{
		setDirection(90);
		for(TetrisBug tb : blocks)
		{
			tb.setDirection(90);
		}
		if(rotationPos == 0)
		{
			if(blocks.get(1).canMove() && blocks.get(2).canMove())
			{
				blocks.get(1).move();
				blocks.get(2).move();
				move();
				blocks.get(0).move();
			}
		}
		else if(rotationPos == 1)
		{
			if(blocks.get(1).canMove() && canMove())
			{
				blocks.get(1).move();
				blocks.get(2).move();
				move();
				blocks.get(0).move();
			}
		}
	}
	
	public void rotate()
	{
		Location nextLoc1;
		Location nextLoc2;
		Location nextLoc3;
		if(rotationPos == 0)
		{
			nextLoc1 = new Location(blocks.get(0).getLocation().getRow() + 1,
					blocks.get(0).getLocation().getCol() - 1);
			nextLoc2 = new Location(blocks.get(2).getLocation().getRow() - 1,
					blocks.get(2).getLocation().getCol() - 1);
			nextLoc3 = new Location(blocks.get(1).getLocation().getRow() - 2,
					blocks.get(1).getLocation().getCol());
			if(gr.isValid(nextLoc1) && gr.get(nextLoc1) == null && 
					gr.isValid(nextLoc2) && gr.get(nextLoc2) == blocks.get(0) &&
					gr.isValid(nextLoc3) && gr.get(nextLoc3) == null)
			{
				blocks.get(0).moveTo(nextLoc1);
				blocks.get(2).moveTo(nextLoc2);
				blocks.get(1).moveTo(nextLoc3);
				rotationPos = (rotationPos + 1) % 2;
			}
		}
		else if(rotationPos == 1)
		{
			nextLoc1 = new Location(blocks.get(0).getLocation().getRow() - 1,
					blocks.get(0).getLocation().getCol() + 1);
			nextLoc2 = new Location(blocks.get(2).getLocation().getRow() + 1,
					blocks.get(2).getLocation().getCol() + 1);
			nextLoc3 = new Location(blocks.get(1).getLocation().getRow() + 2,
					blocks.get(1).getLocation().getCol());
			if(gr.isValid(nextLoc1) && gr.get(nextLoc1) == blocks.get(2) && 
					gr.isValid(nextLoc2) && gr.get(nextLoc2) == null &&
					gr.isValid(nextLoc3) && gr.get(nextLoc3) == null)
			{
				blocks.get(1).moveTo(nextLoc3);
				blocks.get(2).moveTo(nextLoc2);
				blocks.get(0).moveTo(nextLoc1);
				rotationPos = (rotationPos + 1) % 2;
			}
		}
	}
}
