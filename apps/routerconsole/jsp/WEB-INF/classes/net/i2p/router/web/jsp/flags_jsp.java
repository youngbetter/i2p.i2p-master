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

public final class flags_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

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
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;


/*
 * USE CAUTION WHEN EDITING
 * Trailing whitespace OR NEWLINE on the last line will cause
 * IllegalStateExceptions !!!
 *
 * Do not tag this file for translation.
 */

/**
 *  flags.jsp?c=de => icons/flags/de.png
 *  with headers set so the browser caches.
 *
 *  As of 0.9.36:
 *  All new and changed flags must go in the flags16x11/ dir,
 *  which will be checked first by flags.jsp.
 *  The flags/ dir is the original set from famfamfam,
 *  which may be symlinked in package installs.
 *
 */
String c = request.getParameter("c");
if (c != null &&
    (c.length() == 2 || c.length() == 7) &&
    c.replaceAll("[a-z0-9_]", "").length() == 0) {
    String flagSet = "flags16x11";
    String s = request.getParameter("s");

    String base = net.i2p.I2PAppContext.getGlobalContext().getBaseDir().getAbsolutePath() +
                  java.io.File.separatorChar +
                  "docs" + java.io.File.separatorChar + "icons";
    String file = flagSet + java.io.File.separatorChar + c + ".png";
    java.io.File ffile = new java.io.File(base, file);
    if (!ffile.exists()) {
        // fallback to flags dir, which will be symlinked to /usr/share/flags/countries/16x11 for package builds
        file = "flags" + java.io.File.separatorChar + c + ".png";
        ffile = new java.io.File(base, file);
    }
    long lastmod = ffile.lastModified();
    if (lastmod > 0) {
        long iflast = request.getDateHeader("If-Modified-Since");
        // iflast is -1 if not present; round down file time
        if (iflast >= ((lastmod / 1000) * 1000)) {
            response.setStatus(304);
            return;
        }
        response.setDateHeader("Last-Modified", lastmod);
        // cache for a day
        response.setDateHeader("Expires", net.i2p.I2PAppContext.getGlobalContext().clock().now() + 86400000l);
        response.setHeader("Cache-Control", "public, max-age=604800");
        response.setHeader("X-Content-Type-Options", "nosniff");
    }
    long length = ffile.length();
    if (length > 0)
        response.setHeader("Content-Length", Long.toString(length));
    response.setContentType("image/png");
    response.setHeader("Accept-Ranges", "none");
    java.io.FileInputStream fin = null;
    java.io.OutputStream cout = response.getOutputStream();
    try {
        // flags dir may be a symlink, which readFile will reject
        // We carefully vetted the "c" value above.
        //net.i2p.util.FileUtil.readFile(file, base, cout);
        fin = new java.io.FileInputStream(ffile);
        net.i2p.data.DataHelper.copy(fin, cout);
    } catch (java.io.IOException ioe) {
        // prevent 'Committed' IllegalStateException from Jetty
        if (!response.isCommitted()) {
            response.sendError(403, ioe.toString());
        }  else {
            // not an error, happens when the browser closes the stream
            net.i2p.I2PAppContext.getGlobalContext().logManager().getLog(getClass()).warn("Error serving flags/" + c + ".png", ioe);
            // Jetty doesn't log this
            throw ioe;
        }
    } finally {
        if (fin != null)
            try { fin.close(); } catch (java.io.IOException ioe) {}
    }
} else {
    /*
     *  Send a 403 instead of a 404, because the server sends error.jsp
     *  for 404 errors, complete with the summary bar, which would be
     *  a huge load for a page full of flags if the user didn't have the
     *  flags directory for some reason.
     */
    response.sendError(403, "No flag specified");
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
