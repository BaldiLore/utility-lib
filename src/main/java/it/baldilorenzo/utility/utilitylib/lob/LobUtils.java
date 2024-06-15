package it.baldilorenzo.utility.utilitylib.lob;

import javax.sql.rowset.serial.SerialBlob;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.Blob;
import java.sql.SQLException;

public abstract class LobUtils {

    public static String convertBlobToString(Blob immagine){

        if(immagine == null)
            return null;

        InputStream is = null;
        ByteArrayOutputStream os = null;

        try{
            is = immagine.getBinaryStream();
            os = new ByteArrayOutputStream();
            byte[] buffer = new byte[4096];
            int byteRead = -1;

            while((byteRead = is.read(buffer)) != -1){
                os.write(buffer, 0, byteRead);
            }

        } catch (SQLException | IOException e) {
            throw new RuntimeException("An error occured while converting blob to string");
        }

        return os.toString(StandardCharsets.UTF_8);
    }

    public static SerialBlob convertStringToBlob(String immagine){

        if(immagine == null || immagine.isBlank())
            return null;

        SerialBlob sb = null;

        try {
            sb = new SerialBlob(immagine.getBytes());
        } catch (SQLException e) {
            throw new RuntimeException("An error occured while converting string to blob");
        }

        return sb;
    }

}
