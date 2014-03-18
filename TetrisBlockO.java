import info.gridworld.grid.Location;

import java.awt.Color;
	
	
public class TetrisBlockO extends TetrisBlock
{
	
	public TetrisBlockO()
	{
		super();
		TetrisBug b = new TetrisBug(Color.blue);
		TetrisBug c = new TetrisBug(Color.blue);
		b.putSelfInGrid(gr, new Location(0,6));
		c.putSelfInGrid(gr, new Location(1,6));
		blocks.add(b);
		blocks.add(c);
	}
		
		
	public void moveDown()
	{
		move();
		blocks.get(0).move();
		blocks.get(2).move();
		blocks.get(1).move();
		
	}
	public void moveRight() {
		setDirection(90);
		for (TetrisBug tb : blocks)
			tb.setDirection(90);
			if (blocks.get(1).canMove() && blocks.get(2).canMove()) {
				blocks.get(1).move();
				blocks.get(2).move();
				move();
				blocks.get(0).move();
			}
	}
	public void moveLeft() {
		setDirection(270);
		for (TetrisBug tb : blocks)
			tb.setDirection(270);
			if (canMove() && blocks.get(0).canMove()) {
				move();
				blocks.get(0).move();
				blocks.get(1).move();
				blocks.get(2).move();
				
		}
	}
}
