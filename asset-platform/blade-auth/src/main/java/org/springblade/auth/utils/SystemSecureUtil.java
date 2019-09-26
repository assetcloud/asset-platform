package org.springblade.auth.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.Objects;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import org.springblade.core.launch.constant.TokenConstant;
import org.springblade.core.secure.BladeUser;
import org.springblade.core.secure.exception.SecureException;
import org.springblade.core.secure.provider.IClientDetails;
import org.springblade.core.secure.provider.IClientDetailsService;
import org.springblade.core.tool.utils.Charsets;
import org.springblade.core.tool.utils.Func;
import org.springblade.core.tool.utils.SpringUtil;
import org.springblade.core.tool.utils.StringUtil;
import org.springblade.core.tool.utils.WebUtil;

public class SystemSecureUtil {
	private static final String BLADE_USER_REQUEST_ATTR = "_BLADE_USER_REQUEST_ATTR_";
	private static final String HEADER = "blade-auth";
	private static final String BEARER = "bearer";
	private static final String ACCOUNT = "account";
	private static final String USER_ID = "user_id";
	private static final String ROLE_ID = "role_id";
	private static final String USER_NAME = "user_name";
	private static final String ROLE_NAME = "role_name";
	private static final String TENANT_CODE = "tenant_code";
	private static final String CLIENT_ID = "client_id";
	private static final Integer AUTH_LENGTH;
	private static String BASE64_SECURITY;
	private static IClientDetailsService clientDetailsService;

	public SystemSecureUtil() {
	}

	public static BladeUser getUser() {
		HttpServletRequest request = WebUtil.getRequest();
		if (request == null) {
			return null;
		} else {
			Object bladeUser = request.getAttribute("_BLADE_USER_REQUEST_ATTR_");
			if (bladeUser == null) {
				bladeUser = getUser(request);
				if (bladeUser != null) {
					request.setAttribute("_BLADE_USER_REQUEST_ATTR_", bladeUser);
				}
			}

			return (BladeUser)bladeUser;
		}
	}

	public static BladeUser getUser(HttpServletRequest request) {
		Claims claims = getClaims(request);
		if (claims == null) {
			return null;
		} else {
			String clientId = Func.toStr(claims.get("client_id"));
			Integer userId = Func.toInt(claims.get("user_id"));
			String tenantCode = Func.toStr(claims.get("tenant_code"));
			String roleId = Func.toStr(claims.get("role_id"));
			String account = Func.toStr(claims.get("account"));
			String roleName = Func.toStr(claims.get("role_name"));
			String userName = Func.toStr(claims.get("user_name"));
			BladeUser bladeUser = new BladeUser();
			bladeUser.setClientId(clientId);
			bladeUser.setUserId(userId);
			bladeUser.setTenantCode(tenantCode);
			bladeUser.setAccount(account);
			bladeUser.setRoleId(roleId);
			bladeUser.setRoleName(roleName);
			bladeUser.setUserName(userName);
			return bladeUser;
		}
	}

	public static Integer getUserId() {
		BladeUser user = getUser();
		return null == user ? -1 : user.getUserId();
	}

	public static Integer getUserId(HttpServletRequest request) {
		BladeUser user = getUser(request);
		return null == user ? -1 : user.getUserId();
	}

	public static String getUserAccount() {
		BladeUser user = getUser();
		return null == user ? "" : user.getAccount();
	}

	public static String getUserAccount(HttpServletRequest request) {
		BladeUser user = getUser(request);
		return null == user ? "" : user.getAccount();
	}

	public static String getUserName() {
		BladeUser user = getUser();
		return null == user ? "" : user.getUserName();
	}

	public static String getUserName(HttpServletRequest request) {
		BladeUser user = getUser(request);
		return null == user ? "" : user.getUserName();
	}

	public static String getUserRole() {
		BladeUser user = getUser();
		return null == user ? "" : user.getRoleName();
	}

	public static String getUserRole(HttpServletRequest request) {
		BladeUser user = getUser(request);
		return null == user ? "" : user.getRoleName();
	}

	public static String getTenantCode() {
		BladeUser user = getUser();
		return null == user ? "" : user.getTenantCode();
	}

	public static String getTenantCode(HttpServletRequest request) {
		BladeUser user = getUser(request);
		return null == user ? "" : user.getTenantCode();
	}

	public static String getClientId() {
		BladeUser user = getUser();
		return null == user ? "" : user.getClientId();
	}

