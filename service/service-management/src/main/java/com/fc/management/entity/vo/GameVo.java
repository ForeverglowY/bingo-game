package com.fc.management.entity.vo;

import com.fc.management.entity.GameBanner;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.omg.PortableServer.ServantActivator;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author Everglow
 * Created at 2022/11/03 15:36
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameVo implements Serializable {
    private String id;
    private String name;
    private String typeName;
    private BigDecimal score;
    private String picture;
    private BigDecimal price;
    private String intro;
    private List<GameBanner> gameBannerList;
    private Date gmtCreate;

}
