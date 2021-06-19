package ru.itis.servlets;

import javafx.util.Pair;
import ru.itis.exceptions.MinionsDataRepositoryException;
import ru.itis.exceptions.NoSuchEntityException;
import ru.itis.models.Minion;
import ru.itis.models.Upgrade;
import ru.itis.models.User;
import ru.itis.services.MinionsDataService;
import ru.itis.utils.ProfitCalculator;
import ru.itis.utils.StringRefactor;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;


@WebServlet("/main")
public class MainServlet extends HttpServlet {

    protected MinionsDataService minionsDataService;

    @Override
    public void init(ServletConfig config) throws ServletException {

        this.minionsDataService = (MinionsDataService) config.getServletContext().getAttribute("minionsDataService");

        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setAttributes(req);
        String userBackground = (String) req.getSession().getAttribute("background");
        if (userBackground!=null){
            req.setAttribute("user_background", userBackground);
        }
        getServletContext().getRequestDispatcher("/WEB-INF/views/main-page.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String minionName = (req.getParameter("minion").equals("")) ? req.getParameter("reboot_minion") : req.getParameter("minion");
        int tier = Integer.parseInt(req.getParameter("tier"));
        String fuelName = (req.getParameter("fuel").equals("")) ? req.getParameter("reboot_fuel") : req.getParameter("fuel");
        if (fuelName.equals("SELECT")){
            fuelName="";
        }
        String upgrade1Name = (req.getParameter("upgrade1").equals("")) ? req.getParameter("reboot_upgrade1") : req.getParameter("upgrade1");
        if (upgrade1Name.equals("SELECT")){
            upgrade1Name="";
        }
        String upgrade2Name = (req.getParameter("upgrade2").equals("")) ? req.getParameter("reboot_upgrade2") : req.getParameter("upgrade2");
        if (upgrade2Name.equals("SELECT")){
            upgrade2Name="";
        }
        String userBackground = req.getParameter("user_background");
        try{
            int checkResult = ProfitCalculator.checkFormData(minionsDataService, minionName, upgrade1Name, upgrade2Name);
            switch (checkResult){
                case 1: req.setAttribute("errorMessage", "You have to choose a minion");
                    break;
                case 2: req.setAttribute("errorMessage", "Upgrade \"" + StringRefactor.remakeString(upgrade1Name) + "\" cannot be set on \"" + StringRefactor.remakeString(minionName) + "\".");
                    break;
                case 3: req.setAttribute("errorMessage", "Upgrade \"" + StringRefactor.remakeString(upgrade2Name) + "\" cannot be set on \"" + StringRefactor.remakeString(minionName) + "\".");
                    break;
                case 4: req.setAttribute("warningMessage", "Its useless to set two \"" + StringRefactor.remakeString(upgrade1Name) + "\", one of them will be used in the calculations.");
                case 5: double profit = ProfitCalculator.calculateProfit(minionsDataService, minionName, tier, fuelName,
                        upgrade1Name, upgrade2Name);
                    req.setAttribute("calculateResult", (int) profit);
                    break;
            }
        } catch (MinionsDataRepositoryException ex){
            req.setAttribute("errorMessage", "Something went wrong.");
        } catch (NoSuchEntityException ex){
            req.setAttribute("errorMessage", "Incorrect input data.");
        }
        req.setAttribute("minionValue", minionName);
        req.setAttribute("fuelValue", fuelName);
        req.setAttribute("upgrade1Value", upgrade1Name);
        req.setAttribute("upgrade2Value", upgrade2Name);
        req.setAttribute("tierValue", tier-1);
        req.setAttribute("isReq", "1");
        req.getSession().setAttribute("background", userBackground);
        req.setAttribute("user_background", userBackground);
        setAttributes(req);
        getServletContext().getRequestDispatcher("/WEB-INF/views/main-page.jsp").forward(req, resp);
    }

    protected void setAttributes(HttpServletRequest req){
        req.setAttribute("minionsList", minionsDataService.getAllMinions().stream().map(minion-> StringRefactor.remakeString(minion.getName())).collect(Collectors.toList()));
        req.setAttribute("minionsLogosList", minionsDataService.getAllMinions().stream().map(minion->minion.getName().trim()).collect(Collectors.toList()));
        req.setAttribute("fuelsList", minionsDataService.getAllFuels().stream().map(fuel->StringRefactor.remakeString(fuel.getName())).collect(Collectors.toList()));
        req.setAttribute("fuelsLogosList", minionsDataService.getAllFuels().stream().map(fuel->fuel.getName().trim()).collect(Collectors.toList()));
        req.setAttribute("upgradesList", minionsDataService.getAllUpgrades().stream().map(upgrade -> StringRefactor.remakeString(upgrade.getName())).collect(Collectors.toList()));
        req.setAttribute("upgradesLogosList", minionsDataService.getAllUpgrades().stream().map(upgrade->upgrade.getName().trim()).collect(Collectors.toList()));
        if (getServletContext().getAttribute("dataDownloadError")!=null){
            req.setAttribute("dataDownloadError", true);
        }
        if (req.getSession().getAttribute("user")!=null){
            User user = (User) req.getSession().getAttribute("user");
            req.setAttribute("login", user.getLogin());
            req.setAttribute("is_signed", true);
        }
    }
}
