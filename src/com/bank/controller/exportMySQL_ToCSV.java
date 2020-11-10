package com.bank.controller;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
 
import jxl.write.Label;
 
public class exportMySQL_ToCSV {
 
    /**
     * @param args
     */
    public static void main(String[] args) {
          
          //usual database connection part
          Connection con = null;
          String url = "jdbc:mysql://localhost:3306/";
          String db = "iitr";
          String user = "root";
          String pass = "1234";
          FileWriter fw ;
          try{
          con = DriverManager.getConnection(url+db, user, pass);
          Statement st = con.createStatement();
           
          //this query gets all the tables in your database(put your db name in the query)
          ResultSet res = st.executeQuery("SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE table_schema = '"+db+"' ");
           
          //Preparing List of table Names
          List <String> tableNameList = new ArrayList<String>();
          while(res.next())
          {
              tableNameList.add(res.getString(3));
          }
           
          //path to the folder where you will save your csv files
          String filename = "C:\\Users\\ujjwa\\Desktop\\Practice\\Winter\\CSVs";
           
          //star iterating on each table to fetch its data and save in a .csv file
          for(String tableName:tableNameList)
            {
                int k=0;
                 
                int j=1;
                 
                System.out.println(tableName);
                 
                List<String> columnsNameList  = new ArrayList<String>();
                 
                //select all data from table
                res = st.executeQuery("select * from "+tableName);
                 
                //colunm count is necessay as the tables are dynamic and we need to figure out the numbers of columns
                int columnCount = getColumnCount(res);
                 
                 try {
                    fw = new FileWriter(filename+"_"+tableName+".csv");
                     
                     
                    //this loop is used to add column names at the top of file , if you do not need it just comment this loop
                    for(int i=1 ; i<= columnCount ;i++)
                    {
                        fw.append(res.getMetaData().getColumnName(i));
                        if(i<columnCount) {
                        	fw.append(",");
                        }
                    }
                     
                    fw.append(System.getProperty("line.separator"));
                     
                    while(res.next())
                    {
                        for(int i=1;i<=columnCount;i++)
                        {
                             
                            //you can update it here by using the column type but i am fine with the data so just converting 
                            //everything to string first and then saving
                            if(res.getObject(i)!=null)
                            {
                            String data= res.getObject(i).toString();
                            fw.append(data) ;
                            if(i<columnCount) {
                            	fw.append(",");
                            }
                            }
                            else
                            {
                                String data= "null";
                                fw.append(data) ;
                                if(i<columnCount) {
                                	fw.append(",");
                                }
                            }
                             
                        }
                        //new line entered after each row
                        fw.append(System.getProperty("line.separator"));
                    }
                     
                     fw.flush();
                      fw.close();
                     
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                 
            }
                 
          con.close();
          }
          catch(Exception ex){
        	  ex.printStackTrace();
          }
 }
     
    //to get numbers of rows in a result set 
    public static int  getRowCount(ResultSet res) throws SQLException
    {
          res.last();
          int numberOfRows = res.getRow();
          res.beforeFirst();
          return numberOfRows;
    }
 
    //to get no of columns in result set
     
    public static int  getColumnCount(ResultSet res) throws SQLException
    {
        return res.getMetaData().getColumnCount();
    }
}