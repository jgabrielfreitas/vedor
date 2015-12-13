package hackpuc.vedor.utils;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Random;

/**
 * Created by JGabrielFreitas on 12/12/15.
 */
public class ApplicationCache {

    private Context context;
    private final String TAG = "ApplicationCache";
    private String filePath = null;

    /**
     * class to work with application's cache
     * */
    public ApplicationCache(Context context) {
        this.context = context;
        this.filePath = context.getFilesDir() + "/";
    }

    /**
     * save a Object in cache using json
     * */
    public void saveObjectEncryptedJSON(Object object, String name) throws FileNotFoundException {


        Gson gson = new Gson();
        String objectToSave = gson.toJson(object);

        String key = generateRandomKey();

        Encryption encryption = new Encryption();
        FileOutputStream fileOutputStream = new FileOutputStream(filePath + name);
        PrintStream printStream = new PrintStream(fileOutputStream);
        String encryptedFile = encryption.encrypt(key, objectToSave) + key;
        printStream.print(encryptedFile);
        printStream.close();

    }

    /**
     * save a Object in cache using json
     * */
    public void saveObjectEncrypted(String fileName, String toSave) throws FileNotFoundException {


        String key = generateRandomKey();

        Encryption encryption = new Encryption();
        FileOutputStream fileOutputStream = new FileOutputStream(filePath + fileName);
        PrintStream printStream = new PrintStream(fileOutputStream);
        String encryptedFile = encryption.encrypt(key, toSave) + key;
        printStream.print(encryptedFile);
        printStream.close();

    }

    /**
     * generate random key to save cache encrypted
     * */
    private String generateRandomKey() {
        // generate random String with this characters below
        String characters = "ak123c23uipomn456qh%@!&ud789ioh0fgoszqploakhd=f+-*/";
        char[] text = new char[23];
        for (int i = 0; i < 23; i++)
            text[i] = characters.charAt(new Random().nextInt(characters.length()));
        return new String(text);
    }

    public String decryptFile(String fileName) {

        String objectToReturn = null;

        if (checkIfCacheExists(fileName) == true) {


            Encryption encryption = new Encryption();

            // search and read file from cache
            String dataFromCache = readArchiveFromCache(fileName);

            // the key is the last 23 characters of the content stored in the cache
            // example:
            // it's the content:
            //
            //          Ti+WuCa37uGyAmpdOxEndVHy38/+6jROQTh86EKlnZHMPdlpmDgqfhfYaSOfTcAQB2LW3Q+awxqPsS8mLkg+ovHgiPvLLvGMXOjK3
            //          VSwCCEJ2byDNI3jZmydbQxiqsfgxN6aFD+qY3OwRBWZcAo1tDYRFquIhJ7+zm7eh+JQZB7m66lkTCwug+2O9BT5GBxr5kU/xG5szi
            //          BMJ8SxutD5sRYBkErfUFBKHVODAo9ioCdd09uLt6RwKthSEfdYD4VlB9sjJEFxHmqubCM+Pjsf6Fr4aHDuqaeAKZIzU8/JIKycaPp
            //          9nJCac2S5Omx8pkFs6Hz2Lsg413rLP3zG6kfBZXLtGdN34NworaGPy0o4zwZ93X59ssLGE83msAZWIuiz+yrVT9IGE3+ryyDxKHlb
            //          J5c7Bdy/oyJ363JdXTLMuATm66lkTCwugwjLeSUQZnDIxRSr38vo7yp/9lDWCnAspv389ZFVkDb1bDvEw3yFWL/np8nLD/X6zNxj2
            //          27gO0qEAXGSxvPtI3SGRUQItiOKmesbwdSImg/kWpXEEkyjOAk=opk6d2@odp&od+u!zq9hc%3
            //
            // the key is: opk6d2@odp&od+u!zq9hc%3 (the last 23 characters
            if(dataFromCache != null) {
                String key = dataFromCache.substring(dataFromCache.length() - 23, dataFromCache.length());

                // this message is the example above without the last 23 characters
                // so...the is the content above without 'opk6d2@odp&od+u!zq9hc%3'
                String messageToDecryptWithOutKey = dataFromCache.substring(0, dataFromCache.length() - 23);

                // return the content decrypted, as String
                objectToReturn = encryption.decrypt(key, messageToDecryptWithOutKey);
            }

            return objectToReturn;

        } else
            return objectToReturn;
    }

