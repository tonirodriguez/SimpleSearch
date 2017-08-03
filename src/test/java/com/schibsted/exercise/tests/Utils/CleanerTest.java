package com.schibsted.exercise.tests.Utils;

import com.schibsted.exercise.Utils.Cleaner;
import junit.framework.Assert;
import org.junit.*;
import java.util.*;


public class CleanerTest {
    @Test
    public void CleanerBasicTest() throws Exception {
        String[] result = new Cleaner().splitAndRemoveSpecialChars("this is a new Text");

        Assert.assertTrue(result.length == 5);
        Assert.assertEquals(result[result.length - 1], "text");
    }

    @Test
    public void CleanerSpecialCharsTest() throws Exception {
        String[] result = new Cleaner().splitAndRemoveSpecialChars("dfdsfds@!!!!Text");

        Assert.assertTrue(result.length == 1);
        Assert.assertEquals(result[result.length - 1], "dfdsfdstext");
    }

    @Test
    public void CleanerSpecialLineTest() throws Exception {
        String[] result = new Cleaner().splitAndRemoveSpecialChars("$p3ci@l characters, n1mb3r5 and   TaBs");

        Assert.assertTrue(result.length == 5);
        Assert.assertEquals(result[result.length - 1], "tabs");
    }


}
