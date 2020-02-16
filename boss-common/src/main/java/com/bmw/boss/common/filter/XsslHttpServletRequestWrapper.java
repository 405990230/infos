package com.bmw.boss.common.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.regex.Pattern;

/**
 * @Description: XSS代码的过滤是在XsslHttpServletRequestWrapper中实现的，
 * 主要是覆盖实现了getParameter，getParameterValues，getHeader这几个方法，
 * 然后对获取的value值进行XSS处理
 * @Author boss yan
 * @Date 2018/10/23 13:41
 */
public class XsslHttpServletRequestWrapper extends HttpServletRequestWrapper {

    HttpServletRequest xssRequest = null;

    public XsslHttpServletRequestWrapper(HttpServletRequest request) {
        super(request);
        xssRequest = request;
    }


    /**
     * 覆盖getParameter方法，将参数名和参数值都做xss & sql过滤。<br/>
     * 如果需要获得原始的值，则通过super.getParameterValues(name)来获取<br/>
     * getParameterNames,getParameterValues和getParameterMap也可能需要覆盖
     */
    @Override
    public String getParameter(String name) {
        String value = super.getParameter(replaceXSS(name));
        if (value != null) {
            value = replaceXSS(value);
        }
        return value;
    }

    @Override
    public String[] getParameterValues(String name) {
        String[] values = super.getParameterValues(replaceXSS(name));
        if(values != null && values.length > 0){
            for(int i =0; i< values.length ;i++){
                values[i] = replaceXSS(values[i]);
            }
        }
        return values;
    }

    /**
     * 覆盖getHeader方法，将参数名和参数值都做xss & sql过滤。<br/>
     * 如果需要获得原始的值，则通过super.getHeaders(name)来获取<br/>
     * getHeaderNames 也可能需要覆盖
     */
    @Override
    public String getHeader(String name) {

        String value = super.getHeader(replaceXSS(name));
        if (value != null) {
            value = replaceXSS(value);
        }
        return value;
    }
    /**
     * 去除待带script、src、img的语句，转义替换后的value值
     */
    public static String replaceXSS(String value) {
        if (value != null) {
            try{
                value = value.replace("+","%2B");   //'+' replace to '%2B'
                value = URLDecoder.decode(value, "utf-8");
            }catch(UnsupportedEncodingException e){
            }catch(IllegalArgumentException e){
            }

            // Avoid null characters
            value = value.replaceAll("\0", "");

            // Avoid anything between script tags
            Pattern scriptPattern = Pattern.compile("<script>(.*?)</script>", Pattern.CASE_INSENSITIVE);
            value = scriptPattern.matcher(value).replaceAll("");

            scriptPattern = Pattern.compile("<img>(.*?)</img>", Pattern.CASE_INSENSITIVE);
            value = scriptPattern.matcher(value).replaceAll("");

            // Avoid anything in a src='...' type of e­xpression
            scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\'(.*?)\\\'", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");

            scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\"(.*?)\\\"", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");

            // Remove any lonesome </script> tag
            scriptPattern = Pattern.compile("</script>", Pattern.CASE_INSENSITIVE);
            value = scriptPattern.matcher(value).replaceAll("");

            // Remove any lonesome </script> tag
            scriptPattern = Pattern.compile("</img>", Pattern.CASE_INSENSITIVE);
            value = scriptPattern.matcher(value).replaceAll("");

            // Remove any lonesome <script ...> tag
            scriptPattern = Pattern.compile("<script(.*?)>", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");

            scriptPattern = Pattern.compile("<img(.*?)>", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");

            // Avoid eval(...) e­xpressions
            scriptPattern = Pattern.compile("eval\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");

            // Avoid e­xpression(...) e­xpressions
            scriptPattern = Pattern.compile("e­xpression\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");

            // Avoid javascript:... e­xpressions
            scriptPattern = Pattern.compile("javascript:", Pattern.CASE_INSENSITIVE);
            value = scriptPattern.matcher(value).replaceAll("");
            // Avoid alert:... e­xpressions
            scriptPattern = Pattern.compile("alert", Pattern.CASE_INSENSITIVE);
            value = scriptPattern.matcher(value).replaceAll("");
            // Avoid onload= e­xpressions
            scriptPattern = Pattern.compile("onload(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");
            scriptPattern = Pattern.compile("vbscript[\r\n| | ]*:[\r\n| | ]*", Pattern.CASE_INSENSITIVE);
            value = scriptPattern.matcher(value).replaceAll("");
        }
        return filter(value);
    }

    /**
     * 过滤特殊字符
     */
    public static String filter(String value) {
        if (value == null) {
            return null;
        }
        StringBuffer result = new StringBuffer(value.length());
        for (int i=0; i<value.length(); ++i) {
            switch (value.charAt(i)) {
                case '<':
                    result.append("<");
                    break;
                case '>':
                    result.append(">");
                    break;
                case '"':
                    result.append("\"");
                    break;
                case '\'':
                    result.append("'");
                    break;
                case '%':
                    result.append("%");
                    break;
                case ';':
                    result.append(";");
                    break;
                case '(':
                    result.append("(");
                    break;
                case ')':
                    result.append(")");
                    break;
                case '&':
                    result.append("&");
                    break;
                case '+':
                    result.append("+");
                    break;
                default:
                    result.append(value.charAt(i));
                    break;
            }
        }
        return result.toString();
    }
}