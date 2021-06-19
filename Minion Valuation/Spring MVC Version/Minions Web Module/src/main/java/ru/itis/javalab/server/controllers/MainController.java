package ru.itis.javalab.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.itis.javalab.server.annotations.Log;
import ru.itis.javalab.server.security.details.UserDetailsImpl;
import ru.itis.javalab.server.utils.MinionsContentManager;

@Controller
@Log
public class MainController {

    @Autowired
    protected MinionsContentManager minionsContentManager;

    @GetMapping("/main")
    public String getMainPage(Model model){
        try{
            UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (userDetails != null){
                model.addAttribute("user", userDetails.getUser());
            }
        } catch (ClassCastException ex){
            //ignore, its normal for AnonymousAuthentication
        }
        addMainPageContentAttributes(model);
        return "main";
    }

    protected void addMainPageContentAttributes(Model model){
        if (minionsContentManager.getIsConnectionError()){
            model.addAttribute("dataDownloadError", true);
        }
        model.addAttribute("minionsDataList", minionsContentManager.getMinionsDataList());
        model.addAttribute("fuelsDataList", minionsContentManager.getFuelsDataList());
        model.addAttribute("upgradesDataList", minionsContentManager.getUpgradesDataList());
        model.addAttribute("top3BazaarInfo", minionsContentManager.getTop3BazaarData());
        model.addAttribute("top3NpsInfo", minionsContentManager.getTop3NpsData());
    }

}
