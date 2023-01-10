/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Task;
import util.ConnectionFactory;

/**
 *
 * @author HP
 */
public class TaskController {
    
    public void removeById(int TaskId){
        String sql = "DELETE FROM tasks WHERE id = ?";
        
        Connection connection = null;
        PreparedStatement statement = null;
        
        try{
            //Criacao da conexao com o sgbd
            connection = ConnectionFactory.getConnection();
            
            //Preparando a query
            statement = connection.prepareStatement(sql);
            
            //Setando os valores
            statement.setInt(1, TaskId);
            statement.execute();  
        }catch(Exception ex){
            throw new RuntimeException("Erro ao deletar a tarefa.");            
        }finally{
        ConnectionFactory.closeConnection(connection, statement);
        }      
    }
    
    
    
    
    
    public void save(Task task){
        
        String sql = "INSERT INTO tasks(idProject, name, description, completed, notes, deadline, createdAt, updatedAt) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
        
        Connection connection = null;
        PreparedStatement statement = null;
        
        try{
           connection = ConnectionFactory.getConnection();
           statement = connection.prepareStatement(sql);
           
           statement.setInt(1, task.getIdProject());
           statement.setString(2, task.getName());
           statement.setString(3, task.getDescription());
           statement.setBoolean(4, task.isIsCompleted());
           statement.setString(5, task.getNotes());
           statement.setDate(6, new Date(task.getDeadline().getTime()));
           statement.setDate(7, new Date(task.getCreatedAt().getTime()));
           statement.setDate(8, new Date(task.getUpdatedAt().getTime()));
           statement.execute();
                     
        }catch(Exception ex){
            throw new RuntimeException("Erro ao salvar a tarefa" + ex.getMessage(), ex);
            
        }
        finally{
            ConnectionFactory.closeConnection(connection, statement);
        }       
    }
    
    
    
    public void update(Task task){
        
        String sql = "UPDATE tasks SET idProject = ?, name = ?, description = ?, notes = ?, completed = ?, deadline = ?, createdAt = ?, updatedAt = ? where id = ?";
        
        
        Connection connection = null;
        PreparedStatement statement = null;
        
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            
           statement.setInt(1, task.getIdProject());
           statement.setString(2, task.getName());
           statement.setString(3, task.getDescription());
           statement.setString(4, task.getNotes());
           statement.setBoolean(5, task.isIsCompleted());
           statement.setDate(6, new Date(task.getDeadline().getTime()));
           statement.setDate(7, new Date(task.getCreatedAt().getTime()));
           statement.setDate(8, new Date(task.getUpdatedAt().getTime()));
           statement.setInt(9, task.getId());
           statement.execute();
           
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao atualizar");
        }
        finally{
                ConnectionFactory.closeConnection(connection, statement);
            }
        
    }
    
    
    public List<Task> getAll(int idProjet){
        
        String sql = "SELECT * FROM tasks WHERE idProject = ?";
        
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        
        List<Task> tasks = new ArrayList<Task>();
        
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            
            statement.setInt(1, idProjet);
            resultSet = statement.executeQuery();
            
            while(resultSet.next()){
                Task task = new Task();
                
                task.setId(resultSet.getInt("id"));
                task.setIdProject(resultSet.getInt("idProject"));
                task.setName(resultSet.getString("name"));
                task.setDescription(resultSet.getString("description"));
                task.setNotes(resultSet.getString("notes"));
                task.setIsCompleted(resultSet.getBoolean("completed"));
                task.setDeadline(resultSet.getDate("deadline"));
                task.setCreatedAt(resultSet.getDate("createdAt"));
                task.setUpdatedAt(resultSet.getDate("updatedAt"));
                tasks.add(task);
            }
            
        } catch (Exception ex) {
            
            throw new RuntimeException("Erro ao buscar as tarefas");
            }
        
            finally{            
            ConnectionFactory.closeConnection(connection, statement, resultSet);
            
            }

     // Lista de tarefas que foi criada e carregada do banco de dados.
        return tasks;
    }
    
}
