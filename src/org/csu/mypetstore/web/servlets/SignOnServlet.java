package org.csu.mypetstore.web.servlets;

import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.domain.Product;
import org.csu.mypetstore.service.AccountService;
import org.csu.mypetstore.service.CatalogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class SignOnServlet extends HttpServlet {
    private static final String SIGNON = "/WEB-INF/jsp/account/SignOnForm.jsp";
    private static final String MAIN = "/WEB-INF/jsp/catalog/Main.jsp";

    private Account account;
    private List<Product> myList;
    private boolean authenticated;

    private AccountService accountService = new AccountService();
    private CatalogService catalogService = new CatalogService();

    public void clear() {
        account = new Account();
        myList = null;
        authenticated = false;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        String inputCode = request.getParameter("inputCode").toLowerCase();
        String verifyCode = (String) request.getSession().getAttribute("verifyCode");
        if (!verifyCode.equals(inputCode)) {
            String msg = "验证码错误.";
            session.setAttribute("message", msg);
            request.getRequestDispatcher(SIGNON).forward(request, response);
        }

        account = accountService.getAccount(request.getParameter("username"), request.getParameter("password"));

        if (account == null) {
            String msg = "Invalid username or password.  Signon failed.";
            session.setAttribute("message", msg);
            clear();
            session.setAttribute("account", account);
            session.setAttribute("authenticated", authenticated);
            session.setAttribute("myList", myList);
            request.getRequestDispatcher(SIGNON).forward(request, response);
        } else {
            session.setAttribute("message", "");
            //account.setPassword(null);
            myList = catalogService.getProductListByCategory(account.getFavouriteCategoryId());
            authenticated = true;

            session.setAttribute("message", null);

            session.setAttribute("account", account);
            session.setAttribute("authenticated", authenticated);
            session.setAttribute("myList", myList);

            request.getRequestDispatcher(MAIN).forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }


}
