package org.launchcode.controllers;

import org.launchcode.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static java.lang.System.out;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", ListController.columnChoices);
        return "search";
    }

    @RequestMapping(value="results")
    public String search(Model model, @RequestParam String searchTerm, @RequestParam String searchType) {
        if (searchType.contains("all")) {
            ArrayList<HashMap<String, String>> allResults = JobData.findByValue(searchTerm);
            model.addAttribute("jobs", allResults);
            model.addAttribute("columns", ListController.columnChoices);

        } else {
            ArrayList<HashMap<String, String>> columnResults = JobData.findByColumnAndValue(searchType, searchTerm);
            model.addAttribute("columnJob", columnResults);
            model.addAttribute("columns", ListController.columnChoices);
        }

        return "/search";
    }
}

