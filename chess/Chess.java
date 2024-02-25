//Lakshya Gour 
//Dhruv Shidhaye 
package chess;

//imports 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashMap;
import java.util.ArrayList;

import chess.ReturnPiece.PieceFile;
import chess.ReturnPiece.PieceType;
import chess.Chess.Player;

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
class Storage {
    // Static field to contain inputs
    static ReturnPiece[][] storageBoard = new ReturnPiece[8][8]; 
	static Player currPlayer = Player.white;
	public static HashMap<Character, PieceFile> fileMap = new HashMap<>();
	public static HashMap<Integer, PieceFile> fileMap2 = new HashMap<>();
    static {
        fileMap.put('a', PieceFile.a); 
        fileMap.put('b', PieceFile.b);
        fileMap.put('c', PieceFile.c);
        fileMap.put('d', PieceFile.d);
        fileMap.put('e', PieceFile.e);
        fileMap.put('f', PieceFile.f);
        fileMap.put('g', PieceFile.g);
        fileMap.put('h', PieceFile.h);
		fileMap2.put(0, PieceFile.a); //Can use ordinal when converting from PieceFile to int but have to use hashmap when converting from int to piecefile.
        fileMap2.put(1, PieceFile.b);
        fileMap2.put(2, PieceFile.c);
        fileMap2.put(3, PieceFile.d);
        fileMap2.put(4, PieceFile.e);
        fileMap2.put(5, PieceFile.f);
        fileMap2.put(6, PieceFile.g);
        fileMap2.put(7, PieceFile.h);
    }
		


	static ArrayList<PieceType> whites = new ArrayList<>();
	static {
		whites.add(PieceType.WP);
        whites.add(PieceType.WR);
        whites.add(PieceType.WN);
        whites.add(PieceType.WB);
        whites.add(PieceType.WQ);
        whites.add(PieceType.WK);
	}
	
	public static boolean isWhite(ReturnPiece rp){
		return whites.contains(rp.pieceType);
	}
	static PieceFile whitefile;
	static int whiterank;
	static PieceFile blackfile;
	static int blackrank;
	static PieceFile attackFile;
	static int attackRank;

	public static boolean isChecked() {
		for (int i = 0; i < storageBoard.length; i++) {
			for (int j = 0; j < storageBoard.length; j++) {
				if(storageBoard[i][j] != null) {
					ReturnPiece RP = storageBoard[i][j];
					if(currPlayer == Player.white) {
						if(RP.pieceType == PieceType.WK) {
							whitefile = fileMap2.get(i);
							whiterank = 1 + j;
						}
					} else {
						blackfile = fileMap2.get(i);
						blackrank = 1 + j;
					}
				}
			}
		}
		for (ReturnPiece[] row : Storage.storageBoard) {
			for (ReturnPiece returnPiece : row) {
				ChessPiece CP = (ChessPiece)returnPiece;
				if(currPlayer == Player.white) {
					if(CP.isValid(whitefile, whiterank) && !(isWhite(returnPiece))) { //CHECK CONDITION
						attackFile = CP.pieceFile;
						attackRank = CP.pieceRank;
						return true;
					}
				} else {
					if(CP.isValid(blackfile, blackrank) && (isWhite(returnPiece))) { //CHECK CONDITION
						attackFile = CP.pieceFile;
						attackRank = CP.pieceRank;
						return true;
					}
				}
			}
		}
		return false;
	}

