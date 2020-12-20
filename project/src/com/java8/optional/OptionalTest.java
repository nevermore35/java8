package com.java8.optional;

import org.junit.Test;

import java.util.Optional;

/**
 * @author 93746
 */
public class OptionalTest {

    @Test
    public void test(){
        Optional<Godness> op1 = Optional.of(new Godness("张老师"));
        Optional<Man> op = Optional.of(new Man(op1));
        System.out.println(getGodnessName(op));
    }

    public String getGodnessName(Optional<Man> man) {
        return man.orElse(new Man())
                  .getGodness()
                  .orElse(new Godness("刘老师"))
                  .getName();
    }
}
