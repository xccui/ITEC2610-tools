package ca.yorku.itec.C2610;

import com.github.difflib.DiffUtils;
import com.github.difflib.algorithm.DiffException;
import com.github.difflib.patch.Patch;
import org.codehaus.commons.compiler.CompileException;
import org.codehaus.janino.Compiler;
import org.codehaus.janino.util.ResourceFinderClassLoader;
import org.codehaus.janino.util.resource.MapResourceCreator;
import org.codehaus.janino.util.resource.MapResourceFinder;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A1GradingTool
 */
public class A1GradingTool {
    public static void main(String[] args) {
        Compiler compiler = new Compiler(
                null,
                new File[]{new File(".")},
                null,
                null,
                null,
                null,
                false,
                false,
                false,
                false,
                Compiler.DEFAULT_WARNING_HANDLE_PATTERNS,
                false);
        try {
            Map<String, byte[]> classes = new HashMap<>();
            compiler.setClassFileCreator(new MapResourceCreator(classes));
            File f = new File("A1/Test.java");
            System.out.println(f.getAbsolutePath());
            compiler.compile(new File[]{f});
            PrintStream stdout = System.out;
            System.setIn(new FileInputStream("A1/check/TestInput.txt"));
            System.setOut(new PrintStream("A1/check/MyOutput.txt"));
            ClassLoader c1 = new ResourceFinderClassLoader(
                    new MapResourceFinder(classes),
                    ClassLoader.getSystemClassLoader());
            c1.loadClass("Test").getDeclaredMethod("main", String[].class).invoke(null, (Object) new String[]{});

            List<String> original = Files.readAllLines(new File("A1/check/TestOutput.txt").toPath());
            List<String> revised = Files.readAllLines(new File("A1/check/MyOutput.txt").toPath());
            Patch<String> diff = DiffUtils.diff(original, revised);
            System.setOut(stdout);
            diff.getDeltas().forEach(l -> System.out.println(l.getTarget().size()));
        } catch (CompileException
                | IOException
                | InvocationTargetException
                | NoSuchMethodException
                | IllegalAccessException
                | ClassNotFoundException
                | DiffException e) {
            e.printStackTrace();
        }
    }
}
