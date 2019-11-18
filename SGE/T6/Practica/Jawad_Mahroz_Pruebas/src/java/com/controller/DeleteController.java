
package com.controller;

import com.modelos.Conectar;
import com.modelos.Preguntas;
import com.modelos.marcas;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("delete.htm")
public class DeleteController {

    private JdbcTemplate jdbcTemplate;
    
    public DeleteController() {
        Conectar con = new Conectar();
        this.jdbcTemplate = new JdbcTemplate(con.conectar());
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public  ModelAndView form(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        int id =  Integer.parseInt(request.getParameter("idMarca"));
        marcas datos = this.SelectMarcas(id);
        mav.setViewName("delete");
        mav.addObject("BorrarMarca", new marcas(datos.getIdMarca(), datos.getNombreMarca()));
        mav.addObject("nombreMarca", datos.getNombreMarca());
        return mav;
    }
    
    @RequestMapping(method=RequestMethod.POST)
    public ModelAndView form(@ModelAttribute("BorrarMarca") Preguntas preguntas,
                                HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("idMarca"));
        this.jdbcTemplate.update("delete from marcas where idMarca=?",id);
        return new ModelAndView("redirect:home.htm");
    } 

    private marcas SelectMarcas(int id) {

        final marcas marca = new marcas();
        String sql = "SELECT * FROM marcas where idMarca = '"+id+"'";
        return (marcas) jdbcTemplate.query(sql, new ResultSetExtractor<marcas>(){
            public marcas extractData(ResultSet rs) throws SQLException, DataAccessException {
                if (rs.next()) {
                    //marca.setIdMarca(rs.getInt("idMarca"));
                    marca.setNombreMarca(rs.getString("nombreMarca"));
                }
                return marca;
            }
        });
    }
}
