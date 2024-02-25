//Lakshya Gour 
//Dhruv Shidhaye 
package chess;
//imports 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

import chess.ReturnPiece.PieceFile;
import chess.ReturnPiece.PieceType;

public class GlobalClass {
    // Static field to contain inputs
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

//_______________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________//
class ChessPiece extends ReturnPiece {
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
        }
    }
    public boolean isValid(PieceFile file, int rank) {
		return false; //MUST OVERRIDE ISVALID (WOULD LIKE TO MAKE RETURNPIECE ABSTRACT BUT CAN NOT)
	}
}

class Pawn extends ChessPiece {
	int timesMoved; // added by us
	public Pawn() {
		this.pieceType = WP;
        this.pieceFile = PieceFile(a);
		this.pieceRank = 0;
        this.timesMoved = 0;
    }
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
            int horizontal = file.ordinal()-this.pieceFile.ordinal(); //fix horizontal
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
            int horizontal = file.ordinal()-this.pieceFile.ordinal();
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
	}
	public void moveTo(PieceFile file, int rank) {
        super.moveTo(file, rank);
		this.timesMoved++;
    }
}

public class Bishop extends ChessPiece {

    public Bishop(PieceFile file, int rank, boolean isWhite) {
        super(); 
        this.pieceFile = file;
        this.pieceRank = rank;
        this.pieceType = isWhite ? PieceType.WB : PieceType.BB;
    }

    public boolean isValid(PieceFile file, int rank) {
        int vertical = rank - this.pieceRank;
        int horizontal = file.ordinal() - this.pieceFile.ordinal();
        
        if (Math.abs(vertical) == Math.abs(horizontal)) {
            return true;
        }
        return false;
		
    }
}

class Rook extends ChessPiece {
	public Rook() {
		this.pieceType = WR;
        this.pieceFile = PieceFile(a);
		this.pieceRank = 0;
        this.timesMoved = 0;
    }
    public Rook(PieceFile file, int rank, boolean isWhite) {
        if(isWhite) {
			this.PieceType = WR;
		} else {this.PieceType = BR;}
        this.pieceFile = file;
		this.pieceRank = rank;
        this.timesMoved = 0;
    }
	public boolean isValid(PieceFile file, int rank) {
		int vertical = rank - this.pieceRank; 
        int horizontal = file.ordinal()-this.pieceFile.ordinal(); 
		if(horizontal == 0 && vertical != 0){
			return true;
		}
		if(vertical == 0 && horizontal != 0){
			return true;
		}
		return false;
	}
	public void moveTo(PieceFile file, int rank) {
        super.moveTo(file, rank);
		this.timesMoved++;
    }
}

class Knight extends ChessPiece {
	public Knight() {
		this.pieceType = WR;
        this.pieceFile = PieceFile(a);
		this.pieceRank = 0;
        this.timesMoved = 0;
    }
    public Knight(PieceFile file, int rank, boolean isWhite) {
        if(isWhite) {
			this.PieceType = WR;
		} else {this.PieceType = BR;}
        this.pieceFile = file;
		this.pieceRank = rank;
        this.timesMoved = 0;
    }
	public boolean isValid(PieceFile file, int rank) {
		int vertical = rank - this.pieceRank; 
        int horizontal = file.ordinal()-this.pieceFile.ordinal(); 
		if((vertical == 3 || vertical == -3) && (horizontal==1 || horizontal ==-1)) {
			return true;
		}
		if((horizontal == 3 || horizontal == -3) && (vertical==1 || vertical==-1)) {
			return true;
		}
		return false;
	}
	public void moveTo(PieceFile file, int rank) {
        super.moveTo(file, rank);
		this.timesMoved++;
    }
}
//_______________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________//



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
	/**
	 * The readInputs method below was implemented by us
	 */
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
	ReturnPiece WP1 = new Pawn(); 

	WP1.pieceType = PieceType.WP; 
	WP1.pieceFile = PieceFile.a; 
	WP1.pieceRank = 1;

	chessPieces.add(WP1); 

	//White Pawn 2
	ReturnPiece WP2 = new Pawn(); 

	WP2.pieceType = PieceType.WP; 
	WP2.pieceFile = PieceFile.b; 
	WP2.pieceRank = 1;

	chessPieces.add(WP1); 

	//White Pawn 3
	ReturnPiece WP3 = new Pawn(); 

	WP3.pieceType = PieceType.WP; 
	WP3.pieceFile = PieceFile.c; 
	WP3.pieceRank = 1;

	chessPieces.add(WP3); 

	//White Pawn 4
	ReturnPiece WP4 = new Pawn(); 

	WP4.pieceType = PieceType.WP; 
	WP4.pieceFile = PieceFile.d; 
	WP4.pieceRank = 1;
	
	chessPieces.add(WP4); 

	//White Pawn 5
	ReturnPiece WP5 = new Pawn(); 

	WP5.pieceType = PieceType.WP; 
	WP5.pieceFile = PieceFile.e; 
	WP5.pieceRank = 1;
				
	chessPieces.add(WP5); 

	//White Pawn 6
	ReturnPiece WP6 = new Pawn(); 

	WP6.pieceType = PieceType.WP; 
	WP6.pieceFile = PieceFile.f; 
	WP6.pieceRank = 1;
							
	chessPieces.add(WP6); 

	//White Pawn 7
	ReturnPiece WP7 = new Pawn(); 

	WP7.pieceType = PieceType.WP; 
	WP7.pieceFile = PieceFile.g; 
	WP7.pieceRank = 1;
										
	chessPieces.add(WP7); 

	//White Pawn 7
	ReturnPiece WP8 = new Pawn(); 

	WP8.pieceType = PieceType.WP; 
	WP8.pieceFile = PieceFile.h; 
	WP8.pieceRank = 1;
													
	chessPieces.add(WP8); 

	//Black Pawn 1  
	ReturnPiece BP1 = new Pawn(); 

	BP1.pieceType = PieceType.BP; 
	BP1.pieceFile = PieceFile.a; 
	BP1.pieceRank = 6;
