import java.io.UnsupportedEncodingException;

/**
 * @version 1.0.0
 * @author： guog
 * @date 2021/6/10 10:22
 * @description
 */
public class Testlength {
    public static void main(String[] args) throws UnsupportedEncodingException {
        String str = "{\"SysHead\":{\"CnsmrSeqNo\":\"202004303082796942\",\"SvcSplrSeqNo\":\"F-ICPS020080600000002689189\",\"EventNo\":\"202004303082796942\",\"TxnSt\":\"0\",\"TxnDealTp\":\"0\",\"RetCd\":\"000000\",\"RetMsg\":\"交易成功\"},\"AppHead\":{},\"LocalHead\":{\"TxnTlrId\":\"0000\",\"OrgId\":\"0000\",\"TlrPwsd\":\"000\",\"AhrFlag\":\"0000\",\"AhrTlrInfo\":[{\"AhrTlrId\":\"\",\"AhrOrgId\":\"\",\"AhrTlrPswd\":\"\",\"AhrTlrLvl\":\"\",\"AhrTlrTp\":\"\"}]},\"Body\":{\"ChnlSrc\":\"105\",\"CoprtPltfm\":\"0001\",\"LoanAttr\":\"02\",\"PrdtCgy\":\"01\",\"PrdtCd\":\"0001010002\",\"DsbrTrm\":\"12,24,36\",\"RepyMd\":\"4\",\"ExecIntRate\":\"\",\"BaseIntRate\":\"\",\"IntRateFltMd\":\"0\",\"IntRateAdjMd\":\"1\",\"IntRateFltRto\":\"30\",\"LoanPpsDsc\":\"0009\",\"MinDsbrAmt\":\"1000\",\"MaxDsbrAmt\":\"500000\"}}";
        String formartStr = new String(str.getBytes(), "UTF-8");
        System.out.println(String.format("%08d", formartStr.getBytes("UTF-8").length) + formartStr);


        String str2 = "{\"SysHead\":{\"CnsmrSeqNo\":\"202004303082796941\",\"SvcSplrSeqNo\":\"F-ICPS020080600000002689188\",\"EventNo\":\"202004303082796941\",\"TxnSt\":\"0\",\"TxnDealTp\":\"1\",\"RetCd\":\"ICPS01000006\",\"RetMsg\":\"贷款产品查询失败，请稍后再试（错误码：ICPS01000006）\"},\"AppHead\":{},\"LocalHead\":{\"TxnTlrId\":\"0000\",\"OrgId\":\"0000\",\"TlrPwsd\":\"000\",\"AhrFlag\":\"0000\",\"AhrTlrInfo\":[{\"AhrTlrId\":\"\",\"AhrOrgId\":\"\",\"AhrTlrPswd\":\"\",\"AhrTlrLvl\":\"\",\"AhrTlrTp\":\"\"}]},\"Body\":{\"ChnlSrc\":\"105\",\"CoprtPltfm\":\"0001\",\"LoanAttr\":\"02\",\"PrdtCgy\":\"01\",\"PrdtCd\":\"000101000301\",\"DsbrTrm\":\"\",\"RepyMd\":\"\",\"ExecIntRate\":\"\",\"BaseIntRate\":\"\",\"IntRateFltMd\":\"\",\"IntRateAdjMd\":\"\",\"IntRateFltRto\":\"\",\"LoanPpsDsc\":\"\",\"MIN_AMOUNT\":\"\",\"MAX_AMOUNT\":\"\"}}";
        String formartStr2 = new String(str2.getBytes(), "UTF-8");
        System.out.println(String.format("%08d", formartStr2.getBytes("UTF-8").length) + formartStr2);

//        System.out.println(String.format("%08d", str2.length() + 8) + str2);
//
//        System.out.println("123456789".substring(8));
    }

}
