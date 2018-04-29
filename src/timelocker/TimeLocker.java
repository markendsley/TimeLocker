
package timelocker;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.ShortBufferException;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Mark Endsley <your.name at your.org>
 */
public class TimeLocker extends JFrame{


   private final JLabel stringLabel, yearLabel, monthLabel, dayLabel, hourLabel, minuteLabel, resultLabel;
   private final JTextField contentBox, yearBox, monthBox, dayBox, hourBox, minuteBox, resultBox;
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
        createButton.setBackground(Color.darkGray);
        createButton.setForeground(Color.WHITE);
        
        
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
        
        resultLabel = new JLabel("Result");
        box.add(resultLabel);
        resultBox = new JTextField();
        box.add(resultBox);
        
        
        JButton retrieveButton = new JButton( "Get Entry" );
        box.add(retrieveButton);
        retrieveButton.setBackground(Color.darkGray);
        retrieveButton.setForeground(Color.WHITE);
        
        
        retrieveButton.addActionListener(
        
            new ActionListener(){
                    

                @Override
                public void actionPerformed(ActionEvent e) {
                
                    //TODO May need to delete this code
                    
                    
                    try {
                        Entry entry = new Entry();
                        entry.readContent();
                        
                        
                        if(testDate(entry)){
                            
                            JOptionPane.showMessageDialog(null, "The date for this entry is not here yet");
                            return;
                            
                        }
                        
                        resultBox.setText(entry.content);
                      
                        
                    } catch (NoSuchAlgorithmException ex) {
                        Logger.getLogger(TimeLocker.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (NoSuchPaddingException ex) {
                        Logger.getLogger(TimeLocker.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (InvalidKeyException ex) {
                        Logger.getLogger(TimeLocker.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (InvalidAlgorithmParameterException ex) {
                        Logger.getLogger(TimeLocker.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IllegalBlockSizeException ex) {
                        Logger.getLogger(TimeLocker.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (BadPaddingException ex) {
                        Logger.getLogger(TimeLocker.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (FileNotFoundException ex) {
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
    
    private void extractInput() throws FileNotFoundException{
        
        //TODO Will take input string as given filename
        
        Scanner reader = new Scanner(new File("log.txt"));
        reader.useDelimiter("..|..");
        
        String givenDate = reader.next();
        String givenContent = reader.next();
        
        
        
        
    }
    
    public boolean testDate(Entry entry) throws InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException{
        
        String fullCurrent = getCurrentDate();
        String fullSet = entry.decryptedDate();
        
        
        
        int curYear,curMonth,curDay, curHour, curMinute;
        int setYear,setMonth,setDay, setHour, setMinute;
        String curArray[] = fullCurrent.split(" ");
        String setArray[] = fullSet.split(" ");
        
        curYear = Integer.parseInt(curArray[0]);
        curMonth = Integer.parseInt(curArray[1]);
        curDay = Integer.parseInt(curArray[2]);
        curHour = Integer.parseInt(curArray[3]);
        curMinute = Integer.parseInt(curArray[4]);
        
        setYear = Integer.parseInt(setArray[0]);
        setMonth = Integer.parseInt(setArray[1]);
        setDay = Integer.parseInt(setArray[2]);
        setHour = Integer.parseInt(setArray[3]);
        setMinute = Integer.parseInt(setArray[4]);
        
        System.out.println("Current date is: " + curArray[0] + "Set date is: " + setArray[0] );
        
        System.out.println(curYear + "is more than" + setYear);
        
        
        if(curYear > setYear){
            return false;
        }
            
        else if(curMonth > setMonth){
            
            return false;
        }
            
        else if(curDay > setDay){
            return false;
        }
            
        else if(curHour > setHour){
            return false;
        }
            
        else if(curMinute > setMinute){
            return false;
        }
            
        
        
        
        
        
        return true;
    }
}