    public Object decryptFileJSON(String fileName, Class classObject) {

        Object objectToReturn = null;

        if (checkIfCacheExists(fileName) == true) {


            Encryption encryption = new Encryption();

            // search and read file from cache
            String dataFromCache = readArchiveFromCache(fileName);

            Gson gson = new Gson();
            // the key is the last 23 characters of the content stored in the cache
            // example:
            // it's the content:
            //
            //          Ti+WuCa37uGyAmpdOxEndVHy38/+6jROQTh86EKlnZHMPdlpmDgqfhfYaSOfTcAQB2LW3Q+awxqPsS8mLkg+ovHgiPvLLvGMXOjK3
            //          VSwCCEJ2byDNI3jZmydbQxiqsfgxN6aFD+qY3OwRBWZcAo1tDYRFquIhJ7+zm7eh+JQZB7m66lkTCwug+2O9BT5GBxr5kU/xG5szi
            //          BMJ8SxutD5sRYBkErfUFBKHVODAo9ioCdd09uLt6RwKthSEfdYD4VlB9sjJEFxHmqubCM+Pjsf6Fr4aHDuqaeAKZIzU8/JIKycaPp
            //          9nJCac2S5Omx8pkFs6Hz2Lsg413rLP3zG6kfBZXLtGdN34NworaGPy0o4zwZ93X59ssLGE83msAZWIuiz+yrVT9IGE3+ryyDxKHlb
            //          J5c7Bdy/oyJ363JdXTLMuATm66lkTCwugwjLeSUQZnDIxRSr38vo7yp/9lDWCnAspv389ZFVkDb1bDvEw3yFWL/np8nLD/X6zNxj2
            //          27gO0qEAXGSxvPtI3SGRUQItiOKmesbwdSImg/kWpXEEkyjOAk=opk6d2@odp&od+u!zq9hc%3
            //
            // the key is: opk6d2@odp&od+u!zq9hc%3 (the last 23 characters)
            String key = dataFromCache.substring(dataFromCache.length() - 23, dataFromCache.length());

            // this message is the example above without the last 23 characters
            // so...the is the content above without 'opk6d2@odp&od+u!zq9hc%3'
            String messageToDecryptWithOutKey = dataFromCache.substring(0, dataFromCache.length() - 23);

            // return the content decrypted, as String
            String objectDecrypted = encryption.decrypt(key, messageToDecryptWithOutKey);

            // parse String object to JSON and turns in Object
            objectToReturn = gson.fromJson(objectDecrypted, classObject);

            return objectToReturn;

        } else
            return objectToReturn;
    }


    /**
     * check if cache exists by name<br>
     * IMPORTANT: This method just check the file in 'file' folder.
     * */
    public boolean checkIfCacheExists(String fileName) {
        if(context != null) {
            File file = new File(filePath + fileName);
            return file.exists();
        } else {
            Log.e(TAG, "Your context passed to ApplicationCache is null. I'm returning false for you.");
            return false;
        }
    }

    /**
     * print in logcat a cache from stone.application file folder
     * ONLY FOR DEBUG
     * */
    public void showCacheInLogByFileName(String fileName) {
        if (checkIfCacheExists(fileName) == true) {

            try {

                FileInputStream fileInputStreams    = context.openFileInput(fileName);
                InputStreamReader inputStreamReader = new InputStreamReader(fileInputStreams);
                BufferedReader bufferedReader 	    = new BufferedReader(inputStreamReader);
                String fromCache = null;
                String currentLine;

                while ((currentLine = bufferedReader.readLine()) != null)
                    if (fromCache == null)
                        fromCache = currentLine;
                    else
                        fromCache += "\n" + currentLine;

                Log.d(TAG, fromCache);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else
            Log.e(TAG, "There's no stone.cache with this name: " + fileName);
    }

    /**
     * save String in cache
     * */
    public void saveObject(String fileName, String toSave) throws Exception {

        Log.i("saving", toSave);
        FileOutputStream fos = new FileOutputStream(filePath + fileName);
        fos.write(toSave.getBytes());
        fos.close();
    }

    /**
     * Read file from your cache folder
     * and return the content as String Object
     * {@code IMPORTANT}: return null if file was not found
     * */
    public String readArchiveFromCache(String fileName) {

        try {
            if (checkIfCacheExists(fileName) == true) {

                FileInputStream fileInputStream = context.openFileInput(fileName);
                InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String currentLine = null;
                String dataFromCache = null;

                while ((currentLine = bufferedReader.readLine()) != null)
                    if (dataFromCache == null)
                        dataFromCache = currentLine;
                    else
                        dataFromCache += "\n" + currentLine;

                return dataFromCache;
            } else
                return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public void deleteFile(String fileName) {

        if (checkIfCacheExists(fileName) == true) {
            File file = new File(filePath + fileName);
            file.delete();
        }

    }
}
