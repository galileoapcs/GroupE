import info.gridworld.actor.Actor;
import info.gridworld.actor.Flower;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.awt.Color;
	
	
public class TetrisBlockT extends TetrisBlock
{
	
	public TetrisBlockT()
	{
		super();
		TetrisBug b = new TetrisBug(Color.green);
		TetrisBug c = new TetrisBug(Color.yellow);
		b.putSelfInGrid(gr, new Location(0,6));
		c.putSelfInGrid(gr, new Location(0,4));
		blocks.add(b);
		blocks.add(c);
		
	}
		
	public boolean canMoveDown() {
		if(rotationPos==0){
			return canMove() && blocks.get(1).canMove() && blocks.get(2).canMove();
		}
		else if(rotationPos==1 ){
			return blocks.get(1).canMove();
		}
		else if(rotationPos==2 ){
			return canMove() && blocks.get(1).canMove() && blocks.get(2).canMove();
		}
		else if(rotationPos==3 ){
			return blocks.get(2).canMove();
		}
		return false;
	}
	public void moveDown()
	{
		if(rotationPos==0)
		{
			move();
			blocks.get(0).move();
			blocks.get(2).move();
			blocks.get(1).move();
		}
		else if(rotationPos==1)
		{
			blocks.get(1).move();
			move();
			blocks.get(2).move();
			blocks.get(0).move();
		}
		else if(rotationPos==2)
		{
			move();
			blocks.get(1).move();
			blocks.get(2).move();
			blocks.get(0).move();
		}
		else if(rotationPos==3)
		{
			blocks.get(2).move();
			move();
			blocks.get(0).move();
			blocks.get(1).move();
		}
		
	}
	public void moveRight() {
		setDirection(90);
		
		for (TetrisBug tb : blocks)
			tb.setDirection(90);
		if(rotationPos==0)
		{
			if (blocks.get(1).canMove()) {
				move();
				blocks.get(1).move();
				blocks.get(0).move();
				blocks.get(2).move();		
			}
		}
		else if(rotationPos==1)
		{
			if(blocks.get(2).canMove()){
				blocks.get(2).move();
				move();
				blocks.get(1).move();
				blocks.get(0).move();
			}
		}
		else if(rotationPos==2)
		{
			if(blocks.get(2).canMove()){
				blocks.get(2).move();
				move();
				blocks.get(1).move();
				blocks.get(0).move();
			}
		}
		else if(rotationPos==3)
		{
			if(canMove()){
				move();
				blocks.get(2).move();
				blocks.get(0).move();
				blocks.get(1).move();
			}
		}
		
	}
	public void moveLeft() {
		setDirection(270);
		for (TetrisBug tb : blocks)
			tb.setDirection(270);
		if(rotationPos==0)
		{
			if(blocks.get(2).canMove()) {
				move();
				blocks.get(2).move();
				blocks.get(0).move();
				blocks.get(1).move();
			}
		}
		else if(rotationPos==1)
		{
			if(canMove()){
				move();
				blocks.get(1).move();
				blocks.get(0).move();
				blocks.get(2).move();
			}
		}
		else if(rotationPos==2)
		{
			if(blocks.get(1).canMove()){
				blocks.get(1).move();
				move();
				blocks.get(2).move();
				blocks.get(0).move();
			}
		}
		else if(rotationPos==3)
		{
			if(blocks.get(1).canMove()){
				blocks.get(1).move();
				move();
				blocks.get(2).move();
				blocks.get(0).move();
			}
		}
	}
	public void rotate() {
		Location nextLoc;	
		Location nextLoc2;
		if (rotationPos == 0) {
			// only one block must move
			nextLoc = new Location(getLocation().getRow() +1,
					getLocation().getCol());
			nextLoc2 = new Location(getLocation().getRow(),
					getLocation().getCol()+1);
			if (gr.isValid(nextLoc) && gr.get(nextLoc) == null && gr.isValid(nextLoc2) && gr.get(nextLoc) == null) {
				blocks.get(1).moveTo(nextLoc);
				blocks.get(2).moveTo(nextLoc2);
				rotationPos = (rotationPos + 1)%4;// will be % 4 with 4 blocks
			}
		} 
		else if (rotationPos == 1) {
			nextLoc = new Location(getLocation().getRow(),
					getLocation().getCol() - 1);
			if (gr.isValid(nextLoc) && gr.get(nextLoc) == null) {
				blocks.get(1).moveTo(nextLoc);
				rotationPos = (rotationPos + 1)%4;
						// will be % 4 with 4 blocks
			}
		} 
		else if (rotationPos == 2) {
			nextLoc = new Location(getLocation().getRow() + 1,
					getLocation().getCol());
			if (gr.isValid(nextLoc) && gr.get(nextLoc) == null) {
				blocks.get(2).moveTo(nextLoc);
				rotationPos = (rotationPos + 1)%4;
						// will be % 4 with 4 blocks
			}
		}
		else if (rotationPos == 3) {
			nextLoc = new Location(getLocation().getRow() - 1,
					getLocation().getCol() - 1);
			nextLoc2 = new Location(getLocation().getRow() -1,
					getLocation().getCol() + 1);
			if (gr.isValid(nextLoc) && gr.get(nextLoc) == null && gr.isValid(nextLoc2) && gr.get(nextLoc) == null) {
				blocks.get(2).moveTo(nextLoc);
				blocks.get(1).moveTo(nextLoc2);
				rotationPos = (rotationPos + 1)%4;
						// will be % 4 with 4 blocks
			}
		}
	}
}
