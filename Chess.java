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
		//White Pawn 1 ex (do for all 16 lol): 
			ReturnPiece WP1 = new ReturnPiece(); 

			WP1.pieceType = PieceType.WP; 
			WP1.pieceFile = PieceType.a; 
			WP1.pieceRank = 2;

			chessPieces.add(WP1); 

			//White Pawn 2
			ReturnPiece WP2 = new ReturnPiece(); 

			WP2.pieceType = PieceType.WP; 
			WP2.pieceFile = PieceType.b; 
			WP2.pieceRank = 2;

			chessPieces.add(WP2); 

			//White Pawn 3
			ReturnPiece WP3 = new ReturnPiece(); 

			WP3.pieceType = PieceType.WP; 
			WP3.pieceFile = PieceType.c; 
			WP3.pieceRank = 2;

			chessPieces.add(WP3); 

			//White Pawn 4
			ReturnPiece WP4 = new ReturnPiece(); 

			WP4.pieceType = PieceType.WP; 
			WP4.pieceFile = PieceType.d; 
			WP4.pieceRank = 2;
			
			chessPieces.add(WP4); 

			//White Pawn 5
			ReturnPiece WP5 = new ReturnPiece(); 

			WP5.pieceType = PieceType.WP; 
			WP5.pieceFile = PieceType.e; 
			WP5.pieceRank = 2;
						
			chessPieces.add(WP5); 

			//White Pawn 6
			ReturnPiece WP6 = new ReturnPiece(); 

			WP6.pieceType = PieceType.WP; 
			WP6.pieceFile = PieceType.f; 
			WP6.pieceRank = 2;
									
			chessPieces.add(WP6); 

			//White Pawn 7
			ReturnPiece WP7 = new ReturnPiece(); 

			WP7.pieceType = PieceType.WP; 
			WP7.pieceFile = PieceType.g; 
			WP7.pieceRank = 2;
												
			chessPieces.add(WP7); 

			//White Pawn 7
			ReturnPiece WP8 = new ReturnPiece(); 

			WP8.pieceType = PieceType.WP; 
			WP8.pieceFile = PieceType.h; 
			WP8.pieceRank = 2;
															
			chessPieces.add(WP8); 

			//Black Pawn 1  
			ReturnPiece BP1 = new ReturnPiece(); 

			BP1.pieceType = PieceType.BP; 
			BP1.pieceFile = PieceType.a; 
			BP1.pieceRank = 7;
	
			chessPieces.add(BP1); 
	
			//Black Pawn 2
			ReturnPiece BP2 = new ReturnPiece(); 
	
			BP2.pieceType = PieceType.BP; 
			BP2.pieceFile = PieceType.b; 
			BP2.pieceRank = 7;
	
			chessPieces.add(BP2); 
	
			//Black Pawn 3
			ReturnPiece BP3 = new ReturnPiece(); 
	
			BP3.pieceType = PieceType.BP; 
			BP3.pieceFile = PieceType.c; 
			BP3.pieceRank = 7;
	
			chessPieces.add(BP3); 
	
			//Black Pawn 4
			ReturnPiece BP4 = new ReturnPiece(); 
	
			BP4.pieceType = PieceType.BP; 
			BP4.pieceFile = PieceType.d; 
			BP4.pieceRank = 7;
				
			chessPieces.add(BP4); 
	
			//Black Pawn 5
			ReturnPiece BP5 = new ReturnPiece(); 
	
			BP5.pieceType = PieceType.BP; 
			BP5.pieceFile = PieceType.e; 
			BP5.pieceRank = 7;
							
			chessPieces.add(BP5); 
	
			//Black Pawn 6
			ReturnPiece BP6 = new ReturnPiece(); 
	
			BP6.pieceType = PieceType.BP; 
			BP6.pieceFile = PieceType.f; 
			BP6.pieceRank = 7;
										
			chessPieces.add(BP6); 
	
			//Black Pawn 7
			ReturnPiece BP7 = new ReturnPiece(); 
	
			BP7.pieceType = PieceType.BP; 
			BP7.pieceFile = PieceType.g; 
			BP7.pieceRank = 7;
													
			chessPieces.add(BP7); 
	
			//Black Pawn 8
			ReturnPiece BP8 = new ReturnPiece(); 
	
			BP8.pieceType = PieceType.BP; 
			BP8.pieceFile = PieceType.h; 
			BP8.pieceRank = 7;
																
			chessPieces.add(BP8); 
			
		//readInputs();
		//check if the move is valid then readinputs again. Make sure to check 
	}
}
