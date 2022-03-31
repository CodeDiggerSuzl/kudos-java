package dao;

import dos.UserInfoDo;

import java.util.List;

/**
 * @author suzl
 */
public interface UserInfoDao {

    List<UserInfoDo> findByAge(Integer age);

}
