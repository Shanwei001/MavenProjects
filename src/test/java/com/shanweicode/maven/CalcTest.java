package com.shanweicode.maven;
// 静态导入会将指定的类中静态资源全部导入到当前类中，这样当前类在调用那些静态资源的时候就不用写类名了
import org.junit.Test;

import static org.junit.Assert.*;

public class CalcTest {
    @Test
    public void testSum(){
        Calc calc = new Calc();
        int sum = calc.sum(7,8);

        System.out.println("sum = " + sum);

        long expected = 15;
        long actual = sum;

        // 将希望的值和实际的值进行比较，如果不一致会抛出异常，代表测试失败，也叫测试不通过
        assertEquals(expected,actual);
    }
    @Test
    public void testMul(){

    }

}
