package flipTracker.utils.fileUtils;

import java.io.*;

/**
 * Created on 21.4.2017.
 * <p>
 * Serialization utils
 */
public class SerializationUtils {

    public static void serialize(String target, Object object) {
        File targetFile = new File(target);
        if (!targetFile.exists())
            try {
                if (!targetFile.createNewFile())
                    throw new IOException("Error creating file");
            } catch (IOException e) {
                e.printStackTrace();
            }
        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(targetFile))) {
            output.writeObject(object);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error writing object to file!");
        }
    }

    public static Object deserialize(String target) {
        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(target))) {
            return input.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("File " + target + " doesn't exist.");
            return null;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Error loading object from file!");
        }
        return null;
    }
}
