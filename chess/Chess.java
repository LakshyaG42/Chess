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
//IF COLLISSION CHECK DOESNT WORK DO IT DHRUVS WAY
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
class StorageBoard {
    // Static field to contain inputs
    static ReturnPiece[][] storageBoard = new ReturnPiece[8][8]; 

}
//_______________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________//
class ChessPiece extends ReturnPiece {
	public int timesMoved;
	public void moveTo(PieceFile file, int rank) {
        StorageBoard.storageBoard[pieceRank-1][pieceFile.ordinal()-1] = null;
        if(!(StorageBoard.storageBoard[pieceRank-1][pieceFile.ordinal()-1] == null)) {
			//was set to type chesspiece but we cant initialize for static using subclass silly 
            ChessPiece killed = (ChessPiece)StorageBoard.storageBoard[rank-1][file.ordinal()-1];         
			//toString if shit goes wrong       
			System.out.println("CHESSPIECE: " + killed +  "WAS KILLED by: " + this);
            }
        StorageBoard.storageBoard[rank-1][file.ordinal()-1] = this;
        this.pieceFile = file;
		this.pieceRank = rank;
    }
    public boolean isValid(PieceFile file, int rank) {
		return true; //MUST OVERRIDE ISVALID (WOULD LIKE TO MAKE RETURNPIECE ABSTRACT BUT CAN NOT)
	}
}

class Pawn extends ChessPiece {
	public Pawn() {
		this.pieceType = PieceType.WP;
        this.pieceFile = PieceFile.a;
		this.pieceRank = 0;
        this.timesMoved = 0;
    }
    public Pawn(PieceFile file, int rank, boolean isWhite) {
        if(isWhite) {
			this.pieceType = PieceType.WP;
		} else {this.pieceType = PieceType.BP;}
        this.pieceFile = file;
		this.pieceRank = rank;
        this.timesMoved = 0;
    }
    public boolean isValid(PieceFile file, int rank) {
        //attempt 2 will calculate the difference in the moves and if the algo is right it will be valid
        if(this.pieceType == PieceType.WP) {
            int vertical = rank - this.pieceRank; //positive for white
            int horizontal = file.ordinal()-this.pieceFile.ordinal(); //fix horizontal
            if((vertical == 2 && horizontal == 0) && timesMoved == 0) {
                if(!(StorageBoard.storageBoard[this.pieceRank-1+1][this.pieceFile.ordinal()-1] == null)) {
					return false;
				}
				return true;
            } 
            if((vertical == 1 && horizontal == 0)) {
                return true;
            }
            if(vertical == 1 && horizontal == 1) {
                return true;
            }
            if(vertical == 1 && horizontal == -1) {
                return true;
            }
            return false;
        } else {
            int vertical = rank - this.pieceRank; //negative for black
            int horizontal = file.ordinal()-this.pieceFile.ordinal();
            if((vertical == -2 && horizontal == 0) && timesMoved == 0) {
                if(!(StorageBoard.storageBoard[this.pieceRank-1-1][this.pieceFile.ordinal()-1] == null)) {
					return false;
				}
				return true;
            }
            if((vertical == -1 && horizontal == 0)) {
                return true;
            }
            if(vertical == -1 && horizontal == 1) {
                return true;
            }
            if(vertical == -1 && horizontal == -1) {
                return true;
            }
            return false;
        }
	}
	public void moveTo(PieceFile file, int rank) {
        if (this.isValid(file, rank)) {
            super.moveTo(file, rank); 
            this.timesMoved++;
        }
    }
}

class Bishop extends ChessPiece {

	public Bishop() {
		this.pieceType = PieceType.WR;
        this.pieceFile = PieceFile.a;
		this.pieceRank = 0;
        this.timesMoved = 0;
    }

    public Bishop(PieceFile file, int rank, boolean isWhite) {
        super(); 
        this.pieceFile = file;
        this.pieceRank = rank;
        this.pieceType = isWhite ? PieceType.WB : PieceType.BB;
		this.timesMoved = 0; 
    }

