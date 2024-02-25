package chess;
/*
package chess; 

import java.util.ArrayList;

public class FileRank {
    int Col;
    int Row;
    public FileRank(int Col, int Row) {
        this.Col = Col;
        this.Row = Row;
        
    }
    public int getFile() {
        return file;
    }
    
    public void setFile(int file) {
        this.file = file;
    }
    
    public int getRank() {
        return rank;
    }
    
    public void setRank(int rank) {
        this.rank = rank;
    }
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FileRank fileRank = (FileRank) o;
        return file == fileRank.file && rank == fileRank.rank;
    }
}

public abstract class ChessPiece {
    String type;
    FileRank curPos; //data for current position
    boolean isWhite; //checks privilege 
    int timesMoved;
    public boolean isWhite() {
        return isWhite;
    }
    public string toString() {
        return type;
    }
    public void moveTo(FileRank moveTo) {
        if(this.isValid(moveTo)) {
            board[curPos.Row][curPos.Col] = null;
            if(!(board[moveTo.Row][moveTo.Col].isEmpty)) {
                ChessPiece killed = board[moveTo.Row][moveTo.Col];
                System.out.println("CHESSPIECE " + toString(killed) +  "WAS KILLED by: " + toString(this));
            }
            board[moveTo.Row][moveTo.Col] = this;
            curPos = new FileRank(moveTo.Col, moveTo.Row);
            timesMoved++;
        }
    }
    public abstract boolean isValid(FileRank moveTo);
    
}
 
class Pawn extends ReturnPiece {
    public Pawn(PieceFile file, PieceRank rank, boolean isWhite) {
        this.type = "Pawn";
        this.curPos = curPos;
        this.isWhite = isWhite;
        timesMoved = 0;
    }
    public boolean isValid(FileRank moveTo) {
        //attempt 2 will calculate the difference in the moves and if the algo is right it will be valid
        if(isWhite()) {
            int vertical = moveTo.Row-curPos.Row; //positive for white
            int horizontal = moveTo.Col-curPos.Col;
            if((vertical == 2 && horizontal == 0) && timesMoved == 0) {
                if(board[moveTo.Row][moveTo.Col].isEmpty) {
                    return true;
                }
            }
            if((vertical == 1 && horizontal == 0) && timesMoved == 0) {
                if(board[moveTo.Row][moveTo.Col].isEmpty) {
                    return true;
                }
            }
            if((vertical == 1 && horizontal == 0)) {
                if(board[moveTo.Row][moveTo.Col].isEmpty) {
                    return true;
                }
            }
            if(vertical == 1 && horizontal == 1) {
                if(!(board[moveTo.Row][moveTo.Col].isEmpty)) {
                    return true;
                }
            }
            if(vertical == 1 && horizontal == -1) {
                if(!(board[moveTo.Row][moveTo.Col].isEmpty)) {
                    return true;
                }
            }
            return false;
        } else {
            int vertical = moveTo.Col-curPos.Col; //negative for black
            int horizontal = moveTo.Col-curPos.Col;
            if((vertical == -2 && horizontal == 0) && timesMoved == 0) {
                if(board[moveTo.Row][moveTo.Col].isEmpty) {
                    return true;
                }
            }
            if((vertical == -1 && horizontal == 0) && timesMoved == 0) {
                if(board[moveTo.Row][moveTo.Col].isEmpty) {
                    return true;
                }
            }
            if((vertical == -1 && horizontal == 0)) {
                if(board[moveTo.Row][moveTo.Col].isEmpty) {
                    return true;
                }
            }
            if(vertical == -1 && horizontal == 1) {
                if(!(board[moveTo.Row][moveTo.Col].isEmpty)) {
                    return true;
                }
            }
            if(vertical == -1 && horizontal == -1) {
                if(!(board[moveTo.Row][moveTo.Col].isEmpty)) {
                    return true;
                }
            }
            return false;
        }
        /* 
        //Creates an Arraylist of allowed moves
        Arraylist<FileRank> AllowedMoves = new ArrayList<>();
        if(this.isWhite) {
            if(timesMoved == 0) {
                if(Board[curPos.Row+1][curPos.Col].isEmpty()) {
                    AllowedMoves.add(new FileRank(curPos.Col,curPos.Row+1));
                }
                if(Board[curPos.Row+2][curPos.Col].isEmpty()) {
                    AllowedMoves.add(new FileRank(curPos.Col,curPos.Row+2));
                }
            } else {
                if(!(curPos.Row+1 > 7)) {
                    if(Board[curPos.Col][curPos.Row+1].isEmpty()) {
                        AllowedMoves.add(new FileRank(curPos.Col,curPos.Row+1));
                    }
                }
            }
            if((0 <= curPos.Row+1 && curPos.Row+1 <= 7) && (0 <= curPos.Col+1 && curPos.Col+1 <= 7)) {
                if(!(Board[curPos.Row+1][curPos.Col+1].isEmpty())){
                    AllowedMoves.add(new FileRank(curPos.Col+1,curPos.Row+1));
                }
            }
            if((0 <= curPos.Row+1 && curPos.Row+1 <= 7) && (0 <= curPos.Col-1 && curPos.Col-1 <= 7)) {
                if(!(Board[curPos.Row+1][curPos.Col-1].isEmpty())){
                    AllowedMoves.add(new FileRank(curPos.Col-1,curPos.Row+1));
                }
            }
        } else {
            if(timesMoved == 0) {
                if(Board[curPos.Row-1][curPos.Col].isEmpty()) {
                    AllowedMoves.add(new FileRank(curPos.Col,curPos.Row-1));
                }
                if(Board[curPos.Row-2][curPos.Col].isEmpty()) {
                    AllowedMoves.add(new FileRank(curPos.Col,curPos.Row-2));
                }
            } else {
                if(!(curPos.Row-1 < 0)) {
                    if(Board[curPos.Col][curPos.Row-1].isEmpty()) {
                        AllowedMoves.add(new FileRank(curPos.Col,curPos.Row-1));
                    }
                }
            }
            if((0 <= curPos.Row-1 && curPos.Row-1 <= 7) && (0 <= curPos.Col+1 && curPos.Col+1 <= 7)) {
                if(!(Board[curPos.Row-1][curPos.Col+1].isEmpty())){
                    AllowedMoves.add(new FileRank(curPos.Col+1,curPos.Row-1));
                }
            }
            if((0 <= curPos.Row-1 && curPos.Row-1 <= 7) && (0 <= curPos.Col-1 && curPos.Col-1 <= 7)) {
                if(!(Board[curPos.Row-1][curPos.Col-1].isEmpty())){
                    AllowedMoves.add(new FileRank(curPos.Col-1,curPos.Row-1));
                }
            }
        }
        */