import java.util.ArrayList;
import java.util.List;

/**
 * @version: 1.0.0
 * @author: guog
 * @date: 2021/6/24 20:35
 * @description:
 **/
public class TestFilter {
    public static void main(String[] args) {
        InitArea initArea = new InitArea();
        List<AreaInfo> areaInfoList = new ArrayList<>();
        AreaInfo areaInfo2 = new AreaInfo();
        areaInfo2.setQryOrgId("9999");
        areaInfo2.setQryUserId("7777");
        List<AreaCode> areaCodeList = new ArrayList<>();
        AreaCode areaCode2 = new AreaCode();
        areaCode2.setCode("123");
        areaCode2.setName("张三");

        AreaCode areaCode3 = new AreaCode();
        areaCode3.setCode("345");
        areaCode3.setName("李四");

        areaCodeList.add(areaCode2);
        areaCodeList.add(areaCode3);
        areaInfo2.setAreaCodes(areaCodeList);
        areaInfoList.add(areaInfo2);

        initArea.setAreaInfos(areaInfoList);

        AreaInfo nn = initArea.getAreaInfos().stream().filter(areaInfo -> (areaInfo.getAreaCodes().stream().filter(areaCode -> areaCode.getCode().equals("123")).findFirst().orElse(null)) != null).findFirst().orElse(null);
        if (nn == null) {
            System.out.println("99999999999999");
        } else {
            System.out.println("888888888888888888");
        }


    }
}
