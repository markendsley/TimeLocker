
package timelocker;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.ShortBufferException;
import javax.swing.Action;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author Mark Endsley <your.name at your.org>
 */
public class TimeLocker extends JFrame{


   private final JLabel stringLabel, yearLabel, monthLabel, dayLabel, hourLabel, minuteLabel;
   private final JTextField contentBox, yearBox, monthBox, dayBox, hourBox, minuteBox;
   private final Box box;
    
    
    public TimeLocker(){
        
        super("TimeLocker");
        
        box = Box.createVerticalBox();
        
        
        stringLabel = new JLabel("String");
        box.add(stringLabel);
        contentBox = new JTextField();
        box.add(contentBox);

        yearLabel = new JLabel("Year");
        box.add(yearLabel);
        yearBox = new JTextField();
        box.add(yearBox);
        
        monthLabel = new JLabel("Month");
        box.add(monthLabel);
        monthBox = new JTextField();
        box.add(monthBox);
        
        dayLabel = new JLabel("Day");
        box.add(dayLabel);
        dayBox = new JTextField();
        box.add(dayBox);
        
        hourLabel = new JLabel("Hour");
        box.add(hourLabel);
        hourBox = new JTextField();
        box.add(hourBox);
        
        minuteLabel = new JLabel("Minute");
        box.add(minuteLabel);
        minuteBox = new JTextField();
        box.add(minuteBox);
        
        JButton createButton = new JButton( "Create Entry" );
        box.add(createButton);
        
        
        createButton.addActionListener(
        
            new ActionListener(){
                    

                @Override
                public void actionPerformed(ActionEvent e) {
                
                    try {
                        writeEntry(yearBox.getText() + " " + monthBox.getText() + " " + dayBox.getText() + " " + hourBox.getText() + " " + minuteBox.getText() ,contentBox.getText());
                        
                    } catch (NoSuchAlgorithmException ex) {
                        Logger.getLogger(TimeLocker.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (NoSuchPaddingException ex) {
                        Logger.getLogger(TimeLocker.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (InvalidKeyException ex) {
                        Logger.getLogger(TimeLocker.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (InvalidAlgorithmParameterException ex) {
                        Logger.getLogger(TimeLocker.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ShortBufferException ex) {
                        Logger.getLogger(TimeLocker.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IllegalBlockSizeException ex) {
                        Logger.getLogger(TimeLocker.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (BadPaddingException ex) {
                        Logger.getLogger(TimeLocker.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(TimeLocker.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (UnsupportedEncodingException ex) {
                        Logger.getLogger(TimeLocker.class.getName()).log(Level.SEVERE, null, ex);
                    }
                
                    
                
                
                }
                    
            }
        
        );
        
        add (box, BorderLayout.NORTH);
        
        
        getCurrentDate();
        
        setSize( 300, 700 );
        setVisible( true ); 
        setDefaultCloseOperation( DISPOSE_ON_CLOSE );
    }
    
    
    public static void main(String[] args) {
        
        TimeLocker locker = new TimeLocker();
        
    }
    
    private String getCurrentDate(){
        
        Date now = new Date();
        String nonFormat = now.toString();
        SimpleDateFormat format = new SimpleDateFormat("yyyy MM dd HH mm");
        System.out.println(format.format(now));
        
        return format.format(now);
    }
    
    private void writeEntry(String givenDate, String content) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, ShortBufferException, IllegalBlockSizeException, BadPaddingException, FileNotFoundException, UnsupportedEncodingException{
        
        Entry entry = new Entry(givenDate, content);
        String temp = entry.encryptContent();
        entry.writeContent(temp);
    }
}
