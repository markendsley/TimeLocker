package timelocker;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.ShortBufferException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author Mark Endsley <your.name at your.org>
 */
public class Entry {
    
    public String date;
    public String content;
    public byte[] input;
    public byte[] keyBytes;
    public byte[] ivBytes;
    SecretKeySpec key;
    IvParameterSpec ivSpec;
    Cipher cipher;
    
    
    public Entry(String date, String content) throws NoSuchAlgorithmException, NoSuchPaddingException{
        
        this.date = date;
        this.content = content;
        
        keyBytes = "Markasdl".getBytes();
        ivBytes = "Markasdl".getBytes();
        
        key = new SecretKeySpec(keyBytes, "DES");
        ivSpec = new IvParameterSpec(ivBytes);
        cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        
        input = content.getBytes();
        
        
        
    }
    
    
    public String encryptContent() throws InvalidKeyException, InvalidAlgorithmParameterException, ShortBufferException, IllegalBlockSizeException, BadPaddingException{
        
        cipher.init(Cipher.ENCRYPT_MODE, key, ivSpec);
        byte[] encrypted = new byte[cipher.getOutputSize(input.length)];
        int encLen = cipher.update(input, 0, input.length, encrypted, 0);
        encLen += cipher.doFinal(encrypted, encLen);
        
        String encString = new String(encrypted, 0, encLen);
        
         System.out.println(encLen);
        
        return encString;
        
    }
    
    public void writeContent(String encContent) throws FileNotFoundException, UnsupportedEncodingException{
        
         PrintWriter writer = new PrintWriter("log.txt", "UTF-8");
         writer.println(date + "|" + encContent);
         writer.close();
         
        
        
         
    }
    

}
