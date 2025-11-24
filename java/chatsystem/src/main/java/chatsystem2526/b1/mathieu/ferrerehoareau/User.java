package chatsystem2526.b1.mathieu.ferrerehoareau;

import java.io.Serializable;

/**
 * Implement what is a User
 */
class User implements Serializable{
    public String name;

    /**
     * Return the username of a User
     * @return String
     */
    public String getName(){
        return this.name;
    }
    /**
     * Set the username of a User
     * @param New_name Username
     */
    public void setLogin(String New_name){
        this.name = New_name;
    }
}