	public static boolean CheckM8() {
		if(isChecked()) {
			if(currPlayer == Player.white) {
				// WHITE
				// first checks if the king can move anywhere
				ChessPiece king = (ChessPiece)storageBoard[whiterank - 1][whitefile.ordinal() - 1];
				if(storageBoard[whiterank][whitefile.ordinal() - 1] == null) { //up
					if(king.isValid(fileMap2.get(whitefile.ordinal() - 1), whiterank)) {return false;}
				}
				if(storageBoard[whiterank-1-1][whitefile.ordinal() - 1] == null) { //down
					if(king.isValid(fileMap2.get(whitefile.ordinal() - 1), whiterank-1-1)) {return false;}
				}
				if(storageBoard[whiterank-1][whitefile.ordinal() - 1 - 1] == null) { //left
					if(king.isValid(fileMap2.get(whitefile.ordinal() - 1 - 1), whiterank-1)){return false;}
				}
				if(storageBoard[whiterank-1][whitefile.ordinal() - 1 + 1] == null) { //right
					if(king.isValid(fileMap2.get(whitefile.ordinal() - 1 + 1), whiterank-1)){return false;}	
				}
				if(storageBoard[whiterank-1+1][whitefile.ordinal() - 1 + 1] == null) { //topright
					if(king.isValid(fileMap2.get(whitefile.ordinal() - 1 + 1), whiterank)){return false;}	
				}
				if(storageBoard[whiterank-1+1][whitefile.ordinal() - 1 - 1] == null) { //topleft
					if(king.isValid(fileMap2.get(whitefile.ordinal() - 1 - 1), whiterank)){return false;}	
				}
				if(storageBoard[whiterank-1-1][whitefile.ordinal() - 1 + 1] == null) { //bottomright
					if(king.isValid(fileMap2.get(whitefile.ordinal() - 1 + 1), whiterank-1-1)){return false;}	
				}
				if(storageBoard[whiterank-1-1][whitefile.ordinal() - 1 - 1] == null) { //bottomleft
					if(king.isValid(fileMap2.get(whitefile.ordinal() - 1 - 1), whiterank-1-1)){return false;}	
				}
				//checks if any of the current players pieces can get rid of the attacking player
				ChessPiece attacker = (ChessPiece)storageBoard[attackRank-1][attackFile.ordinal()-1];
				ArrayList<int[]> attackMoves = new ArrayList<>();
				int horizontal = whiterank - attackRank;
				int vertical = whitefile.ordinal() - attackFile.ordinal();
				if(!(Storage.isWhite(attacker))) {
					//pawn nothing
					//knight nothing
					//rook
					if(attacker.pieceType == PieceType.BR || attacker.pieceType == PieceType.BQ) {
						if(horizontal == 0 && vertical != 0){	
							if(vertical > 0) {
								for (int i = 1; i < vertical; i++) {
									int[] arr = new int[2];
									arr[0] = attackRank-1 + i;
									arr[1] = attackFile.ordinal()-1;
									attackMoves.add(arr);
								}
							} else {
								for (int i = 1; i < Math.abs(vertical); i++) {
									int[] arr = new int[2];
									arr[0] = attackRank-1 - i;
									arr[1] = attackFile.ordinal()-1;
									attackMoves.add(arr);
								}
							}
						}
						if(horizontal != 0 && vertical == 0){	
							if(horizontal > 0) {
								for (int i = 1; i < horizontal; i++) {
									int[] arr = new int[2];
									arr[0] = attackRank-1;
									arr[1] = attackFile.ordinal()-1 + i;
									attackMoves.add(arr);
								}
							} else {
								for (int i = 1; i < Math.abs(horizontal); i++) {
									int[] arr = new int[2];
									arr[0] = attackRank-1;
									arr[1] = attackFile.ordinal()-1 - i;
									attackMoves.add(arr);
								}
							}
						}
					}
					//bishop
					if (attacker.pieceType == PieceType.BB || attacker.pieceType == PieceType.BQ) {

						int diagonalDistance = Math.abs(attackRank - whiterank);
						for (int i = 1; i < diagonalDistance; i++) {
							// Top-right diagonal
							if (attackRank < whiterank && attackFile.ordinal() < whitefile.ordinal()) {
								int[] arr = {attackRank - 1 + i, attackFile.ordinal() - 1 + i};
								attackMoves.add(arr);
							}
							// Top-left diagonal
							else if (attackRank < whiterank && attackFile.ordinal() > whitefile.ordinal()) {
								int[] arr = {attackRank - 1 + i, attackFile.ordinal() - 1 - i};
								attackMoves.add(arr);
							}
							// Bottom-right diagonal
							else if (attackRank > whiterank && attackFile.ordinal() < whitefile.ordinal()) {
								int[] arr = {attackRank - 1 - i, attackFile.ordinal() - 1 + i};
								attackMoves.add(arr);
							}
							// Bottom-left diagonal
							else if (attackRank > whiterank && attackFile.ordinal() > whitefile.ordinal()) {
								int[] arr = {attackRank - 1 - i, attackFile.ordinal() - 1 - i};
								attackMoves.add(arr);
							}
						}
					}
					//queen
					
					
				} 
				for (ReturnPiece[] row : Storage.storageBoard) {
					for (ReturnPiece returnPiece : row) {
						ChessPiece CP = (ChessPiece)returnPiece;
						if(CP.isValid(attackFile, attackRank) && (isWhite(returnPiece))) { //CHECK CONDITION
							return false;
						}
						if(CP.pieceType != PieceType.WK && (isWhite(CP))) {
							for (int i = 0; i < attackMoves.size(); i++) {
								int[] arr = attackMoves.get(i);
								if(CP.isValid(fileMap2.get(arr[1]), arr[0])) {
									return false;
								}
							}
						}
						
					}
				}
				return true;
				//check if any of the current players pieces can get in between 


			} else {

				// BLACK
				ChessPiece king = (ChessPiece)storageBoard[blackrank - 1][blackfile.ordinal() - 1];
				if(storageBoard[blackrank][blackfile.ordinal() - 1] == null) { //up
					if(king.isValid(fileMap2.get(blackfile.ordinal() - 1), blackrank)) {return true;}
				}
				if(storageBoard[blackrank-1-1][blackfile.ordinal() - 1] == null) { //down
					if(king.isValid(fileMap2.get(blackfile.ordinal() - 1), blackrank-1-1)) {return true;}
				}
				if(storageBoard[blackrank-1][blackfile.ordinal() - 1 - 1] == null) { //left
					if(king.isValid(fileMap2.get(blackfile.ordinal() - 1 - 1), blackrank-1)){return true;}
				}
				if(storageBoard[blackrank-1][blackfile.ordinal() - 1 + 1] == null) { //right
					if(king.isValid(fileMap2.get(blackfile.ordinal() - 1 + 1), blackrank-1)){return true;}	
				}
				if(storageBoard[blackrank-1+1][blackfile.ordinal() - 1 + 1] == null) { //topright
					if(king.isValid(fileMap2.get(blackfile.ordinal() - 1 + 1), blackrank)){return true;}	
				}
				if(storageBoard[blackrank-1+1][blackfile.ordinal() - 1 - 1] == null) { //topleft
					if(king.isValid(fileMap2.get(blackfile.ordinal() - 1 - 1), blackrank)){return true;}	
				}
				if(storageBoard[blackrank-1-1][blackfile.ordinal() - 1 + 1] == null) { //bottomright
					if(king.isValid(fileMap2.get(blackfile.ordinal() - 1 + 1), blackrank-1-1)){return true;}	
				}
				if(storageBoard[blackrank-1-1][blackfile.ordinal() - 1 - 1] == null) { //bottomleft
					if(king.isValid(fileMap2.get(blackfile.ordinal() - 1 - 1), blackrank-1-1)){return true;}	
				}


				//populate black array:
				ChessPiece attacker = (ChessPiece)storageBoard[attackRank-1][attackFile.ordinal()-1];
				ArrayList<int[]> attackMoves = new ArrayList<>();
				int horizontal = whiterank - attackRank;
				int vertical = whitefile.ordinal() - attackFile.ordinal();
				if(Storage.isWhite(attacker)) {
					//rook
					if(attacker.pieceType == PieceType.WR || attacker.pieceType == PieceType.WQ) {
						if(horizontal == 0 && vertical != 0){	
							if(vertical > 0) {
								for (int i = 1; i < vertical; i++) {
									int[] arr = new int[2];
									arr[0] = attackRank-1 + i;
									arr[1] = attackFile.ordinal()-1;
									attackMoves.add(arr);
								}
							} else {
								for (int i = 1; i < Math.abs(vertical); i++) {
									int[] arr = new int[2];
									arr[0] = attackRank-1 - i;
									arr[1] = attackFile.ordinal()-1;
									attackMoves.add(arr);
								}
							}
						}
						if(horizontal != 0 && vertical == 0){	
							if(horizontal > 0) {
								for (int i = 1; i < horizontal; i++) {
									int[] arr = new int[2];
									arr[0] = attackRank-1;
									arr[1] = attackFile.ordinal()-1 + i;
									attackMoves.add(arr);
								}
							} else {
								for (int i = 1; i < Math.abs(horizontal); i++) {
									int[] arr = new int[2];
									arr[0] = attackRank-1;
									arr[1] = attackFile.ordinal()-1 - i;
									attackMoves.add(arr);
								}
							}
						}
					}
					//bishop
					if (attacker.pieceType == PieceType.WB || attacker.pieceType == PieceType.WQ) {

						int diagonalDistance = Math.abs(attackRank - whiterank);
						for (int i = 1; i < diagonalDistance; i++) {
							// Top-right diagonal
							if (attackRank < whiterank && attackFile.ordinal() < whitefile.ordinal()) {
								int[] arr = {attackRank - 1 + i, attackFile.ordinal() - 1 + i};
								attackMoves.add(arr);
							}
							// Top-left diagonal
							else if (attackRank < whiterank && attackFile.ordinal() > whitefile.ordinal()) {
								int[] arr = {attackRank - 1 + i, attackFile.ordinal() - 1 - i};
								attackMoves.add(arr);
							}
							// Bottom-right diagonal
							else if (attackRank > whiterank && attackFile.ordinal() < whitefile.ordinal()) {
								int[] arr = {attackRank - 1 - i, attackFile.ordinal() - 1 + i};
								attackMoves.add(arr);
							}
							// Bottom-left diagonal
							else if (attackRank > whiterank && attackFile.ordinal() > whitefile.ordinal()) {
								int[] arr = {attackRank - 1 - i, attackFile.ordinal() - 1 - i};
								attackMoves.add(arr);
							}
						}
					}

				}
				
				//checks if any of the current players pieces can get rid of the attacking player
				for (ReturnPiece[] row : Storage.storageBoard) {
					for (ReturnPiece returnPiece : row) {
						ChessPiece CP = (ChessPiece)returnPiece;
							if(CP.isValid(attackFile, attackRank) && !(isWhite(returnPiece))) { //CHECK CONDITION
								return false;
							}
							if(CP.pieceType != PieceType.BK && !(isWhite(CP))) {
								for (int i = 0; i < attackMoves.size(); i++) {
									int[] arr = attackMoves.get(i);
									if(CP.isValid(fileMap2.get(arr[1]), arr[0])) {
										return false;
									}
								}
							}
					}
				}
				return true;
			}
		}
		return false;
	}

