import info.gridworld.grid.Location;

import java.awt.Color;


public class TetrisBlockI extends TetrisBlock
{

	public TetrisBlockI()
	{
		super();
		setColor(Color.cyan);
		blocks.get(0).setColor(Color.cyan);
		TetrisBug b = new TetrisBug(Color.cyan);
		TetrisBug c = new TetrisBug(Color.cyan);
		b.putSelfInGrid(gr, new Location(2,5));
		c.putSelfInGrid(gr, new Location(3,5));
		blocks.add(b);
		blocks.add(c);
	}


	public boolean canMoveDown()
	{
		if (rotationPos == 0)
		{
			return blocks.get(2).canMove();
		}
		else if (rotationPos == 1)
		{
			return canMove();
		}
		return canMove();
	}
	
	public void moveDown()
	{
		if (rotationPos == 0) 
		{
			blocks.get(2).move();
			blocks.get(1).move();
			move();
			blocks.get(0).move();
		} 
		else if (rotationPos == 1) 
		{
			move();
			blocks.get(0).move();
			blocks.get(1).move();
			blocks.get(2).move();
		}
	}
	
	public void moveLeft()
	{
		setDirection(270);
		for (TetrisBug tb : blocks)
			tb.setDirection(270);
			if (rotationPos == 0 && canMove() && blocks.get(0).canMove()) 
			{
				move();
				blocks.get(0).move();
				blocks.get(1).move();
				blocks.get(2).move();
			}
			else if (rotationPos == 1 && blocks.get(0).canMove())
			{
				blocks.get(0).move();
				move();
				blocks.get(1).move();
				blocks.get(2).move();
			}
	}
	
	public void moveRight()
	{
		setDirection(90);
		for (TetrisBug tb : blocks)
			tb.setDirection(90);
			if (rotationPos == 0 && blocks.get(1).canMove() && blocks.get(2).canMove()) 
			{
				blocks.get(1).move();
				blocks.get(2).move();
				move();
				blocks.get(0).move();
			}
			else if(rotationPos == 1 && blocks.get(2).canMove())
			{
				blocks.get(2).move();
				blocks.get(1).move();
				move();
				blocks.get(0).move();
			}
	}
	
	public void rotate()
	{

		Location nextLoc;
		Location nextLoc2;
		Location nextLoc3;
		Location nextLoc4;
		if (rotationPos == 0) 
		{
			nextLoc = new Location(getLocation().getRow(),
					getLocation().getCol() - 1);
			nextLoc3 = new Location(blocks.get(1).getLocation().getRow() - 1,
					blocks.get(1).getLocation().getCol() + 1);
			nextLoc4 = new Location(blocks.get(2).getLocation().getRow() - 2,
					blocks.get(2).getLocation().getCol() + 2);
			if (gr.isValid(nextLoc) && gr.get(nextLoc) == null && gr.isValid(nextLoc4) && gr.get(nextLoc4) == null 
					&& gr.isValid(nextLoc3) && gr.get(nextLoc3) == null) 
			{
				blocks.get(0).moveTo(nextLoc);
				blocks.get(1).moveTo(nextLoc3);
				blocks.get(2).moveTo(nextLoc4);
				rotationPos = (rotationPos + 1) % 2;// will be % 4 with 4 blocks
			}
		}
		else if (rotationPos == 1) 
		{
			nextLoc = new Location(getLocation().getRow() - 1,
					getLocation().getCol() );
			nextLoc2 = new Location(blocks.get(1).getLocation().getRow() + 1,
					blocks.get(1).getLocation().getCol() - 1);
			nextLoc3 = new Location(blocks.get(2).getLocation().getRow() + 2,
					blocks.get(2).getLocation().getCol() - 2);
			if (gr.isValid(nextLoc) && gr.get(nextLoc) == null && gr.isValid(nextLoc2) && gr.get(nextLoc2) == null 
					&& gr.isValid(nextLoc3) && gr.get(nextLoc3) == null) 
			{
				blocks.get(0).moveTo(nextLoc);
				blocks.get(1).moveTo(nextLoc2);
				blocks.get(2).moveTo(nextLoc3);
				rotationPos = (rotationPos + 1) % 2;// will be % 4 with 4 blocks
			}	
		}
		
	}
}
