import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SimpleTextEditor {

    private StringBuilder textContent;
    private Path filePath;

    public SimpleTextEditor() {
        this.textContent = new StringBuilder();
    }

    // Open a text file
    public void openFile(String fileName) throws IOException {
        filePath = Paths.get(fileName);
        textContent = new StringBuilder(new String(Files.readAllBytes(filePath)));
    }

    // Edit the text
    public void editText(String newText) {
        textContent.append(newText);
    }

    // Save the text file
    public void saveFile() throws IOException {
        if (filePath == null) {
            throw new IllegalStateException("File not opened");
        }
        Files.write(filePath, textContent.toString().getBytes());
    }

    // Search within the text
    public boolean search(String keyword) {
        return textContent.toString().contains(keyword);
    }

    // Get the current content (for testing)
    public String getTextContent() {
        return textContent.toString();
    }

    // Main method to execute some basic operations
    public static void main(String[] args) throws IOException {
        SimpleTextEditor editor = new SimpleTextEditor();
        editor.openFile("example.txt");
        editor.editText("\nAdding new content.");
        editor.saveFile();

        if (editor.search("new content")) {
            System.out.println("Search term found!");
        } else {
            System.out.println("Search term not found.");
        }
    }
}

// JUnit 5 Test Class
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

public class SimpleTextEditorTest {

    private SimpleTextEditor editor;
    private final String testFileName = "testFile.txt";

    @BeforeEach
    public void setUp() throws IOException {
        editor = new SimpleTextEditor();
        // Create a test file with initial content
        Path testFilePath = Paths.get(testFileName);
        Files.write(testFilePath, "Initial Content".getBytes());
    }

    @Test
    public void testOpenFile() throws IOException {
        editor.openFile(testFileName);
        assertEquals("Initial Content", editor.getTextContent(), "File content should match");
    }

    @Test
    public void testEditText() throws IOException {
        editor.openFile(testFileName);
        editor.editText(" - New Content");
        assertEquals("Initial Content - New Content", editor.getTextContent(), "Text should be appended correctly");
    }

    @Test
    public void testSaveFile() throws IOException {
        editor.openFile(testFileName);
        editor.editText(" - New Content");
        editor.saveFile();

        // Verify the content in the file
        String fileContent = new String(Files.readAllBytes(Paths.get(testFileName)));
        assertEquals("Initial Content - New Content", fileContent, "File content should be saved correctly");
    }

    @Test
    public void testSearchWithinText() throws IOException {
        editor.openFile(testFileName);
        assertTrue(editor.search("Initial"), "Keyword should be found in the text");
        assertFalse(editor.search("NotFound"), "Keyword should not be found in the text");
    }

    @Test
    public void testSaveFileWithoutOpening() {
        assertThrows(IllegalStateException.class, () -> {
            editor.saveFile();
        });
    }

    @AfterEach
    public void tearDown() throws IOException {
        Files.deleteIfExists(Paths.get(testFileName));
    }
}