    public boolean isValid(PieceFile file, int rank) {
    	int vertical = rank - this.pieceRank;
    	int horizontal = file.ordinal() - this.pieceFile.ordinal();

    /* 
        if (Math.abs(vertical) == Math.abs(horizontal)) {
            return true;
        }
        return false;
	*/
	
		if (Math.abs(horizontal) == Math.abs(vertical)) {

			if (horizontal > 0 && vertical > 0) {
				// Top-right diagonal
				for (int i = 1; i < horizontal; i++) {
					if (StorageBoard.storageBoard[this.pieceRank - 1 + i][this.pieceFile.ordinal() - 1 + i] != null) {
						return false;
					}
				}

			} else if (horizontal < 0 && vertical > 0) {
				// Top-left diagonal
				for (int i = 1; i < Math.abs(horizontal); i++) {
					if (StorageBoard.storageBoard[this.pieceRank - 1 + i][this.pieceFile.ordinal() - 1 - i] != null) {
						return false;
					}
				}

			} else if (horizontal > 0 && vertical < 0) {
				// Bottom-right diagonal
				for (int i = 1; i < horizontal; i++) {
					if (StorageBoard.storageBoard[this.pieceRank - 1 - i][this.pieceFile.ordinal() - 1 + i] != null) {
						return false;
					}
				}

			} else if (horizontal < 0 && vertical < 0) {
				// Bottom-left diagonal
				for (int i = 1; i < Math.abs(horizontal); i++) {
					if (StorageBoard.storageBoard[this.pieceRank - 1 - i][this.pieceFile.ordinal() - 1 - i] != null) {
						return false;
					}
				}
				
			}
			return true; // clear path
		}
		return false; // boo not clear
	}
	
	public void moveTo(PieceFile file, int rank) {
        if (this.isValid(file, rank)) {
            super.moveTo(file, rank); 
            this.timesMoved++;
        }
    }

    }


class Queen extends ChessPiece {

	public Queen() {
		this.pieceType = PieceType.WR;
        this.pieceFile = PieceFile.a;
		this.pieceRank = 0;
        this.timesMoved = 0;
    }

    public Queen(PieceFile file, int rank, boolean isWhite) {
        super(); 
        this.pieceFile = file;
        this.pieceRank = rank;
        this.pieceType = isWhite ? PieceType.WQ : PieceType.BQ;
        this.timesMoved = 0; 
    }

    public boolean isValid(PieceFile file, int rank) {
        int verticalMove = Math.abs(rank - this.pieceRank);
        int horizontalMove = Math.abs(file.ordinal() - this.pieceFile.ordinal());
        
        boolean isDiagonalMove = verticalMove == horizontalMove;
        boolean isStraightMove = verticalMove == 0 || horizontalMove == 0;

		//collision check below: 
		int vertical = rank - this.pieceRank; 
        int horizontal = file.ordinal()-this.pieceFile.ordinal(); 
		if(isDiagonalMove){
			//bishop code
		}
		if(isStraightMove){
			//rook code
			if(horizontal == 0 && vertical != 0){
				if(vertical > 0) {
					for (int i = 1; i < vertical; i++) {
						if(!(StorageBoard.storageBoard[this.pieceRank-1 + i][this.pieceFile.ordinal()-1] == null)) {
							return false;
						}
					}
				} else {
					for (int i = 1; i < Math.abs(vertical); i++) {
						if(!(StorageBoard.storageBoard[this.pieceRank-1 - i][this.pieceFile.ordinal()-1] == null)) {
							return false;
						}
					}
				}
				return true;
			}
			if(vertical == 0 && horizontal != 0){
				if(horizontal > 0) {
					for (int i =1 - 1; i < horizontal; i++) {
						if(!(StorageBoard.storageBoard[this.pieceRank - 1][this.pieceFile.ordinal() - 1  + i] == null)) {
							return false;
						}
					}
				} else {
					for (int i =1 - 1; i < Math.abs(horizontal); i++) {
						if(!(StorageBoard.storageBoard[this.pieceRank - 1][this.pieceFile.ordinal() - 1  - i] == null)) {
							return false;
						}
					}
				}
				return true;
			}
		}
		return false;
    }

