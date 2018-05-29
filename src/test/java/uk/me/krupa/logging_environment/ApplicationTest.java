package uk.me.krupa.logging_environment;

import org.hamcrest.CoreMatchers;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;

import static org.hamcrest.CoreMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApplicationTest {

    private static PrintStream outOriginal;
    private static StringWriter outString;

    @BeforeClass
    public static void setupClass() {
        outOriginal = System.out;
        outString = new StringWriter();
        System.setOut(new PrintStream(new OutputStream() {
            @Override
            public void write(int b) {
                outOriginal.write(b);
                outString.write(b);
            }
        }));

        System.setProperty("MY_SECRET_KEY", "MY_SECRET_VALUE");
    }

    @AfterClass
    public static void tearDownClass() {
        System.setOut(outOriginal);
    }

    @Test
    public void initDoesNotWriteSystemProperties() {
        Assert.assertThat(outString.toString(), not(containsString("MY_SECRET_KEY=MY_SECRET_VALUE")));
    }

    @Test
    public void initDoesNotWriteEnvironmentVariables() {
        final String userHome = System.getProperty("user.home");
        Assert.assertThat(outString.toString(), not(containsString("HOME=" + userHome)));
    }


}