;

	chessPieces.add(BP1); 

	//Black Pawn 2
	ReturnPiece BP2 = new Pawn(); 

	BP2.pieceType = PieceType.BP; 
	BP2.pieceFile = PieceFile.b; 
	BP2.pieceRank = 6;

	chessPieces.add(BP1); 

	//Black Pawn 3
	ReturnPiece BP3 = new Pawn(); 

	BP3.pieceType = PieceType.BP; 
	BP3.pieceFile = PieceFile.c; 
	BP3.pieceRank = 6;

	chessPieces.add(BP3); 

	//Black Pawn 4
	ReturnPiece BP4 = new Pawn(); 

	BP4.pieceType = PieceType.BP; 
	BP4.pieceFile = PieceFile.d; 
	BP4.pieceRank = 6;
		
	chessPieces.add(BP4); 

	//Black Pawn 5
	ReturnPiece BP5 = new Pawn(); 

	BP5.pieceType = PieceType.BP; 
	BP5.pieceFile = PieceFile.e; 
	BP5.pieceRank = 6;
					
	chessPieces.add(BP5); 

	//Black Pawn 6
	ReturnPiece BP6 = new Pawn(); 

	BP6.pieceType = PieceType.BP; 
	BP6.pieceFile = PieceFile.f; 
	BP6.pieceRank = 6;
								
	chessPieces.add(BP6); 

	//Black Pawn 7
	ReturnPiece BP7 = new Pawn(); 

	BP7.pieceType = PieceType.BP; 
	BP7.pieceFile = PieceFile.g; 
	BP7.pieceRank = 6;
											
	chessPieces.add(BP7); 

	//Black Pawn 8
	ReturnPiece BP8 = new Pawn(); 

	BP8.pieceType = PieceType.BP; 
	BP8.pieceFile = PieceFile.h; 
	BP8.pieceRank = 6;
														
	chessPieces.add(BP8);
			 
	//White Rook 1
	ReturnPiece WR1 = new Rook(); 

	WR1.pieceType = PieceType.WR; 
	WR1.pieceFile = PieceFile.a; 
	WR1.pieceRank = 0;
														
	chessPieces.add(WR1);

	//White Rook 2
	ReturnPiece WR2 = new Rook(); 

	WR2.pieceType = PieceType.WR; 
	WR2.pieceFile = PieceFile.h; 
	WR2.pieceRank = 0;
														
	chessPieces.add(WR2);

	//Black Rook 1
	ReturnPiece BR1 = new Rook(); 

	BR1.pieceType = PieceType.BR; 
	BR1.pieceFile = PieceFile.a; 
	BR1.pieceRank = 7;
														
	chessPieces.add(BR1);

	//Black Rook 2
	ReturnPiece BR2 = new Rook(); 

	BR2.pieceType = PieceType.BR; 
	BR2.pieceFile = PieceFile.h; 
	BR2.pieceRank = 7;
														
	chessPieces.add(BR2);

	//White Knight 1
	ReturnPiece WK1 = new Knight(); 

	WK1.pieceType = PieceType.WK; 
	WK1.pieceFile = PieceFile.b; 
	WK1.pieceRank = 0;
														
	chessPieces.add(WK1);

	//White Knight 2
	ReturnPiece WK2 = new Knight(); 

	WK2.pieceType = PieceType.WK; 
	WK2.pieceFile = PieceFile.g; 
	WK2.pieceRank = 0;
														
	chessPieces.add(WK2);

	//Black Knight 1
	ReturnPiece BK1 = new Knight(); 

	BK1.pieceType = PieceType.BK; 
	BK1.pieceFile = PieceFile.b; 
	BK1.pieceRank = 7;
														
	chessPieces.add(BK1);
	//Black Knight 2
	ReturnPiece BK2 = new Knight(); 

	BK2.pieceType = PieceType.BK; 
	BK2.pieceFile = PieceFile.g; 
	BK2.pieceRank = 7;
														
	chessPieces.add(BK2);

	

		//readInputs();
		//check if the move is valid then readinputs again. Make sure to check 
	}
}