    public void moveTo(PieceFile file, int rank) {
        if (this.isValid(file, rank)) {
            super.moveTo(file, rank); 
            this.timesMoved++;
        }
    }
}



class Rook extends ChessPiece {
	public Rook() {
		this.pieceType = PieceType.WR;
        this.pieceFile = PieceFile.a;
		this.pieceRank = 0;
        this.timesMoved = 0;
    }
    public Rook(PieceFile file, int rank, boolean isWhite) {
        if(isWhite) {
			this.pieceType = PieceType.WR;
		} else {this.pieceType = PieceType.BR;}
        this.pieceFile = file;
		this.pieceRank = rank;
        this.timesMoved = 0;
    }
	public boolean isValid(PieceFile file, int rank) {
		int vertical = rank - this.pieceRank; 
        int horizontal = file.ordinal()-this.pieceFile.ordinal(); 
		if(horizontal == 0 && vertical != 0){
			if(vertical > 0) {
				for (int i = 1; i < vertical; i++) {
					if(!(StorageBoard.storageBoard[this.pieceRank-1 + i][this.pieceFile.ordinal()-1] == null)) {
						return false;
					}
				}
			} else {
				for (int i = 1; i < Math.abs(vertical); i++) {
					if(!(StorageBoard.storageBoard[this.pieceRank-1 - i][this.pieceFile.ordinal()-1] == null)) {
						return false;
					}
				}
			}
			return true;
		}
		if(vertical == 0 && horizontal != 0){
			if(horizontal > 0) {
				for (int i =1 - 1; i < horizontal; i++) {
					if(!(StorageBoard.storageBoard[this.pieceRank - 1][this.pieceFile.ordinal() - 1  + i] == null)) {
						return false;
					}
				}
			} else {
				for (int i =1 - 1; i < Math.abs(horizontal); i++) {
					if(!(StorageBoard.storageBoard[this.pieceRank - 1][this.pieceFile.ordinal() - 1  - i] == null)) {
						return false;
					}
				}
			}
			return true;
		}
		return false;
	}
	public void moveTo(PieceFile file, int rank) {
        if (this.isValid(file, rank)) {
            super.moveTo(file, rank); 
            this.timesMoved++;
        }
    }
}

class Knight extends ChessPiece {
	public Knight() {
		this.pieceType = PieceType.WK;
        this.pieceFile = PieceFile.b;
		this.pieceRank = 0;
        this.timesMoved = 0;
    }
    public Knight(PieceFile file, int rank, boolean isWhite) {
        if(isWhite) {
			this.pieceType = PieceType.WK;
		} else {this.pieceType = PieceType.BK;}
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
        if (this.isValid(file, rank)) {
            super.moveTo(file, rank); 
            this.timesMoved++;
        }
    }
}

