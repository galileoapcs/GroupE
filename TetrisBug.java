import java.awt.Color;
import info.gridworld.actor.*;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

public class TetrisBug extends Bug
{
	
	public TetrisBug(Color c)
	{
		super(c);
		setDirection(180);
	}
	
	public void move() 
	{ 
		Grid<Actor> gr = getGrid(); 
		if (gr == null)
		{
			return;
		}
		Location loc = getLocation(); 
		Location next = loc.getAdjacentLocation(getDirection()); 
		if (gr.isValid(next)) 
		{
			moveTo(next); 
		}
		else 
		{	 
			removeSelfFromGrid(); 
		}
	 }
	
	public void act()
	{
		
	}
}
