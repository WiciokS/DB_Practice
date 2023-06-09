package lt.viko.eif.vskuder.models;
public class Role {
    private int roleID;
    private String roleName;

    public Role(){}

    public int getRoleID() {
        return roleID;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return "Role\n{\n" +
                "roleID: " + roleID + "\n" +
                "roleName: " + roleName + "\n" +
                '}';
    }
}