class King extends ChessPiece {
	public King() {
		this.pieceType = PieceType.WK;
        this.pieceFile = PieceFile.b;
		this.pieceRank = 0;
        this.timesMoved = 0;
    }
	public boolean isValid(PieceFile file, int rank) {
		int vertical = rank - this.pieceRank; 
        int horizontal = file.ordinal()-this.pieceFile.ordinal(); 
		if(timesMoved == 0){ //castling
			if(horizontal > 1) { //right
				if(this.pieceType == PieceType.WK) {
					ChessPiece piece = (ChessPiece)StorageBoard.storageBoard[0][7];
					if(piece.timesMoved == 0) {
						for (int i = 1; i < horizontal; i++) {
							if(StorageBoard.storageBoard[0][3+i] != null) {
								return false;
							}
						}
					}
				} else {
					ChessPiece piece = (ChessPiece)StorageBoard.storageBoard[7][7];
					if(piece.timesMoved == 0) {
						for (int i = 1; i < horizontal; i++) {
							if(StorageBoard.storageBoard[7][4+i] != null) {
								return false;
							}
						}
					}
				}
			}
			if(horizontal < -1) { //left
				if(this.pieceType == PieceType.WK) {
					ChessPiece piece = (ChessPiece)StorageBoard.storageBoard[0][0];
					if(piece.timesMoved == 0) {
						for (int i = 1; i < horizontal; i++) {
							if(StorageBoard.storageBoard[0][3-i] != null) {
								return false;
							}
						}
					}
				} else {
					ChessPiece piece = (ChessPiece)StorageBoard.storageBoard[7][0];
					if(piece.timesMoved == 0) {
						for (int i = 1; i < horizontal; i++) {
							if(StorageBoard.storageBoard[7][4-i] != null) {
								return false;
							}
						}
					}
				}
			}
		if(((vertical == 1 || vertical == -1) || (vertical == 0)) && ((horizontal==1 || horizontal ==-1) || (horizontal == 0))) {
			for (ReturnPiece[] row : StorageBoard.storageBoard) {
				for (ReturnPiece returnPiece : row) {
					ChessPiece CP = (ChessPiece)returnPiece;
					if(CP.isValid(file, rank)) { //CHECK CONDITION
						return false;
					}
				}
			}
			return true;
		}
		}
		return false;
	}
	public void moveTo(PieceFile file, int rank) {
        if (this.isValid(file, rank)) {
            StorageBoard.storageBoard[pieceRank-1][pieceFile.ordinal()-1] = null;
			if(!(StorageBoard.storageBoard[pieceRank-1][pieceFile.ordinal()-1] == null)) {
				ChessPiece killed = (ChessPiece)StorageBoard.storageBoard[rank-1][file.ordinal()-1];                
				System.out.println("CHESSPIECE " + killed +  "WAS KILLED by: " + this);
				}
			StorageBoard.storageBoard[rank-1][file.ordinal()-1] = this;
			this.pieceFile = file;
			this.pieceRank = rank;
            this.timesMoved++;
        }
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

		ReturnPlay message = new ReturnPlay(); 
		
		/* FILL IN THIS METHOD */
		
		/* FOLLOWING LINE IS A PLACEHOLDER TO MAKE COMPILER HAPPY */
		/* WHEN YOU FILL IN THIS METHOD, YOU NEED TO RETURN A ReturnPlay OBJECT */
		return null;
	}
	/**
	 * The readInputs method below was implemented by us
	 
	public static void readInputs() {
		Scanner inputs = new Scanner(System.in);
		System.out.print("What's the move!?: "); //asks for input
		String line = inputs.nextLine();
		Scanner lineScan = new Scanner(line);
		int count = 0; 
		String word = lineScan.next();
        StorageBoard.startpos = word;
    	if(lineScan.hasNext()) {word = lineScan.next(); StorageBoard.endpos = word;} // if there is a second word it will read it
		if(lineScan.hasNext()) {word = lineScan.next(); StorageBoard.thirdword = word;} //if asks for a draw 
		inputs.close();
        lineScan.close();
	}
	*/

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
	WP1.pieceRank = 2;

	chessPieces.add(WP1); 

	//White Pawn 2
	ReturnPiece WP2 = new Pawn(); 

	WP2.pieceType = PieceType.WP; 
	WP2.pieceFile = PieceFile.b; 
	WP2.pieceRank = 2;

	chessPieces.add(WP1); 

	//White Pawn 3
	ReturnPiece WP3 = new Pawn(); 

	WP3.pieceType = PieceType.WP; 
	WP3.pieceFile = PieceFile.c; 
	WP3.pieceRank = 2;

	chessPieces.add(WP3); 

