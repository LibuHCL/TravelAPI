/**
 * 
 */
package com.assessment.lunatechlabs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * @author Libu
 *
 */
@Controller
public class HomeController {

	@RequestMapping("/")
    public String home() {
        return "index";
    }
}
