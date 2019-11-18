
package com.controller;

import com.modelos.Conectar;
import com.modelos.Preguntas;
import com.modelos.Respuesta;
import com.modelos.RespuestaValidar;
import com.modelos.marcas;
import com.modelos.modeloValidar;
import com.modelos.modelos;
import java.sql.ResultSet;
import java.sql.SQLException;
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
@RequestMapping("agregarRespuesta.htm")
public class AgregarRespuestaController {
    modeloValidar validaModelo;
    private JdbcTemplate jdbcTemplate;
    
    public AgregarRespuestaController() {
        this.validaModelo = new modeloValidar();
        Conectar con = new Conectar();
        this.jdbcTemplate = new JdbcTemplate(con.conectar());
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public  ModelAndView form(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        int id =  Integer.parseInt(request.getParameter("idMarca"));
        marcas datos = this.SelectMarcas(id);
        
        mav.setViewName("agregarRespuesta");
        mav.addObject("marca", new marcas(id,datos.getNombreMarca()));
        mav.addObject("modelo", new modelos());
        return mav;
    }
    
    @RequestMapping(method=RequestMethod.POST)
    public ModelAndView form(@ModelAttribute("marca") modelos mod,
                                BindingResult result,
                                SessionStatus status,
                                HttpServletRequest request) {
    this.validaModelo.validate(mod, result);
    
    if(result.hasErrors()){
            ModelAndView mav = new ModelAndView();
            mav.setViewName("agregarRespuesta");
            mav.addObject("modelo", new Respuesta());
            
            int idEncuesta =  Integer.parseInt(request.getParameter("idMarca"));
            marcas datos = this.SelectMarcas(idEncuesta);
            mav.addObject("marca", new marcas(datos.getIdMarca(),datos.getNombreMarca()));
            return  mav;
        } else {
            ModelAndView mav = new ModelAndView();
            mav.setViewName("agregarRespuesta");
            mav.addObject("modelo", new Respuesta());
            
            this.jdbcTemplate.update("insert into modelos (idModelo,idMarca, nombreModelo, stock) values (?,?,?,?)",
                                     mod.getIdModelo(),mod.getIdMarca(), mod.getNombreModelo(), mod.getStock());
            return new ModelAndView("redirect:/consultar.htm?idMarca=" + mod.getIdMarca());
            }
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


