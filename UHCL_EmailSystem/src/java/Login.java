/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author asini
 */
@ManagedBean
@SessionScoped
public class Login {

    private String id;
    private String password;
    private String name;
    private System theSystem;
    private String newVar;
    DataStorage data=new SQL_Database();
    public Login() {
    }

    public Login(String id, String password, String name) {
        this.id = id;
        this.password = password;
        this.name = name;
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

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNewVar() {
        return newVar;
    }

    public void setNewVar(String newVar) {
        this.newVar = newVar;
    }

    public System getTheSystem() {
        return theSystem;
    }

    public void setTheSystem(System theSystem) {
        this.theSystem = theSystem;
    }

    public DataStorage getData() {
        return data;
    }

    public void setData(DataStorage data) {
        this.data = data;
    }
    
     public String login()
    {
        //load the Driver
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            
        }
        catch (Exception e)
        {
            e.printStackTrace();
            //return to internalError.xhtml
            return ("internalError");
        }
        String fileName ="";
        
             fileName= data.login(id, password);
             if(fileName.equals("homePage"))
            {
                theSystem=new System(id,password,name);
                theSystem.displayLatestEmails();
                
                return "homePage";
            }
                return fileName;
            
            
    }
         
    
}

