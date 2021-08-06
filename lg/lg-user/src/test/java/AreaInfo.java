import lombok.Data;

import java.util.List;

/**
 * @version: 1.0.0
 * @author: guog
 * @date: 2021/6/24 20:33
 * @description:
 **/
@Data
public class AreaInfo {
    private String qryOrgId;
    private String qryUserId;
    private List<AreaCode> areaCodes;
}
