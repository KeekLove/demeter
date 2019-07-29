package com.demeter.portal.dao;
import com.demeter.common.pojo.FoodInfoDO;
import com.demeter.common.pojo.ManagerInfoDO;
import com.demeter.portal.pojo.FindMenuByIdDTO;
import com.demeter.portal.pojo.SelectMenuDTO;
import com.demeter.portal.pojo.UploadMenuDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface FoodMapperDao {
    List<ManagerInfoDO> selectAll();

    /**
     * 根据id修改菜单信息
     * @param id
     * @param icon
     * @param prize
     * @param name
     * @param about
     * @param menuId
     * @return
     */
    Integer updateById(@Param("id") Long id, @Param("icon") String icon,
                       @Param("prize") Float prize, @Param("name") String name,
                       @Param("about") String about, @Param("menuId") Long menuId);

    /**
     * 这里是测试了中文的获取所有菜单信息的SQL测试
     * @return
     */
    List<FoodInfoDO> findAll();
    /**
     * 授权码和用户id
     * @return
     */
    List<FindMenuByIdDTO> findFood(@Param("card") String card,
                                   @Param("menuId") long menuId,
                                   @Param("id") long id);

    /**
     * 添加菜单
     * @param uploadMenuDTO
     * @return
     */
    Integer insertMenu(UploadMenuDTO uploadMenuDTO);

    /**
     * 通过card获取菜单
     * @param card
     * @return
     */
    List<SelectMenuDTO> findMenu(String card);

    /**
     * 根据菜单id删除菜单（伪删除）
     * @param menuId
     * @return
     */
    Integer delMenu(Long menuId);
}
