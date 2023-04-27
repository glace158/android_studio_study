package com.example.activirytest;

public class ArrayExample {
    private int myArray[];
    private int count;

    public ArrayExample(int index){
        this.myArray = new int[index];
    }

    public int arrInit(){
        for(count = 0; count < myArray.length ; count++){
            myArray[count]=count+1;
        }
        return count;
    }

    public int[] getMyArray() {
        return myArray;
    }
}
