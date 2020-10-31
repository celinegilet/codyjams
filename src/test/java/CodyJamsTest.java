
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.nio.file.Files.readAllBytes;
import static java.nio.file.Paths.get;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

public class CodyJamsTest {

    public static final String DIR_RESOURCES = "src/main/resources/";

    private CodyJams codyJams;

    @BeforeEach
    public void init() {
        codyJams = new CodyJams();
    }

    @Test
    public void printSalePricesByCase_withOneCase() {
        // Given
        String input =
                "1\n" +
                "3\n" +
                "15 20 60 75 80 100\n";
        List<String> lines = asList(input.split(CodyJams.NEW_LINE));

        // When
        String result = codyJams.printSalePricesByCase(lines);

        // Then
        assertThat(result).isEqualTo("Case #1: 15 60 75");
    }

    @Test
    public void printSalePricesByCase_withTwoCases() {
        // Given
        String input =
                "2\n" +
                "3\n" +
                "15 20 60 75 80 100\n" +
                "4\n" +
                "9 9 12 12 12 15 16 20";
        List<String> lines = asList(input.split(CodyJams.NEW_LINE));

        // When
        String result = codyJams.printSalePricesByCase(lines);

        // Then
        assertThat(result).isEqualTo(
                "Case #1: 15 60 75\n" +
                "Case #2: 9 9 12 15");
    }

    @Test
    public void printSalePricesByCase_withASmallPracticeIn() throws IOException {
        // Given
        ClassLoader classLoader = getClass().getClassLoader();
        File inputFile = new File(classLoader.getResource("A-small-practice.in").getFile());
        List<String> lines = Files.lines(get(inputFile.getPath())).collect(Collectors.toList());

        // When
        String result = codyJams.printSalePricesByCase(lines);

        // Then
        writeResultFile("A-small-practice.out", result);
        assertThat(Arrays.equals(
                        readAllBytes(get(DIR_RESOURCES + "A-small-practice.expected")),
                        readAllBytes(get(DIR_RESOURCES + "A-small-practice.out"))))
                    .isTrue();
    }

    @Test
    public void printSalePricesByCase_withALargePracticeIn() throws IOException {
        // Given
        ClassLoader classLoader = getClass().getClassLoader();
        File inputFile = new File(classLoader.getResource("A-large-practice.in").getFile());
        List<String> lines = Files.lines(get(inputFile.getPath())).collect(Collectors.toList());

        // When
        String result = codyJams.printSalePricesByCase(lines);

        // Then
        writeResultFile("A-large-practice.out", result);
        assertThat(Arrays.equals(
                        readAllBytes(get(DIR_RESOURCES + "A-large-practice.expected")),
                        readAllBytes(get(DIR_RESOURCES + "A-large-practice.out"))))
                    .isTrue();
    }

    private void writeResultFile(String fileName, String contentFile) throws IOException {
        Files.write(get(DIR_RESOURCES + fileName), contentFile.getBytes());
    }

}
