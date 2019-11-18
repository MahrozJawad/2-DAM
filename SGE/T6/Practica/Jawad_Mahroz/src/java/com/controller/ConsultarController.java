
package com.controller;

import com.modelos.Conectar;
import com.modelos.Preguntas;
import com.modelos.Respuesta;
import com.modelos.marcas;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("consultar.htm")
public class ConsultarController {
    
    private JdbcTemplate jdbcTemplate;
    
    public ConsultarController() {
        Conectar con = new Conectar();
        this.jdbcTemplate = new JdbcTemplate(con.conectar());
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public  ModelAndView form(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        int id =  Integer.parseInt(request.getParameter("idMarca"));
        marcas datos = this.SelectMarcas(id);
        
        String sql = "SELECT * FROM modelos where idMarca = '"+id+"'";
        List respuestas = jdbcTemplate.queryForList(sql);
        
        mav.setViewName("consultar");
        mav.addObject("marca", new marcas(id,datos.getNombreMarca()));
        mav.addObject("Modelos", respuestas);
        return mav;
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
    
//    private Preguntas SelectPreguntas(int id) {
//
//        final Preguntas pregunta = new Preguntas();
//        String sql = "SELECT * FROM encuesta where idEncuesta = '"+id+"'";
//        return (Preguntas) jdbcTemplate.query(sql, new ResultSetExtractor<Preguntas>(){
//            public Preguntas extractData(ResultSet rs) throws SQLException, DataAccessException {
//                if (rs.next()) {
//                    pregunta.setTextoPregunta(rs.getString("textoPregunta"));
//                }
//                return pregunta;
//            }
//        });
//    }

}
