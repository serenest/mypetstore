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

public class EditAccountServlet extends HttpServlet {
    private static final String EDIT_ACCOUNT = "/WEB-INF/jsp/account/EditAccountForm.jsp";

    private AccountService accountService = new AccountService();
    private CatalogService catalogService = new CatalogService();

    private Account account;
    private List<Product> myList = null;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        account = (Account) session.getAttribute("account");
        account.setPassword(request.getParameter("account.repeatedPassword"));
        account.setEmail(request.getParameter("account.email"));
        account.setFirstName(request.getParameter("account.firstName"));
        account.setLastName(request.getParameter("account.lastName"));
        account.setStatus(request.getParameter("account.email"));
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

        accountService.updateAccount(account);
        account = accountService.getAccount(account.getUsername());
        //myList = catalogService.getProductListByCategory(account.getFavouriteCategoryId());

        session.setAttribute("account", account);
        session.setAttribute("myList", myList);

        request.getRequestDispatcher(EDIT_ACCOUNT).forward(request, response);
        //return new RedirectResolution(CatalogActionBean.class);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
