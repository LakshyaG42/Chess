//Lakshya Gour 
//Dhruv Shidhaye 
package chess;
//imports 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

import chess.ReturnPiece.PieceType;

public class GlobalClass {
    // Static field
    public static string startpos;
	public static string endpos;
	public static string thirdword;
}

class ReturnPiece {
	static enum PieceType {WP, WR, WN, WB, WQ, WK, 
		            BP, BR, BN, BB, BK, BQ};
	static enum PieceFile {a, b, c, d, e, f, g, h};
	
	PieceType pieceType;
	PieceFile pieceFile;
	int pieceRank;  // 1..8
	public String toString() {
		return ""+pieceFile+pieceRank+":"+pieceType;
	}
	public boolean equals(Object other) {
		if (other == null || !(other instanceof ReturnPiece)) {
			return false;
		}
		ReturnPiece otherPiece = (ReturnPiece)other;
		return pieceType == otherPiece.pieceType &&
				pieceFile == otherPiece.pieceFile &&
				pieceRank == otherPiece.pieceRank;
	}
}

class ReturnPlay {
	enum Message {ILLEGAL_MOVE, DRAW, 
				  RESIGN_BLACK_WINS, RESIGN_WHITE_WINS, 
				  CHECK, CHECKMATE_BLACK_WINS,	CHECKMATE_WHITE_WINS, 
				  STALEMATE};
	
	ArrayList<ReturnPiece> piecesOnBoard;
	Message message;
}
public class Chess { 
	
	enum Player { white, black }
	
	/**
	 * Plays the next move for whichever player has the turn.
	 * 
	 * @param move String for next move, e.g. "a2 a3"
	 * 
	 * @return A ReturnPlay instance that contains the result of the move.
	 *         See the section "The Chess class" in the assignment description for details of
	 *         the contents of the returned ReturnPlay instance.
	 */
	public static ReturnPlay play(String move) {

		/* FILL IN THIS METHOD */
		
		/* FOLLOWING LINE IS A PLACEHOLDER TO MAKE COMPILER HAPPY */
		/* WHEN YOU FILL IN THIS METHOD, YOU NEED TO RETURN A ReturnPlay OBJECT */
		return null;
	}
	
	public static void readInputs() {
		Scanner inputs = new Scanner(System.in);
		System.out.print("What's the move!?: "); //asks for input
		String line = scanner.nextLine();
		Scanner lineScan = new Scanner(line);
		int count = 0; 
		String word = lineScanner.next();
        GlobalClass.startpos = word;
    	if(lineScanner.hasNext()) {word = lineScanner.next(); GlobalClass.endpos = word;} // if there is a second word it will read it
		if(lineScanner.hasNext()) {word = lineScanner.next(); GlobalClass.thirdword = word;} //if asks for a draw 
		scanner.close();
        lineScanner.close();
	}
	/**
	 * This method should reset the game, and start from scratch.
	 */
	public static void start() {
		/* FILL IN THIS METHOD */

		//setBoard();
		//Hold peices
			ArrayList<ReturnPiece> chessPieces = new ArrayList<>();
		//Pawn 1 ex (do for all 16 lol): 
			ReturnPiece WP1 = new ReturnPiece(); 

			WP1.pieceType = PieceType.WP; 
			WP1.pieceFile = PieceType.a; 
			wp1.pieceRank = 2;

			chessPieces.add(WP1); 

		//readInputs();
		//check if the move is valid then readinputs again. Make sure to check 
	}
}
