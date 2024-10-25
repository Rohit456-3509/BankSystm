
package bank.management.system;
import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class Ministatement extends JFrame{
String Pin;
    Ministatement(String Pin){
        this.Pin=Pin;
        setTitle("Mini Statement ");
        setLayout(null);
        
       JLabel mini = new JLabel();
       add(mini);
       
        JLabel bank = new JLabel("Indian Bank");
        bank.setBounds(150, 20, 100, 20);
       add(bank);
       
       JLabel card = new JLabel();
        card.setBounds(20, 80, 300, 20);
       add(card);
       
       JLabel balance = new JLabel();
       balance.setBounds(20, 400, 300, 20);
       add(balance);
       
       try{
           Connect c=new Connect ();
          ResultSet rs =c.s.executeQuery("select * from login where pin ='"+Pin+"'");
          while(rs.next()){
              card.setText("Card Number "+rs.getString("card").substring(0,4)+"xxxxxxxx"+rs.getString("card").substring(12));
          }
           
       }catch(Exception e){
           System.out.println(e);
       }
       
       try{
           Connect c=new Connect ();
           int bal=0;
          ResultSet rs =c.s.executeQuery("select * from bank where pin ='"+Pin+"'");
          while(rs.next()){
             mini.setText(mini.getText()+"<html> "+rs.getString("date")+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+ rs.getString("type")+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+rs.getString("amount")+"<br><br><html>");
           
                   if(rs.getString("type").equals("Deposit")){
                       bal +=Integer.parseInt(rs.getString("amount"));
                   }else{
                       bal -=Integer.parseInt(rs.getString("amount")); 
         
                   }} balance.setText("Your Current Account Balance Rs "+bal);
       }catch(Exception e){
           System.out.println(e);
       }
       mini.setBounds(20, 100, 400, 200);
        
         setSize(400,600);
        setLocation(50,50);
       // setUndecorated(true);
       getContentPane().setBackground(Color.PINK);
        setVisible(true);
    }
    public static void main(String[] args) {
        new Ministatement("");
    }
    
}