package com.demeter.common.pojo.dto;

import java.io.Serializable;

public class PayDto implements Serializable {
    private static final long serialVersionUID = 2L;
    private String out_trade_no;  //商户网站的唯一订单号
    //    private String product_code="FAST_INSTANT_TRADE_PAY";  //销售产品码，商家和支付宝签约的产品码，为固定值QUICK_MSECURITY_PAY
    private String total_amount; //订单总金额，单位为元，
    private String subject; //商品的标题/交易标题
    private String body; //对一笔交易的具体描述信息。，
//    private String passback_params="merchantBizType%3d3C%26merchantBizNo%3d2016010101111"; //对一笔交易的具体描述信息。，
//    private String extend_params="{\"sys_service_provider_id\":\"2088511833207846\"}"; //对一笔交易的具体描述信息。，


    public PayDto() {
    }

    public PayDto(String out_trade_no, String total_amount, String subject, String body) {
        this.out_trade_no = out_trade_no;
        this.total_amount = total_amount;
        this.subject = subject;
        this.body = body;
    }

    @Override
    public String toString() {
        return "{" +
                "    \"out_trade_no\":\""+out_trade_no+"\"," +
                "    \"product_code\":\"FAST_INSTANT_TRADE_PAY\"," +
                "    \"total_amount\":"+total_amount+"," +
                "    \"subject\":\""+subject+"\"," +
                "    \"body\":\""+body+"\"," +
                "    \"passback_params\":\"merchantBizType%3d3C%26merchantBizNo%3d2016010101111\"," +
                "    \"extend_params\":{" +
                "    \"sys_service_provider_id\":\"2088511833207846\"" +
                "    }"+
                "  }";
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public String getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(String total_amount) {
        this.total_amount = total_amount;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
