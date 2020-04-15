import java.util.Stack;

public class Sudoku{
    public int[][] arr;
    public boolean valid = true;

    public Sudoku(int[][] sud){
        this.arr = sud;
    }

    public Runnable columnChecker = () -> {
        for (int a = 0; a < arr.length; a++){
            Stack<Integer> nums = new Stack<>();
            for (int b = 0; b < arr[0].length; b++){
                if (nums.contains(arr[a][b])){
                    valid = false;
                    break;
                }
                else{
                    nums.push(arr[a][b]);
                }
            }
        }
    };

    public Runnable rowChecker = () -> {
        for (int a = 0; a < arr[0].length; a++){
            Stack<Integer> nums = new Stack<>();
            for (int b = 0; b < arr.length; b++){
                if (nums.contains(arr[b][a])){
                    valid = false;
                    break;
                }
                else{
                    nums.push(arr[b][a]);
                }
            }
        }
    };

    public Runnable squareChecker = () -> {
        for (int a = 0; a < arr.length; a+=3){
            for (int b = 0; b < arr[0].length; b+=3){
                Stack<Integer> nums = new Stack<>();
                for (int c = a; c < a+3 && c < arr.length; c++){
                    for (int d = b; d < b+3 &&  d < arr[0].length; d++){
                        if (nums.contains(arr[c][d])){
                            valid = false;
                        }
                        else{
                            nums.push(arr[c][d]);
                        }
                    }
                }
            }
        }
    };

    public void threading(){
        Thread columnCheck = new Thread(columnChecker);
        Thread rowChecking = new Thread(rowChecker);
        Thread squareCheck = new Thread (squareChecker);
        columnCheck.start();
        rowChecking.start();
        squareCheck.start();
        try{
            columnCheck.join();
            rowChecking.join();
            squareCheck.join();
        }
        catch (InterruptedException ie){}
    }


    public static void main(String[] args){
        int[][] sud = { {5,3,4,6,7,8,9,1,2},
                        {6,7,2,1,9,5,3,4,8},
                        {1,9,8,3,4,2,5,6,7},
                        {8,5,9,7,6,1,4,2,3},
                        {4,2,6,8,5,3,7,9,1},
                        {7,1,3,9,2,4,8,5,6},
                        {9,6,1,5,3,7,2,8,4},
                        {2,8,7,4,1,9,6,3,5},
                        {3,4,5,2,8,6,1,7,9} };

        Sudoku sudoku = new Sudoku(sud);
        sudoku.threading();
        if (sudoku.valid) System.out.println("Buen Sudoku");
        else System.out.println("Mal Sudoku");
    }
}