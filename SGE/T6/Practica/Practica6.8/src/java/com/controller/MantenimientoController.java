
package com.controller;

import com.modulos.Conectar;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

public class MantenimientoController {
    JdbcTemplate jdbcTemplate;
    
    public MantenimientoController() {
        Conectar con = new Conectar();
        jdbcTemplate = new JdbcTemplate(con.conectar());
    }
    
    @RequestMapping("home")
    public ModelAndView home() {
        ModelAndView mav = new ModelAndView();
        String sql = "select * from usuarios";
        List datos = jdbcTemplate.queryForList(sql);
        mav.setViewName("home");
        mav.addObject("datos", datos);
        return mav;
    }
}
