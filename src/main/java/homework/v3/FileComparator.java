package homework.v3;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.io.File;

class FileComparator implements Comparator<File> {
    @Override
    public int compare(File f1, File f2) {
        if (f1 == f2) return 0;
        else if (f1 == null) return -1;
        else if (f2 == null) return 1;


        if (f1.isDirectory() || f2.isDirectory()) {
            throw new IllegalArgumentException("Невозможно сравнить содержимое каталога.");
        }

        if (f1.length() < f2.length()) return -1;
        else if (f1.length() > f2.length()) return 1;

        try {
            return compareContent(f1, f2);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    private int compareContent(File f1, File f2) throws IOException {
        final int BUFFER_SIZE = 1024;

        try (BufferedInputStream is1 = new BufferedInputStream(new FileInputStream(f1), BUFFER_SIZE);
             BufferedInputStream is2 = new BufferedInputStream(new FileInputStream(f2), BUFFER_SIZE);) {

            byte[] b1 = new byte[BUFFER_SIZE];
            byte[] b2 = new byte[BUFFER_SIZE];

            int read1 = -1;
            int read2 = -1;
            int read = -1;

            do {
                read1 = is1.read(b1);
                read2 = is2.read(b2);

                if (read1 < read2) return -1;
                 else if (read1 > read2) return 1;
                 else read = read1;


                if (read >= 0) {
                    if (read != BUFFER_SIZE) {
                        Arrays.fill(b1, read, BUFFER_SIZE, (byte) 0);
                        Arrays.fill(b2, read, BUFFER_SIZE, (byte) 0);
                    }

                    if (!Arrays.equals(b1, b2)) {
                        return new String(b1).compareTo(new String(b2));
                    }
                }
            } while (read >= 0);


            return 0;
        }
    }
}
