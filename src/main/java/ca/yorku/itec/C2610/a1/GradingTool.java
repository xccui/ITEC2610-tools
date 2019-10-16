package ca.yorku.itec.C2610.a1;

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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * GradingTool
 */
public class GradingTool {

    static final int MISSING_FILE = -10;
    static final int COMPILE_ERROR = -1;
    static final int RUN_ERROR = -2;
    static final int NO_OUTPUT = -3;
    static final int COMPARE_ERROR = -4;

    private static final String[] TARGET_FILES = new String[]{
            "PhoneCard.java",
            "CallZone.java",
            "CardTable.java",
            "Global10Card.java",
            "Global25Card.java",
            "SuperNA10Card.java",
            "SuperPhoneCardInc.java"};
    private static final String ENTRY_CLASS = "SuperPhoneCardInc";

    private List<String> outputLines = null;

    private File inputFile;
    private int i = 0;

    GradingTool(File inputFile, File outputFile) {
        this.inputFile = inputFile;
        try {
            outputLines = Files.readAllLines(outputFile.toPath());
        } catch (IOException e) {
            e.printStackTrace();
            System.err.printf("Output file %s does not exist\n", outputFile);
            System.exit(1);
        }
    }

    double getMatchRate(String sourceBase) {
        PrintStream stdout = System.out;
        InputStream stdin = System.in;
        Compiler compiler = new Compiler(
                null,
                new File[]{new File(".")},
                null,
                null,
                null,
                null,
                false,
                true,
                true,
                true,
                Compiler.DEFAULT_WARNING_HANDLE_PATTERNS,
                false);
        String userOutputPath = inputFile.getParent() + String.format("/output%d.txt", i);
        i++;

        try {
            Map<String, byte[]> classes = new HashMap<>();
            compiler.setClassFileCreator(new MapResourceCreator(classes));
            List<File> sourceFileList = new ArrayList<>(TARGET_FILES.length);
            for (String targetFile : TARGET_FILES) {
                sourceFileList.add(new File(sourceBase + "/" + targetFile));
            }
            compiler.compile(sourceFileList.toArray(new File[0]));
            ClassLoader classLoader = new ResourceFinderClassLoader(
                    new MapResourceFinder(classes),
                    ClassLoader.getSystemClassLoader());

            System.setOut(new PrintStream(userOutputPath));
            try {
                System.setIn(new FileInputStream(inputFile));
            } catch (FileNotFoundException ex) {
                System.err.printf("Input file %s does not exist\n", inputFile);
                System.exit(1);
            }
            classLoader.loadClass(ENTRY_CLASS).getDeclaredMethod("main", String[].class).invoke(null, (Object) new String[]{});
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            return MISSING_FILE;
        }
        catch (CompileException | IOException ex) {
            ex.printStackTrace();
            return COMPILE_ERROR;
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException ex) {
            ex.printStackTrace();
            return RUN_ERROR;
        } finally {
            System.setIn(stdin);
            System.setOut(stdout);
        }
        try {
            List<String> userOutput = Files.readAllLines(new File(userOutputPath).toPath());
            Patch<String> diff = DiffUtils.diff(outputLines, userOutput);
            return (outputLines.size() - diff.getDeltas().stream().mapToInt(l -> {
                switch (l.getType()) {
                    case CHANGE:
                        return Math.max(l.getSource().size(), l.getTarget().size());
                    case DELETE:
                        return l.getSource().size();
                    case INSERT:
                        return l.getTarget().size();
                    default:
                        return 0;
                }
            }).sum()) / (outputLines.size() + 0.0);
        } catch (IOException e) {
            return NO_OUTPUT;
        } catch (DiffException e) {
            return COMPARE_ERROR;
        }finally {
            System.setIn(stdin);
            System.setOut(stdout);
        }
    }

    public static void main(String[] args) {
        String basePath = "/Users/xccui/Desktop/2610/";
        GradingTool gradingTool = new GradingTool(
                new File(basePath + "/test/Input.txt"),
                new File(basePath + "/test/Output.txt"));
        System.out.println(gradingTool.getMatchRate("/Users/xccui/Desktop/2610/***/A1/"));
    }
}
