package bg.tusofia;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.atomic.AtomicInteger;

public class FileManipulator {
    private String path;
    private Path filePath;

    public FileManipulator(String path) {
        this.path = path;
        this.filePath = Paths.get(path);
    }

    public String getLineByIndex(int index) throws IOException {
        return Files
                .lines(filePath)
                .skip(index)
                .findFirst()
                .orElse(null);
    }

    public void replaceLines(int index1, int index2) throws IOException {
        String line1 = this.getLineByIndex(index1);
        String line2 = this.getLineByIndex(index2);

        this.performLineReplace(line1, line2, index1, index2);
    }

    public void replaceWords(int line1Index, int word1Index, int line2Index, int word2Index) throws IOException {
        String line1 = this.getLineByIndex(line1Index);
        String line2 = this.getLineByIndex(line2Index);

        String[] words1 = line1.split("\\s+");
        String[] words2 = line2.split("\\s+");

        String temp = words1[word1Index];
        words1[word1Index] = words2[word2Index];
        words2[word2Index] = temp;

        line1 = String.join(" ", words1);
        line2 = String.join(" ", words2);

        this.performLineReplace(line1, line2, line1Index, line2Index);
    }

    private void performLineReplace(final String line1, final String line2, int line1Index, int line2Index) throws IOException {
        String copiedPath = path.concat(".tmp");
        AtomicInteger count = new AtomicInteger();
        FileOutputStream outputStream = new FileOutputStream(copiedPath);

        Files.lines(filePath).forEach(line -> {
            try {
                int currentIndex = count.getAndIncrement();
                if (currentIndex == line1Index) {
                    outputStream.write(line2.getBytes());
                } else if (currentIndex == line2Index) {
                    outputStream.write(line1.getBytes());
                } else {
                    outputStream.write(line.getBytes());
                }
                outputStream.write('\n');
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        outputStream.close();

        Files.delete(filePath);
        Files.move(Paths.get(copiedPath), filePath);
    }
}
