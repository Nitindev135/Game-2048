package com.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {
    private static final int size = 4;
    private List<List<Integer>> grid;
    private static final int ValueToWin = 2048;
    static boolean isWon = false;
    static int currentScore = 0;

    Game(){
        grid = new ArrayList<>();
        for(int i=0;i<size;i++) {
            grid.add(new ArrayList<>());
            for (int j = 0; j < size; j++) {
                grid.get(i).add(j, 0);
            }
        }
    }
    //To display grid at any time we simply call this method
    // And it is default we need to call it from outside of that class

    private void displayGrid(){
        for(List<Integer> l:grid){
            for(Integer i: l){
                System.out.format("%6d",i);
            }
            System.out.println();
        }
        System.out.println();
    }


    /* This method is for,when we push the grid up or down then we need to remove
       all zeros which are present in between of that particular column
    */
    private List<Integer> removeEmptyCellColumn(int col){
        List<Integer> list = new ArrayList<>();
        for(int row=0;row<size;row++){
            Integer val = grid.get(row).get(col);
            if(val != 0){
                list.add(val);
            }
        }
        return list;
    }


    /* This method is for,when we push the grid left or right then we need to remove
       all zeros which are present in between of that particular row
    */
    private List<Integer> removeEmptyCellRow(int row){
        List<Integer> list = new ArrayList<>();
        for(int col=0;col<size;col++){
            Integer val = grid.get(row).get(col);
            if(val != 0){
                list.add(val);
            }
        }
        return list;
    }


    /* This method is for,when we merged a particular list then if some elements get merged
       then we need to add remaining elements with zero value at the end of list
    */
    private void addEmptyCellToLast(List<Integer> list){
        int length = list.size();
        for(int i=length;i<size;i++){
            list.add(0);
        }
    }


    /* This method is for,when we merged a particular list then if some elements get merged
       then we need to add remaining elements with zero value at the start of list
    */
    private void addEmptyCellToFirst(List<Integer> list){
        int diff = size-list.size();
        while(diff > 0){
            list.add(0,0);
            diff--;
        }
    }


    /* This method is for merging operation like if 2 consecutive elements are same we
       add both elements and put the to fist element and remove second element from the list
    */
    private void mergeCell(List<Integer> list){
        for(int i=0;i<list.size()-1;i++){
            int val1 = list.get(i);
            int val2 = list.get(i+1);
            if(val1 != 0 && (val1 + val2) == ValueToWin){
                isWon = true;
            }
            if(val1 != 0 && val1==val2){
                currentScore += val1;
                list.set(i,val1*2);
                list.remove(i+1);
            }
        }
    }


    /* This method is for setting up a particular list after operation(left,right,up,down)
       at the specified column
    */
    private void setColumnToGrid(List<Integer> list,int col){
        for(int i=0;i<size;i++){
            grid.get(i).set(col,list.get(i));
        }
    }


    /* This method is for setting up a particular list after operation(left,right,up,down)
       at the specified row in grid
    */
    private void setRowToGrid(List<Integer> list,int row){
        for(int i=0;i<size;i++){
            grid.get(row).set(i,list.get(i));
        }
    }

    /* This method is for pushing the entire grid towards up direction
    */
    void pushUp(){
        List<Integer> list;
        for(int col=0;col<size;col++){
            list = removeEmptyCellColumn(col);
            mergeCell(list);
            addEmptyCellToLast(list);
            setColumnToGrid(list,col);
        }
        System.out.println("Pushing Up...");
        displayGrid();
    }

    /* This method is for pushing the entire grid towards down direction
     */
    void pushDown(){
        List<Integer> list;
        for(int col = 0;col < size;col++){
            list = removeEmptyCellColumn(col);
            mergeCell(list);
            addEmptyCellToFirst(list);
            setColumnToGrid(list,col);
        }
        System.out.println("Pushing Down...");
        displayGrid();
    }


    /* This method is for pushing the entire grid towards left direction
     */
    void pushLeft(){
        List<Integer> list;
        for(int row = 0;row < size;row++){
            list = removeEmptyCellRow(row);
            mergeCell(list);
            addEmptyCellToLast(list);
            setRowToGrid(list,row);
        }
        System.out.println("Pushing Left...");
        displayGrid();
    }


    /* This method is for pushing the entire grid towards right direction
     */
    void pushRight(){
        List<Integer> list;
        for(int row = 0;row < size;row++){
            list = removeEmptyCellRow(row);
            mergeCell(list);
            addEmptyCellToFirst(list);
            setRowToGrid(list,row);
        }
        System.out.println("Pushing Right...");
        displayGrid();
    }


    /* This method is used after any operation (left,right,up,down) to randomly generate
       a number 2 or 4 and place it inside empty cell or the cell with zero value
       if there is no empty cell in grid then it returns false otherwise it returns true
     */
    boolean pushRandomly(){
        boolean isPossible = false;
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                if(grid.get(i).get(j) == 0){
                    isPossible = true;
                    break;
                }
            }
        }
        if(!isPossible){
            return false;
        }
        Random r = new Random();
        int n = r.nextInt(2)+1;
        int actualNumber = n*2;
        while(true) {
            int x = r.nextInt(size);
            int y = r.nextInt(size);
            if(grid.get(x).get(y) == 0) {
                grid.get(x).set(y,actualNumber);
                break;
            }
        }
        System.out.println("Pushing 2 or 4 randomly...");
        displayGrid();
        return true;
    }
}
