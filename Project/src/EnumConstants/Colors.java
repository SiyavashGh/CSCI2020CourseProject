package EnumConstants;

import java.awt.Color;
//basic color initilization

public enum Colors {
	BLACK(Color.BLACK),
	WHITE(Color.WHITE),
	RED(Color.RED),
	BLUE(new Color(0,0,255)),
	PURPLE(new Color(128,100,162)),
	YELLOW(Color.YELLOW);
	
	
	//properties
	private Color color;
	
	//Constructor
	Colors(Color color){
		this.color = color;
	}
	
	public Color getColor(){
		return this.color;
	}

	//default colour
	public static Color getMyDefaultColor(int ID){
		if(ID==1){
			return RED.getColor();
		}
		else if(ID==2){
			return BLUE.getColor();
		}
		
		return null;
	}
	//focus colour
	public static Color getFocusedColor(int ID){
		if(ID==1){
			return PURPLE.getColor();
		}
		else if(ID==2){
			return YELLOW.getColor();
		}		
		return null;
	}
}
