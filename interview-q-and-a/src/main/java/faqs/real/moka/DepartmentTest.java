package faqs.real.moka;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 一种逻辑上树的遍历, 写的时候没写出来 SHAME ON ME
 * <p></p>
 * <a href="https://blog.csdn.net/Juwenzhe_HEBUT/article/details/121962895">link</a>
 * <p>
 * 其实就是类似于前序遍历!!!!
 * </p>
 */
public class DepartmentTest {

    private static final Gson gson = new Gson();

    public static void main(String[] args) {
        List<Department> allDepartment = new ArrayList<>();
        Department dep1 = new Department(1, 0, "北京总部");
        Department dep3 = new Department(3, 1, "研发中心");
        Department dep4 = new Department(4, 3, "后端研发组");
        Department dep6 = new Department(6, 4, "后端实习生组");
        Department dep7 = new Department(7, 3, "前端研发组");
        Department dep8 = new Department(8, 1, "产品部");

        allDepartment.add(dep6);
        allDepartment.add(dep7);
        allDepartment.add(dep8);
        allDepartment.add(dep1);
        allDepartment.add(dep3);
        allDepartment.add(dep4);


        List<Department> subDepartments = DepartmentTest.getSub(3, allDepartment);
        for (Department subDepartment : subDepartments) {
            System.out.println(subDepartment);
        }
    }

    /* *******************************************************************/

    /**
     * 根据id，获取所有子部门列表(包括隔代子部门，一直到叶子节点)
     * 要求：不能新增参数，不能增加static变量
     */
    public static List<Department> getSub(int id, List<Department> allDepartment) {
        List<Department> children = allDepartment.stream().filter(department -> department.getPid() == id).collect(Collectors.toList());

        ArrayList<Department> result = new ArrayList<>(children);
        System.out.println(gson.toJson(result));
        if (!children.isEmpty()) {
            for (Department child : children) {
                result.addAll(getSub(child.getId(), allDepartment));
            }
        }
        return result;
    }
    /* *******************************************************************/

}


@Data
@AllArgsConstructor
class Department {
    /**
     * id
     */
    private int id;
    /**
     * parent id
     */
    private int pid;
    /**
     * 名称
     */
    private String name;

}