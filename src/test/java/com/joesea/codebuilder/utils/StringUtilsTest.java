package com.joesea.codebuilder.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringUtilsTest {

    @Test
    void first2UpperCase() {
    }

    @Test
    void first2LowerCase() {
    }

    @Test
    void underline2CamelCase() {
        System.out.println(StringUtils.underline2CamelCase("") + "----------1");
        System.out.println(StringUtils.underline2CamelCase("_") + "----------2");
        System.out.println(StringUtils.underline2CamelCase(null) + "----------3");
        System.out.println(StringUtils.underline2CamelCase("_a") + "----------4");
        System.out.println(StringUtils.underline2CamelCase("_abc") + "----------5");
        System.out.println(StringUtils.underline2CamelCase("a_b") + "----------6");
        System.out.println(StringUtils.underline2CamelCase("a_bc") + "----------7");
    }

    @Test
    void replaceFirstStr() {
        System.out.println(StringUtils.replaceFirstStr("t_sys_t_sys_sdf", "t_sys_"));
    }
}