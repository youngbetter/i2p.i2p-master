/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: JspC/ApacheTomcat9
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package net.i2p.router.web.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import net.i2p.router.web.helpers.SummaryHelper;

public final class i2ptunnelmgr_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(5);
    _jspx_dependants.put("/xhr1.jsi", Long.valueOf(1607264743000L));
    _jspx_dependants.put("/summarynoframe.jsi", Long.valueOf(1607264743000L));
    _jspx_dependants.put("/summary.jsi", Long.valueOf(1607264743000L));
    _jspx_dependants.put("/summaryajax.jsi", Long.valueOf(1607264743000L));
    _jspx_dependants.put("/css.jsi", Long.valueOf(1607264743000L));
  }

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = new java.util.HashSet<>();
    _jspx_imports_classes.add("net.i2p.router.web.helpers.SummaryHelper");
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    if (!javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      final java.lang.String _jspx_method = request.getMethod();
      if ("OPTIONS".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        return;
      }
      if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSPs only permit GET, POST or HEAD. Jasper also permits OPTIONS");
        return;
      }
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      net.i2p.router.web.CSSHelper tester = null;
      tester = (net.i2p.router.web.CSSHelper) _jspx_page_context.getAttribute("tester", javax.servlet.jsp.PageContext.REQUEST_SCOPE);
      if (tester == null){
        tester = new net.i2p.router.web.CSSHelper();
        _jspx_page_context.setAttribute("tester", tester, javax.servlet.jsp.PageContext.REQUEST_SCOPE);
      }

   String i2pcontextId1 = null;
   try {
       i2pcontextId1 = (String) session.getAttribute("i2p.contextId");
   } catch (IllegalStateException ise) {}

      org.apache.jasper.runtime.JspRuntimeLibrary.handleSetProperty(_jspx_page_context.findAttribute("tester"), "contextId",
i2pcontextId1);

    // CSSHelper is also pulled in by css.jsi below...
    boolean testIFrame = tester.allowIFrame(request.getHeader("User-Agent"));
    if (!testIFrame) {
        response.setStatus(307);
        response.setHeader("Location", "/i2ptunnel/");
        // force commitment
        response.getOutputStream().close();
        return;
    } else {

      out.write("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">\n<html><head>\n");

   /*
    * This should be included inside <head>...</head>,
    * as it sets the stylesheet.
    *
    * This is included almost 30 times, so keep whitespace etc. to a minimum.
    */

   // http://www.crazysquirrel.com/computing/general/form-encoding.jspx
   if (request.getCharacterEncoding() == null)
       request.setCharacterEncoding("UTF-8");

   // Now that we use POST for most forms, these prevent the back button from working after a form submit
   // Just let the browser do its thing
   //response.setHeader("Pragma", "no-cache");
   //response.setHeader("Cache-Control","no-cache");
   //response.setDateHeader("Expires", 0);

   // the above will b0rk if the servlet engine has already flushed
   // the response prior to including this file, so it should be
   // near the top

   String i2pcontextId = request.getParameter("i2p.contextId");
   try {
       if (i2pcontextId != null) {
           session.setAttribute("i2p.contextId", i2pcontextId);
       } else {
           i2pcontextId = (String) session.getAttribute("i2p.contextId");
       }
   } catch (IllegalStateException ise) {}


      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      net.i2p.router.web.CSSHelper intl = null;
      intl = (net.i2p.router.web.CSSHelper) _jspx_page_context.getAttribute("intl", javax.servlet.jsp.PageContext.REQUEST_SCOPE);
      if (intl == null){
        intl = new net.i2p.router.web.CSSHelper();
        _jspx_page_context.setAttribute("intl", intl, javax.servlet.jsp.PageContext.REQUEST_SCOPE);
      }
      org.apache.jasper.runtime.JspRuntimeLibrary.handleSetProperty(_jspx_page_context.findAttribute("intl"), "contextId",
i2pcontextId);


   // used several times below
   String theUserAgent = request.getHeader("User-Agent");
   String theThemePath = intl.getTheme(theUserAgent);


      out.write("<link rel=\"icon\" href=\"");
      out.print(theThemePath);
      out.write("images/favicon.ico\">");

   response.setHeader("Accept-Ranges", "none");

   String cspNonce = Integer.toHexString(net.i2p.util.RandomSource.getInstance().nextInt());

   // clickjacking
   if (intl.shouldSendXFrame()) {
      response.setHeader("X-Frame-Options", "SAMEORIGIN");
      // unsafe-inline is a fallback for browsers not supporting nonce
      // https://developer.mozilla.org/en-US/docs/Web/HTTP/Headers/Content-Security-Policy/script-src
      response.setHeader("Content-Security-Policy", "default-src 'self'; style-src 'self' 'unsafe-inline'; script-src 'self' 'unsafe-inline' 'nonce-" + cspNonce + "'; form-action 'self'; frame-ancestors 'self'; object-src 'none'; media-src 'none'");
      response.setHeader("X-XSS-Protection", "1; mode=block");
      response.setHeader("X-Content-Type-Options", "nosniff");
   }
   // https://www.w3.org/TR/referrer-policy/
   // https://developer.mozilla.org/en-US/docs/Web/HTTP/Headers/Referrer-Policy
   // As of Chrome 56, Firefox 50, Opera 43. "same-origin" not widely supported.
   response.setHeader("Referrer-Policy", "no-referrer");

   String conNonceParam = request.getParameter("consoleNonce");
   if (net.i2p.router.web.CSSHelper.getNonce().equals(conNonceParam)) {
       intl.setLang(request.getParameter("lang"));
       intl.setNews(request.getParameter("news"));
   }

      out.write("<link href=\"");
      out.print(theThemePath);
      out.write("console.css?");
      out.print(net.i2p.CoreVersion.VERSION);
      out.write("\" rel=\"stylesheet\" type=\"text/css\">\n");

   if (intl.getLang().equals("zh")) {
       // make the fonts bigger for chinese

      out.write("<link href=\"");
      out.print(theThemePath);
      out.write("console_big.css?");
      out.print(net.i2p.CoreVersion.VERSION);
      out.write("\" rel=\"stylesheet\" type=\"text/css\">\n");

   } else if (intl.getLang().equals("ar")) {
       // Use RTL theme for Arabic

      out.write("<link href=\"");
      out.print(theThemePath);
      out.write("console_ar.css?");
      out.print(net.i2p.CoreVersion.VERSION);
      out.write("\" rel=\"stylesheet\" type=\"text/css\">\n");

   }
   if (!intl.allowIFrame(theUserAgent)) {

      out.write("<meta name=\"viewport\" content=\"width=device-width; initial-scale=1.0; maximum-scale=1.0; user-scalable=0;\" />\n<link href=\"");
      out.print(theThemePath);
      out.write("mobile.css?");
      out.print(net.i2p.CoreVersion.VERSION);
      out.write("\" rel=\"stylesheet\" type=\"text/css\">\n");

   }

      out.print(intl.title("Hidden Services Manager"));
      out.write("<script src=\"/js/iframed.js?");
      out.print(net.i2p.CoreVersion.VERSION);
      out.write("\" type=\"text/javascript\"></script>\n");

    if (!intl.getDisableRefresh()) {

      out.write("<script src=\"/js/ajax.js?");
      out.print(net.i2p.CoreVersion.VERSION);
      out.write("\" type=\"text/javascript\"></script>\n<script nonce=\"");
      out.print(cspNonce);
      out.write("\" type=\"text/javascript\">\n/* @license http://creativecommons.org/publicdomain/zero/1.0/legalcode CC0-1.0 */\n\n  var failMessage = \"<hr><b>");
      out.print(intl._t("Router is down"));
      out.write("<\\/b>\";\n  function requestAjax1() { ajax(\"/xhr1.jsp?requestURI=");
      out.print(request.getRequestURI());
      out.write("\", \"xhr\", ");
      out.print(intl.getRefresh());
      out.write("000); }\n  function initAjax() { setTimeout(requestAjax1, ");
      out.print(intl.getRefresh());
      out.write("000);  }\n  initAjax();\n\n/* @license-end */\n</script>");

    }

      out.write("<script nonce=\"");
      out.print(cspNonce);
      out.write("\" type=\"text/javascript\">\n/* @license http://creativecommons.org/publicdomain/zero/1.0/legalcode CC0-1.0 */\n\n  function injectClassSpecific(f) {\n      var doc = 'contentDocument' in f? f.contentDocument : f.contentWindow.document;\n      if (doc.getElementsByClassName == undefined) {\n      doc.getElementsByClassName = function(className)\n      {\n          var hasClassName = new RegExp(\"(?:^|\\\\s)\" + className + \"(?:$|\\\\s)\");\n          var allElements = document.getElementsByTagName(\"*\");\n          var results = [];\n\n          var element;\n          for (var i = 0; (element = allElements[i]) != null; i++) {\n              var elementClass = element.className;\n              if (elementClass && elementClass.indexOf(className) != -1 && hasClassName.test(elementClass))\n                  results.push(element);\n          }\n\n          return results;\n      }\n      }\n      doc.getElementsByClassName('panel')[0].className += ' iframed';\n  }\n  function setupFrame() {\n      f = document.getElementById(\"i2ptunnelframe\");\n      f.addEventListener(\"load\", function() {\n");
      out.write("          injectClass(f);\n          injectClassSpecific(f);\n          resizeFrame(f);\n      }, true);\n  }\n\n/* @license-end */\n</script>\n</head><body>\n\n");
      net.i2p.router.web.NewsHelper newshelper = null;
      newshelper = (net.i2p.router.web.NewsHelper) _jspx_page_context.getAttribute("newshelper", javax.servlet.jsp.PageContext.REQUEST_SCOPE);
      if (newshelper == null){
        newshelper = new net.i2p.router.web.NewsHelper();
        _jspx_page_context.setAttribute("newshelper", newshelper, javax.servlet.jsp.PageContext.REQUEST_SCOPE);
      }
      org.apache.jasper.runtime.JspRuntimeLibrary.handleSetProperty(_jspx_page_context.findAttribute("newshelper"), "contextId",
i2pcontextId);

    java.io.File newspath = new java.io.File(net.i2p.I2PAppContext.getGlobalContext().getRouterDir(), "docs/news.xml");

      org.apache.jasper.runtime.JspRuntimeLibrary.handleSetProperty(_jspx_page_context.findAttribute("newshelper"), "page",
newspath.getAbsolutePath());
      org.apache.jasper.runtime.JspRuntimeLibrary.introspecthelper(_jspx_page_context.findAttribute("newshelper"), "maxLines", "300", null, null, false);
      out.write("<div class=\"routersummaryouter\">\n");

    // The refresh delay, 0 to disable
    String d = "0";
    boolean allowIFrame = intl.allowIFrame(request.getHeader("User-Agent"));
    if (allowIFrame) {
        // skip the iframe if refresh disabled
        d = request.getParameter("refresh");
        String newDelay = "";
        if (d == null || "".equals(d))
            d = intl.getRefresh();
        else {
            long delay;
            try {
                delay = Long.parseLong(d);
            } catch (NumberFormatException nfe) {
                delay = 60;
            }
            // pass the new delay parameter to the iframe
            newDelay = "?refresh=" + delay;
            // update disable boolean
            intl.setDisableRefresh(d);
        }
        if (false && !intl.getDisableRefresh())
            out.print("<noscript><iframe src=\"/summaryframe.jsp" + newDelay + "\" height=\"1500\" width=\"200\" scrolling=\"auto\" frameborder=\"0\" title=\"sidepanel\"></noscript>\n");
    }

      out.write("<div class=\"routersummary\">\n");

    if (allowIFrame) {
        // Display the whole summary bar


/*
 * TODO - the bar would render more cleanly if we specified the img height and width here,
 * but unfortunately the images in the different themes are different sizes.
 * They range in height from 37 to 43 px. But there's a -2 bottom margin...
 * So put it in a div.
 */

      out.write("<div>\n   <a href=\"/\" target=\"_top\">\n    <img src=\"");
      out.print(intl.getTheme(request.getHeader("User-Agent")));
      out.write("images/i2plogo.png\" alt=\"");
      out.print(intl._t("I2P Router Console"));
      out.write("\" title=\"");
      out.print(intl._t("I2P Router Console"));
      out.write("\">\n   </a>\n  </div>\n  <div id=\"xhr\">\n<!-- for non-script -->\n");

/*
 * Note:
 * This is included on every refresh, so keep whitespace etc. to a minimum.
 */

      net.i2p.router.web.helpers.SummaryHelper helper = null;
      helper = (net.i2p.router.web.helpers.SummaryHelper) _jspx_page_context.getAttribute("helper", javax.servlet.jsp.PageContext.REQUEST_SCOPE);
      if (helper == null){
        helper = new net.i2p.router.web.helpers.SummaryHelper();
        _jspx_page_context.setAttribute("helper", helper, javax.servlet.jsp.PageContext.REQUEST_SCOPE);
      }
      org.apache.jasper.runtime.JspRuntimeLibrary.handleSetProperty(_jspx_page_context.findAttribute("helper"), "contextId",
i2pcontextId);
      org.apache.jasper.runtime.JspRuntimeLibrary.handleSetProperty(_jspx_page_context.findAttribute("helper"), "action",
request.getParameter("action"));
      org.apache.jasper.runtime.JspRuntimeLibrary.handleSetProperty(_jspx_page_context.findAttribute("helper"), "updateNonce",
request.getParameter("updateNonce"));
      org.apache.jasper.runtime.JspRuntimeLibrary.handleSetProperty(_jspx_page_context.findAttribute("helper"), "consoleNonce",
request.getParameter("consoleNonce"));

    String reqURI = request.getRequestURI();
    if (reqURI.contains("/xhr1"))
        reqURI = request.getParameter("requestURI");
    helper.setRequestURI(reqURI);
    helper.storeWriter(out);
    helper.storeNewsHelper(newshelper);
/*
 * The following is required for the reseed button to work, although we probably
 * only need the reseedNonce property.
 */

      net.i2p.router.web.helpers.ReseedHandler reseed = null;
      reseed = (net.i2p.router.web.helpers.ReseedHandler) _jspx_page_context.getAttribute("reseed", javax.servlet.jsp.PageContext.REQUEST_SCOPE);
      if (reseed == null){
        reseed = new net.i2p.router.web.helpers.ReseedHandler();
        _jspx_page_context.setAttribute("reseed", reseed, javax.servlet.jsp.PageContext.REQUEST_SCOPE);
      }
      org.apache.jasper.runtime.JspRuntimeLibrary.introspect(_jspx_page_context.findAttribute("reseed"), request);

/*
 * The following is required for the update buttons to work, although we probably
 * only need the updateNonce property.
 */

      net.i2p.router.web.UpdateHandler update = null;
      update = (net.i2p.router.web.UpdateHandler) _jspx_page_context.getAttribute("update", javax.servlet.jsp.PageContext.REQUEST_SCOPE);
      if (update == null){
        update = new net.i2p.router.web.UpdateHandler();
        _jspx_page_context.setAttribute("update", update, javax.servlet.jsp.PageContext.REQUEST_SCOPE);
      }
      org.apache.jasper.runtime.JspRuntimeLibrary.introspect(_jspx_page_context.findAttribute("update"), request);
      org.apache.jasper.runtime.JspRuntimeLibrary.handleSetProperty(_jspx_page_context.findAttribute("update"), "contextId",
i2pcontextId);

    // moved to java for ease of translation
    helper.renderSummaryBar();

      out.write("</div>\n");

    } else {
        // Text browsers don't render the two divs side-by-side, so just provide a link
        out.print("<a href=\"/summaryframe\">");
        out.print(intl._t("Sidebar"));
        out.print("</a>");
    }

    // d and allowIFrame defined above
    if (false && !intl.getDisableRefresh()) {
        out.print("</div><noscript></iframe></noscript>\n");
    } else if (false && allowIFrame) {
        // since we don't have an iframe this will reload the base page, and
        // the new delay will be passed to the iframe above
        out.print("<noscript><div class=\"refresh\"><form action=\"" + request.getRequestURI() + "\" method=\"POST\">\n" +
                  "<b>");
        // We have intl defined when this is included, but not when compiled standalone.
        out.print(intl._t("Refresh (s)"));
        out.print(":</b> <input size=\"3\" type=\"text\" name=\"refresh\" value=\"60\" >\n" +
                  "<button type=\"submit\" value=\"Enable\" >");
        // ditto
        out.print(intl._t("Enable"));
        out.print("</button>\n" +
                  "</form></div></noscript></div>\n");
    } else {
        out.print("</div>\n");
    }

      out.write("</div>\n");
      out.write("<h1>");
      out.print(intl._t("Hidden Services Manager"));
      out.write("<span class=\"newtab\"><a href=\"/i2ptunnel/\" target=\"_blank\" title=\"");
      out.print(intl._t("Open in new tab"));
      out.write("\"><img src=\"");
      out.print(intl.getTheme(request.getHeader("User-Agent")));
      out.write("images/newtab.png\" /></a></span></h1>\n<div class=\"main\" id=\"tunnelmgr\">\n<iframe src=\"/i2ptunnel/\" width=\"100%\" height=\"100%\" frameborder=\"0\" border=\"0\" name=\"i2ptunnelframe\" id=\"i2ptunnelframe\" allowtransparency=\"true\">\n");
      out.print(intl._t("Your browser does not support iFrames."));
      out.write("\n&nbsp;<a href=\"/i2ptunnel/\">");
      out.print(intl._t("Click here to continue."));
      out.write("</a>\n</iframe>\n</div></body></html>\n");

    }

    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
