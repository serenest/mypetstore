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

public class NewAccountServlet extends HttpServlet {
    private static final String MAIN = "/WEB-INF/jsp/catalog/Main.jsp";

    private AccountService accountService = new AccountService();
    private CatalogService catalogService = new CatalogService();

    private Account account;
    private List<Product> myList = null;
    private boolean authenticated;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        account = new Account();
        account.setUsername(request.getParameter("account.username"));
        account.setPassword(request.getParameter("account.password"));
        account.setEmail(request.getParameter("account.email"));
        account.setFirstName(request.getParameter("account.firstName"));
        account.setLastName(request.getParameter("account.lastName"));
        account.setStatus(request.getParameter("account.status"));
        account.setAddress1(request.getParameter("account.address1"));
        account.setAddress2(request.getParameter("account.address2"));
        account.setCity(request.getParameter("account.city"));
        account.setState(request.getParameter("account.state"));
        account.setZip(request.getParameter("account.zip"));
        account.setCountry(request.getParameter("account.country"));
        account.setPhone(request.getParameter("account.phone"));

        account.setLanguagePreference(request.getParameter("account.languagePreference"));
        account.setFavouriteCategoryId(request.getParameter("account.favouriteCategoryId"));
        account.setListOption(Boolean.parseBoolean(request.getParameter("account.listOption")));
        account.setBannerOption(Boolean.parseBoolean(request.getParameter("account.bannerOption")));
        account.setBannerName(request.getParameter("meiyou"));

        accountService.insertAccount(account);
        account = accountService.getAccount(account.getUsername());
        //myList = catalogService.getProductListByCategory(account.getFavouriteCategoryId());
        authenticated = true;

        session.setAttribute("account", account);
        session.setAttribute("authenticated", authenticated);
        session.setAttribute("myList", myList);

        request.getRequestDispatcher(MAIN).forward(request, response);

//        return new RedirectResolution(CatalogActionBean.class);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
