package ud2json04serializargson;

public class Users {

    public int UserId;
    public String UserName;
// Constructor

    public Users(int UserId, String UserName) {
        this.UserId = UserId;
        this.UserName = UserName;
    }
// getters y setters

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int UserId) {
        this.UserId = UserId;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }
// Sobrecarga del método toString para verlo como un JSON

    @Override
    public String toString() {
        String strObjeto;
        strObjeto = "{\"Userid\":" + UserId
                + ",\"UserName\":\"" + UserName + "\"}";
        return strObjeto;
    }
}
