import com.sun.security.auth.UnixNumericGroupPrincipal;

//import java.io.*;
import java.util.*;
public class Sudoku {
    public static void display(int[][] board){
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[0].length;j++){
                System.out.print(board[i][j]+ " ");
            }
            System.out.println();
        }
    }
    public static void solveSudoku(int [][]board,int i,int j) {
        //logic 1
        if (i == board.length) {
            display(board);
            return;
        }
        //logic2 here j is col and i is row
        int ni = 0; //ni means next row
        int nj = 0; //nj means next column
        if (j == board[0].length - 1) { //it means when we reach at last col i.e (0,8)
            ni = i + 1; //then row increases (1,0)
            nj = 0; // and col decrease (0,8) to (1,0)
        } else { //it means when we are not at last so
            ni = i; //in this case row will be same
            nj = j + 1;//col increase (0,7) to (0,8)
        }
        //logic3
        if (board[i][j] != 0) { //this line means value is already present at that cell then call
            solveSudoku(board, ni, nj);
        } else {// if value in not present means there is 0
            for (int po = 1; po <= 9; po++) {  //here po means possible option(123456789) i.e jis cell mein 0 hai
                // woha inmein se hi koi fill hoga
                if (isValid(board, i, j, po) == true) {  //isvalid is function to
                    // check wether enter value is not present in row col and submatrix
                    board[i][j] = po;
                    solveSudoku(board, ni, nj);
                    board[i][j] = 0;// this line mean ifwe had fill one value which become wrong at last
                    // so backtracking is done and rubing the filled value
                }
            }
        }
    }
    //logic4
    public static boolean isValid(int [][]board,int x,int y,int value){
        //row fix and col change
        for (int j=0;j<board[0].length;j++){
            if(board[x][j]==value){
                return false;
            }
        }
        //col fix and row change
        for(int i=0;i<board.length;i++){
            if(board[i][y]==value){
                return false;
            }
        }
        // logic 5 submatrix 3*3
        int smi=x/3 *3;
        int smj=y/3*3;
        for(int i=0;i<3;i++){
            for (int j=0;j<3;j++){
                if(board[smi+i][smj+j]==value){
                    return false;
                }
            }
        }
        return true;
    }
    public static void main(String[]args)throws Exception{
        Scanner s=new Scanner(System.in);
        int arr[][]=new int[9][9];
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                arr[i][j]=s.nextInt();
            }
        }
        solveSudoku(arr,0,0);
    }
}
