package cn.whaifree.test;

import org.junit.Test;

import java.io.FileNotFoundException;

public class DePatternTest {

    @Test
    public void testMain() throws Exception {
        // Setup
        // Run the test
        DePattern.main(new String[]{"args"});

        // Verify the results
    }

    @Test(expected = FileNotFoundException.class)
    public void testMain_ThrowsFileNotFoundException() throws Exception {
        // Setup
        // Run the test
        DePattern.main(new String[]{"args"});
    }
}
