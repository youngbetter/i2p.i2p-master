/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: JspC/ApacheTomcat9
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package net.i2p.i2ptunnel.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import net.i2p.i2ptunnel.web.IndexBean;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(1);
    _jspx_dependants.put("/headers.jsi", Long.valueOf(1607264743000L));
  }

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = new java.util.HashSet<>();
    _jspx_imports_classes.add("net.i2p.i2ptunnel.web.IndexBean");
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


    // NOTE: Do the header carefully so there is no whitespace before the <?xml... line

    // http://www.crazysquirrel.com/computing/general/form-encoding.jspx
    if (request.getCharacterEncoding() == null)
        request.setCharacterEncoding("UTF-8");

    response.setHeader("X-Frame-Options", "SAMEORIGIN");
    response.setHeader("Content-Security-Policy", "default-src 'self'; style-src 'self' 'unsafe-inline'; script-src 'none'; frame-ancestors 'self'; object-src 'none'; media-src 'none'");
    response.setHeader("X-XSS-Protection", "1; mode=block");
    response.setHeader("X-Content-Type-Options", "nosniff");
    response.setHeader("Referrer-Policy", "no-referrer");
    response.setHeader("Accept-Ranges", "none");


      out.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">\r\n");
      net.i2p.i2ptunnel.web.IndexBean indexBean = null;
      indexBean = (net.i2p.i2ptunnel.web.IndexBean) _jspx_page_context.getAttribute("indexBean", javax.servlet.jsp.PageContext.REQUEST_SCOPE);
      if (indexBean == null){
        indexBean = new net.i2p.i2ptunnel.web.IndexBean();
        _jspx_page_context.setAttribute("indexBean", indexBean, javax.servlet.jsp.PageContext.REQUEST_SCOPE);
      }
      org.apache.jasper.runtime.JspRuntimeLibrary.introspecthelper(_jspx_page_context.findAttribute("indexBean"), "tunnel", request.getParameter("tunnel"), request, "tunnel", false);
      org.apache.jasper.runtime.JspRuntimeLibrary.introspect(_jspx_page_context.findAttribute("indexBean"), request);
      net.i2p.i2ptunnel.ui.Messages intl = null;
      intl = (net.i2p.i2ptunnel.ui.Messages) _jspx_page_context.getAttribute("intl", javax.servlet.jsp.PageContext.REQUEST_SCOPE);
      if (intl == null){
        intl = new net.i2p.i2ptunnel.ui.Messages();
        _jspx_page_context.setAttribute("intl", intl, javax.servlet.jsp.PageContext.REQUEST_SCOPE);
      }
      out.write("<html xmlns=\"http://www.w3.org/1999/xhtml\" xml:lang=\"en\" lang=\"en\">\r\n<head>\r\n    <title>");
      out.print(intl._t("Hidden Services Manager"));
      out.write("</title>\r\n    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\r\n    <link href=\"/themes/console/images/favicon.ico\" type=\"image/x-icon\" rel=\"shortcut icon\" />\r\n    <link href=\"");
      out.print(indexBean.getTheme());
      out.write("i2ptunnel.css?");
      out.print(net.i2p.CoreVersion.VERSION);
      out.write("\" rel=\"stylesheet\" type=\"text/css\" /> \r\n</head><body id=\"tunnelListPage\">\r\n<div class=\"panel\" id=\"overview\"><h2>");
      out.print(intl._t("Hidden Services Manager"));
      out.write("</h2><p>\r\n");
      out.print(intl._t("These are the local services provided by your router."));
      out.write("\r\n&nbsp;\r\n");
      out.print(intl._t("By default, most of your client services (email, HTTP proxy, IRC) will share the same set of tunnels and be listed as \"Shared Clients\" and \"Shared Clients(DSA)\"."));
      out.write("</p></div>\r\n");

  boolean isInitialized = indexBean.isInitialized();
  String nextNonce = isInitialized ? net.i2p.i2ptunnel.web.IndexBean.getNextNonce() : null;

  // not synced, oh well
  int lastID = indexBean.getLastMessageID();
  String msgs = indexBean.getMessages();
  if (msgs.length() > 0) {

      out.write("<div class=\"panel\" id=\"messages\">\r\n    <h2>");
      out.print(intl._t("Status Messages"));
      out.write("</h2>\r\n    <table id=\"statusMessagesTable\">\r\n        <tr>\r\n            <td id=\"tunnelMessages\">\r\n        <textarea id=\"statusMessages\" rows=\"4\" cols=\"60\" readonly=\"readonly\">");
      out.print(msgs);
      out.write("</textarea>\r\n            </td>\r\n        </tr><tr>\r\n            <td class=\"buttons\">\r\n                <a class=\"control\" href=\"list\">");
      out.print(intl._t("Refresh"));
      out.write("</a>\r\n");

  if (isInitialized) {

      out.write("<a class=\"control\" href=\"list?action=Clear&amp;msgid=");
      out.print(lastID);
      out.write("&amp;nonce=");
      out.print(nextNonce);
      out.write('"');
      out.write('>');
      out.print(intl._t("Clear"));
      out.write("</a>\r\n");

  }  // isInitialized

      out.write("</td>\r\n        </tr>\r\n    </table>\r\n</div>\r\n");

  }  // !msgs.isEmpty()
  if (isInitialized) {

      out.write("<div class=\"panel\" id=\"globalTunnelControl\">\r\n    <h2>");
      out.print(intl._t("Global Tunnel Control"));
      out.write("</h2>\r\n    <table>\r\n        <tr>\r\n            <td class=\"buttons\">\r\n                <a class=\"control\" href=\"wizard\">");
      out.print(intl._t("Tunnel Wizard"));
      out.write("</a>\r\n                <a class=\"control\" href=\"list?nonce=");
      out.print(nextNonce);
      out.write("&amp;action=Stop%20all\">");
      out.print(intl._t("Stop All"));
      out.write("</a>\r\n                <a class=\"control\" href=\"list?nonce=");
      out.print(nextNonce);
      out.write("&amp;action=Start%20all\">");
      out.print(intl._t("Start All"));
      out.write("</a>\r\n                <a class=\"control\" href=\"list?nonce=");
      out.print(nextNonce);
      out.write("&amp;action=Restart%20all\">");
      out.print(intl._t("Restart All"));
      out.write("</a>\r\n");
      out.write("</td>\r\n        </tr>\r\n    </table>\r\n</div>\r\n<div class=\"panel\" id=\"servers\">\r\n    <h2>");
      out.print(intl._t("I2P Hidden Services"));
      out.write("</h2>\r\n<table id=\"serverTunnels\">\r\n    <tr>\r\n        <th class=\"tunnelName\">");
      out.print(intl._t("Name"));
      out.write("</th>\r\n        <th class=\"tunnelType\">");
      out.print(intl._t("Type"));
      out.write("</th>\r\n        <th class=\"tunnelLocation\">");
      out.print(intl._t("Points at"));
      out.write("</th>\r\n        <th class=\"tunnelPreview\">");
      out.print(intl._t("Preview"));
      out.write("</th>\r\n        <th class=\"tunnelStatus\">");
      out.print(intl._t("Status"));
      out.write("</th>\r\n        <th class=\"tunnelControl\">");
      out.print(intl._t("Control"));
      out.write("</th>\r\n    </tr>\r\n");

        for (int curServer = 0; curServer < indexBean.getTunnelCount(); curServer++) {
            if (indexBean.isClient(curServer)) continue;

      out.write("<tr class=\"tunnelProperties\">\r\n        <td class=\"tunnelName\">\r\n            <a href=\"edit?tunnel=");
      out.print(curServer);
      out.write("\" title=\"");
      out.print(intl._t("Edit Server Tunnel Settings for"));
      out.write("&nbsp;");
      out.print(indexBean.getTunnelName(curServer));
      out.write('"');
      out.write('>');
      out.print(indexBean.getTunnelName(curServer));
      out.write("</a>\r\n        </td><td class=\"tunnelType\">");
      out.print(indexBean.getTunnelType(curServer));
      out.write("</td><td class=\"tunnelLocation\">\r\n");

            if (indexBean.isServerTargetLinkValid(curServer)) {
                if (indexBean.isSSLEnabled(curServer)) { 
      out.write("<a href=\"https://");
      out.print(indexBean.getServerTarget(curServer));
      out.write("/\" title=\"");
      out.print(intl._t("Test HTTPS server, bypassing I2P"));
      out.write("\" target=\"_top\">");
      out.print(indexBean.getServerTarget(curServer));
      out.write(" SSL</a>\r\n");
              } else { 
      out.write("<a href=\"http://");
      out.print(indexBean.getServerTarget(curServer));
      out.write("/\" title=\"");
      out.print(intl._t("Test HTTP server, bypassing I2P"));
      out.write("\" target=\"_top\">");
      out.print(indexBean.getServerTarget(curServer));
      out.write("</a>\r\n");

                }
            } else {
          
      out.print(indexBean.getServerTarget(curServer));

                if (indexBean.isSSLEnabled(curServer)) { 
      out.write("\r\n                    SSL\r\n");

                }
            }

      out.write("</td><td class=\"tunnelPreview\">\r\n");

            if (("httpserver".equals(indexBean.getInternalType(curServer)) || ("httpbidirserver".equals(indexBean.getInternalType(curServer)))) && indexBean.getTunnelStatus(curServer) == IndexBean.RUNNING) {

      out.write("<a class=\"control\" title=\"");
      out.print(intl._t("Test HTTP server through I2P"));
      out.write("\" href=\"http://");
      out.print(indexBean.getDestHashBase32(curServer));
      out.write("\" target=\"_top\">");
      out.print(intl._t("Preview"));
      out.write("</a>\r\n");

            } else {
          
      out.print(intl._t("No Preview"));

            }

      out.write("</td><td class=\"tunnelStatus\">\r\n");

            switch (indexBean.getTunnelStatus(curServer)) {
                case IndexBean.STARTING:
          
      out.write("<div class=\"statusStarting text\" title=\"");
      out.print(intl._t("Starting..."));
      out.write('"');
      out.write('>');
      out.print(intl._t("Starting..."));
      out.write("</div>\r\n        </td><td class=\"tunnelControl\">\r\n            <a class=\"control\" title=\"");
      out.print(intl._t("Stop this Tunnel"));
      out.write("\" href=\"list?nonce=");
      out.print(nextNonce);
      out.write("&amp;action=stop&amp;tunnel=");
      out.print(curServer);
      out.write('"');
      out.write('>');
      out.print(intl._t("Stop"));
      out.write("</a>\r\n");

                break;
                case IndexBean.RUNNING:
          
      out.write("<div class=\"statusRunning text\" title=\"");
      out.print(intl._t("Running"));
      out.write('"');
      out.write('>');
      out.print(intl._t("Running"));
      out.write("</div>\r\n        </td><td class=\"tunnelControl\">\r\n            <a class=\"control\" title=\"");
      out.print(intl._t("Stop this Tunnel"));
      out.write("\" href=\"list?nonce=");
      out.print(nextNonce);
      out.write("&amp;action=stop&amp;tunnel=");
      out.print(curServer);
      out.write('"');
      out.write('>');
      out.print(intl._t("Stop"));
      out.write("</a>\r\n");

                break;
                case IndexBean.NOT_RUNNING:
          
      out.write("<div class=\"statusNotRunning text\" title=\"");
      out.print(intl._t("Stopped"));
      out.write('"');
      out.write('>');
      out.print(intl._t("Stopped"));
      out.write("</div>\r\n        </td><td class=\"tunnelControl\">\r\n            <a class=\"control\" title=\"");
      out.print(intl._t("Start this Tunnel"));
      out.write("\" href=\"list?nonce=");
      out.print(nextNonce);
      out.write("&amp;action=start&amp;tunnel=");
      out.print(curServer);
      out.write('"');
      out.write('>');
      out.print(intl._t("Start"));
      out.write("</a>\r\n");

                break;
            }

      out.write("</td>\r\n    </tr><tr>\r\n        <td class=\"tunnelDestination\" colspan=\"6\">\r\n            <span class=\"tunnelDestinationLabel\">\r\n");

                String name = indexBean.getSpoofedHost(curServer);
                    if (name == null || name.equals("")) {
                        name = indexBean.getTunnelName(curServer);
                        out.write("<b>");
                        out.write(intl._t("Destination"));
                        out.write(":</b></span> ");
                        out.write(indexBean.getDestHashBase32(curServer));
                   } else {
                       out.write("<b>");
                       out.write(intl._t("Hostname"));
                       out.write(":</b></span> ");
                       out.write(name);
                   }

      out.write("</td>\r\n    </tr>\r\n");

            String encName = indexBean.getEncryptedBase32(curServer);
            if (encName != null && encName.length() > 0) {

      out.write("<tr>\r\n        <td class=\"tunnelDestination\" colspan=\"6\">\r\n            <span class=\"tunnelDestinationLabel\"><b>");
      out.print(intl._t("Encrypted"));
      out.write(":</b></span>\r\n            ");
      out.print(encName);
      out.write("</td>\r\n    </tr>\r\n");

            } // encName

      out.write("<tr>\r\n        <td class=\"tunnelDescription\" colspan=\"6\">\r\n");

            String descr = indexBean.getTunnelDescription(curServer);
            if (descr != null && descr.length() > 0) {

      out.write("<span class=\"tunnelDestinationLabel\"><b>");
      out.print(intl._t("Description"));
      out.write(":</b></span>\r\n            ");
      out.print(descr);

            } else {
                // needed to make the spacing look right
                
      out.write("&nbsp;");

            } // descr

      out.write("</td>\r\n    </tr>\r\n");

        } // for loop

      out.write("<tr>\r\n        <td class=\"newTunnel\" colspan=\"6\">\r\n           <form id=\"addNewServerTunnelForm\" action=\"edit\">\r\n               <b>");
      out.print(intl._t("New hidden service"));
      out.write(":</b>&nbsp;\r\n                    <select name=\"type\">\r\n                        <option value=\"httpserver\">HTTP</option>\r\n                        <option value=\"server\">");
      out.print(intl._t("Standard"));
      out.write("</option>\r\n                        <option value=\"httpbidirserver\">HTTP bidir</option>\r\n                        <option value=\"ircserver\">IRC</option>\r\n                        <option value=\"streamrserver\">Streamr</option>\r\n                    </select>\r\n                    <input class=\"control\" type=\"submit\" value=\"");
      out.print(intl._t("Create"));
      out.write("\" />\r\n            </form>\r\n        </td>\r\n    </tr>\r\n</table>\r\n</div>\r\n<div class=\"panel\" id=\"clients\">\r\n    <h2>");
      out.print(intl._t("I2P Client Tunnels"));
      out.write("</h2>\r\n<table id=\"clientTunnels\">\r\n    <tr>\r\n        <th class=\"tunnelName\">");
      out.print(intl._t("Name"));
      out.write("</th>\r\n        <th class=\"tunnelType\">");
      out.print(intl._t("Type"));
      out.write("</th>\r\n        <th class=\"tunnelInterface\">");
      out.print(intl._t("Interface"));
      out.write("</th>\r\n        <th class=\"tunnelPort\">");
      out.print(intl._t("Port"));
      out.write("</th>\r\n        <th class=\"tunnelStatus\">");
      out.print(intl._t("Status"));
      out.write("</th>\r\n        <th class=\"tunnelControl\">");
      out.print(intl._t("Control"));
      out.write("</th>\r\n    </tr>\r\n");

        for (int curClient = 0; curClient < indexBean.getTunnelCount(); curClient++) {
            if (!indexBean.isClient(curClient)) continue;

      out.write("<tr class=\"tunnelProperties\">\r\n        <td class=\"tunnelName\">\r\n            <a href=\"edit?tunnel=");
      out.print(curClient);
      out.write("\" title=\"");
      out.print(intl._t("Edit Tunnel Settings for"));
      out.write("&nbsp;");
      out.print(indexBean.getTunnelName(curClient));
      out.write('"');
      out.write('>');
      out.print(indexBean.getTunnelName(curClient));
      out.write("</a>\r\n        </td><td class=\"tunnelType\">");
      out.print(indexBean.getTunnelType(curClient));
      out.write("</td><td class=\"tunnelInterface\">\r\n");

               /* should only happen for streamr client */
               String cHost= indexBean.getClientInterface(curClient);
               if (cHost == null || "".equals(cHost)) {
                   out.write("<font color=\"red\">");
                   out.write(intl._t("Host not set"));
                   out.write("</font>");
               } else {
                   out.write(cHost);
               }

      out.write("</td><td class=\"tunnelPort\">\r\n");

               String cPort= indexBean.getClientPort2(curClient);
               out.write(cPort);
               if (indexBean.isSSLEnabled(curClient))
                   out.write(" SSL");

      out.write("</td><td class=\"tunnelStatus\">\r\n");

            switch (indexBean.getTunnelStatus(curClient)) {
                case IndexBean.STARTING:
          
      out.write("<div class=\"statusStarting text\" title=\"");
      out.print(intl._t("Starting..."));
      out.write('"');
      out.write('>');
      out.print(intl._t("Starting..."));
      out.write("</div>\r\n        </td><td class=\"tunnelControl\">\r\n            <a class=\"control\" title=\"");
      out.print(intl._t("Stop this Tunnel"));
      out.write("\" href=\"list?nonce=");
      out.print(nextNonce);
      out.write("&amp;action=stop&amp;tunnel=");
      out.print(curClient);
      out.write('"');
      out.write('>');
      out.print(intl._t("Stop"));
      out.write("</a>\r\n");

                break;
                case IndexBean.STANDBY:
          
      out.write("<div class=\"statusStarting text\" title=\"");
      out.print(intl._t("Standby"));
      out.write('"');
      out.write('>');
      out.print(intl._t("Standby"));
      out.write("</div>\r\n        </td><td class=\"tunnelControl\">\r\n            <a class=\"control\" title=\"Stop this Tunnel\" href=\"list?nonce=");
      out.print(nextNonce);
      out.write("&amp;action=stop&amp;tunnel=");
      out.print(curClient);
      out.write('"');
      out.write('>');
      out.print(intl._t("Stop"));
      out.write("</a>\r\n");

                break;
                case IndexBean.RUNNING:
          
      out.write("<div class=\"statusRunning text\" title=\"");
      out.print(intl._t("Running"));
      out.write('"');
      out.write('>');
      out.print(intl._t("Running"));
      out.write("</div>\r\n        </td><td class=\"tunnelControl\">\r\n            <a class=\"control\" title=\"Stop this Tunnel\" href=\"list?nonce=");
      out.print(nextNonce);
      out.write("&amp;action=stop&amp;tunnel=");
      out.print(curClient);
      out.write('"');
      out.write('>');
      out.print(intl._t("Stop"));
      out.write("</a>\r\n");

                break;
                case IndexBean.NOT_RUNNING:
          
      out.write("<div class=\"statusNotRunning text\" title=\"");
      out.print(intl._t("Stopped"));
      out.write('"');
      out.write('>');
      out.print(intl._t("Stopped"));
      out.write("</div>\r\n        </td><td class=\"tunnelControl\">\r\n            <a class=\"control\" title=\"");
      out.print(intl._t("Start this Tunnel"));
      out.write("\" href=\"list?nonce=");
      out.print(nextNonce);
      out.write("&amp;action=start&amp;tunnel=");
      out.print(curClient);
      out.write('"');
      out.write('>');
      out.print(intl._t("Start"));
      out.write("</a>\r\n");

                break;
            }

      out.write("</td>\r\n    </tr><tr>\r\n        <td class=\"tunnelDestination\" colspan=\"6\">\r\n            <span class=\"tunnelDestinationLabel\">\r\n");
             if ("httpclient".equals(indexBean.getInternalType(curClient)) || "connectclient".equals(indexBean.getInternalType(curClient)) ||
                   "sockstunnel".equals(indexBean.getInternalType(curClient)) || "socksirctunnel".equals(indexBean.getInternalType(curClient))) { 
      out.write('<');
      out.write('b');
      out.write('>');
      out.print(intl._t("Outproxy"));
      out.write(":</b>\r\n");
             } else { 
      out.write('<');
      out.write('b');
      out.write('>');
      out.print(intl._t("Destination"));
      out.write(":</b>\r\n ");
            } 
      out.write("</span>\r\n");

               if (indexBean.getIsUsingOutproxyPlugin(curClient)) {
                   
      out.print(intl._t("internal plugin"));

               } else {
                   String cdest = indexBean.getClientDestination(curClient);
                   if (cdest.length() > 70) { // Probably a B64 (a B32 is 60 chars) so truncate
                       
      out.print(cdest.substring(0, 45));
      out.write("&hellip;");
      out.print(cdest.substring(cdest.length() - 15, cdest.length()));

                   } else if (cdest.length() > 0) {
                       
      out.print(cdest);

                   } else {
                       
      out.write('<');
      out.write('i');
      out.write('>');
      out.print(intl._t("none"));
      out.write("</i>");

                   }
               } 
      out.write("</td>\r\n    </tr>\r\n");
 /* TODO SSL outproxy for httpclient if plugin not present */ 
      out.write("<tr>\r\n        <td class=\"tunnelDescription\" colspan=\"6\">\r\n");

            boolean isShared = indexBean.isSharedClient(curClient);
            String descr = indexBean.getTunnelDescription(curClient);
            if (isShared || (descr != null && descr.length() > 0)) {

      out.write("<span class=\"tunnelDescriptionLabel\">\r\n");

                if (isShared) {
                     
      out.write('<');
      out.write('b');
      out.write('>');
      out.print(intl._t("Shared Client"));

                } else {
                     
      out.write('<');
      out.write('b');
      out.write('>');
      out.print(intl._t("Description"));

                }
                if (descr != null && descr.length() > 0) {
                     
      out.write(":</b></span> ");
      out.print(descr);

                } else {
                     
      out.write("</b></span>");

                }
            } else {
                // needed to make the spacing look right
                
      out.write("&nbsp;");

            } // descr

      out.write("</td>\r\n    </tr>\r\n");

        } // for loop

      out.write("<tr>\r\n        <td class=\"newTunnel\" colspan=\"6\">\r\n            <form id=\"addNewClientTunnelForm\" action=\"edit\">\r\n                <b>");
      out.print(intl._t("New client tunnel"));
      out.write(":</b>&nbsp;\r\n                    <select name=\"type\">\r\n                        <option value=\"client\">");
      out.print(intl._t("Standard"));
      out.write("</option>\r\n                        <option value=\"httpclient\">HTTP/CONNECT</option>\r\n                        <option value=\"ircclient\">IRC</option>\r\n                        <option value=\"sockstunnel\">SOCKS 4/4a/5</option>\r\n                        <option value=\"socksirctunnel\">SOCKS IRC</option>\r\n                        <option value=\"connectclient\">CONNECT</option>\r\n                        <option value=\"streamrclient\">Streamr</option>\r\n                    </select>\r\n                    <input class=\"control\" type=\"submit\" value=\"");
      out.print(intl._t("Create"));
      out.write("\" />\r\n            </form>\r\n        </td>\r\n    </tr>\r\n</table>\r\n</div>\r\n");


  }  // isInitialized()


      out.write("</body></html>\r\n");
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