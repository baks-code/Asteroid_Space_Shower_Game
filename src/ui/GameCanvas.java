package ui;

import util.Buffer;
import visitordesignpattern.RenderGameObjectVisitor;
import javafx.scene.canvas.Canvas;


public class GameCanvas extends Canvas{
	
	RenderGameObjectVisitor visitor = new RenderGameObjectVisitor();
	
	
	public GameCanvas() {
		setUpEventListeners();		
	}
	
	
	public void redrawCanvas(){
		
	}
	
	private void setUpEventListeners() {
		
		this.setOnKeyPressed((event)    -> { Buffer.handleKeyPressed(event);    });
		this.setOnKeyReleased((event)   -> { Buffer.handleKeyReleased(event);   });
		//Listeners Needed
		this.setOnMouseMoved((event)    -> { Buffer.handleMouseMoved(event);    });		
		this.setOnMousePressed((event)  -> { Buffer.handleMousePressed(event);  });
		this.setOnMouseReleased((event) -> { Buffer.handleMouseReleased(event); });
		this.setOnMouseDragged((event)  -> { Buffer.handleMouseDragged(event);  });
		this.setOnMouseEntered((event)  -> { Buffer.handleMouseEntered(event);  });
		this.setOnMouseExited((event)   -> { Buffer.handleMouseExited(event);   });
		//Event Handler needed
	}
}
