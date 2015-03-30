package com.sunlights.op.forkJoin;

import java.util.concurrent.RecursiveTask;

/**
 * Created by guxuelong on 2014/12/12.
 */
public class Calculator extends RecursiveTask<Integer> {

    private static final int THRESHOLD = 100;
    private int start;
    private int end;

    public Calculator(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        int sum = 0;
        if((start - end) < THRESHOLD){
            for(int i = start; i< end;i++){
                sum += i;
                System.out.println(i);
            }
        }else{
            int middle = (start + end) /2;
            Calculator left = new Calculator(start, middle);
            Calculator right = new Calculator(middle + 1, end);
            left.fork();
            right.fork();

            sum = left.join() + right.join();
        }
        return sum;
    }

}