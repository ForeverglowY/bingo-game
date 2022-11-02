import com.fc.commonutils.MD5;
import com.fc.management.entity.Admin;
import com.fc.management.service.impl.AdminServiceImpl;

/**
 * @author Everglow
 * Created at 2022/11/02 9:43
 */
public class Demo {

    public static void main(String[] args) {
        AdminServiceImpl adminService = new AdminServiceImpl();
        Admin admin = new Admin();
        admin.setUsername("admin");
        admin.setPassword(MD5.encrypt("admin"));
        adminService.save(admin);
    }
}
