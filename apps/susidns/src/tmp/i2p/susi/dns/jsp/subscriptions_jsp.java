/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: JspC/ApacheTomcat9
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package i2p.susi.dns.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class subscriptions_jsp extends org.apache.jasper.runtime.HttpJspBase
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
    _jspx_imports_classes = null;
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


/*
 * Created on Sep 02, 2005
 *
 *  This file is part of susidns project, see http://susi.i2p/
 *
 *  Copyright (C) 2005 <susi23@mail.i2p>
 *
 *  This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program; if not, write to the Free Software
 *  Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 * $Revision: 1.2 $
 */

      out.write('\n');

    // http://www.crazysquirrel.com/computing/general/form-encoding.jspx
    if (request.getCharacterEncoding() == null)
        request.setCharacterEncoding("UTF-8");

    response.setHeader("X-Frame-Options", "SAMEORIGIN");
    response.setHeader("Content-Security-Policy", "default-src 'self'; style-src 'self' 'unsafe-inline'; script-src 'self'; form-action 'self'; frame-ancestors 'self'; object-src 'none'; media-src 'none'");
    response.setHeader("X-XSS-Protection", "1; mode=block");
    response.setHeader("X-Content-Type-Options", "nosniff");
    response.setHeader("Referrer-Policy", "no-referrer");
    response.setHeader("Accept-Ranges", "none");

      out.write('\n');
      out.write('\n');
      out.write('\n');
      out.write('\n');
      i2p.susi.dns.VersionBean version = null;
      synchronized (application) {
        version = (i2p.susi.dns.VersionBean) _jspx_page_context.getAttribute("version", javax.servlet.jsp.PageContext.APPLICATION_SCOPE);
        if (version == null){
          version = new i2p.susi.dns.VersionBean();
          _jspx_page_context.setAttribute("version", version, javax.servlet.jsp.PageContext.APPLICATION_SCOPE);
        }
      }
      out.write('\n');
      i2p.susi.dns.SubscriptionsBean subs = null;
      synchronized (session) {
        subs = (i2p.susi.dns.SubscriptionsBean) _jspx_page_context.getAttribute("subs", javax.servlet.jsp.PageContext.SESSION_SCOPE);
        if (subs == null){
          subs = new i2p.susi.dns.SubscriptionsBean();
          _jspx_page_context.setAttribute("subs", subs, javax.servlet.jsp.PageContext.SESSION_SCOPE);
        }
      }
      out.write('\n');
      i2p.susi.dns.Messages intl = null;
      synchronized (application) {
        intl = (i2p.susi.dns.Messages) _jspx_page_context.getAttribute("intl", javax.servlet.jsp.PageContext.APPLICATION_SCOPE);
        if (intl == null){
          intl = new i2p.susi.dns.Messages();
          _jspx_page_context.setAttribute("intl", intl, javax.servlet.jsp.PageContext.APPLICATION_SCOPE);
        }
      }
      out.write('\n');
      org.apache.jasper.runtime.JspRuntimeLibrary.introspect(_jspx_page_context.findAttribute("subs"), request);
      out.write("\n<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">\n<html>\n<head>\n<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n<title>");
      out.print(intl._t("subscriptions"));
      out.write(" - susidns</title>\n<link rel=\"stylesheet\" type=\"text/css\" href=\"");
      out.print(subs.getTheme());
      out.write("susidns.css?");
      out.print(net.i2p.CoreVersion.VERSION);
      out.write("\">\n<script src=\"js/messages.js?");
      out.print(net.i2p.CoreVersion.VERSION);
      out.write("\" type=\"text/javascript\"></script>\n</head>\n<body>\n<div class=\"page\">\n<hr>\n<div id=\"navi\">\n<a id=\"overview\" href=\"index\">");
      out.print(intl._t("Overview"));
      out.write("</a>&nbsp;\n<a class=\"abook\" href=\"addressbook?book=private&amp;filter=none\">");
      out.print(intl._t("Private"));
      out.write("</a>&nbsp;\n<a class=\"abook\" href=\"addressbook?book=local&amp;filter=none\">");
      out.print(intl._t("Local"));
      out.write("</a>&nbsp;\n<a class=\"abook\" href=\"addressbook?book=router&amp;filter=none\">");
      out.print(intl._t("Router"));
      out.write("</a>&nbsp;\n<a class=\"abook\" href=\"addressbook?book=published&amp;filter=none\">");
      out.print(intl._t("Published"));
      out.write("</a>&nbsp;\n<a id=\"subs\" class=\"active\" href=\"subscriptions\">");
      out.print(intl._t("Subscriptions"));
      out.write("</a>&nbsp;\n<a id=\"config\" href=\"config\">");
      out.print(intl._t("Configuration"));
      out.write("</a>\n</div>\n<hr>\n<div class=\"headline\" id=\"subscriptions\">\n<h3>");
      out.print(intl._t("Subscriptions"));
      out.write("</h3>\n<h4>");
      out.print(intl._t("File location"));
      out.write(':');
      out.write(' ');
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${subs.fileName}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("</h4>\n</div>\n<div id=\"messages\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${subs.messages}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("</div>\n<form method=\"POST\" action=\"subscriptions\">\n<div id=\"content\">\n<input type=\"hidden\" name=\"serial\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${subs.serial}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("\" >\n<textarea name=\"content\" rows=\"10\" cols=\"80\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${subs.content}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("</textarea>\n</div>\n<div id=\"buttons\">\n<input class=\"reload\" type=\"submit\" name=\"action\" value=\"");
      out.print(intl._t("Reload"));
      out.write("\" >\n<input class=\"accept\" type=\"submit\" name=\"action\" value=\"");
      out.print(intl._t("Save"));
      out.write("\" >\n</div>\n</form>\n<div class=\"help\" id=\"helpsubs\">\n<p class=\"help\">\n");
      out.print(intl._t("The subscription file contains a list of i2p URLs."));
      out.write('\n');
      out.print(intl._t("The addressbook application regularly checks this list for new eepsites."));
      out.write('\n');
      out.print(intl._t("Those URLs refer to published hosts.txt files."));
      out.write('\n');
      out.print(intl._t("The default subscription is the hosts.txt from {0}, which is updated infrequently.", "i2p-projekt.i2p"));
      out.write('\n');
      out.print(intl._t("So it is a good idea to add additional subscriptions to sites that have the latest addresses."));
      out.write("\n<a href=\"/help#addressbooksubs\" target=\"_top\">");
      out.print(intl._t("See the FAQ for a list of subscription URLs."));
      out.write("</a>\n</p>\n</div>\n<div id=\"footer\">\n<hr>\n<p class=\"footer\">susidns v");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${version.version}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write(" &copy; <a href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${version.url}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("\" target=\"_top\">susi</a> 2005</p>\n</div>\n</div>\n</body>\n</html>\n");
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
