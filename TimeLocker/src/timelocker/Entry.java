package timelocker;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Scanner;
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
    
    
    public Entry(String date, String content) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, ShortBufferException, IllegalBlockSizeException, BadPaddingException{
        
        this.date = date;
        this.content = content;
        
        keyBytes = "Markasdl".getBytes();
        ivBytes = "Markasdl".getBytes();
        
        key = new SecretKeySpec(keyBytes, "Blowfish");
        //ivSpec = new IvParameterSpec(ivBytes);
        cipher = Cipher.getInstance("Blowfish");
        
        input = content.getBytes();
        
        encryptDate();
        
        
    }
    
    public Entry() throws NoSuchAlgorithmException, NoSuchPaddingException{
        
        
        keyBytes = "Markasdl".getBytes();
        ivBytes = "Markasdl".getBytes();
        
        key = new SecretKeySpec(keyBytes, "Blowfish");
        //ivSpec = new IvParameterSpec(ivBytes);
        cipher = Cipher.getInstance("Blowfish");
        
        //input = content.getBytes();
        
        
        
    }
    
    public void encryptDate() throws InvalidKeyException, InvalidAlgorithmParameterException, ShortBufferException, IllegalBlockSizeException, BadPaddingException{
        
        
        
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encrypted = cipher.doFinal(date.getBytes());
        
        
        
        Base64.Encoder encoder = Base64.getEncoder();
        String encString = encoder.encodeToString(encrypted);
        
        
        
        this.date = encString;

        
    }
    
    public String encryptContent() throws InvalidKeyException, InvalidAlgorithmParameterException, ShortBufferException, IllegalBlockSizeException, BadPaddingException{
        
        
        
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encrypted = cipher.doFinal(content.getBytes());
        
        
        
        Base64.Encoder encoder = Base64.getEncoder();
        String encString = encoder.encodeToString(encrypted);
        
        
        
        return encString;

        
    }
    
    public void decryptContent(String ncontent) throws InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException{
        
        
        cipher.init(Cipher.DECRYPT_MODE, key);
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] decrypted=decoder.decode(ncontent.getBytes());
        
        String strData = new String(cipher.doFinal(decrypted));
       
        
        
        content = strData;

       
    }
    
    public String decryptedDate() throws InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException{
        
        
        cipher.init(Cipher.DECRYPT_MODE, key);
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] decrypted=decoder.decode(date.getBytes());
        
        String strData = new String(cipher.doFinal(decrypted));
       
        
        
        

        return strData;
       
    }
    
    
    public void writeContent(String encContent) throws FileNotFoundException, UnsupportedEncodingException{
        
        
        //TODO allow file name to be chosen from TimeLocker class
        
        try (PrintWriter writer = new PrintWriter("log.txt", "UTF-8")) {
            writer.print(date + "/////" + encContent);
        }
         

         
    }
    
    public void readContent() throws InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, FileNotFoundException{
        
        String tempContent;
        
        //Ok decryption is fucked up
        
        
            Scanner scanner;
            File file = new File("log.txt");
            scanner = new Scanner(file);
            
            String temp = scanner.next();
            
            String data[] = temp.split("/////");
            
            String tempDate = data[0];
            
            date = tempDate;
            tempContent = data[1];
            
            decryptContent(tempContent);
        
        

        
    }
    


}
