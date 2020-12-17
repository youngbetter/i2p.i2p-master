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
import java.io.InputStream;
import net.i2p.i2ptunnel.web.EditBean;
import net.i2p.servlet.RequestWrapper;
import net.i2p.client.I2PSessionException;
import net.i2p.client.naming.HostTxtEntry;
import net.i2p.data.PrivateKeyFile;
import net.i2p.data.SigningPrivateKey;
import net.i2p.util.OrderedProperties;

public final class register_jsp extends org.apache.jasper.runtime.HttpJspBase
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
    _jspx_imports_classes.add("net.i2p.client.naming.HostTxtEntry");
    _jspx_imports_classes.add("net.i2p.data.PrivateKeyFile");
    _jspx_imports_classes.add("net.i2p.data.SigningPrivateKey");
    _jspx_imports_classes.add("net.i2p.servlet.RequestWrapper");
    _jspx_imports_classes.add("net.i2p.util.OrderedProperties");
    _jspx_imports_classes.add("net.i2p.i2ptunnel.web.EditBean");
    _jspx_imports_classes.add("net.i2p.client.I2PSessionException");
    _jspx_imports_classes.add("java.io.InputStream");
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


      out.write('\n');
      out.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">\n");

  /* right now using EditBean instead of IndexBean for getSpoofedHost() */
  /* but might want to POST to it anyway ??? */

      out.write('\n');
      net.i2p.i2ptunnel.web.EditBean editBean = null;
      editBean = (net.i2p.i2ptunnel.web.EditBean) _jspx_page_context.getAttribute("editBean", javax.servlet.jsp.PageContext.REQUEST_SCOPE);
      if (editBean == null){
        editBean = new net.i2p.i2ptunnel.web.EditBean();
        _jspx_page_context.setAttribute("editBean", editBean, javax.servlet.jsp.PageContext.REQUEST_SCOPE);
      }
      out.write('\n');
      net.i2p.i2ptunnel.ui.Messages intl = null;
      intl = (net.i2p.i2ptunnel.ui.Messages) _jspx_page_context.getAttribute("intl", javax.servlet.jsp.PageContext.REQUEST_SCOPE);
      if (intl == null){
        intl = new net.i2p.i2ptunnel.ui.Messages();
        _jspx_page_context.setAttribute("intl", intl, javax.servlet.jsp.PageContext.REQUEST_SCOPE);
      }
      out.write('\n');

   RequestWrapper wrequest = new RequestWrapper(request);
   String tun = wrequest.getParameter("tunnel");
   int curTunnel = -1;
   if (tun != null) {
     try {
       curTunnel = Integer.parseInt(tun);
     } catch (NumberFormatException nfe) {
       curTunnel = -1;
     }
   }

      out.write("\n<html xmlns=\"http://www.w3.org/1999/xhtml\" xml:lang=\"en\" lang=\"en\">\n<head>\n    <title>");
      out.print(intl._t("Hidden Services Manager"));
      out.write(' ');
      out.write('-');
      out.write(' ');
      out.print(intl._t("Registration Helper"));
      out.write("</title>\n    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\n    <link href=\"/themes/console/images/favicon.ico\" type=\"image/x-icon\" rel=\"shortcut icon\" />\n    <link href=\"");
      out.print(editBean.getTheme());
      out.write("i2ptunnel.css?");
      out.print(net.i2p.CoreVersion.VERSION);
      out.write("\" rel=\"stylesheet\" type=\"text/css\" /> \n<style type='text/css'>\ninput.default { width: 1px; height: 1px; visibility: hidden; }\n</style>\n</head>\n<body id=\"tunnelRegistration\">\n\n");


  if (editBean.isInitialized()) {


      out.write("\n    <form method=\"post\" enctype=\"multipart/form-data\" action=\"register\" accept-charset=\"UTF-8\">\n        <div class=\"panel\" id=\"registration\">\n");

    String tunnelTypeName;
    String tunnelType;
    boolean valid = false;
    if (curTunnel >= 0) {
        tunnelTypeName = editBean.getTunnelType(curTunnel);
        tunnelType = editBean.getInternalType(curTunnel);
      
      out.write("<h2>");
      out.print(intl._t("Registration Helper"));
      out.write(' ');
      out.write('(');
      out.print(editBean.getTunnelName(curTunnel));
      out.write(")</h2>");
 
    } else {
        tunnelTypeName = "new";
        tunnelType = "new";
      
      out.write("<h2>Fail</h2><p>Tunnel not found</p>");
 
    }
    String b64 = editBean.getDestinationBase64(curTunnel);
    String name = editBean.getSpoofedHost(curTunnel);
    if (name == null || name.equals(""))
        name = editBean.getTunnelName(curTunnel);

      out.write("\n                <input type=\"hidden\" name=\"tunnel\" value=\"");
      out.print(curTunnel);
      out.write("\" />\n                <input type=\"hidden\" name=\"nonce\" value=\"");
      out.print(net.i2p.i2ptunnel.web.IndexBean.getNextNonce());
      out.write("\" />\n                <input type=\"hidden\" name=\"type\" value=\"");
      out.print(tunnelType);
      out.write("\" />\n                <input type=\"submit\" class=\"default\" name=\"action\" value=\"Save changes\" />\n");

    String curEncryptMode = editBean.getEncryptMode(curTunnel);
    if (!"0".equals(curEncryptMode)) {

      out.write("\n      <table><tr><td class=\"infohelp\">\n        ");
      out.print(intl._t("This service uses encrypted leasesets. Registration is not recommended. Registration authentication is disabled."));
      out.write("\n      </td></tr>\n");

    } else if (!"new".equals(tunnelType)) {

      out.write("\n\n<table>\n    <tr>\n        <td class=\"infohelp\">\n    ");
      out.print(intl._t("Please be sure to select, copy, and paste the entire contents of the appropriate authentication data into the form of your favorite registration site"));
      out.write("\n        </td>\n    </tr>\n    <tr>\n        <td>\n            <b>");
      out.print(intl._t("Tunnel name"));
      out.write(":</b> ");
      out.print(editBean.getTunnelName(curTunnel));
      out.write("\n        </td>\n    </tr>\n\n");

      if (("httpserver".equals(tunnelType)) || ("httpbidirserver".equals(tunnelType))) {
          
      out.write("\n    <tr><td><b>");
      out.print(intl._t("Website name"));
      out.write(":</b> ");
      out.print(editBean.getSpoofedHost(curTunnel));
      out.write("</td></tr>\n");

       }

      out.write("\n\n<!--\n    <tr>\n        <th>\n            <b>");
      out.print(intl._t("Local Destination"));
      out.write("</b>\n        </th>\n    </tr>\n    <tr>\n        <td>\n            <textarea rows=\"1\" style=\"height: 3em;\" cols=\"60\" readonly=\"readonly\" id=\"localDestination\" title=\"Read Only: Local Destination (if known)\" wrap=\"off\" spellcheck=\"false\">");
      out.print(editBean.getDestinationBase64(curTunnel));
      out.write("</textarea>\n        </td>\n    </tr>\n-->\n\n");

       if (b64 == null || b64.length() < 516) {
           
      out.write("<tr><td class=\"infohelp\">");
      out.print(intl._t("Local destination is not available. Start the tunnel."));
      out.write("</td></tr>");

       } else if (name == null || name.equals("") || name.contains(" ") || !name.endsWith(".i2p")) {
           if (("httpserver".equals(tunnelType)) || ("httpbidirserver".equals(tunnelType))) {
               
      out.write("<tr><td class=\"infohelp\">");
      out.print(intl._t("To enable registration verification, edit tunnel and set name (or website name) to a valid host name ending in '.i2p'"));
      out.write("</td></tr>");

           } else {
               
      out.write("<tr><td class=\"infohelp\">");
      out.print(intl._t("To enable registration verification, edit tunnel and set name to a valid host name ending in '.i2p'"));
      out.write("</td></tr>");

           }
       } else {
           SigningPrivateKey spk = editBean.getSigningPrivateKey(curTunnel);
           if (spk == null) {
               
      out.write("<tr><td class=\"infohelp\">");
      out.print(intl._t("Destination signing key is not available. Start the tunnel."));
      out.write("</td></tr>");

           } else if (spk.isOffline()) {
               
      out.write("<tr><td class=\"infohelp\">");
      out.print(intl._t("Destination signing key is offline. Use CLI tools on the offline machine."));
      out.write("</td></tr>");

           } else {
               valid = true;
               OrderedProperties props = new OrderedProperties();
               HostTxtEntry he = new HostTxtEntry(name, b64, props);
               he.sign(spk);
          
      out.write("\n\n    <tr>\n        <th>\n            ");
      out.print(intl._t("Authentication for adding host {0}", name));
      out.write("\n        </th>\n    </tr>\n    <tr>\n        <td>\n            <div class=\"displayText\" tabindex=\"0\" title=\"");
      out.print(intl._t("Copy and paste this to the registration site"));
      out.write('"');
      out.write('>');
 he.write(out); 
      out.write("</div>\n        </td>\n    </tr>\n</table>\n\n<h3>");
      out.print(intl._t("Advanced authentication strings"));
      out.write("</h3>\n\n");

               props.remove(HostTxtEntry.PROP_SIG);
               props.setProperty(HostTxtEntry.PROP_ACTION, HostTxtEntry.ACTION_REMOVE);
               he.signRemove(spk);
          
      out.write("\n\n<table>\n    <tr>\n        <th>\n            ");
      out.print(intl._t("Authentication for removing host {0}", name));
      out.write("\n        </th>\n    </tr>\n    <tr>\n        <td>\n            <div class=\"displayText\" tabindex=\"0\" title=\"");
      out.print(intl._t("Copy and paste this to the registration site"));
      out.write('"');
      out.write('>');
 he.writeRemove(out); 
      out.write("</div>\n        </td>\n    </tr>\n\n");

               String oldname = wrequest.getParameter("oldname");
               String olddestfile = wrequest.getFilename("olddestfile");
               SigningPrivateKey spk2 = null;
               String olddest = null;
               if (olddestfile != null) {
                   InputStream destIn = wrequest.getInputStream("olddestfile");
                   if (destIn.available() > 0) {
                       try {
                           PrivateKeyFile pkf2 = new PrivateKeyFile(destIn);
                           String oldb64 = pkf2.getDestination().toBase64();
                           if (!b64.equals(oldb64)) {
                               // disallow dup
                               olddest = oldb64;
                               spk2 = pkf2.getSigningPrivKey();
                           }
                       } catch (I2PSessionException ise) {
                           throw new IllegalStateException("Unable to open private key file " + olddestfile, ise);
                       }
                   }
               }
               props.remove(HostTxtEntry.PROP_SIG);
          
      out.write("\n    <tr>\n        <th>\n                    ");
      out.print(intl._t("Authentication for changing name"));
      out.write("\n        </th>\n    </tr>\n");

               if (oldname != null && oldname.length() > 0 && !oldname.equals(name)) {
                   props.setProperty(HostTxtEntry.PROP_ACTION, HostTxtEntry.ACTION_CHANGENAME);
                   props.setProperty(HostTxtEntry.PROP_OLDNAME, oldname);
                   he.sign(spk);
                
      out.write("\n    <tr>\n        <td>\n            <div class=\"displayText\" tabindex=\"0\" title=\"");
      out.print(intl._t("Copy and paste this to the registration site"));
      out.write('"');
      out.write('>');
 he.write(out); 
      out.write("</div>\n        </td>\n    </tr>\n    <tr>\n        <td class=\"infohelp\">\n            ");
      out.print(intl._t("This will change the name from {0} to {1}, using the same destination", oldname, name));
      out.write("\n        </td>\n    </tr>\n\n");

               } else {
                
      out.write("<tr><td class=\"infohelp\">");
      out.print(intl._t("This tunnel must be configured with the new host name."));
      out.write("\n                  &nbsp;");
      out.print(intl._t("Enter old hostname below."));
      out.write("</td></tr>\n");

               }
          
      out.write('\n');
      out.write('\n');

               props.remove(HostTxtEntry.PROP_SIG);
          
      out.write("\n    <tr>\n        <th>\n                    ");
      out.print(intl._t("Authentication for adding alias"));
      out.write("\n        </th>\n    </tr>\n");

               if (oldname != null && oldname.length() > 0 && !oldname.equals(name)) {
                   props.setProperty(HostTxtEntry.PROP_ACTION, HostTxtEntry.ACTION_ADDNAME);
                   props.setProperty(HostTxtEntry.PROP_OLDNAME, oldname);
                   he.sign(spk);
                
      out.write("\n    <tr>\n        <td>\n            <div class=\"displayText\" tabindex=\"0\" title=\"");
      out.print(intl._t("Copy and paste this to the registration site"));
      out.write('"');
      out.write('>');
 he.write(out); 
      out.write("</div>\n        </td>\n    </tr>\n    <tr>\n        <td class=\"infohelp\">\n            ");
      out.print(intl._t("This will add an alias {0} for {1}, using the same destination", name, oldname));
      out.write("\n        </td>\n    </tr>\n");

               } else {
                
      out.write("<tr> <td class=\"infohelp\">");
      out.print(intl._t("This tunnel must be configured with the new host name."));
      out.write("\n                  &nbsp;");
      out.print(intl._t("Enter old hostname below."));
      out.write("</td></tr>\n");

               }
          
      out.write('\n');
      out.write('\n');

               props.remove(HostTxtEntry.PROP_SIG);
               props.remove(HostTxtEntry.PROP_OLDNAME);
          
      out.write("\n\n    <tr>\n        <th>\n                    ");
      out.print(intl._t("Authentication for changing destination"));
      out.write("\n        </th>\n    </tr>\n\n");

               if (spk2 != null) {
                   props.setProperty(HostTxtEntry.PROP_ACTION, HostTxtEntry.ACTION_CHANGEDEST);
                   props.setProperty(HostTxtEntry.PROP_OLDDEST, olddest);
                   he.signInner(spk2);
                   he.sign(spk);
                
      out.write("\n\n    <tr>\n        <td>\n            <div class=\"displayText\" tabindex=\"0\" title=\"");
      out.print(intl._t("Copy and paste this to the registration site"));
      out.write('"');
      out.write('>');
 he.write(out); 
      out.write("</div>\n        </td>\n    </tr>\n    <tr>\n        <td class=\"infohelp\">\n            ");
      out.print(intl._t("This will change the destination for {0}", name));
      out.write("\n        </td>\n    </tr>\n\n");

               } else {
                
      out.write("<tr><td class=\"infohelp\">");
      out.print(intl._t("This tunnel must be configured with the new destination."));
      out.write("\n                  &nbsp;");
      out.print(intl._t("Enter old destination below."));
      out.write("</td></tr>\n");

               }
          
      out.write('\n');
      out.write('\n');

               props.remove(HostTxtEntry.PROP_SIG);
               props.remove(HostTxtEntry.PROP_OLDSIG);
          
      out.write("\n\n    <tr>\n        <th>\n                    ");
      out.print(intl._t("Authentication for adding alternate destination"));
      out.write("\n        </th>\n    </tr>\n\n");

               if (spk2 != null) {
                   props.setProperty(HostTxtEntry.PROP_ACTION, HostTxtEntry.ACTION_ADDDEST);
                   props.setProperty(HostTxtEntry.PROP_OLDDEST, olddest);
                   he.signInner(spk2);
                   he.sign(spk);
                
      out.write("\n    <tr>\n        <td>\n            <div class=\"displayText\" tabindex=\"0\" title=\"");
      out.print(intl._t("Copy and paste this to the registration site"));
      out.write('"');
      out.write('>');
 he.write(out); 
      out.write("</div>\n        </td>\n    </tr>\n    <tr>\n        <td class=\"infohelp\">\n            ");
      out.print(intl._t("This will add an alternate destination for {0}", name));
      out.write("\n        </td>\n    </tr>\n");

               } else {
                   // If set, use the configured alternate destination as the new alias destination,
                   // and the configured primary destination as the inner signer.
                   // This is backwards from all the other ones, so we have to make a second HostTxtEntry just for this.
                   SigningPrivateKey spk3 = null;
                   String altdest = null;
                   String altdestfile = editBean.getAltPrivateKeyFile(curTunnel);
                   if (altdestfile.length() > 0) {
                       try {
                           PrivateKeyFile pkf3 = new PrivateKeyFile(altdestfile);
                           altdest = pkf3.getDestination().toBase64();
                           if (!b64.equals(altdest)) {
                               // disallow dup
                               spk3 = pkf3.getSigningPrivKey();
                           }
                       } catch (Exception e) {}
                   }
                   if (spk3 != null) {
                       OrderedProperties props2 = new OrderedProperties();
                       HostTxtEntry he2 = new HostTxtEntry(name, altdest, props2);
                       props2.setProperty(HostTxtEntry.PROP_ACTION, HostTxtEntry.ACTION_ADDDEST);
                       props2.setProperty(HostTxtEntry.PROP_OLDDEST, b64);
                       he2.signInner(spk);
                       he2.sign(spk3);
                
      out.write("<tr><td><div class=\"displayText\" tabindex=\"0\" title=\"");
      out.print(intl._t("Copy and paste this to the registration site"));
      out.write('"');
      out.write('>');
 he2.write(out); 
      out.write("</div></td></tr>\n                <tr><td class=\"infohelp\">");
      out.print(intl._t("This will add an alternate destination for {0}", name));
      out.write("</td></tr>\n");

                   } else {
                
      out.write("<tr><td class=\"infohelp\">");
      out.print(intl._t("This tunnel must be configured with the new destination."));
      out.write("\n                  &nbsp;");
      out.print(intl._t("Enter old destination below."));
      out.write("</td></tr>\n");

                   }  // spk3
               }  // spk2
          
      out.write('\n');
      out.write('\n');



               props.remove(HostTxtEntry.PROP_SIG);
               props.remove(HostTxtEntry.PROP_OLDSIG);
          
      out.write("\n\n    <tr>\n        <th>\n                    ");
      out.print(intl._t("Authentication for adding subdomain"));
      out.write("\n        </th>\n    </tr>\n");

               if (oldname != null && oldname.length() > 0 && !oldname.equals(name) && spk2 != null) {
                   props.setProperty(HostTxtEntry.PROP_ACTION, HostTxtEntry.ACTION_ADDSUBDOMAIN);
                   props.setProperty(HostTxtEntry.PROP_OLDNAME, oldname);
                   props.setProperty(HostTxtEntry.PROP_OLDDEST, olddest);
                   he.signInner(spk2);
                   he.sign(spk);
                
      out.write("\n\n    <tr>\n        <td>\n            <div class=\"displayText\" tabindex=\"0\" title=\"");
      out.print(intl._t("Copy and paste this to the registration site"));
      out.write('"');
      out.write('>');
 he.write(out); 
      out.write("</div>\n        </td>\n    </tr>\n    <tr>\n        <td class=\"infohelp\">\n            ");
      out.print(intl._t("This will add a subdomain {0} of {1}, with a different destination", name, oldname));
      out.write("\n        </td>\n    </tr>\n\n");

               } else {
                
      out.write("\n    <tr>\n        <td class=\"infohelp\">\n            ");
      out.print(intl._t("This tunnel must be configured with the new subdomain and destination."));
      out.write("\n            &nbsp;");
      out.print(intl._t("Enter higher-level domain and destination below."));
      out.write("\n        </td>\n    </tr>\n\n");

               }
          
      out.write('\n');
      out.write('\n');

          }  // spk != null
       }  // valid b64 and name
    }  // !"new".equals(tunnelType)
    if (!valid && curTunnel >= 0) {
        
      out.write("\n    <tr>\n        <td>\n            <a href=\"edit?tunnel=");
      out.print(curTunnel);
      out.write('"');
      out.write('>');
      out.print(intl._t("Go back and edit the tunnel"));
      out.write("</a>\n        </td>\n    </tr>\n        ");

    }

      out.write('\n');
      out.write('\n');

    if (valid) {

      out.write("\n\n    <tr>\n        <th>\n            ");
      out.print(intl._t("Specify old name and destination"));
      out.write("\n        </th>\n    </tr>\n    <tr>\n        <td class=\"infohelp\">\n            ");
      out.print(intl._t("This is only required for advanced authentication."));
      out.write("\n            &nbsp;");
      out.print(intl._t("See above for required items."));
      out.write("\n        </td>\n    </tr>\n");

               String oldname = wrequest.getParameter("oldname");
               if (oldname == null) oldname = "";
          
      out.write("\n    <tr>\n        <td>\n            <b>");
      out.print(intl._t("Old hostname"));
      out.write(":</b>\n            <input type=\"text\" size=\"30\" maxlength=\"50\" name=\"oldname\" id=\"oldName\" value=\"");
      out.print(oldname);
      out.write("\" class=\"freetext\" />\n        </td>\n    </tr>\n    <tr>\n        <td>\n            <b>");
      out.print(intl._t("Private Key File for old Destination"));
      out.write(":</b>\n            <input type=\"file\" name=\"olddestfile\" id=\"oldDestFile\" value=\"\" />\n        </td>\n    </tr>\n    <tr>\n        <td class=\"buttons\">\n                    <input type=\"hidden\" value=\"true\" name=\"removeConfirm\" />\n                    <a class=\"control\" href=\"list\">");
      out.print(intl._t("Cancel"));
      out.write("</a>\n                    <button id=\"controlSave\" class=\"control\" type=\"submit\" name=\"action\" value=\"authenticate\"  title=\"");
      out.print(intl._t("Generate Authentication"));
      out.write('"');
      out.write('>');
      out.print(intl._t("Generate"));
      out.write("</button>\n        </td>\n    </tr>\n\n");

     } // valid

      out.write("\n\n</table>\n</div>\n    </form>\n");


  } else {
     
      out.write("<div id=\"notReady\">");
      out.print(intl._t("Tunnels are not initialized yet, please reload in two minutes."));
      out.write("</div>");

  }  // isInitialized()


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