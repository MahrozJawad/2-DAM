
package com.controller;

import com.modelos.Conectar;
import com.modelos.Preguntas;
import com.modelos.Respuesta;
import com.modelos.UsuariosValidar;
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
@RequestMapping("agregarRespuesta.htm")
public class AgregarRespuestaController {
UsuariosValidar usuariosValidar;
    private JdbcTemplate jdbcTemplate;
    
    public AgregarRespuestaController() {
        this.usuariosValidar = new UsuariosValidar();
        Conectar con = new Conectar();
        this.jdbcTemplate = new JdbcTemplate(con.conectar());
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public  ModelAndView form(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        int id =  Integer.parseInt(request.getParameter("idEncuesta"));
        Preguntas datos = this.SelectPreguntas(id);
        
        mav.setViewName("agregarRespuesta");
        mav.addObject("pregunta", new Preguntas(id,datos.getTextoPregunta()));
        mav.addObject("respuesta", new Respuesta());
        return mav;
    }
    
    @RequestMapping(method=RequestMethod.POST)
    public ModelAndView form(@ModelAttribute("respuesta") Respuesta resp) {
            ModelAndView mav = new ModelAndView();
            mav.setViewName("agregar");
            mav.addObject("respuesta", new Respuesta());
            
            this.jdbcTemplate.update("insert into respuesta (idRespuesta,idEncuesta, textoRespuesta, numeroRespuestas) values (?,?,?,?)",
                                     resp.getIdRespuesta(),resp.getIdEncuesta(), resp.getTextoRespuesta(), resp.getNumeroRespuestas());
            return new ModelAndView("redirect:/consultar.htm?idEncuesta=" + resp.getIdEncuesta());
        }
    
    private Preguntas SelectPreguntas(int id) {

        final Preguntas pregunta = new Preguntas();
        String sql = "SELECT * FROM encuesta where idEncuesta = '"+id+"'";
        return (Preguntas) jdbcTemplate.query(sql, new ResultSetExtractor<Preguntas>(){
            public Preguntas extractData(ResultSet rs) throws SQLException, DataAccessException {
                if (rs.next()) {
                    pregunta.setTextoPregunta(rs.getString("textoPregunta"));
                }
                return pregunta;
            }
        });
    }
    
    }


