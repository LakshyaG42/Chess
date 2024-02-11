import java.util.ArrayList;
public class FileRank {
    int Col;
    int Row;
    public FileRank(int Col, int Row) {
        this.Col = Col;
        this.Row = Row;
        
    }
    public String toString() {
        String str = "Not Implemented Yet";
        return str;
    }
}
public abstract class ChessPiece {
    FileRank curPos; //data for current position
    boolean isWhite; //checks privilege 
    public boolean isWhite() {
        return isWhite;
    }
}
class Pawn extends ChessPiece {
    Arraylist<FileRank> whitePawnStarts = new ArrayList<>();
    Arraylist<FileRank> blackPawnStarts = new ArrayList<>();
    for (int i = 0; i <= 7; i++) {
        FileRank pos = new FileRank(i, 1);
        whitePawnStarts.add(pos);
        FileRank blackpos = new FileRank(i, 6);
        blackPawnStarts.add(blackpos);
    }

    public void createAllowedMoves() {
        Arraylist<FileRank> AllowedMoves = new ArrayList<>();
        
        if(this.isWhite) {
            if(whitePawnStarts.contains(curPos)) {
                //allowed moves
                FileRank move1 = new FileRank(curPos.Col,curPos.Row+1);
                FileRank move2 = new FileRank(curPos.Col,curPos.Row+2);
                AllowedMoves.add(move1);
                AllowedMoves.add(move2);
            } else {
                FileRank move1 = new FileRank(curPos.Col,curPos.Row+1);
                AllowedMoves.add(move1);
            }
        } else {
            if(blackPawnStarts.contains(curPos)) {
                //allowed moves
                FileRank move1 = new FileRank(curPos.Col,curPos.Row-1);
                FileRank move2 = new FileRank(curPos.Col,curPos.Row-2);
                AllowedMoves.add(move1);
                AllowedMoves.add(move2);
            } else {
                FileRank move1 = new FileRank(curPos.Col,curPos.Row+1);
                if(Board[move1.Row][move1.Col].isEmpty()) {
                    AllowedMoves.add(move1);
                }
                
            }
            
        }
    }
    
} 


