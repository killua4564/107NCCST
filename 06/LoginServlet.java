// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   LoginServlet.java

package golden;

import java.io.*;
import java.net.URLEncoder;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import org.apache.tomcat.util.codec.binary.Base64;

// Referenced classes of package golden:
//            property

public class LoginServlet extends HttpServlet
{

    public LoginServlet()
    {
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        PrintWriter out;
        Throwable throwable;
        Exception exception;
        response.setContentType("text/html;charset=UTF-8");
        out = response.getWriter();
        throwable = null;
        try
        {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Logintest</title>");
            out.println("</head>");
            out.println("<body>");
            out.println((new StringBuilder()).append("<h1>Servlet Logintest at ").append(request.getContextPath()).append("</h1>").toString());
            out.println("</body>");
            out.println("</html>");
        }
        catch(Throwable throwable2)
        {
            throwable = throwable2;
            throw throwable2;
        }
        finally
        {
            if(out == null) goto _L0; else goto _L0
        }
        if(out != null)
            if(throwable != null)
                try
                {
                    out.close();
                }
                catch(Throwable throwable1)
                {
                    throwable.addSuppressed(throwable1);
                }
            else
                out.close();
        break MISSING_BLOCK_LABEL_180;
        if(throwable != null)
            try
            {
                out.close();
            }
            catch(Throwable throwable3)
            {
                throwable.addSuppressed(throwable3);
            }
        else
            out.close();
        throw exception;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        try
        {
            property property = new property();
            String acc = request.getParameter("acc");
            String pass = request.getParameter("pass");
            String jwt_token = request.getParameter("token");
            System.out.println((new StringBuilder()).append("acc::").append(acc).toString());
            System.out.println((new StringBuilder()).append("pass::").append(pass).toString());
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            SecretKeySpec secretKey = new SecretKeySpec(property.key, "AES");
            if("test12345".equals(acc) && "test".equals(pass))
            {
                // S4TU/s2RVdc2cyiW/vVHtQ==
                String token = (new StringBuilder()).append(acc).append("_guest").toString();
                cipher.init(1, secretKey, new IvParameterSpec(property.IV.getBytes()));
                byte byteCipherText[] = cipher.doFinal(token.getBytes());
                System.out.println((new StringBuilder()).append("byteCipherText.length::").append(byteCipherText.length).toString());
                token = new String(Base64.encodeBase64(byteCipherText));
                token = URLEncoder.encode(token, "UTF-8");
                response.getWriter().append("Welcome test12345_guest\n").append((new StringBuilder()).append("Your token is ").append(token).toString());
            } else if(jwt_token != null)
            {
                cipher.init(2, secretKey, new IvParameterSpec(property.IV.getBytes()));
                byte byteCipherText[] = Base64.decodeBase64(jwt_token.getBytes());
                byte bytePlainText[] = cipher.doFinal(byteCipherText);
                response.getWriter().append((new StringBuilder()).append("Welcome:").append(new String(bytePlainText)).append("\n").toString());
                String token[] = (new String(bytePlainText)).split("_");
                if(token.length == 2 && "admin".equals(token[1]))
                    response.getWriter().append((new StringBuilder()).append("Flag : ").append(property.FLAG).append("\n").toString());
            } else
            {
                response.getWriter().append("Wrong account and password.");
            }
        }
        catch(Exception e)
        {
            response.getWriter().append(e.getMessage()).append(request.getContextPath());
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        processRequest(request, response);
    }

    public String getServletInfo()
    {
        return "Short description";
    }
}
