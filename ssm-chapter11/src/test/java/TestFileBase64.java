import org.springframework.ui.Model;
import org.springframework.util.Base64Utils;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.stream.FileImageOutputStream;
import java.io.*;
import java.util.Base64;
import java.util.Collections;
import java.util.UUID;

public class TestFileBase64 {
    public static void main(String[] args) throws IOException {
        File pig = new File("/home/lnx/ss/test2.jpg");
        pig.createNewFile();
        //strBase64ToFile(encodeFile64(pig),"/home/lnx/Desktop/d.jpg");
    }

    public static String encodeFile64(File file){
        StringBuilder strBuilder = new StringBuilder();
        byte [] bytes = new byte[1024];
        String str = null;
        try {
                FileInputStream fileInputStream = new FileInputStream(file);
                int len =0;
                bytes = new byte[fileInputStream.available()];
                fileInputStream.read(bytes);
                str = Base64.getEncoder().encodeToString(bytes);
                strBuilder.append(str);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return strBuilder.toString();
    }

    public static boolean strBase64ToFile(String strBase64,String newFilePath) {
        if(newFilePath==null||strBase64==null){
            throw new NullPointerException("the strBase64 to transfer or newFilePath cannot be null");
        }
        FileOutputStream fouStream = null;
        boolean flag = true;
        try {
            byte[] bytes = Base64.getDecoder().decode(strBase64);
            fouStream = new FileOutputStream(newFilePath);
            fouStream.write(bytes);
            fouStream.flush();
        }catch(IOException ie){

            flag = false;
        }finally {
            if(fouStream!=null){
                try {
                    fouStream.close();
                } catch (IOException empty) {
                }
            }
        }
        return flag;
    }
}
