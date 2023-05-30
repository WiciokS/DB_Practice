package lt.viko.eif.vskuder.models;

public class RolePermission {
    private Role roleID;
    private Permission permissionID;

    public RolePermission(){}

    public Role getRoleID() {
        return roleID;
    }

    public void setRoleID(Role roleID) {
        this.roleID = roleID;
    }

    public Permission getPermissionID() {
        return permissionID;
    }

    public void setPermissionID(Permission permissionID) {
        this.permissionID = permissionID;
    }

    @Override
    public String toString() {
        return "RolePermission\n{\n" +
                "roleID: " + roleID + "\n" +
                "permissionID: " + permissionID + "\n" +
                '}';
    }
}
