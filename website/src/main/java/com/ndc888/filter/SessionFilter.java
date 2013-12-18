package com.ndc888.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.MessageFormat;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import com.ndc888.constants.CommonConstants;
import com.ndc888.utils.UrlUtils;
import com.ndc888.vo.NdcUser;

/**
 * 
 * @title
 * @description
 * @usage
 * @company 上海微汇信息技术有限公司
 * @author TQSUMMER
 * @create 2012-7-11 下午1:08:44
 * 
 */
public class SessionFilter implements Filter {
	private static final Logger logger = LoggerFactory.getLogger(SessionFilter.class);

	/**
	 * sevelet Filter Config.
	 */
	private FilterConfig config;

	private UrlPatternChecker urlPatternChecker;

	/**
	 * 设置完FilterConfig之后的逻辑.
	 */
	public void afterSetFilterConfig() {
		String[] unlimited = config.getInitParameter("unlimited").split(",");
        String login = config.getInitParameter("login");
		urlPatternChecker = new UrlPatternChecker();
		urlPatternChecker.setUnlimited(unlimited);
        urlPatternChecker.setLogin(login);

	}

	/**
	 * 进行request filter.
	 * 
	 * @param request
	 *            http request
	 * @param response
	 *            http response
	 * @param chain
	 *            filer chain
	 * @throws java.io.IOException
	 *             io exception
	 * @throws javax.servlet.ServletException
	 *             servlet exception
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
			ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		HttpServletResponseWrapper wrapper = new HttpServletResponseWrapper(httpResponse) {
			public String encodeRedirectUrl(String url) {
				return url;
			}

			public String encodeRedirectURL(String url) {
				return url;
			}

			public String encodeUrl(String url) {
				return url;
			}

			public String encodeURL(String url) {
				return url;
			}
		};
		String uri = httpRequest.getRequestURI();
		String referer = httpRequest.getHeader("Referer");
		if (logger.isDebugEnabled()) {
			logger.debug(MessageFormat.format("uri:{0},referer:{1}", uri, referer));
		}

		FilterProcessor processor = new FilterProcessor(httpRequest, urlPatternChecker);
		processor.process();

		boolean isFilter = processor.isFilter();
		boolean isAjax = processor.isAjax();
		String redirect = processor.getRedirect();

		if (isFilter) {
			chain.doFilter(request, wrapper);
			return;
		}

		if (isAjax) {
			httpResponse.addHeader("Ajax-Response-Status", "session-timeout");
			httpResponse.addHeader("Ajax-Response-Redirect", redirect);
			return;
		} else {
			wrapper.sendRedirect(redirect);
			return;
		}

	}

	/**
	 * 初始化.
	 * 
	 * @param filterConfig
	 *            servlet filter config
	 * @throws javax.servlet.ServletException
	 *             sevlet exception when error
	 */
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.config = filterConfig;
		this.afterSetFilterConfig();
	}

	/**
	 * destroy.
	 */
	@Override
	public void destroy() {

	}

	private static class UrlPatternChecker {
		private static PathMatcher pathMatcher = new AntPathMatcher();
		private String[] unlimited;
        private String login;

        public void setLogin(String login) {
            this.login = login;
        }

        public boolean isIndex(String uri) {
            return pathMatcher.match("/", uri);
        }

        public String getLogin() {
            return login;
        }

        /**
		 * @param unlimited
		 *            the unlimited to set
		 */
		public void setUnlimited(String[] unlimited) {
			this.unlimited = unlimited;
		}
		public boolean isUnlimited(String uri) {
			if (unlimited == null) {
				return false;
			}
			for (String unlimit : unlimited) {
				if (pathMatcher.match(unlimit, uri)) {
					return true;
				}
			}
			return false;
		}

        public boolean isLogin(String uri) {
            return pathMatcher.match(login, uri);
        }

	}

	private static class FilterProcessor {
		private UrlPatternChecker urlPatternChecker;
		private HttpServletRequest request;
		private String redirect;
		private boolean isFilter;
		private boolean isAjax;

		public FilterProcessor(HttpServletRequest request, UrlPatternChecker urlPatternChecker)
				throws UnsupportedEncodingException {
			this.request = request;
			this.urlPatternChecker = urlPatternChecker;
		}

		public void process() throws UnsupportedEncodingException {
			NdcUser loginUser = (NdcUser) request.getSession().getAttribute(
					CommonConstants.SESSION_ATTR_NAME_CURRENT_USER);
			String uri = request.getRequestURI().replaceFirst(request.getContextPath(), "");
            String login_url=     request.getContextPath() + urlPatternChecker.getLogin();


			// 判断是否是首页、认证登录、非限制的URL_PATTERN
			if (urlPatternChecker.isIndex(uri) || urlPatternChecker.isUnlimited(uri)) {
				isFilter = true;
				return;
			}


			// 判断未登录用户
			if (loginUser == null) {
				String ajaxRequest = request.getHeader("x-Requested-With");
				if (ajaxRequest != null && ajaxRequest.equals("XMLHttpRequest")) {
					isAjax = true;
					redirect = UrlUtils.getActionUrl(login_url, getReferEncodeUrl());
					return;
				} else {
					redirect = UrlUtils.getActionUrl(login_url, getEncodeUrl());
					return;
				}
			}

			isFilter = true;
			return;
		}

		private String getReferEncodeUrl() throws UnsupportedEncodingException {
			String referer = request.getHeader("Referer");
			String refer_encode_url = null;
			if (referer != null) {
				refer_encode_url = URLEncoder.encode(referer, request.getCharacterEncoding());
			}
			return refer_encode_url;
		}

		private String getGetEncodeUrl() throws UnsupportedEncodingException {
			String get_encode_url = null;
			if ("GET".equals(request.getMethod())) {
				String queryString = request.getQueryString();
				get_encode_url = URLEncoder.encode(request.getRequestURL().toString()
						+ (queryString != null ? "?" + queryString : ""), request.getCharacterEncoding());
			}
			return get_encode_url;
		}

		private String getEncodeUrl() throws UnsupportedEncodingException {
			String get_encode_url = getGetEncodeUrl();
			String refer_encode_url = getReferEncodeUrl();
			String encode_url = null;
			if (!StringUtils.isEmpty(get_encode_url)) {
				encode_url = get_encode_url;
			} else if (!StringUtils.isEmpty(get_encode_url)) {
				encode_url = refer_encode_url;
			}
			return encode_url;
		}

		/**
		 * @return the redirect
		 */
		public String getRedirect() {
			return redirect;
		}

		/**
		 * @return the isFilter
		 */
		public boolean isFilter() {
			return isFilter;
		}

		/**
		 * @return the isAjax
		 */
		public boolean isAjax() {
			return isAjax;
		}

	}

}