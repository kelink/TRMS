//package com.dummy.common;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import org.springframework.security.authentication.AuthenticationServiceException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.security.web.util.TextEscapeUtils;
//import org.springframework.util.StringUtils;
//
//public class ValidateCodeUsernamePasswordAuthenticationFilter extends  
//        UsernamePasswordAuthenticationFilter {  
//  
//    private boolean postOnly = true;  
//    private boolean allowEmptyValidateCode = false;  
//    private String sessionvalidateCodeField = DEFAULT_SESSION_VALIDATE_CODE_FIELD;  
//    private String validateCodeParameter = DEFAULT_VALIDATE_CODE_PARAMETER;  
//    public static final String SPRING_SECURITY_LAST_USERNAME_KEY = "SPRING_SECURITY_LAST_USERNAME";  
//    // session�б������֤��  
//    public static final String DEFAULT_SESSION_VALIDATE_CODE_FIELD = "rand";  
//    // �������֤��  
//    public static final String DEFAULT_VALIDATE_CODE_PARAMETER = "code";  
//  
//    @Override  
//    public Authentication attemptAuthentication(HttpServletRequest request,  
//            HttpServletResponse response) throws AuthenticationException {  
//        if (postOnly && !request.getMethod().equals("POST")) {  
//            throw new AuthenticationServiceException(  
//                    "Authentication method not supported: "  
//                            + request.getMethod());  
//        }  
//  
//        String username = obtainUsername(request);  
//        String password = obtainPassword(request);  
//  
//        if (username == null) {  
//            username = "";  
//        }  
//  
//        if (password == null) {  
//            password = "";  
//        }  
//  
//        username = username.trim();  
//  
//        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(  
//                username, password);  
//  
//        // Place the last username attempted into HttpSession for views  
//        HttpSession session = request.getSession(false);  
//  
//        if (session != null || getAllowSessionCreation()) {  
//            request.getSession().setAttribute(  
//                    SPRING_SECURITY_LAST_USERNAME_KEY,  
//                    TextEscapeUtils.escapeEntities(username));  
//        }  
//  
//        // Allow subclasses to set the "details" property  
//        setDetails(request, authRequest);  
//        // check validate code  
//        if (!isAllowEmptyValidateCode())  
//            checkValidateCode(request);  
//        // �����û��������ѯ  
//        USer adminUser = ServiceManager.getInstance()  
//                .getAdminUserService()  
//                .login(username, MD5.getMD5ofStr(password));  
//        session.setAttribute(GlobalParameter.HT_ADMIN_LOGIN_USER_SESSION_NAME,  
//                adminUser);  
//        return this.getAuthenticationManager().authenticate(authRequest);  
//    }  
//  
//    /** 
//     *  
//     * <li>�Ƚ�session�е���֤����û��������֤���Ƿ����</li> 
//     *  
//     */  
//    protected void checkValidateCode(HttpServletRequest request) {  
//        String sessionValidateCode = obtainSessionValidateCode(request);  
//        String validateCodeParameter = obtainValidateCodeParameter(request);  
//        if (StringUtils.isEmpty(validateCodeParameter)  
//                || !sessionValidateCode.equalsIgnoreCase(validateCodeParameter)) {  
//            throw new AuthenticationServiceException("��֤�����");  
//        }  
//    }  
//  
//    private String obtainValidateCodeParameter(HttpServletRequest request) {  
//        return request.getParameter(validateCodeParameter);  
//    }  
//  
//    protected String obtainSessionValidateCode(HttpServletRequest request) {  
//        Object obj = request.getSession()  
//                .getAttribute(sessionvalidateCodeField);  
//        return null == obj ? "" : obj.toString();  
//    }  
//  
//    public boolean isPostOnly() {  
//        return postOnly;  
//    }  
//  
//    @Override  
//    public void setPostOnly(boolean postOnly) {  
//        this.postOnly = postOnly;  
//    }  
//  
//    public String getValidateCodeName() {  
//        return sessionvalidateCodeField;  
//    }  
//  
//    public void setValidateCodeName(String validateCodeName) {  
//        this.sessionvalidateCodeField = validateCodeName;  
//    }  
//  
//    public boolean isAllowEmptyValidateCode() {  
//        return allowEmptyValidateCode;  
//    }  
//  
//    public void setAllowEmptyValidateCode(boolean allowEmptyValidateCode) {  
//        this.allowEmptyValidateCode = allowEmptyValidateCode;  
//    }  
//  
// }  