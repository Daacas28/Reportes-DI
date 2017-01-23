/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reportesesion2;

/**
 *
 * @author dam2
 */
public class Participante {
    
    
    
       private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }
       private String nombre;
       private String userName;
       private String password;
       private String comentarios;
    

    public Participante(int id, String nombre, String userName, String password, String comentarios) {
        this.id = id;
        this.nombre = nombre;
        this.userName = userName;
        this.password = password;
        this.comentarios = comentarios;
    }
       

    /**
     * @param args the command line arguments
     */
   
    
}
