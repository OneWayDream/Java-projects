package ru.itis.filters;


import ru.itis.utils.StringRefactor;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Properties;

@WebFilter("/main")
public class MainPageFilter extends HttpFilter {

    Properties top3Properties;

    @Override
    public void init(FilterConfig config) throws ServletException {
        top3Properties = (Properties) config.getServletContext().getAttribute("top3Properties");
        super.init(config);
    }

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        req.setAttribute("top1nps", StringRefactor.remakeString(top3Properties.getProperty("top1nps")));
        req.setAttribute("top2nps", StringRefactor.remakeString(top3Properties.getProperty("top2nps")));
        req.setAttribute("top3nps", StringRefactor.remakeString(top3Properties.getProperty("top3nps")));
        req.setAttribute("top1npsProfit", top3Properties.getProperty("top1npsProfit"));
        req.setAttribute("top2npsProfit", top3Properties.getProperty("top2npsProfit"));
        req.setAttribute("top3npsProfit", top3Properties.getProperty("top3npsProfit"));
        req.setAttribute("top1bazaar", StringRefactor.remakeString(top3Properties.getProperty("top1bazaar")));
        req.setAttribute("top2bazaar", StringRefactor.remakeString(top3Properties.getProperty("top2bazaar")));
        req.setAttribute("top3bazaar", StringRefactor.remakeString(top3Properties.getProperty("top3bazaar")));
        req.setAttribute("top1bazaarProfit", top3Properties.getProperty("top1bazaarProfit"));
        req.setAttribute("top2bazaarProfit", top3Properties.getProperty("top2bazaarProfit"));
        req.setAttribute("top3bazaarProfit", top3Properties.getProperty("top3bazaarProfit"));
        req.setAttribute("nps1image", top3Properties.getProperty("top1nps"));
        req.setAttribute("nps2image", top3Properties.getProperty("top2nps"));
        req.setAttribute("nps3image", top3Properties.getProperty("top3nps"));
        req.setAttribute("bazaar1image", top3Properties.getProperty("top1bazaar"));
        req.setAttribute("bazaar2image", top3Properties.getProperty("top2bazaar"));
        req.setAttribute("bazaar3image", top3Properties.getProperty("top3bazaar"));
        chain.doFilter(req, res);
    }
}
