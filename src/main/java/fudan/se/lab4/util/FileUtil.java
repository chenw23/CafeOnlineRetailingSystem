package fudan.se.lab4.util;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;
import fudan.se.lab4.constant.FileConstant;
import fudan.se.lab4.constant.InfoConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.text.MessageFormat;

public class FileUtil {
    private static Logger logger = LoggerFactory.getLogger(FileUtil.class);

    private static boolean createFile(String destFileName) {
        File file = new File(destFileName);
        if (file.exists()) {
            return true;
        } else {
            if (destFileName.endsWith(File.separator)) {
                throw new RuntimeException(MessageFormat.format(InfoConstant.FILE_CANNOT_BE_DIR, destFileName));
            }
            // file exists?
            if (!file.getParentFile().exists()) {
                //if the parent dir is not exist, then create it.
                logger.info(InfoConstant.CREATING_PARENT_DIR);
                if (!file.getParentFile().mkdirs()) {
                    throw new RuntimeException(InfoConstant.FAILED_CREAT_DIR);
                }
            }
            // create target file.
            try {
                if (file.createNewFile()) {
                    logger.info(InfoConstant.SUCCESS_TO_CREATE_FILE, destFileName);
                    return true;
                } else {
                    throw new RuntimeException(MessageFormat.format(InfoConstant.FAILED_TO_CREATE_FILE, destFileName));
                }
            } catch (IOException e) {
                logger.info(e.getMessage());
                throw new RuntimeException(MessageFormat.format(InfoConstant.FAILED_TO_CREATE_FILE_REASON, destFileName,
                        e.getMessage()));
            }
        }
    }

    public static void write(String[] array, String dataFilePath) {
        if (createFile(dataFilePath)) {
            BufferedWriter bw;
            try {
                bw = new BufferedWriter(new FileWriter(dataFilePath, true));
                CsvWriter out = new CsvWriter(bw, FileConstant.CSV_SEPARATOR);
                out.writeRecord(array);
                out.flush();
                bw.flush();
                out.close();
                bw.close();
            } catch (IOException e) {
                logger.info(e.getMessage());
                throw new RuntimeException(e.getMessage());
            }
        }
    }

    public static String[] readByName(String Name, String dataFilePath) {
        CsvReader reader;
        try {
            reader = new CsvReader(dataFilePath, FileConstant.CSV_SEPARATOR,
                    Charset.forName(FileConstant.CHARSET));
            while (reader.readRecord()) {
                String[] item = reader.getValues();
                if (item[0].equals(Name)) {
                    return item;
                }
            }
        } catch (IOException e) {
            logger.info(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }

        throw new RuntimeException(MessageFormat.format(InfoConstant.ENTITY_NOT_FOUND, Name));
    }

    public static boolean exist(String name, String dataFilePath) {
        createFile(dataFilePath);
        CsvReader reader;
        try {
            reader = new CsvReader(dataFilePath, FileConstant.CSV_SEPARATOR,
                    Charset.forName(FileConstant.CHARSET));
            while (reader.readRecord()) {
                String[] item = reader.getValues();
                if (item[0].equals(name)) {
                    return true;
                }
            }
        } catch (IOException e) {
            logger.info(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
        return false;
    }
}
