
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
public class SQL_Database implements DataStorage{

      final String DATABASE_URL="jdbc:mysql://127.0.0.1:3306/annamalaishai70?useSSL=false";
        final String db_id="root";
        final String db_pwd="root123";

    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;
    @Override
    public String login(String id, String password) {
         try
        {

            //connect to the databse
            connection = DriverManager.getConnection(DATABASE_URL,
                  db_id, db_pwd);
            //create statement
            statement = connection.createStatement();
            //search the accountID in the onlineAccount table
            resultSet = statement.executeQuery("Select * from user where id = '" + id + "'");

            if(resultSet.next())
            {
                //the id is found, check the password
                if(password.equals(resultSet.getString(2)))
                {
                    //password is good
                    return "homePage";
                    //go to the welcome page
                }
                else
                {
                    //password is not correct
                    return "loginNotOK";

                }
            }
            else
            {
                return "loginNotOK";
            }

        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return "internalError";
        }
        finally
        {
            //close the database
            try
            {
                connection.close();
                statement.close();
                resultSet.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }

        }
    }

    @Override
    public ArrayList<Email> getLatestEmail(String id) {
        try
        {
            //connect to the databse
            connection = DriverManager.getConnection(DATABASE_URL,
                  db_id, db_pwd);

            statement = connection.createStatement();
            //Get all the threads to display in thread page
              ArrayList<Email> aList = new ArrayList<Email>();
              int count=0;
            resultSet = statement.executeQuery("Select * from email where receiverId='" + id + "' order by date desc");
            while(resultSet.next()&& count<2)
            {
                Email email = new Email(resultSet.getString(1),
                    resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getString(7),resultSet.getString(8),resultSet.getString(9));
                aList.add(email);
                count++;
            }
            return aList;

        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
        finally
        {
            //close the database
            try
            {
                connection.close();
                statement.close();
                resultSet.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }

        }

    }

    @Override
    public ArrayList<Email> getEmailBody(String id, String emailTitle) {
       try
        {
            //connect to the databse
            connection = DriverManager.getConnection(DATABASE_URL,
                  db_id, db_pwd);

            statement = connection.createStatement();
            //Get all the threads to display in thread page
              ArrayList<Email> aList = new ArrayList<Email>();
              int count=0;
            resultSet = statement.executeQuery("Select * from email where receiverId='" + id + "' and emailTitle='" + emailTitle + "' order by date asc");
            while(resultSet.next())
            {
                Email email = new Email(resultSet.getString(1),
                    resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getString(7),resultSet.getString(8),resultSet.getString(9));
                aList.add(email);
                
            }
            return aList;

        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
        finally
        {
            //close the database
            try
            {
                connection.close();
                statement.close();
                resultSet.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }

        }

    }

    @Override
    public String sendReplyEmail(String emailId,String senderId, String senderName, String receiverId, String receiverName, String selectedEmailtoView, String content, String isReply) {
         try
        {
            connection = DriverManager.getConnection(DATABASE_URL, db_id, db_pwd);
            statement = connection.createStatement();
            connection.setAutoCommit(false);
            //insert a record in jobShare table
            int r=statement.executeUpdate("insert into email values('" + emailId + "','" + senderId + "','" + senderName + "','" + receiverId + "','" + receiverName + "','" + selectedEmailtoView + "','" + content + "',now(),'" + isReply + "')");
            connection.commit();
            connection.setAutoCommit(true);
            return "confirm";

        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return "internalError";
        }
        finally
        {
            //close the database
            try
            {
                connection.close();
                statement.close();
                //resultSet.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }

        }
    }

    @Override
    public String getEmailId(String emailTitle) {
         try
        {
            //connect to the databse
            connection = DriverManager.getConnection(DATABASE_URL,
                  db_id, db_pwd);

            statement = connection.createStatement();
            //Get all the threads to display in thread page
              

            resultSet = statement.executeQuery("Select emailId from email where emailTitle='" + emailTitle + "'");
            if(resultSet.next())
            {
                return resultSet.getString(1);
            }
            return null;

        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
        finally
        {
            //close the database
            try
            {
                connection.close();
                statement.close();
                resultSet.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }

        }
    }

    @Override
    public String newEmail(String emailId, String senderId, String senderName, String receiverId, String receiverName, String selectedEmailtoView, String content, String isReply) {
  try
        {
            connection = DriverManager.getConnection(DATABASE_URL, db_id, db_pwd);
            statement = connection.createStatement();
            connection.setAutoCommit(false);
            //insert a record in jobShare table
            int r=statement.executeUpdate("insert into email values('" + emailId + "','" + senderId + "','" + senderName + "','" + receiverId + "','" + receiverName + "','" + selectedEmailtoView + "','" + content + "',now(),'" + isReply + "')");
            connection.commit();
            connection.setAutoCommit(true);
            return "confirm";

        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return "internalError";
        }
        finally
        {
            //close the database
            try
            {
                connection.close();
                statement.close();
                //resultSet.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }

        }
    }

    @Override
    public ArrayList<Email> getSentEmail(String id) {
        try
        {
            //connect to the databse
            connection = DriverManager.getConnection(DATABASE_URL,
                  db_id, db_pwd);

            statement = connection.createStatement();
            //Get all the threads to display in thread page
              ArrayList<Email> aList = new ArrayList<Email>();
              
            resultSet = statement.executeQuery("Select * from email where senderId='" + id + "' order by date desc");
            while(resultSet.next())
            {
                Email email = new Email(resultSet.getString(1),
                    resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getString(7),resultSet.getString(8),resultSet.getString(9));
                aList.add(email);
              
            }
            return aList;

        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
        finally
        {
            //close the database
            try
            {
                connection.close();
                statement.close();
                resultSet.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }

        }
    }

    @Override
    public String deleteEmail(String id, String emailTitle) {
 try
        {
            connection = DriverManager.getConnection(DATABASE_URL, db_id, db_pwd);
            statement = connection.createStatement();
            connection.setAutoCommit(false);
            //insert a record in jobShare table
            int r=statement.executeUpdate("delete from email where receiverId='" + id + "' and emailTitle='" + emailTitle + "'");
            connection.commit();
            connection.setAutoCommit(true);
            return "confirm";

        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return "internalError";
        }
        finally
        {
            //close the database
            try
            {
                connection.close();
                statement.close();
                //resultSet.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }

        }
    }

    @Override
    public ArrayList<Email> getSentEmailBody(String id, String emailTitle) {
     try
        {
            //connect to the databse
            connection = DriverManager.getConnection(DATABASE_URL,
                  db_id, db_pwd);

            statement = connection.createStatement();
            //Get all the threads to display in thread page
              ArrayList<Email> aList = new ArrayList<Email>();
              int count=0;
            resultSet = statement.executeQuery("Select * from email where senderId='" + id + "' and emailTitle='" + emailTitle + "' order by date asc");
            while(resultSet.next())
            {
                Email email = new Email(resultSet.getString(1),
                    resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getString(7),resultSet.getString(8),resultSet.getString(9));
                aList.add(email);
                
            }
            return aList;

        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
        finally
        {
            //close the database
            try
            {
                connection.close();
                statement.close();
                resultSet.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }

        }

    }

    
}
