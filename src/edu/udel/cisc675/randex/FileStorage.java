package edu.udel.cisc675.randex;
import java.io.FileReader;

/* Module FileStorage: hides the design decision behind
 * storing the contents of the latex file uploaded provided
 * in the CLI.
 */
public class FileStorage {
    private static FileStorage instance;

    private char[] fileChars;

    private FileStorage() {}

    public static FileStorage getInstance() {
        if (instance == null)
            instance = new FileStorage();
        return instance;
    }

    public char getChar(int i) { return this.fileChars[i]; }

    public char[] getChars() { return this.fileChars; }

    public void setFileChars(char[] fileChars) { this.fileChars = fileChars; }
}