	//White Pawn 4
	ReturnPiece WP4 = new Pawn(); 

	WP4.pieceType = PieceType.WP; 
	WP4.pieceFile = PieceFile.d; 
	WP4.pieceRank = 2;
	
	chessPieces.add(WP4); 

	//White Pawn 5
	ReturnPiece WP5 = new Pawn(); 

	WP5.pieceType = PieceType.WP; 
	WP5.pieceFile = PieceFile.e; 
	WP5.pieceRank = 2;
				
	chessPieces.add(WP5); 

	//White Pawn 6
	ReturnPiece WP6 = new Pawn(); 

	WP6.pieceType = PieceType.WP; 
	WP6.pieceFile = PieceFile.f; 
	WP6.pieceRank = 2;
							
	chessPieces.add(WP6); 

	//White Pawn 7
	ReturnPiece WP7 = new Pawn(); 

	WP7.pieceType = PieceType.WP; 
	WP7.pieceFile = PieceFile.g; 
	WP7.pieceRank = 2;
										
	chessPieces.add(WP7); 

	//White Pawn 7
	ReturnPiece WP8 = new Pawn(); 

	WP8.pieceType = PieceType.WP; 
	WP8.pieceFile = PieceFile.h; 
	WP8.pieceRank = 2;
													
	chessPieces.add(WP8); 

	//Black Pawn 1  
	ReturnPiece BP1 = new Pawn(); 

	BP1.pieceType = PieceType.BP; 
	BP1.pieceFile = PieceFile.a; 
	BP1.pieceRank = 7;
;

	chessPieces.add(BP1); 

	//Black Pawn 2
	ReturnPiece BP2 = new Pawn(); 

	BP2.pieceType = PieceType.BP; 
	BP2.pieceFile = PieceFile.b; 
	BP2.pieceRank = 7;

	chessPieces.add(BP1); 

	//Black Pawn 3
	ReturnPiece BP3 = new Pawn(); 

	BP3.pieceType = PieceType.BP; 
	BP3.pieceFile = PieceFile.c; 
	BP3.pieceRank = 7;

	chessPieces.add(BP3); 

	//Black Pawn 4
	ReturnPiece BP4 = new Pawn(); 

	BP4.pieceType = PieceType.BP; 
	BP4.pieceFile = PieceFile.d; 
	BP4.pieceRank = 7;
		
	chessPieces.add(BP4); 

	//Black Pawn 5
	ReturnPiece BP5 = new Pawn(); 

	BP5.pieceType = PieceType.BP; 
	BP5.pieceFile = PieceFile.e; 
	BP5.pieceRank = 7;
					
	chessPieces.add(BP5); 

	//Black Pawn 6
	ReturnPiece BP6 = new Pawn(); 

	BP6.pieceType = PieceType.BP; 
	BP6.pieceFile = PieceFile.f; 
	BP6.pieceRank = 7;
								
	chessPieces.add(BP6); 

	//Black Pawn 7
	ReturnPiece BP7 = new Pawn(); 

	BP7.pieceType = PieceType.BP; 
	BP7.pieceFile = PieceFile.g; 
	BP7.pieceRank = 7;
											
	chessPieces.add(BP7); 

	//Black Pawn 8
	ReturnPiece BP8 = new Pawn(); 

	BP8.pieceType = PieceType.BP; 
	BP8.pieceFile = PieceFile.h; 
	BP8.pieceRank = 7;
														
	chessPieces.add(BP8);
			 
	//White Rook 1
	ReturnPiece WR1 = new Rook(); 

	WR1.pieceType = PieceType.WR; 
	WR1.pieceFile = PieceFile.a; 
	WR1.pieceRank = 1;
														
	chessPieces.add(WR1);

	//White Rook 2
	ReturnPiece WR2 = new Rook(); 

	WR2.pieceType = PieceType.WR; 
	WR2.pieceFile = PieceFile.h; 
	WR2.pieceRank = 1;
														
