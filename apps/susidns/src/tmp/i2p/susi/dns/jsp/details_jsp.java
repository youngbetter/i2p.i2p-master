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

public final class details_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      i2p.susi.dns.NamingServiceBean book = null;
      synchronized (session) {
        book = (i2p.susi.dns.NamingServiceBean) _jspx_page_context.getAttribute("book", javax.servlet.jsp.PageContext.SESSION_SCOPE);
        if (book == null){
          book = new i2p.susi.dns.NamingServiceBean();
          _jspx_page_context.setAttribute("book", book, javax.servlet.jsp.PageContext.SESSION_SCOPE);
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
      org.apache.jasper.runtime.JspRuntimeLibrary.introspect(_jspx_page_context.findAttribute("book"), request);
      out.write('\n');
      org.apache.jasper.runtime.JspRuntimeLibrary.introspecthelper(_jspx_page_context.findAttribute("book"), "resetDeletionMarks", "1", null, null, false);
      out.write("\n<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">\n<html>\n<head>\n<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n<title>");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${book.book}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write(' ');
      out.print(intl._t("addressbook"));
      out.write(" - susidns</title>\n<link rel=\"stylesheet\" type=\"text/css\" href=\"");
      out.print(book.getTheme());
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
      out.write("</a>&nbsp;\n<a id=\"subs\" href=\"subscriptions\">");
      out.print(intl._t("Subscriptions"));
      out.write("</a>&nbsp;\n<a id=\"config\" href=\"config\">");
      out.print(intl._t("Configuration"));
      out.write("</a>\n</div>\n<hr>\n<div class=\"headline\">\n<h3>");
      out.print(intl._t("Address book"));
      out.write(':');
      out.write(' ');
      out.print(intl._t(book.getBook()));
      out.write("</h3>\n<h4>");
      out.print(intl._t("Storage"));
      out.write(':');
      out.write(' ');
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${book.displayName}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("</h4>\n</div>\n<div id=\"book\">\n");

    String detail = request.getParameter("h");
    if (detail == null) {
        
      out.write("<p>No host specified</p>");

    } else {
        // process save notes form
        book.saveNotes();
        detail = net.i2p.data.DataHelper.stripHTML(detail);
        java.util.List<i2p.susi.dns.AddressBean> addrs = book.getLookupAll();
        if (addrs == null) {
            
      out.write("<p>Not found: ");
      out.print(detail);
      out.write("</p>");

        } else {
            boolean haveImagegen = book.haveImagegen();
            // use one nonce for all
            String nonce = book.getSerial();
            boolean showNotes = !book.getBook().equals("published");
            for (i2p.susi.dns.AddressBean addr : addrs) {
                String b32 = addr.getB32();

      out.write('\n');
      org.apache.jasper.runtime.JspRuntimeLibrary.introspecthelper(_jspx_page_context.findAttribute("book"), "trClass", "0", null, null, false);
      out.write('\n');
 if (showNotes) { 
      out.write("\n<form method=\"POST\" action=\"details\">\n<input type=\"hidden\" name=\"book\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${book.book}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("\">\n<input type=\"hidden\" name=\"serial\" value=\"");
      out.print(nonce);
      out.write("\">\n<input type=\"hidden\" name=\"h\" value=\"");
      out.print(detail);
      out.write("\">\n<input type=\"hidden\" name=\"destination\" value=\"");
      out.print(addr.getDestination());
      out.write('"');
      out.write('>');
      out.write('\n');
 }  // showNotes  
      out.write("\n<table class=\"book\" id=\"host_details\" cellspacing=\"0\" cellpadding=\"5\">\n<tr class=\"list");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${book.trClass}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("\">\n<td>");
      out.print(intl._t("Hostname"));
      out.write("</td>\n<td><a href=\"http://");
      out.print(addr.getName());
      out.write("/\" target=\"_top\">");
      out.print(addr.getDisplayName());
      out.write("</a></td>\n</tr>\n<tr class=\"list");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${book.trClass}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write('"');
      out.write('>');
      out.write('\n');

    if (addr.isIDN()) {

      out.write("\n<td>");
      out.print(intl._t("Encoded Name"));
      out.write("</td>\n<td><a href=\"http://");
      out.print(addr.getName());
      out.write("/\" target=\"_top\">");
      out.print(addr.getName());
      out.write("</a></td>\n</tr>\n<tr class=\"list");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${book.trClass}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write('"');
      out.write('>');
      out.write('\n');

    }

      out.write("\n<td>");
      out.print(intl._t("Base 32 Address"));
      out.write("</td>\n<td><a href=\"http://");
      out.print(b32);
      out.write("/\" target=\"_top\">");
      out.print(b32);
      out.write("</a></td>\n</tr>\n<tr class=\"list");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${book.trClass}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("\">\n<td>");
      out.print(intl._t("Base 64 Hash"));
      out.write("</td>\n<td>");
      out.print(addr.getB64());
      out.write("</td>\n</tr>\n<tr class=\"list");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${book.trClass}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("\">\n<td>");
      out.print(intl._t("Address Helper"));
      out.write("</td>\n<td><a href=\"http://");
      out.print(addr.getName());
      out.write("/?i2paddresshelper=");
      out.print(addr.getDestination());
      out.write("\" target=\"_top\">");
      out.print(intl._t("link"));
      out.write("</a></td>\n</tr>\n<tr class=\"list");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${book.trClass}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("\">\n<td>");
      out.print(intl._t("Public Key"));
      out.write("</td>\n<td>");
      out.print(intl._t("ElGamal 2048 bit"));
      out.write("</td>\n</tr>\n<tr class=\"list");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${book.trClass}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("\">\n<td>");
      out.print(intl._t("Signing Key"));
      out.write("</td>\n<td>");
      out.print(addr.getSigType());
      out.write("</td>\n</tr>\n<tr class=\"list");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${book.trClass}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("\">\n<td>");
      out.print(intl._t("Certificate"));
      out.write("</td>\n<td>");
      out.print(addr.getCert());
      out.write("</td>\n</tr>\n<tr class=\"list");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${book.trClass}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("\">\n<td>");
      out.print(intl._t("Validated"));
      out.write("</td>\n<td>");
      out.print(addr.isValidated() ? intl._t("yes") : intl._t("no"));
      out.write("</td>\n</tr>\n");
 if (showNotes) { 
      out.write("\n<tr class=\"list");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${book.trClass}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("\">\n<td>");
      out.print(intl._t("Source"));
      out.write("</td>\n<td>");
      out.print(addr.getSource());
      out.write("</td>\n</tr>\n<tr class=\"list");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${book.trClass}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("\">\n<td>");
      out.print(intl._t("Added Date"));
      out.write("</td>\n<td>");
      out.print(addr.getAdded());
      out.write("</td>\n</tr>\n<tr class=\"list");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${book.trClass}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("\">\n<td>");
      out.print(intl._t("Last Modified"));
      out.write("</td>\n<td>");
      out.print(addr.getModded());
      out.write("</td>\n</tr>\n");
 }  // showNotes  
      out.write("\n<tr class=\"list");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${book.trClass}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("\">\n<td>");
      out.print(intl._t("Destination"));
      out.write("</td>\n<td class=\"destinations\"><div class=\"destaddress\" tabindex=\"0\">");
      out.print(addr.getDestination());
      out.write("</div></td>\n</tr>\n");
 if (showNotes) { 
      out.write("\n<tr class=\"list");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${book.trClass}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("\">\n<td>");
      out.print(intl._t("Notes"));
      out.write("<br>\n<input class=\"accept\" type=\"submit\" name=\"action\" value=\"");
      out.print(intl._t("Save Notes"));
      out.write("\"></td>\n<td><textarea name=\"nofilter_notes\" rows=\"3\" style=\"height:6em\" wrap=\"off\" cols=\"70\">");
      out.print(addr.getNotes());
      out.write("</textarea></td>\n</tr>\n");
 }  // showNotes  
      out.write("\n</table>\n");
 if (showNotes) { 
      out.write("\n</form>\n");
 }  // showNotes  
      out.write("\n<div id=\"buttons\">\n<form method=\"POST\" action=\"addressbook\">\n<p class=\"buttons\">\n<input type=\"hidden\" name=\"book\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${book.book}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("\">\n<input type=\"hidden\" name=\"serial\" value=\"");
      out.print(nonce);
      out.write("\">\n<input type=\"hidden\" name=\"begin\" value=\"0\">\n<input type=\"hidden\" name=\"end\" value=\"49\">\n<input type=\"hidden\" name=\"checked\" value=\"");
      out.print(detail);
      out.write("\">\n<input type=\"hidden\" name=\"destination\" value=\"");
      out.print(addr.getDestination());
      out.write("\">\n<input class=\"delete\" type=\"submit\" name=\"action\" value=\"");
      out.print(intl._t("Delete Entry"));
      out.write("\" >\n</p>\n</form>\n</div>");
      out.write('\n');

                if (haveImagegen) {

      out.write("\n<div id=\"visualid\">\n<h3>");
      out.print(intl._t("Visual Identification for"));
      out.write(" <span id=\"idAddress\">");
      out.print(addr.getName());
      out.write("</span></h3>\n<table>\n<tr>\n<td><img src=\"/imagegen/id?s=256&amp;c=");
      out.print(addr.getB64().replace("=", "%3d"));
      out.write("\" width=\"256\" height=\"256\"></td>\n<td><img src=\"/imagegen/qr?s=384&amp;t=");
      out.print(addr.getName());
      out.write("&amp;c=http%3a%2f%2f");
      out.print(addr.getName());
      out.write("%2f%3fi2paddresshelper%3d");
      out.print(addr.getDestination());
      out.write("\"></td>\n</tr>\n<tr>\n<td colspan=\"2\"><a class=\"fakebutton\" href=\"/imagegen\" title=\"");
      out.print(intl._t("Create your own identification images"));
      out.write("\" target=\"_blank\">");
      out.print(intl._t("Launch Image Generator"));
      out.write("</a></td>\n</tr>\n</table>\n</div>");
      out.write('\n');

                }  // haveImagegen

      out.write("\n<hr>\n");

            }  // foreach addr
        }  // addrs == null
    }  // detail == null

      out.write("\n</div>");
      out.write("\n<div id=\"footer\">\n<p class=\"footer\">susidns v");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${version.version}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write(" &copy; <a href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${version.url}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("\" target=\"_top\">susi</a> 2005</p>\n</div>\n</div>");
      out.write("\n</body>\n</html>\n");
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
