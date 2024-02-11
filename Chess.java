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

	int timesMoved; // added by us
	
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


	public void moveTo(PieceFile file, int rank) {
        if(this.isValid(file, rank)) {
            board[pieceRank][pieceFile] = null;
            if(!(board[pieceRank][pieceFile].isEmpty)) {
                ChessPiece killed = board[rank][file];
                System.out.println("CHESSPIECE " + toString(killed) +  "WAS KILLED by: " + toString(this));
            }
            board[rank][file] = this;
            this.pieceFile = file;
			this.pieceRank = rank;
            timesMoved++;
        }
    }
    public boolean isValid(PieceFile file, int rank) {
		return false; //MUST OVERRIDE ISVALID (WOULD LIKE TO MAKE RETURNPIECE ABSTRACT BUT CAN NOT)
	}
	
}

class Pawn extends ReturnPiece {
    public Pawn(PieceFile file, int rank, boolean isWhite) {
        if(isWhite) {
			this.PieceType = WP;
		} else {this.PieceType = BP;}
        this.pieceFile = file;
		this.pieceRank = rank;
        this.timesMoved = 0;
    }
    public boolean isValid(PieceFile file, int rank) {
        //attempt 2 will calculate the difference in the moves and if the algo is right it will be valid
        if(this.pieceType == WP) {
            int vertical = rank - this.pieceRank; //positive for white
            int horizontal = file-this.pieceFile; //fix horizontal
            if((vertical == 2 && horizontal == 0) && timesMoved == 0) {
                if(board[rank][file].isEmpty) {
                    return true;
                }
            }
            if((vertical == 1 && horizontal == 0) && timesMoved == 0) {
                if(board[rank][file].isEmpty) {
                    return true;
                }
            }
            if((vertical == 1 && horizontal == 0)) {
                if(board[rank][file].isEmpty) {
                    return true;
                }
            }
            if(vertical == 1 && horizontal == 1) {
                if(!(board[rank][file].isEmpty)) {
                    return true;
                }
            }
            if(vertical == 1 && horizontal == -1) {
                if(!(board[rank][file].isEmpty)) {
                    return true;
                }
            }
            return false;
        } else {
            int vertical = rank - this.pieceRank; //negative for black
            int horizontal = file-this.pieceFile;
            if((vertical == -2 && horizontal == 0) && timesMoved == 0) {
                if(board[rank][file].isEmpty) {
                    return true;
                }
            }
            if((vertical == -1 && horizontal == 0) && timesMoved == 0) {
                if(board[rank][file].isEmpty) {
                    return true;
                }
            }
            if((vertical == -1 && horizontal == 0)) {
                if(board[rank][file].isEmpty) {
                    return true;
                }
            }
            if(vertical == -1 && horizontal == 1) {
                if(!(board[rank][file].isEmpty)) {
                    return true;
                }
            }
            if(vertical == -1 && horizontal == -1) {
                if(!(board[rank][file].isEmpty)) {
                    return true;
                }
            }
            return false;
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
			WP1.pieceFile = PieceFile.a; 
			WP1.pieceRank = 2;

			chessPieces.add(WP1); 

			//White Pawn 2
			ReturnPiece WP2 = new ReturnPiece(); 

			WP2.pieceType = PieceType.WP; 
			WP2.pieceFile = PieceFile.b; 
			WP2.pieceRank = 2;

			chessPieces.add(WP2); 

			//White Pawn 3
			ReturnPiece WP3 = new ReturnPiece(); 

			WP3.pieceType = PieceType.WP; 
			WP3.pieceFile = PieceFile.c; 
			WP3.pieceRank = 2;

			chessPieces.add(WP3); 

			//White Pawn 4
			ReturnPiece WP4 = new ReturnPiece(); 

			WP4.pieceType = PieceType.WP; 
			WP4.pieceFile = PieceFile.d; 
			WP4.pieceRank = 2;
			
			chessPieces.add(WP4); 

			//White Pawn 5
			ReturnPiece WP5 = new ReturnPiece(); 

			WP5.pieceType = PieceType.WP; 
			WP5.pieceFile = PieceFile.e; 
			WP5.pieceRank = 2;
						
			chessPieces.add(WP5); 

			//White Pawn 6
			ReturnPiece WP6 = new ReturnPiece(); 

			WP6.pieceType = PieceType.WP; 
			WP6.pieceFile = PieceFile.f; 
			WP6.pieceRank = 2;
									
			chessPieces.add(WP6); 

			//White Pawn 7
			ReturnPiece WP7 = new ReturnPiece(); 

			WP7.pieceType = PieceType.WP; 
			WP7.pieceFile = PieceFile.g; 
			WP7.pieceRank = 2;
												
			chessPieces.add(WP7); 

			//White Pawn 7
			ReturnPiece WP8 = new ReturnPiece(); 

			WP8.pieceType = PieceType.WP; 
			WP8.pieceFile = PieceFile.h; 
			WP8.pieceRank = 2;
															
			chessPieces.add(WP8); 

			//Black Pawn 1  
			ReturnPiece BP1 = new ReturnPiece(); 

			BP1.pieceType = PieceType.BP; 
			BP1.pieceFile = PieceFile.a; 
			BP1.pieceRank = 7;
	
			chessPieces.add(BP1); 
	
			//Black Pawn 2
			ReturnPiece BP2 = new ReturnPiece(); 
	
			BP2.pieceType = PieceType.BP; 
			BP2.pieceFile = PieceFile.b; 
			BP2.pieceRank = 7;
	
			chessPieces.add(BP2); 
	
			//Black Pawn 3
			ReturnPiece BP3 = new ReturnPiece(); 
	
			BP3.pieceType = PieceType.BP; 
			BP3.pieceFile = PieceFile.c; 
			BP3.pieceRank = 7;
	
			chessPieces.add(BP3); 
	
			//Black Pawn 4
			ReturnPiece BP4 = new ReturnPiece(); 
	
			BP4.pieceType = PieceType.BP; 
			BP4.pieceFile = PieceFile.d; 
			BP4.pieceRank = 7;
				
			chessPieces.add(BP4); 
	
			//Black Pawn 5
			ReturnPiece BP5 = new ReturnPiece(); 
	
			BP5.pieceType = PieceType.BP; 
			BP5.pieceFile = PieceFile.e; 
			BP5.pieceRank = 7;
							
			chessPieces.add(BP5); 
	
			//Black Pawn 6
			ReturnPiece BP6 = new ReturnPiece(); 
	
			BP6.pieceType = PieceType.BP; 
			BP6.pieceFile = PieceFile.f; 
			BP6.pieceRank = 7;
										
			chessPieces.add(BP6); 
	
			//Black Pawn 7
			ReturnPiece BP7 = new ReturnPiece(); 
	
			BP7.pieceType = PieceType.BP; 
			BP7.pieceFile = PieceFile.g; 
			BP7.pieceRank = 7;
													
			chessPieces.add(BP7); 
	
			//Black Pawn 8
			ReturnPiece BP8 = new ReturnPiece(); 
	
			BP8.pieceType = PieceType.BP; 
			BP8.pieceFile = PieceFile.h; 
			BP8.pieceRank = 7;
																
			chessPieces.add(BP8); 
			
		//readInputs();
		//check if the move is valid then readinputs again. Make sure to check 
	}
}