	////REVIEW
	
	public static boolean simulateMovetoCheck() {

    boolean isInCheckBeforeMove = Storage.isChecked();
    if (!isInCheckBeforeMove) {
        return false;
    }

    for (int i = 0; i < Storage.storageBoard.length; i++) {
        for (int j = 0; j < Storage.storageBoard[i].length; j++) {

			//grab current board spot
            ChessPiece piece = (ChessPiece)Storage.storageBoard[i][j];

			//check if board spot is piece 
            if (piece != null && ((Storage.currPlayer == Player.white && Storage.isWhite(piece)) ||
                                  (Storage.currPlayer == Player.black && !Storage.isWhite(piece)))) {
            

				//test all possible moves
                for (PieceFile file : PieceFile.values()) {
                    for (int rank = 1; rank <= 8; rank++) {

						//piece original pos
                        int originalRank = piece.pieceRank;
                        PieceFile originalFile = piece.pieceFile;
                        
						//simulate moves
                        if (piece.isValid(file, rank)) {

							//move piece
                            ChessPiece targetPiece = (ChessPiece)Storage.storageBoard[rank-1][file.ordinal()];
                            Storage.storageBoard[originalRank-1][originalFile.ordinal()] = null;
                            Storage.storageBoard[rank-1][file.ordinal()] = piece;
                            piece.pieceRank = rank;
                            piece.pieceFile = file;
                            
                            //isChecked? 
                            boolean isInCheckAfterMove = Storage.isChecked();
                            
                            //put piece back to og pos
                            piece.pieceFile = originalFile;
                            piece.pieceRank = originalRank;
                            Storage.storageBoard[originalRank-1][originalFile.ordinal()] = piece;
                            Storage.storageBoard[rank-1][file.ordinal()] = targetPiece;
                            
                            if (!isInCheckAfterMove) {
                                //not a checkmate we good 
                                return true;
                            }
                        }
                    }
                }
            }
        }
    }
    
    //uh oh update the checkmate status
    return false;
	}

