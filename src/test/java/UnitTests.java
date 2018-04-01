/**
 * Created by rentala on 4/1/18.
 */

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

import com.app.AnagramDetector;
import com.app.FileHelper;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.util.List;


public class UnitTests {
    static AnagramDetector ad;
    static AnagramDetector adOnDisk;
    static List<String> words;
    private static final String FILE_PATH = "dictionary.txt";
    @BeforeClass
    public static void setUp() throws IOException {
        FileHelper fh = new FileHelper();
        words = fh.read(FILE_PATH);
        ad = new AnagramDetector(words, false);
        adOnDisk = new AnagramDetector(words, true);
        System.out.println(" Completed set up  . .");
    }
    @Test
    public void testValidAnagramSize(){
        String word = "open";
        assertTrue(ad.find(word).size() == 4);
    }
    @Test
    public void testValidAnagramArrays(){
        String word = "stop";
        String[] anagrams = new String[]{"post", "spot", "stop", "tops"};
        List<String> op = ad.find(word);
        String[] output = new String[op.size()];
        op.toArray(output);
        assertArrayEquals(output, anagrams);
    }
    @Test
    public void testInValidAnagram(){
        String word = "zzzz";
        List<String> op = ad.find(word);
        assertTrue(op == null);
    }
}



