package util;

import java.util.BitSet;

import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;


public class Buffer{
	
	private static BitSet keys = new BitSet();
	
	
	private static boolean mouseInWindow = false;
	private static boolean mouseLeftPressed = false;
	private static boolean mouseRightPressed = false;
	private static boolean mouseMiddlePressed = false;
	private static Point2D mouseNodeLocation = new Point2D(0,0); 	
	private static Point2D mouseSceneLocation = new Point2D(0,0); 	
	private static Point2D mouseScreenLocation = new Point2D(0,0); 	
	
	
	public static void handleKeyPressed(KeyEvent event) {
		
		keys.set(event.getCode().ordinal(), true);
	}	
	
	public static void handleKeyReleased(KeyEvent event) {
		
		keys.set(event.getCode().ordinal(), false);
	}
	
	
	public static void handleMouseMoved(MouseEvent event) {
		
		mouseNodeLocation = new Point2D(event.getX(), event.getY());
		mouseSceneLocation = new Point2D(event.getSceneX(), event.getSceneY());
		mouseScreenLocation = new Point2D(event.getScreenX(), event.getScreenY());
	}	
	
	public static void handleMousePressed(MouseEvent event) {
		
		if(event.getButton() == MouseButton.PRIMARY) {
			mouseLeftPressed = true;
		}
		
		if(event.getButton() == MouseButton.SECONDARY) {
			mouseRightPressed = true;
		}
		
		if(event.getButton() == MouseButton.MIDDLE) {
			mouseMiddlePressed = true;
		}
	}	
	
	public static void handleMouseReleased(MouseEvent event) {
		
		if(event.getButton() == MouseButton.PRIMARY) {
			mouseLeftPressed = false;
		}
		
		if(event.getButton() == MouseButton.SECONDARY) {
			mouseRightPressed = false;
		}
		
		if(event.getButton() == MouseButton.MIDDLE) {
			mouseMiddlePressed = false;
		}
	}	
	
	public static void handleMouseDragged(MouseEvent event) {
		handleMousePressed(event);
		handleMouseMoved(event);		
	}
	
	public static void handleMouseExited(MouseEvent event) {
		mouseInWindow = false;
	}	
	
	public static void handleMouseEntered(MouseEvent event) {
		mouseInWindow = true;
	}
	
	
	public static boolean isKeyPressed(KeyCode code) {
		return keys.get(code.ordinal());
	}
	
	public static boolean isMouseInWindow() {
		return mouseInWindow;
	}
	
	public static Point2D getMouseNodeLocation() {
		return mouseNodeLocation;
	}
	
	public static Point2D getMouseSceneLocation() {
		return mouseSceneLocation;
	}
	
	public static Point2D getMouseScreenLocation() {
		return mouseScreenLocation;
	}
	
	public static boolean isLeftMousePressed() {
		return mouseLeftPressed;
	}
	
	public static boolean isRightMousePressed() {
		return mouseRightPressed;
	}
	
	public static boolean isMiddleMousePressed() {
		return mouseMiddlePressed;
	}
}
