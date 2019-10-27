package org.csu.mypetstore.web.servlets;

import org.csu.mypetstore.domain.Product;
import org.csu.mypetstore.service.CatalogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class SearchProductsServlet extends HttpServlet {
    private static final String SEARCH_PRODUCTS = "/WEB-INF/jsp/catalog/SearchProducts.jsp";

    private String keywords;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        keywords = request.getParameter("keywords");

        CatalogService service = new CatalogService();

        List<Product> productList = service.searchProductList(keywords);

        HttpSession session = request.getSession();
        session.setAttribute("productList", productList);

        request.getRequestDispatcher(SEARCH_PRODUCTS).forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