	public static void pawnPromotion(ChessPiece piece, char promotionChar) {

		// last rank and better be a pawn homie
		if ((piece instanceof Pawn) && ((piece.pieceRank == 1 && piece.pieceType == PieceType.BP) ||
										(piece.pieceRank == 8 && piece.pieceType == PieceType.WP))) {
			// promo piecetype
			PieceType newType = null;

			switch (promotionChar) {
				case 'q':
					newType = (piece.pieceType == PieceType.WP) ? PieceType.WQ : PieceType.BQ;
					break;
				case 'r':
					newType = (piece.pieceType == PieceType.WP) ? PieceType.WR : PieceType.BR;
					break;
				case 'b':
					newType = (piece.pieceType == PieceType.WP) ? PieceType.WB : PieceType.BB;
					break;
				case 'n':
					newType = (piece.pieceType == PieceType.WP) ? PieceType.WN : PieceType.BN;
					break;
				default:
					newType = (piece.pieceType == PieceType.WP) ? PieceType.WQ : PieceType.BQ;
					break;
			}
	
			// promote
			piece.pieceType = newType;
		}
	}
	
	
	}
	




//_______________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________//
class ChessPiece extends ReturnPiece {
	public int timesMoved;
	public void moveTo(PieceFile file, int rank) {
        Storage.storageBoard[pieceRank-1][pieceFile.ordinal()-1] = null;
        if(!(Storage.storageBoard[pieceRank-1][pieceFile.ordinal()-1] == null)) {
			//was set to type chesspiece but we cant initialize for static using subclass silly 
            ChessPiece killed = (ChessPiece)Storage.storageBoard[rank-1][file.ordinal()-1];         
			//toString if shit goes wrong       
			System.out.println("CHESSPIECE: " + killed +  "WAS KILLED by: " + this);
            }
        Storage.storageBoard[rank-1][file.ordinal()-1] = this;
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
                if(!(Storage.storageBoard[this.pieceRank-1+1][this.pieceFile.ordinal()-1] == null)) {
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
                if(!(Storage.storageBoard[this.pieceRank-1-1][this.pieceFile.ordinal()-1] == null)) {
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
					if (Storage.storageBoard[this.pieceRank - 1 + i][this.pieceFile.ordinal() - 1 + i] != null) {
						return false;
					}
				}

			} else if (horizontal < 0 && vertical > 0) {
				// Top-left diagonal
				for (int i = 1; i < Math.abs(horizontal); i++) {
					if (Storage.storageBoard[this.pieceRank - 1 + i][this.pieceFile.ordinal() - 1 - i] != null) {
						return false;
					}
				}

			} else if (horizontal > 0 && vertical < 0) {
				// Bottom-right diagonal
				for (int i = 1; i < horizontal; i++) {
					if (Storage.storageBoard[this.pieceRank - 1 - i][this.pieceFile.ordinal() - 1 + i] != null) {
						return false;
					}
				}

			} else if (horizontal < 0 && vertical < 0) {
				// Bottom-left diagonal
				for (int i = 1; i < Math.abs(horizontal); i++) {
					if (Storage.storageBoard[this.pieceRank - 1 - i][this.pieceFile.ordinal() - 1 - i] != null) {
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
						if(!(Storage.storageBoard[this.pieceRank-1 + i][this.pieceFile.ordinal()-1] == null)) {
							return false;
						}
					}
				} else {
					for (int i = 1; i < Math.abs(vertical); i++) {
						if(!(Storage.storageBoard[this.pieceRank-1 - i][this.pieceFile.ordinal()-1] == null)) {
							return false;						}
					}
				}
				return true;
			}
			if(vertical == 0 && horizontal != 0){
				if(horizontal > 0) {
					for (int i =1 - 1; i < horizontal; i++) {
						if(!(Storage.storageBoard[this.pieceRank - 1][this.pieceFile.ordinal() - 1  + i] == null)) {
							return false;
						}
					}
				} else {
					for (int i =1 - 1; i < Math.abs(horizontal); i++) {
						if(!(Storage.storageBoard[this.pieceRank - 1][this.pieceFile.ordinal() - 1  - i] == null)) {
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
					if(!(Storage.storageBoard[this.pieceRank-1 + i][this.pieceFile.ordinal()-1] == null)) {
						return false;
					}
				}
			} else {
				for (int i = 1; i < Math.abs(vertical); i++) {
					if(!(Storage.storageBoard[this.pieceRank-1 - i][this.pieceFile.ordinal()-1] == null)) {
						return false;
					}
				}
			}
			return true;
		}
		if(vertical == 0 && horizontal != 0){
			if(horizontal > 0) {
				for (int i =1 - 1; i < horizontal; i++) {
					if(!(Storage.storageBoard[this.pieceRank - 1][this.pieceFile.ordinal() - 1  + i] == null)) {
						return false;
					}
				}
			} else {
				for (int i =1 - 1; i < Math.abs(horizontal); i++) {
					if(!(Storage.storageBoard[this.pieceRank - 1][this.pieceFile.ordinal() - 1  - i] == null)) {
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
					ChessPiece piece = (ChessPiece)Storage.storageBoard[0][7];
					if(piece.timesMoved == 0) {
						for (int i = 1; i < horizontal; i++) {
							if(Storage.storageBoard[0][3+i] != null) {
								return false;
							}
						}
					}
				} else {
					ChessPiece piece = (ChessPiece)Storage.storageBoard[7][7];
					if(piece.timesMoved == 0) {
						for (int i = 1; i < horizontal; i++) {
							if(Storage.storageBoard[7][4+i] != null) {
								return false;
							}
						}
					}
				}
			}
			if(horizontal < -1) { //left
				if(this.pieceType == PieceType.WK) {
					ChessPiece piece = (ChessPiece)Storage.storageBoard[0][0];
					if(piece.timesMoved == 0) {
						for (int i = 1; i < horizontal; i++) {
							if(Storage.storageBoard[0][3-i] != null) {
								return false;
							}
						}
					}
				} else {
					ChessPiece piece = (ChessPiece)Storage.storageBoard[7][0];
					if(piece.timesMoved == 0) {
						for (int i = 1; i < horizontal; i++) {
							if(Storage.storageBoard[7][4-i] != null) {
								return false;
							}
						}
					}
				}
			}
		if(((vertical == 1 || vertical == -1) || (vertical == 0)) && ((horizontal==1 || horizontal ==-1) || (horizontal == 0))) {
			for (ReturnPiece[] row : Storage.storageBoard) {
				for (ReturnPiece returnPiece : row) {
					ChessPiece CP = (ChessPiece)returnPiece;
					if((Storage.isWhite(this) && !(Storage.isWhite(CP))) || (Storage.isWhite(CP) && !(Storage.isWhite(this)))) {
						if(CP.isValid(file, rank)) { //CHECK CONDITION
							return false;
						}
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
            Storage.storageBoard[pieceRank-1][pieceFile.ordinal()-1] = null;
			if(!(Storage.storageBoard[pieceRank-1][pieceFile.ordinal()-1] == null)) {
				ChessPiece killed = (ChessPiece)Storage.storageBoard[rank-1][file.ordinal()-1];                
				System.out.println("CHESSPIECE " + killed +  "WAS KILLED by: " + this);
				}
			Storage.storageBoard[rank-1][file.ordinal()-1] = this;
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
		/* FILL IN THIS METHOD */
		ReturnPlay ret = new ReturnPlay();
		if(move == "resign") {  // Check for resign and return if it is
			if(Storage.currPlayer == Player.white) {
				ret.message = ReturnPlay.Message.RESIGN_BLACK_WINS;
				return ret;
			} else {
				ret.message = ReturnPlay.Message.RESIGN_WHITE_WINS;
				return ret;
			}
		}
		String[] moves = move.split(" "); //Splitting string into words
		char[] first = moves[0].toCharArray();
		char[] second = moves[1].toCharArray();
		String third;
		PieceFile start_file = Storage.fileMap.get(first[0]); //uses the hashmap that we have in our storage to convert the character into a PieceFile
		int start_rank = (int) first[1] - '0'; //Way to convert char into an integer

		PieceFile end_file = Storage.fileMap.get(second[0]); //uses the hashmap that we have in our storage to convert the character into a PieceFile
		int end_rank = (int) second[1] - '0'; //Way to convert char into an integer
		ChessPiece activePiece = (ChessPiece) Storage.storageBoard[start_rank - 1][start_file.ordinal() - 1];
		/* 
		// for the method below we will have to definitely change it quite a bit after we figure how to 
		figure out when to check if its checked and how to check for checkmate; at least for now I completed 
		reading the inputs, fixed the King check check, added an isWhite method.
		The main problem for check mate check is the problem of figuring out if any of your own pieces 
		moves can either position themselves between the king and the attack piece, but that would require 
		us logging which piece is the attack piece and then checking if there are any pieces that can take 
		out the attacking piece. If there are no pieces that can get the attacking piece, we can then try and simulate
		the possible moves of the king and see if they also get checked using the same isValid method <-- for the second part
		we can just use the existing isvalid method
		*/

		if(Storage.isChecked()) { //yet to be implemented but checks if there is a check and if there is the move must be made so that it either ends with it no longer being threatened to be checked
			Storage.simulateMovetoCheck(); // method that will make a copy of the board at current state and try and do move to check if the check is gone
		} else {
			if(activePiece.isValid(end_file, end_rank)) {
				activePiece.moveTo(end_file, end_rank);
				if(Storage.CheckM8()) { //checks if a checkmate is done
					if(Storage.currPlayer == Player.white) {
						ret.message = ReturnPlay.Message.CHECKMATE_WHITE_WINS;
					} else {
						ret.message = ReturnPlay.Message.CHECKMATE_BLACK_WINS;
					}
				}//checks if there is currently a check mate
			} else {
				ret.message = ReturnPlay.Message.ILLEGAL_MOVE;
			}
			
		}


		if(moves.length > 2) { //Third can either be pawn promotion or draw?
			third = moves[2];
			if(third == "draw?") {

			}
		}


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
        Storage.startpos = word;
    	if(lineScan.hasNext()) {word = lineScan.next(); Storage.endpos = word;} // if there is a second word it will read it
		if(lineScan.hasNext()) {word = lineScan.next(); Storage.thirdword = word;} //if asks for a draw 
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
		Storage.storageBoard[piece.pieceRank - 1][piece.pieceFile.ordinal()-1] = piece;
	}
	

		//readInputs();
		//check if the move is valid then readinputs again. Make sure to check 
	}
}
