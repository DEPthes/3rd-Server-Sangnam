package com.tdd.tddPractice;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
class Result{

    public int add(int num1,int num2){
        return num1 + num2;
    }
}
class TddPracticeApplicationTest {
//    @Test
//    void addTest(){
//        int firstNumber = 1;
//        int secondNumber = 2;
//
//        assertThat(add(firstNumber,secondNumber)).isEqualTo(3);
//    }
    @Test
    void sumTest(){
        int firstNumber = 1;
        int secondNumber = 2;
        //Result 객체생성
        Result result = new Result();
        //result애서 add 호출
        assertThat(result.add(firstNumber, secondNumber)).isEqualTo(3);
    }
}