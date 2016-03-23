/*
 * Name: Haani Al-Challabi
 * Student ID: 9521577
 * Project: Comp 472
 * Deliverable: #2
 * 
 */
public class Driver {

	public static void main(String agrs[]){
		Mode playMode = new Mode();
		System.out.println("Welcome to Haani's Ganji-Ho Game.\n");
		System.out.println("Game rules: White plays first. Each turn a player will place two tokens of their color.\n" + 
				"If you are white, your two tokens must be placed top-to-bottom and vice-versa, i.e. vertically.\n" +
				"If you are black, your two tokens must be placed left-to-right and vice-versa, i.e. horizontally.\n" + 
				"First player with no places available to place two tokens loses.\n");
		System.out.println("Please select game mode:\n"
				+ "If you would like to play Player Vs. Player please enter: 1\n"
				+ "If you would like to play Player Vs. White Comp please enter: 2\n"
				+ "If you would like to play Player Vs. Black Comp please enter: 3\n"
				+ "If you would like to play Comp Vs. Comp please enter: 4");
		playMode.selectMode();


	}
}