	public static String getClientId(HttpServletRequest request) {
		BladeUser user = getUser(request);
		return null == user ? "" : user.getClientId();
	}

	public static Claims getClaims(HttpServletRequest request) {
		String auth = request.getHeader("blade-auth");
		if (auth != null && auth.length() > AUTH_LENGTH) {
			String headStr = auth.substring(0, 6).toLowerCase();
			if (headStr.compareTo("bearer") == 0) {
				auth = auth.substring(7);
				return parseJWT(auth);
			}
		}

		return null;
	}

	public static String getHeader() {
		return getHeader((HttpServletRequest)Objects.requireNonNull(WebUtil.getRequest()));
	}

	public static String getHeader(HttpServletRequest request) {
		return request.getHeader("blade-auth");
	}

	public static Claims parseJWT(String jsonWebToken) {
		try {
			return (Claims)Jwts.parser().setSigningKey(Base64.getDecoder().decode(BASE64_SECURITY)).parseClaimsJws(jsonWebToken).getBody();
		} catch (Exception var2) {
			return null;
		}
	}

	public static String createJWT(Map<String, String> user, String audience, String issuer, boolean isExpire) {
		String[] tokens = extractAndDecodeHeader();

		assert tokens.length == 2;

		String clientId = tokens[0];
		String clientSecret = tokens[1];
		if (!validateClient(clientId, clientSecret)) {
			throw new SecureException("客户端认证失败!");
		} else {
			SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
			long nowMillis = System.currentTimeMillis();
			Date now = new Date(nowMillis);
			byte[] apiKeySecretBytes = Base64.getDecoder().decode(BASE64_SECURITY);
			Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
			JwtBuilder builder = Jwts.builder().setHeaderParam("typ", "JsonWebToken").setIssuer(issuer).setAudience(audience).signWith(signatureAlgorithm, signingKey);
			user.forEach(builder::claim);
			builder.claim("client_id", clientId);
			if (isExpire) {
				long expMillis = nowMillis + getExpire();
				Date exp = new Date(expMillis);
				builder.setExpiration(exp).setNotBefore(now);
			}

			return builder.compact();
		}
	}

	public static long getExpire() {
		Calendar cal = Calendar.getInstance();
		cal.add(6, 1);
		cal.set(11, 3);
		cal.set(13, 0);
		cal.set(12, 0);
		cal.set(14, 0);
		return cal.getTimeInMillis() - System.currentTimeMillis();
	}

	public static int getExpireSeconds() {
		return (int)(getExpire() / 1000L);
	}

	public static String[] extractAndDecodeHeader() {
		try {
			String header = ((HttpServletRequest)Objects.requireNonNull(WebUtil.getRequest())).getHeader("Authorization");
			if (header != null && header.startsWith("Basic ")) {
				byte[] base64Token = header.substring(6).getBytes(Charsets.UTF_8_NAME);

				byte[] decoded;
				try {
					decoded = Base64.getDecoder().decode(base64Token);
				} catch (IllegalArgumentException var5) {
					throw new RuntimeException("Failed to decode basic authentication token");
				}

				String token = new String(decoded, Charsets.UTF_8_NAME);
				int index = token.indexOf(":");
				if (index == -1) {
					throw new RuntimeException("Invalid basic authentication token");
				} else {
					return new String[]{token.substring(0, index), token.substring(index + 1)};
				}
			} else {
				throw new SecureException("No client information in request header");
			}
		} catch (Throwable var6) {
			try {
				throw var6;
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public static String getClientIdFromHeader() {
		String[] tokens = extractAndDecodeHeader();

		assert tokens.length == 2;

		return tokens[0];
	}

	private static boolean validateClient(String clientId, String clientSecret) {
		IClientDetails clientDetails = clientDetailsService.loadClientByClientId(clientId);
		if (clientDetails == null) {
			return false;
		} else {
			return StringUtil.equals(clientId, clientDetails.getClientId()) && StringUtil.equals(clientSecret, clientDetails.getClientSecret());
		}
	}

	static {
		AUTH_LENGTH = TokenConstant.AUTH_LENGTH;
		BASE64_SECURITY = Base64.getEncoder().encodeToString("Blade".getBytes(Charsets.UTF_8));
		clientDetailsService = (IClientDetailsService)SpringUtil.getBean(IClientDetailsService.class);
	}
}
