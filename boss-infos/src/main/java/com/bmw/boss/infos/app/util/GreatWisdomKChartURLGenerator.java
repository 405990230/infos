package com.bmw.boss.infos.app.util;

public class GreatWisdomKChartURLGenerator {
  private static String CHART_BASE_URL = "http://t.gw.com.cn/img/bd/stockData/";

  private static String CHART_URL_POSTFIX_TIME = "WeiboBlackM.png";
  private static String CHART_URL_POSTFIX_DAY = "WeiboBlackKD.png";
  private static String CHART_URL_POSTFIX_WEEK = "WeiboBlackKW.png";
  private static String CHART_URL_POSTFIX_MONTH = "WeiboBlackKM.png";

  public static String generateKChartURL(String stockCode, String kChartType) {

    StringBuffer url = new StringBuffer(CHART_BASE_URL);
    if (stockCode.matches("\\b[A-Za-z]{2}\\d+\\b")) {
      // Regex: Stock codestart with 2 letters and then digits
      url.append(decodeCommonStock(stockCode));
    } else if (stockCode.matches("\\bHK\\D+\\b")) {
      // Regex: Stock code starts with "HK", and no digit
      url.append(decodeHkStock(stockCode));
    }

    switch (kChartType) {
      case "hour":
        url.append(CHART_URL_POSTFIX_TIME);
        break;
      case "day":
        url.append(CHART_URL_POSTFIX_DAY);
        break;
      case "week":
        url.append(CHART_URL_POSTFIX_WEEK);
        break;
      case "month":
        url.append(CHART_URL_POSTFIX_MONTH);
        break;
      default:
        break;
    }
    return url.toString();
  }

  private static String decodeCommonStock(String stockCode) {
    String marketCode = stockCode.substring(0, 2);
    String lastTwoCode = stockCode.substring(stockCode.length() - 2);

    StringBuffer imageUrl = new StringBuffer();
    imageUrl.append(marketCode).append("/")
        .append(Integer.valueOf(lastTwoCode));
    imageUrl.append("/").append(stockCode).append("/").append(stockCode);

    return imageUrl.toString();
  }

  private static String decodeHkStock(String stockCode) {
    StringBuffer imageUrl = new StringBuffer("HK/100/");
    imageUrl.append(stockCode).append("/").append(stockCode);
    return imageUrl.toString();
  }

}
