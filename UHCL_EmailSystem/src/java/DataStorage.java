
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author asini
 */
public interface DataStorage {
    public String login(String id,String password);
    public ArrayList<Email> getLatestEmail(String id);
    public ArrayList<Email> getSentEmail(String id);
    public ArrayList<Email> getEmailBody(String id, String emailTitle);
    public ArrayList<Email> getSentEmailBody(String id, String emailTitle);
    public String sendReplyEmail(String emailId,String senderId,String senderName,String receiverId,String receiverName,String selectedEmailtoView,String content,String isReply);
    public String newEmail(String emailId,String senderId,String senderName,String receiverId,String receiverName,String selectedEmailtoView,String content,String isReply);
    public String getEmailId(String emailTitle);
    public String deleteEmail(String id,String emailTitle);
    
}