	chessPieces.add(WR2);

	//Black Rook 1
	ReturnPiece BR1 = new Rook(); 

	BR1.pieceType = PieceType.BR; 
	BR1.pieceFile = PieceFile.a; 
	BR1.pieceRank = 8;
														
	chessPieces.add(BR1);

	//Black Rook 2
	ReturnPiece BR2 = new Rook(); 

	BR2.pieceType = PieceType.BR; 
	BR2.pieceFile = PieceFile.h; 
	BR2.pieceRank = 8;
														
	chessPieces.add(BR2);

	//White Knight 1
	ReturnPiece WK1 = new Knight(); 

	WK1.pieceType = PieceType.WN; 
	WK1.pieceFile = PieceFile.b; 
	WK1.pieceRank = 1;
														
	chessPieces.add(WK1);

	//White Knight 2
	ReturnPiece WK2 = new Knight(); 

	WK2.pieceType = PieceType.WN; 
	WK2.pieceFile = PieceFile.g; 
	WK2.pieceRank = 1;
														
	chessPieces.add(WK2);

	//Black Knight 1
	ReturnPiece BK1 = new Knight(); 

	BK1.pieceType = PieceType.BN; 
	BK1.pieceFile = PieceFile.b; 
	BK1.pieceRank = 8;
														
	chessPieces.add(BK1);
	//Black Knight 2
	ReturnPiece BK2 = new Knight(); 

	BK2.pieceType = PieceType.BN; 
	BK2.pieceFile = PieceFile.g; 
	BK2.pieceRank = 8;

	chessPieces.add(BK2);

	//Black Bishop 1
	ReturnPiece BB1 = new Bishop(); 

	BB1.pieceType = PieceType.BB; 
	BB1.pieceFile = PieceFile.c; 		
	BB1.pieceRank = 8;
															
	chessPieces.add(BB1);

	//Black Bishop 2
	ReturnPiece BB2 = new Bishop(); 
	
	BB2.pieceType = PieceType.BB; 
	BB2.pieceFile = PieceFile.f; 
	BB2.pieceRank = 8;

	chessPieces.add(BB2);

	//White Bishop 1
	ReturnPiece WB1 = new Bishop(); 

	WB1.pieceType = PieceType.WB; 
	WB1.pieceFile = PieceFile.c; 		
	WB1.pieceRank = 1;
															
	chessPieces.add(WB1);

	//White Bishop 2
	ReturnPiece WB2 = new Bishop(); 
	
	WB2.pieceType = PieceType.WB; 
	WB2.pieceFile = PieceFile.f; 
	WB2.pieceRank = 1;

	chessPieces.add(WB2);

	//White Queen
	ReturnPiece WQX = new Queen(); 

	WQX.pieceType = PieceType.WQ; 
	WQX.pieceFile = PieceFile.e; 		
	WQX.pieceRank = 1;
															
	chessPieces.add(WQX);

	//Black Queen
	ReturnPiece BQX = new Queen(); 

	BQX.pieceType = PieceType.BQ; 
	BQX.pieceFile = PieceFile.d; 		
	BQX.pieceRank = 8;
															
	chessPieces.add(BQX);

	//Black King
	ReturnPiece BKX = new King(); 

	BKX.pieceType = PieceType.BK; 
	BKX.pieceFile = PieceFile.e; 		
	BKX.pieceRank = 8;
															
	chessPieces.add(WQX);

	//Black King
	ReturnPiece WKX = new King(); 

	WKX.pieceType = PieceType.WK; 
	WKX.pieceFile = PieceFile.d; 		
	WKX.pieceRank = 1;
															
	chessPieces.add(WKX);

	
	
													
	
	for(ReturnPiece piece : chessPieces) {
		StorageBoard.storageBoard[piece.pieceRank - 1][piece.pieceFile.ordinal()-1] = piece;
	}
	

		//readInputs();
		//check if the move is valid then readinputs again. Make sure to check 
	}
}
