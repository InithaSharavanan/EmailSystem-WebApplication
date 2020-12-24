
import java.util.ArrayList;
import javax.faces.context.FacesContext;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author asini
 */
public class System {
    private String id;
    private String name;
    private String password;
    private String selectedEmailtoView;
    private String emailId;
    private String senderId;
    private String senderName;
    private String receiverId;
    private String receiverName;
    private String emailTitle;
    private String content;
    private String date;
    private String isReply;
    private String newVar;
    private String selectedSentEmailtoView;
    DataStorage data=new SQL_Database();
    ArrayList<Email> emails=new ArrayList<>();
    ArrayList<Email> emailbody=new ArrayList<>();
    
    public System(){
        
    }

    public System(String id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }
   

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getSelectedSentEmailtoView() {
        return selectedSentEmailtoView;
    }

    public void setSelectedSentEmailtoView(String selectedSentEmailtoView) {
        this.selectedSentEmailtoView = selectedSentEmailtoView;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public String getNewVar() {
        return newVar;
    }

    public void setNewVar(String newVar) {
        this.newVar = newVar;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getEmailTitle() {
        return emailTitle;
    }

    public void setEmailTitle(String emailTitle) {
        this.emailTitle = emailTitle;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getIsReply() {
        return isReply;
    }

    public void setIsReply(String isReply) {
        this.isReply = isReply;
    }

    public ArrayList<Email> getEmails() {
        return emails;
    }

    public void setEmails(ArrayList<Email> emails) {
        this.emails = emails;
    }

    public ArrayList<Email> getEmailbody() {
        return emailbody;
    }

    public void setEmailbody(ArrayList<Email> emailbody) {
        this.emailbody = emailbody;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSelectedEmailtoView() {
        return selectedEmailtoView;
    }

    public void setSelectedEmailtoView(String selectedEmailtoView) {
        this.selectedEmailtoView = selectedEmailtoView;
    }

    public DataStorage getData() {
        return data;
    }

    public void setData(DataStorage data) {
        this.data = data;
    }
    
    
       public String signOut()
    {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "index.xhtml"; 
    }
       
    public ArrayList<Email> displayLatestEmails(){
        newVar="new";
            emails=data.getLatestEmail(id);
            return emails;

    }
    public ArrayList<Email> displaySentEmails(){
            emails=data.getSentEmail(id);
            return emails;

    }
     public String updateSelectedEmailToView(String emailTitle){
        selectedEmailtoView=emailTitle;
        newVar="";
        return "emailBody.xhtml";
    }
     public String updateSelectedSentEmailToView(String emailTitle){
         selectedSentEmailtoView=emailTitle;
         return "sentEmailBody.xhtml";
     }
     public ArrayList<Email> displayEmailBody(){
         newVar="";
         emailbody=data.getEmailBody(id,selectedEmailtoView);
        return emailbody; 
     }
     public ArrayList<Email> displaySentEmailBody(){
         newVar="";
         emailbody=data.getSentEmailBody(id,selectedSentEmailtoView);
        return emailbody; 
     }
     public String sendReply(){
         emailId=data.getEmailId(selectedEmailtoView);
         String fileName=data.sendReplyEmail(emailId, senderId, senderName, receiverId, receiverName, selectedEmailtoView, content, "Yes");
         return fileName;
     }
     public String newEmail(){
         String fileName=data.newEmail(emailId, senderId, senderName, receiverId, receiverName, emailTitle, content, "No");
         return fileName;
     }
     public String deleteEmail(String emailTitle){
         String fileName=data.deleteEmail(id,emailTitle);
         return fileName;
     }
}
